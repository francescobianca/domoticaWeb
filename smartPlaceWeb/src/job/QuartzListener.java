package job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

import controllerAndroid.deleteActivity;
import persistence.DatabaseManager;
import persistence.PersistenceException;

@WebListener
public class QuartzListener extends QuartzInitializerListener {

	Scheduler scheduler = null;
	ReadActivity read = null;
	Connection connection = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		System.out.println("inizializzato QUARTZ");
		super.contextInitialized(sce);
		ServletContext ctx = sce.getServletContext();
		StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
		try {
			scheduler = factory.getScheduler();
			/*
			 * JobDetail jobDelete = JobBuilder.newJob(DeleteActivityJob.class).
			 * withIdentity("delete activity").build(); Trigger triggerDelete =
			 * TriggerBuilder.newTrigger()
			 * .withIdentity("trigger delete activity", "group1")
			 * .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ?"))
			 * .build(); scheduler.scheduleJob(jobDelete,triggerDelete);
			 */

			connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);

			String queryB = "update attivitaperiodica set schedulata=false";
			PreparedStatement preparedStatement = connection.prepareStatement(queryB);
			preparedStatement.executeUpdate();

			try {
				String query = "select * from attivitaperiodica where schedulata=false and (giornoFine > ? or (giornoFine=? and orarioFine >= ?))";
				PreparedStatement statement = connection.prepareStatement(query);

				Date today = calendar.getTime();
				long dateFine = today.getTime();
				statement.setDate(1, new java.sql.Date(dateFine));
				statement.setDate(2, new java.sql.Date(dateFine));
				long orarioFine = today.getTime();
				statement.setTime(3, new java.sql.Time(orarioFine));

				ResultSet resultSet = statement.executeQuery();

				while (resultSet.next()) {

					query = "select porta from arduino where \"indirizzoIP\" = ? ";
					statement = connection.prepareStatement(query);
					statement.setString(1, resultSet.getString("indirizzoIP"));

					System.out.println("ID JOB:" + resultSet.getInt("id"));

					ResultSet rs = statement.executeQuery();
					int porta = 0;
					if (rs.next()) {
						porta = rs.getInt("porta");
					}

					/* JOB che inizia l'attività */
					JobDetail job = JobBuilder.newJob(ActivityJob.class)
							.withIdentity(Integer.toString(resultSet.getInt("id")) + "inizio").build();
					JobDataMap map = job.getJobDataMap();

					map.put("id", resultSet.getInt("id"));
					map.put("indirizzoIP", resultSet.getString("indirizzoIP"));
					map.put("porta", porta);
					map.put("stanza", resultSet.getString("stanza"));
					map.put("tipo", resultSet.getString("tipo"));
					map.put("operazione", "accendi");

					Time timeSQL = resultSet.getTime("orarioInizio");
					@SuppressWarnings("deprecation")
					int minuti = timeSQL.getMinutes();
					@SuppressWarnings("deprecation")
					int ora = timeSQL.getHours();
					
					java.sql.Date dataSQL = resultSet.getDate("giornoInizio");
					java.sql.Date dataSQLFine = resultSet.getDate("giornoFine");
					System.out.println("dataSQL:  giorno "+dataSQL.getDate()+" mese "+dataSQL.getMonth()+" anno "+dataSQL.getYear());
					System.out.println("dataSQLFine:  giorno "+dataSQLFine.getDate()+" mese "+dataSQLFine.getMonth()+" anno "+dataSQLFine.getYear());
					
					
					@SuppressWarnings("deprecation")
					int giorno = dataSQL.getDate();

					@SuppressWarnings("deprecation")
					int mese = dataSQL.getMonth() + 1;

					@SuppressWarnings("deprecation")
					int anno = dataSQL.getYear() + 1900;

					// System.out.println("trigger:"+
					// Integer.toString(resultSet.getInt("id"))+"inizio-->"+"0 "
					// + minuti + " " + ora + " * * ?");
					Trigger trigger = null;

					if (dataSQL.equals(dataSQLFine)) {
						trigger = TriggerBuilder.newTrigger()
								.withIdentity("trigger:" + Integer.toString(resultSet.getInt("id")) + "inizio",
										"group1")
								.withSchedule(CronScheduleBuilder.cronSchedule(
										"0 " + minuti + " " + ora + " " + giorno + " " + mese + " ? " + anno))
								.build();
						System.out.println("stesso giorno trigger Inizio");
					} else {
						if (dataSQL.compareTo(new Date()) > 0) {
							trigger = TriggerBuilder.newTrigger()
									.withIdentity("trigger:" + Integer.toString(resultSet.getInt("id")) + "inizio",
											"group1")
									.withSchedule(
											CronScheduleBuilder.dailyAtHourAndMinute(ora, minuti))
									.startAt(dataSQL).endAt(dataSQLFine).build();
						} else {
							trigger = TriggerBuilder.newTrigger()
									.withIdentity("trigger:" + Integer.toString(resultSet.getInt("id")) + "inizio",
											"group1")
									.withSchedule(
											CronScheduleBuilder.dailyAtHourAndMinute(ora, minuti))
									.startAt(new Date()).endAt(dataSQLFine).build();
						}
					}
					/* JOB che chiude l'attività */
					JobDetail jobEnd = JobBuilder.newJob(ActivityJob.class)
							.withIdentity(Integer.toString(resultSet.getInt("id")) + "fine").build();
					JobDataMap mapEnd = jobEnd.getJobDataMap();
					mapEnd.put("id", resultSet.getInt("id"));
					mapEnd.put("indirizzoIP", resultSet.getString("indirizzoIP"));
					mapEnd.put("porta", porta);
					mapEnd.put("stanza", resultSet.getString("stanza"));
					mapEnd.put("tipo", resultSet.getString("tipo"));
					mapEnd.put("operazione", "spegni");

					Time timeSQLFine = resultSet.getTime("orarioFine");
					@SuppressWarnings("deprecation")
					int minutiFine = timeSQLFine.getMinutes();
					@SuppressWarnings("deprecation")
					int oraFine = timeSQLFine.getHours();

					Trigger triggerFine = null;

					if (dataSQL.equals(dataSQLFine)) {
						triggerFine = TriggerBuilder.newTrigger()
								.withIdentity("trigger:" + Integer.toString(resultSet.getInt("id")) + "fine", "group2")
								.withSchedule(CronScheduleBuilder.cronSchedule(
										"0 " + minutiFine + " " + oraFine + " " + giorno + " " + mese + " ? " + anno))
								.build();
						System.out.println("stesso giorno trigger fine");
					}

					else {
						if (dataSQL.compareTo(new Date()) > 0) {
							triggerFine = TriggerBuilder.newTrigger()
									.withIdentity("trigger:" + Integer.toString(resultSet.getInt("id")) + "fine",
											"group2")
									.withSchedule(CronScheduleBuilder
											.dailyAtHourAndMinute(oraFine, minutiFine))
									.startAt(dataSQL).endAt(dataSQLFine).build();
						} else {
							triggerFine = TriggerBuilder.newTrigger()
									.withIdentity("trigger:" + Integer.toString(resultSet.getInt("id")) + "fine",
											"group2")
									.withSchedule(CronScheduleBuilder
											.dailyAtHourAndMinute(oraFine, minutiFine))
									.startAt(new Date()).endAt(dataSQLFine).build();
						}
					}
					try {
						scheduler.scheduleJob(job, trigger);
						scheduler.scheduleJob(jobEnd, triggerFine);
					} catch (SchedulerException e) {
						e.printStackTrace();
					}

					query = "update attivitaperiodica set schedulata=true where id = ? ";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setInt(1, resultSet.getInt("id"));
					ps.executeUpdate();

				}

			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}

			scheduler.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

		read = new ReadActivity(scheduler, connection);
		read.start();

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		try {
			scheduler.shutdown();
			scheduler.clear();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
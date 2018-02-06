package job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import persistence.DatabaseManager;
import persistence.PersistenceException;

public class DeleteActivityJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Controllo job da eliminare");
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String delete = "delete from attivitaperiodica where giornoFine<=? and orarioFine<? ";
			PreparedStatement deleteStatement = connection.prepareStatement(delete);
			Date d = new Date();
			long date = d.getTime();

			deleteStatement.setDate(1, new java.sql.Date(date));
			deleteStatement.setTime(2, new java.sql.Time(date));

			deleteStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

}

package controllerAndroid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AttivitaPeriodica;
import model.Sensore;
import model.Utente;
import persistence.DatabaseManager;
import persistence.PersistenceException;
import persistence.dao.AttivitaPeriodicaDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

@SuppressWarnings("serial")
public class planActivity extends HttpServlet {

	String ip = "";
	int porta;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("programma un attivitÃ ");

		try {

			String utente = req.getParameter("utente");

			findInfo(utente);

			String nome = req.getParameter("nome");
			String giornoInizio = req.getParameter("giornoInizio");
			String giornoFine = req.getParameter("giornoFine");
			String oraInizio = req.getParameter("oraInizio");
			String oraFine = req.getParameter("oraFine");
			String sensore = req.getParameter("sensore");
			String stanza = req.getParameter("stanza");

			boolean c = noIncongruente(giornoInizio, giornoFine, oraInizio, oraFine, stanza, sensore);
			
			boolean trovaAttivita = false;
			Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
			try {
				String query = "select * from attivitaperiodica where utente = ? and nome = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				statement.setString(2, nome);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					trovaAttivita = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new PersistenceException(e.getMessage());
				}
			}

			if (!trovaAttivita) {

				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

				Date parsedDataInizio = format.parse(giornoInizio);
				Date parsedDataFine = format.parse(giornoFine);

				SimpleDateFormat orarioFormat = new SimpleDateFormat("HH:mm");
				Date parsedOrarioInizio = orarioFormat.parse(oraInizio);
				Date parsedOrarioFine = orarioFormat.parse(oraFine);

				@SuppressWarnings("deprecation")
				Time timeInizio = new Time(parsedOrarioInizio.getHours(), parsedOrarioInizio.getMinutes(),
						parsedOrarioFine.getSeconds());
				@SuppressWarnings("deprecation")
				Time timeFine = new Time(parsedOrarioFine.getHours(), parsedOrarioFine.getMinutes(),
						parsedOrarioFine.getSeconds());

				AttivitaPeriodica activity = new AttivitaPeriodica(nome);

				activity.setGiornoInizio(parsedDataInizio);
				activity.setGiornoFine(parsedDataFine);
				activity.setOrarioInizio(timeInizio);
				activity.setOrarioFine(timeFine);

				Utente u = new Utente();
				UtenteDao uDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
				u = uDao.findByPrimaryKey(utente);

				activity.setUtente(u);

				Sensore s = new Sensore();
				SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();

				s = sDao.findByPrimaryKey(ip, sensore, stanza);

				if (s == null)
					resp.getOutputStream().print("sensoreNonEsiste");
				resp.getOutputStream().flush();
				resp.getOutputStream().close();

				activity.setSensore(s);

				AttivitaPeriodicaDao activityDao = DatabaseManager.getInstance().getDaoFactory()
						.getAttivitaPeriodicaDAO();

				activityDao.save(activity);
			} else {
				resp.getOutputStream().print("attivitaGiaPresenteConLoStessoNome");
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			}

		} catch (Exception e) {
			resp.getOutputStream().print("errore");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		}

	}

	private void findInfo(String utente) {
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String query = "select \"indirizzoIP\",porta from arduino where utenteArduino=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				this.ip = result.getString("indirizzoIP");
				this.porta = result.getInt("porta");
			}
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

	private boolean noIncongruente(String giornoInizio, String giornoFine, String oraInizio, String oraFine,
			String stanza, String tipo) {

		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

			Date parsedDataInizio = format.parse(giornoInizio);
			Date parsedDataFine = format.parse(giornoFine);

			long dataInizioLong = parsedDataInizio.getTime();
			long dataFineLong = parsedDataFine.getTime();

			SimpleDateFormat orarioFormat = new SimpleDateFormat("HH:mm");
			Date parsedOrarioInizio = orarioFormat.parse(oraInizio);
			Date parsedOrarioFine = orarioFormat.parse(oraFine);

			long orarioInizioLong = parsedOrarioInizio.getTime();
			long orarioFineLong = parsedOrarioFine.getTime();

			String query = "select * from attivitaperiodica where indirizzoIP = ? and tipo = ? and stanza = ? and "
					+ " (( ? > giornoInizio and ? < giornoInizio) or (giornoInizio > ? and ? > giornoFine and giornoInizio > ? and ? > giornoFine)"
					+ "or (giornoInizio > ? and ? > giornoFine and ? < giornoFine))";
			
			 /*and"
				+ "((? >= orarioInizio) and (? <= orarioFine)) ";*/
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, ip);
			statement.setString(2, tipo);
			statement.setString(3, stanza);
			statement.setDate(4,  new java.sql.Date(dataInizioLong));
			statement.setDate(5,  new java.sql.Date(dataFineLong));
			statement.setDate(6,  new java.sql.Date(dataInizioLong));
			statement.setDate(7,  new java.sql.Date(dataInizioLong));
			statement.setDate(8,  new java.sql.Date(dataFineLong));
			statement.setDate(9,  new java.sql.Date(dataFineLong));
			statement.setDate(10,  new java.sql.Date(dataInizioLong));
			statement.setDate(11,  new java.sql.Date(dataInizioLong));
			statement.setDate(12,  new java.sql.Date(dataFineLong));
			//statement.setTime(13,  new java.sql.Time(orarioFineLong));
			//statement.setTime(14,  new java.sql.Time(orarioInizioLong));		
			
			System.out.println("sto eseguendo query incongruenze");
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				
				System.out.println("attività incongruente: "+result.getInt("id"));
				System.out.println(new java.sql.Time(orarioFineLong)+" > "+result.getTime("orarioInizio").toString());
				System.out.println(new java.sql.Time(orarioInizioLong)+ " < " +result.getTime("orarioFine").toString());			
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return false;

	}

}
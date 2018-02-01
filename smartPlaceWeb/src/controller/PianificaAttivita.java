package controller;

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
import javax.servlet.http.HttpSession;

import model.AttivitaPeriodica;
import model.Sensore;
import model.Utente;
import persistence.DatabaseManager;
import persistence.PersistenceException;
import persistence.dao.AttivitaPeriodicaDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

@SuppressWarnings("serial")
public class PianificaAttivita extends HttpServlet {

	String ip = "";
	int porta;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Sono nella servlet pianificaAttività web");

		try {

			HttpSession session = req.getSession();
			String utente = req.getParameter(""); // Da stabilire come viene passato il parametro.

			findInfo(utente);

			String nome = req.getParameter(""); // Da stabilire come viene passato il parametro.
			String giornoInizio = req.getParameter(""); // Da stabilire come viene passato il parametro.
			String giornoFine = req.getParameter(""); // Da stabilire come viene passato il parametro.
			String oraInizio = req.getParameter(""); // Da stabilire come viene passato il parametro.
			String oraFine = req.getParameter(""); // Da stabilire come viene passato il parametro.
			String sensore = req.getParameter(""); // Da stabilire come viene passato il parametro.
			String stanza = req.getParameter(""); // Da stabilire come viene passato il parametro.

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

			activity.setSensore(s);

			AttivitaPeriodicaDao activityDao = DatabaseManager.getInstance().getDaoFactory().getAttivitaPeriodicaDAO();

			activityDao.save(activity);

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

}
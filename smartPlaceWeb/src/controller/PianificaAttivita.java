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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Sono nella servlet pianificaAttivit√† web");

		try {

			HttpSession session = req.getSession();
			String utente = (String) session.getAttribute("email");

			String ip = findInfo(utente);

			String nome = req.getParameter("nome");
			String giornoInizio = req.getParameter("giornoInizio");
			String giornoFine = req.getParameter("giornoFine");
			String oraInizio = req.getParameter("oraInizio");
			String oraFine = req.getParameter("oraFine");
			String sensore = req.getParameter("tipo");
			String stanza = req.getParameter("stanza");
			System.err.println("---------------------------------");
			System.out.println(nome);
			System.out.println(giornoInizio);
			System.out.println(giornoFine);
			System.out.println(oraInizio);
			System.out.println(oraFine);
			System.out.println(sensore);
			System.out.println(stanza);
			System.err.println("-----------------------------------");
			boolean congruente = noIncongruente(ip, giornoInizio, giornoFine, oraInizio, oraFine, stanza, sensore);

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

			if (congruente) {

				if (!trovaAttivita) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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

					if (s == null) {
						resp.getOutputStream().print("sensoreNonEsiste");
						resp.getOutputStream().flush();
						resp.getOutputStream().close();
						return ;
					}
					activity.setSensore(s);

					AttivitaPeriodicaDao activityDao = DatabaseManager.getInstance().getDaoFactory()
							.getAttivitaPeriodicaDAO();

					activityDao.save(activity);
					resp.getOutputStream().print("salvata");
					resp.getOutputStream().flush();
					resp.getOutputStream().close();
					return ;
				} else {
					resp.getOutputStream().print("attivitaGiaPresenteConLoStessoNome");
					resp.getOutputStream().flush();
					resp.getOutputStream().close();
					return ;
				}
			} else {
				resp.getOutputStream().print("attivitaIncoerente");
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
				return ;
			}

		} catch (Exception e) {
			resp.getOutputStream().print("errore");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
			System.out.println(e.getMessage());
		}

	}

	private String findInfo(String utente) {
		String ip = "";
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String query = "select \"indirizzoIP\",porta from arduino where utenteArduino=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				ip = result.getString("indirizzoIP");
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
		return ip;
	}

	private boolean noIncongruente(String ip, String giornoInizio, String giornoFine, String oraInizio, String oraFine,
			String stanza, String tipo) {
		System.err.println("ip: "+ip);
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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
					+ " (( ? < giornoInizio and ? < giornoFine) or (? >= giornoInizio and ? <= giornoFine)"
					+ "or (? >= giornoInizio and giornoFine > ? and ? > giornoFine) or (? < giornoInizio and ? > giornoFine)) "
					+ "and ((? >= orarioInizio) and (? <= orarioFine))";

			// Se un'attivit‡ rientra in questa query Ë incongruente.

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, ip);
			statement.setString(2, tipo);
			statement.setString(3, stanza);
			statement.setDate(4, new java.sql.Date(dataInizioLong));
			statement.setDate(5, new java.sql.Date(dataFineLong));
			statement.setDate(6, new java.sql.Date(dataInizioLong));
			statement.setDate(7, new java.sql.Date(dataFineLong));
			statement.setDate(8, new java.sql.Date(dataInizioLong));
			statement.setDate(9, new java.sql.Date(dataInizioLong));
			statement.setDate(10, new java.sql.Date(dataFineLong));
			statement.setDate(11, new java.sql.Date(dataInizioLong));
			statement.setDate(12, new java.sql.Date(dataFineLong));
			statement.setTime(13, new java.sql.Time(orarioFineLong));
			statement.setTime(14, new java.sql.Time(orarioInizioLong));

			System.out.println("sto eseguendo query incongruenze");

			ResultSet result = statement.executeQuery();

			boolean condition = true;
			while (result.next()) {

				System.out.println("attivit‡ incongruente: " + result.getInt("id"));
				condition = false;
			}

			return condition;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

}
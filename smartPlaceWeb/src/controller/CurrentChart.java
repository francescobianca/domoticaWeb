package controller;

import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Misurazione;
import persistence.DatabaseManager;
import persistence.PersistenceException;

@SuppressWarnings("serial")
public class CurrentChart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("sono nella servlet current chart");

		String email = (String) req.getSession().getAttribute("email");
		String indirizzoArduino = findInfo(email);

		System.out.println(email);

		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		// Misurazioni temperatura
		ResultSet setTemperatura = null;
		try {
			String query = "select giorno,ora,valore from misurazione where \"arduino_indirizzoip\"=? and giorno = ? and tipo=? order by ora";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, indirizzoArduino);

			Date today = new Date(); // current day
			statement.setDate(2, new java.sql.Date(today.getTime()));
			statement.setString(3, "temperatura");

			setTemperatura = statement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

		// Misurazioni umidita
		connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		ResultSet setUmidita = null;
		try {
			String query = "select giorno,ora,valore from misurazione where \"arduino_indirizzoip\"=? and giorno = ? and tipo=? order by ora";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, indirizzoArduino);

			Date today = new Date(); // current day
			statement.setDate(2, new java.sql.Date(today.getTime()));
			statement.setString(3, "umidità");

			setUmidita = statement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

		ArrayList<Misurazione> misurazioniTemperatura = new ArrayList<>();
		ArrayList<Misurazione> misurazioniUmidita = new ArrayList<>();
		
		ArrayList<ArrayList<Misurazione>> misurazioniFinali = new ArrayList<>();
		misurazioniFinali.add(misurazioniTemperatura);
		misurazioniFinali.add(misurazioniUmidita);
		
		Misurazione mTemp = null;
		Misurazione mUmid = null;
		
		try {

			while (setTemperatura.next()) {

				long date = setTemperatura.getDate("giorno").getTime();
				long secs = setTemperatura.getTime("ora").getTime();

				mTemp = new Misurazione();

				mTemp.setGiorno(new java.util.Date(date));
				mTemp.setOra(new java.util.Date(secs));
				mTemp.setValore(setTemperatura.getFloat("valore"));

				misurazioniTemperatura.add(mTemp);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {

			while (setUmidita.next()) {

				long date = setUmidita.getDate("giorno").getTime();
				long secs = setUmidita.getTime("ora").getTime();

				mUmid = new Misurazione();

				mUmid.setGiorno(new java.util.Date(date));
				mUmid.setOra(new java.util.Date(secs));
				mUmid.setValore(setUmidita.getFloat("valore"));

				misurazioniUmidita.add(mUmid);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String valoreProva = (new JSONArray(misurazioniFinali).toString());
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.getWriter().print(valoreProva);

	}

	private String findInfo(String utente) {
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		String ip = null;
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

}

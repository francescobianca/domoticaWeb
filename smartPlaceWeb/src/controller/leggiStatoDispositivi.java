/**
 * 
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Arduino;
import model.Sensore;
import persistence.DatabaseManager;
import persistence.PersistenceException;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public class leggiStatoDispositivi extends HttpServlet {

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Okkk");
		String email=(String) req.getSession().getAttribute("email");
		String indirizzoArduino=findInfo(email);

		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		ResultSet set=null;
		try {
			String query = "select * from sensore where \"arduino_indirizzoIP\"=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, indirizzoArduino);
			set = statement.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
	    req.setAttribute("luce_bagno",getSensore(set,"luce","bagno"));
	    req.setAttribute("luce_cucina", getSensore(set,"luce","cucina"));
		RequestDispatcher disp=req.getRequestDispatcher("dashboard.jsp");
		disp.forward(req, resp);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Okkk");
		String email=(String) req.getSession().getAttribute("email");
		String indirizzoArduino=findInfo(email);

		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		ResultSet set=null;
		try {
			String query = "select * from sensore where \"arduino_indirizzoIP\"=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, indirizzoArduino);
			set = statement.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
	    req.setAttribute("luce_bagno",getSensore(set,"luce","bagno"));
	    req.setAttribute("luce_cucina", getSensore(set,"luce","cucina"));
		RequestDispatcher disp=req.getRequestDispatcher("dashboard.jsp");
		disp.forward(req, resp);
		
	}

	private Sensore getSensore(ResultSet set, String tipo, String stanza) {
		Sensore s = null;
		try {
			while (set.next()) {
				if (set.getString("tipo").equals(tipo) && set.getString("stanza").equals(stanza)) {
					s = new Sensore(tipo, stanza);
					s.setStato(set.getInt("stato"));
					return s;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
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

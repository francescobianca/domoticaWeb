/**
 * 
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Arduino;
import model.Sensore;
import model.Utente;
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
			
		ArrayList<Sensore> sensori=createList(set);
		for(Sensore s:sensori){
			req.setAttribute(s.getTipo()+"_"+s.getStanza(),s);
		}
		
		HttpSession session = req.getSession();

		RequestDispatcher disp=req.getRequestDispatcher("dashboard.jsp");
		disp.forward(req, resp);
		
	}
	

	private ArrayList<Sensore> createList(ResultSet set) {
		ArrayList<Sensore> sensori=new ArrayList<>();
		try {
			while (set.next()) {
				Sensore s = null;
				s=new Sensore(set.getString("tipo"),set.getString("stanza"));
				s.setStato(set.getInt("stato"));
				sensori.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sensori;
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

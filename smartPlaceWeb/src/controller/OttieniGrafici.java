package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.Misurazione;
import persistence.DatabaseManager;
import persistence.PersistenceException;

@SuppressWarnings("serial")
public class OttieniGrafici extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("sono nella servlet ottieni chart");
		
		String email = (String) req.getSession().getAttribute("email");
		String indirizzoArduino = findInfo(email);
		
		String giornoInizio = req.getParameter("giornoInizio");
		String giornoFine = req.getParameter("giornoFine");
		String tipo = req.getParameter("tipo");

		ResultSet set = null;
				
		if (tipo.equals("Temperatura")) {
			set = findValue(giornoInizio, giornoFine, "temperatura", indirizzoArduino);
		}
		else if (tipo.equals("Umidita")) {
			set = findValue(giornoInizio, giornoFine, "umidità", indirizzoArduino);
		}

		ArrayList<Misurazione> misurazioni = new ArrayList<>();
		Misurazione m = null;
		
		boolean trovaMisurazioni=false;
		
		try {

			while (set.next()) {
				
				trovaMisurazioni = true;
							
				long date = set.getDate("giorno").getTime();
				long secs = set.getTime("ora").getTime();

				m = new Misurazione();

				m.setGiorno(new java.util.Date(date));
				m.setOra(new java.util.Date(secs));
				m.setValore(set.getFloat("valore"));

				misurazioni.add(m);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(trovaMisurazioni) {
			String valori = (new JSONArray(misurazioni).toString());
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().print(valori);
		} else {
			resp.getWriter().print("noDati");
		}
			
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
	
	private ResultSet findValue(String dataInizio, String dataFine,String tipo,String ip) {
		
		ResultSet rs = null;
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String query = "select giorno,ora,valore from misurazione where \"arduino_indirizzoip\"=? and giorno between ? and ? and tipo=?  ORDER BY giorno,ora";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, ip);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN);
			Date parsed = format.parse(dataInizio);
			
			java.sql.Date inizio = new java.sql.Date(parsed.getTime());

			statement.setDate(2, inizio);

			Date parsed1 = format.parse(dataFine);
			java.sql.Date fine = new java.sql.Date(parsed1.getTime());

			statement.setDate(3, fine);
			statement.setString(4, tipo);
			rs = statement.executeQuery();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return rs;
	}
	
}

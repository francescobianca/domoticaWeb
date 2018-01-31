package controllerAndroid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DatabaseManager;
import persistence.PersistenceException;

@SuppressWarnings("serial")
public class humidityChart extends HttpServlet {

	String ip = "";
	int porta;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Sono nella servlet humidity chart ");

		try {

			String utente = req.getParameter("utente");
			findInfo(utente);

			String dataInizio = req.getParameter("data_inizio");
			String dataFine = req.getParameter("data_fine");

			ResultSet r = findHumidity(dataInizio, dataFine);
			boolean trovaDati=false;
			String valoriStatistica="";
			
			while (r.next()) {
				trovaDati=true;
				
				valoriStatistica+=r.getDate("giorno") + " " + r.getFloat("valore");
				valoriStatistica+="/";

			}
			if (trovaDati)
				resp.getOutputStream().print(valoriStatistica);
			else 
				resp.getOutputStream().print("datiNonPresenti");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
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
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	private ResultSet findHumidity(String dataInizio, String dataFine) {

		ResultSet rs = null;
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String query = "select giorno,ora,valore from misurazione where \"arduino_indirizzoip\"=? and giorno between ? and ? and tipo=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, ip);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date parsed = format.parse(dataInizio);
			java.sql.Date inizio = new java.sql.Date(parsed.getTime());

			// long inizio = Long.parseLong(dataInizio);
			statement.setDate(2, inizio);

			Date parsed1 = format.parse(dataFine);
			java.sql.Date fine = new java.sql.Date(parsed1.getTime());

			// long fine = Long.parseLong(dataFine);
			statement.setDate(3, fine);
			statement.setString(4, "umidità");
			rs = statement.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
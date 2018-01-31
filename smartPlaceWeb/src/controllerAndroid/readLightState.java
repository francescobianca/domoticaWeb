package controllerAndroid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DatabaseManager;
import persistence.PersistenceException;

@SuppressWarnings("serial")
public class readLightState extends HttpServlet {

	String ip = "";
	int porta;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("read light state");

		try {

			String utente = req.getParameter("utente");

			findInfo(utente);

			ResultSet rs = findState(ip);

			if (rs != null) {

				while (rs.next()) {

					resp.getOutputStream().print(rs.getString("stanza") + "-" + rs.getInt("stato"));
					resp.getOutputStream().print("/");

				}
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			}

			else {
				resp.getOutputStream().print("errore");
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

	private ResultSet findState(String indirizzoIP) {

		ResultSet rs = null;
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String update = "select stanza,stato from sensore where \"arduino_indirizzoIP\"=? and tipo=? ";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, indirizzoIP);
			statement.setString(2, "luce");
			rs = statement.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

		return rs;
	}

}
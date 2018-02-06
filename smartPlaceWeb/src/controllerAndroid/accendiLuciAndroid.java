package controllerAndroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
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
public class accendiLuciAndroid extends HttpServlet {

	String ip = "";
	int porta;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BufferedReader in = null;
		PrintStream out = null;
		Socket socket = null;

		System.out.println("Sono nella servlet accendi luci android ");

		try {

			String utente = req.getParameter("utente");

			findInfo(utente);

			// open a socket connection
			socket = new Socket(ip, porta);
			// Apre i canali I/O
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream(), true);

			String name = "";
			String stanza = req.getParameter("stanza");
			String stato = req.getParameter("stato");

			if (stanza.equals("bagno")) {
				if (stato.equals("1"))
					name = "1";
				else if (stato.equals("0"))
					name = "0";
			} else if (stanza.equals("salone")) {
				if (stato.equals("1"))
					name = "3";
				else if (stato.equals("0"))
					name = "2";
			} else if (stanza.equals("cucina")) {
				if (stato.equals("1"))
					name = "a";
				else if (stato.equals("0"))
					name = "b";
			} else if (stanza.equals("cameraLetto")) {
				if (stato.equals("1"))
					name = "c";
				else if (stato.equals("0"))
					name = "d";
			}

			// Invio nuovo stato da impostare al server di arduino
			out.println("setta led");
			out.println(name);
			out.flush();

			out.close();
			in.close();
			salvaStato(ip, stanza, stato);

			resp.getOutputStream().print("ledSettato");
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
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	private void salvaStato(String indirizzoIP, String stanza, String stato) {
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String update = "update sensore set stato = ? where \"arduino_indirizzoIP\" = ? and stanza = ? and tipo = ?";
			PreparedStatement updateStatement = connection.prepareStatement(update);
			updateStatement.setInt(1, Integer.parseInt(stato));
			updateStatement.setString(2, indirizzoIP);
			updateStatement.setString(3, stanza);
			updateStatement.setString(4, "luce");
			updateStatement.executeUpdate();
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
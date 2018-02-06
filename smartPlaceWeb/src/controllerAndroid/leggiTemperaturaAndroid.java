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
public class leggiTemperaturaAndroid extends HttpServlet {

	String ip = "";
	int porta;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BufferedReader in = null;
		PrintStream out = null;
		Socket socket = null;

		System.out.println("Sono nella servlet leggi temperatura android ");

		try {

			String utente = req.getParameter("utente");
			findInfo(utente);

			// open a socket connection
			socket = new Socket(ip,porta);
			// Apre i canali I/O
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream(), true);

			String name = "";
			String stanza = req.getParameter("stanza");

			if (stanza.equals("casa")) {
				name = "9";
			}

			// Invio nuovo stato da impostare al server di arduino
			out.println("readData");
			out.println(name);
			out.flush();

			String s = in.readLine();
			
			String[] date = s.split("/");
			String temp = date[1];

			out.close();
			in.close();

			resp.getOutputStream().print(temp);
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

}
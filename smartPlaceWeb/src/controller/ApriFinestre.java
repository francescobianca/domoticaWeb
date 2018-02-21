package controller;

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
import javax.servlet.http.HttpSession;

import persistence.DatabaseManager;
import persistence.PersistenceException;

@SuppressWarnings("serial")
public class ApriFinestre extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BufferedReader in = null;
		PrintStream out = null;
		Socket socket = null;

		System.out.println("Sono nella servlet apri finestre web");

		try {

			HttpSession session = req.getSession();

			String utente = (String)session.getAttribute("email");
			String ip="";
			int porta=0;
			
			Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
			try {
				String query = "select \"indirizzoIP\",porta from arduino where utenteArduino=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					ip = result.getString("indirizzoIP");
					porta = result.getInt("porta");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			connection.close();
			
			System.err.println(ip);
			System.err.println(porta);
			
			socket = new Socket(ip, porta);
			// Apre i canali di I/O
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream(), true);

			String name = "";
			String stanza = req.getParameter(""); // Da stabilire come viene passato il parametro.
			String stato = req.getParameter(""); // Da stabilire come viene passato il parametro.

			if (stanza.equals("bagno")) {
				if (stato.equals("up"))
					name = "7";
				else if (stato.equals("down"))
					name = "8";
			}

			// Invio nuovo stato da impostare al server di arduino
			out.println("setta led");
			out.println(name);
			out.flush();

			System.out.println(in.readLine());

			out.close();
			in.close();
			
			int newValue=salvaStato(ip, stanza, stato);
			resp.getWriter().print(newValue);
			resp.getWriter().flush();
		} catch (Exception e) {
			resp.getWriter().print("errore");
			resp.getWriter().flush();
			System.out.println(e.getMessage());
		}
	}

	private int salvaStato(String indirizzoIP, String stanza, String stato) {
		int value=0;
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String query = "select stato from sensore where \"arduino_indirizzoIP\" = ? and stanza = ? and tipo = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, indirizzoIP);
			statement.setString(2, stanza);
			statement.setString(3, "finestra");
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				value = rs.getInt("stato");

			String update = "update sensore set stato = ? where \"arduino_indirizzoIP\" = ? and stanza = ? and tipo = ?";
			statement = connection.prepareStatement(update);

			if (value < 180)
				if (stato.equals("up")) {
					value += 30;
				}

			if (value > 0)
				if (stato.equals("down")) {
					value -= 30;
				}

			statement.setInt(1, value);
			statement.setString(2, indirizzoIP);
			statement.setString(3, stanza);
			statement.setString(4, "finestra");
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return value;
	}

}

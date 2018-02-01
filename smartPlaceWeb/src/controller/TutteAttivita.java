package controller;

import java.io.IOException;
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

//Servlet che legge tutte le attività --> Bisogna mapparla nel web.xml

@SuppressWarnings("serial")
public class TutteAttivit� extends HttpServlet {

	String utente = "";
	String tipo = "";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Sono nella servlet tutte attività web ");

		try {

			HttpSession session = req.getSession();
			
			utente = req.getParameter(""); // Da stabilire come viene passato il parametro.
			tipo = req.getParameter(""); // Da stabilire come viene passato il parametro.

			ResultSet r = findActivity();
			boolean trovaDati = false;
			String activity = "";

			while (r.next()) {

				trovaDati = true;

				activity += r.getString("nome") + "," + r.getDate("giornoInizio") + "," + r.getTime("orarioInizio")
						+ "," + r.getDate("giornoFine") + "," + r.getTime("orarioFine");
				activity += "/";
			}

			if (trovaDati)
				System.out.println(activity);
			else
				System.out.println("nessun attività");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private ResultSet findActivity() {

		ResultSet rs = null;
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			if (tipo.equals("temperatura")) {
				String query = "select * from attivitaperiodica where utente = ? and (tipo = ? or tipo = ?) ";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				statement.setString(2, "ventilatore");
				statement.setString(3, "riscaldamenti");
				rs = statement.executeQuery();
			} else {
				String query = "select * from attivitaperiodica where utente = ? and tipo = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				statement.setString(2, tipo);
				rs = statement.executeQuery();
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
		return rs;
	}

}
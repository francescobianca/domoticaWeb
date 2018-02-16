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
public class findAllActivity extends HttpServlet {

	String utente = "";
	String tipo = "";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Sono nella servlet find all activity");

		try {
			utente = req.getParameter("utente");
			tipo = req.getParameter("tipo");

			ResultSet r = findActivity();
			boolean trovaDati = false;
			String activity = "";
			
			if (tipo.equals("temperatura") || tipo.equals("cancello")) {
				while (r.next()) {

					trovaDati = true;

					activity += r.getString("nome") + "," + r.getDate("giornoInizio") + "," + r.getTime("orarioInizio")
							+ "," + r.getDate("giornoFine") + "," + r.getTime("orarioFine") + "," + r.getString("tipo");
					activity += "/";
				}
			} else {
				while (r.next()) {

					trovaDati = true;

					activity += r.getString("nome") + "," + r.getDate("giornoInizio") + "," + r.getTime("orarioInizio")
							+ "," + r.getDate("giornoFine") + "," + r.getTime("orarioFine") + ","
							+ r.getString("stanza");
					activity += "/";
				}
			}
			if (trovaDati)
				resp.getOutputStream().print(activity);
			else
				resp.getOutputStream().print("NessunaAttivitaPresente");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		} catch (Exception e) {
			resp.getOutputStream().print("errore");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
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
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
public class calendarActivity extends HttpServlet {

	String utente = "";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Sono nella servlet calendar activity");

		try {
			utente = req.getParameter("utente");

			ResultSet r = findActivity();
			boolean trovaDati = false;
			String activity = "";

			while (r.next()) {

				trovaDati = true;

				if (r.getString("tipo").equals("ventilatore")) {
					activity += r.getString("nome") + "," + r.getDate("giornoInizio") + "," + r.getTime("orarioInizio")
							+ "," + r.getDate("giornoFine") + "," + r.getTime("orarioFine") + "," + r.getString("tipo")
							+ "," + "ventilatore";
					activity += "/";
				} else if (r.getString("tipo").equals("riscaldamento")) {
					activity += r.getString("nome") + "," + r.getDate("giornoInizio") + "," + r.getTime("orarioInizio")
							+ "," + r.getDate("giornoFine") + "," + r.getTime("orarioFine") + "," + r.getString("tipo")
							+ "," + "riscaldamento";
					activity += "/";
				} else if (r.getString("tipo").equals("deumidificatore")) {
					activity += r.getString("nome") + "," + r.getDate("giornoInizio") + "," + r.getTime("orarioInizio")
							+ "," + r.getDate("giornoFine") + "," + r.getTime("orarioFine") + "," + r.getString("tipo")
							+ "," + "deumidificatore";
					activity += "/";
				} else if (r.getString("tipo").equals("cancello")) {
					activity += r.getString("nome") + "," + r.getDate("giornoInizio") + "," + r.getTime("orarioInizio")
					+ "," + r.getDate("giornoFine") + "," + r.getTime("orarioFine") + "," + r.getString("tipo")
					+ "," + "cancello";
					activity += "/";
				}
				else {
					activity += r.getString("nome") + "," + r.getDate("giornoInizio") + "," + r.getTime("orarioInizio")
							+ "," + r.getDate("giornoFine") + "," + r.getTime("orarioFine") + "," + r.getString("tipo")
							+ "," + r.getString("stanza");
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
			String query = "select * from attivitaperiodica where utente = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			rs = statement.executeQuery();
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

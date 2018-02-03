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
public class findAllRules extends HttpServlet {

	String utente = "";
	String tipo = "";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Sono nella servlet find all rules");

		try {

			utente = req.getParameter("utente");
			tipo = req.getParameter("tipo");

			ResultSet r = findRules();
			boolean trovaDati = false;
			String rule = "";

			while (r.next()) {

				trovaDati = true;

				rule += r.getString("nome") + "," + r.getFloat("valoreMisurazione") + "," + r.getString("tipo");
				rule += "/";

			}

			if (trovaDati)
				resp.getOutputStream().print(rule);
			else
				resp.getOutputStream().print("NessunaRegolaPresente");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		} catch (Exception e) {
			resp.getOutputStream().print("errore");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		}
		
	}

	private ResultSet findRules() {

		ResultSet rs = null;
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			if (tipo.equals("temperatura")) {
				String query = "select * from regola where utente = ? and (tipo = ? or tipo = ?) ";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				statement.setString(2, "ventilatore");
				statement.setString(3, "riscaldamenti");
				rs = statement.executeQuery();
			} else {
				String query = "select * from regola where utente = ? and tipo = ?";
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
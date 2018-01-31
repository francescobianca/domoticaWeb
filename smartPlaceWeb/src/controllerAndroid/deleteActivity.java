package controllerAndroid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DatabaseManager;
import persistence.PersistenceException;

//Servlet che legge tutte le attività --> Bisogna mapparla nel web.xml

@SuppressWarnings("serial")
public class deleteActivity extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Sono nella servlet delete activity");

		try {
			String utente = req.getParameter("utente");

			String nomeActivity = req.getParameter("nome");

			Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
			try {
				String query = "delete from attivitaperiodica where utente = ? and nome = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				statement.setString(2, nomeActivity);
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
			resp.getOutputStream().print("eliminata");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		} catch (Exception e) {
			resp.getOutputStream().print("errore");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		}
	}
}
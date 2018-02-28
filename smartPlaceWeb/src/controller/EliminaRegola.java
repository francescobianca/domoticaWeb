/**
 * 
 */
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

import model.AttivitaPeriodica;
import model.Regola;
import model.Sensore;
import model.Utente;
import persistence.DatabaseManager;
import persistence.PersistenceException;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public class EliminaRegola extends HttpServlet{

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Sono nella servlet elimina regola web");
		/*
		Regola r = DatabaseManager.getInstance().getDaoFactory().getRegolaDAO()
				.findByPrimaryKey(Integer.parseInt(req.getParameter("id")));
		DatabaseManager.getInstance().getDaoFactory().getRegolaDAO().delete(r);
	*/

		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String delete = "delete FROM regola WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1,Integer.parseInt(req.getParameter("id")));
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
		
		resp.getWriter().print("eliminata");
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}

package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AttivitaPeriodica;
import persistence.DatabaseManager;
import persistence.PersistenceException;
import persistence.dao.AttivitaPeriodicaDao;

@SuppressWarnings("serial")
public class EliminaAttivita extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Sono nella servlet elimina attivita web");

		AttivitaPeriodica a = DatabaseManager.getInstance().getDaoFactory().getAttivitaPeriodicaDAO()
				.findByPrimaryKey(Integer.parseInt(req.getParameter("id")));
		DatabaseManager.getInstance().getDaoFactory().getAttivitaPeriodicaDAO().delete(a);

		resp.getWriter().print("eliminata");
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
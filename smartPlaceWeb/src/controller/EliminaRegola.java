/**
 * 
 */
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AttivitaPeriodica;
import model.Regola;
import persistence.DatabaseManager;

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

		Regola r = DatabaseManager.getInstance().getDaoFactory().getRegolaDAO()
				.findByPrimaryKey(Integer.parseInt(req.getParameter("id")));
		DatabaseManager.getInstance().getDaoFactory().getRegolaDAO().delete(r);

		resp.getWriter().print("eliminata");
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}

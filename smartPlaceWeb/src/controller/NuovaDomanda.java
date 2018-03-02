package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Domanda;
import persistence.DatabaseManager;

@SuppressWarnings("serial")
public class NuovaDomanda extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String utente = (String) req.getSession().getAttribute("email");
			String title = req.getParameter("title");
			String text = req.getParameter("text");
			String categoria = req.getParameter("categoria");

			Domanda d = new Domanda(title);
			d.setTesto(text);
			d.setCategoria(DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().findByPrimaryKey(categoria));
			d.setUtente(DatabaseManager.getInstance().getDaoFactory().getUtenteDAO().findByPrimaryKey(utente));

			DatabaseManager.getInstance().getDaoFactory().getDomandaDAO().save(d);
		} catch (Exception e) {
			resp.getWriter().print("errore");
			resp.getWriter().flush();
			resp.getWriter().close();
		}

	}
}

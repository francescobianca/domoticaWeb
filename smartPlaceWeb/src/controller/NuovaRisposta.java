package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Risposta;
import persistence.DatabaseManager;

@SuppressWarnings("serial")
public class NuovaRisposta extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			String text = req.getParameter("text");

			Risposta r = new Risposta(text);
			r.setDomanda(DatabaseManager.getInstance().getDaoFactory().getDomandaDAO().findByPrimaryKey(id));
			r.setUtente(DatabaseManager.getInstance().getDaoFactory().getUtenteDAO()
					.findByPrimaryKey((String) req.getSession().getAttribute("email")));

			DatabaseManager.getInstance().getDaoFactory().getRispostaDAO().save(r);
		} catch (Exception e) {
			resp.getWriter().print("errore");
			resp.getWriter().flush();
			resp.getWriter().close();
		}
		
	}

}

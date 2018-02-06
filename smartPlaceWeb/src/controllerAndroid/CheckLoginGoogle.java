package controllerAndroid;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import persistence.DatabaseManager;
import persistence.UtenteCredenziali;
import persistence.dao.UtenteDao;

@SuppressWarnings("serial")
public class CheckLoginGoogle extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("SONO NELLA SERVLET CHECKLOGIN CON GOOGLE");

		HttpSession session = req.getSession();
		session.setAttribute("email", null);
		// PrintWriter out = resp.getWriter();
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String email = req.getParameter("email");

		UtenteDao dao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		UtenteCredenziali utente = dao.findByPrimaryKeyCredential(email);

		if (utente == null) {
			Utente utenteGoogle = new Utente(email, nome, cognome, new Date());
			/*
			 * L'utente non ш ancora registrato con l'email che ha inserito
			 * bisogna quindi registrare questo utente e poi farlo entrare.
			 */

			dao.save(utenteGoogle);
			// L'utente ш stato registrato per la prima volta tramite google e
			// salvato nel database.
			session.setAttribute("email", email);
			resp.getOutputStream().print("ok");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		} else {
			// Ha giра effettuato in precedenza un login con google quindi non
			// c'ш bisogno di salvarlo nel database.
			session.setAttribute("email", email);
			resp.getOutputStream().print("ok");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		}
	}
}
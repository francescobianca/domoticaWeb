package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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
public class AlternativeCheckLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("sono nella servlet login con facebook");
		
		
		HttpSession session = req.getSession();
		session.setAttribute("email", null);
		// PrintWriter out = resp.getWriter();
		String email = req.getParameter("email");
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String tipo=req.getParameter("tipo");
		UtenteDao dao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		UtenteCredenziali utente = dao.findByPrimaryKeyCredential(email);

		if (utente == null) {
			@SuppressWarnings("deprecation")
			Utente alternativeUser = new Utente(email, nome, cognome, new Date());
			/*
			 * L'utente non ш ancora registrato con l'email che ha inserito
			 * bisogna quindi registrare questo utente e poi farlo entrare.
			 */
			
			dao.save(alternativeUser);
			// L'utente ш stato registrato per la prima volta tramite facebook e
			// salvato nel database.
			session.setAttribute("email", email);
			session.setAttribute("nome", nome);
			session.setAttribute("cognome", cognome);
			session.setAttribute("tipo",tipo);
	/*		RequestDispatcher disp;
			disp= req.getRequestDispatcher("entryPage.jsp");
			req.setAttribute("utente", alternativeUser);
			disp.forward(req, resp);*/
			
		} else {
			// Ha giра effettuato in precedenza un login con facebook quindi non
			// c'ш bisogno di salvarlo nel database.
			session.setAttribute("email", email);
			session.setAttribute("nome", nome);
			session.setAttribute("cognome", cognome);
			session.setAttribute("tipo",tipo);
		/*	RequestDispatcher disp;
			disp= req.getRequestDispatcher("entryPage.jsp");
			Utente alternativeUser = dao.findByPrimaryKey(email);
			req.setAttribute("utente", alternativeUser);
			disp.forward(req, resp);*/
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		System.out.println("Logout facebook");
		
		//log-out
		session.removeAttribute("email");
		session.removeAttribute("nome");
		session.removeAttribute("cognome");
		
		RequestDispatcher disp;
		disp= req.getRequestDispatcher("entryPage.jsp");
		disp.forward(req, resp);
	}
	
}

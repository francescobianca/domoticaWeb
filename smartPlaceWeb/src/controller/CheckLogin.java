package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.DatabaseManager;
import persistence.UtenteCredenziali;
import persistence.dao.UtenteDao;

@SuppressWarnings("serial")
public class CheckLogin extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		System.out.println("Logout");
		//log-out
		session.removeAttribute("email");
		session.removeAttribute("nome");
		session.removeAttribute("cognome");
		session.removeAttribute("tipo");
		
		RequestDispatcher disp;
		disp= req.getRequestDispatcher("entryPage.jsp");
		disp.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("sono in post login");
		
		HttpSession session = req.getSession();
		session.setAttribute("email", null);
		PrintWriter out = resp.getWriter();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UtenteDao dao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		UtenteCredenziali utente = dao.findByPrimaryKeyCredential(email);

		if (req.getParameter("checkEmail")!=null){
			if(utente!=null)
				out.print("ok");
		}else{
			if (password.equals(utente.getPassword())){
				session.setAttribute("email", email);
				session.setAttribute("nome", utente.getNome());
				session.setAttribute("cognome", utente.getCognome());
				session.setAttribute("tipo", "normale");
				RequestDispatcher disp;
				disp= req.getRequestDispatcher("entryPage.jsp");
				req.setAttribute("utente", utente);
				disp.forward(req, resp);
			}else{
				out.print("passwordErrata");
				RequestDispatcher dispatcher;
				dispatcher=req.getRequestDispatcher("login.jsp");
				req.setAttribute("email", email);
				dispatcher.forward(req, resp);
			}				
		}
	}
}
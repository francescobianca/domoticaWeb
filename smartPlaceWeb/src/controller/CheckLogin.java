package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		if ( (req.getParameter("logout") != null) && (req.getParameter("logout").equals("true"))){
			session.setAttribute("email", null);	
			out.println("<html>");
			out.println("<head><title>Login</title></head>");
			out.println("<body>");
			out.println("<h1>Logout effettuato con successo</h1>");
			out.println("</body>");
			out.println("</html>");	
		}else{
			String email = (String) session.getAttribute("email");		
			if (email != null){			
				out.println("<html>");
				out.println("<head><title>Login</title></head>");
				out.println("<body>");
				out.println("<h1>Sei già loggato come " + email + "</h1>");
				out.println("<a href=\"checkLogin?logout=true\">Logout</a>");
				out.println("</body>");
				out.println("</html>");	
			}else{
				out.println("<html>");
				out.println("<head><title>Effettual il Login</title></head>");
				out.println("<body>");
				out.println("<h1>Effettua il login</h1>");
				out.println("<form method=\"post\" action=\"checkLogin\">");
				out.println("Username : <input name=\"email\" type=\"text\" />");
				out.println("Password : <input name=\"password\" type=\"password\" />");
				out.println("<input type=\"submit\" />");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("email", null);
		PrintWriter out = resp.getWriter();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UtenteDao dao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		UtenteCredenziali utente = dao.findByPrimaryKeyCredential(email);
		
		if (utente == null){
			out.println("<html>");
			out.println("<head><title>Login</title></head>");
			out.println("<body>");
			out.println("<h1>Nessun utente è registrato come " + email + "</h1>");			
			out.println("</body>");
			out.println("</html>");				
		}else{
			if (password.equals(utente.getPassword())){
				session.setAttribute("email", email);
				resp.sendRedirect("report/index.html"); 
			}else{
				out.println("<html>");
				out.println("<head><title>Login</title></head>");
				out.println("<body>");
				out.println("<h1>Spiacente, password non corrispondente per l'utente " + email + "</h1>");			
				out.println("</body>");
				out.println("</html>");	
			}				
		}
	}
}
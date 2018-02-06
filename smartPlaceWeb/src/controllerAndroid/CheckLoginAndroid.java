package controllerAndroid;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.DatabaseManager;
import persistence.UtenteCredenziali;
import persistence.dao.UtenteDao;

@SuppressWarnings("serial")
public class CheckLoginAndroid extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		//PrintWriter out = resp.getWriter();
		if ((req.getParameter("logout") != null) && (req.getParameter("logout").equals("true"))) {
			session.setAttribute("email", null);
		} else {
			String email = (String) session.getAttribute("email");
			if (email != null) {
			} else {
				/*resp.getOutputStream().print("ciao");
				resp.getOutputStream().flush();
				resp.getOutputStream().close();*/
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("SONO NELLA SERVLET CHECKLOGIN");
		
		HttpSession session = req.getSession();
		session.setAttribute("email", null);
		//PrintWriter out = resp.getWriter();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UtenteDao dao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		UtenteCredenziali utente = dao.findByPrimaryKeyCredential(email);

		if (utente == null) {
			resp.getOutputStream().print("DatiSbagliati");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		} else {
			if (password.equals(utente.getPassword())) {
				session.setAttribute("email", email);
				System.out.println(email);
				resp.getOutputStream().print("ok"+"/"+utente.getNome()+"/"+utente.getCognome());
				//resp.getOutputStream().println(utente.getNome());
				//resp.getOutputStream().println(utente.getCognome());
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			} else {
				resp.getOutputStream().print("DatiSbagliati");
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			}
		}
	}
}
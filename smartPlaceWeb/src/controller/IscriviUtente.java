package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

@SuppressWarnings("serial")
public class IscriviUtente extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("sono nella servlet iscrivi utente");
		
		String email = req.getParameter("email");
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String dataNascita = req.getParameter("dataNascita");
		String password = req.getParameter("password");

		DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ITALIAN);
		Date date;
		try {
			date = format.parse(dataNascita);
			Utente utente = new Utente(email, nome, cognome, date);
			
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			
			//System.out.println("okkk");
			if (utenteDao.findByPrimaryKey(email) == null) {
				System.out.println("OK");
				utenteDao.save(utente);
				utenteDao.setPassword(utente, password);
				
				// PrintWriter out = resp.getWriter();
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head><title>Iscrizione studente</title></head>");
				out.println("<body>");
				out.println("<h1>Abbiamo iscritto il seguente utente:</h1>");
				out.println(utente.getEmail());
				out.println(utente.getNome());
				out.println(utente.getCognome());
				out.println(utente.getDataNascita());
				out.println("</body>");
				out.println("</html>");
			}
			
			else {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head><title>Iscrizione studente</title></head>");
				out.println("<body>");
				out.println("<h1>utente già esistente</h1>");
				out.println("</body>");
				out.println("</html>");
			}
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
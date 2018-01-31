package controllerAndroid;

import java.io.IOException;
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
public class IscriviUtenteAndroid extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("sono nella servlet iscrivi utente di android");

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

			if (utenteDao.findByPrimaryKey(email) == null) {
				utenteDao.save(utente);
				utenteDao.setPassword(utente, password);
				// PrintWriter out = resp.getWriter();
				resp.getOutputStream().print("ok");
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
				System.out.println("ABBIAMO REGISTRATO");
			}
			
			else {
				resp.getOutputStream().print("no");
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
				System.out.println("GIA ESISTE");
			}
				

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
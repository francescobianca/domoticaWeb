package controller;

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

import model.Arduino;
import model.Sensore;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.ArduinoDao;
import persistence.dao.SensoreDao;
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
		String indirizzoIP = req.getParameter("indirizzoIP");
		String porta = req.getParameter("porta");

		if (req.getParameter("checkEmail") != null) {

			if (DatabaseManager.getInstance().getDaoFactory().getUtenteDAO().findByPrimaryKey(email) != null) {
				resp.getWriter().print("errore");
			} else {
				req.getSession().setAttribute("email", email);
				resp.getWriter().print("ok");
			}
			return;

		}

		else if (req.getParameter("checkPassword") != null) {
			req.getSession().setAttribute("password", password);
			return;
		}

		else if (req.getParameter("checkNome") != null) {
			req.getSession().setAttribute("nome", nome);
			return;
		}

		else if (req.getParameter("checkCognome") != null) {
			req.getSession().setAttribute("cognome", cognome);
			return;
		}

		else if (req.getParameter("checkData") != null) {
			req.getSession().setAttribute("dataNascita", dataNascita);
			return;
		}

		else if (req.getParameter("checkIndirizzoIP") != null) {
			req.getSession().setAttribute("indirizzoIP", indirizzoIP);
			return;
		}

		else if (req.getParameter("checkPorta") != null) {
			req.getSession().setAttribute("porta", porta);
			return;
		}

		boolean errori = false;
		if(!req.getSession().getAttribute("email").equals(email))
			errori=true;
		if(!req.getSession().getAttribute("nome").equals(nome))
			errori=true;
		if(!req.getSession().getAttribute("cognome").equals(cognome))
			errori=true;
		if(!req.getSession().getAttribute("dataNascita").equals(dataNascita))
			errori=true;
		if(!req.getSession().getAttribute("indirizzoIP").equals(indirizzoIP))
			errori=true;
		if(!req.getSession().getAttribute("password").equals(password))
			errori=true;
		if(!req.getSession().getAttribute("porta").equals(porta))
			errori=true;
		/*
		Enumeration<String> parametri = req.getParameterNames();
		while (parametri.hasMoreElements()) {
			String string = (String) parametri.nextElement();
			if (!string.equals("inviaDati")) {
				if (!req.getParameter(string).equals(req.getSession().getAttribute(string))) {
					System.err.println("" + req.getParameter(string) + " : " + req.getSession().getAttribute(string));
					errori = true;
				}
			}
		}*/
		if (!errori) {
			DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ITALIAN);
			Date date;
			try {
				date = format.parse(dataNascita);
				Utente utente = new Utente(email, nome, cognome, date);

				UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

				utenteDao.save(utente);
				utenteDao.setPassword(utente, password);

				Arduino arduino = new Arduino(indirizzoIP, Integer.parseInt(porta));
				ArduinoDao aDao = DatabaseManager.getInstance().getDaoFactory().getArduinoDAO();

				arduino.setUtente(utente);
				aDao.save(arduino);

				installaDispositivi(arduino);

			} catch (ParseException e) {
				resp.getWriter().print("errore");
			}
		} else {
			System.out.println("I dati non corrispondono");
		}
	}

	private void installaDispositivi(Arduino arduino) {

		// installazione vari dispositivi
		Sensore sensore1 = new Sensore("ventilatore", "casa");
		Sensore sensore2 = new Sensore("umidità", "casa");
		Sensore sensore3 = new Sensore("temperatura", "casa");

		Sensore sensore4 = new Sensore("luce", "salone");
		Sensore sensore5 = new Sensore("luce", "cucina");
		Sensore sensore6 = new Sensore("luce", "cameraLetto");
		Sensore sensore7 = new Sensore("luce", "bagno");

		Sensore sensore8 = new Sensore("finestra", "salone");
		Sensore sensore9 = new Sensore("finestra", "cucina");
		Sensore sensore10 = new Sensore("finestra", "cameraLetto");
		Sensore sensore11 = new Sensore("finestra", "bagno");

		Sensore sensore12 = new Sensore("cancello", "casa");

		SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();

		sensore1.setArduino(arduino);
		sensore2.setArduino(arduino);
		sensore3.setArduino(arduino);
		sensore4.setArduino(arduino);
		sensore5.setArduino(arduino);
		sensore6.setArduino(arduino);
		sensore7.setArduino(arduino);
		sensore8.setArduino(arduino);
		sensore9.setArduino(arduino);
		sensore10.setArduino(arduino);
		sensore11.setArduino(arduino);
		sensore12.setArduino(arduino);

		sDao.save(sensore1);
		sDao.save(sensore2);
		sDao.save(sensore3);
		sDao.save(sensore4);
		sDao.save(sensore5);
		sDao.save(sensore6);
		sDao.save(sensore7);
		sDao.save(sensore8);
		sDao.save(sensore9);
		sDao.save(sensore10);
		sDao.save(sensore11);
		sDao.save(sensore12);

	}
}
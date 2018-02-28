/**
 * 
 */
package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Arduino;
import model.Sensore;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.ArduinoDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public class ConfiguraArduino extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String utente = (String) req.getSession().getAttribute("email");
		String indirizzoIP = req.getParameter("indirizzoIP");
		String porta = req.getParameter("porta");

		if (req.getParameter("checkIndirizzoIP") != null) {
			if(!esistente(indirizzoIP))
				req.getSession().setAttribute("indirizzoIP", indirizzoIP);
			else {
				resp.getWriter().print("indirizzoGiaEsistente");
				resp.getWriter().flush();
				resp.getWriter().close();
			}
			return;
		}
		if (req.getParameter("checkPorta") != null) {
			req.getSession().setAttribute("porta", porta);
			return;
		}

		boolean errori = false;
		if (!req.getSession().getAttribute("indirizzoIP").equals(indirizzoIP))
			errori = true;
		if (!req.getSession().getAttribute("porta").equals(porta))
			errori = true;

		if (!errori) {
			try {

				UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

				Utente user = utenteDao.findByPrimaryKey((String) req.getSession().getAttribute("email"));

				Arduino arduino = new Arduino(indirizzoIP, Integer.parseInt(porta));
				ArduinoDao aDao = DatabaseManager.getInstance().getDaoFactory().getArduinoDAO();

				arduino.setUtente(user);
				aDao.save(arduino);

				installaDispositivi(arduino);
				resp.getWriter().print("ok");
				resp.getWriter().flush();
				resp.getWriter().close();
			} catch (Exception e) {
				resp.getWriter().print("errore");
				resp.getWriter().flush();
				resp.getWriter().close();
			}
		} else {
			System.out.println("I dati non corrispondono");
		}
	}
	
	private boolean esistente(String indirizzoIP){
		Arduino a=DatabaseManager.getInstance().getDaoFactory().getArduinoDAO().findByPrimaryKey(indirizzoIP);
		if(a!=null)
			return true;
		else
			return false;
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

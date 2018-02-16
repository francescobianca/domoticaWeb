package controllerAndroid;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Arduino;
import model.Sensore;
import persistence.DatabaseManager;
import persistence.dao.ArduinoDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

@SuppressWarnings("serial")
public class checkSensoriGoogle extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("sono in check sensori");

		String utente = req.getParameter("utente");
		String indirizzoIP = req.getParameter("indirizzoIP");
		String porta = req.getParameter("porta");

		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		Arduino arduino = new Arduino(indirizzoIP, Integer.parseInt(porta));
		ArduinoDao aDao = DatabaseManager.getInstance().getDaoFactory().getArduinoDAO();

		if (aDao.findByPrimaryKey(indirizzoIP) == null) {

			arduino.setUtente(utenteDao.findByPrimaryKey(utente));
			aDao.save(arduino);

			installaDispositivi(arduino);

			resp.getOutputStream().print("ok");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		}

		else {
			resp.getOutputStream().print("no");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
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

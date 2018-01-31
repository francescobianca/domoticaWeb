package test;

import model.Arduino;
import model.Sensore;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.ArduinoDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

public class Prova {
	public static void main(String[] args) {
		
		Arduino a = new Arduino("127.0.0.1", 4000);
		
		UtenteDao uDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		Utente u = uDao.findByPrimaryKey(""); //Metti l'email con la quale ti registri.

		a.setUtente(u);
		
		ArduinoDao aDao = DatabaseManager.getInstance().getDaoFactory().getArduinoDAO();
		aDao.save(a);

		Sensore sensore1 = new Sensore("ventilatore","casa");
		Sensore sensore2 = new Sensore("umidità","casa");
		Sensore sensore3 = new Sensore("temperatura","casa");
		
		Sensore sensore4 = new Sensore("luce","salone");
		Sensore sensore5 = new Sensore("luce","cucina");
		Sensore sensore6 = new Sensore("luce","cameraLetto");
		Sensore sensore7 = new Sensore("luce","bagno");
		
		Sensore sensore8 = new Sensore("finestra","salone");
		Sensore sensore9 = new Sensore("finestra","cucina");
		Sensore sensore10 = new Sensore("finestra","cameraLetto");
		Sensore sensore11 = new Sensore("finestra","bagno");	
		
		SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
		
		Arduino arduino = aDao.findByPrimaryKey("127.0.0.1");
		
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
		
	}
}

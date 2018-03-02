package test;

import java.util.Iterator;
import java.util.List;

import model.Arduino;
import model.Categoria;
import model.Domanda;
import model.Risposta;
import model.Sensore;
import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.ArduinoDao;
import persistence.dao.CategoriaDao;
import persistence.dao.DomandaDao;
import persistence.dao.RispostaDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

public class Prova {
	public static void main(String[] args) {

		/*
		 * Arduino a = new Arduino("127.0.0.1", 4000);
		 * 
		 * UtenteDao uDao =
		 * DatabaseManager.getInstance().getDaoFactory().getUtenteDAO(); Utente
		 * u = uDao.findByPrimaryKey("prova@smartplace.com"); //Metti l'email
		 * con la quale ti registri.
		 * 
		 * a.setUtente(u);
		 * 
		 * ArduinoDao aDao =
		 * DatabaseManager.getInstance().getDaoFactory().getArduinoDAO();
		 * aDao.save(a);
		 * 
		 * Sensore sensore1 = new Sensore("ventilatore","casa"); Sensore
		 * sensore2 = new Sensore("umidità","casa"); Sensore sensore3 = new
		 * Sensore("temperatura","casa");
		 * 
		 * Sensore sensore4 = new Sensore("luce","salone"); Sensore sensore5 =
		 * new Sensore("luce","cucina"); Sensore sensore6 = new
		 * Sensore("luce","cameraLetto"); Sensore sensore7 = new
		 * Sensore("luce","bagno");
		 * 
		 * Sensore sensore8 = new Sensore("finestra","salone"); Sensore sensore9
		 * = new Sensore("finestra","cucina"); Sensore sensore10 = new
		 * Sensore("finestra","cameraLetto"); Sensore sensore11 = new
		 * Sensore("finestra","bagno");
		 * 
		 * SensoreDao sDao =
		 * DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
		 * 
		 * Arduino arduino = aDao.findByPrimaryKey("127.0.0.1");
		 * 
		 * sensore1.setArduino(arduino); sensore2.setArduino(arduino);
		 * sensore3.setArduino(arduino); sensore4.setArduino(arduino);
		 * sensore5.setArduino(arduino); sensore6.setArduino(arduino);
		 * sensore7.setArduino(arduino); sensore8.setArduino(arduino);
		 * sensore9.setArduino(arduino); sensore10.setArduino(arduino);
		 * sensore11.setArduino(arduino);
		 * 
		 * sDao.save(sensore1); sDao.save(sensore2); sDao.save(sensore3);
		 * sDao.save(sensore4); sDao.save(sensore5); sDao.save(sensore6);
		 * sDao.save(sensore7); sDao.save(sensore8); sDao.save(sensore9);
		 * sDao.save(sensore10); sDao.save(sensore11);
		 */

		/*
		 * Categoria c1 = new Categoria("Luce"); Categoria c2 = new
		 * Categoria("Temperatura");
		 * 
		 * CategoriaDao cDao =
		 * DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO();
		 * 
		 * cDao.save(c1); cDao.save(c2);
		 */

		/*
		 * Domanda d = new Domanda("Problema sensore luce"); CategoriaDao cDao =
		 * DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO();
		 * Categoria c = cDao.findByPrimaryKey("Luce"); d.setCategoria(c);
		 * 
		 * d.setTesto("Mi potete aiutare a risolvere il problema?");
		 * 
		 * UtenteDao uDao =
		 * DatabaseManager.getInstance().getDaoFactory().getUtenteDAO(); Utente
		 * uDomanda = uDao.findByPrimaryKey("francesco@gmail.com");
		 * 
		 * d.setUtente(uDomanda);
		 * 
		 * DomandaDao dDao =
		 * DatabaseManager.getInstance().getDaoFactory().getDomandaDAO();
		 * dDao.save(d);
		 * 
		 * Utente uRisposta = uDao.findByPrimaryKey("andrea@gmail.com");
		 * Risposta r = new
		 * Risposta("contatta l'assistenza! Ho avuto il tuo stesso problema");
		 * 
		 * d=dDao.findByPrimaryKey(5);
		 * 
		 * r.setDomanda(d); r.setUtente(uRisposta);
		 * 
		 * RispostaDao rDao =
		 * DatabaseManager.getInstance().getDaoFactory().getRispostaDAO();
		 * rDao.save(r);
		 */

		/*
		 * RispostaDao rDao =
		 * DatabaseManager.getInstance().getDaoFactory().getRispostaDAO();
		 * Risposta r = rDao.findByPrimaryKey(5);
		 * System.out.println(r.getDomanda().getTitolo());
		 */

		/*
		 * RispostaDao rDao =
		 * DatabaseManager.getInstance().getDaoFactory().getRispostaDAO();
		 * 
		 * Risposta r1=new Risposta("risposta 1");
		 * r1.setDomanda(DatabaseManager.getInstance().getDaoFactory().
		 * getDomandaDAO().findByPrimaryKey(4));
		 * r1.setUtente(DatabaseManager.getInstance().getDaoFactory().
		 * getUtenteDAO().findByPrimaryKey("andrea@gmail.com")); rDao.save(r1);
		 * 
		 * Risposta r2=new Risposta("risposta 2");
		 * r2.setDomanda(DatabaseManager.getInstance().getDaoFactory().
		 * getDomandaDAO().findByPrimaryKey(4));
		 * r2.setUtente(DatabaseManager.getInstance().getDaoFactory().
		 * getUtenteDAO().findByPrimaryKey("andrea@gmail.com")); rDao.save(r2);
		 * 
		 * Risposta r3=new Risposta("risposta 3");
		 * r3.setDomanda(DatabaseManager.getInstance().getDaoFactory().
		 * getDomandaDAO().findByPrimaryKey(4));
		 * r3.setUtente(DatabaseManager.getInstance().getDaoFactory().
		 * getUtenteDAO().findByPrimaryKey("andrea@gmail.com")); rDao.save(r3);
		 */
		/*DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Luce"));
		DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Temperatura"));
		DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Sicurezza"));
		DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Umidità"));
		DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Ingressi"));
		DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Finestre"));
		DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Attività"));
		DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Regole"));
		DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Dispositivi"));
		DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().save(new Categoria("Statistiche"));*/

		/*
		 * Risposta r = new Risposta(
		 * "Ti faccio un esempio pratico. Se memorizzi un'attività dal 2 marzo al 3 marzo dalle 16:00 alle 18:00 questa sarà eseguita nell'arco di tempo tra le 16 e le 18 del giorno di inizio e poi verra rieseguita anche il 3 marzo (quindi per tutti i giorni compresi tra inizio e fine"
		 * );
		 * 
		 * r.setDomanda(DatabaseManager.getInstance().getDaoFactory().
		 * getDomandaDAO().findByPrimaryKey(7));
		 * r.setUtente(DatabaseManager.getInstance().getDaoFactory().
		 * getUtenteDAO().findByPrimaryKey("francesco@gmail.com"));
		 * 
		 * DatabaseManager.getInstance().getDaoFactory().getRispostaDAO().save(r
		 * );
		 */

	}
}

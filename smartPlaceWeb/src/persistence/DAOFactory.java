package persistence;

import persistence.dao.ArduinoDao;
import persistence.dao.AttivitaPeriodicaDao;
import persistence.dao.CategoriaDao;
import persistence.dao.DomandaDao;
import persistence.dao.MisurazioneDao;
import persistence.dao.RegolaDao;
import persistence.dao.RispostaDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

public abstract class DAOFactory {

	// --- List of supported DAO types ---

	
	/**
	 * Numeric constant '1' corresponds to explicit Hsqldb choice
	 */
	public static final int HSQLDB = 1;
	
	/**
	 * Numeric constant '2' corresponds to explicit Postgres choice
	 */
	public static final int POSTGRESQL = 2;
	
	
	// --- Actual factory method ---
	
	/**
	 * Depending on the input parameter
	 * this method returns one out of several possible 
	 * implementations of this factory spec 
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch ( whichFactory ) {
		
		case HSQLDB:
			return null;//new HsqldbDAOFactory();
		case POSTGRESQL:
			return new PostgresDAOFactory();
		default:
			return null;
		}
	}
	
	
	
	// --- Factory specification: concrete factories implementing this spec must provide this methods! ---
	
	/**
	 * Method to obtain a DATA ACCESS OBJECT
	 * for the datatype 'Student'
	 */
	public abstract UtenteDao getUtenteDAO();
	
	public abstract ArduinoDao getArduinoDAO();
	
	public abstract AttivitaPeriodicaDao getAttivitaPeriodicaDAO();
	
	public abstract MisurazioneDao getMisurazioneDAO();
	
	public abstract SensoreDao getSensoreDAO();
	
	public abstract RegolaDao getRegolaDAO();
	
	public abstract CategoriaDao getCategoriaDAO();
	
	public abstract DomandaDao getDomandaDAO();
	
	public abstract RispostaDao getRispostaDAO();

	public abstract persistence.UtilDao getUtilDAO();
	
	public abstract DataSource getDataSource();

}
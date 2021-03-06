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

class PostgresDAOFactory extends DAOFactory {

	private static DataSource dataSource;

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			// questi vanno messi in file di configurazione!!!
			// dataSource=new
			// DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");

			// dbLocale
			dataSource = new DataSource("jdbc:postgresql://localhost:5432/SmartPlaceWeb","postgres", "postgres");

			// dbRemoto
			/*dataSource = new DataSource("jdbc:postgresql://packy.db.elephantsql.com:5432/hvkahtzn", "hvkahtzn",
					"Lk4jd8cKUMF4Jox30NF5R4I5cWERb6vh");*/
			

		} catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	// --------------------------------------------

	@Override
	public UtenteDao getUtenteDAO() {
		return new UtenteDaoJDBC(dataSource);
	}

	@Override
	public ArduinoDao getArduinoDAO() {
		return new ArduinoDaoJDBC(dataSource);
	}

	@Override
	public AttivitaPeriodicaDao getAttivitaPeriodicaDAO() {
		return new AttivitaPeriodicaDaoJDBC(dataSource);
	}

	@Override
	public MisurazioneDao getMisurazioneDAO() {
		return new MisurazioneDaoJDBC(dataSource);
	}

	@Override
	public SensoreDao getSensoreDAO() {
		return new SensoreDaoJDBC(dataSource);
	}

	@Override
	public RegolaDao getRegolaDAO() {
		return new RegolaDaoJDBC(dataSource);
	}
	
	@Override
	public CategoriaDao getCategoriaDAO() {
		return new CategoriaDaoJDBC(dataSource);
	}
	
	@Override
	public DomandaDao getDomandaDAO() {
		return new DomandaDaoJDBC(dataSource);
	}
	
	@Override
	public RispostaDao getRispostaDAO() {
		return new RispostaDaoJDBC(dataSource);
	}

	@Override
	public UtilDao getUtilDAO() {
		return new UtilDao(dataSource);
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

}
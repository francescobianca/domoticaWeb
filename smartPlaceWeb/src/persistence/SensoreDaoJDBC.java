package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Arduino;
import model.Sensore;
import persistence.dao.ArduinoDao;
import persistence.dao.SensoreDao;

public class SensoreDaoJDBC implements SensoreDao {

	private DataSource dataSource;

	public SensoreDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Sensore sensore) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into sensore(\"arduino_indirizzoIP\", tipo, stanza, stato) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, sensore.getArduino().getIndirizzoIP());
			statement.setString(2, sensore.getTipo());
			statement.setString(3, sensore.getStanza());
			statement.setInt(4, sensore.getStato());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	/*
	 * versione con Join
	 */
	/*
	 * public Sensore findByPrimaryKeyJoin(String indirizzoIP, String tipo,
	 * String stanza) {
	 * 
	 * Connection connection = this.dataSource.getConnection(); Sensore sensore
	 * = null; try { PreparedStatement statement; String query =
	 * "select cl.codice as cl_codice, cl.nome as cl_nome, c.codice as c_codice, c.nome as c_nome, "
	 * + "s.matricola as s_matricola, s.nome as s_nome, " +
	 * "s.cognome as s_cognome, s.data_nascita as s_data_nascita, s.indirizzo_codice as s_indirizzo_codice "
	 * + "from corsodilaurea cl, afferisce a, corso c, studente s, iscritto i "
	 * + "where cl.codice = ?" +
	 * "			AND cl.codice = a.corsodilaurea_codice " +
	 * "			AND a.corso_codice = c.codice " +
	 * "			AND i.corso_codice = c.codice " +
	 * "			AND i.matricola_studente = s.matricola" ; statement =
	 * connection.prepareStatement(query); statement.setLong(1, id); ResultSet
	 * result = statement.executeQuery(); boolean primaRiga = true; while
	 * (result.next()) { if (primaRiga) { corsoDiLaurea = new CorsoDiLaurea();
	 * corsoDiLaurea.setCodice(result.getLong("cl_codice"));
	 * corsoDiLaurea.setNome(result.getString("cl_nome")); primaRiga = false; }
	 * if(result.getString("c_codice")!=null){ CorsoDaoJDBC corsoDao = new
	 * CorsoDaoJDBC(dataSource); Corso corso; corso =
	 * corsoDao.findByPrimaryKeyJoin(result.getLong("c_codice")); ////
	 * corso.setCodice(result.getLong("c_codice")); ////
	 * corso.setNome(result.getString("c_nome")); // // if
	 * (result.getString("s_matricola") != null){ // StudenteDaoJDBC studenteDao
	 * = new StudenteDaoJDBC(dataSource); //
	 * corso.addStudente(studenteDao.findByPrimaryKey(result.getString(
	 * "s_matricola"))); // }
	 * 
	 * corsoDiLaurea.addCorso(corso);
	 * 
	 * 
	 * } } } catch (SQLException e) { throw new
	 * PersistenceException(e.getMessage()); } finally { try {
	 * connection.close(); } catch (SQLException e) { throw new
	 * PersistenceException(e.getMessage()); } } return corsoDiLaurea; }
	 */

	/*
	 * Versione con lazy load
	 */

	@Override
	public Sensore findByPrimaryKey(String indirizzoIP, String tipo, String stanza) {
		Connection connection = this.dataSource.getConnection();
		Sensore sensore = null;
		try {
			PreparedStatement statement;
			String query = "select * from sensore where \"arduino_indirizzoIP\" = ? and \"tipo\" = ? and \"stanza\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, indirizzoIP);
			statement.setString(2, tipo);
			statement.setString(3, stanza);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				sensore = new Sensore();
				Arduino a = new Arduino();
				ArduinoDao aDao = DatabaseManager.getInstance().getDaoFactory().getArduinoDAO();
				a = aDao.findByPrimaryKey(result.getString("arduino_indirizzoIP"));
				sensore.setArduino(a);
				sensore.setTipo(result.getString("tipo"));
				sensore.setStanza(result.getString("stanza"));
		
			}

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return sensore;
	}

	@Override
	public List<Sensore> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Sensore> sensori = new ArrayList<>();
		try {
			Sensore sensore = null;
			PreparedStatement statement;
			String query = "select * from sensore";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				// sensore = findByPrimaryKeyJoin(result.getLong("codice"));
				sensore = new Sensore();
				sensore.setStanza(result.getString("stanza"));
				sensore.setTipo(result.getString("tipo"));
				sensore.setStato(result.getInt("stato"));
				
				Arduino a = new Arduino();
				ArduinoDao aDao = DatabaseManager.getInstance().getDaoFactory().getArduinoDAO();
				
				a = aDao.findByPrimaryKey(result.getString("arduino_indirizzoIP"));
				
				sensore.setArduino(a);
				
				sensori.add(sensore);
				
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return sensori;
	}

	@Override
	public void update(Sensore sensore) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update sensore SET stato = ? WHERE arduino_indirizzoIP = ?, tipo = ?, stanza = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, sensore.getStato());
			statement.setString(2, sensore.getArduino().getIndirizzoIP());
			statement.setString(3, sensore.getTipo());
			statement.setString(4, sensore.getStanza());

			// connection.setAutoCommit(false);
			// connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			// this.updateCorsi(corsodilaurea, connection); // se abbiamo deciso
			// di propagare gli update seguendo il riferimento
			// connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new PersistenceException(e.getMessage());
				}
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(Sensore sensore) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM sensore WHERE arduino_indirizzoIP = ?, tipo = ?, stanza = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, sensore.getArduino().getIndirizzoIP());
			statement.setString(2, sensore.getTipo());
			statement.setString(3, sensore.getStanza());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
}
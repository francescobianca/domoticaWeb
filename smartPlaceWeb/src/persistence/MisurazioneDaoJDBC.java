package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Arduino;
import model.Misurazione;
import model.Sensore;
import persistence.dao.ArduinoDao;
import persistence.dao.MisurazioneDao;
import persistence.dao.SensoreDao;

public class MisurazioneDaoJDBC implements MisurazioneDao {

	private DataSource dataSource;

	public MisurazioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Misurazione misurazione) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into misurazione(arduino_indirizzoIP, tipo, stanza, giorno, ora,valore) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, misurazione.getSensore().getArduino().getIndirizzoIP());
			statement.setString(2, misurazione.getSensore().getTipo());
			statement.setString(3, misurazione.getSensore().getStanza());
			
			long date = misurazione.getGiorno().getTime();
			long secs = misurazione.getOra().getTime();
			
			statement.setDate(4, new java.sql.Date(date));
			statement.setTime(5, new java.sql.Time(secs));
			
			statement.setFloat(6, misurazione.getValore());

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

	@Override
	public Misurazione findByPrimaryKey(String indirizzoIP, String tipo, String stanza, Date giorno, Date ora) {
		Connection connection = this.dataSource.getConnection();
		Misurazione misurazione = null;
		
		try {
			PreparedStatement statement;
			String query = "select * from misurazione where arduino_indirizzoIP = ?, tipo = ?, stanza = ?, giorno = ?, ora = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, indirizzoIP);
			statement.setString(2, tipo);
			statement.setString(3, stanza);
			long giornoDate = giorno.getTime();
			long oraDate = ora.getTime();
			
			statement.setDate(4,new java.sql.Date(giornoDate));
			statement.setTime(5,new java.sql.Time(oraDate));

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				
				Sensore s = new Sensore();
				Arduino a = new Arduino();
				misurazione = new Misurazione();
				
				ArduinoDao aDao = DatabaseManager.getInstance().getDaoFactory().getArduinoDAO();
				a = aDao.findByPrimaryKey(result.getString("arduino_indirizzoIP"));
				
				s.setTipo(result.getString("tipo"));
				s.setStanza(result.getString("stanza"));
				s.setArduino(a);
				
				long date = result.getDate("giorno").getTime();
				long secs = result.getDate("ora").getTime();
				
				misurazione.setGiorno(new java.util.Date(date));
				misurazione.setOra(new java.util.Date(secs));
				misurazione.setSensore(s);
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
		return misurazione;
	}

	@Override
	public List<Misurazione> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Misurazione> misurazioni = new ArrayList<>();
		try {
			Misurazione misurazione = null;
			PreparedStatement statement;
			String query = "select * from misurazione";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				misurazione = new Misurazione();
				misurazione.setValore(result.getFloat("valore"));
				long date = result.getDate("giorno").getTime();
				long secs = result.getDate("ora").getTime();
				
				misurazione.setGiorno(new java.util.Date(date));
				misurazione.setOra(new java.util.Date(secs));
				
				Sensore s = new Sensore();
				SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
				s=sDao.findByPrimaryKey(result.getString("arduino_indirizzoIP"), result.getString("tipo"), result.getString("stanza"));
				
				misurazione.setSensore(s);
				
				misurazioni.add(misurazione);
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
		return misurazioni;
	}

	@Override
	public void update(Misurazione misurazione) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update misurazione SET valore = ? WHERE arduino_indirizzoIP = ?, giorno = ?, ora = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setFloat(1, misurazione.getValore());
			statement.setString(2, misurazione.getSensore().getArduino().getIndirizzoIP());
			long date = misurazione.getGiorno().getTime();
			long secs = misurazione.getOra().getTime();
			
			statement.setDate(3, new java.sql.Date(date));
			statement.setTime(4, new java.sql.Time(secs));


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
	public void delete(Misurazione misurazione) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM misurazione WHERE arduino_indirizzoIP = ?, giorno = ?, ora = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, misurazione.getSensore().getArduino().getIndirizzoIP());
			long date = misurazione.getGiorno().getTime();
			long secs = misurazione.getOra().getTime();
			
			statement.setDate(4, new java.sql.Date(date));
			statement.setTime(5, new java.sql.Time(secs));


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
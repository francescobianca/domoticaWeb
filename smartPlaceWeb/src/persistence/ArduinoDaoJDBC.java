package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Arduino;
import persistence.dao.ArduinoDao;

public class ArduinoDaoJDBC implements ArduinoDao {

	private DataSource dataSource;

	public ArduinoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Arduino arduino) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into arduino(\"indirizzoIP\", porta, utenteArduino) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, arduino.getIndirizzoIP());
			statement.setLong(2, arduino.getPorta());
			statement.setString(3, arduino.getUtente().getEmail());
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
	public Arduino findByPrimaryKey(String indirizzoIP) {
		Connection connection = this.dataSource.getConnection();
		Arduino arduino = null;
		try {
			PreparedStatement statement;
			String query = "select * from arduino where \"indirizzoIP\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, indirizzoIP);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				arduino = new Arduino();
				arduino.setIndirizzoIP(result.getString("indirizzoIP"));
				arduino.setPorta(result.getInt("porta"));
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
		return arduino;
	}

	@Override
	public List<Arduino> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Arduino> arduini = new LinkedList<>();
		try {
			Arduino arduino;
			PreparedStatement statement;
			String query = "select * from arduino";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				arduino = new Arduino();
				arduino.setIndirizzoIP(result.getString("indirizzoIP"));
				arduino.setPorta(result.getInt("porta"));
				arduini.add(arduino);
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
		return arduini;
	}

	@Override
	public void update(Arduino arduino) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update arduino SET porta = ?, utenteArduino = ?, WHERE indirizzoIP=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setLong(1, arduino.getPorta());
			statement.setString(2, arduino.getUtente().getEmail());
			statement.setString(3, arduino.getIndirizzoIP());
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
	public void delete(Arduino arduino) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM arduino WHERE indirizzoIP = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, arduino.getIndirizzoIP());
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
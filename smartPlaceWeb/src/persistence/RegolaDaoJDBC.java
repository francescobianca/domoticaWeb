package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Regola;
import model.Sensore;
import model.Utente;
import persistence.dao.RegolaDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

public class RegolaDaoJDBC implements RegolaDao {

	private DataSource dataSource;

	public RegolaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Regola regola) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into regola(nome,valoreMisurazione,utente,indirizzoIP,tipo,stanza,attiva) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setString(1, regola.getNome());
			statement.setFloat(2, regola.getValoreMisurazione());
			statement.setString(3, regola.getUtente().getEmail());
			statement.setString(4, regola.getSensore().getArduino().getIndirizzoIP());
			statement.setString(5, regola.getSensore().getTipo());
			statement.setString(6, regola.getSensore().getStanza());
			statement.setBoolean(7, false); // per vedere se la regola è attiva
											// o no.
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
	public Regola findByPrimaryKey(int id) {

		Connection connection = this.dataSource.getConnection();
		Regola regola = null;
		try {
			PreparedStatement statement;
			String query = "select * from regola where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {

				regola = new Regola();
				regola.setId(result.getInt("id"));
				regola.setNome(result.getString("nome"));
				regola.setValoreMisurazione(result.getFloat("valoreMisurazione"));
				Utente u = new Utente();
				UtenteDao uDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
				u = uDao.findByPrimaryKey(result.getString("utente"));
				regola.setUtente(u);

				Sensore s = new Sensore();
				SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
				s = sDao.findByPrimaryKey(result.getString("indirizzoIP"), result.getString("tipo"),
						result.getString("stanza"));
				regola.setSensore(s);

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
		return regola;

	}

	@Override
	public List<Regola> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Regola> regole = new LinkedList<>();
		try {
			Regola regola;
			PreparedStatement statement;
			String query = "select * from regola";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				regola = new Regola();
				regola.setId(result.getInt("id"));
				regola.setNome(result.getString("nome"));
				regola.setValoreMisurazione(result.getFloat("valoreMisurazione"));
				Utente u = new Utente();
				UtenteDao uDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
				u = uDao.findByPrimaryKey(result.getString("utente"));
				regola.setUtente(u);

				Sensore s = new Sensore();
				SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
				s = sDao.findByPrimaryKey(result.getString("indirizzoIP"), result.getString("tipo"),
						result.getString("stanza"));
				regola.setSensore(s);

				regole.add(regola);

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

		return regole;
	}

	@Override
	public void update(Regola regola) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update regola SET nome = ?, valoreMisurazione = ?, utente = ?, indirizzoIP = ?, tipo = ? , stanza = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setString(1, regola.getNome());
			statement.setString(2, regola.getUtente().getEmail());
			statement.setString(3, regola.getSensore().getArduino().getIndirizzoIP());
			statement.setString(4, regola.getSensore().getTipo());
			statement.setString(5, regola.getSensore().getStanza());
			statement.setInt(6, regola.getId());

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
	public void delete(Regola regola) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM regola WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, regola.getId());
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

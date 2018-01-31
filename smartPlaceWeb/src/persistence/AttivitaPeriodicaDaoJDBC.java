package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.AttivitaPeriodica;
import model.Sensore;
import model.Utente;
import persistence.dao.AttivitaPeriodicaDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

public class AttivitaPeriodicaDaoJDBC implements AttivitaPeriodicaDao {

	private DataSource dataSource;

	public AttivitaPeriodicaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(AttivitaPeriodica attivita) {
		
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into attivitaperiodica(giornoInizio, giornoFine, orarioInizio, orarioFine, nome,utente,indirizzoIP,tipo,stanza,schedulata) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);

			long dateInizio = attivita.getGiornoInizio().getTime();
			statement.setDate(1, new java.sql.Date(dateInizio));

			long dateFine = attivita.getGiornoFine().getTime();
			statement.setDate(2, new java.sql.Date(dateFine));

			long orarioInizio = attivita.getOrarioInizio().getTime();
			statement.setTime(3, new java.sql.Time(orarioInizio));

			long orarioFine = attivita.getOrarioFine().getTime();
			statement.setTime(4, new java.sql.Time(orarioFine));

			statement.setString(5, attivita.getNome());
			statement.setString(6, attivita.getUtente().getEmail());
			statement.setString(7, attivita.getSensore().getArduino().getIndirizzoIP());
			statement.setString(8, attivita.getSensore().getTipo());
			statement.setString(9, attivita.getSensore().getStanza());
			statement.setBoolean(10, false);
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
	public AttivitaPeriodica findByPrimaryKey(int id) {
		Connection connection = this.dataSource.getConnection();
		AttivitaPeriodica attivitaPeriodica = null;
		try {
			PreparedStatement statement;
			String query = "select * from attivitaperiodica where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				attivitaPeriodica = new AttivitaPeriodica();
				attivitaPeriodica.setId(result.getInt("id"));
				attivitaPeriodica.setGiornoInizio(result.getDate("giornoInizio"));
				attivitaPeriodica.setGiornoFine(result.getDate("giornoFine"));
				attivitaPeriodica.setOrarioInizio(result.getTime("orarioInizio"));
				attivitaPeriodica.setOrarioFine(result.getTime("orarioFine"));
				attivitaPeriodica.setNome(result.getString("nome"));

				Utente u = new Utente();
				UtenteDao uDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
				u = uDao.findByPrimaryKey(result.getString("utente"));
				attivitaPeriodica.setUtente(u);

				Sensore s = new Sensore();
				SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
				s = sDao.findByPrimaryKey(result.getString("indirizzoIP"), result.getString("tipo"),
						result.getString("stanza"));
				attivitaPeriodica.setSensore(s);

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
		return attivitaPeriodica;
	}

	@Override
	public List<AttivitaPeriodica> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<AttivitaPeriodica> listaAttivita = new LinkedList<>();
		try {
			AttivitaPeriodica attivitaPeriodica;
			PreparedStatement statement;
			String query = "select * from attivitaperiodica";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				attivitaPeriodica = new AttivitaPeriodica();
				attivitaPeriodica.setId(result.getInt("id"));
				attivitaPeriodica.setGiornoInizio(result.getDate("giornoInizio"));
				attivitaPeriodica.setGiornoFine(result.getDate("giornoFine"));
				attivitaPeriodica.setOrarioInizio(result.getTime("orarioInizio"));
				attivitaPeriodica.setOrarioFine(result.getTime("orarioFine"));
				attivitaPeriodica.setNome(result.getString("nome"));

				Utente u = new Utente();
				UtenteDao uDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
				u = uDao.findByPrimaryKey(result.getString("utente"));
				attivitaPeriodica.setUtente(u);

				Sensore s = new Sensore();
				SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
				s = sDao.findByPrimaryKey(result.getString("indirizzoIP"), result.getString("tipo"),
						result.getString("stanza"));
				attivitaPeriodica.setSensore(s);

				listaAttivita.add(attivitaPeriodica);
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
		return listaAttivita;
	}

	@Override
	public void update(AttivitaPeriodica attivita) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update attivitaperiodica SET giornoInizio = ?, giornoFine = ?, orarioInizio = ?, orarioFine = ?, nome = ?, utente = ?, indirizzoIP = ?, tipo = ? , stanza = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);

			long dateInizio = attivita.getGiornoInizio().getTime();
			statement.setDate(1, new java.sql.Date(dateInizio));

			long dateFine = attivita.getGiornoFine().getTime();
			statement.setDate(2, new java.sql.Date(dateFine));

			long orarioInizio = attivita.getOrarioInizio().getTime();
			statement.setTime(3, new java.sql.Time(orarioInizio));

			long orarioFine = attivita.getOrarioFine().getTime();
			statement.setTime(5, new java.sql.Time(orarioFine));

			statement.setString(5, attivita.getNome());
			statement.setString(6, attivita.getUtente().getEmail());
			statement.setString(7, attivita.getSensore().getArduino().getIndirizzoIP());
			statement.setString(8, attivita.getSensore().getTipo());
			statement.setString(9, attivita.getSensore().getStanza());
			statement.setInt(10, attivita.getId());

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
	public void delete(AttivitaPeriodica attivita) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM attivitaperiodica WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, attivita.getId());
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
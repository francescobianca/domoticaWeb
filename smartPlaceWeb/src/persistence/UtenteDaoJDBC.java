package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Utente;
import persistence.dao.UtenteDao;

class UtenteDaoJDBC implements UtenteDao {

	private DataSource dataSource;

	public UtenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Utente utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into utente(email, nome, cognome, data_nascita) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, utente.getEmail());
			statement.setString(2, utente.getNome());
			statement.setString(3, utente.getCognome());
			long secs = utente.getDataNascita().getTime();
			statement.setDate(4, new java.sql.Date(secs));
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

	public Utente findByPrimaryKey(String email) {
		Connection connection = this.dataSource.getConnection();
		Utente utente = null;
		try {
			PreparedStatement statement;
			String query = "select * from utente where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setEmail(result.getString("email"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));

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
		return utente;
	}

	@Override
	public UtenteCredenziali findByPrimaryKeyCredential(String email) {
		Utente utente = findByPrimaryKey(email);
		UtenteCredenziali utenteCred = null;
		if (utente != null) {
			utenteCred = new UtenteCredenziali(dataSource);
			utenteCred.setEmail(utente.getEmail());
			utenteCred.setCognome(utente.getCognome());
			utenteCred.setNome(utente.getNome());
			utenteCred.setDataNascita(utente.getDataNascita());
		}
		return utenteCred;
	}

	public List<Utente> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Utente> utenti = new LinkedList<>();
		try {
			Utente utente;
			PreparedStatement statement;
			String query = "select * from utente";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				utente = new Utente();
				utente.setEmail(result.getString("email"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));

				utenti.add(utente);
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
		return utenti;
	}

	public void update(Utente utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update utente SET nome = ?, cognome = ?, data_nascita = ?, WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());
			long secs = utente.getDataNascita().getTime();
			statement.setDate(3, new java.sql.Date(secs));
			statement.setString(4, utente.getEmail());
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

	public void delete(Utente utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM utente WHERE email = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, utente.getEmail());
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
	public void setPassword(Utente utente, String password) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update utente SET password = ? WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, password);
			statement.setString(2, utente.getEmail());
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
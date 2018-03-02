package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Categoria;
import model.Domanda;
import model.Utente;
import persistence.dao.DomandaDao;

public class DomandaDaoJDBC implements DomandaDao {

	private DataSource dataSource;

	public DomandaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Domanda domanda) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into domanda(titolo,testo,categoria,utente) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setString(1, domanda.getTitolo());
			statement.setString(2, domanda.getTesto());
			statement.setString(3, domanda.getCategoria().getNome());
			statement.setString(4, domanda.getUtente().getEmail());

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
	public Domanda findByPrimaryKey(int id) {

		Connection connection = this.dataSource.getConnection();
		Domanda domanda = null;
		try {
			PreparedStatement statement;
			String query = "select * from domanda,utente as u where id = ? and u.email = \"utente\" ";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {

				domanda = new Domanda();
				domanda.setId(result.getInt("id"));
				domanda.setTitolo(result.getString("titolo"));
				domanda.setTesto(result.getString("testo"));

				Categoria categoria = new Categoria(result.getString("categoria"));
				domanda.setCategoria(categoria);

				Utente utente = new Utente();
				utente.setEmail(result.getString("email"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));

				domanda.setUtente(utente);

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
		return domanda;

	}

	@Override
	public List<Domanda> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Domanda> domande = new LinkedList<>();
		try {
			Domanda domanda;
			PreparedStatement statement;
			String query = "select * from domanda,utente as u where u.email = \"utente\" ";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				
				domanda = new Domanda();
				domanda.setId(result.getInt("id"));
				domanda.setTitolo(result.getString("titolo"));
				domanda.setTesto(result.getString("testo"));

				Categoria categoria = new Categoria(result.getString("categoria"));
				domanda.setCategoria(categoria);

				Utente utente = new Utente();
				utente.setEmail(result.getString("email"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));

				domanda.setUtente(utente);

				domande.add(domanda);

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

		return domande;
	}

	@Override
	public void update(Domanda domanda) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update domanda SET titolo = ?, testo = ?, utente = ?, categoria = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setString(1, domanda.getTitolo());
			statement.setString(2, domanda.getTesto());
			statement.setString(3, domanda.getUtente().getEmail());
			statement.setString(4, domanda.getCategoria().getNome());

			statement.setInt(5, domanda.getId());

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
	public void delete(Domanda domanda) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM domanda WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, domanda.getId());
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

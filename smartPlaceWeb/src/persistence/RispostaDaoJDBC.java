package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Categoria;
import model.Domanda;
import model.Risposta;
import model.Utente;
import persistence.dao.RispostaDao;

public class RispostaDaoJDBC implements RispostaDao {

	private DataSource dataSource;

	public RispostaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Risposta risposta) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into risposta(testo,domanda,utente) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setString(1, risposta.getTesto());
			statement.setInt(2, risposta.getDomanda().getId());
			statement.setString(3, risposta.getUtente().getEmail());

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
	public Risposta findByPrimaryKey(int id) {
		Connection connection = this.dataSource.getConnection();
		Risposta risposta = null;
		try {
			PreparedStatement statement;
			String query = "select r.id as idRisposta,r.testo as testoRisposta,domanda,r.utente as utenteRisposta,"
					+ "d.id as idDomanda, d.utente as utenteDomanda, d.testo as testoDomanda, d.titolo as titoloDomanda, categoria,"
					+ "u1.nome as nomeUtenteRisposta, u1.cognome as cognomeUtenteRisposta, u1.email as emailUtenteRisposta, u1.data_nascita as dataNascitaRisposta,"
					+ "u2.nome as nomeUtenteDomanda, u2.email as emailUtenteDomanda, u2.cognome as cognomeUtenteDomanda, u2.data_nascita as dataNascitaDomanda"
					+ " from risposta as r,domanda as d,utente as u1,utente as u2 where r.id = ? and u1.email = r.utente and r.domanda = d.id and "
					+ "d.utente=u2.email";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				risposta = new Risposta();
				risposta.setId(result.getInt("idRisposta"));
				risposta.setTesto(result.getString("testoRisposta"));
				Utente utenteRisposta = new Utente();
				utenteRisposta.setEmail(result.getString("emailUtenteRisposta"));
				utenteRisposta.setNome(result.getString("nomeUtenteRisposta"));
				utenteRisposta.setCognome(result.getString("cognomeUtenteRisposta"));
				long secsUtenteRisposta = result.getDate("dataNascitaRisposta").getTime();
				utenteRisposta.setDataNascita(new java.util.Date(secsUtenteRisposta));
				risposta.setUtente(utenteRisposta);

				Domanda domanda = new Domanda();
				Utente utenteDomanda = new Utente();
				utenteDomanda.setEmail(result.getString("emailUtenteDomanda"));
				utenteDomanda.setNome(result.getString("nomeUtenteDomanda"));
				utenteDomanda.setCognome(result.getString("cognomeUtenteDomanda"));
				long secsUtenteDomanda = result.getDate("dataNascitaDomanda").getTime();
				utenteDomanda.setDataNascita(new java.util.Date(secsUtenteDomanda));
				domanda.setUtente(utenteDomanda);
				Categoria categoria = new Categoria(result.getString("categoria"));
				domanda.setCategoria(categoria);
				domanda.setId(result.getInt("idDomanda"));
				domanda.setTitolo(result.getString("titoloDomanda"));
				domanda.setTesto(result.getString("testoDomanda"));
				risposta.setDomanda(domanda);

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
		return risposta;
	}

	@Override
	public List<Risposta> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Risposta> risposte = new LinkedList<>();
		try {
			Risposta risposta;
			PreparedStatement statement;
			String query = "select r.id as idRisposta,r.testo as testoRisposta,domanda,r.utente as utenteRisposta,"
					+ "d.id as idDomanda, d.utente as utenteDomanda, d.testo as testoDomanda, d.titolo as titoloDomanda, categoria,"
					+ "u1.nome as nomeUtenteRisposta, u1.cognome as cognomeUtenteRisposta, u1.email as emailUtenteRisposta, u1.data_nascita as dataNascitaRisposta,"
					+ "u2.nome as nomeUtenteDomanda, u2.email as emailUtenteDomanda, u2.cognome as cognomeUtenteDomanda, u2.data_nascita as dataNascitaDomanda"
					+ " from risposta as r,domanda as d,utente as u1,utente as u2 where u1.email = r.utente and r.domanda = d.id and "
					+ "d.utente=u2.email";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				risposta = new Risposta();
				risposta.setId(result.getInt("idRisposta"));
				risposta.setTesto(result.getString("testoRisposta"));
				Utente utenteRisposta = new Utente();
				utenteRisposta.setEmail(result.getString("emailUtenteRisposta"));
				utenteRisposta.setNome(result.getString("nomeUtenteRisposta"));
				utenteRisposta.setCognome(result.getString("cognomeUtenteRisposta"));
				long secsUtenteRisposta = result.getDate("dataNascitaRisposta").getTime();
				utenteRisposta.setDataNascita(new java.util.Date(secsUtenteRisposta));
				risposta.setUtente(utenteRisposta);

				Domanda domanda = new Domanda();
				Utente utenteDomanda = new Utente();
				utenteDomanda.setEmail(result.getString("emailUtenteDomanda"));
				utenteDomanda.setNome(result.getString("nomeUtenteDomanda"));
				utenteDomanda.setCognome(result.getString("cognomeUtenteDomanda"));
				long secsUtenteDomanda = result.getDate("dataNascitaDomanda").getTime();
				utenteDomanda.setDataNascita(new java.util.Date(secsUtenteDomanda));
				domanda.setUtente(utenteDomanda);
				Categoria categoria = new Categoria(result.getString("categoria"));
				domanda.setCategoria(categoria);
				domanda.setId(result.getInt("idDomanda"));
				domanda.setTitolo(result.getString("titoloDomanda"));
				domanda.setTesto(result.getString("testoDomanda"));
				risposta.setDomanda(domanda);

				risposte.add(risposta);

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

		return risposte;
	}

	@Override
	public void update(Risposta risposta) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update risposta SET testo = ?, utente = ?, domanda = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setString(1, risposta.getTesto());
			statement.setString(2, risposta.getUtente().getEmail());
			statement.setInt(3, risposta.getDomanda().getId());

			statement.setInt(4, risposta.getId());

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
	public void delete(Risposta risposta) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM risposta WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, risposta.getId());
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

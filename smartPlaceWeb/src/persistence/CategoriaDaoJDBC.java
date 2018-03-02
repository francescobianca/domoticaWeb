package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Categoria;
import persistence.dao.CategoriaDao;

public class CategoriaDaoJDBC implements CategoriaDao {

	private DataSource dataSource;

	public CategoriaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Categoria categoria) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into categoria(nome) values (?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, categoria.getNome());
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
	public Categoria findByPrimaryKey(String nome) {
		Connection connection = this.dataSource.getConnection();
		Categoria categoria = null;
		try {
			PreparedStatement statement;
			String query = "select * from categoria where nome = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, nome);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				categoria = new Categoria(result.getString("nome"));
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
		return categoria;
	}

	@Override
	public List<Categoria> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Categoria> categorie = new LinkedList<>();
		try {
			Categoria categoria;
			PreparedStatement statement;
			String query = "select * from categoria";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				categoria = new Categoria(result.getString("nome"));

				categorie.add(categoria);
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
		return categorie;
	}

	@Override
	public void update(Categoria categoria) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update categoria SET nome = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, categoria.getNome());
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
	public void delete(Categoria categoria) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM categoria WHERE nome = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, categoria.getNome());
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

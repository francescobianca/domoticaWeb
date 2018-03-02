package persistence.dao;

import java.util.List;

import model.Categoria;

public interface CategoriaDao {
	
	public void save(Categoria categoria); // Create

	public Categoria findByPrimaryKey(String nome); 

	public List<Categoria> findAll();

	public void update(Categoria categoria); // Update

	public void delete(Categoria categoria); // Delete

}

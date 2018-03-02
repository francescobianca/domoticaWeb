package persistence.dao;

import java.util.List;

import model.Domanda;

public interface DomandaDao {

	public void save(Domanda domanda); // Create

	public Domanda findByPrimaryKey(int id); 

	public List<Domanda> findAll();

	public void update(Domanda domanda); // Update

	public void delete(Domanda domanda); // Delete
	
}

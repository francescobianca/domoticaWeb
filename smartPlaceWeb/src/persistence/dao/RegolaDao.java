package persistence.dao;

import java.util.List;

import model.Regola;

public interface RegolaDao {

	public void save(Regola regola); // Create

	public Regola findByPrimaryKey(int id); // Retrieve

	public List<Regola> findAll();

	public void update(Regola regola); // Update

	public void delete(Regola regola); // Delete
	
}

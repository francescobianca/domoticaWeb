package persistence.dao;

import java.util.List;

import model.Risposta;

public interface RispostaDao {

	public void save(Risposta risposta); // Create

	public Risposta findByPrimaryKey(int id); 

	public List<Risposta> findAll();

	public void update(Risposta risposta); // Update

	public void delete(Risposta risposta); // Delete
}

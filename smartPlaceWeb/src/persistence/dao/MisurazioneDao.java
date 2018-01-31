package persistence.dao;

import java.util.Date;
import java.util.List;

import model.Misurazione;


public interface MisurazioneDao {

	public void save(Misurazione misurazione); // Create

	public Misurazione findByPrimaryKey(String indirizzoIP, String tipo, String stanza, Date giorno, Date ora); 

	public List<Misurazione> findAll();

	public void update(Misurazione misurazione); // Update

	public void delete(Misurazione misurazione); // Delete
	
}
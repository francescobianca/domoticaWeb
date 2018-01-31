
package persistence.dao;

import java.util.List;

import model.Sensore;

public interface SensoreDao {

	public void save(Sensore sensore); // Create

	public Sensore findByPrimaryKey(String indirizzoIP, String tipo, String stanza);

	public List<Sensore> findAll();

	public void update(Sensore sensore); // Update

	public void delete(Sensore sensore); // Delete

}
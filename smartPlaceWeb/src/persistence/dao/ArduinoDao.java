package persistence.dao;

import java.util.List;

import model.Arduino;

public interface ArduinoDao {

	public void save(Arduino arduino); // Create

	public Arduino findByPrimaryKey(String indirizzoIP); // Retrieve

	public List<Arduino> findAll();

	public void update(Arduino arduino); // Update

	public void delete(Arduino arduino); // Delete

}
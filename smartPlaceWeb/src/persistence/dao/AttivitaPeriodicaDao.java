package persistence.dao;

import java.util.List;

import model.AttivitaPeriodica;

public interface AttivitaPeriodicaDao {

	public void save(AttivitaPeriodica attivita); // Create

	public AttivitaPeriodica findByPrimaryKey(int id); // Retrieve

	public List<AttivitaPeriodica> findAll();

	public void update(AttivitaPeriodica attivita); // Update

	public void delete(AttivitaPeriodica attivita); // Delete
	
}
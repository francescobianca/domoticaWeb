package model;

import java.util.HashSet;
import java.util.Set;

public class Sensore {

	private Arduino arduino; //Con riferimento alla chiave primaria di arduino.
	private String tipo;
	private String stanza;
	/* ----- indirizzoIP, tipo e stanza primary key ------ */
	private int stato;
	private Set<AttivitaPeriodica> attivita;
	private Set<Misurazione> misurazioni;

	public Sensore() {
	}

	public Sensore(String tipo, String stanza) {
		this.tipo = tipo;
		this.stanza = stanza;
	}

	public Arduino getArduino() {
		return arduino;
	}

	public void setArduino(Arduino arduino) {
		this.arduino = arduino;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStanza() {
		return stanza;
	}

	public void setStanza(String stanza) {
		this.stanza = stanza;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

	public Set<AttivitaPeriodica> getAttivita() {
		return attivita;
	}

	public void setAttivita(Set<AttivitaPeriodica> attivita) {
		this.attivita= attivita;
	}

	public void addAttivita(AttivitaPeriodica attivitaPeriodica) {
		if (attivita == null) {
			attivita = new HashSet<AttivitaPeriodica>();
		}
		attivita.add(attivitaPeriodica);
	}

	public Set<Misurazione> getMisurazioni() {
		return misurazioni;
	}

	public void setMisurazioni(Set<Misurazione> misurazioni) {
		this.misurazioni = misurazioni;
	}

	public void addMisurazioni(Misurazione misurazione) {
		if (misurazioni==null) {
			misurazioni = new HashSet<Misurazione>();
		}
		misurazioni.add(misurazione);
	}

}
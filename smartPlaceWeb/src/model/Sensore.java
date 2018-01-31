package model;

import java.util.HashSet;
import java.util.Set;

public class Sensore {

	private Arduino arduino; //Con riferimento alla chiave primaria di arduino.
	private String tipo;
	private String stanza;
	/* ----- indirizzoIP, tipo e stanza primary key ------ */
	private int stato;
	private Set<AttivitaPeriodica> attività;
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

	public Set<AttivitaPeriodica> getAttività() {
		return attività;
	}

	public void setAttività(Set<AttivitaPeriodica> attività) {
		this.attività = attività;
	}

	public void addAttività(AttivitaPeriodica attivitaPeriodica) {
		if (attività == null) {
			attività = new HashSet<AttivitaPeriodica>();
		}
		attività.add(attivitaPeriodica);
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
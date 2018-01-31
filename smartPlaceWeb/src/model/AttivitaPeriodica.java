package model;

import java.util.Date;

public class AttivitaPeriodica {

	private int id; // primary key
	private Date giornoInizio;
	private Date giornoFine;
	private Date orarioInizio;
	private Date orarioFine;
	private String nome;

	private Utente utente;
	private Sensore sensore;

	public AttivitaPeriodica() {
	}

	public AttivitaPeriodica(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getGiornoInizio() {
		return giornoInizio;
	}

	public void setGiornoInizio(Date giornoInizio) {
		this.giornoInizio = giornoInizio;
	}

	public Date getGiornoFine() {
		return giornoFine;
	}

	public void setGiornoFine(Date giornoFine) {
		this.giornoFine = giornoFine;
	}

	public Date getOrarioInizio() {
		return orarioInizio;
	}

	public void setOrarioInizio(Date orarioInizio) {
		this.orarioInizio = orarioInizio;
	}

	public Date getOrarioFine() {
		return orarioFine;
	}

	public void setOrarioFine(Date orarioFine) {
		this.orarioFine = orarioFine;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Sensore getSensore() {
		return sensore;
	}

	public void setSensore(Sensore sensore) {
		this.sensore = sensore;
	}

	public boolean equals(Object object) {
		AttivitaPeriodica attivita = (AttivitaPeriodica) object;
		return (this.getId() == attivita.getId());
	}

	@Override
	public String toString() {
		return "nome attività " + nome + " giornoInizio: " + giornoInizio.toString() + " giornoFine: "
				+ giornoFine.toString() + " orarioInizio: " + orarioInizio.toString() + " orarioFine: "
				+ orarioFine.toString() + " utente: " + utente.getEmail() + " sensore indirizzo: "
				+ sensore.getArduino().getIndirizzoIP() + " stanzaSensore: " + sensore.getStanza() + " tipoSensore "
				+ sensore.getTipo();
	}

}
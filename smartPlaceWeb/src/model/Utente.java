package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Utente {

	private String email;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private Set<AttivitaPeriodica> attivita;
	private Set<Regola> regola;
	private Set<Domanda> domanda;
	private Set<Risposta> risposta;

	public Utente() {
		this.attivita = new HashSet<>();
	}

	public Utente(String email, String nome, String cognome, Date dataNascita) {
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Set<AttivitaPeriodica> getAttivita() {
		return attivita;
	}

	public void setAttivita(Set<AttivitaPeriodica> attivita) {
		this.attivita = attivita;
	}

	public void addAttivita(AttivitaPeriodica attivitaPeriodica) {
		this.getAttivita().add(attivitaPeriodica);
	}

	public void removeStudente(AttivitaPeriodica attivitaPeriodica) {
		this.getAttivita().remove(attivitaPeriodica);
	}
	
	public Set<Regola> getRegola() {
		return regola;
	}
	
	public void setRegola(Set<Regola> regola) {
		this.regola = regola;
	}
	
	public void addRegola(Regola regola) {
		this.getRegola().add(regola);
	}
	
	public void removeRegola(Regola regola) {
		this.getRegola().remove(regola);
	}
	
	public Set<Domanda> getDomanda() {
		return domanda;
	}
	
	public void setDomanda(Set<Domanda> domanda) {
		this.domanda = domanda;
	}
	
	public void addRegola(Domanda domanda) {
		this.getDomanda().add(domanda);
	}
	
	public void removeDomanda(Domanda domanda) {
		this.getRegola().remove(domanda);
	}
	
	public Set<Risposta> getRisposta() {
		return risposta;
	}
	
	public void setRisposta(Set<Risposta> risposta) {
		this.risposta = risposta;
	}
	
	public void addRisposta(Risposta risposta) {
		this.getRisposta().add(risposta);
	}
	
	public void removeRisposta(Risposta risposta) {
		this.getRisposta().remove(risposta);
	}

	public boolean equals(Object object) {
		Utente utente = (Utente) object;
		return (this.getEmail() == utente.getEmail());
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return "Utente[" + this.getEmail() + ", " + this.getNome() + ", " + this.getCognome() + ", "
				+ sdf.format(this.getDataNascita()) + "]";
	}
}
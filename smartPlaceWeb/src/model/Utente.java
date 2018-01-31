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
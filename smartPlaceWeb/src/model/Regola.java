package model;

public class Regola {

	private int id; // primary key
	private String nome;
	private float valoreMisurazione;
	private String condizione; // L'utente deve stabilire se il
								// valoreMisurazione che fa attivare la regola è
								// maggiore o minore di un valore rilevato

	private Utente utente;
	private Sensore sensore;

	public Regola() {
	}

	public Regola(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getValoreMisurazione() {
		return valoreMisurazione;
	}

	public void setValoreMisurazione(float valoreMisurazione) {
		this.valoreMisurazione = valoreMisurazione;
	}

	public String getCondizione() {
		return condizione;
	}
	
	public void setCondizione(String condizione) {
		this.condizione = condizione;
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
		Regola regola = (Regola) object;
		return (this.getId() == regola.getId());
	}

	@Override
	public String toString() {
		return "Regola  " + nome + " utente: " + utente.getEmail() + " sensore indirizzo: "
				+ sensore.getArduino().getIndirizzoIP() + " stanzaSensore: " + sensore.getStanza() + " tipoSensore "
				+ sensore.getTipo();
	}

}

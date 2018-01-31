package model;

public class Arduino {

	private String indirizzoIP;
	private int porta;
	private Utente utente;

	public Arduino() {
	}

	public Arduino(String indirizzoIP, int porta) {
		this.indirizzoIP = indirizzoIP;
		this.porta = porta;
	}

	public String getIndirizzoIP() {
		return indirizzoIP;
	}
	
	public void setIndirizzoIP(String indirizzoIP) {
		this.indirizzoIP = indirizzoIP;
	}
	
	public int getPorta() {
		return porta;
	}
	
	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public boolean equals(Object object) {
		Arduino arduino = (Arduino) object;
		return (this.getIndirizzoIP() == arduino.getIndirizzoIP());
	}
	
}
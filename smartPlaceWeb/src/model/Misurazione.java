package model;

import java.util.Date;

public class Misurazione {

	private Sensore sensore;
	private Date giorno;
	private Date ora;
	private float valore;

	public Misurazione() {
	}

	public Misurazione(float valore) { // Da vedere se va bene valore.
		this.valore = valore;
	}

	public Sensore getSensore() {
		return sensore;
	}

	public void setSensore(Sensore sensore) {
		this.sensore = sensore;
	}

	public Date getGiorno() {
		return giorno;
	}

	public void setGiorno(Date giorno) {
		this.giorno = giorno;
	}

	public Date getOra() {
		return ora;
	}

	public void setOra(Date ora) {
		this.ora = ora;
	}

	public float getValore() {
		return valore;
	}

	public void setValore(float valore) {
		this.valore = valore;
	}

}
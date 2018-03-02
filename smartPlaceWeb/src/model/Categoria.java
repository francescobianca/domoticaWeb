package model;

import java.util.Set;

public class Categoria {

	private String nome;
	private Set<Domanda> domande;
	
	public Categoria() {
	
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Set<Domanda> getDomande() {
		return domande;
	}
	
	public void setDomande(Set<Domanda> domande) {
		this.domande = domande;
	}
	
	public void addDomanda(Domanda domanda){
		this.getDomande().add(domanda);
	}
	
	public void removeDomanda(Domanda domanda){
		this.getDomande().remove(domanda);
	}
	
	
}

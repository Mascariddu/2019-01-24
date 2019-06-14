package it.polito.tdp.extflightdelays.model;

public class Adiacenza {

	String partenza;
	String destinazione;
	int peso;
	
	public String getPartenza() {
		return partenza;
	}
	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}
	public String getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public Adiacenza(String partenza, String destinazione, int peso) {
		super();
		this.partenza = partenza;
		this.destinazione = destinazione;
		this.peso = peso;
	}
}

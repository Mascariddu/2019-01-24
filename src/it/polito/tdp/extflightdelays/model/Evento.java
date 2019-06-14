package it.polito.tdp.extflightdelays.model;

public class Evento{

	public enum Tipo{
		PARTENZA,
		ARRIVO;
	}
	
	String stato;
	Tipo tipo;
	
	public Evento(String stato, Tipo tipo) {
		super();
		this.stato = stato;
		this.tipo = tipo;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}

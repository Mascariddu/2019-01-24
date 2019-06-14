package it.polito.tdp.extflightdelays.model;

public class Evento implements Comparable<Evento>{

	public enum Tipo{
		PARTENZA,
		ARRIVO;
	}
	
	String stato;
	Tipo tipo;
	int giorno;
	
	public Evento(String stato, Tipo tipo,int giorno) {
		super();
		this.stato = stato;
		this.tipo = tipo;
		this.giorno = giorno;
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
	
	@Override
	public int compareTo(Evento arg0) {
		// TODO Auto-generated method stub
		return giorno - arg0.getGiorno();
	}
	
	public int getGiorno() {
		return giorno;
	}
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}
}

package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import it.polito.tdp.extflightdelays.model.Evento.Tipo;

class Simulatore {
	
	public Simulatore(int turisti,String state, int giorni, SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> grafo) {
		
		this.turisti = turisti;
		this.state = state;
		this.giorni = giorni;
		this.graph = grafo;
		idMap = new HashMap<String, Integer>();
		queue = new PriorityQueue<Evento>();
	}
	
	//stato del sistema
	HashMap<String, Integer> idMap;
	PriorityQueue<Evento> queue;

	//parametri simulazione
	int turisti;
	int giorni;
	String state;
	private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;
	
	//init
	public void init() {
		
		for(int i = 0; i < giorni; i++) {
			for(i = 0 ; i < turisti ; i++) {
				
				queue.add(new Evento(state,Tipo.PARTENZA));
				
			}
		}
		
	}
	
	//run
	public void run() {
		
		Evento evento;
		
		while((evento = queue.poll()) != null) {
			
			switch (evento.getTipo()) {
			case PARTENZA:
					
				break;

			case ARRIVO:
				
				break;
			}
			
		}
	}
}

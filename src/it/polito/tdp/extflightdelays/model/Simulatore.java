package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.sun.media.jfxmedia.events.NewFrameEvent;

import it.polito.tdp.extflightdelays.model.Evento.Tipo;

class Simulatore {
	
	public Simulatore(int turisti,String state, int giorni, DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> grafo, HashMap<String, Integer> idMap) {
		
		this.turisti = turisti;
		this.state = state;
		this.giorni = giorni;
		this.graph = grafo;
		this.idMap = new HashMap<String, Integer>(idMap);
		queue = new PriorityQueue<Evento>();
	}
	
	//stato del sistema
	PriorityQueue<Evento> queue;

	//parametri simulazione
	int turisti;
	int giorni;
	String state;
	private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph;
	
	//output
	HashMap<String, Integer> idMap;
	
	//init
	public void init() {
		
		for(int i = 0 ; i < turisti ; i++) {
				queue.add(new Evento(state,Tipo.PARTENZA,1));
			}
		
		idMap.replace(state, turisti);
		
	}
	
	//run
	public void run() {
		
		Evento evento;
		boolean flag = true;
		
		while((evento = queue.poll()) != null && flag==true) {
			
			if(evento.getGiorno() <= this.giorni) {
			
			switch (evento.getTipo()) {
			case PARTENZA:
				
					System.out.println("Parte da: "+evento.getStato()+" nel giorno "+evento.getGiorno());
					String stato = "";
					
					List<DefaultWeightedEdge> outgoing = new ArrayList<DefaultWeightedEdge>(graph.outgoingEdgesOf(evento.getStato()));
					for(DefaultWeightedEdge edge : outgoing) {
						
						double prob = graph.getEdgeWeight(edge)/getPeso(outgoing);
						
						Random random = new Random();
						
						if(prob > random.nextDouble()) {
							stato = graph.getEdgeTarget(edge);
							System.out.println("destinazione aggiornata");
						}
					}
					
					if(stato.equals("")) {
						stato = evento.getStato();
						System.out.println("Resta nello stato");
					}
					
					queue.add(new Evento(stato,Tipo.ARRIVO,evento.getGiorno()));
					idMap.replace(evento.getStato(), idMap.get(evento.getStato())-1);
				break;

			case ARRIVO:
					System.out.println("Arrivato a : "+evento.getStato()+" nel giorno "+evento.getGiorno());
					idMap.replace(evento.getStato(), idMap.get(evento.getStato())+1);
					queue.add(new Evento(evento.getStato(),Tipo.PARTENZA,(evento.getGiorno())+1));
				break;
			}
			
		} else flag = false;
		}
	}

	private double getPeso(List<DefaultWeightedEdge> outgoing) {
		// TODO Auto-generated method stub
		double somma = 0.0;
		
		for(DefaultWeightedEdge e: outgoing)
			somma += graph.getEdgeWeight(e);
		
		return somma;
	}
	
	public HashMap<String, Integer> getMap(){
		return this.idMap;
	}
}

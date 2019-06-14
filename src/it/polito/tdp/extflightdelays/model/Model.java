package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	ExtFlightDelaysDAO dao;
	List<String> states;
	SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> grafo;
	List<Adiacenza> edges;
	HashMap<String, Integer> idMap;
	
	public Model() {
		
		dao = new ExtFlightDelaysDAO();
		idMap = new HashMap<String, Integer>();
		states = new ArrayList<String>(dao.loadAllStates(this.idMap));
		edges = new ArrayList<Adiacenza>(dao.loadArchi());
	}

	public void creaGrafo() {
		// TODO Auto-generated method stub
		grafo = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, states);
		
		for(Adiacenza a : edges) {
			Graphs.addEdge(grafo, a.partenza, a.destinazione, a.peso);
		}
		
		for(String string : grafo.vertexSet()) {
			System.out.println(string+" con "+grafo.degreeOf(string)+" archi");
		}
		System.out.println("#vertici : "+grafo.vertexSet().size());
		System.out.println("#archi: "+grafo.edgeSet().size());
	}

	public List<String> getStates() {
		// TODO Auto-generated method stub
		Collections.sort(states);
		return states;
	}

	public String getResult(String value) {
		// TODO Auto-generated method stub
		String res = "";
		List<DefaultWeightedEdge> outgoing = new ArrayList<DefaultWeightedEdge>();
		
		outgoing.addAll(grafo.outgoingEdgesOf(value));
		
		Collections.sort(outgoing, new Comparator<DefaultWeightedEdge>() {

			@Override
			public int compare(DefaultWeightedEdge o1, DefaultWeightedEdge o2) {
				// TODO Auto-generated method stub
				if( grafo.getEdgeWeight(o1) > grafo.getEdgeWeight(o2))
					return 1;
				if(grafo.getEdgeWeight(o1) < grafo.getEdgeWeight(o2))
					return -1;
				
				return 0;
			}
			
			
		});
		
		for(DefaultWeightedEdge edge : outgoing) {
			res+= grafo.getEdgeTarget(edge) + " con peso: "+grafo.getEdgeWeight(edge)+"\n";
		}
		
		return res;
	}

	public HashMap<String, Integer> getMap() {
		// TODO Auto-generated method stub
		return this.idMap;
	}

	public void simula(int parseInt, String value, int parseInt2) {
		// TODO Auto-generated method stub
		Simulatore simulatore = new Simulatore(parseInt, value, parseInt2, this.grafo);
		
		simulatore.init();
		
		simulatore.run();
		
	}

}

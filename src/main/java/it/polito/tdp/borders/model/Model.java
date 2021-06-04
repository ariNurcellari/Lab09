package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao ;
	private Map <Integer, Country> idMap ;
	private Graph<Country, DefaultEdge> grafo ;
	private List<Border> lista ;

	public Model() {
		dao = new BordersDAO() ;
		idMap = new HashMap<>() ;
		grafo = new SimpleDirectedGraph<>(DefaultEdge.class) ;
		lista = new ArrayList<Border>() ;
	}
	
	public void creaGrafo (int anno) {
		dao.loadAllCountries(idMap);
		lista = dao.getCountryPairs(idMap, anno) ;
		
		Graphs.addAllVertices(grafo, idMap.values()) ;
		
		
		
		
	}
	
	public Integer getVertex() {
		return grafo.vertexSet().size() ;
	}
	
	public Integer getEdges() {
		return grafo.edgeSet().size() ;
	}
}

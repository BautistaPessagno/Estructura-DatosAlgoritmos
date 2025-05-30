package core;


import java.util.*;

import java.util.Map.Entry;
import core_service.GraphService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.Map;

abstract public class AdjacencyListGraph<V, E> implements GraphService<V, E> {

	private boolean isSimple;
	protected boolean isDirected;
	private boolean acceptSelfLoop;
	private boolean isWeighted;
	protected String type;
	
	// HashMap no respeta el orden de insercion. En el testing considerar eso
	private Map<V,Collection<InternalEdge>> adjacencyList= new HashMap<>();
	
	// respeta el orden de llegada y facilita el testing
	//	private Map<V,Collection<InternalEdge>> adjacencyList= new LinkedHashMap<>();
	
	protected   Map<V,  Collection<AdjacencyListGraph<V, E>.InternalEdge>> getAdjacencyList() {
		return adjacencyList;
	}
	
	
	protected AdjacencyListGraph(boolean isSimple, boolean isDirected, boolean acceptSelfLoop, boolean isWeighted) {
		this.isSimple = isSimple;
		this.isDirected = isDirected;
		this.acceptSelfLoop= acceptSelfLoop;
		this.isWeighted = isWeighted;

		this.type = String.format("%s %sWeighted %sGraph with %sSelfLoop", 
				isSimple ? "Simple" : "Multi", isWeighted ? "" : "Non-",
				isDirected ? "Di" : "", acceptSelfLoop? "":"No ");
	}
	
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public void addVertex(V aVertex) {
	
		if (aVertex == null )
		throw new IllegalArgumentException(Messages.getString("addVertexParamCannotBeNull"));
	
		// no edges yet
		getAdjacencyList().putIfAbsent(aVertex, 
				new ArrayList<InternalEdge>());
	}


	@Override
	public int numberOfVertices() {
		return getVertices().size();
	}

	@Override
	public Collection<V> getVertices() {
		return getAdjacencyList().keySet() ;
	}
	
	@Override
	public int numberOfEdges() {
		int count = 0;
		for (Collection<InternalEdge> edges : getAdjacencyList().values()) {
			count += edges.size();
		}

		if (!isDirected) {
			count /= 2; // each edge is counted twice
		}

		return count;

	}

	

	@Override
	public void addEdge(V aVertex, V otherVertex, E theEdge) {

		// validation!!!!
		if (aVertex == null || otherVertex == null || theEdge == null)
			throw new IllegalArgumentException(Messages.getString("addEdgeParamCannotBeNull"));

		// es con peso? debe tener implementado el metodo double getWeight()
		if (isWeighted) {
			// reflection
			Class<? extends Object> c = theEdge.getClass();
			try {
				c.getDeclaredMethod("getWeight");
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(
						type + " is weighted but the method double getWeighed() is not declared in theEdge");
			}
		}
		
		if (! acceptSelfLoop && aVertex.equals(otherVertex)) {
			throw new RuntimeException(String.format("%s does not accept self loops between %s and %s" , 
					type, aVertex, otherVertex) );
		}

		// if any of the vertex is not presented, the node is created automatically
		addVertex(aVertex);
		addVertex(otherVertex);
		

	}




	@Override
	public boolean removeVertex(V aVertex) {
		if(aVertex == null)
			throw new RuntimeException("Vertex is null");

		if(adjacencyList.get(aVertex) == null)
			return false;

		if(isDirected){
			adjacencyList.remove(aVertex);
			for(Map.Entry<V, Collection<InternalEdge>> entry : adjacencyList.entrySet()) {
				entry.getValue().removeIf(edge -> edge.target.equals(aVertex));
			}
		}
		else {
			for(InternalEdge edge : adjacencyList.get(aVertex)) {
				if(!edge.target.equals(aVertex)) {
					adjacencyList.get(edge.target).removeIf(otherEdge -> otherEdge.target.equals(aVertex));
				}
			}
			adjacencyList.remove(aVertex);
		}
		return true;
	}

	@Override
	public boolean removeEdge(V aVertex, V otherVertex) {
		if (aVertex == null || otherVertex == null)
			throw new IllegalArgumentException(Messages.getString("removeEdgeParamCannotBeNull"));

		Collection<InternalEdge> adjListSrc = getAdjacencyList().get(aVertex);
		if (adjListSrc == null) {
			return false; // no such vertex
		}

		boolean removed = adjListSrc.removeIf(edge -> edge.target.equals(otherVertex));

		if (!isDirected) {
			Collection<InternalEdge> adjListDst = getAdjacencyList().get(otherVertex);
			if (adjListDst != null) {
				adjListDst.removeIf(edge -> edge.target.equals(aVertex));
			}
		}

		return removed;
	}

	@Override
	public boolean removeEdge(V aVertex, V otherVertex, E theEdge) {
		// COMPLETAR
		throw new RuntimeException("not implemented yet");
	}



	@Override
	public void dump() {
		System.out.println("Graph dump: " + getType());
		for (V vertex : getVertices()) {
			System.out.println(vertex + " -> " + getAdjacencyList().get(vertex));
		}
		System.out.println("Number of vertices: " + numberOfVertices());
		System.out.println("Number of edges: " + numberOfEdges());
	}

	@Override
	public int degree(V aVertex) {
		//Si es dirigido, no podemos hablar de degree
		if(isDirected)
			throw new RuntimeException("directed graph cannot call this method");

		if(aVertex == null)
			throw new RuntimeException("vertex cannot be null");

		if(adjacencyList.get(aVertex) == null)
			throw new RuntimeException("vertex not found");

		return adjacencyList.get(aVertex).size();
	}

	@Override
	public int inDegree(V aVertex) {
		//Solo para dirigidos
		if(!isDirected)
			throw new RuntimeException("graph that isn't directed, cannot call this method");

		if(aVertex == null)
			throw new RuntimeException("vertex cannot be null");

		int degree = 0;

		for(Map.Entry<V, Collection<InternalEdge>> entry : adjacencyList.entrySet()) {
			for(InternalEdge edge : entry.getValue()) {
				if(edge.target.equals(aVertex))
					degree++;
			}
		}

		return degree;
	}

	@Override
	public int outDegree(V aVertex) {
		//Solamente para dirigidos
		if(!isDirected)
			throw new RuntimeException("graph that isn't directed cannot call this method");

		if(aVertex == null)
			throw new RuntimeException("vertex cannot be null");

		if(adjacencyList.get(aVertex) == null)
			throw new RuntimeException("vertex not found");

		return adjacencyList.get(aVertex).size();
	}






	class InternalEdge {
		E edge;
		V target;

		InternalEdge(E propEdge, V target) {
			this.target = target;
			this.edge = propEdge;
		}

		@Override
		public boolean equals(Object obj) {
			@SuppressWarnings("unchecked")
			InternalEdge aux = (InternalEdge) obj;

			return ((edge == null && aux.edge == null) || (edge != null && edge.equals(aux.edge)))
					&& target.equals(aux.target);
		}

		@Override
		public int hashCode() {
			return target.hashCode();
		}

		@Override
		public String toString() {
			return String.format("-[%s]-(%s)", edge, target);
		}
	}

	public void printBFS(V vertex){
		if(!getAdjacencyList().containsKey(vertex))
			return;
		Queue<V> queue = new LinkedList<>();
		Map<V, Boolean> visited = new HashMap<>();
		queue.add(vertex);
		visited.put(vertex, true);
		while(!queue.isEmpty()){
			V current = queue.remove();
			System.out.print(current + " ");
			for(InternalEdge edge: getAdjacencyList().get(current)){
				if(!visited.getOrDefault(edge.target, false)){
					queue.add(edge.target);
					visited.put(edge.target, true);
				}
			}
		}
		System.out.println();
	}

	//DFS iterativo
	public void printDFS(V vertex) {
		if(!getAdjacencyList().containsKey(vertex))
			return;
		LinkedList<V> stack = new LinkedList<>();
		Map<V, Boolean> visited = new HashMap<>();
		stack.push(vertex);
		visited.put(vertex, true);
		while(!stack.isEmpty()){
			V current = stack.pop();
			System.out.print(current + " ");
			for(InternalEdge edge: getAdjacencyList().get(current)){
				if(!visited.getOrDefault(edge.target, false)){
					stack.push(edge.target);
					visited.put(edge.target, true);
				}
			}
		}
		System.out.println();
	}

	////DFS recursivo
	public void printDFSRec(V vertex) {
		if (!getAdjacencyList().containsKey(vertex))
			return;
		Map<V, Boolean> visited = new HashMap<>();
		printDFS(vertex, visited);
		System.out.println();
	}

	private void printDFS(V vertex, Map<V, Boolean> visited){
		if(visited.getOrDefault(vertex, false))
			return;
		visited.put(vertex, true);
		System.out.println(vertex + " ");
		for(InternalEdge edge : getAdjacencyList().get(vertex))
			printDFS(edge.target, visited);
	}

	public Iterable<V> getBFS(V startNode){
		if (startNode == null || !getAdjacencyList().containsKey(startNode))
			throw new IllegalArgumentException(Messages.getString("vertexParamError"));
		return new Iterable<V>(){

			@Override
			public Iterator<V> iterator(){
				return new BFSIterator(startNode);
			}
		};
	}

	public Iterable<V> getDFS(V startNode){
		if (startNode == null || !getAdjacencyList().containsKey(startNode))
			throw new IllegalArgumentException(Messages.getString("vertexParamError"));
		return new Iterable<V>(){

			@Override
			public Iterator<V> iterator(){
				return new DFSIterator(startNode);
			}
		};
	}

	private class BFSIterator implements Iterator<V>{

		private Queue<V> queue = new LinkedList<>();
		private Map<V, Boolean> visited = new HashMap<>();

		public BFSIterator(V start){
			queue.add(start);
			visited.put(start, true);
		}

		@Override
		public boolean hasNext() {
			return !queue.isEmpty();
		}

		@Override
		public V next() {
			if(!hasNext())
				throw new NoSuchElementException();
			V current = queue.remove();
			for(InternalEdge edge: getAdjacencyList().get(current)){
				if(!visited.getOrDefault(edge.target, false)){
					queue.add(edge.target);
					visited.put(edge.target, true);
				}
			}
			return current;
		}
	}

	private class DFSIterator implements Iterator<V>{

		private Queue<V> queue = new LinkedList<>();
		private Map<V, Boolean> visited = new HashMap<>();

		public DFSIterator(V start){
			queue.add(start);
			visited.put(start, true);
		}

		@Override
		public boolean hasNext() {
			return !queue.isEmpty();
		}

		@Override
		public V next() {
			if(!hasNext())
				throw new NoSuchElementException();
			V current = queue.remove();
			for(InternalEdge edge: getAdjacencyList().get(current)){
				if(!visited.getOrDefault(edge.target, false)){
					queue.add(edge.target);
					visited.put(edge.target, true);
				}
			}
			return current;
		}
	}
	
	
}

package nz.ac.auckland.se281.a4.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nz.ac.auckland.se281.a4.TwitterHandle;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS 
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	protected Map<Node<String>, LinkedList<Edge<Node<String>>>> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	protected Node<String> root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */

	/**
	 * Creates a Graph
	 * 
	 * @param edges a list of edges to be added to the graph
	 */
	public Graph(List<String> edges) {
		adjacencyMap = new LinkedHashMap<>();
		int i = 0;
		if (edges.isEmpty()) {
			throw new IllegalArgumentException("edges are empty");
		}

		for (String edge : edges) {
			String[] split = edge.split(",");
			Node<String> source = new Node<String>(split[0]);
			Node<String> target = new Node<String>(split[1]);
			Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new LinkedList<Edge<Node<String>>>());
			}
			adjacencyMap.get(source).append(edgeObject);

			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * This method returns a boolean based on whether the input sets are reflexive.
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {

		// iterating through each vertex
		for (String vertexString : set) {

			// converting vertex string to a node
			Node<String> vertex = new Node<String>(vertexString);

			// boolean for if the edge (vertex,vertex) exists
			boolean isSelfEdge = false;

			// iterating through all the edges
			for (String edgeString : relation) {

				// transforming string to an edge object
				Edge<Node<String>> edge = turnStringToEdge(edgeString);

				// getting values of source and target nodes
				Node<String> source = edge.getSource();
				Node<String> target = edge.getTarget();

				// checking if self edge (vertex,vertex) exists
				if ((source.equals(vertex)) && (target.equals(vertex))) {
					isSelfEdge = true;
					break; // breaks inner for loop
				}
			}

			// ensuring the self edge exists for each vertex
			if (!isSelfEdge) {
				return false; // returns false if there exists a vertex without a self edge
			}
		}

		// returns true if isSelfEdge is true for all vertices
		return true;

	}

	/**
	 * This method returns a boolean based on whether the input set is symmetric.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be symmetric tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {

		// iterating through each edge
		for (String edgeString : relation) {

			// converting edge from string to Edge object
			Edge<Node<String>> edge = turnStringToEdge(edgeString);

			// source and target variables
			Node<String> source = edge.getSource();
			Node<String> target = edge.getTarget();

			// required string for symmetry to occure
			String requiredElement = target.getValue() + "," + source.getValue();

			// ensuring there exists the symmetrical edge exists
			if (!relation.contains(requiredElement)) {
				System.out.println("For the graph to be symmetric tuple: " + requiredElement + " MUST be present");
				return false;
			}
		}

		return true;
	}

	/**
	 * This method returns a boolean based on whether the input set is transitive.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be transitive tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {

		// iterating through each edge
		for (String edgeString : relation) {

			// converting edge from string to edge object
			Edge<Node<String>> edge = turnStringToEdge(edgeString);

			// iterating through each edge again
			for (String edgeString2 : relation) {

				// converting edge from string to object
				Edge<Node<String>> edgeToCompare = turnStringToEdge(edgeString2);

				// checking if there is possibility of transitivity
				// i.e. if (x,y) and (y,z) exists
				if (edge.getTarget().equals(edgeToCompare.getSource())) {

					// required string
					String requiredElement = edge.getSource().getValue() + "," + edgeToCompare.getTarget().getValue();

					// ensuring (x,z) exists
					if (!relation.contains(requiredElement)) {
						System.out.println(
								"For the graph to be transitive tuple: " + requiredElement + " MUST be present");
						return false;
					}
				}
			}
		}

		return true;

	}

	/**
	 * This method returns a boolean based on whether the input sets are of
	 * equivalence relation. TODO: Complete this method (Note a set is not passed in
	 * as a parameter but a list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is an equivalence relation
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {
		// equivalence when the relation is reflexive, symmetric, and transitive
		return (isReflexive(set, relation) && isSymmetric(relation) && isTransitive(relation));
	}

	/**
	 * This method returns a List of the equivalence class
	 * 
	 * If the method can not find the equivalence class, then The following must be
	 * printed to the console: "Can't compute equivalence class as this is not an
	 * equivalence relation"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param node     A "TwitterHandle" in the graph
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return List that is the equivalence class or null if relation isn't
	 *         equivalence relation.
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {

		// ensuring relation is an equivalence relation
		if (!isEquivalence(set, relation)) {
			System.out.println("Can't compute equivalence class as this is not an equivalence relation.");
			return null;

		} else {
			// creating hash set for keeping track of equivalence classes
			HashSet<String> equivClassSet = new HashSet<String>();

			// each node contains itself in the equivalence class
			equivClassSet.add(node);

			// iterating through each edge string
			for (String edgeString : relation) {

				// converting each edge from string to edge object
				Edge<Node<String>> edge = turnStringToEdge(edgeString);

				// source and target variables
				String source = edge.getSource().getValue();
				String target = edge.getTarget().getValue();

				// adds a if edge = (x,a)
				if (node.equals(source)) {
					equivClassSet.add(target);
				}
			}

			// creating new list for equivalence classes
			List<String> equivClassList = new ArrayList<String>(equivClassSet);

			return equivClassList;
		}
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {
		return breadthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start, boolean rooted) {// name to breadthFirstSearch

		// creating a hashset for order
		LinkedHashSet<Node<String>> bfsOrderSet = new LinkedHashSet<Node<String>>();

		// creating a queue
		NodesStackAndQueue<Node<String>> queue = new NodesStackAndQueue<Node<String>>();

		// set of all source nodes in the graph converted to list
		Set<Node<String>> setOfSourceNodes = this.adjacencyMap.keySet();
		List<Node<String>> listOfSourceNodes = new ArrayList<Node<String>>(setOfSourceNodes);

		// starting with start node
		Node<String> currentSourceNode = start;

		while (currentSourceNode != null) {
			// adding current source node to the order hashset
			bfsOrderSet.add(currentSourceNode);

			// getting all the successors of current source node into an arraylist
			LinkedList<Edge<Node<String>>> successors = this.adjacencyMap.get(currentSourceNode);

			// if the current source node has successors and if it hasn't been visited
			if (successors != null && listOfSourceNodes.contains(currentSourceNode)) {

				// iterating through each successor in successors linked list
				for (int i = 0; i <= successors.size() - 1; i++) {

					// getting the successor's target
					Node<String> target = successors.get(i).getTarget();

					// adding all targets to the queue
					queue.append(target);
				}
			}

			// removing current source node from list of source nodes
			listOfSourceNodes.remove(currentSourceNode);

			// assigning the next source node to search
			if (!queue.isEmpty()) {
				// assigns next source node to be the next successor in queue
				currentSourceNode = queue.pop();

			} else if (!listOfSourceNodes.isEmpty()) {
				// if queue is empty, then visit next root
				currentSourceNode = listOfSourceNodes.get(0);
			}

			else {
				// if everything is visited, stop the loop
				currentSourceNode = null;
			}

		}

		// converting bfs order set to a list
		List<Node<String>> bfsOrderList = new ArrayList<Node<String>>(bfsOrderSet);
		return bfsOrderList;

	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {
		return depthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start, boolean rooted) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * @return returns the set of all nodes in the graph
	 */
	public Set<Node<String>> getAllNodes() {

		Set<Node<String>> out = new HashSet<>();
		out.addAll(adjacencyMap.keySet());
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i).getSource());
				out.add(list.get(i).getTarget());
			}
		}
		return out;
	}

	/**
	 * @return returns the set of all edges in the graph
	 */
	protected Set<Edge<Node<String>>> getAllEdges() {
		Set<Edge<Node<String>>> out = new HashSet<>();
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i));
			}
		}
		return out;
	}

	/**
	 * @return returns the set of twitter handles in the graph
	 */
	public Set<TwitterHandle> getUsersFromNodes() {
		Set<TwitterHandle> users = new LinkedHashSet<TwitterHandle>();
		for (Node<String> n : getAllNodes()) {
			users.add(new TwitterHandle(n.getValue()));
		}
		return users;
	}

	/**
	 * This method turns strings to an edge object which separates the source and
	 * the target.
	 * 
	 * @param edge the string that contains the relation
	 * @return the edge object that contains the source and target vertices
	 */
	private static Edge<Node<String>> turnStringToEdge(String edge) {
		// separating the string into an array
		String[] split = edge.split(",");

		// taking each array element and separating into source and target
		Node<String> source = new Node<String>(split[0]);
		Node<String> target = new Node<String>(split[1]);

		// creating edge object
		Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

		return edgeObject;
	}

}

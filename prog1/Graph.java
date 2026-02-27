package prog1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: 016916341                                        */
/* CS 3310, Spring 2026                                       */
/* Programming Assignment 1                                   */
/* Graph class: Represent an undirected graph with            */
/*              DFS-based methods                             */
/**************************************************************/
public class Graph {
	// Number of vertices in the graph
	private int numVertices;

	// Number of edges in the graph
	private int numEdges;

	// Adjacency list: maps vertex to list of adjacent vertices
	private Map<Integer, List<Integer>> adjacencyList;

	// Tracking DFS State:
	private Set<Integer> visited; // Track visited vertices
	private List<Set<Integer>> components; // List of all connected components
	private Map<Integer, Integer> parent; // Detect cycles via parent tracking
	private boolean hasCycle; // Flag indicating cycled found
	private List<Integer> cycle; // Track cycle path

	/**************************************************************/
	/* Constructor: Graph                                         */
	/* Purpose: Initialize empty graph with numVertices vertices  */
	/* Parameters:                                                */
	/*   int numVertices: number of vertices in the graph         */
	/**************************************************************/
	public Graph(int numVertices) {
		this.numVertices = numVertices;
		this.numEdges = 0;
		this.adjacencyList = new HashMap<>();
	}

	/**************************************************************/
	/* Method: addEdge                                            */
	/* Purpose: Add an undirected edge between two vertices       */
	/* Parameters:                                                */
	/*   int vertex1: first vertex                                */
	/*   int vertex2: second vertex                               */
	/**************************************************************/
	public void addEdge(int vertex1, int vertex2) {
		// Add vertex2 to vertex1's neighbor adjacency list
		adjacencyList.putIfAbsent(vertex1, new ArrayList<>());
		List<Integer> neighbors1 = adjacencyList.get(vertex1);
		if (!neighbors1.contains(vertex2)) {
			neighbors1.add(vertex2);
		}
		
		// Add vertex1 to vertex2's neighbor adjacency list (undirected graph)
		adjacencyList.putIfAbsent(vertex2, new ArrayList<>());
		List<Integer> neighbors2 = adjacencyList.get(vertex2);
		if (!neighbors2.contains(vertex1)) {
			neighbors2.add(vertex1);
		}

		// Increment edge count
		numEdges++;
	}

	/**************************************************************/
	/* Getters: getNumVertices and getNumEdges                    */
	/* Purpose: Get the number of vertices/edges                  */
	/* Returns: int - number of vertices/edges in graph           */
	/**************************************************************/
	public int getNumVertices() {
		return numVertices;
	}

	public int getNumEdges() {
		return numEdges;
	}

	/**************************************************************/
	/* Method: findComponentsAndCycles                            */
	/* Purpose: Find connected components and detect cycles       */
	/**************************************************************/
	public void findComponentsAndCycles() {
		// Initialize visited set and components list
		visited = new HashSet<>();
		components = new ArrayList<>();

		// Initialize cycle detection variables
		parent = new HashMap<>();
		hasCycle = false;
		cycle = null;

		// Iterate through all vertices to find unvisited ones and start DFS
		for (int vertex = 1; vertex <= numVertices; vertex++) {
			// If vertex is not visited, it is the start of a new component
			if (!visited.contains(vertex)) {
				// Create a new component set and add current vertex to it
				Set<Integer> currentComponent = new HashSet<>();
				components.add(currentComponent);

				// Start DFS from this vertex to find all vertices in the same component
				System.out.print("\tStarting DFS from vertex " + vertex + ": ");
				dfs(vertex, -1, currentComponent);
				System.out.println();
			}
		}

		// After finding all components, display results
		printComponents();
	}

	/**************************************************************/
	/* Method: dfs                                                */
	/* Purpose: Recursive depth-first search traversal            */
	/* Parameters:                                                */
	/*   int vertex: current vertex being visited                 */
	/*   int parentVertex: vertex we came from (-1 if root)       */
	/*   Set<Integer> component: current component being built    */
	/**************************************************************/
	private void dfs(int vertex, int parentVertex, Set<Integer> component) {
		// Mark this vertex as visited and add it to the current component
		visited.add(vertex);
		component.add(vertex);
		parent.put(vertex, parentVertex);
		System.out.print(vertex + " ");

		// Get adjacent vertices of current vertex
		List<Integer> neighbors = adjacencyList.getOrDefault(vertex, new ArrayList<>());

		// Visit each neighbor
		for (int neighbor : neighbors) {
			if (!visited.contains(neighbor)) {
				// If neighbor is not visited, continue DFS recursively
				dfs(neighbor, vertex, component);
			} else if (neighbor != parentVertex && !hasCycle) {
				// If neighbor is visited and is not the parent, a cycle is detected
				hasCycle = true;
				cycle = buildCycle(vertex, neighbor);
			}
		}
	}

	/**************************************************************/
	/* Constructor: buildCycle                                    */
	/* Purpose: Construct cycle path when back edge found         */
	/* Parameters:                                                */
	/*   int current: vertex that detected the back edge          */
	/*   int backTo: vertex the back edge points to               */
	/* Returns: List<Integer> - the cycle path                    */
	/**************************************************************/
	private List<Integer> buildCycle(int current, int backTo) {
		// List to store the cycle path
		List<Integer> path = new ArrayList<>();

		// Trace back from current vertex to backTo vertex using parent pointers
		int vertex = current;
		path.add(vertex);

		// Follow parent pointers until we reach backTo
		while (vertex != backTo) {
			vertex = parent.get(vertex);
			path.add(vertex);
		}

		// Add backTo again to close the cycle
		path.add(current);

		return path;
	}

	/**************************************************************/
	/* Method: printComponents                                    */
	/* Purpose: Display the connected components and cycle info   */
	/**************************************************************/
	private void printComponents() {
		// Print description based on number of components
		if (components.size() == 1) {
			System.out.print("1 connected component: ");
		} else {
			System.out.print(components.size() + " connected components: ");
		}

		// Print each component
		for (Set<Integer> component : components) {
			System.out.print("{");

			// Use a sorted list for consistent output
			List<Integer> sortedComponent = new ArrayList<>(component);
			Collections.sort(sortedComponent);

			// Print vertices separated by spaces
			for (int i = 0; i < sortedComponent.size(); i++) {
				System.out.print(sortedComponent.get(i));

				if (i < sortedComponent.size() - 1) {
					System.out.print(" ");
				}
			}

			System.out.print("} ");
		}

		System.out.println();

		// Print cycle information
		if (hasCycle && cycle != null) {
			System.out.println("Cycle detected: ");
			for (int i = 0; i < cycle.size(); i++) {
				System.out.print(cycle.get(i));

				if (i < cycle.size() - 1) {
					System.out.print(" - ");
				}
			}
			System.out.println();
		} else {
			System.out.println("The graph is acyclic.");
		}
	}
}

package prog1;

import java.util.ArrayList;
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

	// Track visited vertices for DFS
	private Set<Integer> visited;

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
		adjacencyList.get(vertex1).add(vertex2);

		// Add vertex1 to vertex2's neighbor adjacency list (undirected graph)
		adjacencyList.putIfAbsent(vertex2, new ArrayList<>());
		adjacencyList.get(vertex2).add(vertex1);

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
		// Initialize visited set
		visited = new HashSet<>();

		// Iterate through all vertices to find unvisited ones and start DFS
		for (int vertex = 1; vertex <= numVertices; vertex++) {
			// If vertex is not visited, it is the start of a new component
			if (!visited.contains(vertex)) {
				System.out.print("\tStarting DFS from vertex " + vertex + ": ");
				dfs(vertex, -1);
				System.out.println();
			}
		}
	}

	/**************************************************************/
	/* Method: dfs                                                */
	/* Purpose: Recursive depth-first search traversal            */
	/* Parameters:                                                */
	/*   int vertex: current vertex being visited                 */
	/*   int parent: vertex we came from (-1 if root)             */
	/**************************************************************/
	private void dfs(int vertex, int parent) {
		// Mark this vertex as visited
		visited.add(vertex);
		System.out.print(vertex + " ");

		// Get adjacent vertices of current vertex
		List<Integer> neighbors = adjacencyList.getOrDefault(vertex, new ArrayList<>());

		// Visit each neighbor
		for (int neighbor : neighbors) {
			// If neighbor is not visited, continue DFS recursively
			if (!visited.contains(neighbor)) {
				dfs(neighbor, vertex);
			}
		}
	}
}

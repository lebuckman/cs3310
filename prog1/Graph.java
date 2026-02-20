package prog1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}

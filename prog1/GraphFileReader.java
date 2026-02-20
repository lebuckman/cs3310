package prog1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: 016916341                                        */
/* CS 3310, Spring 2026                                       */
/* Programming Assignment 1                                   */
/* GraphFileReader class: Handle reading and parsing          */
/*                        graph data from input file          */
/**************************************************************/
public class GraphFileReader {
	// Input file to read from
	private String filename;

	/**************************************************************/
	/* Constructor: GraphFileReader                               */
	/* Purpose: Initialize GraphFileReader with filename          */
	/* Parameters:                                                */
	/*   String filename: name of input file                      */
	/**************************************************************/
	public GraphFileReader(String filename) {
		this.filename = filename;
	}

	/**************************************************************/
	/* Method: processGraphs                                      */
	/* Purpose: Read file and process each graph line             */
	/**************************************************************/
	public void processGraphs() {
		try {
			// Create Scanner to read file
			Scanner fileScanner = new Scanner(new File(filename));

			// Track graph number for output formatting
			int graphNumber = 1;

			// Read file line by line
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();

				// Skip whitespace lines
				if (line.isBlank()) {
					continue;
				}

				// Parse line into Graph object
				Graph graph = parseLine(line);

				// Testing
				System.out.println("Graph" + graphNumber + ":");
				System.out.println("Vertices: " + graph.getNumVertices());
				System.out.println("Edges: " + graph.getNumEdges());
				System.out.println();

				// Increment graph number for next graph
				graphNumber++;
			}

			fileScanner.close();

		} catch (FileNotFoundException e) {
			// Handle case where file is not found
			System.out.println("FileNotFoundException: " + e.getMessage());
		} 
	}

	/**************************************************************/
	/* Method: parseLine                                          */
	/* Purpose: Parse a line of input into a Graph object         */
	/* Parameters:                                                */
	/*   String line: line of input to parse                      */
	/* Returns: Graph - object representing the parsed graph      */
	/**************************************************************/
	private Graph parseLine(String line) {
		// Split lines into tokens separated by whitespace
		String[] tokens = line.trim().split("\\s+");

		// First token is number of vertices
		int numVertices = Integer.parseInt(tokens[0]);

		// Create new Graph from numVertices
		Graph graph = new Graph(numVertices);

		// Remaining tokens represent edges in format (v1,v2)
		for (int i = 1; i < tokens.length; i++) {
			// Remove parentheses and split by comma to get vertex numbers
			String[] vertices = tokens[i].replaceAll("[()]", "").split(",");

			// Parse vertex numbers and add edge to graph
			int vertex1 = Integer.parseInt(vertices[0]);
			int vertex2 = Integer.parseInt(vertices[1]);

			// Add edge to graph
			graph.addEdge(vertex1, vertex2);
		}

		return graph;
	}

}

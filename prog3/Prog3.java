package prog3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: lebuckman                                        */
/* CS 3310, Spring 2026                                       */
/* Programming Assignment 3                                   */
/* Prog3 class: Main driver for canoe trip cost optimization  */
/**************************************************************/
public class Prog3 {

	/**************************************************************/
	/* Method: main                                               */
	/* Purpose: Program entry point + access file from CLI        */
	/*          Read cost matrix, run DP solver, print results    */
	/* Parameters:                                                */
	/*   String[] args: command line arguments (expects filename) */
	/**************************************************************/
	public static void main(String[] args) {

		// Check if filename was provided
		if (args.length != 1) {
			System.out.println("Usage: java prog3.Prog3 <filename>");
			return;
		}

		// Attempt to read the cost matrix from the input file
		int[][] costMatrix = readCostMatrix(args[0]);
		if (costMatrix == null) {
			System.out.println("Error reading cost matrix from file.");
			return;
		}

		// Number of trading posts
		int numPosts = costMatrix.length;

		// Solve for optimal costs with DP
		CanoeSolver solver = new CanoeSolver(costMatrix, numPosts);
		solver.solve();

		// Print the optimal cost matrix for all post pairs (i, j)
		System.out.println("Optimal cost matrix:");
		solver.printOptimalCosts();

		// Print the optimal rental sequence from post 0 to post n-1
		System.out.println("\nOptimal rental sequence (post 0 to post "
				+ (numPosts - 1) + "):");
		solver.printRentalSequence(0, numPosts - 1);

		// Print the total optimal cost for the full trip
		System.out.println("\nTotal optimal cost: "
				+ solver.getOptimalCost(0, numPosts - 1));

	}

	/**************************************************************/
	/* Method: readCostMatrix                                     */
	/* Purpose: Parse the input file into a cost matrix           */
	/* Parameters:                                                */
	/*   String filePath: path to the input file                  */
	/* Returns: int[][] - the parsed cost matrix, or null         */
	/**************************************************************/
	private static int[][] readCostMatrix(String filePath) {
		try {
			Scanner scanner = new Scanner(new File(filePath));

			// Read the number of trading posts and initialize the cost matrix
			int numPosts = scanner.nextInt();
			int[][] costMatrix = new int[numPosts][numPosts];

			// Read the cost matrix from the file
			for (int i = 0; i < numPosts - 1; i++) {
				for (int j = i + 1; j < numPosts; j++) {
					costMatrix[i][j] = scanner.nextInt();
				}
			}

			scanner.close();
			return costMatrix;

		} catch (FileNotFoundException e) {
			// Handle case where file is not found
			System.out.println("FileNotFoundException: " + e.getMessage());
			return null;
		}
	}
}
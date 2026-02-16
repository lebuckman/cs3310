package prog1;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: 016916341                                        */
/* CS 3310, Spring 2026                                       */
/* Programming Assignment 1                                   */
/* Prog1 class: Main driver for graph component               */
/*              and cycle detection using DFS                 */
/**************************************************************/

public class Prog1 {

	/**************************************************************/
	/* Method: main                                               */
	/* Purpose: Reads graphs from file and analyzes               */
	/* Parameters:                                                */
	/*   String[] args: command line arguments (expects filename) */
	/* Returns: None                                              */
	/**************************************************************/
	public static void main(String[] args) {
		// Check if filename was provided
		if (args.length != 1) {
			System.out.println("Usage: java Prog1.java <filename>");
			return;
		}

		String filename = args[0];
		System.out.println("Filename: " + filename);
	}
}

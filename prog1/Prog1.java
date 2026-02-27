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
	/* Purpose: Program entry point + access file from CLI        */
	/* Parameters:                                                */
	/*   String[] args: command line arguments (expects filename) */
	/**************************************************************/
	public static void main(String[] args) {
		// Check if filename was provided
		if (args.length != 1) {
			System.out.println("Usage: java prog1.Prog1 <filename>");
			return;
		}

		String filename = args[0];

		GraphFileReader reader = new GraphFileReader(filename);
		reader.processGraphs();
	}
}

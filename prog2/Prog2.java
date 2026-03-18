package prog2;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: 016916341                                        */
/* CS 3310, Spring 2026                                       */
/* Programming Assignment 2                                   */
/* Prog2 class: Main driver for anagram detection             */
/**************************************************************/
public class Prog2 {

	/**************************************************************/
	/* Method: main                                               */
	/* Purpose: Program entry point + access file from CLI        */
	/* Parameters:                                                */
	/*   String[] args: command line arguments (expects filename) */
	/**************************************************************/
	public static void main(String[] args) {
		// Check if filename was provided
		if (args.length != 1) {
			System.out.println("Usage: java prog2.Prog2 <filename>");
			return;
		}

		String filename = args[0];
		System.out.println("Filename: " + filename);
	}
}

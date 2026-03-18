package prog2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: lebuckman                                        */
/* CS 3310, Spring 2026                                       */
/* Programming Assignment 2                                   */
/* AnagramFinder class: Find anagram sets in a list of        */
/*                      words using presorting                */
/**************************************************************/
public class AnagramFinder {
	// Map words by their sorted letter signature
	private Map<String, List<String>> anagramGroups;

	/**************************************************************/
	/* Constructor: AnagramFinder                                 */
	/* Purpose: Initialize the anagram finder                     */
	/**************************************************************/
	public AnagramFinder() {
		this.anagramGroups = new HashMap<>();
	}

	/**************************************************************/
	/* Method: processFile                                        */
	/* Purpose: Read words from file and find anagram sets        */
	/* Parameters:                                                */
	/*   String filename: path to input file                      */
	/**************************************************************/
	public void processFile(String filename) {
		try {
			// Create Scanner to read file 
			Scanner scanner = new Scanner(new File(filename));

			// Read file line by line
			while (scanner.hasNextLine()) {
				String word = scanner.nextLine().trim();

				// Skip whitespace lines
				if (word.isBlank()) {
					continue;
				}

				// Testing output
				System.out.println("Word read: " + word);
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			// Handle case where file is not found
			System.out.println("FileNotFoundException: " + e.getMessage());
		}
	}
}

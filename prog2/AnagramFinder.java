package prog2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: 016916341                                        */
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

				// Get signature of word (sorted letters)
				String signature = getSignature(word);

				System.out.println("word: " + word + " -> " + signature); // Test

				// Add word to the appropriate anagram group
				anagramGroups.putIfAbsent(signature, new ArrayList<>());
				anagramGroups.get(signature).add(word);
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			// Handle case where file is not found
			System.out.println("FileNotFoundException: " + e.getMessage());
		}
	}

	/**************************************************************/
	/* Method: getSignature                                       */
	/* Purpose: Create a sorted-letter signature for a word       */
	/* Parameters:                                                */
	/*   String word: the original word                           */
	/* Returns: String - lowercase letters sorted alphabetically  */
	/**************************************************************/
	private String getSignature(String word) {
		// Use lowercase for case-insensitivity
		String lowercase = word.toLowerCase();

		// Convert to character array and sort letters
		char[] letters = lowercase.toCharArray();
		Arrays.sort(letters);

		// Return sorted letters as a string
		return new String(letters);
	}
}

package prog2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: lebuckman                                        */
/* CS 3310, Spring 2026                                       */
/* Programming Assignment 2                                   */
/* AnagramFinder class: Find anagram sets in a list of        */
/*                      words using presorting                */
/**************************************************************/
public class AnagramFinder {
	// Group words by their sorted-letter signature without duplicates
	private Map<String, Set<String>> anagramGroups;

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

				// Parse word to lowercase and remove non-letter characters for storage
				String parsedWord = parseWord(word);

				// Skip parsed word is empty
				if (parsedWord.isEmpty()) {
					continue;
				}

				// Get signature of word (sorted letters)
				String signature = getSignature(parsedWord);

				// Add parsed word to the appropriate anagram set
				anagramGroups.putIfAbsent(signature, new HashSet<>());
				anagramGroups.get(signature).add(parsedWord);
			}

			scanner.close();

			// Print anagram sets
			printAnagrams();

		} catch (FileNotFoundException e) {
			// Handle case where file is not found
			System.out.println("FileNotFoundException: " + e.getMessage());
		}
	}

	/**************************************************************/
	/* Method: parseWord                                          */
	/* Purpose: Parse word by removing non-letter characters      */
	/* Parameters:                                                */
	/*   String word: the original word                           */
	/* Returns: String - lowercase with non-letter                */
	/*                   characters removed                       */
	/**************************************************************/
	private String parseWord(String word) {
		// Convert to lowercase for case-insensitivity
		String lowercase = word.toLowerCase();

		// Remove non-letter characters (punctuation, spaces, numbers)
		return lowercase.replaceAll("[^\\p{L}]", "");
	}

	/**************************************************************/
	/* Method: getSignature                                       */
	/* Purpose: Create a sorted-letter signature for a word       */
	/* Parameters:                                                */
	/*   String word: the parsed word                             */
	/* Returns: String - letters sorted alphabetically            */
	/**************************************************************/
	private String getSignature(String word) {
		// Sort letters alphabetically
		char[] letters = word.toCharArray();
		Arrays.sort(letters);

		// Return sorted letters as a string
		return new String(letters);
	}

	/**************************************************************/
	/* Method: printAnagrams                                      */
	/* Purpose: Print all anagram groups found                    */
	/**************************************************************/
	private void printAnagrams() {
		System.out.println("Anagram Sets Found:\n");

		// Counter for numbering sets
		int setNumber = 1;

		// Iterate through all anagram sets
		for (Map.Entry<String, Set<String>> entry : anagramGroups.entrySet()) {
			Set<String> wordSet = entry.getValue();

			// Only print sets with 2 or more words (valid anagram sets)
			if (wordSet.size() >= 2) {
				// Convert set to list and sort alphabetically for display
				List<String> words = new ArrayList<>(wordSet);
				Collections.sort(words);

				// Print set with word count
				System.out.print("Set " + setNumber + " (" + words.size() + " words): ");

				// Print all words in this anagram set
				for (int i = 0; i < words.size(); i++) {
					System.out.print(words.get(i));
					if (i < words.size() - 1) {
						System.out.print(", ");
					}
				}

				System.out.println();
				setNumber++;
			}
		}

		// If no anagram sets found
		if (setNumber == 1) {
			System.out.println("No anagram sets found.");
		}
	}
}

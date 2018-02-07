package main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This AnagramFinder class is time efficient, not space efficient because this
 * class method parse whole dictionary in Hash Map.
 * 
 * @author Harsh Kevadia
 *
 */
public class ImprovedAnagramFinder {

	private static Scanner scan;

	public static String convertToBaseString(String str) {
		char[] stringCharArray = str.toLowerCase().toCharArray();
		Arrays.sort(stringCharArray);
		return new String(stringCharArray);
	}

	public static HashMap<String, ArrayList<String>> readDictionary(String file) {

		HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
		List<String> words = Collections.emptyList();

		try {
			words = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("There is some problem in reading File");
			// e.printStackTrace();
			System.exit(0);
		}
		for (String word : words) {
			if (dictionary.containsKey(convertToBaseString(word))) {
				ArrayList<String> anagrams = dictionary.get(convertToBaseString(word));
				anagrams.add(word);
				dictionary.put(convertToBaseString(word), anagrams);
			} else {
				ArrayList<String> anagrams = new ArrayList<String>();
				anagrams.add(word);
				dictionary.put(convertToBaseString(word), anagrams);
			}
		}
		return dictionary;
	}

	public static void main(String[] args) {

		// If there is not 1 argument then it is invalid
		if (args.length != 1) {
			System.out.println("This program requires only 1 argunment");
			System.exit(0);
		}

		// Loading Dictionary
		long dictionaryReadingStartTime = System.currentTimeMillis();
		HashMap<String, ArrayList<String>> dictionary = readDictionary(args[0]);
		long dictionaryReadingEndTime = System.currentTimeMillis();
		long dictionaryReadingTime = dictionaryReadingEndTime - dictionaryReadingStartTime;
		
		System.out.println("Welcome to the Anagram Finder");
		System.out.println("-----------------------------");
		System.out.println("Dictionary will be loaded in " + dictionaryReadingTime + " ms");

		scan = new Scanner(System.in);
		String input;

		// Continuous looping till Input is not exit.
		System.out.print("\n\nAnagramFinder>");
		while (scan.hasNextLine() && !((input = scan.nextLine()).equalsIgnoreCase("exit"))) {
			// Start Timer
			long startTime = System.currentTimeMillis();

			// Convert input string to lower case and sorted by character
			String comparableInput = convertToBaseString(input);

			int counter;
			String outputString = "";
			if (dictionary.containsKey(comparableInput)) {
				ArrayList<String> anagrams = dictionary.get(comparableInput);
				counter = anagrams.size();
				outputString = String.join(", ", anagrams);
			} else {
				counter = 0;
			}

			// End Timer
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;

			if (counter == 0) {
				System.out.println("No" + " anagrams found for " + input + " in " + totalTime + "ms");
			} else {
				System.out.println(counter + " Anagrams found for " + input + " in " + totalTime + "ms");
				System.out.println(outputString);
			}

			System.out.print("\n\nAnagramFinder>");
		}
	}

}

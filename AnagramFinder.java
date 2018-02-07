package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This AnagramFinder class is space efficient, not computing efficient because
 * this class method can not store whole dictionary in memory.
 *
 * @author Harsh Kevadia
 *
 */

public class AnagramFinder {

	private static Scanner scan;

	/**
	 * This method convert into lower case sorted string by character of String
	 * 
	 * @param str:
	 *            Input String
	 * @return: Lower case sorted String
	 */
	public static String convertToBaseString(String str) {
		char[] stringCharArray = str.toLowerCase().toCharArray();
		Arrays.sort(stringCharArray);
		return new String(stringCharArray);
	}

	public static void main(String[] args) {

		// If there is not 1 argument then it is invalid
		if (args.length != 1) {
			System.out.println("This program requires only 1 argunment");
			System.exit(0);
		}

		System.out.println("Welcome to the Anagram Finder");
		System.out.println("-----------------------------");
		System.out.println("Dictionary will be loaded Run-Time because this program is Space Complexity efficient");

		scan = new Scanner(System.in);
		String input;

		// Continuous looping till Input is not exit.
		System.out.print("\n\nAnagramFinder>");
		while (scan.hasNextLine() && !((input = scan.nextLine()).equalsIgnoreCase("exit"))) {
			// Start Timer
			long startTime = System.currentTimeMillis();

			// Convert input string to lower case and sorted by character
			String comparableInput = convertToBaseString(input);

			// Every time it reads the dictionary file for improve space complexity
			try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
				int counter = 0;
				String outputString = "";
				for (String word; (word = reader.readLine()) != null;) {
					String baseWord = convertToBaseString(word);
					if (comparableInput.equals(baseWord)) {
						if (counter != 0) {
							outputString += (", ");
							// System.out.print(", ");
						}
						counter++;
						outputString += word;
						// System.out.print(word);
					}
				}
				reader.close();

				// End Timer
				long endTime = System.currentTimeMillis();
				long totalTime = endTime - startTime;

				if (counter == 0) {
					System.out.println("No" + " anagrams found for " + input + " in " + totalTime + "ms");
				} else {
					System.out.println(counter + " Anagrams found for " + input + " in " + totalTime + "ms");
					System.out.println(outputString);
				}
			} catch (IOException e) {
				System.out.println("There is some problem in File");
				// e.printStackTrace();
				System.exit(0);
			}
			System.out.print("\n\nAnagramFinder>");
		}
	}
}

# Realtor-Test
Anagram Finder Programming Task

This project contains 2 java files:
1. AnagramFinder.java
2. ImprovedAnagramFinder.java

## AnagramFinder.java
This java program is efficient in terms of space complexity because this program is not store whole dictionary in to RAM.

Everytime it will check dictionary file while program find anagram, It has two major benifits:
- This program use less space in RAM
- This program never generate out of space error becuase of any size of dictionary

### How program works:
- Program convert input string to lower case character based sorted string, for example: Harsh will convert into ahhrs
- It read file line by line and each line is also converted into lower case character based sorted string then it will compairs with lower case character based sorted input string

## ImprovedAnagramFinder.java
This java program is efficient in terms of time complexity because this program is store whole dictionary in meaningful way using key-value pair data structure HashMap

Everytime it will not check dictionary file, but it will check HashMap based dictionary, It has one major benifits:
- This program is very efficient in terms of time complexity because to find value based on key takes O(1) time complexity because of Java Random Access Interface.

### How dictionary file converted into HashMap based dictionary
- Program real all contains of dictionary file and each line i.e word is convert into lower case character based sorted string (This will be Key of key-value pair).
- After that, It will checks with key of HashMap, if key is already exist then value of the dictionary HashMap ArrayList of String updated.
- If lower case character based sorted string is not exist in HashMap key then it will add into HashMap with word as an value of this HashMap.

`	public static HashMap<String, ArrayList<String>> readDictionary(String file) {

		HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
		List<String> words = Collections.emptyList();

		try {
			// Read all the contains of files using Java Neo Package
			words = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("There is some problem in reading File");
			// e.printStackTrace();
			System.exit(0);
		}
		for (String word : words) {
			// Check if base string of word is available in HashMap as a key or not
			if (dictionary.containsKey(convertToBaseString(word))) {
				// If it contains then update the ArrayList value of HashMap
				ArrayList<String> anagrams = dictionary.get(convertToBaseString(word));
				anagrams.add(word);
				dictionary.put(convertToBaseString(word), anagrams);
			} else {
				// If it not contains in the HashMap then it will create entry
				ArrayList<String> anagrams = new ArrayList<String>();
				anagrams.add(word);
				dictionary.put(convertToBaseString(word), anagrams);
			}
		}
		return dictionary;
	}`

### How program works:

- Program convert input string to lower case character based sorted string, for example: Harsh will convert into ahhrs
- After that, lower case character based sorted input string use as key of  dictionary HashMap
- If HashMap find the key then it will return the anagram word array list.

## Author

#### [Harsh Kevadia](https://www.linkedin.com/in/kevadiaharsh/)


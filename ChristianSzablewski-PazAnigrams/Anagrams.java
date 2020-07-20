import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	/**
	 * Constructs a letter table giving each letter a different prime number in the hashtable
	 */
	public void buildLetterTable() {
		Character alphabet[] = 
		{'a','b','c','d','e','f','g','h',
		'i','j','k','l','m','n','o','p','q',
		'r','s','t','u','v','w','x','y','z'};
	    letterTable = new HashMap<Character, Integer>();
	    for(int i = 0; i < alphabet.length; i++) {
	    	letterTable.put(alphabet[i], primes[i]);
	    }
	}
	
	/**
	 * initializes Anagrams object
	 */

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	/**
	 * adds s to table
	 * @param s - string to be added to table
	 */
	public void addWord(String s) {
		if (anagramTable.containsKey(myhashcode(s))) {
			ArrayList<String> addedElement = anagramTable.get(myhashcode(s));
			addedElement.add(s);
			anagramTable.replace(myhashcode(s), addedElement);
		} 
		else {
			ArrayList<String> initElement = new ArrayList<String>();
			initElement.add(s);
			anagramTable.put(myhashcode(s), initElement);
		}
	}
	
	/**
	 * makes hash code for s
	 * @param s - string code is being generated for
	 * @return hashcode
	 */
	public long myhashcode(String s) {
		Long product = 1L;
		for (Character letter : s.toCharArray()) {
			product *= (long)letterTable.get(letter);
		}
		return product;
	}
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	/**
	 * @return arrayList of words that have the highest number of anagrams in the hashtable.
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		int maxSize = 0;
		
		for(Map.Entry<Long,ArrayList<String>> entry: anagramTable.entrySet()) {
			if(entry.getValue().size() > maxSize) {
				maxSize = entry.getValue().size();
				maxEntries.clear();
				maxEntries.add(entry);
			} 
			else if(entry.getValue().size() == maxSize) {
				maxEntries.add(entry);
			}
		}
		
		return maxEntries;
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}

package main;
import java.util.*;

public class Main {
	private static List<String> words = new ArrayList<String>();
	
	//Behavior: The main method gets the user inputs to add words to the dictionary, before
	//			printing a sorted version
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		String get = "";
		while (!get.equals("execute")) {
			System.out.println("Gimme a word");
			get = kb.nextLine();
			if(get.equals("execute")) {
				break;
			}
			words.add(get);
		}
		List<String> packaged = packageWords(words);
		System.out.println(packaged);
		System.out.println(unpackageWords(packaged));
	}
	
	//Behavior: The packageWords method returns the packaged version of the List passed to
	//			it.
	//Exceptions: N/A
	//Returns: List<String> - The packaged List in the format <num same as previous>#<rest of word>
	//Parameters: List<String> - The unpackaged semi-alphabetical List of Strings
	public static List<String> packageWords(List<String> toPackage) {
		List<String> toReturn = new ArrayList<String>();
		String last = "";
		for(int i = 0; i < toPackage.size(); i++) {
			String toAdd = toPackage.get(i);
			int count = 0;
			for(int j = 0; j < toPackage.get(i).length(); j++) {
				if(last.length() < j + 1) {
					break;
				}
				if(toPackage.get(i).substring(0, j + 1).equals(last.substring(0, j + 1))) {
					count++;
				}
			}
			last = toAdd;
			if(count > 0) {
				toAdd = count + "#" + toAdd.substring(count);
			}
			toReturn.add(toAdd);
		}
		return toReturn;
	}
	
	//Behavior: The unpackageWords method returns the unpackaged version of a packaged List passed
	//			to it.
	//Exceptions: N/A
	//Returns: List<String> - The List of Strings that serves as the original dictionary list
	//Parameters: List<String> - The packaged version of the String list
	public static List<String> unpackageWords(List<String> toUnpackage) {
		List<String> toReturn = new ArrayList<String>();
		String last = "";
		for(int i = 0; i < toUnpackage.size(); i++) {
			String[] parts = toUnpackage.get(i).split("#");
			if(parts.length < 2) {
				last = toUnpackage.get(i);
				toReturn.add(toUnpackage.get(i));
				continue;
			}
			System.out.println(parts.length);
			String toAdd = last.substring(0, Integer.parseInt(parts[0])) + parts[1];
			last = toAdd;
			toReturn.add(toAdd);
		}
		return toReturn;
	}
}

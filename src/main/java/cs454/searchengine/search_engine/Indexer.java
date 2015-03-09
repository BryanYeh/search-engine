package cs454.searchengine.search_engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Indexer {
	
	/**
	 * Sources:
	 * http://stackoverflow.com/questions/7036332/jsoup-select-and-iterate-all-elements
	 * http://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
	 * http://stackoverflow.com/questions/4576352/java-remove-all-occurrences-of-char-from-string
	 * http://stackoverflow.com/questions/10661482/remove-a-specific-word-from-a-string
	 * 
	 */
	
	public static void index(Map<String, Map<String, Object>> linkInfo) {
		Set<String> linkValues = linkInfo.keySet();
		Set<String> listOfWords = new HashSet<String>();
		int wordOccurence = 0;
		Map<String, Map<String, Integer>> wordList = new HashMap<String, Map<String, Integer>>(); //word -> [doc:# occur, ...]
		Map<String, Integer> occurences = new HashMap<String, Integer>(); //doc:# occur
		
		for (String k : linkValues) {
			String bodyContent = linkInfo.get(k).get("body").toString();
			
			bodyContent = bodyContent.toLowerCase();
			bodyContent = bodyContent.replace('|', ' ');
			bodyContent = bodyContent.replace('.', ' ');
			
			String regex = "\\bis\\b";
			String regex2 = "\\ba\\b";
			String regex3 = "\\bthe\\b";
			bodyContent = bodyContent.replaceAll(regex, "");
			bodyContent = bodyContent.replaceAll(regex2, "");
			bodyContent = bodyContent.replaceAll(regex3, "");
			
			String[] splitBC = bodyContent.split("\\s+");
			
			for (String word : splitBC) {				
				listOfWords.add(word);
			}
		}

		//System.out.println(listOfWords.toString());
		
		for (String w : listOfWords) {
			//System.out.println(w);
			
			for (String k : linkValues) {
				//System.out.println(k + " -> " + linkInfo.get(k).get("body"));
				String bodyContent = linkInfo.get(k).get("body").toString();
				//System.out.println(bodyContent);
				
				bodyContent = bodyContent.toLowerCase();
				bodyContent = bodyContent.replace('|', ' ');
				bodyContent = bodyContent.replace('.', ' ');
				
				String regex = "\\bis\\b";
				String regex2 = "\\ba\\b";
				String regex3 = "\\bthe\\b";
				bodyContent = bodyContent.replaceAll(regex, "");
				bodyContent = bodyContent.replaceAll(regex2, "");
				bodyContent = bodyContent.replaceAll(regex3, "");
				
				String[] splitBC = bodyContent.split("\\s+");
				
				for (String word : splitBC) {				
					if (word.equals(w)) {
						wordOccurence++;
					}
				}
				//System.out.println(wordOccurence);
				
				if (wordOccurence != 0) {
					occurences.put(linkInfo.get(k).get("DocumentNumber").toString(), wordOccurence);
				}
//				System.out.println("ssssssssssssssssss");
//				System.out.println(occurences.toString());
//				System.out.println("sssssssssssssssssssss");
				wordOccurence = 0;
			}
			wordList.put(w, occurences);
			occurences = new HashMap<String, Integer>();
			wordOccurence = 0;
		}
//		System.out.println("iiiiiiiiiiiiiiiiiiiii");
//		System.out.println(wordList.toString());
//		System.out.println("iiiiiiiiiiiiiiiiiiii");
//		System.out.println();
		

		Set<String> wordO = wordList.keySet();
		
		for (String wd : wordO) {
			System.out.println(wd + " " + wordList.get(wd));
		}
	}
}

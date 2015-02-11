package cs454.searchengine.search_engine;

/**
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
 **/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class App2 {

	public static Set<String> links = new HashSet<String>();
	public static Queue<String> copylinks = new LinkedList<String>();
	public static int maxDepth = 2;
	public static String domain;
	public static String protocol;
	public static int counter;
	public static int depth = 0;
	public static int countNextDepth = 0;

	public static void main(String args[]) {

		Crawler crawler = new Crawler();
		Extractor extract = new Extractor();
		
//		maxDepth = Integer.parseInt(args[1]);
		ArrayList<String> currentLinks = crawler.crawl("http://www.calstatela.edu");
		counter+=currentLinks.size();
		countNextDepth = currentLinks.size();
		copylinks.addAll(currentLinks);
		depth++;
		
//		copylinks.addAll(crawler.crawl(args[0]));
		ArrayList<String> currentDepth = new ArrayList<String>();
		
		while (depth< maxDepth) {
			int linkCounter = 0;
			while(countNextDepth >0  && !copylinks.isEmpty()){
				String currentURL = copylinks.remove();
				if(!currentURL.isEmpty() & !links.contains(currentURL)){
					links.add(currentURL);
					currentLinks = crawler.crawl(currentURL);
				}
				linkCounter+=currentLinks.size();
//				extract.parseExample(currentURL);
				currentDepth.addAll(currentLinks);
			}
			copylinks.addAll(currentDepth);
			links.addAll(currentDepth);
			countNextDepth = linkCounter;
			depth++;
		}
		
		System.out.println("NUMBER OF LINKS: " + links.size());

		// for(int i=0;i<copylinks.size();i++){
		// System.out.println(copylinks.get(i));
		// }
		// depth++;
		// System.out.println("next depth: " + depth);
	}
}

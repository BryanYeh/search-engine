package cs454.searchengine.search_engine;

/**
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
 **/

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class App2 {

	public static void main(String args[]) {

		// Initialize Crawler & Extractor
		Crawler crawler = new Crawler();
		Extractor extr = new Extractor();
		Storage saving = new Storage();
		// Map<String, Map<String,Set<String>>> linkMap = new HashMap<String,
		// Map<String, Set<String>>>();

		// Initialize local variables
		Set<String> links = new HashSet<String>();
		Queue<String> linksQueue = new LinkedList<String>();
		int maxDepth = 2;
		int depth = 0;
		int countNextDepth;
		int countCurrDepth = 0;
		String currentURL = "http://www.calstatela.edu/ecst/cs/student-handbook"; // default URL
		Map<String, Set<String>> currentLinkMap = new HashMap<String, Set<String>>();

		// TESTING CRAWLER

		try {
			currentLinkMap = crawler.crawl(currentURL);
			links = currentLinkMap.get("images");
			
			for (String link: links){
				extr.downloadImageFile(link);
			}
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// TESTING EXTRACTOR
		try {
			extr.parseFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// //initial extraction
		// saving.store(obMap, currentURL);
		//
		// // maxDepth = Integer.parseInt(args[1]);
		// links.addAll(crawler.crawl(currentURL));
		// countNextDepth = links.size();
		// linksQueue.addAll(links);
		// depth++;
		//
		// Set<String> currentDepth = new HashSet<String>();
		//
		// while (depth < maxDepth) {
		// countCurrDepth = 0;
		// currentDepth.clear();
		//
		// while (countNextDepth > 0 && !linksQueue.isEmpty()) {
		// currentURL = linksQueue.remove();
		// if (!currentURL.isEmpty()) {
		// currentDepth.addAll(crawler.crawl(currentURL));
		// countNextDepth--;
		// countCurrDepth += currentDepth.size();
		// }
		//
		// System.out.println("ERROR URL: " + currentURL);
		// // CrawledLink currentLink = Extractor2.parseExample(currentURL);
		// Map<String, String> metadataMap = new HashMap<String, String>();
		// try {
		// metadataMap = extr.parseExample(currentURL);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }
		// linkMap.put(currentURL, metadataMap);
		// System.out.print(metadataMap.toString());
		//
		// //saving links to metadata
		// saving.store2(obMap, linkMap);
		// }
		// linksQueue.addAll(currentDepth);
		// links.addAll(currentDepth);
		// countNextDepth = countCurrDepth;
		// depth++;
		// }
		// System.out.println("NUMBER OF LINKS: " + links.size());
	}
}

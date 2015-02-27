package cs454.searchengine.search_engine;

/**
 * Sources:
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
 * http://www.urbanophile.com/arenn/hacking/getopt/gnu.getopt.Getopt.html#_top_
 * http://stackoverflow.com/questions/5585779/converting-string-to-int-in-java
 **/

import gnu.getopt.Getopt;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class App2 {
	static int depth = 0;
	static String url = null;

	public static void main(String args[]) {

		Getopt g = new Getopt("testprog", args, "d:u:");
		int c;
		String arg;
		
		while ((c = g.getopt()) != -1)
		{
			switch(c)
			{
			case 'd':
				arg = g.getOptarg();
				System.out.println("You picked " + (char)c + " with argument " + ((arg != null) ? arg : "null"));
				if (arg != null) depth = Integer.parseInt(arg);
				break;
			case 'u':
				arg = g.getOptarg();
				System.out.println("You picked " + (char)c + " with argument " + ((arg != null) ? arg : "null"));
				if(arg != null) url = arg;
				break;
			case '?':
				break; // getopt() already printed an error
			default:
				System.out.print("getopt() returned " + c + "\n");
			}
		}
		
		System.out.println("Inputted depth: " + depth);
		System.out.println("Inputted URL: " + url);
		
		
//		// Initialize Crawler & Extractor
//		Crawler crawler = new Crawler();
//		//Extractor extr = new Extractor();
//		Storage saving = new Storage();
//		Set<String> visitedLinks = new HashSet<String>();
//		Map<String, String> fileMap = new HashMap<String, String>();
//		Set<String> currentDepth = new HashSet<String>();
//
//		// Initialize local variables
//		Set<String> links = new HashSet<String>();
//		Set<String> links2 = new HashSet<String>();
//		Set<String> links3 = new HashSet<String>();
//
//		Queue<String> linksQueue = new LinkedList<String>();
//		int maxDepth = 2;
//		int depth = 0;
//		int countNextDepth = 0;
//		int countCurrDepth = 0;
//		String currentURL = "http://www.calstatela.edu/ecst/cs/student-handbook"; // default
//																					// URL
//		Map<String, Set<String>> currentLinkMap = new HashMap<String, Set<String>>();
//		Map<String, String> metaData = new HashMap<String, String>();
//		Map<String, Map<String, String>> linkMap = new HashMap<String, Map<String, String>>();
//
//		// INITIAL CRAWL
//
//		try {
//			currentLinkMap = crawler.crawl(currentURL);
//			links = currentLinkMap.get("images");
//			links2 = currentLinkMap.get("files");
//			links3 = currentLinkMap.get("links");
//			linksQueue.addAll(links3);
//			countNextDepth = links3.size();
//
//			System.out.println("--------------" + countNextDepth
//					+ "--------------");
//
//			depth++;
//
//			for (String link : links) {
//				String fileDir = extr.downloadFiles(link);
//				fileMap.put(link, fileDir);
//			}
//
//			for (String link : links2) {
//				String fileDir = extr.downloadFiles(link);
//				fileMap.put(link, fileDir);
//			}
//
//			visitedLinks.add(currentURL);
//			linkMap.put(currentURL, extr.parseExample(currentURL));
//			saving.store2(linkMap);
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//		// CRAWL LOOP
//
//		while (!linksQueue.isEmpty() && depth <= maxDepth) {
//
//			System.out
//					.println("--------------ENTERING CRAWL LOOP--------------");
//			System.out.println("--------------LINKS QUEUE SIZE: "
//					+ linksQueue.size());
//
//
//			while (countNextDepth > 0 && visitedLinks.size() < 10) {
//				currentURL = linksQueue.remove();
//				
//				System.out.println("--------------VISITED LINKS SIZE: "
//						+ visitedLinks.size());
//
//				if (!visitedLinks.contains(currentURL)) {
//					System.out.println("--------------NEXT URL: " + currentURL
//							+ "--------------");
//					System.out
//							.println("--------------PASSED TEST--------------");
//					try {
//						currentLinkMap = crawler.crawl(currentURL);
//						links = currentLinkMap.get("images");
//						links2 = currentLinkMap.get("files");
//						links3 = currentLinkMap.get("links");
//						linksQueue.addAll(links3);
//						countNextDepth--;
//						countCurrDepth += links3.size();
//						System.out.println("--------------COUNT CURR DEPTH: "
//								+ countCurrDepth + "--------------");
//						System.out.println("--------------COUNT NEXT DEPTH: "
//								+ countNextDepth + "--------------");
//						for (String link : links) {
//							String fileDir = extr.downloadFiles(link);
//							fileMap.put(link, fileDir);
//						}
//
//						for (String link : links2) {
//							String fileDir = extr.downloadFiles(link);
//							fileMap.put(link, fileDir);
//						}
//						visitedLinks.add(currentURL);
//						linkMap.put(currentURL, extr.parseExample(currentURL));
//						saving.store2(linkMap);
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
//
//				}
//			}
//
//			System.out.println("--------------I'M WORKING!!!!--------------");
//			countNextDepth = countCurrDepth;
//			depth++;
//
//		}
//
//		Iterator<Entry<String, String>> it = fileMap.entrySet().iterator();
//		while (it.hasNext()) {
//			@SuppressWarnings("rawtypes")
//			Map.Entry pair = (Map.Entry) it.next();
//			System.out.println(pair.getKey() + " = " + pair.getValue());
//			try {
//				linkMap.put((String) pair.getValue(),
//						extr.extractMeta((String) pair.getValue()));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("--------------------");
//			saving.store2(linkMap);
//			it.remove();
//		}
//
//		System.out.println("LINK MAP SIZE: " + linkMap.size());
//
//		// storage
//
//		saving.store2(linkMap);
//
	}

}
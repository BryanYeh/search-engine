package cs454.searchengine.search_engine;

/**
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
 **/

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;


public class App2 {

	public static void main(String args[]) {

		// Initialize Crawler & Extractor
		Crawler crawler = new Crawler();
		Storage saving = new Storage();
		Set<String> visitedLinks = new HashSet<String>();
		Set<CrawledLink> totalLinkSet = new HashSet<CrawledLink>();


		Queue<String> linksQueue = new LinkedList<String>();
		int maxDepth = 2;
		int depth = 0;
		int countNextDepth = 0;
		int countCurrDepth = 0;
		String currentURL = "http://www.cs.berkeley.edu/~russell/classes/cs188/f14/"; // default
																					// URL
		Set<Link> currentLinkSet = new HashSet<Link>();
		Map<String, String> metaData = new HashMap<String, String>();
		Map<String, Map<String, String>> linkMap = new HashMap<String, Map<String, String>>();

		// INITIAL CRAWL

		try {
			currentLinkSet = crawler.crawl(currentURL);
			for(Link l: currentLinkSet){
				linksQueue.add(l.getUrl());
			}
			
			countNextDepth = currentLinkSet.size();

			System.out.println("--------------" + countNextDepth
					+ "--------------");
			
			String[] fileInfo = saving.saveFiles(currentURL);
			totalLinkSet.add(new CrawledLink(currentURL, fileInfo[0], new Date().toString(), fileInfo[1], new HashSet<Link>(currentLinkSet)));

			visitedLinks.add(currentURL);
			saving.store2(totalLinkSet);
			System.out.println("TOTAL LINKS AFTER INITIAL CRAWL: " + totalLinkSet.size());

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// CRAWL LOOP

			

			while (countNextDepth > 0 && !linksQueue.isEmpty() && depth <= maxDepth && visitedLinks.size() < 200) {
				System.out.println("--------------ENTERING CRAWL LOOP--------------");
				
				currentURL = linksQueue.remove();
				
				System.out.println("--------------VISITED LINKS SIZE: "
						+ visitedLinks.size());

				if (!visitedLinks.contains(currentURL)) {
					System.out.println("--------------NEXT URL: " + currentURL
							+ "--------------");
					System.out
							.println("--------------PASSED TEST--------------");
					try {
						currentLinkSet.clear();
						currentLinkSet = crawler.crawl(currentURL);
						System.out.println("+++++++++++CURRENT LINK SET SIZE: " + currentLinkSet.size());
						for(Link l: currentLinkSet){
							linksQueue.add(l.getUrl());
						}
						
						String[] fileInfo = saving.saveFiles(currentURL);
						totalLinkSet.add(new CrawledLink(currentURL, fileInfo[0], new Date().toString(), fileInfo[1], new HashSet<Link>(currentLinkSet)) );
						
						countNextDepth--;
						countCurrDepth += currentLinkSet.size();
						System.out.println("--------------COUNT CURR DEPTH: "
								+ countCurrDepth + "--------------");
						System.out.println("--------------COUNT NEXT DEPTH: "
								+ countNextDepth + "--------------");
					
						
						visitedLinks.add(currentURL);
						System.out.println("TOTAL LINKS AFTER LOOP CRAWL: " + totalLinkSet.size());
//						saving.store2(linkMap);
						saving.store2(totalLinkSet);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					

				}
				
				if(countNextDepth == 0){
					countNextDepth = countCurrDepth;
					depth++;
					countCurrDepth = 0;
				}
			}
			
			
			System.out.println("TOTAL FILES: " + totalLinkSet.size());
			
			System.out.println("--------------LINKS QUEUE SIZE: "
					+ linksQueue.size());

			

			

			System.out.println("--------------I'M WORKING!!!!--------------");
			

		}
		

	}

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

import cs454.searchengine.extractor.Extractor;

public class App2 {

	public static void main(String args[]) {

		// Initialize Crawler & Extractor
		Crawler crawler = new Crawler();
		Extractor extr = new Extractor();
		Storage saving = new Storage();
		Set<String> visitedLinks = new HashSet<String>();
//		Map<String, String> fileMap = new HashMap<String, String>();
		Set<String> currentDepth = new HashSet<String>();
		Set<CrawledLink> totalLinkSet = new HashSet<CrawledLink>();
//		// Initialize local variables
//		Set<String> links = new HashSet<String>();
//		Set<String> links2 = new HashSet<String>();
//		Set<String> links3 = new HashSet<String>();

		Queue<String> linksQueue = new LinkedList<String>();
		int maxDepth = 2;
		int depth = 0;
		int countNextDepth = 0;
		int countCurrDepth = 0;
		String currentURL = "http://www.calstatela.edu/ecst/cs/student-handbook"; // default
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

			depth++;

			for (Link l : currentLinkSet) {
				String[] fileInfo = saving.saveFiles(l.getUrl());
				totalLinkSet.add(new CrawledLink(l.getUrl(),fileInfo[0], new Date().toString(), fileInfo[1], currentLinkSet) );
			}

//			for (String link : links2) {
//				String fileDir = extr.downloadFiles(link);
//				fileMap.put(link, fileDir);
//			}
//			
//			for (String link : links3) {
//				String fileDir = extr.downloadFiles(link);
//				fileMap.put(link, fileDir);
//			}

			visitedLinks.add(currentURL);
			linkMap.put(currentURL, extr.parseExample(currentURL));
			saving.store2(totalLinkSet);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// CRAWL LOOP

		while (!linksQueue.isEmpty() && depth <= maxDepth) {

			System.out
					.println("--------------ENTERING CRAWL LOOP--------------");
			System.out.println("--------------LINKS QUEUE SIZE: "
					+ linksQueue.size());


			while (countNextDepth > 0 && visitedLinks.size() < 10) {
				currentURL = linksQueue.remove();
				
				System.out.println("--------------VISITED LINKS SIZE: "
						+ visitedLinks.size());

				if (!visitedLinks.contains(currentURL)) {
					System.out.println("--------------NEXT URL: " + currentURL
							+ "--------------");
					System.out
							.println("--------------PASSED TEST--------------");
					try {
						currentLinkSet = crawler.crawl(currentURL);
						for(Link l: currentLinkSet){
							linksQueue.add(l.getUrl());
						}
						countNextDepth--;
						countCurrDepth += currentLinkSet.size();
						System.out.println("--------------COUNT CURR DEPTH: "
								+ countCurrDepth + "--------------");
						System.out.println("--------------COUNT NEXT DEPTH: "
								+ countNextDepth + "--------------");
						
//						for (String link : links3) {
//							String fileDir = extr.downloadFiles(link);
//							fileMap.put(link, fileDir);
//						}
//						
//						for (String link : links) {
//							String fileDir = extr.downloadFiles(link);
//							fileMap.put(link, fileDir);
//						}
//
//						for (String link : links2) {
//							String fileDir = extr.downloadFiles(link);
//							fileMap.put(link, fileDir);
//						}
						
						for (Link l : currentLinkSet) {
							String[] fileInfo = saving.saveFiles(l.getUrl());
							totalLinkSet.add(new CrawledLink(l.getUrl(),fileInfo[0], new Date().toString(), fileInfo[1], currentLinkSet) );
						}
					
						
						visitedLinks.add(currentURL);
//						saving.store2(linkMap);
						saving.store2(totalLinkSet);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}

			System.out.println("--------------I'M WORKING!!!!--------------");
			countNextDepth = countCurrDepth;
			depth++;

		}

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

		System.out.println("TOTAL FILES: " + totalLinkSet.size());

		// storage

		saving.store2(totalLinkSet);

	}

}
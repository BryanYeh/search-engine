package cs454.searchengine.search_engine;

/**
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
 **/

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.xml.sax.SAXException;


public class App2 {

	public static void main(String args[]) {

		// Initialize Crawler & Extractor
		Crawler crawler = new Crawler();
		Extractor extr = new Extractor();
		ObjectMapper obMap = new ObjectMapper();
		Map<String, Map<String,String>> linkMap = new HashMap<String, Map<String, String>>();

		// Initialize local variables
		Set<String> links = new HashSet<String>();
		Queue<String> linksQueue = new LinkedList<String>();
		int maxDepth = 2;
		int depth = 0;
		int countNextDepth;
		int countCurrDepth = 0;
		String currentURL = "http://espn.go.com";
		File jsonFile = new File("metadata.json");
		

		// initial extraction
		try {
			obMap.writeValue(jsonFile, extr.parseExample(currentURL));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		// maxDepth = Integer.parseInt(args[1]);
		links.addAll(crawler.crawl(currentURL));
		countNextDepth = links.size();
		;
		linksQueue.addAll(links);
		depth++;

		Set<String> currentDepth = new HashSet<String>();

		while (depth < maxDepth) {
			countCurrDepth = 0;
			currentDepth.clear();

			while (countNextDepth > 0 && !linksQueue.isEmpty()) {
				currentURL = linksQueue.remove();
				if (!currentURL.isEmpty()) {
					currentDepth.addAll(crawler.crawl(currentURL));
					countNextDepth--;
					countCurrDepth += currentDepth.size();
				}

					System.out.println("ERROR URL: " + currentURL);
//					CrawledLink currentLink = Extractor2.parseExample(currentURL);
					Map<String, String> metadataMap = new HashMap<String, String>();
					try {
						metadataMap = extr.parseExample(currentURL);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					linkMap.put(currentURL, metadataMap);
					System.out.print(metadataMap.toString());
					
					try {
						obMap.writeValue(jsonFile, linkMap);
					} catch (JsonGenerationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}

			linksQueue.addAll(currentDepth);
			links.addAll(currentDepth);
			countNextDepth = countCurrDepth;
			depth++;
		}

		System.out.println("NUMBER OF LINKS: " + links.size());

	}
}

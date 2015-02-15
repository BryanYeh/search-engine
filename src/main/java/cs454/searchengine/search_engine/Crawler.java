package cs454.searchengine.search_engine;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	Set<String> linkSet = new HashSet<String>();
	
	public Set<String> crawl(String urlString){
		Document doc;

		try {
			doc = Jsoup.connect(urlString).ignoreHttpErrors(true).get();
			Elements links = doc.select("a[href]");
	        
	        for(Element e: links){
	        	linkSet.add(e.attr("abs:href"));
	        }
	        System.out.println("NUMBER OF LINKS EXTRACTED: " + linkSet.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linkSet;
	}
}

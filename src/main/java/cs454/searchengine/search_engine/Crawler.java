package cs454.searchengine.search_engine;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	ArrayList<String> linkArray = new ArrayList<String>();
	
	public ArrayList<String> crawl(String urlString){
		
		Document doc;

		try {
			System.out.println(urlString);
			doc = Jsoup.connect(urlString).ignoreHttpErrors(true).get();
			Elements links = doc.select("a[href]");
	        

	        for(Element e: links){
	        	System.out.println(e.attr("abs:href"));
	        	linkArray.add(e.attr("abs:href"));
	        }
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return linkArray;
	
	}
}

package cs454.searchengine.search_engine;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	Set<String> linkSet = new HashSet<String>();
	Set<String> imageSet = new HashSet<String>();
	Set<String> fileSet = new HashSet<String>();
	
	public Map<String, Set<String>> crawl(String urlString){
		Document doc;

		try {
			doc = Jsoup.connect(urlString).ignoreHttpErrors(true).get();
			Elements links = doc.select("a[href]");
			Elements docFiles = doc.select("a[href~=(?i).(doc|ppt|pdf|xls|mp3|png|gif|bmp|tiff|jpg|jpeg|txt(?x)$)]");
			
			Elements files = doc.select("[src]");
	        
	        for(Element e: links){
	        	linkSet.add(e.attr("abs:href"));
	        }
	        
	        for(Element e: docFiles){
	        	fileSet.add(e.attr("abs:href"));
	        }
	        

	        for(Element e: files){
	        	if(e.tagName().equals("img")){
	        		imageSet.add(e.attr("abs:src"));
	        	}
	        }

	        System.out.println("NUMBER OF LINKS EXTRACTED: " + linkSet.size());
	        
	        
	        for(String link: linkSet){
	        	System.out.println("Normal Link: " +  link);
	        	
	        }
	        
	        for (String img: imageSet){
	        	System.out.println("Image Link: " + img);
	        }
	        
	        for (String file: fileSet){
	        	System.out.println("File Link: " + file);
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, Set<String>> linksMap = new HashMap<String, Set<String>>();
		linksMap.put("links", linkSet);
		linksMap.put("images", imageSet);
		linksMap.put("files", fileSet);
		return linksMap;
	}
	
	
	
	
	
	public static void main(String args[]){
		//new Crawler().crawl("https://www.washington.edu/doit/programs/accesscollege/faculty-room/resources/examples-powerpoint-presentations");
		new Crawler().crawl("http://www.calstatela.edu/ecst/cs/student-handbook");
		//new Crawler().crawl("http://www.noiseaddicts.com/free-samples-mp3/?id=280&desc=American Bison");

	}
}

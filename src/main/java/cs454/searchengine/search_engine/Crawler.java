package cs454.searchengine.search_engine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

public class Crawler {
	/**
	 * http://stackoverflow.com/questions/2056221/recursively-list-files-in-java
	 * http://stackoverflow.com/questions/1921181/java-arraylist-of-string-arrays
	 * http://stackoverflow.com/questions/7935613/adding-to-an-arraylist-java
	 * http://beginnersbook.com/2013/12/how-to-joincombine-two-arraylists-in-java/
	 * http://stackoverflow.com/questions/5287538/how-to-get-basic-user-input-for-java
	 * http://stackoverflow.com/questions/18281543/java-using-scanner-enter-key-pressed
	 */
	
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
	        	else{
	        		fileSet.add(e.attr("abs:src"));
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
	
    public void walk( String path, Extractor extr ) throws IOException, SAXException, TikaException {
        
    	File root = new File( path );
        File[] list = root.listFiles();
        
        if (list == null) {
        	System.out.println("There are no files, the path may not have been typed correctly, or this path does not exist.");
        	return;
        }
        
        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath(), extr );
                //System.out.println(f.getAbsolutePath());
            } else {
            	extr.localExtract(f);
            	System.out.println(f.getAbsolutePath());
            }
        }

    }
    
	public void crawlLocal(String path) {
		String startDir = path;
		Extractor extr = new Extractor();
    	try {
    		new localCrawler().walk(startDir, extr);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
	}
}

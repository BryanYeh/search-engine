package cs454.searchengine.search_engine;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.xml.sax.SAXException;

public class Extractor2 {
	
	public static void main(String args[]) {
		try {
			parseExample("http://www.google.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static CrawledLink parseExample(String url) throws IOException, SAXException, TikaException{

		URL inputURL = null;
		Map<String,String> metadataMap = new HashMap<String,String>();
		
		try {
			inputURL = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStream input = null;
		try {
			input = inputURL.openStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Parser parser = new HtmlParser();
		Metadata metaD = new Metadata();
		LinkContentHandler bodyCH = new LinkContentHandler();
		ParseContext context = new ParseContext();

		parser.parse(input, bodyCH, metaD, context);
		System.out.println("Number of Metadata Tags: " + metaD.size());
		
		Set<String> urlList = new HashSet<String>();
		List<Link> links = bodyCH.getLinks();
		
		System.out.println("SIZE OF LINKS: " + links.size());
		CharSequence questionMark = "?";
		for(Link l: links){
			System.out.println(l.getUri());
			if (l.getUri().contains(questionMark)){
				urlList.add(l.getUri().substring(0, l.getUri().lastIndexOf("?")));
			}
			else{
				urlList.add(l.getUri());
			}
				
		}
		
		
		
		for (String eachName: metaD.names()) {
			System.out.println(eachName + ": " + metaD.get(eachName));
			metadataMap.put(eachName, metaD.get(eachName));
			
//			System.out.println(metaD.get(Metadata.CONTENT_TYPE));
//			System.out.println(metaD.get(Metadata.CONTENT_DISPOSITION));
//			System.out.println(metaD.get(Metadata.CONTENT_ENCODING));
//			System.out.println(metaD.get(Metadata.CONTENT_LANGUAGE));
//			System.out.println(metaD.get(Metadata.CONTENT_LENGTH));
//			System.out.println(metaD.get(Metadata.CONTENT_LOCATION));
//			System.out.println(metaD.get(Metadata.CONTENT_MD5));
//			System.out.println(metaD.get(Metadata.LAST_MODIFIED));
//			System.out.println(metaD.get(Metadata.LOCATION));
			//System.out.println(metaD.get(eachName));
		}
		
		
		
		
		input.close();
		
		return new CrawledLink(metadataMap, urlList);
	}
	
	

	
}



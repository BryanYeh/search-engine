package cs454.searchengine.search_engine;

/**
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
 **/

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class App {
	
	public static ArrayList<String> links = new ArrayList<String>();
	public static ArrayList<String> copylinks = new ArrayList<String>();
	public static String domain;
	public static String protocol;
	public static int[] counter;
	public static int depth = 0;
	
	public static void main(String args[]){
		//http://stackoverflow.com/questions/6576855/java-how-to-access-methods-from-another-class
		//Extractor.parseExample();
		/**http://www.mkyong.com/java/how-to-get-http-response-header-in-java/**/
		counter = new int[3];
		crawl("http://www.google.com");
		
		while(copylinks.size()>0 && depth<3){
			crawl(copylinks.remove(0));
		}
		//System.out.println(links.size());
		
		for(int i=0;i<links.size();i++){
			System.out.println(i + ": " + links.get(i));
			Extractor.parseExample(links.get(i));
		}
	}
	
	public static void crawl(String urlString){
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(urlString.substring(urlString.length() - 1).equals("/")){
			urlString = urlString.substring(0,urlString.length() - 1);
		}
		links.add(urlString);
		protocol = url.getProtocol();
		domain = url.getProtocol() + "://" + url.getHost();
		
		Reader reader = null;
		try {
			reader = new InputStreamReader((InputStream) url.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			new ParserDelegator().parse(reader, new LinkPage(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//for(int i=0;i<copylinks.size();i++){
		//	System.out.println(copylinks.get(i));
		//} 	
		depth++;
		//System.out.println("next depth: " + depth);
	}
}

class LinkPage extends HTMLEditorKit.ParserCallback {

	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		if (t == HTML.Tag.A) {
			//System.out.println(a.getAttribute(HTML.Attribute.HREF));
			// href != null
			// href starts with #
			if (a.getAttribute(HTML.Attribute.HREF) != null && 
					!((String) a.getAttribute(HTML.Attribute.HREF)).startsWith("#") && 
					!((String) a.getAttribute(HTML.Attribute.HREF)).startsWith("javascript:void")){
				String newURL = ((String) a.getAttribute(HTML.Attribute.HREF));
				if(newURL.length()>0 && newURL.substring(newURL.length() - 1).equals("/")){
					newURL = newURL.substring(0,newURL.length() - 1);
				}
				
				// href = /
				if(newURL.equals("")){
					if(!App.links.contains(App.domain)){
						App.links.add(App.domain);
						App.copylinks.add(App.domain);
						//System.out.println("/: " + App.domain);
					}
				}
				// href = domain
				else if(newURL.equals(App.domain)){
					if(!App.links.contains(App.domain)){
						App.links.add(App.domain);
						App.copylinks.add(App.domain);
						//System.out.println("domain: " + App.domain);
					}
				}
				// href starts with /
				else if(newURL.startsWith("/")){
					String newLink = App.domain + newURL;
					if(!App.links.contains(newLink)){
						App.links.add(newLink);
						App.copylinks.add(newLink);
						//System.out.println("/start: " + newLink);
					}
				}
				// href starts with www.
				else if(newURL.startsWith("www.")){
					String newLink = App.protocol + newURL;
					if(!App.links.contains(newLink)){
						App.links.add(newLink);
						App.copylinks.add(newLink);
						//System.out.println("www: " + newLink);
					}
				}
				// href = https/http
				else if (newURL.startsWith("http")){
					if(!App.links.contains(newURL)){
						App.links.add(newURL);
						App.copylinks.add(newURL);
						//System.out.println("http: " + newURL);
					}
				}
				// href = example.html
				else{
					String newLink = App.domain + "/" + newURL;
					if(!App.links.contains(newLink)){
						App.links.add(newLink);
						App.copylinks.add(newLink);
						//System.out.println("example.html: " + newLink);
					}
				}
				
				
			}
				
			
		}
	}

}
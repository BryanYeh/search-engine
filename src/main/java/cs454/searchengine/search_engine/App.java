package cs454.searchengine.search_engine;

/**
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
 **/

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class App {
	
	public static ArrayList<String> links = new ArrayList<String>();
	public static String domain;
	public static String protocol;
	
	public static void main(String args[]) throws Exception {
		URL url = new URL(args[0]);
		protocol = url.getProtocol();
		domain = url.getProtocol() + "://" + url.getHost();
		System.out.println("domain: " + domain);
		Reader reader = new InputStreamReader((InputStream) url.getContent());
		new ParserDelegator().parse(reader, new LinkPage(), true);
	}
}

class LinkPage extends HTMLEditorKit.ParserCallback {

	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		if (t == HTML.Tag.A) {
			//System.out.println("ORIGINAL: " + a.getAttribute(HTML.Attribute.HREF));
			if (a.getAttribute(HTML.Attribute.HREF) != null && !((String) a.getAttribute(HTML.Attribute.HREF)).startsWith("#")){
				if(((String) a.getAttribute(HTML.Attribute.HREF)).startsWith(App.domain)){
					System.out.println("Unchanged: " + a.getAttribute(HTML.Attribute.HREF));
				}
				else if(((String) a.getAttribute(HTML.Attribute.HREF)).startsWith("/")){
					System.out.println("Added domain: " + App.domain + a.getAttribute(HTML.Attribute.HREF));
				}
				else if(((String) a.getAttribute(HTML.Attribute.HREF)).startsWith("http") && !((String) a.getAttribute(HTML.Attribute.HREF)).startsWith(App.domain)){
					System.out.println("Not in domain: " + a.getAttribute(HTML.Attribute.HREF));
				}
				else if(((String) a.getAttribute(HTML.Attribute.HREF)).startsWith("www.")){
					System.out.println("Added protocol: " + App.protocol + a.getAttribute(HTML.Attribute.HREF));
				}
				else{
					System.out.println("Added domain: " + App.domain + "/" + a.getAttribute(HTML.Attribute.HREF));
				}
				
				// there could be more to watch out for
			}
				
			
		}
	}

}
package cs454.searchengine.search_engine;

/**
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
 **/

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class App {
	public static void main(String args[]) throws Exception {
		URL url = new URL(args[0]);
		Reader reader = new InputStreamReader((InputStream) url.getContent());
		new ParserDelegator().parse(reader, new LinkPage(), true);
	}
}

class LinkPage extends HTMLEditorKit.ParserCallback {

	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		if (t == HTML.Tag.A) {
			if (!((String) a.getAttribute(HTML.Attribute.HREF)).startsWith("#"))
				System.out.println(a.getAttribute(HTML.Attribute.HREF));
		}
	}

}
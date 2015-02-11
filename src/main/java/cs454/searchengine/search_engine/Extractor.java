package cs454.searchengine.search_engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class Extractor {
	public static void parseExample(String urlString) throws IOException {
		/*
		 * Sources:
		 * http://chrisjordan.ca/post/15219674437/parsing-html-with-apache-tika
		 * Source: http://www.infoq.com/news/2011/12/tika-10 Source:
		 * http://www.hascode
		 * .com/2012/12/content-detection-metadata-and-content-
		 * extraction-with-apache-tika/#Tutorial_Sources
		 * http://www.tutorialspoint.com/tika/tika_metadata_extraction.htm
		 * http:/
		 * /stackoverflow.com/questions/6713927/extract-the-contenttext-of-
		 * a-url-using-tika
		 * http://www.ibm.com/developerworks/opensource/tutorials
		 * /os-apache-tika/
		 * http://stackoverflow.com/questions/5429814/how-can-i-
		 * use-the-html-parser-with-apache-tika-in-java-to-extract-all-html-tags
		 * http
		 * ://www.javaprogrammingforums.com/whats-wrong-my-code/34932-parse-any
		 * -file-using-auto-detect-parser-apache-tika-library.html
		 * http://www.javatpoint.com/jsoup-example-print-meta-data-of-an-url
		 * http://stackoverflow.com/questions/11656064/how-to-get-page-meta-title-description-images-like-facebook-attach-url-using
		 */
		

		Document doc = Jsoup.connect(urlString).ignoreHttpErrors(true).timeout(0).get();
		System.out.println("Title of URL: " + doc.title());
		for(Element meta : doc.select("meta")) {
		    System.out.println("Name: " + meta.attr("name") + " Property: " + meta.attr("property") + " - Content: " + meta.attr("content"));
		}
		
		System.out.println();
		
		Elements links = doc.select("a[href]");
		if (!links.isEmpty()) {
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				System.out.println("Absolute URL: " + link.absUrl("href"));
				System.out.println("URL of href:" + link.attr("abs:href"));
				System.out.println("Anchor Text: " + link.text());
				System.out.println();
			}
		}
		System.out.println("DONE WITH: " + urlString);
		System.out
				.println("------------------------------------------------------------------");
		System.out.println();
		
		

	}

}

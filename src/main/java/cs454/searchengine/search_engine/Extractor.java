package cs454.searchengine.search_engine;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class Extractor {
	public static void parseExample() throws IOException, SAXException, TikaException {
		/* Sources:
		 * http://chrisjordan.ca/post/15219674437/parsing-html-with-apache-tika
		 * Source: http://www.infoq.com/news/2011/12/tika-10
		 * Source: http://www.hascode.com/2012/12/content-detection-metadata-and-content-extraction-with-apache-tika/#Tutorial_Sources
		 * http://www.tutorialspoint.com/tika/tika_metadata_extraction.htm
		 * http://stackoverflow.com/questions/6713927/extract-the-contenttext-of-a-url-using-tika
		 * http://www.ibm.com/developerworks/opensource/tutorials/os-apache-tika/
		 * http://stackoverflow.com/questions/5429814/how-can-i-use-the-html-parser-with-apache-tika-in-java-to-extract-all-html-tags
		*/
		URL inputURL = new URL("http://www.yahoo.com");
		InputStream input = inputURL.openStream();
		HtmlParser parser = new HtmlParser();
		ParseContext parseC = new ParseContext();
		Metadata metaD = new Metadata();
		ContentHandler bodyCH = new BodyContentHandler();
		//TeeContentHandler teeCH = new TeeContentHandler();
		
		try {
		parser.parse(input, bodyCH, metaD, parseC);
		
		System.out.println("Number of Metadata: " + metaD.size());
		for (String eachName: metaD.names()) {
			System.out.println(eachName + ": " + metaD.get(eachName));
			//System.out.println(metaD.get(eachName));
		}
		
		input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

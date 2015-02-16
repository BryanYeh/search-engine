package cs454.searchengine.search_engine;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class Extractor {

	public static void main(String args[]) throws IOException, SAXException, TikaException {
		Extractor ex = new Extractor();
		
		ex.parseExample("http://www.google.com");

	}

	public Map<String,String> parseExample(String url) {
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
		 */
		URL inputURL = null;
		Map<String, String> metaDataMap = new HashMap<String, String>();

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
		ContentHandler bodyCH = new BodyContentHandler();
		// TeeContentHandler teeCH = new TeeContentHandler();

		try {
			try {
				parser.parse(input, bodyCH, metaD, new ParseContext());
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TikaException e) {
				e.printStackTrace();
			}

			System.out.println("Number of Metadata Tags: " + metaD.size());

			for (String eachName : metaD.names()) {
				System.out.println(eachName + ": " + metaD.get(eachName));
				metaDataMap.put(eachName, metaD.get(eachName));

				// System.out.println(metaD.get(Metadata.CONTENT_TYPE));
				// System.out.println(metaD.get(Metadata.CONTENT_DISPOSITION));
				// System.out.println(metaD.get(Metadata.CONTENT_ENCODING));
				// System.out.println(metaD.get(Metadata.CONTENT_LANGUAGE));
				// System.out.println(metaD.get(Metadata.CONTENT_LENGTH));
				// System.out.println(metaD.get(Metadata.CONTENT_LOCATION));
				// System.out.println(metaD.get(Metadata.CONTENT_MD5));
				// System.out.println(metaD.get(Metadata.LAST_MODIFIED));
				// System.out.println(metaD.get(Metadata.LOCATION));
				// System.out.println(metaD.get(eachName));
			}

			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return metaDataMap;
	}
}

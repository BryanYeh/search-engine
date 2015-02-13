package cs454.searchengine.search_engine;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class Extractor2 {
	
	public static void parse() throws IOException, SAXException, TikaException {
	     
	    URL inputURL = new URL("http://www.yahoo.com");
		InputStream input = inputURL.openStream();

		 // Tika parsing
		 Metadata metadata = new Metadata();
		 BodyContentHandler handler = new BodyContentHandler();
		 Parser parser = new AutoDetectParser();
		 parser.parse(input, handler, metadata, new ParseContext());
		 
		 try {
				
				System.out.println("Number of Metadata: " + metadata.size());
				for (String eachName: metadata.names()) {
						System.out.println(eachName + ": " + metadata.get(eachName));

				}
				
				input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	
	}
	
}

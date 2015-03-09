package cs454.searchengine.indexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/** Source
 * 
 * http://crunchify.com/how-to-read-json-object-from-file-in-java/
 * http://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library
 * http://jsoup.org/cookbook/input/load-document-from-file
 *
 */

public class IndexerApp {
	public static void main(String args[]) {
		String jsonFilePath = "C:\\Users\\tranw_000\\Desktop\\cs454SE\\data_extraction\\metadata2.json";
		
		JSONParser parser = new JSONParser();
        JSONArray jsonObject = new JSONArray();
        
        try {
			jsonObject =  (JSONArray) parser.parse(new FileReader(jsonFilePath));
			
			for (Object link : jsonObject) {
				JSONObject jsonO2 = (JSONObject) link;
				if (jsonO2.get("localPath").toString().contains(".html")) {
					System.out.println(jsonO2.get("linkURL").toString());
					System.out.println(jsonO2.get("localPath").toString());
					System.out.println();
					
					String localFilePath = jsonO2.get("localPath").toString();
					File linkHTMLfile = new File(localFilePath);
					Document doc = Jsoup.parse(linkHTMLfile, "UTF-8");
					
					System.out.println(doc.toString());
				}
			}
			
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		//Deleter.delLocalFolder(toDel);
	}
}

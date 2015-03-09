package cs454.searchengine.indexer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** Source
 * 
 * http://crunchify.com/how-to-read-json-object-from-file-in-java/
 * http://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library
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
				System.out.println(jsonO2.get("linkURL").toString());
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

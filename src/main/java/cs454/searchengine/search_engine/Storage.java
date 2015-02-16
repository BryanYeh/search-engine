package cs454.searchengine.search_engine;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Storage {
	File jsonFile = new File("metadata.json");
	
	public void store(ObjectMapper obMap, String currentURL) {
		Extractor extr = new Extractor();
		
		// initial extraction
		try {
			obMap.writeValue(jsonFile, extr.parseExample(currentURL));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	
	public void store2(ObjectMapper obMap, Map<String, Map<String,String>> linkMap) {
		try {
			obMap.writeValue(jsonFile, linkMap);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

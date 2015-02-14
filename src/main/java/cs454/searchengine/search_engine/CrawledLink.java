package cs454.searchengine.search_engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CrawledLink {
	private Map<String,String> metadata;
	private Set<String> listOfLinks;
	
	public CrawledLink(){
		metadata = new HashMap<String,String>();
		listOfLinks = new HashSet<String>();
	}
	
	public CrawledLink(Map<String,String> metadata, Set<String> urlList){
		this.metadata = metadata;
		this.listOfLinks = urlList;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public Set<String> getListOfLinks() {
		return listOfLinks;
	}

	public void setListOfLinks(Set<String> listOfLinks) {
		this.listOfLinks = listOfLinks;
	}
	
	
	
	

}

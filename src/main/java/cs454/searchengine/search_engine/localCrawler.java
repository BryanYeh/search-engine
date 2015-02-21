package cs454.searchengine.search_engine;

import java.io.File;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

public class localCrawler {
	/*
	 * Source:
	 * http://stackoverflow.com/questions/2056221/recursively-list-files-in-java
	 * http://stackoverflow.com/questions/1921181/java-arraylist-of-string-arrays
	 * http://stackoverflow.com/questions/7935613/adding-to-an-arraylist-java
	 * http://beginnersbook.com/2013/12/how-to-joincombine-two-arraylists-in-java/
	 * http://stackoverflow.com/questions/5287538/how-to-get-basic-user-input-for-java
	 * http://stackoverflow.com/questions/18281543/java-using-scanner-enter-key-pressed
	 */

    public void walk( String path, Extractor extr ) throws IOException, SAXException, TikaException {
        
    	File root = new File( path );
        File[] list = root.listFiles();
        
        if (list == null) {
        	System.out.println("There are no files, the path may not have been typed correctly, or this path does not exist.");
        	return;
        }
        
        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath(), extr );
                //System.out.println(f.getAbsolutePath());
            } else {
            	extr.localExtract(f);
            	System.out.println(f.getAbsolutePath());
            }
        }

    }
    
	public void crawlLocal(String path) {
		String startDir = path;
		Extractor extr = new Extractor();
    	try {
    		//String startDir = ".";
    		new localCrawler().walk(startDir, extr);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
	}
}

package cs454.searchengine.search_engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.UUID;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Storage {
	File jsonFile = new File("metadata.json");
	File directoryFile = new File("files");
	ObjectMapper obMap = new ObjectMapper();
	
	public Storage(){
		directoryFile.mkdir();
	}
	
	public void store2(Set<CrawledLink> linkMap) {
		try {
			System.out.println("Saving to JSON");
			obMap.writeValue(jsonFile, linkMap);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//http://www.programcreek.com/2012/12/download-image-from-url-in-java/
	
	public static void saveImage(String imageUrl) throws IOException {
		URL url = new URL(imageUrl);
		String fileName = url.getFile();
		String destName = "./figures" + fileName.substring(fileName.lastIndexOf("/"));
		System.out.println(destName);
	 
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destName);
	 
		byte[] b = new byte[2048];
		int length;
	 
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
	 
		is.close();
		os.close();
	}
	
	
//	public String downloadFiles(String fileUrl) throws IOException {
//		/**
//		 * Source:
//		 * http://stackoverflow.com/questions/17101276/java-download-all-files-and-folders-in-a-directory
//		 * http://stackoverflow.com/questions/3024002/how-to-create-a-folder-in-java
//		 * http://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path
//		 * http://www.java2s.com/Tutorial/Java/0180__File/Removefileordirectory.htm
//		 * http://stackoverflow.com/questions/4875064/jsoup-how-to-get-an-images-absolute-url
//		 * http://www.avajava.com/tutorials/lessons/how-do-i-save-an-image-from-a-url-to-a-file.html
//		 * http://stackoverflow.com/questions/3987921/not-able-to-delete-the-directory-through-java
//		 * 
//		 */
//
////		String[] folders = fileUrl.split("/");
////
////		try{
////			if(folder.mkdirs()) { 
////				System.out.println("Directory Created");
////			} else {
////				System.out.println("Directory exists");
////			}
////		} catch(Exception e){
////			e.printStackTrace();
////		}
//		
//		
//		String file = saveFiles(fileUrl);
//		
//		System.out.println("File Saved: " + file);
//		return file;
//	}

	public String[] saveFiles(String fileUrl){
		
		URL fileURL2;
		String folderName = "";
		File savedFile = null;
		String contentType = "";
		try {
			fileURL2 = new URL(fileUrl);
			contentType = getContentType(fileUrl);
			System.out.println("URL: " + fileUrl + " --------- Content-Type" + contentType);
			
			if(contentType.contains("text/html")){
				savedFile = new File(directoryFile, UUID.randomUUID().toString() + ".html");
				savedFile.createNewFile();
			}
			else{
				String fileName = "";
				if(fileUrl.contains(".docx"))
					fileName = UUID.randomUUID().toString() + ".docx";
				else if(fileUrl.contains(".doc"))
					fileName = UUID.randomUUID().toString() + ".doc";
				else if(fileUrl.contains(".xlsx"))
					fileName = UUID.randomUUID().toString() + ".xlsx";
				else if(fileUrl.contains(".xls"))
					fileName = UUID.randomUUID().toString() + ".xls";
				else if(fileUrl.contains(".pptx"))
					fileName = UUID.randomUUID().toString() + ".pptx";
				else if(fileUrl.contains(".ppt"))
					fileName = UUID.randomUUID().toString() + ".ppt";
				else if(fileUrl.contains(".pdf"))
					fileName = UUID.randomUUID().toString() + ".pdf";
				else if(fileUrl.contains(".mp3"))
					fileName = UUID.randomUUID().toString() + ".mp3";
				else if(fileUrl.contains(".jpg"))
					fileName = UUID.randomUUID().toString() + ".jpg";
				else if(fileUrl.contains(".png"))
					fileName = UUID.randomUUID().toString() + ".png";
				else if(fileUrl.contains(".bmp"))
					fileName = UUID.randomUUID().toString() + ".bmp";
				else if(fileUrl.contains(".jpeg"))
					fileName = UUID.randomUUID().toString() + ".jpeg";
				else if(fileUrl.contains(".txt"))
					fileName = UUID.randomUUID().toString() + ".txt";
				else if(fileUrl.contains(".tiff"))
					fileName = UUID.randomUUID().toString() + ".tiff";
				
				//doc|ppt|pdf|xls|mp3|png|gif|bmp|tiff|jpg|jpeg|txt
				
				
				savedFile = new File(directoryFile, fileName);
				savedFile.createNewFile();
			}
		
	    	InputStream is = fileURL2.openStream();
	    	
	    	folderName = savedFile.getAbsolutePath();
	    	System.out.println(folderName);
	    	OutputStream os = new FileOutputStream(savedFile);
	    	
			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String [] info = new String[3];
		info[0] = folderName;
		info[1] = contentType;
		
		
		return info;
	}
	
	
	public String getContentType (String urlString){
		URL url = null;
		String contentType = "";
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection)  url.openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			contentType = connection.getContentType();
			
			System.out.println(contentType);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contentType;
	}

}


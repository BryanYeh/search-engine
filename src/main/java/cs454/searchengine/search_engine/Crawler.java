package cs454.searchengine.search_engine;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	Set<Link> linkSet = new HashSet<Link>();

	public Set<Link> crawl(String urlString) {
		Document doc;

		try {
			doc = Jsoup.connect(urlString).ignoreHttpErrors(true).get();
			Elements links = doc.select("a[href]");
			Elements docFiles = doc
					.select("a[href~=(?i).(doc|ppt|pdf|xls|mp3|png|gif|bmp|tiff|jpg|jpeg|txt(?x)$)]");
			Elements files = doc.select("[src]");

			for (Element e : links) {
					linkSet.add(new Link(e.attr("abs:href"), e.text()));

				// linkSet.add(e.attr("abs:href"));
			}

			// for(Element e: docFiles){
			// linkSet.add(new Link(e.attr("abs:href"), e.text()));
			// // linkSet.add(e.attr("abs:href"));
			// }
			//

			for (Element e : files) {
				if (e.tagName().equals("img")) {
					linkSet.add(new Link(e.attr("abs:src"), e.text()));
					// linkSet.add(e.attr("abs:src"));
				}
			}

			System.out.println("NUMBER OF LINKS EXTRACTED1: " + linkSet.size());

			// for(String link, String text: linkSet){
			// System.out.println("Normal Link: " + link);
			//
			// }
			//
			// // for (String img: imageSet){
			// // System.out.println("Image Link: " + img);
			// // }
			// //
			// for (String file: fileSet){
			// System.out.println("File Link: " + file);
			// }

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("----------++++++Number of Links in link set: "
				+ linkSet.size() + "----------++++++");
		return linkSet;
	}

	public static void main(String args[]) {
		// new
		// Crawler().crawl("https://www.washington.edu/doit/programs/accesscollege/faculty-room/resources/examples-powerpoint-presentations");
//		new Crawler().crawl("http://www.calstatela.edu/ecst/cs/student-handbook");
//		String urlString = "http://www.calstatela.edu/sites/default/files/groups/Department%20of%20Computer%20Science/undergraduate_student_handbook_0.docx";
		String urlString = "https://www.washington.edu/doit/programs/accesscollege/faculty-room/resources/examples-powerpoint-presentations";

		
		
		
		
		
		// new
		// Crawler().crawl("http://www.noiseaddicts.com/free-samples-mp3/?id=280&desc=American Bison");

	}
}

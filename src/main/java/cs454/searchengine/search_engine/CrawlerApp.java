package cs454.searchengine.search_engine;

/**
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
 **/

import gnu.getopt.Getopt;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class CrawlerApp {

	public void startCrawler(int maxDepth, String currentURL) {
		// Initialize Crawler & Extractor
		Crawler crawler = new Crawler();
		Storage saving = new Storage();
		Set<String> visitedLinks = new HashSet<String>();
		Set<CrawledLink> totalLinkSet = new HashSet<CrawledLink>();

		Queue<String> linksQueue = new LinkedList<String>();
		int depth = 0;
		int countNextDepth = 0;
		int countCurrDepth = 0;
		Set<Link> currentLinkSet = new HashSet<Link>();

		// INITIAL CRAWL

		try {
			currentLinkSet = crawler.crawl(currentURL);
			for (Link l : currentLinkSet) {
				linksQueue.add(l.getUrl());
			}

			countNextDepth = currentLinkSet.size();

			System.out.println("--------------" + countNextDepth
					+ "--------------");

			String[] fileInfo = saving.saveFiles(currentURL);
			totalLinkSet.add(new CrawledLink(currentURL, fileInfo[0],
					new Date().toString(), fileInfo[1], new HashSet<Link>(
							currentLinkSet)));

			visitedLinks.add(currentURL);
			saving.store2(totalLinkSet);
			System.out.println("TOTAL LINKS AFTER INITIAL CRAWL: "
					+ totalLinkSet.size());

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// CRAWL LOOP

		while (countNextDepth > 0 && !linksQueue.isEmpty() && depth <= maxDepth
				&& visitedLinks.size() < 20000) {
			System.out
					.println("--------------ENTERING CRAWL LOOP--------------");

			currentURL = linksQueue.remove();
			String currentURLEdit = currentURL;

			if (currentURL.lastIndexOf("/") == currentURL.length() - 1
					|| currentURL.lastIndexOf("#") == currentURL.length() - 1) {
				currentURLEdit = StringUtils.stripEnd(currentURLEdit, "/");
				currentURLEdit = StringUtils.stripEnd(currentURLEdit, "#");

			}

			if (!visitedLinks.contains(currentURL)
					&& !visitedLinks.contains(currentURLEdit)) {
				System.out.println("--------------NEXT URL: " + currentURL
						+ "--------------");
				try {
					currentLinkSet.clear();
					if (saving.isURL(currentURL)) {
						currentLinkSet = crawler.crawl(currentURL);
						System.out.println("+++++++++++CURRENT LINK SET SIZE: "
								+ currentLinkSet.size());
						for (Link l : currentLinkSet) {
							linksQueue.add(l.getUrl());
						}
						String[] fileInfo = saving.saveFiles(currentURL);
						if (fileInfo != null) {
							totalLinkSet.add(new CrawledLink(currentURL,
									fileInfo[0], new Date().toString(),
									fileInfo[1], new HashSet<Link>(
											currentLinkSet)));
							visitedLinks.add(currentURL);
							System.out.println("TOTAL LINKS AFTER LOOP CRAWL: "
									+ totalLinkSet.size());
							saving.store2(totalLinkSet);
						}
					} else {
						String[] fileInfo = saving.saveFiles(currentURL);
						if (fileInfo != null) {
							totalLinkSet.add(new CrawledLink(currentURL,
									fileInfo[0], new Date().toString(),
									fileInfo[1], new HashSet<Link>()));
							visitedLinks.add(currentURL);
							System.out.println("TOTAL LINKS AFTER LOOP CRAWL: "
									+ totalLinkSet.size());
							saving.store2(totalLinkSet);
						}
					}

					countNextDepth--;
					countCurrDepth += currentLinkSet.size();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			else {
				countNextDepth--;
			}

			if (countNextDepth == 0) {
				countNextDepth = countCurrDepth;
				depth++;
				countCurrDepth = 0;
			}
			
			System.out.println("TOTAL FILES2fasdfafa: " + totalLinkSet.size());
			System.out.println("VISITED LINKSdafasfafa: " + visitedLinks.size());
		}

		System.out.println("--------------LINKS QUEUE SIZE: "
				+ linksQueue.size());
	}

	public static void main(String args[]) {

		Getopt g = new Getopt("testprog", args, "d:u:");
		int c;
		String arg;

		String url = "http://www.cs.berkeley.edu/~russell/classes/cs188/f14/"; // default
		// URL
		int depth = 2;

		// if(args.length == 4 && args[0].equals("-d") &&
		// Integer.parseInt(args[1]) > 0 && args[2].equals("-u")){
		// depth = Integer.parseInt(args[1]);
		// url = args[3];
		// }
		// else if(args.length == 4 && args[2].equals("-d") &&
		// Integer.parseInt(args[3]) > 0 && args[0].equals("-u")){
		// depth = Integer.parseInt(args[3]);
		// url = args[1];
		// }
		// else{
		// System.out.println("Usage: -d <depth> -u <url> ");
		// System.exit(0);
		// }

		while ((c = g.getopt()) != -1) {
			switch (c) {
			case 'd':
				arg = g.getOptarg();
				System.out.println("You picked " + (char) c + " with argument "
						+ ((arg != null) ? arg : "null"));
				if (arg != null)
					depth = Integer.parseInt(arg);
				break;
			case 'u':
				arg = g.getOptarg();
				System.out.println("You picked " + (char) c + " with argument "
						+ ((arg != null) ? arg : "null"));
				if (arg != null)
					url = arg;
				break;
			case '?':
				break; // getopt() already printed an error
			default:
				System.out.print("getopt() returned " + c + "\n");
			}
		}

		System.out.println("Inputted depth: " + depth);
		System.out.println("Inputted URL: " + url);

		new CrawlerApp().startCrawler(depth, url);

	}

}

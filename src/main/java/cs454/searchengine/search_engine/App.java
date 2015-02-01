package cs454.searchengine.search_engine;

/**
 * http://www.java2s.com/Tutorial/Java/0320__Network/Getallhyperlinksfromawebpage.htm
**/

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class App {
  public static void main(String args[]) throws Exception {
    URL url = new URL(args[0]);
    Reader reader = new InputStreamReader((InputStream) url.getContent());
    System.out.println("<HTML><HEAD><TITLE>Links for " + args[0] + "</TITLE>");
    System.out.println("<BASE HREF=\"" + args[0] + "\"></HEAD>");
    System.out.println("<BODY>");
    new ParserDelegator().parse(reader, new LinkPage(), true);
    System.out.println("</BODY></HTML>");
  }
}

class LinkPage extends HTMLEditorKit.ParserCallback {

  public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
    if (t == HTML.Tag.A) {
      System.out.println("<A HREF=\"" + a.getAttribute(HTML.Attribute.HREF) + "\">"
          + a.getAttribute(HTML.Attribute.HREF) + "</A><BR>");
    }
  }

}
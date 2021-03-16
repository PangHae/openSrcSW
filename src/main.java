import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;





public class main {


    public static void main(String[] args) throws ParserConfigurationException {
        makeXML mkX = new makeXML();

        mkX.html2XML();

    }

}

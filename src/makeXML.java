import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class makeXML {
    public void html2XML(int i, String text) throws ParserConfigurationException {

        fileWork fw = new fileWork();
        File[] htmls = fw.fileInFolder();

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = db.newDocument();
            Element doc = document.createElement("docs");
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

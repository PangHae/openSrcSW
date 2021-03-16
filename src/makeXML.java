import org.jsoup.select.Elements;
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
import java.io.FileOutputStream;

public class makeXML {

    public void html2XML() throws ParserConfigurationException {

        //System.out.println("XML파일을 작성합니다.");
        fileWork fw = new fileWork();
        File[] htmls = fw.fileInFolder();
        int numFile = htmls.length;

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = db.newDocument();
            Element docs = document.createElement("docs");
            document.appendChild(docs);
            for(int i = 0; i < numFile; i++){
                String num = Integer.toString(i);

                String [] strArray = fw.getTag(htmls[i]);
                String getTitle = strArray[0];
                String getP = strArray[1];

                /*System.out.println("title: " +  getTitle);
                System.out.println("p : "+ getP);*/

                Element doc = document.createElement("doc");
                Element title = document.createElement("title");
                Element body = document.createElement("body");
                docs.appendChild(doc);
                doc.setAttribute("id", num);
                doc.appendChild(title);
                title.appendChild(document.createTextNode(getTitle));
                doc.appendChild(body);
                body.appendChild(document.createTextNode(getP));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource src = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(new File("./sampleIR.xml")));

            transformer.transform(src, result);

            //System.out.println("XML파일 작성을 종료합니다.");

        }catch (Exception e){
            System.out.println(e);
        }

    }
}

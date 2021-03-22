import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class makeKeyword {

    public String [] parseXML(String fileRoute) throws IOException, SAXException, ParserConfigurationException {
        String [] getTags = new String[2];

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(fileRoute);

            Element doc = document.getDocumentElement();

            NodeList children = doc.getChildNodes();

            for(int i = 0; i < children.getLength(); i++){
                Node node = children.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element ele = (Element)node;
                    String nodeName = ele.getNodeName();
                    System.out.println("node name : " + nodeName);
                    if(nodeName.equals("p")){
                        getTags[0] = ele.getTextContent();
                    }else if(nodeName.equals("body")){
                        getTags[1] = ele.getTextContent();
                    }else{
                        continue;
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return getTags;
    }

    public void makeIndexXml() throws ParserConfigurationException, SAXException, IOException {

        String [] getTags = parseXML("./collection.xml");

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            org.w3c.dom.Document document = db.newDocument();
            Element docs = document.createElement("docs");
            document.appendChild(docs);
            for(int i = 0; i < numFile; i++){
                String num = Integer.toString(i);

                String [] strArray = getTag(htmls[i]);
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
            StreamResult result = new StreamResult(new FileOutputStream(new File("./index.xml")));

            transformer.transform(src, result);

            //System.out.println("XML파일 작성을 종료합니다.");

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public String useKkma(String text){

        String changedBody = "";

        KeywordExtractor ke = new KeywordExtractor();
        KeywordList kl = ke.extractKeyword(text, true);

        for(int i = 0; i < kl.size(); i++){
            Keyword kwrd = kl.get(i);
            if (i == kl.size() - 1){
                changedBody = changedBody + ":" + kwrd.getCnt() + "#";
            }else{
                changedBody = changedBody + ":" + kwrd.getCnt();
            }
        }
        return changedBody;
    }
}

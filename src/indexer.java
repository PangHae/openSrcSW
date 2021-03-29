import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

public class indexer {

    String xmlRoute;
    ArrayList <ArrayList<String>> finalNameData = new ArrayList<>();
    ArrayList <ArrayList<Integer>> finalNumData = new ArrayList<>();
    ArrayList <String> keyword = new ArrayList<>();
    ArrayList <Integer> tf = new ArrayList<>();

    public indexer(String xmlRoute){
        this.xmlRoute = xmlRoute;
    }

    public String[][] getTagsFromXml(){
        String [][] idAndTags = new String[5][2];
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(this.xmlRoute);

            Element doc = document.getDocumentElement();

            NodeList children = doc.getChildNodes();
            int docId = 0;
            for(int i = 0; i < children.getLength(); i++){
                Node node = children.item(i);
                NodeList docChild = node.getChildNodes();

                for(int j = 0; j < docChild.getLength(); j++){
                    Node docChildNode = docChild.item(j);
                    if(docChildNode.getNodeType() == Node.ELEMENT_NODE){
                        Element ele = (Element)docChildNode;
                        String nodeName = ele.getNodeName();
                        //System.out.println("node name : " + nodeName);
                        if(nodeName.equals("body")){
                            idAndTags[docId][0] = Integer.toString(docId);
                            idAndTags[docId][1] = ele.getTextContent();
                            docId++;
                            //System.out.println(ele.getTextContent());
                        }else{
                            continue;
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return idAndTags;
    }

    public void cutBody(){
        String [][] matrix = getTagsFromXml();
        String [][] cutStr = new String[5][];

        String [] dataSave = new String[2];

        for(int i = 0; i < matrix.length; i++){
                cutStr[i] = matrix[i][1].split("#");
        }

        for(int i = 0; i < cutStr.length; i++){
            ArrayList <String> dataName = new ArrayList();
            ArrayList <Integer> dataNum = new ArrayList();
            for(int j = 0; j < cutStr[i].length; j++){
                dataSave = cutStr[i][j].split(":");
                dataName.add(dataSave[0]);
                dataNum.add(Integer.parseInt(dataSave[1]));
                if(!this.keyword.contains(dataSave[0])){
                    this.keyword.add(dataSave[0]);
                }
            }
            this.finalNameData.add(dataName);
            this.finalNumData.add(dataNum);
        }
    }

    public void calculate4HashMap(){
        cutBody();


    }

    public void input2HashMap(){

    }
}

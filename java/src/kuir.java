import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class kuir {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        String option = args[0];

        if(option.equals("-c")){
            makeCollection mkC = new makeCollection(args[1]);
            mkC.html2XML();
        }else if(option.equals("-k")){
            String xmlRoute = "./data/" + args[1];
            makeKeyword mkK = new makeKeyword(xmlRoute);
            mkK.makeIndexXml();
        }else if(option.equals("-i")){
            String xmlRoute = "./data/" + args[1];
            indexer ix = new indexer(xmlRoute);
            ix.input2HashMap();//파일 생성
            //ix.printIndex("./data/index.post");//파일 출력

        }else if(option.equals("-s")){
            String postRoute = "./data/" + args[1];
            if(args[2].equals("-q")){
                String query = args[3];
                searcher sr = new searcher(postRoute, query);
                sr.checkRank();
            }else{
                System.out.println("Query doesn't exist.");
            }
        }

    }

}

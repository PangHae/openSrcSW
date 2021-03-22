import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class kuir {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String option = args[0];

        if(option.equals("-c")){
            makeCollection mkC = new makeCollection(args[1]);
            mkC.html2XML();
        }else if(option.equals("-k")){
            makeKeyword mkK = new makeKeyword(args[1]);
            //mkK.parseXML();
            mkK.makeIndexXml();
        }

    }

}

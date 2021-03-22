import javax.xml.parsers.ParserConfigurationException;

public class kuir {

    public static void main(String[] args) throws ParserConfigurationException {
        //String htmlPath= args[0];

        makeCollection mkC = new makeCollection();
        mkC.html2XML();

    }

}

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

public class fileWork {

    public File[] fileInFolder(){
        File dir = new File("./2주차 실습 html");
        File files[] = dir.listFiles();

        /*for(int i = 0; i < files.length; i++){
            System.out.println("file : " + files[i]);
        }*/
        return files;
    }

    public String[] getTag(File fileName) throws IOException {

        String [] strArr = new String[2];

        try{
                Document doc = Jsoup.parse(fileName, "UTF-8");
                Elements p = doc.select("p");
                Elements title = doc.select("title");
                String strP = p.text();
                String strTitle = title.text();
                strArr[0] = strTitle;
                strArr[1] = strP;

        }catch(Exception e){
            System.out.println(e);
        }
        return strArr;
    }


}

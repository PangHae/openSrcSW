import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class fileWork {

    public File[] fileInFolder(){
        File dir = new File("./2주차 실습 html");
        File files[] = dir.listFiles();

        for(int i = 0; i < files.length; i++){
            System.out.println("file : " + files[i]);
        }
        return files;
    }

    public void giveTag(File fileName) throws IOException {

        try{
                Document doc = Jsoup.parse(fileName, "UTF-8");
                Elements p = doc.select("p");
                Elements title = doc.select("title");

                String text = p.text();

        }catch(Exception e){
            System.out.println(e);
        }
    }


}

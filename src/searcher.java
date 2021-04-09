import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class searcher {
    String route;
    String query;
    String [] getTitle = new String[5];

    public searcher(String route, String query) throws ParserConfigurationException, SAXException, IOException {
        this.route = route;
        this.query = query;
        makeKeyword mk = new makeKeyword("./data/collection.xml");
        for(int i = 0; i < mk.parseXML().length; i++){
            this.getTitle[i] = mk.parseXML()[i][0];
        }
    }

    public HashMap getHashMap() throws IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream(this.route);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);

        Object object = objectInputStream.readObject();
        objectInputStream.close();

        HashMap hashMap = (HashMap)object;

        return hashMap;

    }

    public void printHashMap() throws IOException, ClassNotFoundException {
        HashMap hashMap = getHashMap();
        Iterator<String> it = hashMap.keySet().iterator();

        while(it.hasNext()){
            String key = it.next();
            ArrayList value = (ArrayList) hashMap.get(key);
            System.out.println(key + " → " + value);
        }
    }


    public ArrayList<String> calcSim() throws IOException, ClassNotFoundException {
        
    }

    public void checkRank() throws IOException, ClassNotFoundException {
        ArrayList <String> result = calcSim();
        String [] ranking = new String[3];
        ranking[2] = " ";
        double num = 0;
        int index = 0;
        int k = 0;
        while(ranking[2].equals(" ")){
            for(int i = 0; i < result.size(); i++){
                if(num < Double.parseDouble(result.get(i))){
                    index = i;
                    num = Double.parseDouble(result.get(i));
                }
            }
            ranking[k] = this.getTitle[index];
            result.set(index, "-1");
            //System.out.println(result);
            for(int i = 0; i < result.size(); i++){
                if(Double.parseDouble(result.get(i)) == 0){
                    index = i;
                    break;
                }else{
                    continue;
                }
            }
            k++;
            num = 0;
        }
        for(int j = 0; j < ranking.length; j++){
            System.out.println((j+1) +"등 : " + ranking[j]);
        }
    }
}
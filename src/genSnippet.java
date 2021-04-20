import java.util.ArrayList;
import java.util.Scanner;

public class genSnippet {
    String fileName;
    String query;
    ArrayList <ArrayList<String>> getFile = new ArrayList();

    public genSnippet(String fileName, String query){
        this.fileName = "./data/"+ fileName;
        this.query = query;
    }

    public void getTxt(){
        Scanner sc = new Scanner(this.fileName);
        String body;

        while(sc.hasNext()){
            body = sc.nextLine();
            String [] cutBody;
            ArrayList <String> inner = new ArrayList<>();
            cutBody = body.split(" ");
            for(int i = 0; i < cutBody.length; i++){
                inner.add(cutBody[i]);
            }
            this.getFile.add(inner);
        }
    }

    public int [] countAmount(){
        getTxt();
        int [] count = {0,0,0,0,0};
        String [] cutQuery;
        cutQuery = query.split(" ");
        int j = 0;
        for(int i = 0;  i < cutQuery.length; i++){
            if(getFile.get(j).contains(cutQuery[i])){
                count[i]++;
            }
            if(j == 5){
                break;
            }
            j++;
        }
        return count;
    }

    public void printResult(){
        int [] result = countAmount();
        int index = 0;

        for(int i = 0; i < result.length; i++){
            if(result[i] > result[index]){
                index = i;
            }
        }
        System.out.println(getFile.get(index));
    }
}
// 쿼리를 얻어서 자른 후, count 수로 비교해 count가 가장 높은 인덱스의 arrayList를 출력하고자 하였다.
// 인덱스 outofbound 오류로 실행은 불가능

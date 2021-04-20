public class midterm {
    public static void main(String [] args){
        String option = args[0];
        String fileName = args[1];
        String secondOption = args[2];
        String keyword = args[3];

        if(option.equals("-f")){
            if(secondOption.equals("-q")){
                genSnippet gs = new genSnippet(fileName, keyword);
                gs.printResult();
            }
        }
    }
}

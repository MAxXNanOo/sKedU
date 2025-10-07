import Subject.*;

public class Main {
    public static void main(String[] args) {
        Data data = new Data("D:\\sKedU\\KUdata\\KUKPSForTest.csv");
        data.setSubjects();

        data.displayDataById("01423345-65");
        // System.out.println(data.getDataById("01423345-65"));
    }
}



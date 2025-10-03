package Ex;


import java.io.*;



public class ReadCSV {
    public static void main(String[] args) {
        String csvFile = "D:\\WORK\\sKedUTest\\KUdata\\copyKUKPS.csv"; // เปลี่ยนเป็น path ของคุณ
        String line;
        String csvSplitBy = ",";
        int index;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(csvFile), "UTF-8"))) {

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                index = -1;
                for (String value : values) {
                    index++;
                    if (index == 0 && value.length() < 1)
                        System.out.print(">>>>>");
                    System.out.print(value + " | ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


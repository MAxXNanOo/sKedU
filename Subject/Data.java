package Subject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Data {
    private String csvPath;
    private ArrayList<Subject> subjects;

    public Data(String csvPath){
        this.csvPath = csvPath;
        this.subjects = new ArrayList<>();
    }


    
    //Set

    //Jang 5/10
    public void setSubjects(){
        
        //อันนี้คือตัวอย่างการ สร้างวิชาเเละเเอด Lecture เเละ Lab เข้าไปในวิชานั้น 
        // Subject s1 = new Subject("01423345-65", "Data_I", 3);
        // CourseComponent lec1 = new CourseComponent(2, 700, "วันอังคาร 10:30-12:00,วันพฤหัสบดี", "LH2-201 , LH2-201", "E29,E34", 50, "WaranYa haha, bunyaratddddddddddddddd");
        // s1.addLecture(lec1);
        // CourseComponent lab = new CourseComponent(1, 711, "วันพฤหัสบดี 16:00-18:00", "E8404", "E29", 60, "Bunyarat");
        // s1.addLab(lab);
        // CourseComponent CourseComponent = new CourseComponent(1, 712, "วันศุกร์ 16:00-18:00", "E8404", "E29", 60, "Bunyarat");
        // s1.addLab(lab);
        // subjects.add(s1);

        Subject subject = null;

        String id = null;
        String name = null;
        int totalCredit;

        int credit;
        int sec;
        String dayTime;
        String room;
        String major;
        int numAddmission;



        String row;
        int index=-1;
        String pattern = "^(\\d{8}-\\d{2})(.*)$";

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvPath),"UTF-8"))){

            while((row = br.readLine()) != null){
                String[] values = row.split(",");
                for(String value : values){
                    if(value.matches("\\d{8}-\\d{2}.*")){
                        index = 0;
                        System.out.println(value);
                        id = value.replace(pattern, "$1");
                        name = value.replace(pattern, "$1");
                    }
                    else if(value.equals("")){
                        System.out.printf("-");
                    }


                    // else if(index%15==1){
                    //     totalCredit = Integer.parseInt(value);

                    //     subject = new Subject(id, name, totalCredit);
                    //     subjects.add(subject);
                    // }
                    // else if(index%15==2){
                    //     credit = Integer.parseInt(value);
                    // }
                    // else if(index%15==3){
                    //     sec = Integer.parseInt(value);
                    // }
                    // else if(index%15==4){
                    //     dayTime = value;
                    // }
                    // else if(index%15==5){
                    //     room = value;
                    // }
                    // else if(index%15==6){
                    //     major = value;
                    // }


                    index++;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }









    //Get

    //Wa 5/10
    public void displayDataById(String id){

    }

    public ArrayList<Subject> getSubjects(String id) {
        return subjects;
    }


}


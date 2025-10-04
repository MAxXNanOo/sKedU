package Subject;

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
        Subject s1 = new Subject("01423345-65", "Data_I", 3);
        CourseComponent lec1 = new CourseComponent(2, 700, "วันอังคาร 10:30-12:00,วันพฤหัสบดี", "LH2-201 , LH2-201", "E29,E34", 50, "WaranYa haha, bunyaratddddddddddddddd");
        s1.addLecture(lec1);
        CourseComponent lab = new CourseComponent(1, 711, "วันพฤหัสบดี 16:00-18:00", "E8404", "E29", 60, "Bunyarat");
        s1.addLab(lab);
        CourseComponent CourseComponent = new CourseComponent(1, 712, "วันศุกร์ 16:00-18:00", "E8404", "E29", 60, "Bunyarat");
        s1.addLab(lab);
        subjects.add(s1);
    }









    //Get

    //Wa 5/10
    public void displayDataById(String id){

    }

    public ArrayList<Subject> getSubjects(String id) {
        return subjects;
    }


}


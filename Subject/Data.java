package Subject;

import java.util.ArrayList;

public class Data {
    private String csvPath;
    private ArrayList<Subject> subjects;

    public Data(String csvPath){
        this.csvPath = csvPath;
        this.subjects = new ArrayList<>();
    }


    //Jang
    //Set

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

    public void displayDataById(String id){
        for (Subject s : subjects){
            if(s.getId().equals(id)){
                System.out.printf("รหัสวิชา: %s | ชื่อวิชา: %s | หน่วยกิตรวม: %d\n", s.getId(), s.getName(), s.getTotalCredit());
                for(CourseComponent Lecture : s.getAllLecture()){
                    System.out.printf("     รหัสวิชา: %s | ชื่อวิชา: %s | หน่วยกิต: %d | หมู่เรียน: %d | วัน-เวลา: %s | ห้อง: %s | คณะที่เรียนได้: %s | รับ: %d | อาจารย์ผู้สอน: %s  \n",
                                            s.getId(), s.getName(), Lecture.getCredit(), Lecture.getSection(), Lecture.getDayTimes()[0], Lecture.getRooms()[0], Lecture.getMajors()[0],Lecture.getMaxStudent(), Lecture.getTeacherName()[0]);
                }
                System.out.println();
                for(CourseComponent Lab : s.getAllLab()){
                    System.out.printf("     รหัสวิชา: %s | ชื่อวิชา: %s | หน่วยกิต: %d | หมู่เรียน: %d | วัน-เวลา: %s | ห้อง: %s | คณะที่เรียนได้: %s | รับ: %d | อาจารย์ผู้สอน: %s  \n",
                            s.getId(), s.getName(), Lab.getCredit(), Lab.getSection(), Lab.getDayTimes()[0], Lab.getRooms()[0], Lab.getMajors()[0],Lab.getMaxStudent(), Lab.getTeacherName()[0]);
                }
            }
        }
    }

    public String getSubjectById(String id) {
        for (Subject s : subjects) {
            if (id.equals(s.getId())) {
                return "รหัสวิชา: " + s.getId() + " | ชื่อวิชา: " + s.getName() + " | หน่วยกิต: " + s.getTotalCredit();
            }
        }
        String str = new String ( "ไม่พบข้อมูลของวิชา ");

        return str + id;
    }


}


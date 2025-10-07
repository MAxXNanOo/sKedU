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
        Subject s2 = new Subject("01130171-64", "การบัญชีการเงิน", 3);
        CourseComponent lec2 = new CourseComponent(3, 700, "วันอังคาร 10:30-12:00,วันพฤหัสบดี 10:30-12:00", "LH2-201 , LH2-201 ", "Q20,Q34", 100, "Jee, hong");
        s2.addLecture(lec2);
        CourseComponent lec3 = new CourseComponent(3, 701, "วันพุธ 10:30-12:00,วันศุกร์ 10:30-12:00", "LH2-203 , LH2-212 ", "Q60,Q44", 100, "Jee, hong");
        s2.addLecture(lec3);
        CourseComponent lec4 = new CourseComponent(3, 702, "วันอังคาร 13:00-15:00,วันศุกร์ 13:00-15:00", "LH2-205 , LH2-212 ", "Q70,Q54", 100, "Jee, hong");
        s2.addLecture(lec4);
        CourseComponent lab2 = new CourseComponent(1, 706, "วันศุกร์ 9:00-12:00 ","LH2-205 , LH2-212 ", "Q70,Q54", 100, "Jee, hong");
        s2.addLab(lab2);
        subjects.add(s2);
    }









    //Get

    //Wa 5/10
    public void displayDataById(String id){
        for (Subject item : subjects) {
            if(item.getId().equals(id)){
                System.out.printf("รหัสวิชา : %s ชื่อวิชา: %s\n",item.getId(),item.getName());
                int totalLabCredit = 0;
                int totalLectureCredit = 0; 
                if (!item.getAllLecture().isEmpty()) {
                    CourseComponent lec = item.getAllLecture().get(0);
                    totalLectureCredit = lec.getCredit();
                }
                if (!item.getAllLab().isEmpty()) {
                    CourseComponent lab = item.getAllLab().get(0);
                    totalLabCredit = lab.getCredit();
                }
                System.out.printf("หน่วยกิตรวม(แลป+บรรยาย): %d (บรรยาย: %d, แลป: %d)\n",
                totalLectureCredit+totalLabCredit, totalLectureCredit, totalLabCredit);
                System.out.printf("จำนวนหมู่บรรยายที่เปิดให้ลงทะเบียน: %d จำนวนแลปที่เปิดให้ลงทะเบียน: %d\n", item.getAllLecture().size(), item.getAllLab().size());
                if (!item.getAllLecture().isEmpty()){
                System.out.printf("เซคเรียน(บรรยาย):\n");
                    for(CourseComponent lec : item.getAllLecture()){
                        System.out.printf("%s ",lec.getSection());
                        System.out.printf(String.join(",",lec.getDayTimes()));
                        System.out.printf(" ห้องเรียน: "+String.join(",",lec.getRooms()));
                        System.out.printf(" อาจารย์ผู้สอน: "+String.join("",lec.getTeacherNames()));
                        System.out.printf(" คณะที่ลงทะเบียนได้ : "+String.join(" ",lec.getMajors()));
                        System.out.printf(" จำนวนที่รับ: "+lec.getMaxStudent());
                        System.out.printf("\n");
                    }
                }
                else
                    System.out.printf("\nรายวิชานี้ไม่มีหมู่บรรยายเปิดให้ลงทีเบียน\n");
                if (!item.getAllLab().isEmpty()){
                System.out.printf("เซคเรียน(แลป):\n");
                    for(CourseComponent lab : item.getAllLab()){
                        System.out.printf("%s ",lab.getSection());
                        System.out.printf(String.join(", ", lab.getDayTimes()));
                        System.out.printf(" ห้องเรียน: "+String.join(",",lab.getRooms()));
                        System.out.printf(" อาจารย์ผู้สอน: "+String.join("",lab.getTeacherNames()));
                        System.out.printf(" คณะที่ลงทะเบียนได้ : "+String.join(" ",lab.getMajors()));
                        System.out.printf(" จำนวนที่รับ: "+lab.getMaxStudent());
                        System.out.printf("\n");
                    }
                }
                else
                    System.out.printf("รายวิชานี้ไม่มีหมู่แลปเปิดให้ลงทะเบียน\n");
            }
        }

    }

    public ArrayList<Subject> getSubjects(String id) {
        return subjects;
    }


}


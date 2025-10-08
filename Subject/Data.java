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

        int credit = 0;
        int sec = 0;
        String dayTime = null;
        String room = null;
        String major = null;
        int maxStudent = 0;
        String teacherName = null;


        String row;
        int index=-1;
        String pattern = "^(\\d{8}-\\d{2})(.*)$";
        int lecORLab;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvPath),"UTF-8"))){

            while((row = br.readLine()) != null){
                String[] values = row.split(",");
                lecORLab=-1;
                for(String value : values){
                    if(value.matches("\\d{8}-\\d{2}.*")){
                        index = 0;
                        System.out.println(value);
                        id = value.replace(pattern, "$1");
                        name = value.replace(pattern, "$1");
                    }
                    else if(index%15==1){
                        totalCredit = Integer.parseInt(value);

                        subject = new Subject(id, name, totalCredit);
                        subjects.add(subject);
                    }               
                    else if(value.equals("") && index!= 0 && index!=1){
                        // -_-
                    }
                    else if(index%15==2 || index%15==8){
                        credit = Integer.parseInt(value);
                        if(index == 2)
                            lecORLab = 0;
                        else
                            lecORLab = 1;
                    }
                    else if(index%15==3 || index%15==9){
                        sec = Integer.parseInt(value);
                    }
                    else if(index%15==4 || index%15==10){
                        dayTime = value;
                    }
                    else if(index%15==5 || index%15==11){
                        room = value;
                    }
                    else if(index%15==6 || index%15==12){
                        major = value;
                    }
                    else if(index%15==7 || index%15==13){
                        maxStudent = Integer.parseInt(value);
                    }
                    else if(index%15==14){
                        teacherName = value;

                        if(lecORLab == 0){
                            subject.addLecture(new CourseComponent(credit, sec, dayTime, room, major, maxStudent, teacherName));
                        }
                        else if(lecORLab == 1){
                            subject.addLab(new CourseComponent(credit, sec, dayTime, room, major, maxStudent, teacherName));
                        }
                    }




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


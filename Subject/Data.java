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
                ArrayList<String> values = splitCSVLine(row);
                if (values.size() < 15) continue;

            
                if (values.get(0).matches("\\d{8}-\\d{2}.*")) {
                    id = values.get(0).replaceAll(pattern, "$1");
                    name = values.get(0).replaceAll(pattern, "$2").trim();
                    subject = new Subject(id, name);
                    subjects.add(subject);
                }
            
                if (!values.get(2).equals("") && values.get(2).matches("[0-9]")) {
                    totalCredit = Integer.parseInt(values.get(1));
                    credit = Integer.parseInt(values.get(2));
                    sec = Integer.parseInt(values.get(3));
                    dayTime = values.get(4);
                    room = values.get(5);
                    major = values.get(6);
                    maxStudent = Integer.parseInt(values.get(7));
                    teacherName = values.get(14);

                    subject.setTotalCredit(totalCredit);
                    subject.addLecture(new CourseComponent(credit, sec, dayTime, room, major, maxStudent, teacherName));
                }
                else if (!values.get(8).equals("") && values.get(8).matches("[0-9]")) {
                    credit = Integer.parseInt(values.get(8));
                    sec = Integer.parseInt(values.get(9));
                    dayTime = values.get(10);
                    room = values.get(11);
                    major = values.get(12);
                    maxStudent = Integer.parseInt(values.get(13));
                    teacherName = values.get(14);

                    subject.addLab(new CourseComponent(credit, sec, dayTime, room, major, maxStudent, teacherName));
                }
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    //chat GPT
    public static ArrayList<String> splitCSVLine(String line) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (ch == '"') {
                inQuotes = !inQuotes; // toggle quote state
            } else if (ch == ',' && !inQuotes) {
                result.add(current.toString().trim());
                current.setLength(0); // reset buffer
            } else {
                current.append(ch);
            }
        }

        result.add(current.toString().trim()); // add last item
        return result;
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

    public void displayAll(){
        for(Subject sub : subjects){
            System.out.printf("\n%s %s\n",sub.getId(), sub.getName());

            for(CourseComponent lec : sub.getAllLecture()){
                System.out.printf("    %d %d %d %s %s %s %d %s\n", sub.getTotalCredit(), lec.getCredit(), lec.getSection(), lec.getDayTimes(), lec.getRooms(), lec.getMajors(), lec.getMaxStudent(), lec.getTeacherNames());
            }
        }
    }


}


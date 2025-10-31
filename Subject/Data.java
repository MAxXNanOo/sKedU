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

    // Safe integer parsing
    private int safeParseInt(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    // Find a subject by its ID
    private Subject findSubjectById(String id) {
        for (Subject s : subjects) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    // Read CSV
    public void setSubjects() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(csvPath), "UTF-8"))) {

            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                // Skip header rows if needed
                if (lineNumber <= 7) continue;

                // Split CSV safely (handle commas inside quotes)
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (data.length < 15 || data[0].trim().isEmpty()) continue;

                // Remove quotes and trim
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replaceAll("^\"|\"$", "").trim();
                }

                // Parse Subject info
                String firstColumn = data[0];
                String id, name;
                int spaceIndex = firstColumn.indexOf(' ');
                if (spaceIndex > 0) {
                    id = firstColumn.substring(0, spaceIndex).trim();
                    name = firstColumn.substring(spaceIndex + 1).trim();
                } else {
                    id = firstColumn;
                    name = "";
                }

                int totalCredit = safeParseInt(data[1]);

                Subject subject = findSubjectById(id);
                if (subject == null) {
                    subject = new Subject(id, name, totalCredit);
                    subjects.add(subject);
                }

                // Teacher name (column 15)
                String teacherName = data[14];

                // ----- Lecture -----
                int lectureCredit = safeParseInt(data[2]);
                int lectureSec = safeParseInt(data[3]);
                String lectureDayTimes = data[4];
                String lectureRoom = data[5];
                String lectureMajors = data[6];
                int lectureMaxStudent = safeParseInt(data[7]);

                if (lectureCredit > 0) {
                    CourseComponent lecture = new CourseComponent(
                            lectureCredit, lectureSec, lectureDayTimes, lectureRoom,
                            lectureMajors, lectureMaxStudent, teacherName
                    );
                    subject.addLecture(lecture);
                }

                // ----- Lab -----
                int labCredit = safeParseInt(data[8]);
                int labSec = safeParseInt(data[9]);
                String labDayTimes = data[10];
                String labRoom = data[11];
                String labMajors = data[12];
                int labMaxStudent = safeParseInt(data[13]);

                if (labCredit > 0) {
                    CourseComponent lab = new CourseComponent(
                            labCredit, labSec, labDayTimes, labRoom,
                            labMajors, labMaxStudent, teacherName
                    );
                    subject.addLab(lab);
                }
            }
        } catch (IOException e) {
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

    public void displayAll(){
        for(Subject sub : subjects){
            System.out.printf("\n%s %s\n",sub.getId(), sub.getName());

            for(CourseComponent lec : sub.getAllLecture()){
                System.out.printf("    %d %d %d %s %s %s %d %s\n", sub.getTotalCredit(), lec.getCredit(), lec.getSection(), lec.getDayTimes(), lec.getRooms(), lec.getMajors(), lec.getMaxStudent(), lec.getTeacherNames());
            }
        }
    }


}


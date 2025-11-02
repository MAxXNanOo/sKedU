package Subject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;

public class StudentData {
    private String csvFile;
    private Data data;
    private ArrayList<Student> students = new ArrayList<>();

    private Student student;
    private Student studentTmp;



    public StudentData(String csvFile, Data data){
        this.csvFile = csvFile;
        this.data = data;
    }


    public Student getStudentLogin(){
        return this.student;
    }
    public Student getStudentTmp(){
        return this.studentTmp;
    }
    






    public void setStudents() {
        String line;
        String csvSplitBy = ",";
        int index;

        try (BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(csvFile), "UTF-8"))) {

            while ((line = br.readLine()) != null) {
                ArrayList<String> values = splitCSVLine(line);
                
                if(values.get(0).equals("user")) {
                    continue;
                }
                else{
                    Student student = new Student(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
                    students.add(student);
                    
                    if(values.get(5).equals("Subject")){
                        index = 6;
                        while (index < values.size() && 
                            (values.get(index).startsWith("Lec") || values.get(index).startsWith("Lab"))) {

                            String[] courseInfo = values.get(index).split(" ");
                            String courseType = courseInfo[0]; // Lec or Lab
                            String courseId = courseInfo[1];
                            int section = Integer.parseInt(courseInfo[2]);

                            addSubjectToStudentLogin(student, courseType, courseId, section);

                            index++;
                            if (index < values.size() && values.get(index).equals("Detail")) {
                                index++;
                                break;
                            }
                        }

                        while(index < values.size()){
                            student.addDetail(values.get(index));
                            index++;
                        }
                    }



                    // some body helpME
                }
            }

        } catch (IOException e) {
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

    public boolean addDetailToStudentTmp(String detail){
        if(this.studentTmp != null){
            this.studentTmp.addDetail(detail);
            return true;
        }
        return false;
    }


    public boolean deleteSubjectFromStudentTmp(String courseType, String courseId, int section){
        if(courseType.equals("Lec")){
            for(Subject sub : studentTmp.getSubjects()){ 
                if(sub.getId().equals(courseId)){
                    for(CourseComponent lec : sub.getAllLecture()){
                        if(lec.getSection() == section){
                            sub.getAllLecture().remove(lec);
                            return true;
                        }
                    }
                }
            }
        }
        else if(courseType.equals("Lab")){
            for(Subject sub : studentTmp.getSubjects()){ 
                if(sub.getId().equals(courseId)){
                    for(CourseComponent lab : sub.getAllLab()){
                        if(lab.getSection() == section){
                            sub.getAllLab().remove(lab);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // public void addSubjectToStudentLogin(String type, Subject subject, int section) {
    //     studentTmp.addSubject(subject);
    // }


    //return 0 = fail
    //return 1 = success
    //return 2 = time conflict
    //return 3 = already added
    public int addSubjectToStudentLogin(Student student, String courseType, String courseId, int section) {
        int found = 0;
        if(student == null) return 0; 
        else {
            for(Subject subject : student.getSubjects()){
                if(subject.getId().equals(courseId)){
                    found = 1;
                    break;
                }
            }
        }

        if(found == 0){
            Subject subject = new Subject(courseId,
                data.findSubjectById(courseId).getName(),
                data.findSubjectById(courseId).getTotalCredit());
            student.addSubject(subject);
        }
        else{
            for(Subject subject : student.getSubjects()){
                if(subject.getId().equals(courseId)){
                    if(courseType.equals("Lec")){
                        if(subject.getAllLecture() != null){
                            System.out.printf("Lecture already added for subject %s\n", courseId);
                            return 3; // already added
                        }
                        // CourseComponent lecture = data.getCourseForStudent(courseType, courseId, section);
                        // if(lecture != null) subject.addLecture(lecture);
                    }
                    else if(courseType.equals("Lab")){
                        if(subject.getAllLab().size() > 0){
                            System.out.printf("Lab already added for subject %s\n", courseId);
                            for(CourseComponent lab : subject.getAllLab()){
                                System.out.printf("Existing lab section: %d\n", lab.getSection());
                            }
                            return 3;
                        }
                    }
                    break;
                }
            }
        }




                int index = 0;
        for(Subject subject : student.getSubjects()){
            if(subject.getId().equals(courseId)){
                if(courseType.equals("Lec")){
                    CourseComponent lecture = data.getCourseForStudent(courseType, courseId, section);
                    index = 0;
                    for(String dayTime : lecture.getDayTimes()){
                        String day = lecture.getDayTimes().get(index);
                        double start = lecture.getStarts().get(index);
                        double end = lecture.getEnds().get(index);

                        for(Subject sub : student.getSubjects()){
                            if(sub.getId().equals(courseId)){
                                for(CourseComponent lec : sub.getAllLecture()){
                                    for(int i = 0; i < lec.getDayTimes().size(); i++){
                                        String existingDay = lec.getDays().get(i);
                                        double existingStart = lec.getStarts().get(i);
                                        double existingEnd = lec.getEnds().get(i);

                                        if(day.equals(existingDay)){
                                            if((start >= existingStart && start < existingEnd) ||
                                               (end > existingStart && end <= existingEnd) ||
                                               (start <= existingStart && end >= existingEnd)){
                                                System.out.printf("Time conflict detected for student %s on %s %f-%f with existing %f-%f\n",
                                                    student.getStudentName(), day, start, end, existingStart, existingEnd);
                                                return 2;
                                            }
                                        }
                                    }
                                }
                                for(CourseComponent lab : sub.getAllLab()){
                                    for(int i = 0; i < lab.getDayTimes().size(); i++){
                                        String existingDay = lab.getDays().get(i);
                                        double existingStart = lab.getStarts().get(i);
                                        double existingEnd = lab.getEnds().get(i);

                                        if(day.equals(existingDay)){
                                            if((start >= existingStart && start < existingEnd) ||
                                               (end > existingStart && end <= existingEnd) ||
                                               (start <= existingStart && end >= existingEnd)){
                                                System.out.printf("Time conflict detected for student %s on %s %f-%f with existing %f-%f\n",
                                                    student.getStudentName(), day, start, end, existingStart, existingEnd);
                                                return 2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(lecture != null) subject.addLecture(lecture);
                }
                else if(courseType.equals("Lab")){
                    // System.out.printf("Adding Lab %s %d for student %s\n", courseId, section, student.getStudentName());
                    CourseComponent lab = data.getCourseForStudent(courseType, courseId, section);

                    for(Subject sub : student.getSubjects()){
                        if(sub.getId().equals(courseId)){
                            for(CourseComponent lec : sub.getAllLecture()){
                                for(int i = 0; i < lec.getDayTimes().size(); i++){
                                    String day = lec.getDays().get(i);
                                    double start = lec.getStarts().get(i);
                                    double end = lec.getEnds().get(i);

                                    for(int j = 0; j < lab.getDayTimes().size(); j++){
                                        String labDay = lab.getDays().get(j);
                                        double labStart = lab.getStarts().get(j);
                                        double labEnd = lab.getEnds().get(j);

                                        if(day.equals(labDay)){
                                            if((labStart >= start && labStart < end) ||
                                               (labEnd > start && labEnd <= end) ||
                                               (labStart <= start && labEnd >= end)){
                                                System.out.printf("Time conflict detected for student %s on %s %f-%f with existing %f-%f\n",
                                                    student.getStudentName(), day, labStart, labEnd, start, end);
                                                return 2;
                                            }
                                        }
                                    }
                                    for(CourseComponent existingLab : sub.getAllLab()){
                                        for(int j = 0; j < existingLab.getDayTimes().size(); j++){
                                            String existingDay = existingLab.getDays().get(j);
                                            double existingStart = existingLab.getStarts().get(j);
                                            double existingEnd = existingLab.getEnds().get(j);

                                            if(day.equals(existingDay)){
                                                if((start >= existingStart && start < existingEnd) ||
                                                   (end > existingStart && end <= existingEnd) ||
                                                   (start <= existingStart && end >= existingEnd)){
                                                    System.out.printf("Time conflict detected for student %s on %s %f-%f with existing %f-%f\n",
                                                        student.getStudentName(), day, start, end, existingStart, existingEnd);
                                                    return 2;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(lab != null) subject.addLab(lab);
                }
                break;
            }
        }
        return 1;
    }

    public boolean checkTimeConflict(Student student, String courseType, String courseId, int section){
        
        return false; // Placeholder
    }
    



    public boolean validateLogin(String username, String password) {
        for (Student st : students) {
            if (st.getUsername().equals(username) && st.getPassword().equals(password)) {
                this.student = st;
                this.studentTmp = new Student(st); // copy constructor

                return true;
            }
        }
        return false;
    }

    public void studentConfirm() {
        // deleteSubjectFromStudentTmp("Lab", "02204221-65", 712);
        // addSubjectToStudentLogin(studentTmp, "Lab", "02204221-65", 712);
        // อัพเดต student ตัวจริงให้ตรงกับ temp ก่อน
        this.student = this.studentTmp;

        try {


            // 1. อ่านข้อมูลทั้งหมดจากไฟล์
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();

            // 2. หา index ของบรรทัดที่เป็น student ปัจจุบัน
            int indexToUpdate = -1;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith(student.getUsername() + ",")) {
                    indexToUpdate = i;
                    break;
                }
            }

            if (indexToUpdate == -1) {
                System.out.println("ไม่พบข้อมูลนักศึกษาในไฟล์ CSV: " + student.getUsername());
                return;
            }

            // 3. สร้างบรรทัดใหม่ของ student
            StringBuilder newLine = new StringBuilder();
            newLine.append(student.getUsername()).append(",");
            newLine.append(student.getPassword()).append(",");
            newLine.append(student.getId()).append(",");
            newLine.append(student.getStudentName()).append(",");
            newLine.append(student.getMajor()).append(",");

            // เพิ่มส่วน Subject
            newLine.append("Subject").append(",");
            for (Subject sub : student.getSubjects()) {
                for (CourseComponent lec : sub.getAllLecture()) {
                    newLine.append("Lec ").append(sub.getId()).append(" ")
                        .append(lec.getSection()).append(",");
                }
                for (CourseComponent lab : sub.getAllLab()) {
                    newLine.append("Lab ").append(sub.getId()).append(" ")
                        .append(lab.getSection()).append(",");
                }
            }

            // เพิ่มส่วน Detail (ถ้ามี)
            if (student.getDetails() != null && !student.getDetails().isEmpty()) {
                newLine.append("Detail").append(",");
                for (String detail : student.getDetails()) {
                    newLine.append(detail).append(",");
                }
            }


            // 4. แทนที่บรรทัดเก่าใน list ด้วยบรรทัดใหม่
            lines.set(indexToUpdate, newLine.toString());

            // 5. เขียนกลับลงไฟล์
            java.io.BufferedWriter bw = new java.io.BufferedWriter(
                    new java.io.OutputStreamWriter(new java.io.FileOutputStream(csvFile), "UTF-8"));
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            bw.close();

            System.out.println("อัพเดตข้อมูลนักศึกษาเรียบร้อยแล้วในไฟล์ CSV");

        } catch (IOException e) {
            e.printStackTrace();
        }


}



    
}
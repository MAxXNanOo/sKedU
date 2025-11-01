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


    public StudentData(String csvFile, Data data){
        this.csvFile = csvFile;
        this.data = data;
    }


    public Student getStudentLogin(){
        return this.student;
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
                        while(index < values.size() && values.get(index).startsWith("Lec") || values.get(index).startsWith("Lap")){
                            // System.out.println(values.get(index));

                            String[] courseInfo = values.get(index).split(" ");
                            String courseType = courseInfo[0]; // Lec or Lap
                            String courseId = courseInfo[1]; // e.g., 01131111-67
                            int section = Integer.parseInt(courseInfo[2]); // e.g., 700

                            boolean found = false;
                            if(student == null) break; // safety check
                            else{
                                for(Subject subject : student.getSubjects()){
                                    if(subject.getId().equals(courseId)){
                                        found = true;
                                        break;
                                    }
                                }
                            }

                            if(!found){
                                // System.out.printf("Course : %s\nsubject name: %s\nsubject total: %d", courseId, data.findSubjectById(courseId).getName(), data.findSubjectById(courseId).getTotalCredit());
                                // System.out.println("Adding subject:" + courseId + "-");
                                Subject subject = new Subject(courseId, data.findSubjectById(courseId).getName(), data.findSubjectById(courseId).getTotalCredit());

                                student.addSubject(subject);
                            }

                            for(Subject subject : student.getSubjects()){
                                if(subject.getId().equals(courseId)){
                                    if(courseType.equals("Lec")){
                                        CourseComponent lecture = data.getCourseForStudent(courseType, courseId, section);
                                        if(lecture != null){
                                            subject.addLecture(lecture);
                                        }
                                    }
                                    else if(courseType.equals("Lap")){
                                        CourseComponent lab = data.getCourseForStudent(courseType, courseId, section);
                                        if(lab != null){
                                            subject.addLab(lab);
                                        }
                                    }
                                    break;
                                }
                            }

                            index++;
                            if(values.get(index).equals("Detail")){
                                index++;
                                break;
                            }
                        }

                        while(index < values.size()){
                            // System.out.println(values.get(index));
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





    public boolean validateLogin(String username, String password) {
        for (Student st : students) {
            if (st.getUsername().equals(username) && st.getPassword().equals(password)) {
                this.student = st;
                return true;
            }
        }
        return false;
    }


    
}
package Subject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;

public class StudentData {
    private ArrayList<Student> students = new ArrayList<>();
    private String csvFile;





    public StudentData(String csvFile) {
        this.csvFile = csvFile;
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
        for (Student student : students) {
            if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
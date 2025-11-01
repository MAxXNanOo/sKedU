package Subject;

import java.util.ArrayList;

public class Student{
    private String username;
    private String password;
    private String studentId;
    private String studentName;
    private String major;
    private ArrayList<Subject> subjects;
    private ArrayList<String> details;

    public Student(String username, String password, String studentId, String studentName, String major){
        this.username = username;
        this.password = password;
        this.studentId = studentId;
        this.studentName = studentName;
        this.major = major;
        this.subjects = new ArrayList<Subject>();
    }




    // Get
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getStudentId(){
        return this.studentId;
    }
    public String getStudentName(){
        return this.studentName;
    }
    public String getId(){
        return this.studentId;
    }
    public String getMajor(){
        return this.major;
    }
    public ArrayList<Subject> getSubjects(){
        return this.subjects;
    }
    public ArrayList<String> getDetails(){
        return this.details;
    }





    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }
    public void addDetail(String detail){
        if(this.details == null){
            this.details = new ArrayList<String>();
        }
        this.details.add(detail);
    }
    



}
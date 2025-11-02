package Subject;

import java.util.ArrayList;

public class Subject {
    private String id;
    private String name;
    private int totalCredit;
    private ArrayList<CourseComponent> lectures;
    private ArrayList<CourseComponent> labs;



    public Subject(String id, String name, int totalCredit) {
        this.id = id;
        this.name = name;
        this.totalCredit = totalCredit;
        this.lectures = new ArrayList<>();
        this.labs = new ArrayList<>();
    }

    









    // Set

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTotalCredit(int c){
        this.totalCredit = c;
    }
    public void addLecture(CourseComponent lecture) {
        lectures.add(lecture);
    }
    public void addLab(CourseComponent lab) {
        labs.add(lab);
    }









    // Get

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getTotalCredit() {
        return totalCredit;
    }



    public CourseComponent getLecture(int Index) {
        return lectures.get(Index);
    }
    public ArrayList<CourseComponent> getAllLecture() {
        return lectures;
    }



    public CourseComponent getLab(int Index) {
        return labs.get(Index);
    }
    public ArrayList<CourseComponent> getAllLab() {
        return labs;
    }









}

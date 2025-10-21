package table;

import java.util.ArrayList;

public class Student {
    private String Id;
    private String firstName;
    private String lastName;
    private String Major;
    private int year;
    private ArrayList<Subject> subjects;

    public Student(String Id, String firstName, String lastName, String Major, int year){
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Major = Major;
        this.year = year;

        subjects = new ArrayList<Subject>();
    }

    
}

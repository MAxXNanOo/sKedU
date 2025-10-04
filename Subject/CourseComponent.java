package Subject;

import java.util.ArrayList;
import java.util.Collections;

public class CourseComponent {
    //,3,3,815,"วันจันทร์  10:30-12:00,วันพุธ  10:30-12:00","LH3-211 , LH3-211",Q08,150,,,,,,,ชุดาพร สอนภักดี*
    protected int credit;
    protected int sec;
    protected ArrayList<String> dayTime; //dayTime[0] = "วันจันทร์  10:30-12:00"   , dayTime[1] = "วันพุธ  10:30-12:00"
    protected ArrayList<String> day; //day[0] = "วันจันทร์" ,  day[1] = "วันพุธ"
    protected ArrayList<String> start;   //start[0] = 10.30 ,  start[1] = 10.30
    protected ArrayList<String> end; //end[0] = 12.00  ,  end[1] = 12.00
    protected ArrayList<String> room; //room[0] = "LH3-211" , room[1] = "LH3-211"
    protected ArrayList<String> major;
    protected int maxStudent;
    protected  ArrayList<String> teacherName;

    public CourseComponent(int credit, int sec, String dayTime, String room, String major,  int maxStudent, String teacherName) {
        this.credit = credit;
        this.sec = sec;

        this.dayTime = new ArrayList<>();
        this.day = new ArrayList<>();
        this.start = new ArrayList<>();
        this.end = new ArrayList<>();
        this.room = new ArrayList<>();
        this.major = new ArrayList<>();
        
        Collections.addAll(this.dayTime, dayTime.split(","));
        Collections.addAll(this.room, room.split(","));
        Collections.addAll(this.major, major.split(","));

        this.maxStudent = maxStudent;

        this.teacherName = new ArrayList<>();
        Collections.addAll(this.teacherName, teacherName.split("."));
    }


    








    //Get

    public int getCredit() {
        return credit;
    }

    public int getSection() {
        return sec;
    }

    public String getDayTimeRoom() {
        return dayTime[0].trim() + " @ " + room[0].trim();
    }


    public String[] getDayTimes() {
        return dayTime;
    }

    public String[] getRooms() {
        return room;
    }

    public String[] getMajors() {
        return major;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public String[] getTeacherName() {
        return teacherName;
    }
}
package Subject;


public class CourseComponent {
    protected int credit;
    protected int sec;
    protected String[] dayTime;
    protected String[] room;
    protected String[] major;
    protected int maxStudent;
    protected  String[] teacherName;

    public CourseComponent(int credit, int sec, String dayTime, String room, String major,  int maxStudent, String teacherName) {
        this.credit = credit;
        this.sec = sec;
        this.dayTime = dayTime.split(",");
        this.room = room.split(",");
        this.major = major.split(",");
        this.maxStudent = maxStudent;
        this.teacherName = teacherName.split(",");
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



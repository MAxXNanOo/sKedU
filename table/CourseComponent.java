package table;

import java.util.ArrayList;
import java.util.Collections;

public class CourseComponent {
    //,3,3,815,"วันจันทร์  10:30-12:00,วันพุธ  13:30-15:00","LH3-211 , LH3-211",Q08,150,,,,,,,ชุดาพร สอนภักดี*
    protected int credit;
    protected int sec;
    protected ArrayList<String> dayTimes; //dayTime[0] = "วันจันทร์  10:30-12:00"   , dayTime[1] = "วันพุธ  13:30-15:00"
    protected ArrayList<String> days; //day[0] = "วันจันทร์" ,  day[1] = "วันพุธ"
    protected ArrayList<Double> starts;   //start[0] = 10.30 ,  start[1] = 13.30
    protected ArrayList<Double> ends; //end[0] = 12.00  ,  end[1] = 15.00
    protected ArrayList<String> rooms; //room[0] = "LH3-211" , room[1] = "LH3-211"
    protected ArrayList<String> majors; //major[0] = Q08
    protected int maxStudent; // maxStudent = 150
    protected  ArrayList<String> teacherNames;

    public CourseComponent(int credit, int sec, String dayTime, String room, String major,  int maxStudent, String teacherName) {
        this.credit = credit;
        this.sec = sec;

        this.dayTimes = new ArrayList<>();
        this.days = new ArrayList<>();
        this.starts = new ArrayList<>();
        this.ends = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.majors = new ArrayList<>();
        
        Collections.addAll(this.dayTimes, dayTime.split(","));
        Collections.addAll(this.rooms, room.split(","));
        Collections.addAll(this.majors, major.split(","));

        this.maxStudent = maxStudent;

        this.teacherNames = new ArrayList<>();
        Collections.addAll(this.teacherNames, teacherName.split(","));

        set();
    }


    //safe 5/10
    public void set() 
    {
        for (String dt : dayTimes) 
        {
            dt = dt.trim();//ใว้ตัดช่องว่างส่วนเกินออก

            // แยกส่วนของวัน กับ ส่วนของเวลา
            // วันจันทร์ 10:30-12:00 > วันจันทร์ , 10:30-12:00
            String[] parts = dt.split("\\s+", 2);
            if (parts.length < 2) continue; //กัน error ถ้าข้อมูลไม่ครบ

            String day = parts[0].trim();
            String time = parts[1].trim();

            // แยกเวลาเริ่มและเวลาเลิก
            String[] timeParts = time.split("-");

            if (timeParts.length < 2) continue;
            
            double start = CT(timeParts[0].trim()); //10:30 → 10.30
            double end = CT(timeParts[1].trim());

            days.add(day);
            starts.add(start);
            ends.add(end);
        }
    }

    private double CT(String time) 
    {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        return hour + (minute / 60.0);
    }

    //Get


    public int getCredit() {
        return credit;
    }


    public int getSection() {
        return sec;
    }


    public ArrayList<String> getDayTimes() {
        return dayTimes;
    }
    public ArrayList<String> getDays(){
        return days;
    }
    public ArrayList<Double> getStarts(){
        return starts;
    }
    public ArrayList<Double> getEnds(){
        return ends;
    }


    public ArrayList<String> getRooms() {
        return rooms;
    }


    public ArrayList<String> getMajors() {
        return majors;
    }


    public int getMaxStudent() {
        return maxStudent;
    }

    
    public ArrayList<String> getTeacherNames() {
        return teacherNames;
    }
}
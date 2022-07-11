package Library.Human.Staff_Manager;

import Library.Human.Human;
import Library.LentBook_Manager.LentBookManager;

import java.util.Calendar;

public class Staff extends Human {
    private String staff;
    private Long salary;
    private String attendace;
    private Calendar timeAttendance;

    //Constructor
    public Staff(int id, String name, String gender, Calendar dateOfBirth, String address, String phoneNumber, String email,String staff, Long salary, String attendace) {
        super(id, name, gender, dateOfBirth, address, phoneNumber, email);
        this.staff = staff;
        this.salary = salary;
        this.attendace = attendace;
    }

    //Getter and Setter
    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getAttendace() {
        return attendace;
    }

    public void setAttendace(String attendace) {
        this.attendace = attendace;
    }

    public Calendar getTimeAttendance() {
        return timeAttendance;
    }

    public void setTimeAttendance(Calendar timeAttendance) {
        this.timeAttendance = timeAttendance;
    }

    //Date convert
    public String dateConvert(){
        return getDateOfBirth().get(Calendar.DATE) + "/" + (getDateOfBirth().get(Calendar.MONTH) + 1) + "/" + getDateOfBirth().get(Calendar.YEAR);
    }

    //DateReConvert
    public void dateReConvert(String dateReConvert){
        Calendar newCalendar = Calendar.getInstance();
        String[] times = dateReConvert.split("/");
        int date = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        int year = Integer.parseInt(times[2]);
        newCalendar.set(year, month - 1 , date);
        setDateOfBirth(newCalendar);
    }

    //Money convert
    public String moneyConvert(){
        String soDu_String = Long.toString(getSalary());
        String soDu_String_result = "" ;
        for(int i=soDu_String.length()-1 ; i>=0; i-=3){
            if(i >= 3){
                soDu_String_result = "." + soDu_String.substring(i-2, i+1) + soDu_String_result;
            }else {
                soDu_String_result = soDu_String.substring(0, i+1) + soDu_String_result;
            }
        }
        return soDu_String_result + "VND";
    }
}

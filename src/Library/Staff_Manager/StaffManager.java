package Library.Staff_Manager;

import Library.Check;

import java.util.ArrayList;
import java.util.Calendar;

public class StaffManager extends Check {
    private boolean isUpdate = false;
    private int codeCount = 0;

    //Getter and Setter
    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean update) {
        isUpdate = update;
    }

    //Staff List
    private final ArrayList<Staff> staffs = new ArrayList<>();

    //Staff Header
    public String[] staffContent(){
        return new String[]{"ID", "Name", "Gender", "Date Of Birth", "Address", "Phone Number", "Email", "Position", "Salary", "Attendance"};
    }

    //Staff Category
    public String[] staffCategory(){
        return new String[]{"Sanitation worker", "Treasurer"};
    }

    //Staff Gender
    public String[] staffGender(){
        return new String[]{"Male", "Female", "Other"};
    }

    //Staff Attendence
    public String[] staffAttendence(){
        return new String[]{"None", "Attendance", "Absent", "Late", "Leave of Absence Letter" , "Quit"};
    }

    //Create a Staff
    public Staff createStaff(String name, String gender, Calendar dateOfBirth, String address, String phoneNumber,String email, String staff, Long salary, String attendace){
        codeCount++;
        return new Staff(codeCount, name, gender, dateOfBirth, address, phoneNumber, email,staff , salary, attendace);
    }

    //Add Staff
    public void addStaff(Staff staff){
        staffs.add(staff);
    }

    //Total Staff
    public int totalStaff(){
        return codeCount;
    }

    //Staff List
    public String[][] listStaff(){
        String[][] mainObj = new String[totalStaff()][10];
        int count = 0;
        for (Staff staff : staffs){
            for (int i=0; i<10; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = String.valueOf(staff.getId());
                        break;
                    case 1:
                        mainObj[count][i] = staff.getName();
                        break;
                    case 2:
                        mainObj[count][i] = staff.getGender();
                        break;
                    case 3:
                        mainObj[count][i] = staff.dateConvert();
                        break;
                    case 4:
                        mainObj[count][i] = staff.getAddress();
                        break;
                    case 5:
                        mainObj[count][i] = staff.getPhoneNumber();
                        break;
                    case 6:
                        mainObj[count][i] = staff.getEmail();
                        break;
                    case 7:
                        mainObj[count][i] = staff.getStaff();
                        break;
                    case 8:
                        mainObj[count][i] = staff.moneyConvert();
                        break;
                    case 9:
                        mainObj[count][i] = staff.getAttendace();
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //Staff Delete
    public void removeStaff(String code){
        boolean codeReduce = false;
        for (int i=0; i<staffs.size(); i++){
            if(Integer.toString(staffs.get(i).getId()).equalsIgnoreCase(code.trim()) && !codeReduce){
                staffs.remove(staffs.get(i));
                codeReduce = true;
                codeCount--;
            }
            if (codeReduce && i!=staffs.size()){
                staffs.get(i).setId(staffs.get(i).getId() - 1);
            }
        }
    }

    //Staff Fix
    public void editStaff(String code, int col, String value){
        for (Staff staff: staffs){
            if(Integer.toString(staff.getId()).equalsIgnoreCase(code.trim())){
                switch (col) {
                    case 1 -> staff.setName(value);
                    case 2 -> staff.setGender(value);
                    case 3 -> staff.dateReConvert(value);
                    case 4 -> staff.setAddress(value);
                    case 5 -> staff.setPhoneNumber(value);
                    case 6 -> staff.setEmail(value);
                    case 7 -> staff.setStaff(value);
                    case 8 -> staff.setSalary(Long.parseLong(value));
                    case 9 -> staff.setAttendace(value);
                    default -> {
                    }
                }
            }
        }
    }
}


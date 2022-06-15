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
    private ArrayList<Staff> staffs = new ArrayList<>();

    //Staff Header
    public String[] staffContent(){
        String staffContent[] = {"ID", "Name", "Gender", "Date Of Birth", "Address", "Phone Number", "Position", "Salary", "Attendance"};
        return staffContent;
    }

    //Staff Category
    public String[] staffCategory(){
        String staffCategory[] = {"Sanitation worker", "Treasurer"};
        return staffCategory;
    }

    //Staff Gender
    public String[] staffGender(){
        String staffGender[] = {"Male", "Female", "Other"};
        return staffGender;
    }

    //Staff Attendence
    public String[] staffAttendence(){
        String staffAttendence[] = {"Attendance", "Absent", "Late", "Leave of Absence Letter" , "Quit"};
        return staffAttendence;
    }

    //Create a Staff
    public Staff createStaff(String name, String gender, Calendar dateOfBirth, String address, String phoneNumber, String staff, Long salary, String attendace){
        codeCount++;
        Staff staff1 = new Staff(codeCount, name, gender, dateOfBirth, address, phoneNumber, staff , salary, attendace);
        return staff1;
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
        String[][] mainObj = new String[totalStaff()][9];
        int count = 0;
        for (Staff staff : staffs){
            for (int i=0; i<9; i++){
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
                        mainObj[count][i] = staff.getStaff();
                        break;
                    case 7:
                        mainObj[count][i] = staff.moneyConvert();
                        break;
                    case 8:
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
                switch (col){
                    case 1:
                        staff.setName(value);
                        break;
                    case 2:
                        staff.setGender(value);
                        break;
                    case 3:
                        staff.dateReConvert(value);
                        break;
                    case 4:
                        staff.setAddress(value);
                        break;
                    case 5:
                        staff.setPhoneNumber(value);
                        break;
                    case 6:
                        staff.setStaff(value);
                        break;
                    case 7:
                        staff.setSalary(Long.parseLong(value));
                        break;
                    case 8:
                        staff.setAttendace(value);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}


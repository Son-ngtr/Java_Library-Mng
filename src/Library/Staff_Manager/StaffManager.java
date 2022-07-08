package Library.Staff_Manager;

import Database.ConectionDTB;
import Database.Staff_Database;
import Library.Check;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class StaffManager {
    private Check check = new Check();
    private boolean isUpdate = false;
    private int codeCount = 0;
    private ConectionDTB conectionDTB = new ConectionDTB();
    private Connection connection = conectionDTB.getConnect();
    private Staff_Database staff_database = new Staff_Database();


    //Getter and Setter
    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean update) {
        isUpdate = update;
    }

    //Staff List
    private final ArrayList<Staff> staffs = new ArrayList<>();

    //Get Staff
    private Staff getStaff(int ID){
        return staffs.get(ID -1 );
    }

    //Staff Header
    public String[] staffContent(){
        return new String[]{"ID", "Name", "Gender", "Date Of Birth", "Address", "Phone Number", "Email", "Position", "Salary", "Attendance"};
    }
    public int staffContentIndex(String s){
        switch (s){
            case "ID":
                return 0;
            case "Name":
                return 1;
            case "Gender":
                return 2;
            case "Date Of Birth":
                return 3;
            case "Address":
                return 4;
            case "Phone Number":
                return 5;
            case "Email":
                return 6;
            case "Position":
                return 7;
            case "Salary":
                return 8;
            case "Attendance":
                return 9;

        }
        return 100;
    }

    //Staff Category
    public String[] staffCategory(){
        return new String[]{"Sanitation worker", "Treasurer", "Librarian"};
    }

    //Staff Salary Per Day
    public Long[] staffSalary(){
        return new Long[]{50000L, 70000L, 100000L};
    }

    public Long[] staffSalaryLate(){
        return new Long[]{30000L, 50000L, 70000L};
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
        try {
            staff_database.addNewStaff(
                    connection,
                    staff.getId(),
                    staff.getName(),
                    staff.getGender(),
                    staff.dateConvert(),
                    staff.getAddress(),
                    staff.getPhoneNumber(),
                    staff.getEmail(),
                    staff.getStaff(),
                    String.valueOf(staff.getSalary()),
                    staff.getAttendace()
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Total Staff
    public int totalStaff(){
        return codeCount;
    }

    //Salary Payment
    public void salaryPayment(){
        Vector<Vector<Object>> vectors = null;
        try {
            vectors = staff_database.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (Vector<Object> vector : vectors){
            if(!String.valueOf(vector.get(10)).equalsIgnoreCase("0")){
                Calendar currentTime = Calendar.getInstance();
                Calendar time = check.dateReConvert(String.valueOf(vector.get(10)));
                int timeDate = time.get(Calendar.DATE);
                int currentTimeDate = currentTime.get(Calendar.DATE);
                if(currentTimeDate > timeDate){
                    editStaff(String.valueOf(vector.get(0)) , 9, "None");
                    switch (String.valueOf(vector.get(7))){
                        case "Sanitation worker":
                            switch (String.valueOf(vector.get(9))){
                                case "Attendance":
                                    editStaff(String.valueOf(vector.get(0)) , 8, String.valueOf(Long.parseLong(String.valueOf(vector.get(8))) + staffSalary()[0]));
                                    break;
                                case "Late":
                                    editStaff(String.valueOf(vector.get(0)) , 8, String.valueOf(Long.parseLong(String.valueOf(vector.get(8))) + staffSalaryLate()[0]));
                                    break;
                                case "Absent":
                                    editStaff(String.valueOf(vector.get(0)) , 8, String.valueOf(Long.parseLong(String.valueOf(vector.get(8))) - staffSalary()[0]));
                                    break;
                            }
                            break;
                        case "Treasurer":
                            switch (String.valueOf(vector.get(9))){
                                case "Attendance":
                                    editStaff(String.valueOf(vector.get(0)) , 8, String.valueOf(Long.parseLong(String.valueOf(vector.get(8))) + staffSalary()[1]));
                                    break;
                                case "Late":
                                    editStaff(String.valueOf(vector.get(0)) , 8, String.valueOf(Long.parseLong(String.valueOf(vector.get(8))) + staffSalaryLate()[1]));
                                    break;
                                case "Absent":
                                    editStaff(String.valueOf(vector.get(0)) , 8, String.valueOf(Long.parseLong(String.valueOf(vector.get(8))) - staffSalary()[1]));
                                    break;
                            }
                            break;
                        case "Librarian":
                            switch (String.valueOf(vector.get(9))){
                                case "Attendance":
                                    editStaff(String.valueOf(vector.get(0)) , 8, String.valueOf(Long.parseLong(String.valueOf(vector.get(8))) + staffSalary()[2]));
                                    break;
                                case "Late":
                                    editStaff(String.valueOf(vector.get(0)) , 8, String.valueOf(Long.parseLong(String.valueOf(vector.get(8))) + staffSalaryLate()[2]));
                                    break;
                                case "Absent":
                                    editStaff(String.valueOf(vector.get(0)) , 8, String.valueOf(Long.parseLong(String.valueOf(vector.get(8))) - staffSalary()[2]));
                                    break;
                            }
                            break;
                    }

                }
            }else {
                editStaff(String.valueOf(codeCount) , 9, "None");
            }
        }
    }

    //Download Staff
    public void downloadAllStaff(){
        Vector<Vector<Object>> vectors = null;
        try {
            vectors = staff_database.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Vector<Object> vector : vectors){
            codeCount++;
            Staff staff = new Staff(
                    codeCount,
                    String.valueOf(vector.get(1)),
                    String.valueOf(vector.get(2)),
                    check.dateReConvert(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    String.valueOf(vector.get(6)),
                    String.valueOf(vector.get(7)),
                    Long.parseLong(String.valueOf(vector.get(8))),
                    String.valueOf(vector.get(9))
            );
            staffs.add(staff);
        }
        salaryPayment();
    }

    //Staff List
    public String[][] listStaff(){
        String[][] mainObj = new String[totalStaff()][staffContent().length];
        int count = 0;
        for (Staff staff : staffs){
            for (int i=0; i<staffContent().length; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = "HS" + String.valueOf(staff.getId());
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
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCount ){
            if(Integer.toString(staffs.get(intCode-1).getId()).equalsIgnoreCase(code.trim())){
                staffs.remove(intCode - 1);

                //Delete in DTB
                try {
                    staff_database.deleteStaff(connection, code);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                codeCount--;
                for (int i=intCode-1;i<staffs.size(); i++ ){
                    int newCode = staffs.get(i).getId() - 1;
                    staffs.get(i).setId(newCode);

                    //Update in DTB
                    try {
                        staff_database.updateStaff(connection, i+2,0, String.valueOf(newCode));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    //Staff Fix
    public void editDatabase(String code, int col, String value){
        try {
            staff_database.updateStaff(connection, Integer.parseInt(code), col, value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editStaff(String code, int col, String value){
        for (Staff staff: staffs){
            if(Integer.toString(staff.getId()).equalsIgnoreCase(code.trim())){
                switch (col) {
                    case 1 -> {
                        staff.setName(value);
                        editDatabase(code, col, value);
                    }
                    case 2 -> {
                        staff.setGender(value);
                        editDatabase(code, col, value);
                    }
                    case 3 -> {
                        staff.dateReConvert(value);
                        editDatabase(code, col, value);
                    }
                    case 4 -> {
                        staff.setAddress(value);
                        editDatabase(code, col, value);
                    }
                    case 5 -> {
                        staff.setPhoneNumber(value);
                        editDatabase(code, col, value);
                    }
                    case 6 -> {
                        staff.setEmail(value);
                        editDatabase(code, col, value);
                    }
                    case 7 -> {
                        staff.setStaff(value);
                        editDatabase(code, col, value);
                    }
                    case 8 -> {
                        if(Long.parseLong(value) < 0){
                            value = "0";
                        }
                        staff.setSalary(Long.parseLong(value));
                        editDatabase(code, col, value);
                    }
                    case 9 -> {
                        staff.setAttendace(value);
                        editDatabase(code, col, value);
                    }
                }
            }
        }
    }
}


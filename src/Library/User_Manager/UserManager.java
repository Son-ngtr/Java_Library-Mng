package Library.User_Manager;

import Library.Check;
import Library.LentBook_Manager.LentBookManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

public class UserManager {
    private boolean isUpdate = false;
    private int codeCount = 0;
    private Connection conn;

    //Constructor
    public UserManager(Connection conn){
        this.conn = conn;
    }

    //Getter and Setter
    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean update) {
        isUpdate = update;
    }

    //Staff List
    private final ArrayList<User> users = new ArrayList<>();

    //User Header
    public String[] userContent(){
        return new String[]{"ID", "Name", "Gender", "Date Of Birth", "Address", "Phone Number", "Email", "Total Books", "Fine Money"};
    }

    //User Gender
    public String[] userGender(){
        return new String[]{"Male", "Female", "Other"};
    }

    //Create a User
    public User createUser(String name, String gender, Calendar dateOfBirth, String address, String phoneNumber, String email,int totalBooks, Long moneyFine){
        codeCount++;
        return new User(codeCount, name, gender, dateOfBirth, address, phoneNumber, email ,totalBooks, moneyFine);
    }

    //Add User
    public void addUser(User user){
        users.add(user);
    }

    //Total User
    public int totalUser(){
        return codeCount;
    }

    //User List
    public String[][] listUser(){
        String[][] mainObj = new String[totalUser()][userContent().length];
        int count = 0;
        for (User user : users){
            for (int i=0; i<userContent().length; i++){
                switch (i) {
                    case 0 -> mainObj[count][i] = "HU" + String.valueOf(user.getId());
                    case 1 -> mainObj[count][i] = user.getName();
                    case 2 -> mainObj[count][i] = user.getGender();
                    case 3 -> mainObj[count][i] = user.dateConvert(user.getDateOfBirth());
                    case 4 -> mainObj[count][i] = user.getAddress();
                    case 5 -> mainObj[count][i] = user.getPhoneNumber();
                    case 6 -> mainObj[count][i] = user.getEmail();
                    case 7 -> mainObj[count][i] = String.valueOf(user.getTotalBooks());
                    case 8 -> mainObj[count][i] = user.moneyConvert();
                    default -> {
                    }
                }
            }
            count++;
        }
        return mainObj;
    }

    //User Delete
    public void removeUser(String code){
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCount ){
            if(Integer.toString(users.get(intCode-1).getId()).equalsIgnoreCase(code.trim())){
                users.remove(intCode - 1);
                codeCount--;

                for (int i=intCode-1;i<users.size(); i++ ){
                    users.get(i).setId(users.get(i).getId() - 1);
                }
            }

        }
    }

    //User Fix
    public void editUser(String code, int col, String value){
        for (User user: users){
            if(Integer.toString(user.getId()).equalsIgnoreCase(code.trim())){
                switch (col) {
                    case 1 -> user.setName(value);
                    case 2 -> user.setGender(value);
                    case 3 -> user.setDateOfBirth(user.dateReConvert(value));
                    case 4 -> user.setAddress(value);
                    case 5 -> user.setPhoneNumber(value);
                    case 6 -> user.setEmail(value);
                    case 7 -> user.setTotalBooks(Integer.parseInt(value));
                    case 8 -> user.setMoneyFine(Long.parseLong(value));
                    default -> {
                    }
                }
            }
        }
    }

    //LentBook List
    public LentBookManager lentBookManager(String userCode){
        int intUserCode = Integer.parseInt(userCode);
        return users.get(intUserCode - 1).getLentBookManager();
    }
}

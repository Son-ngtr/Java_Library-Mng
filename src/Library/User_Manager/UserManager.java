package Library.User_Manager;

import Database.ConectionDTB;
import Database.User_Database;
import Library.Check;
import Library.LentBook_Manager.LentBookManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class UserManager {

    Check check = new Check();

    //DataBase
    private User_Database user_database = new User_Database();


    private boolean isUpdate = false;
    private int codeCount = 0;
    private ConectionDTB conectionDTB = new ConectionDTB();
    private Connection connection = conectionDTB.getConnect();

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
        try {
            user_database.addNewUser(
                    connection,
                    user.getId(),
                    user.getName(),
                    user.getGender(),
                    user.dateConvert(user.getDateOfBirth()),
                    user.getAddress(),
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getTotalBooks(),
                    Integer.parseInt(String.valueOf(user.getMoneyFine()))
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Total User
    public int totalUser(){
        return codeCount;
    }

    //Download User
    public void downloadAllUser(){
        Vector<Vector<Object>> vectors = null;
        try {
            vectors = user_database.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Vector<Object> vector : vectors){
            codeCount++;
            User user = new User(
                    codeCount,
                    String.valueOf(vector.get(1)),
                    String.valueOf(vector.get(2)),
                    check.dateReConvert(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    String.valueOf(vector.get(6)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    Long.parseLong(String.valueOf(vector.get(8)))
            );
            users.add(user);
        }
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

                //Delete in DTB
                try {
                    user_database.deleteUser(connection, code);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                codeCount--;

                for (int i=intCode-1;i<users.size(); i++ ){
                    int newCode = users.get(i).getId() - 1;
                    users.get(i).setId(newCode);

                    //Update In DTB
                    try {
                        user_database.updateUser(connection, i+2, 0, String.valueOf(newCode));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    //User Fix
    public void editDatabase(String code, int col, String value){
        try {
            user_database.updateUser(connection, Integer.parseInt(code), col, value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editUser(String code, int col, String value){
        for (User user: users){
            if(Integer.toString(user.getId()).equalsIgnoreCase(code.trim())){
                switch (col) {
                    case 1 -> {
                        user.setName(value);
                        editDatabase(code, col, value);
                    }
                    case 2 -> {
                        user.setGender(value);
                        editDatabase(code, col, value);
                    }
                    case 3 -> {
                        user.setDateOfBirth(user.dateReConvert(value));
                        editDatabase(code, col, value);
                    }
                    case 4 -> {
                        user.setAddress(value);
                        editDatabase(code, col, value);
                    }
                    case 5 -> {
                        user.setPhoneNumber(value);
                        editDatabase(code, col, value);
                    }
                    case 6 -> {
                        user.setEmail(value);
                        editDatabase(code, col, value);
                    }
                    case 7 -> {
                        user.setTotalBooks(Integer.parseInt(value));
                        editDatabase(code, col, value);
                    }
                    case 8 -> {
                        user.setMoneyFine(Long.parseLong(value));
                        editDatabase(code, col, value);
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

package Library.User_Manager;

import Database.ConectionDTB;
import Database.UserBook_info;
import Database.User_Database;
import Library.Check;
import Library.LentBook_Manager.CountDownBook;
import Library.LentBook_Manager.LentBookManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class UserManager {

    Check check = new Check();

    //DataBase
    private User_Database user_database = new User_Database();

    private UserBook_info userBook_info = new UserBook_info();
    private boolean isUpdate = false;
    private int codeCount = 0;
    private ConectionDTB conectionDTB = new ConectionDTB();
    private Connection connection = conectionDTB.getConnect();
    private String[] useLentInfo;

    //Getter and Setter
    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean update) {
        isUpdate = update;
    }

    public String[] getUseLentInfo() {
        return useLentInfo;
    }

    //User Save Info
    public void setUseLentInfo(String[] useLentInfo) {
        this.useLentInfo = useLentInfo;
    }

    //Get User
    public User getUser(int ID){
        return users.get(ID-1);
    }

    //User List
    private final ArrayList<User> users = new ArrayList<>();

    //Lent Book List
    private final ArrayList<LentBookManager> lentBookManagers = new ArrayList<>();

    //User Header
    public String[] userContent(){
        return new String[]{"ID", "Name", "Gender", "Date Of Birth", "Address", "Phone Number", "Email", "Total Books", "Fine Money"};
    }
    public int userContentIndex(String s){
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
            case "Total Books":
                return 7;
            case "Fine Money":
                return 8;
        }
        return 100;
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

    //Add LentBookManager
    public void addLentBookManager(){
        lentBookManagers.add(new LentBookManager(String.valueOf(codeCount)));
    }

    //Get LentBook Manager
    public LentBookManager getLentBookManager(String code){
        return lentBookManagers.get(Integer.parseInt(code) - 1);
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
                    String.valueOf(user.getMoneyFine())
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Create lentbookManager
        addLentBookManager();

        //Create lent zone
        try {
            userBook_info.createTable(connection , String.valueOf(codeCount));

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
            addLentBookManager();
        }
        //Lent Book Manager
        Vector<Vector<Object>> lentVectors = null;
        for (LentBookManager lentBookManager : lentBookManagers){
            lentBookManager.downloadLentBook();
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
                    userBook_info.deleteAndSort(connection, code, String.valueOf(codeCount));
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

                //Remove in lentbook
                lentBookManagers.remove(Integer.parseInt(code) - 1);
                for (int i = Integer.parseInt(code) - 1; i<lentBookManagers.size(); i++){
                    lentBookManagers.get(i).changeUserID(String.valueOf(i+1));
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

    //Start Count Down
    public void startCountDown(LentBookManager lentBookManager, JTable tableBook, UserManager userManager, DefaultTableModel defaultTableModelBook){
        for (LentBookManager lentBookList : lentBookManagers){
            lentBookList.startCountDown(lentBookManager, tableBook, userManager, defaultTableModelBook);
        }
    }

    //Stop Run
    public void stopCountDown(){
        for (LentBookManager lentBooklist : lentBookManagers){
            lentBooklist.stopRun();
        }
    }
}

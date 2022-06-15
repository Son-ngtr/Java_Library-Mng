package Library.User_Manager;

import Library.Check;

import java.util.ArrayList;
import java.util.Calendar;

public class UserManager extends Check {
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
    private ArrayList<User> users = new ArrayList<>();

    //User Header
    public String[] userCategory(){
        String staffCategory[] = {"ID", "Name", "Gender", "Date Of Birth", "Address", "Phone Number", "Email", "EXP.Date", "REGIS.Date", "Fine Money"};
        return staffCategory;
    }

    //Create a User
    public User createUser(String name, String gender, Calendar dateOfBirth, String address, String phoneNumber, String email, Calendar expDate, Calendar regisDate, Long moneyFine){
        codeCount++;
        User user = new User(codeCount, name, gender, dateOfBirth, address, phoneNumber, email , expDate, regisDate, moneyFine);
        return user;
    }

    //Add User
    public void addStaff(User user){
        users.add(user);
    }

    //Total User
    public int totalUser(){
        return codeCount;
    }

    //User List
    public String[][] listUser(){
        String[][] mainObj = new String[totalUser()][10];
        int count = 0;
        for (User user : users){
            for (int i=0; i<10; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = String.valueOf(user.getId());
                        break;
                    case 1:
                        mainObj[count][i] = user.getName();
                        break;
                    case 2:
                        mainObj[count][i] = user.getGender();
                        break;
                    case 3:
                        mainObj[count][i] = user.dateConvert(user.getDateOfBirth());
                        break;
                    case 4:
                        mainObj[count][i] = user.getAddress();
                        break;
                    case 5:
                        mainObj[count][i] = user.getPhoneNumber();
                        break;
                    case 6:
                        mainObj[count][i] = user.getEmail();
                        break;
                    case 7:
                        mainObj[count][i] = user.dateConvert(user.getExpDate());
                        break;
                    case 8:
                        mainObj[count][i] = user.dateConvert(user.getRegisDate());
                        break;
                    case 9:
                        mainObj[count][i] = user.moneyConvert();
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //User Delete
    public void removeUser(String code){
        boolean codeReduce = false;
        for (int i=0; i<users.size(); i++){
            if(Integer.toString(users.get(i).getId()).equalsIgnoreCase(code.trim()) && !codeReduce){
                users.remove(users.get(i));
                codeReduce = true;
                codeCount--;
            }
            if (codeReduce && i!=users.size()){
                users.get(i).setId(users.get(i).getId() - 1);
            }
        }
    }

    //User Fix
    public void editUser(String code, int col, String value){
        for (User user: users){
            if(Integer.toString(user.getId()).equalsIgnoreCase(code.trim())){
                switch (col){
                    case 1:
                        user.setName(value);
                        break;
                    case 2:
                        user.setGender(value);
                        break;
                    case 3:
                        user.dateReConvert(value);
                        break;
                    case 4:
                        user.setAddress(value);
                        break;
                    case 5:
                        user.setPhoneNumber(value);
                        break;
                    case 6:
                        user.setEmail(value);
                        break;
                    case 7:
                        user.dateReConvert(value);
                        break;
                    case 8:
                        user.dateReConvert(value);
                        break;
                    case 9:
                        user.setMoneyFine(Long.parseLong(value));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}

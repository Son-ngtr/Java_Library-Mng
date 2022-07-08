package Library.HIstory_Manager;

import Database.ConectionDTB;
import Database.History_DataBase;
import Library.Check;
import Library.Staff_Manager.Staff;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class HistoryManager {
    private Check check = new Check();
    private ConectionDTB conectionDTB = new ConectionDTB();
    private Connection connection = conectionDTB.getConnect();
    private History_DataBase history_dataBase = new History_DataBase();
    private boolean isUpdate = false;
    private int codeCount = 0;

    //Getter and Setter
    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    //History list
    ArrayList<History> histories = new ArrayList<>();

    //History Header
    public String[] historyContent(){
        return new String[]{"ID", "Name", "Phone Number","RegisDate", "ExpDate", "Book Name", "Book Type","Quantity"};
    }
    public int historyContentIndex(String s){
        switch (s){
            case "ID":
                return 0;
            case "Name":
                return 1;
            case "Phone Number":
                return 2;
            case "RegisDate":
                return 3;
            case "ExpDate":
                return 4;
            case "Book Name":
                return 5;
            case "Book Type":
                return 6;
            case "Quantity":
                return 7;
        }
        return 100;
    }

    //Create History
    public History createHistory(String name, String phoneNumber, Calendar regisDate, Calendar expDate, String bookName,String bookType, int quantity){
        codeCount++;
        return new History(codeCount, name, phoneNumber, regisDate, expDate, bookName,bookType, quantity);
    }

    //Add History
    public void addHistory(History history){
        histories.add(history);
        try {
            history_dataBase.addNewHistory(
                    connection,
                    history.getID(),
                    history.getName(),
                    history.getPhoneNumber(),
                    history.dateConvert(history.getRegisDate()),
                    history.dateConvert(history.getExpDate()),
                    history.getBookName(),
                    history.getBookType(),
                    history.getQuantity()
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Total History
    public int totalHistory(){
        return codeCount;
    }

    //Download History
    public void downLoadHistory(){
        Vector<Vector<Object>> vectors = null;
        try {
            vectors = history_dataBase.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Vector<Object> vector : vectors){
            codeCount++;
            History history = new History(
                    codeCount,
                    String.valueOf(vector.get(1)),
                    String.valueOf(vector.get(2)),
                    check.dateReConvert(String.valueOf(vector.get(3))),
                    check.dateReConvert(String.valueOf(vector.get(4))),
                    String.valueOf(vector.get(5)),
                    String.valueOf(vector.get(6)),
                    Integer.parseInt(String.valueOf(vector.get(7)))
            );
            histories.add(history);
        }
    }

    //History List
    public String[][] listHistory(){
        String[][] mainObj = new String[totalHistory()][historyContent().length];
        int count = 0;
        for (int i=histories.size()-1; i>=0; i--){
            for (int j=0; j<historyContent().length; j++){
                switch (j){
                    case 0:
                        mainObj[count][j] = "HL" + String.valueOf(histories.get(i).getID());
                        break;
                    case 1:
                        mainObj[count][j] = histories.get(i).getName();
                        break;
                    case 2:
                        mainObj[count][j] = histories.get(i).getPhoneNumber();
                        break;
                    case 3:
                        mainObj[count][j] = histories.get(i).dateConvert(histories.get(i).getRegisDate());
                        break;
                    case 4:
                        mainObj[count][j] = histories.get(i).dateConvert(histories.get(i).getExpDate());
                        break;
                    case 5:
                        mainObj[count][j] = histories.get(i).getBookName();
                        break;
                    case 6:
                        mainObj[count][j] = histories.get(i).getBookType();
                        break;
                    case 7:
                        mainObj[count][j] = Integer.toString(histories.get(i).getQuantity()) ;
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

//    //History Delete
//    public void removeHistory(String code){
//        int intCode = Integer.parseInt(code);
//        if(intCode <= codeCount ){
//            if(Integer.toString(histories.get(intCode-1).getID()).equalsIgnoreCase(code.trim())){
//                histories.remove(intCode - 1);
//                codeCount--;
//
//                for (int i=intCode-1;i<histories.size(); i++ ){
//                    histories.get(i).setID(histories.get(i).getID() - 1);
//                }
//            }
//
//        }
//    }

//    //History Fix
//    public void editStaff(String code, int col, String value){
//        for (History history: histories){
//            if(Integer.toString(history.getID()).equalsIgnoreCase(code.trim())){
//                switch (col) {
//                    case 1 -> history.setName(value);
//                    case 2 -> history.setPhoneNumber(value);
//                    case 3 -> history.dateReConvert(value, 1);
//                    case 4 -> history.dateReConvert(value, 2);
//                    case 5 -> history.setBookName(value);
//                    case 6 -> history.setBookType(value);
//                    case 7 -> history.setQuantity(Integer.parseInt(value));
//                    case 8 -> history.dateReConvert(value, 3);
//                    default -> {
//                    }
//                }
//            }
//        }
//    }
}

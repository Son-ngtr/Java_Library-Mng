package Library.HIstory_Manager;

import Database.History_DataBase;
import Library.Check;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class HistoryManager {
    private Check check = new Check();
    private Connection connection;
    private History_DataBase history_dataBase = new History_DataBase();
    private boolean isUpdate = false;
    private int codeCount = 0;

    //Constructor
    public HistoryManager(Connection connection){
        this.connection = connection;
    }

    //Getter and Setter
    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    //History list
    ArrayList<HistoryLent> histories = new ArrayList<>();

    //History Header
    public String[] historyContent(){
        return new String[]{"ID", "Name", "Phone Number","RegisDate", "ExpDate", "Book Name","Quantity"};
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
            case "Quantity":
                return 6;
        }
        return 100;
    }

    //Create History
    public HistoryLent createHistory(String name, String phoneNumber, Calendar regisDate, Calendar expDate, String bookName, int quantity){
        codeCount++;
        return new HistoryLent(codeCount, name, phoneNumber, regisDate, expDate, bookName, quantity);
    }

    //Add History
    public void addHistory(HistoryLent historyLent){
        histories.add(historyLent);
        try {
            history_dataBase.addNewHistory(
                    connection,
                    historyLent.getID(),
                    historyLent.getName(),
                    historyLent.getPhoneNumber(),
                    historyLent.dateConvert(historyLent.getRegisDate()),
                    historyLent.dateConvert(historyLent.getExpDate()),
                    historyLent.getBookName(),
                    historyLent.getQuantity()
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
            HistoryLent historyLent = new HistoryLent(
                    codeCount,
                    String.valueOf(vector.get(1)),
                    String.valueOf(vector.get(2)),
                    check.dateReConvert(String.valueOf(vector.get(3))),
                    check.dateReConvert(String.valueOf(vector.get(4))),
                    String.valueOf(vector.get(5)),
                    Integer.parseInt(String.valueOf(vector.get(6)))
            );
            histories.add(historyLent);
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

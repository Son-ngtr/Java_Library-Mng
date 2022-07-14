package Library.HIstory_Manager;

import Database.HistoryReceive_DataBase;
import Library.Check;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class HistoryReceive_Manager {
    private Check check = new Check();
    private Connection connection;
    private HistoryReceive_DataBase historyReceive_dataBase = new HistoryReceive_DataBase();
    private boolean isUpdate = false;
    private int codeCount = 0;

    //Constructor
    public HistoryReceive_Manager(Connection connection){
        this.connection = connection;
    }

    //Getter and Setter
    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    //History Receive List
    ArrayList<HistoryReceive> historyReceives = new ArrayList<>();

    //History Header
    public String[] historyReceiveContent(){
        return new String[]{"ID", "Name", "Phone Number", "ExpDate", "Book Name", "Quantity","Return Day"};
    }
    public int historyReceiveContentIndex(String s){
        switch (s){
            case "ID":
                return 0;
            case "Name":
                return 1;
            case "Phone Number":
                return 2;
            case "ExpDate":
                return 3;
            case "Book Name":
                return 4;
            case "Quantity":
                return 5;
            case "Return Day":
                return 6;
        }

        return 100;
    }

    //Create History Receive
    public HistoryReceive createHistory(String name, String phoneNumber, Calendar expDate, String bookName ,int quantity, Calendar returnDate){
        codeCount++;
        return new HistoryReceive(codeCount, name, phoneNumber,  expDate, bookName,quantity, returnDate);
    }

    //Add History Receive
    public void addHistoryReceive(HistoryReceive historyReceive){
        historyReceives.add(historyReceive);
        try {
            historyReceive_dataBase.addNewHistory(
                    connection,
                    historyReceive.getID(),
                    historyReceive.getName(),
                    historyReceive.getPhoneNumber(),
                    historyReceive.dateConvert(historyReceive.getExpDate()),
                    historyReceive.getBookName(),
                    historyReceive.getQuantity(),
                    historyReceive.dateConvert(historyReceive.getReturnDate())
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

    //Download History Receive
    public void downloadHistoryReceive(){
        Vector<Vector<Object>> vectors = null;
        try {
            vectors = historyReceive_dataBase.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Vector<Object> vector : vectors){
            codeCount++;
            HistoryReceive historyReceive = new HistoryReceive(
                    codeCount,
                    String.valueOf(vector.get(1)),
                    String.valueOf(vector.get(2)),
                    check.dateReConvert(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    Integer.parseInt(String.valueOf(vector.get(5))),
                    check.dateReConvert(String.valueOf(vector.get(6)))
            );
            historyReceives.add(historyReceive);
        }
    }

    //History List
    public String[][] listHistoryReceive(){

        String[][] mainObj = new String[totalHistory()][historyReceiveContent().length];
        int count = 0;
        for (int i=historyReceives.size()-1; i>=0; i--){
            for (int j=0; j<historyReceiveContent().length; j++){
                switch (j){
                    case 0:
                        mainObj[count][j] = "HL" + String.valueOf(historyReceives.get(i).getID());
                        break;
                    case 1:
                        mainObj[count][j] = historyReceives.get(i).getName();
                        break;
                    case 2:
                        mainObj[count][j] = historyReceives.get(i).getPhoneNumber();
                        break;
                    case 3:
                        mainObj[count][j] = historyReceives.get(i).dateConvert(historyReceives.get(i).getExpDate());
                        break;
                    case 4:
                        mainObj[count][j] = historyReceives.get(i).getBookName();
                        break;
                    case 5:
                        mainObj[count][j] = String.valueOf(historyReceives.get(i).getQuantity());
                        break;
                    case 6:
                        mainObj[count][j] = historyReceives.get(i).dateConvert(historyReceives.get(i).getReturnDate());
                        break;
                    default:
                        break;
                }
            }
            count++;
        }

        return mainObj;
    }
}

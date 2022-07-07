package Library.HIstory_Manager;

import Database.ConectionDTB;
import Database.HistoryReceive_DataBase;
import Database.History_DataBase;
import Library.Check;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class HistoryReceive_Manager {
    private Check check = new Check();
    private ConectionDTB conectionDTB = new ConectionDTB();
    private Connection connection = conectionDTB.getConnect();
    private HistoryReceive_DataBase historyReceive_dataBase = new HistoryReceive_DataBase();
    private boolean isUpdate = false;
    private int codeCount = 0;

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
        return new String[]{"ID", "Name", "Phone Number","RegisDate", "ExpDate", "Book Name", "Book Type","Quantity","Return Day"};
    }

    //Create History Receive
    public HistoryReceive createHistory(String name, String phoneNumber, Calendar regisDate, Calendar expDate, String bookName, String bookType, int quantity, Calendar returnDate){
        codeCount++;
        return new HistoryReceive(codeCount, name, phoneNumber, regisDate, expDate, bookName,bookType, quantity, returnDate);
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
                    historyReceive.dateConvert(historyReceive.getRegisDate()),
                    historyReceive.dateConvert(historyReceive.getExpDate()),
                    historyReceive.getBookName(),
                    historyReceive.getBookType(),
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
                    check.dateReConvert(String.valueOf(vector.get(4))),
                    String.valueOf(vector.get(5)),
                    String.valueOf(vector.get(6)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    check.dateReConvert(String.valueOf(vector.get(8)))
            );
        }
    }

    //History List
    public String[][] listHistoryReceive(){
        String[][] mainObj = new String[totalHistory()][historyReceiveContent().length];
        int count = 0;
        for (int i=historyReceives.size()-1; i>=0; i--){
            for (int j=0; i<historyReceiveContent().length; j++){
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
                        mainObj[count][j] = historyReceives.get(i).dateConvert(historyReceives.get(i).getRegisDate());
                        break;
                    case 4:
                        mainObj[count][j] = historyReceives.get(i).dateConvert(historyReceives.get(i).getExpDate());
                        break;
                    case 5:
                        mainObj[count][j] = historyReceives.get(i).getBookName();
                        break;
                    case 6:
                        mainObj[count][j] = historyReceives.get(i).getBookType();
                        break;
                    case 7:
                        mainObj[count][j] = Integer.toString(historyReceives.get(i).getQuantity()) ;
                        break;
                    case 8:
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
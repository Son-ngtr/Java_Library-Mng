package Library.LentBook_Manager;

import Database.LentBook_DataBase;
import Library.Check;
import Library.Human.User_Manager.UserManager;
import Library.Table_Manager.TableManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class LentBookManager {
    private Check check = new Check();
    LentBook_DataBase lentBook_dataBase;
    private Connection connection;
    private int codeCount = 0;
    private String userID;

    //Constructor
    public LentBookManager(Connection connection,String ID){
        this.connection = connection;
        this.userID = ID;
        lentBook_dataBase = new LentBook_DataBase(ID);
    }

    //Change user ID
    public void changeUserID(String ID){
        lentBook_dataBase.setCurrentID(ID);
    }

    //Lent Book List
    private final ArrayList<LentBook> lentBooks = new ArrayList<>();
    private ArrayList<CountDownBook> countDownBooks = new ArrayList<>();

    //Get Lent Book By STT
    public LentBook getLentBook(int STT){
        return lentBooks.get(STT -1 );
    }

    //Get The Highest Code Value
    public int getHighestCode(){
        int highest = 0;
        for (LentBook lentBook : lentBooks){
            if(lentBook.getCode() > highest){
                highest = lentBook.getCode();
            }
        }
        return highest;
    }

    //LentBook Header
    public String[] lentBookContent(){
        return new String[]{"STT", "Name Book", "Quantity", "LentMoney" ,"End Date", "Serial Number", "Remain Time"};
    }
    public int lentBookContentIndex(String s){
        switch (s){
            case "STT":
                return 0;
            case "Name Book":
                return 1;
            case "Quantity":
                return 2;
            case "LentMoney":
                return 3;
            case "End Date":
                return 4;
            case "Serial Number":
                return 5;
            case "Remain Time":
                return 6;
        }
        return 100;
    }

    //Create lentBook
    public LentBook createLentBook(String bookName, int numberOfBook, Long lentMoney, Calendar endDate, String SerialNumber){
        codeCount++;
        return new LentBook(codeCount, bookName, numberOfBook, lentMoney, endDate, SerialNumber, 0L);
    }

    //Add Count Down
    public void addCountDown(LentBook lentBook){
        countDownBooks.add(new CountDownBook(lentBook));
    }

    //Add Lent Book
    public void addLentBook(LentBook lentBook){
        addCountDown(lentBook);
        lentBooks.add(lentBook);
        try {
            lentBook_dataBase.addNewLentBook(
                    connection,
                    lentBook.getSTT(),
                    lentBook.getBookName(),
                    lentBook.getNumberOfBook(),
                    String.valueOf(lentBook.getLentMoney()),
                    lentBook.dateConvert(),
                    lentBook.getSerialNumber(),
                    String.valueOf(lentBook.getTimeLate()),
                    lentBook.getCode()
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Total Lent Book
    public int totalLentBook(){
        return lentBooks.size();
    }

    //Number Of Book
    public int numberOfBook(){
        int sum = 0;
        for (LentBook lentBook : lentBooks){
            sum+=lentBook.getNumberOfBook();
        }
        return sum;
    }

    //Download LentBook
    public void downloadLentBook(){
        Vector<Vector<Object>> vectors = null;
        try {
            vectors = lentBook_dataBase.getAll(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Vector<Object> vector : vectors){
            codeCount++;
            LentBook lentBook = new LentBook(
                    codeCount,
                    String.valueOf(vector.get(1)),
                    Integer.parseInt(String.valueOf(vector.get(2))),
                    Long.parseLong(String.valueOf(vector.get(3))),
                    check.dateReConvert(String.valueOf(vector.get(4))),
                    String.valueOf(vector.get(5)),
                    Long.parseLong(String.valueOf(vector.get(6)))
            );
            lentBook.setCode(Integer.parseInt(String.valueOf(vector.get(7))));
            addCountDown(lentBook);
            lentBooks.add(lentBook);
        }
    }

    //Lent Book List
    public String[][] listLentBook(){

        String[][] mainObj = new String[totalLentBook()][lentBookContent().length];
        int count = 0;
        for (LentBook lentBook : lentBooks){
            for (int i=0; i<lentBookContent().length; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = Integer.toString(lentBook.getSTT());
                        break;
                    case 1:
                        mainObj[count][i] = lentBook.getBookName();
                        break;
                    case 2:
                        mainObj[count][i] = Integer.toString(lentBook.getNumberOfBook());
                        break;
                    case 3:
                        mainObj[count][i] = lentBook.moneyConvert();
                        break;
                    case 4:
                        mainObj[count][i] = lentBook.dateConvert();
                        break;
                    case 5:
                        mainObj[count][i] = lentBook.getSerialNumber();
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //Lent Book Delete
    public void removeLentBook(String code){
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCount ){
            if(Integer.toString(lentBooks.get(intCode-1).getSTT()).equalsIgnoreCase(code.trim())){
                lentBooks.remove(intCode - 1);

                countDownBooks.get(intCode - 1).stopRun();
                countDownBooks.remove(intCode - 1);

                try {
                    lentBook_dataBase.deleteLentBook(connection, code);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                codeCount--;

                for (int i=intCode-1;i<lentBooks.size(); i++ ){
                    int newCode = lentBooks.get(i).getSTT() - 1;
                    lentBooks.get(i).setSTT(newCode);

                    try {
                        lentBook_dataBase.updateLentBook(connection, i+2, 0, String.valueOf(newCode));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    //Edit Lent Book
    public void editDataBase(String STT, int col, String value){
        try {
            lentBook_dataBase.updateLentBook(connection, Integer.parseInt(STT), col, value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editLentBook(String STT, int col, String value){
        for (LentBook lentBook : lentBooks){
            if(Integer.toString(lentBook.getSTT()).equalsIgnoreCase(STT.trim())){
                switch (col){
                    case 1:
                        lentBook.setBookName(value);
                        editDataBase(STT, col, value);
                        break;
                    case 2:
                        lentBook.setNumberOfBook(Integer.parseInt(value));
                        editDataBase(STT, col, value);
                        break;
                    case 3:
                        lentBook.setLentMoney(Long.valueOf(value));
                        editDataBase(STT, col, value);
                        break;
                    case 4:
                        lentBook.dateReConvert(value);
                        editDataBase(STT, col, value);
                        break;
                    case 5:
                        lentBook.setSerialNumber(value);
                        editDataBase(STT, col, value);
                        break;
                    case 6:
                        lentBook.setTimeLate(Long.valueOf(value));
                        editDataBase(STT, col, value);
                        break;
                    case 7:
                        lentBook.setCode(Integer.parseInt(value));
                        editDataBase(STT, col, value);
                        break;

                }
            }
        }
    }

    //Count Down Starter
    public void startCountDown(String userID, LentBookManager lentBookManager, JTable tableBook, UserManager userManager, DefaultTableModel defaultTableModelBook, TableManager tableManager){
        for (CountDownBook countDownBook : countDownBooks){
            countDownBook.run(userID, lentBookManager, tableBook, userManager,defaultTableModelBook,tableManager);
        }
    }

    //Stop Run
    public void stopRun(){
        for (CountDownBook countDownBook : countDownBooks){
            countDownBook.stopRun();
        }
    }
}

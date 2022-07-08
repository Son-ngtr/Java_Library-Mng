package Library.LentBook_Manager;

import Database.ConectionDTB;
import Database.LentBook_DataBase;
import Database.UserBook_info;
import Library.Check;
import Library.Staff_Manager.Staff;
import Library.User_Manager.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.nio.file.StandardWatchEventKinds;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.util.function.BiPredicate;

public class LentBookManager {
    private Check check = new Check();
    LentBook_DataBase lentBook_dataBase;
    private ConectionDTB conectionDTB = new ConectionDTB();
    private Connection connection = conectionDTB.getConnect();
    private int codeCount = 0;
    private String userID;

    //Constructor
    public LentBookManager(String ID){
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
    public LentBook getLentBook(String STT){
        return lentBooks.get(Integer.parseInt(STT) -1 );
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
                    String.valueOf(lentBook.getTimeLate())
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
                    case 2:
                        lentBook.setNumberOfBook(Integer.parseInt(value));
                        editDataBase(STT, col, value);
                    case 3:
                        lentBook.setLentMoney(Long.valueOf(value));
                        editDataBase(STT, col, value);
                    case 4:
                        lentBook.dateReConvert(value);
                        editDataBase(STT, col, value);
                    case 5:
                        lentBook.setSerialNumber(value);
                        editDataBase(STT, col, value);
                    case 6:
                        lentBook.setTimeLate(Long.valueOf(value));
                        editDataBase(STT, col, value);
                }
            }
        }
    }

    //Count Down Starter
    public void startCountDown(LentBookManager lentBookManager, JTable tableBook, UserManager userManager, DefaultTableModel defaultTableModelBook){
        for (CountDownBook countDownBook : countDownBooks){
            countDownBook.run(lentBookManager, tableBook, userManager,defaultTableModelBook);
        }
    }

    //Stop Run
    public void stopRun(){
        for (CountDownBook countDownBook : countDownBooks){
            countDownBook.stopRun();
        }
    }
}

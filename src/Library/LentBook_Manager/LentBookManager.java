package Library.LentBook_Manager;

import Database.ConectionDTB;
import Database.LentBook_DataBase;
import Database.UserBook_info;
import Library.Check;
import Library.Staff_Manager.Staff;

import java.nio.file.StandardWatchEventKinds;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

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

    //LentBook Header
    public String[] lentBookContent(){
        return new String[]{"STT", "Name Book", "Quantity", "LentMoney/Day" ,"Remain Time"};
    }

    //Create lentBook
    public LentBook createLentBook(String bookName, int numberOfBook, Long lentMoney, Calendar endDate, String SerialNumber){
        codeCount++;
        return new LentBook(codeCount, bookName, numberOfBook, lentMoney, endDate, SerialNumber);
    }

    //Add Lent Book
    public void addLentBook(LentBook lentBook){
        lentBooks.add(lentBook);
        try {
            lentBook_dataBase.addNewLentBook(
                    connection,
                    lentBook.getSTT(),
                    lentBook.getBookName(),
                    lentBook.getNumberOfBook(),
                    String.valueOf(lentBook.getLentMoney()),
                    lentBook.dateConvert(),
                    lentBook.getSerialNumber()
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
                    String.valueOf(vector.get(5))
            );
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
}

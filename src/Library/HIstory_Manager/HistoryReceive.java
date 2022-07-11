package Library.HIstory_Manager;

import java.util.Calendar;

public class HistoryReceive {
    private int ID;
    private String name;
    private String phoneNumber;
    private Calendar expDate;
    private String bookName;
    private int quantity;
    private Calendar returnDate;

    //Constructor
    public HistoryReceive(int ID, String name, String phoneNumber, Calendar expDate, String bookName, int quantity, Calendar returnDate) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.expDate = expDate;
        this.bookName = bookName;
        this.quantity = quantity;
        this.returnDate = returnDate;
    }

    //Date convert
    public String dateConvert(Calendar date){
        return date.get(Calendar.DATE) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR);
    }


    //Getter and Setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Calendar getExpDate() {
        return expDate;
    }

    public void setExpDate(Calendar expDate) {
        this.expDate = expDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }
}

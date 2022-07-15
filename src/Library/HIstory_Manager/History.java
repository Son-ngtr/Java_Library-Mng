package Library.HIstory_Manager;

import java.util.Calendar;

public class History {
    private int ID;
    private String name;
    private String phoneNumber;
    private Calendar expDate;
    private String bookName;
    private int quantity;

    //Constructor
    public History(int ID, String name, String phoneNumber, Calendar expDate, String bookName, int quantity) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.expDate = expDate;
        this.bookName = bookName;
        this.quantity = quantity;
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
}

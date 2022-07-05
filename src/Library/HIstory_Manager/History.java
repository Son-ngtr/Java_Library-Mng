package Library.HIstory_Manager;

import java.util.Calendar;

public class History {
    private int ID;
    private String name;
    private String phoneNumber;
    private Calendar regisDate;
    private Calendar expDate;
    private String bookName;
    private String bookType;
    private int quantity;

    public History(int ID, String name, String phoneNumber, Calendar regisDate, Calendar expDate, String bookName,String bookType ,int quantity) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.regisDate = regisDate;
        this.expDate = expDate;
        this.bookName = bookName;
        this.bookType = bookType;
        this.quantity = quantity;
    }

    //Date convert
    public String dateConvert(Calendar date){
        return date.get(Calendar.DATE) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR);
    }

    //DateReConvert
    public void dateReConvert(String dateReConvert, int num){
        Calendar newCalendar = Calendar.getInstance();
        String[] times = dateReConvert.split("/");
        int date = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        int year = Integer.parseInt(times[2]);
        newCalendar.set(year, month - 1 , date);
        switch (num){
            case 1:
                setRegisDate(newCalendar);
                break;
            case 2:
                setExpDate(newCalendar);
                break;
        }
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

    public Calendar getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(Calendar regisDate) {
        this.regisDate = regisDate;
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

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

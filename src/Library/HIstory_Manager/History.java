package Library.HIstory_Manager;

import java.util.Calendar;

public class History {
    private int ID;
    private String name;
    private String phoneNumber;
    private Calendar regisDate;
    private Calendar expDate;
    private String bookName;
    private int quantity;
    private Long fineMoney;
    private String currentTime;

    public History(int ID, String name, String phoneNumber, Calendar regisDate, Calendar expDate, String bookName, int quantity, Long fineMoney) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.regisDate = regisDate;
        this.expDate = expDate;
        this.bookName = bookName;
        this.quantity = quantity;
        this.fineMoney = fineMoney;
    }

    //Date convert
    public String dateConvert(Calendar date){
        return date.get(Calendar.DATE) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR);
    }

    //DateReConvert
    public void dateReConvert(String dateReConvert, boolean a){
        Calendar newCalendar = Calendar.getInstance();
        String[] times = dateReConvert.split("/");
        int date = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        int year = Integer.parseInt(times[2]);
        newCalendar.set(year, month - 1 , date);
        if(a){
            setRegisDate(newCalendar);
        }else {
            setExpDate(newCalendar);
        }
    }

    //Money convert
    public String moneyConvert(){
        String soDu_String = Long.toString(getFineMoney());
        String soDu_String_result = "" ;
        for(int i=soDu_String.length()-1 ; i>=0; i-=3){
            if(i >= 3){
                soDu_String_result = "." + soDu_String.substring(i-2, i+1) + soDu_String_result;
            }else {
                soDu_String_result = soDu_String.substring(0, i+1) + soDu_String_result;
            }
        }
        return soDu_String_result + "VND";
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getFineMoney() {
        return fineMoney;
    }

    public void setFineMoney(Long fineMoney) {
        this.fineMoney = fineMoney;
    }
}

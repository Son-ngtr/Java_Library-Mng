package Library.LentBook_Manager;

import java.util.Calendar;

public class LentBook {
    private int STT;
    private String bookName;
    private int numberOfBook;
    private Long lentMoney;
    private Calendar endDate;
    private String SerialNumber;
    private Long timeLate;
    private int code;

    //Constructor
    public LentBook(int STT, String bookName, int numberOfBook, Long lentMoney, Calendar endDate, String SerialNumber,Long timeLate) {
        this.STT = STT;
        this.bookName = bookName;
        this.numberOfBook = numberOfBook;
        this.lentMoney = lentMoney;
        this.endDate = endDate;
        this.SerialNumber = SerialNumber;
        this.timeLate = timeLate;
    }

    //Getter and Setter
    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getNumberOfBook() {
        return numberOfBook;
    }

    public void setNumberOfBook(int numberOfBook) {
        this.numberOfBook = numberOfBook;
    }

    public Long getLentMoney() {
        return lentMoney;
    }

    public void setLentMoney(Long lentMoney) {
        this.lentMoney = lentMoney;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public Long getTimeLate() {
        return timeLate;
    }

    public void setTimeLate(Long timeLate) {
        this.timeLate = timeLate;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    //Date convert
    public String dateConvert(){
        return getEndDate().get(Calendar.DATE) + "/" + (getEndDate().get(Calendar.MONTH) + 1) + "/" + getEndDate().get(Calendar.YEAR);
    }

    //DateReConvert
    public void dateReConvert(String dateReConvert){
        Calendar newCalendar = Calendar.getInstance();
        String[] times = dateReConvert.split("/");
        int date = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        int year = Integer.parseInt(times[2]);
        newCalendar.set(year, month - 1 , date);
        setEndDate(newCalendar);
    }

    //Money convert
    public String moneyConvert(){
        String soDu_String= Long.toString(getLentMoney());
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

}

package Library.HIstory_Manager;

import java.util.Calendar;

public class HistoryReceive extends History{
    private Calendar returnDate;

    //Constructor
    public HistoryReceive(int ID, String name, String phoneNumber,Calendar expDate, String bookName,int quantity, Calendar returnDate) {
        super(ID, name, phoneNumber, expDate, bookName, quantity);
        this.returnDate = returnDate;

    }

    //Date convert
    public String dateConvert(Calendar date){
        return date.get(Calendar.DATE) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR);
    }


    //Getter and Setter
    public Calendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }
}

package Library.Human.User_Manager;

import Library.Human.Human;
import Library.LentBook_Manager.LentBookManager;

import java.sql.Connection;
import java.util.Calendar;

public class User extends Human {
   private int totalBooks;
   private Long moneyFine;
   //!!
   private Connection connection;
   private LentBookManager lentBookManager;

   //Constructor
   public User(int id, String name, String gender, Calendar dateOfBirth, String address, String phoneNumber, String email, int totalBooks, Long moneyFine) {
       super(id, name, gender, dateOfBirth, address, phoneNumber, email);
        this.totalBooks = totalBooks;
        this.moneyFine = moneyFine;
        lentBookManager = new LentBookManager(connection,String.valueOf(id));
   }

   //Getter and Setter
    public int getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public Long getMoneyFine() {
        return moneyFine;
    }

    public void setMoneyFine(Long moneyFine) {
        this.moneyFine = moneyFine;
    }


    //Date convert
    public String dateConvert(Calendar calendar){
        return calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
    }

    //DateReConvert
    public Calendar dateReConvert(String dateReConvert){
        Calendar newCalendar = Calendar.getInstance();
        String[] times = dateReConvert.split("/");
        int date = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        int year = Integer.parseInt(times[2]);
        newCalendar.set(year, month - 1 , date);
        return newCalendar;
    }

    //Money convert
    public String moneyConvert(){
        String soDu_String = Long.toString(getMoneyFine());
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

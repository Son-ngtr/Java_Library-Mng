package Library.HIstory_Manager;

import java.util.Calendar;

public class HistoryLent extends History {
    private Calendar regisDate;

    public HistoryLent(int ID, String name, String phoneNumber, Calendar regisDate, Calendar expDate, String bookName, int quantity) {
        super(ID, name, phoneNumber, expDate, bookName, quantity);
        this.regisDate = regisDate;
    }

    //Date convert
    public String dateConvert(Calendar date) {
        return date.get(Calendar.DATE) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR);
    }

    //DateReConvert
    public void dateReConvert(String dateReConvert, int num) {
        Calendar newCalendar = Calendar.getInstance();
        String[] times = dateReConvert.split("/");
        int date = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        int year = Integer.parseInt(times[2]);
        newCalendar.set(year, month - 1, date);
        switch (num) {
            case 1:
                setRegisDate(newCalendar);
                break;
            case 2:
                setExpDate(newCalendar);
                break;
        }
    }

    //Getter and Setter
    public Calendar getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(Calendar regisDate) {
        this.regisDate = regisDate;
    }
}


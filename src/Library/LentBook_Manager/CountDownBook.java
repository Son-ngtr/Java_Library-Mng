package Library.LentBook_Manager;

import Library.Check;
import Library.Human.User_Manager.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class CountDownBook {
    private Check check = new Check();
    private Long second;
    private Timer timer;
    private TimerTask timerTask;
    private JTable tableBook;
    private UserManager userManager;
    private DefaultTableModel defaultTableModelBook;
    private LentBookManager lentBookManager;
    private LentBook lentBook;
    private int row, col;

    //Constructor
    public CountDownBook(LentBook lentBook) {
        this.lentBook = lentBook;
        timer = new Timer();
    }

    //Time till midnight
    public Long timeTillMidnight(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (c.getTimeInMillis()-System.currentTimeMillis())/1000;
    }

    //Time till End
    public Long timeTillEnd(Calendar c){
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Long timeTillEnd = (c.getTimeInMillis()-System.currentTimeMillis())/1000;
        return timeTillEnd <= 0 ? 0 : timeTillEnd;
    }

    //Get Time
    public static String getTime(Long totalSecs) {
        Long hours = totalSecs / 3600;
        Long minutes = (totalSecs % 3600) / 60;
        Long seconds = totalSecs % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return timeString;
    }

    //Increase Lent Money
    public void IncreaseLentMoney(){

    }

    public void run(LentBookManager lentBookManager, JTable tableBook, UserManager userManager, DefaultTableModel defaultTableModelBook){
        this.lentBookManager = lentBookManager;
        this.tableBook = tableBook;
        this.userManager = userManager;
        this.defaultTableModelBook = defaultTableModelBook;

        second = timeTillEnd(lentBook.getEndDate());

        if(second != 0){
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    second--;
                    tableBook.setValueAt(getTime(second),lentBook.getSTT()-1, lentBookManager.lentBookContentIndex("Remain Time"));
                    while (second <= -lentBook.getTimeLate()){
                        lentBookManager.editLentBook(String.valueOf(lentBook.getSTT()), 6, String.valueOf(lentBook.getTimeLate() + 86400));
                        IncreaseLentMoney();
                    }
                }
            };
            timer.schedule(timerTask, 0, 1000);
        }else {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    tableBook.setValueAt("--:--:--",lentBook.getSTT()-1, lentBookManager.lentBookContentIndex("Remain Time"));
                }
            };
            timer.schedule(timerTask, 0, 1000);
        }

    }

    public void stopRun(){
        timerTask.cancel();
    }
}

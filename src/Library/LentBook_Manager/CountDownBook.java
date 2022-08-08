package Library.LentBook_Manager;

import Library.Check;
import Library.Human.User_Manager.UserManager;
import Library.Table_Manager.TableManager;

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
    private TableManager tableManager;
    private LentBook lentBook;
    private String userID;
    private boolean borrowType;

    //Constructor
    public CountDownBook(LentBook lentBook) {
        this.lentBook = lentBook;
        timer = new Timer();
    }

    //Time till End
    public Long timeTillEnd(Calendar c){
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Long timeTillEnd = (c.getTimeInMillis()-System.currentTimeMillis())/1000;
        return timeTillEnd;
    }

    //Get Time
    public static String getTime(Long totalSecs) {
        Long hours = totalSecs / 3600;
        Long minutes = (totalSecs % 3600) / 60;
        Long seconds = totalSecs % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return timeString;
    }



    public void run(String userID, LentBookManager lentBookManager, JTable tableBook, UserManager userManager, DefaultTableModel defaultTableModelBook, TableManager tableManager){
        this.userID = userID;
        this.lentBookManager = lentBookManager;
        this.tableBook = tableBook;
        this.userManager = userManager;
        this.defaultTableModelBook = defaultTableModelBook;
        this.tableManager = tableManager;

        //Define BorrowType
        if (tableManager.getSTTByCode(lentBook.getCode()) == 0){
            borrowType = true;
        }else {
            borrowType = false;
        }

        second = timeTillEnd(lentBook.getEndDate());
        if(borrowType){
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    second--;
                    tableBook.setValueAt(getTime(second),lentBook.getSTT()-1, lentBookManager.lentBookContentIndex("Remain Time"));
                    while (second <= -lentBook.getTimeLate()){
                        lentBookManager.editLentBook(String.valueOf(lentBook.getSTT()), 6, String.valueOf(lentBook.getTimeLate() + 86400));
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

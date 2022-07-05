package Library.LentBook_Manager;

import Library.Staff_Manager.StaffManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class CountDownBook {
    private Long second;
    private Long currentTime;
    private Calendar endTime;
    private Timer timer;
    private TimerTask timerTask;
    private JTable table;
    private StaffManager staffManager;
    private DefaultTableModel defaultTableModel;
    private int row, col;

    //Constructor
    public CountDownBook(Calendar endTime, TimerTask timerTask, JTable table, StaffManager staffManager, DefaultTableModel defaultTableModel) {
        timer = new Timer();
        this.second = second;
        this.currentTime = second;
        this.endTime = endTime;
        this.timer = timer;
        this.timerTask = timerTask;
        this.table = table;
        this.staffManager = staffManager;
        this.defaultTableModel = defaultTableModel;
    }

    public void run(){
        timerTask = new TimerTask() {
            @Override
            public void run() {

                currentTime--;
            }
        };
    }
}

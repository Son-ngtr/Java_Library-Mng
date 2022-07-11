package Library.Staff_Manager;

import Library.Staff_Manager.StaffManager;
import Library.User_Manager.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CountDownStaff {

    private Long currentTime;
    private Timer timer;
    private TimerTask timerTask;
    private JTable table;
    private StaffManager staffManager;
    private DefaultTableModel defaultTableModel;


    //Table reset
    public void tableReset(){
        staffManager.setIsUpdate(true);
        defaultTableModel.setDataVector(staffManager.listStaff(), staffManager.staffContent());
        table.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(new JComboBox(staffManager.staffCategory())));
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox(staffManager.staffGender())));
        table.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(new JComboBox(staffManager.staffAttendence())));
        staffManager.setIsUpdate(false);
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

    //Constructor
    public CountDownStaff(JTable table, StaffManager staffManager, DefaultTableModel defaultTableModel){
        this.currentTime = timeTillMidnight();
        this.staffManager = staffManager;
        this.defaultTableModel = defaultTableModel;
        this.table = table;
        timer = new Timer();
        run();
    }

    public void run(){
        tableReset();
        try {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    currentTime--;
                    if(currentTime == -1){
                        staffManager.salaryPayment();
                        tableReset();
                        currentTime = timeTillMidnight();
                    }
                }
            };
            timer.schedule(timerTask, 0, 1000);
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public void stopRun(){
        timerTask.cancel();
    }

    //Get Time Convert
    public String getTime(Long totalSecs) {
        Long hours = totalSecs / 3600;
        Long minutes = (totalSecs % 3600) / 60;
        Long seconds = totalSecs % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return timeString;
    }
}

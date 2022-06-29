package Library_UI.Funtion;

import Database.ConectionDTB;
import Library.Check;
import Library.Book_Manager.BookManager;
import Library.Staff_Manager.StaffManager;
import Library.User_Manager.UserManager;
import Library_UI.Lib_UI.LentBooks_UI;
import Library_UI.Lib_UI.Lobby_UI;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class User_In4_UI {
    private JFrame main_Frame, managerBookFrame;
    private ImageIcon bk_Icon, notepad_Icon, login_Ani, login_ef;
    private JLabel label, notification_Label, login_Icon, logout_Label, exit_Label;
    private JButton button ,b1, b2, b3, b4, b5, b6,b7, bt_lent, bt_delete, bt_info;
    private JTextField txt_1, txt_3, txt_4, txt_5, txt_6;
    private JDatePickerImpl datePicker_staff;
    private DefaultTableModel defaultTableModel;
    private JTable table;
    private UserManager userManager;
    private BookManager bookManager;
    private String code;

    //Constructor
    public User_In4_UI(BookManager bookManager, UserManager userManager, String code){
        this.bookManager = bookManager;
        this.userManager = userManager;
        this.code = code;
        content();
    }

    //Manager User Side
    public void setManagerUser(JFrame frame, DefaultTableModel defaultTableModel, JTable table){
        managerBookFrame = frame;
        this.defaultTableModel = defaultTableModel;
        this.table = table;
    }

    public void content() {

        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/UserIn4_UI.png");
        label = new JLabel(bk_Icon);
        label.setSize(bk_Icon.getIconWidth(), bk_Icon.getIconHeight());

        Font Font_left = new Font("Lucida Calligraphy", Font.PLAIN, 42);
        Font Font_login = new Font("Lucida Calligraphy", Font.PLAIN, 20);
        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_me_3 = new Font("MV Boli", Font.PLAIN, 20);
        Font Font_me_4 = new Font("MV Boli", Font.PLAIN, 14);

        Color Color_1 = new Color(252, 248, 232);
        Color Color_2 = new Color(148, 180, 159);
        Color Color_3 = new Color(250, 183, 61);

// create 3 button
        ImageIcon notification_Icon = new ImageIcon("src/Image_Icon/icon/notification (1).png");
        notification_Label = new JLabel(notification_Icon);
        notification_Label.setSize(80,80);
        notification_Label.setBounds(850,290,45,45);
        notification_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        ImageIcon logout_Icon = new ImageIcon("src/Image_Icon/icon/log-out (1).png");
        logout_Label = new JLabel(logout_Icon);
        logout_Label.setSize(80,80);
        logout_Label.setBounds(845,290+145,45,45);
        logout_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                managerBookFrame.setEnabled(true);
                main_Frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        ImageIcon exit_Icon = new ImageIcon("src/Image_Icon/icon/power (1).png");
        exit_Label = new JLabel(exit_Icon);
        exit_Label.setSize(80,80);
        exit_Label.setBounds(850,290+145*2,45,45);
        exit_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
// create 6 button
        b1 = new JButton("Name");
        b1.setBounds(59, 52, 167, 40);
        b1.setFont(Font_me_3);
        b1.setBorder(BorderFactory.createLineBorder(Color_3));
        b1.setForeground(Color_2);
        b1.setBackground(Color_1);


// create 6 text fields
        txt_1 = new JTextField("name chosen reader");
        txt_1.setBackground(Color_1);
        txt_1.setBounds(277, 52, 470, 40);
        txt_1.setForeground(Color_2);
        txt_1.setBorder(BorderFactory.createLineBorder(Color_3));
        txt_1.setFont(Font_me_3);
        txt_1.setEditable(false);




//create table
        String[][] tableData = {{"hasagi", "3", "29000", "26"}};

        String[] tableColumn = {"name.book", "no", "money", "remain time <day>"};

        JTable jt = new JTable(tableData, tableColumn);
        jt.setBackground(Color_1);
        jt.setForeground(Color_2);
        jt.setFont(Font_me_4);
        jt.setGridColor(Color_3);

        JTableHeader jth = jt.getTableHeader();
        jth.setForeground(Color_3);
        jth.setFont(Font_me_4);
        jth.setBackground(Color_2);

        JScrollPane jsp = new JScrollPane(jt);
        jsp.setBounds(58, 135, 690, 490);
        jsp.setBackground(Color_1);
        jsp.setFont(Font_me_4);
        jsp.setForeground(Color_2);


// create 3 function bt
        bt_lent = new JButton("lent book");
        bt_lent.setForeground(Color_2);
        bt_lent.setBackground(Color_1);
        bt_lent.setFont(Font_me_3);
        bt_lent.setBorder(BorderFactory.createLineBorder(Color_2));
        bt_lent.setBounds(163, 680, 166, 36);
        bt_lent.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LentBooks_UI(bookManager, userManager);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bt_lent.setBackground(Color_2);
                bt_lent.setForeground(Color_3);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_lent.setBackground(Color_1);
                bt_lent.setForeground(Color_2);
            }
        });

        bt_delete = new JButton("delete book");
        bt_delete.setForeground(Color_2);
        bt_delete.setBackground(Color_1);
        bt_delete.setFont(Font_me_3);
        bt_delete.setBorder(BorderFactory.createLineBorder(Color_2));
        bt_delete.setBounds(413, 680, 167, 36);
        bt_delete.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bt_delete.setBackground(Color_2);
                bt_delete.setForeground(Color_3);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_delete.setBackground(Color_1);
                bt_delete.setForeground(Color_2);
            }
        });

        bt_info = new JButton("info_book");
        bt_info.setForeground(Color_2);
        bt_info.setBackground(Color_1);
        bt_info.setFont(Font_me_3);
        bt_info.setBorder(BorderFactory.createLineBorder(Color_2));
        bt_info.setBounds(663, 680, 167, 36);
        bt_info.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bt_info.setBackground(Color_2);
                bt_info.setForeground(Color_3);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_info.setBackground(Color_1);
                bt_info.setForeground(Color_2);
            }
        });




// add all properties on UI
        label.add(b1);

        label.add(txt_1);

        label.add(notification_Label);
        label.add(logout_Label);
        label.add(exit_Label);

        label.add(bt_lent);
        label.add(bt_delete);
        label.add(bt_info);
        label.add(jsp);

        main_Frame = new JFrame("Main_UI");
        main_Frame.add(label);
        main_Frame.setSize(bk_Icon.getIconWidth(), bk_Icon.getIconHeight());
        main_Frame.setResizable(false);
        main_Frame.setLayout(null);
        main_Frame.setDefaultCloseOperation(main_Frame.EXIT_ON_CLOSE);
        main_Frame.setLocationRelativeTo(null);
        main_Frame.setUndecorated(true);
        main_Frame.setVisible(true);
    }

    public static void main(String[] args) {
//        new User_In4_UI();
    }
}

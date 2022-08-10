package Library_UI.Lib_UI;


import Library.Book_Manager.BookManager;
import Library.HIstory_Manager.HistoryManager;
import Library.HIstory_Manager.HistoryReceive_Manager;
import Library.Human.Staff_Manager.StaffManager;
import Library.Human.User_Manager.UserManager;
import Library.Table_Manager.TableManager;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import javax.swing.JFrame;


public class Lobby_UI {
    private JFrame main_Frame;
    private ImageIcon bk_Icon, notepad_Icon, login_Ani, login_ef;
    private JLabel label, notification_Label, login_Icon, logout_Label, exit_Label;
    private JButton button ,b1, b2, b3, b4, b5, b6;
    private JTextField txt_Group, txt_Reader, txt_NoBook, txt_NoBookBorrowed;
    private JButton logIn;
    private JPanel inFo;
    private Connection connection;
    private BookManager bookManager ;
    private UserManager userManager ;
    private StaffManager staffManager;
    private HistoryManager historyManager;
    private HistoryReceive_Manager historyReceive_manager;
    private TableManager tableManager;

    //Constructor
    public Lobby_UI(Connection connection){
        this.connection = connection;
        bookManager = new BookManager(connection);
        userManager = new UserManager(connection);
        staffManager = new StaffManager(connection);
        historyManager = new HistoryManager(connection);
        historyReceive_manager = new HistoryReceive_Manager(connection);
        tableManager = new TableManager(connection);
        content();
    }

    //Dowload Data
    public void downloadData(){
        bookManager.downloadAllBook();
        userManager.downloadAllUser();
        staffManager.downloadAllStaff();
        historyManager.downLoadHistory();
        historyReceive_manager.downloadHistoryReceive();
        tableManager.downloadTable();
    }


    public void content(){
        //Dowload From My SQL
        downloadData();

        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/lobby_1.png");
        label = new JLabel(bk_Icon);
        label.setSize(bk_Icon.getIconWidth(), bk_Icon.getIconHeight());

        ImageIcon notification_Icon = new ImageIcon("src/Image_Icon/icon/notification (1).png");
        notification_Label = new JLabel(notification_Icon);
        notification_Label.setSize(80,80);
        notification_Label.setBounds(1508,876,45,45);
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
        logout_Label.setBounds(1610,876,45,45);
        logout_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main_Frame.dispose();
                new Login_UI();
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
        exit_Label.setBounds(1695,876,45,45);
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

        Font Font_left = new Font("MV Boli", Font.PLAIN, 30);
        Font Font_login = new Font("MV Boli", Font.PLAIN, 20);
        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_me_3 = new Font("MV Boli", Font.ITALIC, 12);

        Color Color_me = new Color(250,183,61);
        Color Color_ForeG = new Color(13,54,57);
        Color Color_ForeG_2 = new Color(236,131,2);
        Color Color_left = new Color(84, 103, 71);


// set up 6 button funtion
        b1 = new JButton("Manage"+" \t" +"books");
        b1.setBounds(612,431,204,100);
        b1.setFont(Font_login);
        b1.setBorder(BorderFactory.createLineBorder(Color_me));
        b1.setForeground(Color_ForeG);
        b1.setBackground(Color_me);
        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ManageBook_UI manageBook_ui = new ManageBook_UI(bookManager);
                manageBook_ui.setLobbySide(main_Frame);
                manageBook_ui.setLobbyInfo(txt_Reader,txt_NoBook,txt_NoBookBorrowed,userManager);
                main_Frame.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                b1.setBackground(Color_ForeG);
                b1.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                b1.setBackground(Color_me);
                b1.setForeground(Color_ForeG);
            }
        });

        b2 = new JButton("Manage"+" \t" +"users");
        b2.setBounds(998,433,204,99);
        b2.setFont(Font_login);
        b2.setBorder(BorderFactory.createLineBorder(Color_me));
        b2.setForeground(Color_ForeG);
        b2.setBackground(Color_me);
        b2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ManageUser_UI manageUser_ui = new ManageUser_UI(bookManager, userManager, historyManager, historyReceive_manager, tableManager);
                manageUser_ui.setLobbySide(main_Frame);
                manageUser_ui.setLobbyInfo(txt_Reader,txt_NoBook,txt_NoBookBorrowed);
                main_Frame.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                b2.setBackground(Color_ForeG);
                b2.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                b2.setBackground(Color_me);
                b2.setForeground(Color_ForeG);
            }
        });

        b3 = new JButton("Manage"+" \t" +"staff");
        b3.setBounds(1385,436,203,99);
        b3.setFont(Font_login);
        b3.setBorder(BorderFactory.createLineBorder(Color_me));
        b3.setForeground(Color_ForeG);
        b3.setBackground(Color_me);
        b3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //ReDownLoad
                staffManager = new StaffManager(connection);
                staffManager.downloadAllStaff();

                //Staff Manager
                ManageStaff_UI manageStaff_ui = new ManageStaff_UI(staffManager);
                manageStaff_ui.setLobbySide(main_Frame);
                manageStaff_ui.setLobbyInfo(txt_Reader,txt_NoBook,txt_NoBookBorrowed, userManager, bookManager);
                main_Frame.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                b3.setBackground(Color_ForeG);
                b3.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                b3.setBackground(Color_me);
                b3.setForeground(Color_ForeG);
            }
        });

        b4 = new JButton("Lent books");
        b4.setBounds(612,666,204,99);
        b4.setFont(Font_login);
        b4.setBorder(BorderFactory.createLineBorder(Color_me));
        b4.setForeground(Color_ForeG);
        b4.setBackground(Color_me);
        b4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LentBooks_UI lentBooks_ui = new LentBooks_UI(bookManager, userManager,historyManager, historyReceive_manager, tableManager);
                lentBooks_ui.setLobbySide(main_Frame);
                lentBooks_ui.setLobbyInfo(txt_Reader,txt_NoBook,txt_NoBookBorrowed);
                main_Frame.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                b4.setBackground(Color_ForeG);
                b4.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                b4.setBackground(Color_me);
                b4.setForeground(Color_ForeG);
            }
        });

        b5 = new JButton("Day _ his");
        b5.setBounds(998,667,204,99);
        b5.setFont(Font_login);
        b5.setBorder(BorderFactory.createLineBorder(Color_me));
        b5.setForeground(Color_ForeG);
        b5.setBackground(Color_me);
        b5.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DayHis_UI dayHis_ui = new DayHis_UI(historyManager, historyReceive_manager);
                dayHis_ui.setLobbySide(main_Frame);
                dayHis_ui.setLobbyInfo(txt_Reader,txt_NoBook,txt_NoBookBorrowed, userManager, bookManager);
                main_Frame.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                b5.setBackground(Color_ForeG);
                b5.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                b5.setBackground(Color_me);
                b5.setForeground(Color_ForeG);
            }
        });

        b6 = new JButton("Table Manager");
        b6.setBounds(1385,669,203,99);
        b6.setFont(Font_login);
        b6.setBorder(BorderFactory.createLineBorder(Color_me));
        b6.setForeground(Color_ForeG);
        b6.setBackground(Color_me);
        b6.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Table_UI table_ui = new Table_UI(tableManager);
                table_ui.setLobbySide(main_Frame);
                main_Frame.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                b6.setBackground(Color_ForeG);
                b6.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                b6.setBackground(Color_me);
                b6.setForeground(Color_ForeG);
            }
        });



        txt_Group = new JTextField("designed by TropicalHorseTeam");
        txt_Group.setBackground(new Color(84, 103, 71));
        txt_Group.setFont(Font_me_3);
        txt_Group.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_Group.setForeground(Color_ForeG);
        txt_Group.setBounds(1200,910,230,20);
        txt_Group.setEditable(false);
        txt_Group.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Team_In4 team_in4 =  new Team_In4();
                team_in4.setLobbySide(main_Frame);
                main_Frame.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                txt_Group.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txt_Group.setForeground(Color_ForeG);
            }
        });

// add 3 properties of book on the left side
        txt_Reader = new JTextField("",362);
        txt_Reader.setText(String.valueOf(userManager.totalUser()));
        txt_Reader.setBackground(Color_left);
        txt_Reader.setFont(Font_left);
        txt_Reader.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_Reader.setForeground(Color_ForeG_2);
        txt_Reader.setBounds(250,353,80,100);
        txt_Reader.setEditable(false);

        txt_NoBook = new JTextField("",362);
        txt_NoBook.setText(String.valueOf(bookManager.numberOfBook()));
        txt_NoBook.setBackground(Color_left);
        txt_NoBook.setFont(Font_left );
        txt_NoBook.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_NoBook.setForeground(Color_ForeG_2);
        txt_NoBook.setBounds(250,550,80,100);
        txt_NoBook.setEditable(false);

        txt_NoBookBorrowed = new JTextField("",362);
        txt_NoBookBorrowed.setText(String.valueOf(userManager.totalBookBorrow()));
        txt_NoBookBorrowed.setBackground(Color_left);
        txt_NoBookBorrowed.setFont(Font_left );
        txt_NoBookBorrowed.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_NoBookBorrowed.setForeground(Color_ForeG_2);
        txt_NoBookBorrowed.setBounds(250,750,80,100);
        txt_NoBookBorrowed.setEditable(false);




// add all properties on UI
        label.add(txt_Group);
        label.add(b1);
        label.add(b2);
        label.add(b3);
        label.add(b4);
        label.add(b5);
        label.add(b6);
        label.add(txt_Reader);
        label.add(txt_NoBook);
        label.add(txt_NoBookBorrowed);
        label.add(notification_Label);
        label.add(logout_Label);
        label.add(exit_Label);

        main_Frame = new JFrame("Main_UI");
        main_Frame.add(label);
        main_Frame.setSize(1794,956);
        main_Frame.setResizable(false);;
        main_Frame.setLayout(null);
        main_Frame.setDefaultCloseOperation(main_Frame.EXIT_ON_CLOSE);
        main_Frame.setLocationRelativeTo(null);
        main_Frame.setUndecorated(true);
        main_Frame.setVisible(true);
    }

    public static void main(String[] args) {
//        new Lobby_UI();
    }
}


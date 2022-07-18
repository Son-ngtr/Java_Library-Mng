package Library_UI.Lib_UI;

import Library.Book_Manager.BookManager;
import Library.HIstory_Manager.HistoryManager;
import Library.HIstory_Manager.HistoryReceive_Manager;
import Library.Human.User_Manager.UserManager;
import Library.Table_Manager.TableManager;
import Library_UI.Funtion.User_In4_UI;
import Library_UI.Funtion.lent_UI;
import Library_UI.Funtion.lent_table_UI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LentBooks_UI {

    private JFrame main_Frame, userInfoFrame, lobbyFrame;
    private JTextField info;
    private JLabel label, notification_Label, logout_Label,exit_Label;
    private BookManager bookManager;
    private DefaultTableModel defaultTableModel, defaultTableModelUser;
    private JTextField txt_Reader,txt_NoBook, txt_NoBookBorrowed ;
    private JTable jt, tableUser;
    private UserManager userManager;
    private String[] tableContent;
    private HistoryManager historyManager;
    private HistoryReceive_Manager historyReceive_manager;
    private JButton op_here, op_home;
    private TableManager tableManager;
    private boolean readPlace = true;

    //Set Lobby Side
    public void setLobbySide(JFrame jFrameLobby){
        lobbyFrame = jFrameLobby;
    }

    //Constructor
    public LentBooks_UI(BookManager bookManager, UserManager userManager, HistoryManager historyManager, HistoryReceive_Manager historyReceive_manager, TableManager tableManager){
        this.bookManager = bookManager;
        this.userManager = userManager;
        this.historyManager = historyManager;
        this.historyReceive_manager = historyReceive_manager;
        this.tableManager = tableManager;
        tableContent = bookManager.bookBorrowContent();
        content();
    }

    //Set lobby info
    public void setLobbyInfo(JTextField txt_Reader, JTextField txt_NoBook, JTextField txt_NoBookBorrowed){
        this.txt_Reader = txt_Reader;
        this.txt_NoBook = txt_NoBook;
        this.txt_NoBookBorrowed = txt_NoBookBorrowed;
    }

    //Reset lobby
    public void resetLobbyInfo(){
        txt_Reader.setText(String.valueOf(userManager.totalUser()));
        txt_NoBook.setText(String.valueOf(bookManager.numberOfBook()));
        txt_NoBookBorrowed.setText(String.valueOf(userManager.totalBookBorrow()));
    }

    //User info Side
    public void setUserI4InfoSide(JFrame frame, DefaultTableModel defaultTableModelUser, JTable tableUser){
        this.userInfoFrame = frame;
        this.defaultTableModelUser = defaultTableModelUser;
        this.tableUser = tableUser;
    }


    public void content(){
        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/_LentBooks_UI_.png");
        label = new JLabel(bk_Icon);
        label.setSize(1794,956);

        Font Font_left = new Font("MV Boli", Font.PLAIN, 16);
//        Font Font_login = new Font("Lucida Calligraphy", Font.PLAIN, 20);
//        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_Brand = new Font("MV Boli", Font.BOLD, 60);
        Font Font_me_3 = new Font("MV Boli", Font.ITALIC, 12);
        Font Font_Table = new Font("MV Boli", Font.PLAIN, 12);

        Color Color_me = new Color(250,183,61);
        Color Color_ForeG = new Color(13,54,57);
//        Color Color_ForeG_2 = new Color(236,131,2);
        Color Color_left = new Color(84, 103, 71);

        ImageIcon notification_Icon = new ImageIcon("src/Image_Icon/icon/notification (1).png");
        notification_Label = new JLabel(notification_Icon);
        notification_Label.setSize(80,80);
        notification_Label.setBounds(1643,700,45,45);
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
        logout_Label.setBounds(1639,765,45,45);
        logout_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(userInfoFrame != null){
                    User_In4_UI user_in4_ui = new User_In4_UI(bookManager, userManager, historyManager,historyReceive_manager , tableManager);
                    user_in4_ui.setManagerUserSide(userInfoFrame,defaultTableModelUser, tableUser);
                    main_Frame.dispose();
                }else {
                    resetLobbyInfo();
                    lobbyFrame.setEnabled(true);
                    main_Frame.dispose();
                }

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
        exit_Label.setBounds(1643,830,45,45);
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


// create table
        //Search Field
        JTextField bookFilter = new JTextField("  ",20);
        bookFilter.setBounds(160, 85, 1475-135, 35);
        bookFilter.setFont(Font_Table);
        bookFilter.setBackground(Color_left);
        bookFilter.setBorder(BorderFactory.createLineBorder(Color_me));
        bookFilter.setForeground(Color_me);


        //Table
        defaultTableModel = new DefaultTableModel(bookManager.listBookBorrow(), tableContent);
        jt = new JTable(defaultTableModel){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jt.getTableHeader().setReorderingAllowed(false);
        jt.setFont(Font_Table);
        jt.setGridColor(Color_ForeG);
        jt.setBackground(Color_me);
        jt.setForeground(Color_ForeG);

        JTableHeader jth = jt.getTableHeader();
        jth.setBackground(Color_ForeG);
        jth.setFont(Font_Table);
        jth.setForeground(Color_me);

        JScrollPane Jsc = new JScrollPane(jt);
        Jsc.setBounds(160, 120, 1475, 750);
        Jsc.setForeground(Color_me);
        Jsc.setFont(Font_Table);

        //Table Search
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jt.getModel());
        jt.setRowSorter(rowSorter);
        bookFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = bookFilter.getText().trim();
                if(text.length() == 0){
                    rowSorter.setRowFilter(null);
                }else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = bookFilter.getText().trim();
                if(text.length() == 0){
                    rowSorter.setRowFilter(null);
                }else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });



// create button lent book
        JButton add_bt = new JButton("lent");
        add_bt.setBounds(102,816,55,55);
        add_bt.setFont(Font_left);
        add_bt.setBorder(BorderFactory.createLineBorder(Color_me));
        add_bt.setForeground(Color_ForeG);
        add_bt.setBackground(Color_me);
        add_bt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(readPlace){
                    try {
                        if(Integer.parseInt(String.valueOf(jt.getValueAt(jt.getSelectedRow(), bookManager.bookBorrowContentIndex("Quantity")))) > 0){
                            if(jt.getSelectedRow() != -1 ){
                                lent_UI lent_ui = new lent_UI(String.valueOf(jt.getValueAt(jt.getSelectedRow(), bookManager.bookBorrowContentIndex("Code"))).trim(), userManager, bookManager,  historyManager);
                                lent_ui.setLentBooksSide(main_Frame,userInfoFrame, defaultTableModel, defaultTableModelUser,tableUser,jt);
                                main_Frame.setEnabled(false);
                            }else {
                                JOptionPane.showMessageDialog(null,"Please chose a book form the list");
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"Run out of book");
                        }
                    }catch (Exception e1){
                        JOptionPane.showMessageDialog(null,"Please chose a book form the list");
                    }
                }else {
                    try {
                        if(Integer.parseInt(String.valueOf(jt.getValueAt(jt.getSelectedRow(), bookManager.bookBorrowContentIndex("Quantity")))) > 0){
                            if(jt.getSelectedRow() != -1 ){
                                lent_table_UI lent_table_ui = new lent_table_UI(String.valueOf(jt.getValueAt(jt.getSelectedRow(), bookManager.bookBorrowContentIndex("Code"))).trim(), userManager, bookManager, tableManager);
                                lent_table_ui.setLentBooksSide(main_Frame,userInfoFrame, defaultTableModel, defaultTableModelUser,tableUser,jt);
                                main_Frame.setEnabled(false);
                            }else {
                                JOptionPane.showMessageDialog(null,"Please chose a book form the list");
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"Run out of book");
                        }
                    }catch (Exception e1){
                        JOptionPane.showMessageDialog(null,"Please chose a book form the list");
                    }
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                add_bt.setBackground(Color_ForeG);
                add_bt.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                add_bt.setBackground(Color_me);
                add_bt.setForeground(Color_ForeG);
            }
        });




        info = new JTextField("BRING TO HOME");
        info.setBounds(1500, 85, 135, 35);
        info.setFont(Font_Table);
        info.setBackground(Color_ForeG);
        info.setBorder(BorderFactory.createLineBorder(Color_me));
        info.setForeground(Color_me);
        info.setEditable(true);

        op_home = new JButton("home");
        op_home.setBounds(102,816-100,55,55);
        op_home.setFont(Font_left);
        op_home.setBorder(BorderFactory.createLineBorder(Color_me));
        op_home.setForeground(Color_ForeG);
        op_home.setBackground(Color_me);

        op_home.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                readPlace = true;
                info.setText("BRING TO HOME");
                op_home.setBackground(Color_ForeG);
                op_home.setForeground(Color_me);
                op_here.setBackground(Color_me);
                op_here.setForeground(Color_ForeG);
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

        op_here = new JButton("here");
        op_here.setBounds(102,816-200,55,55);
        op_here.setFont(Font_left);
        op_here.setBorder(BorderFactory.createLineBorder(Color_me));
        op_here.setForeground(Color_ForeG);
        op_here.setBackground(Color_me);
        op_here.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                readPlace = false;
                info.setText("READ RIGHT HERE");
                op_here.setBackground(Color_ForeG);
                op_here.setForeground(Color_me);
                op_home.setBackground(Color_me);
                op_home.setForeground(Color_ForeG);
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

        op_home.setBackground(Color_ForeG);
        op_home.setForeground(Color_me);
        op_here.setBackground(Color_me);
        op_here.setForeground(Color_ForeG);



// add to frame
        label.add(Jsc);
        label.add(bookFilter);
        label.add(add_bt);
        label.add(notification_Label);
        label.add(logout_Label);
        label.add(exit_Label);

        label.add(op_here);
        label.add(op_home);
        label.add(info);

        main_Frame = new JFrame("_LentBooks_UI_");
        main_Frame.add(label);
        main_Frame.setSize(1794,956);
        main_Frame.setResizable(false);
        main_Frame.setLayout(null);
        main_Frame.setDefaultCloseOperation(main_Frame.EXIT_ON_CLOSE);
        main_Frame.setLocationRelativeTo(null);
        main_Frame.setUndecorated(true);
        main_Frame.setVisible(true);
    }




    public static void main(String[] args) {
//        new LentBooks_UI();
    }
}
package Library_UI.Lib_UI;

import Library.HIstory_Manager.HistoryManager;
import Library.HIstory_Manager.HistoryReceive_Manager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DayHis_Op_UI {
    private JFrame main_Frame, lobbyFrame;
    private JLabel label, notification_Label, logout_Label,exit_Label;
    private JTable jt;
    private DefaultTableModel defaultTableModel;
    private HistoryManager historyManager;
    private HistoryReceive_Manager historyReceive_manager;

    //Constructor
    public DayHis_Op_UI(HistoryManager historyManager, HistoryReceive_Manager historyReceive_manager){
        this.historyManager = historyManager;
        this.historyReceive_manager = historyReceive_manager;
        content();
    }

    //Set Lobby Side
    public void setLobbySide(JFrame jFrameLobby){
        lobbyFrame = jFrameLobby;
    }

    public void content(){
        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/Day_His.png");
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
                lobbyFrame.setEnabled(true);
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

// next + back category
        ImageIcon left_Icon = new ImageIcon("src/Image_Icon/icon/left.png");
        JLabel left_Label = new JLabel(left_Icon);
        left_Label.setSize(45,45);
        left_Label.setBounds(900,876,45,45);
        left_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DayHis_UI dayHis_ui = new DayHis_UI(historyManager, historyReceive_manager);
                dayHis_ui.setLobbySide(lobbyFrame);
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

        ImageIcon right_Icon = new ImageIcon("src/Image_Icon/icon/right.png");
        JLabel right_Label = new JLabel(right_Icon);
        right_Label.setSize(45,45);
        right_Label.setBounds(960,876,45,45);
        right_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DayHis_UI dayHis_ui = new DayHis_UI(historyManager, historyReceive_manager);
                dayHis_ui.setLobbySide(lobbyFrame);
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

// create label
        JLabel head = new JLabel("BookBack.His");
        head.setBounds(725, 60, 1475, 100);
        head.setFont(Font_Brand);
        head.setForeground(Color_me);

// create table
        //Search Field
        JTextField bookFilter = new JTextField("  ",20);
        bookFilter.setBounds(160, 85+40+40, 1475, 35);
        bookFilter.setFont(Font_Table);
        bookFilter.setBackground(Color_left);
        bookFilter.setBorder(BorderFactory.createLineBorder(Color_me));
        bookFilter.setForeground(Color_me);


        //Table
        defaultTableModel = new DefaultTableModel(historyReceive_manager.listHistoryReceive(), historyReceive_manager.historyReceiveContent());
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

        JScrollPane Jsc = new JScrollPane(jt);
        jt.setFont(Font_Table);
        jt.setGridColor(Color_ForeG);
        jt.setBackground(Color_me);
        jt.setForeground(Color_ForeG);
        JTableHeader jth = jt.getTableHeader();
        jth.setBackground(Color_ForeG);
        jth.setFont(Font_Table);
        jth.setForeground(Color_me);

        Jsc.setBounds(160, 160+40, 1475, 600);
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

        label.add(head);
        label.add(notification_Label);
        label.add(logout_Label);
        label.add(exit_Label);
        label.add(Jsc);
        label.add(bookFilter);

        label.add(left_Label);
        label.add(right_Label);

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
//        new DayHis_Op_UI();
    }
}

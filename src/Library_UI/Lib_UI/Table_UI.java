package Library_UI.Lib_UI;

import Library.Book_Manager.BookManager;
import Library.HIstory_Manager.HistoryManager;
import Library.Table_Manager.Table;
import Library.Table_Manager.TableManager;
import Library.User_Manager.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Table_UI {

    private JFrame main_Frame, lobbyFrame;
    private JTable jt;
    private DefaultTableModel defaultTableModel;
    private TableManager tableManager;
    private String code;
    private UserManager userManager;
    private BookManager bookManager;

    //Constructor
    public Table_UI(TableManager tableManager){
        this.tableManager = tableManager;
        content();
    }
    public Table_UI(String code, UserManager userManager , BookManager bookManager){
        this.code = code;
        this.userManager = userManager;
        this.bookManager = bookManager;
    }

    //Set Lobby Side
    public void setLobbySide(JFrame jFrameLobby){
        lobbyFrame = jFrameLobby;
    }

    public void content(){
        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/Table/sfff (1).png");
        JLabel label = new JLabel(bk_Icon);
        label.setSize(1794,956);

        Font Font_left = new Font("MV Boli", Font.PLAIN, 16);
//        Font Font_login = new Font("Lucida Calligraphy", Font.PLAIN, 20);
//        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_Brand = new Font("MV Boli", Font.BOLD, 60);
        Font Font_me_3 = new Font("MV Boli", Font.ITALIC, 15);
        Font Font_Table = new Font("MV Boli", Font.PLAIN, 12);

        Color Color_me = new Color(250,183,61);
        Color Color_ForeG = new Color(13,54,57);
//        Color Color_ForeG_2 = new Color(236,131,2);
        Color Color_left = new Color(84, 103, 71);

        ImageIcon notification_Icon = new ImageIcon("src/Image_Icon/icon/notification (1).png");
        JLabel notification_Label = new JLabel(notification_Icon);
        notification_Label.setSize(80,80);
        notification_Label.setBounds(1508,876+12,45,45);
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
        JLabel logout_Label = new JLabel(logout_Icon);
        logout_Label.setSize(80,80);
        logout_Label.setBounds(1610,876+12,45,45);
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
        JLabel exit_Label = new JLabel(exit_Icon);
        exit_Label.setSize(80,80);
        exit_Label.setBounds(1695,876+12,45,45);
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

        ImageIcon table_Icon = new ImageIcon("src/Image_Icon/background/Table/dinner-table (3).png");
        ImageIcon table_full_Icon = new ImageIcon("src/Image_Icon/background/Table/dinner-table-full.png");

// create table area in left of Frame
        JLabel[] table_LB = new JLabel[20];
        JLabel[] table_Name = new JLabel[20];


        //Line 1
        for (int i = 0; i < 5; i++){
            int y = 90;
            int t = i;

            table_LB[i] = new JLabel();
            table_LB[i].setSize(75,75);
            table_LB[i].setBounds(1207+ (t-2)*200, y, 75, 75);

            table_Name[i] = new JLabel("table " + (i+1));
            table_Name[i].setBounds(1207+ (t-2)*200 + 15, y+65, 75, 75);
            table_Name[i].setFont(Font_me_3);
            table_Name[i].setForeground(Color_ForeG);

            //Set enable
            if(userManager != null){
                if(tableManager.checkUsedTable(i)){
                    table_LB[i].setIcon(table_full_Icon);
                    table_Name[i].setForeground(Color_me);
                }else {
                    table_LB[i].setIcon(table_Icon);
                    table_Name[i].setForeground(Color_ForeG);
                }

                //Table Action
                int finalI = i;
                table_LB[i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(table_LB[finalI].getIcon().equals(table_full_Icon)){
                            JOptionPane.showMessageDialog(null,"Table " + finalI + " Is Full");
                        }else {
                            if (JOptionPane.showConfirmDialog(null, "Chose table " + finalI +". Are you sure ?",  "Table",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                table_LB[finalI].setIcon(table_full_Icon);
                                table_Name[finalI].setForeground(Color_me);

                                //Input Value
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

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }else {
                table_LB[i].setIcon(table_Icon);
            }

            //Add To Label
            label.add(table_Name[i]);
            label.add(table_LB[i]);
        }

        //Line 2
        for (int i = 5; i < 10; i++){
            int y = 90+200;
            int t = i-5;

            table_LB[i] = new JLabel();
            table_LB[i].setSize(75,75);
            table_LB[i].setBounds(1207+ (t-2)*200, y, 75, 75);

            table_Name[i] = new JLabel("table " + (i+1));
            table_Name[i].setBounds(1207+ (t-2)*200 + 15, y+65, 75, 75);
            table_Name[i].setFont(Font_me_3);
            table_Name[i].setForeground(Color_ForeG);

            if(userManager != null){
                if(tableManager.checkUsedTable(i)){
                    table_LB[i].setIcon(table_full_Icon);
                    table_Name[i].setForeground(Color_me);
                }else {
                    table_LB[i].setIcon(table_Icon);
                    table_Name[i].setForeground(Color_ForeG);
                }

                //Table Action
                int finalI = i;
                table_LB[i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(table_LB[finalI].getIcon().equals(table_full_Icon)){
                            table_LB[finalI].setIcon(table_Icon);
                            table_Name[finalI].setForeground(Color_ForeG);
                        }else {
                            table_LB[finalI].setIcon(table_full_Icon);
                            table_Name[finalI].setForeground(Color_me);
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
            }else {
                table_LB[i].setIcon(table_Icon);
            }

            label.add(table_Name[i]);
            label.add(table_LB[i]);
        }

        //Line 3
        for (int i = 10; i < 15; i++){
            int y = 90+200+200;
            int t = i-10;

            table_LB[i] = new JLabel();
            table_LB[i].setSize(75,75);
            table_LB[i].setBounds(1207+ (t-2)*200, y, 75, 75);

            table_Name[i] = new JLabel("table " + (i+1));
            table_Name[i].setBounds(1207+ (t-2)*200 + 15, y+65, 75, 75);
            table_Name[i].setFont(Font_me_3);
            table_Name[i].setForeground(Color_ForeG);

            if(userManager != null){
                if(tableManager.checkUsedTable(i)){
                    table_LB[i].setIcon(table_full_Icon);
                    table_Name[i].setForeground(Color_me);
                }else {
                    table_LB[i].setIcon(table_Icon);
                    table_Name[i].setForeground(Color_ForeG);
                }

                //Table Action
                int finalI = i;
                table_LB[i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(table_LB[finalI].getIcon().equals(table_full_Icon)){
                            table_LB[finalI].setIcon(table_Icon);
                            table_Name[finalI].setForeground(Color_ForeG);
                        }else {
                            table_LB[finalI].setIcon(table_full_Icon);
                            table_Name[finalI].setForeground(Color_me);
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
            }else {
                table_LB[i].setIcon(table_Icon);
            }

            label.add(table_Name[i]);
            label.add(table_LB[i]);
        }

        //Line 4
        for (int i = 15; i < 20; i++){
            int y = 90+200+200+200;
            int t = i-15;

            table_LB[i] = new JLabel();
            table_LB[i].setSize(75,75);
            table_LB[i].setBounds(1207+ (t-2)*200, y, 75, 75);

            table_Name[i] = new JLabel("table " + (i+1));
            table_Name[i].setBounds(1207+ (t-2)*200 + 15, y+65, 75, 75);
            table_Name[i].setFont(Font_me_3);
            table_Name[i].setForeground(Color_ForeG);

            if(userManager != null){
                if(tableManager.checkUsedTable(i)){
                    table_LB[i].setIcon(table_full_Icon);
                    table_Name[i].setForeground(Color_me);
                }else {
                    table_LB[i].setIcon(table_Icon);
                    table_Name[i].setForeground(Color_ForeG);
                }

                //Table Action
                int finalI = i;
                table_LB[i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(table_LB[finalI].getIcon().equals(table_full_Icon)){
                            table_LB[finalI].setIcon(table_Icon);
                            table_Name[finalI].setForeground(Color_ForeG);
                        }else {
                            table_LB[finalI].setIcon(table_full_Icon);
                            table_Name[finalI].setForeground(Color_me);
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
            }else {
                table_LB[i].setIcon(table_Icon);
            }

            label.add(table_Name[i]);
            label.add(table_LB[i]);
        }


// create information table of used table

        defaultTableModel = new DefaultTableModel(tableManager.listTable(), tableManager.tableContent());
        jt = new JTable(defaultTableModel){
            public boolean isCellEditable(int row, int column) {
                if (column == tableManager.tableContentIndex("STT") || column == tableManager.tableContentIndex("Book Name") || column == tableManager.tableContentIndex("Quantity")) return false;
                return true;
            }
        };

        jt.setFont(Font_Table);
        jt.setGridColor(Color_ForeG);
        jt.setBackground(Color_me);
        jt.setForeground(Color_ForeG);
        JTableHeader jth = jt.getTableHeader();
        jth.setBackground(Color_ForeG);
        jth.setFont(Font_Table);
        jth.setForeground(Color_me);


        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(20, 20, 679, 918);
        sp.setForeground(Color_me);
        sp.setFont(Font_Table);


        label.add(sp);
        label.add(notification_Label);
        label.add(logout_Label);
        label.add(exit_Label);

        main_Frame = new JFrame("Main_UI");
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
    }
}

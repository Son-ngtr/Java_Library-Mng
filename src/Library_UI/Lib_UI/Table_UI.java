package Library_UI.Lib_UI;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Table_UI {

    private JFrame main_Frame;

    public Table_UI(){
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
                main_Frame.dispose();
                new Lobby_UI();
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
        JLabel table_Label = new JLabel(table_Icon);
        table_Label.setSize(75,75);
        table_Label.setBounds(1100, 500, 75, 75);


// create table area in left of Frame
        JLabel table_Area = new JLabel();

        JLabel[] table_LB = new JLabel[20];
        JLabel[] table_Name = new JLabel[20];
        for (int i = 0; i < 5; i++){
            int y = 90;
            int t = i;
            table_LB[i] = new JLabel(table_Icon);
            table_LB[i].setSize(75,75);
            table_LB[i].setBounds(1207+ (t-2)*200, y, 75, 75);

            table_Name[i] = new JLabel("table " + (i+1));
            table_Name[i].setBounds(1207+ (t-2)*200 + 15, y+65, 75, 75);
            table_Name[i].setFont(Font_me_3);
            table_Name[i].setForeground(Color_ForeG);
            int finalI = i;
            table_Name[i].addMouseListener(new MouseListener() {
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
                    table_Name[finalI].setForeground(Color_me);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    table_Name[finalI].setForeground(Color_ForeG);
                }
            });

            label.add(table_Name[i]);
            label.add(table_LB[i]);
        }

        for (int i = 5; i < 10; i++){
            int y = 90+200;
            int t = i-5;
            table_LB[i] = new JLabel(table_Icon);
            table_LB[i].setSize(75,75);
            table_LB[i].setBounds(1207+ (t-2)*200, y, 75, 75);

            table_Name[i] = new JLabel("table " + (i+1));
            table_Name[i].setBounds(1207+ (t-2)*200 + 15, y+65, 75, 75);
            table_Name[i].setFont(Font_me_3);
            table_Name[i].setForeground(Color_ForeG);
            int finalI = i;
            table_Name[i].addMouseListener(new MouseListener() {
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
                    table_Name[finalI].setForeground(Color_me);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    table_Name[finalI].setForeground(Color_ForeG);
                }
            });

            label.add(table_Name[i]);
            label.add(table_LB[i]);
        }

        for (int i = 10; i < 15; i++){
            int y = 90+200+200;
            int t = i-10;
            table_LB[i] = new JLabel(table_Icon);
            table_LB[i].setSize(75,75);
            table_LB[i].setBounds(1207+ (t-2)*200, y, 75, 75);

            table_Name[i] = new JLabel("table " + (i+1));
            table_Name[i].setBounds(1207+ (t-2)*200 + 15, y+65, 75, 75);
            table_Name[i].setFont(Font_me_3);
            table_Name[i].setForeground(Color_ForeG);
            int finalI = i;
            table_Name[i].addMouseListener(new MouseListener() {
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
                    table_Name[finalI].setForeground(Color_me);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    table_Name[finalI].setForeground(Color_ForeG);
                }
            });

            label.add(table_Name[i]);
            label.add(table_LB[i]);
        }

        for (int i = 15; i < 20; i++){
            int y = 90+200+200+200;
            int t = i-15;
            table_LB[i] = new JLabel(table_Icon);
            table_LB[i].setSize(75,75);
            table_LB[i].setBounds(1207+ (t-2)*200, y, 75, 75);

            table_Name[i] = new JLabel("table " + (i+1));
            table_Name[i].setBounds(1207+ (t-2)*200 + 15, y+65, 75, 75);
            table_Name[i].setFont(Font_me_3);
            table_Name[i].setForeground(Color_ForeG);
            int finalI = i;
            table_Name[i].addMouseListener(new MouseListener() {
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
                    table_Name[finalI].setForeground(Color_me);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    table_Name[finalI].setForeground(Color_ForeG);
                }
            });

            label.add(table_Name[i]);
            label.add(table_LB[i]);
        }


// create information table of used table
        String[][] data = {
                { "Kundan Kumar Jha", "1", "Lucas Journeys", "1" },
                { "Anand Jha", "1", "Moon Space", "2" }
        };

        // Column Names
        String[] columnNames = { "Name", "Table", "Lent_Book", "Quantity" };

        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        j.setFont(Font_Table);
        j.setGridColor(Color_ForeG);
        j.setBackground(Color_me);
        j.setForeground(Color_ForeG);
        JTableHeader jth = j.getTableHeader();
        jth.setBackground(Color_ForeG);
        jth.setFont(Font_Table);
        jth.setForeground(Color_me);


        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
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
        new Table_UI();
    }
}

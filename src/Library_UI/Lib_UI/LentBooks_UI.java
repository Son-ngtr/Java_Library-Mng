package Library_UI.Lib_UI;

import Library_UI.Funtion.lent_UI;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LentBooks_UI {

    private JFrame main_Frame;
    private JLabel label, notification_Label, logout_Label,exit_Label;
    public LentBooks_UI(){
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
        JTextField bookFilter = new JTextField("  ",20);
        bookFilter.setBounds(160, 85, 1475, 35);
        bookFilter.setFont(Font_Table);
        bookFilter.setBackground(Color_left);
        bookFilter.setBorder(BorderFactory.createLineBorder(Color_me));
        bookFilter.setForeground(Color_me);


        String[][] tableData = {{"1", "one piece", "20.000", "Oda Eiichiro", "Kim Dong", "Anime", "26"}};

        String[] tableColumn = {"code", "name", "price", "author", "publisher", "category", "quantity"};

        JTable jt = new JTable(tableData, tableColumn);

        JScrollPane Jsc = new JScrollPane(jt);
        jt.setFont(Font_Table);
        jt.setGridColor(Color_ForeG);
        jt.setBackground(Color_me);
        jt.setForeground(Color_ForeG);
        JTableHeader jth = jt.getTableHeader();
        jth.setBackground(Color_ForeG);
        jth.setFont(Font_Table);
        jth.setForeground(Color_me);

        Jsc.setBounds(160, 120, 1475, 750);
        Jsc.setForeground(Color_me);
        Jsc.setFont(Font_Table);



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
                new lent_UI();
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



// add to frame
        label.add(Jsc);
        label.add(bookFilter);
        label.add(add_bt);
        label.add(notification_Label);
        label.add(logout_Label);
        label.add(exit_Label);

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
        new LentBooks_UI();
    }
}

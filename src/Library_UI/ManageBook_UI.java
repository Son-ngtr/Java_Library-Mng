package Library_UI;

import Library_UI.Funtion.Addbook_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ManageBook_UI {
    private JFrame main_Frame;
    private ImageIcon bk_Icon;
    private JLabel label, notification_Label, logout_Label, exit_Label;
    private JButton bt_add, bt_remove, bt_search;
    private JTextField txt_Group;
    private JLabel brand;
    public ManageBook_UI(){
        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/_Book_UI_.png");
        label = new JLabel(bk_Icon);
        label.setSize(1794,956);

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

        Font Font_left = new Font("MV Boli", Font.PLAIN, 16);
//        Font Font_login = new Font("Lucida Calligraphy", Font.PLAIN, 20);
//        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_Brand = new Font("MV Boli", Font.BOLD, 60);
        Font Font_me_3 = new Font("MV Boli", Font.ITALIC, 12);

        Color Color_me = new Color(250,183,61);
        Color Color_ForeG = new Color(13,54,57);
//        Color Color_ForeG_2 = new Color(236,131,2);
        Color Color_left = new Color(84, 103, 71);

        txt_Group = new JTextField("designed by TropicalHorseTeam");
        txt_Group.setBackground(new Color(84, 103, 71));
        txt_Group.setFont(Font_me_3);
        txt_Group.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_Group.setForeground(Color_ForeG);
        txt_Group.setBounds(1200,910,230,20);
        txt_Group.setEditable(false);

// create brand
        brand = new JLabel("Books Management");
        brand.setBounds(750, 30, 900, 100);
        brand.setFont(Font_Brand);
        brand.setForeground(Color_me);
// create 3 button -> function
        bt_add = new JButton("Add book");
        bt_add.setBounds(75,287+8,190,42);
        bt_add.setFont(Font_left);
        bt_add.setBorder(BorderFactory.createLineBorder(Color_me));
        bt_add.setForeground(Color_me);
        bt_add.setBackground(Color_left);
        bt_add.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Addbook_UI();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bt_add.setBackground(Color_ForeG);
                bt_add.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_add.setBackground(Color_me);
                bt_add.setForeground(Color_ForeG);
            }
        });

        bt_remove = new JButton("Remove book");
        bt_remove.setBounds(75,399+8,190,42);
        bt_remove.setFont(Font_left);
        bt_remove.setBorder(BorderFactory.createLineBorder(Color_me));
        bt_remove.setForeground(Color_me);
        bt_remove.setBackground(Color_left);
        bt_remove.addMouseListener(new MouseListener() {
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
                bt_remove.setBackground(Color_ForeG);
                bt_remove.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_remove.setBackground(Color_me);
                bt_remove.setForeground(Color_ForeG);
            }
        });

        bt_search = new JButton("???");
        bt_search.setBounds(75,511+8,190,42);
        bt_search.setFont(Font_left);
        bt_search.setBorder(BorderFactory.createLineBorder(Color_me));
        bt_search.setForeground(Color_me);
        bt_search.setBackground(Color_left);
        bt_search.addMouseListener(new MouseListener() {
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
                bt_search.setBackground(Color_ForeG);
                bt_search.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_search.setBackground(Color_me);
                bt_search.setForeground(Color_ForeG);
            }
        });


// add all properties on UI
        label.add(brand);
        label.add(txt_Group);
        label.add(notification_Label);
        label.add(logout_Label);
        label.add(exit_Label);
        label.add(bt_add);
        label.add(bt_remove);
        label.add(bt_search);

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
        new ManageBook_UI();
    }
}

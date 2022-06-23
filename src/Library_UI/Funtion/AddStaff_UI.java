package Library_UI.Funtion;

import Library.Check;
import Library.Book_Manager.BookManager;
import Library.Staff_Manager.StaffManager;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class AddStaff_UI {
    private JFrame main_Frame, managerBookFrame;
    private ImageIcon bk_Icon, notepad_Icon, login_Ani, login_ef;
    private JLabel label, notification_Label, login_Icon, logout_Label, exit_Label;
    private JButton button ,b1, b2, b3, b4, b5, b6, bt_save, bt_exit, bt_reset;
    private JTextField txt_1, txt_2, txt_3, txt_4, txt_5, txt_6;
    private JButton logIn;
    private JPanel inFo;
    private StaffManager staffManager;
    private DefaultTableModel defaultTableModel;
    private String job[] = {"Sanitation worker", "Treasurer", "Librarian"};
    private JComboBox cb = new JComboBox(job);
    private JTable table;
    private Check check = new Check();

    //Manager Staff Side
    public void setManagerUser(JFrame frame, StaffManager staffManager, DefaultTableModel defaultTableModel, JTable table){
        managerBookFrame = frame;
        this.staffManager = staffManager;
        this.defaultTableModel = defaultTableModel;
        this.table = table;
    }


    public AddStaff_UI(){
        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/Add_UI.png");
        label = new JLabel(bk_Icon);
        label.setSize(bk_Icon.getIconWidth(), bk_Icon.getIconHeight());

        Font Font_left = new Font("Lucida Calligraphy", Font.PLAIN, 42);
        Font Font_login = new Font("Lucida Calligraphy", Font.PLAIN, 20);
        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_me_3 = new Font("MV Boli", Font.ITALIC, 20);

        Color Color_me = new Color(250,183,61);
        Color Color_ForeG = new Color(13,54,57);
        Color Color_ForeG_2 = new Color(236,131,2);
        Color Color_left = new Color(84, 103, 71);

// width and height of txt
        int width = 176;
        int height = 30;
        int po_x = 59;
        int po_y = 60;
// create 6 button
        b1 = new JButton("name");
        b1.setBounds(po_x, po_y, width, height);
        b1.setFont(Font_me_3);
        b1.setBorder(BorderFactory.createLineBorder(Color_me));
        b1.setForeground(Color_me);
        b1.setBackground(Color_left);

        b2 = new JButton("gender");
        b2.setBounds(po_x, po_y+65, width, height);
        b2.setFont(Font_me_3);
        b2.setBorder(BorderFactory.createLineBorder(Color_me));
        b2.setForeground(Color_me);
        b2.setBackground(Color_left);

        b3 = new JButton("date.birth");
        b3.setBounds(po_x, po_y+65*2, width, height);
        b3.setFont(Font_me_3);
        b3.setBorder(BorderFactory.createLineBorder(Color_me));
        b3.setForeground(Color_me);
        b3.setBackground(Color_left);

        b4 = new JButton("address");
        b4.setBounds(po_x, po_y+65*3, width, height);
        b4.setFont(Font_me_3);
        b4.setBorder(BorderFactory.createLineBorder(Color_me));
        b4.setForeground(Color_me);
        b4.setBackground(Color_left);

        b5 = new JButton("phone.No");
        b5.setBounds(po_x, po_y+65*4, width, height);
        b5.setFont(Font_me_3);
        b5.setBorder(BorderFactory.createLineBorder(Color_me));
        b5.setForeground(Color_me);
        b5.setBackground(Color_left);

        b6 = new JButton("email");
        b6.setBounds(po_x, po_y+65*5, width, height);
        b6.setFont(Font_me_3);
        b6.setBorder(BorderFactory.createLineBorder(Color_me));
        b6.setForeground(Color_me);
        b6.setBackground(Color_left);

        JButton b7 = new JButton("staff");
        b7.setBounds(po_x, po_y+65*6, width, height);
        b7.setFont(Font_me_3);
        b7.setBorder(BorderFactory.createLineBorder(Color_me));
        b7.setForeground(Color_me);
        b7.setBackground(Color_left);

//        JButton b8 = new JButton("???");
//        b8.setBounds(po_x, po_y+65*7, width, height);
//        b8.setFont(Font_me_3);
//        b8.setBorder(BorderFactory.createLineBorder(Color_me));
//        b8.setForeground(Color_me);
//        b8.setBackground(Color_left);



// create 6 text fields
        txt_1 = new JTextField();
        txt_1.setBackground(Color_left);
        txt_1.setBounds(283, po_y, 337, height);
        txt_1.setForeground(Color_me);
        txt_1.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_1.setFont(Font_me_3);


        txt_2 = new JTextField();
        txt_2.setBackground(Color_left);
        txt_2.setBounds(283, po_y+65, 337, height);
        txt_2.setForeground(Color_me);
        txt_2.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_2.setFont(Font_me_3);


        txt_3 = new JTextField();
        txt_3.setBackground(Color_left);
        txt_3.setBounds(283, po_y+65*2, 337, height);
        txt_3.setForeground(Color_me);
        txt_3.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_3.setFont(Font_me_3);


        txt_4 = new JTextField();
        txt_4.setBackground(Color_left);
        txt_4.setBounds(283, po_y+65*3, 337, height);
        txt_4.setForeground(Color_me);
        txt_4.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_4.setFont(Font_me_3);


        txt_5 = new JTextField();
        txt_5.setBackground(Color_left);
        txt_5.setBounds(283, po_y+65*4, 337, height);
        txt_5.setForeground(Color_me);
        txt_5.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_5.setFont(Font_me_3);


        txt_6 = new JTextField();
        txt_6.setBackground(Color_left);
        txt_6.setBounds(283, po_y+65*5, 337, height);
        txt_6.setForeground(Color_me);
        txt_6.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_6.setFont(Font_me_3);

        cb.setBackground(Color_left);
        cb.setBounds(283, po_y+65*6, 337, height);
        cb.setForeground(Color_me);
        cb.setBorder(BorderFactory.createLineBorder(Color_me));
        cb.setFont(Font_me_3);

//        JTextField txt_8 = new JTextField();
//        txt_8.setBackground(Color_left);
//        txt_8.setBounds(283, po_y+65*7, 337, height);
//        txt_8.setForeground(Color_me);
//        txt_8.setBorder(BorderFactory.createLineBorder(Color_me));
//        txt_8.setFont(Font_me_3);

// create 3 function bt
        bt_save = new JButton("save");
        bt_save.setForeground(Color_ForeG);
        bt_save.setBackground(Color_left);
        bt_save.setFont(Font_me_3);
        bt_save.setBorder(BorderFactory.createLineBorder(Color_ForeG));
        bt_save.setBounds(46, 582+32, 175, 39);
        bt_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean inputCheck = true;
                String type = String.valueOf(cb.getItemAt(cb.getSelectedIndex()));
                switch (type){
                    case "Learning Book":
                    case "Noval Book":
                    case "Children Book":
                    case "Psychological Book":
                }


//                if (inputCheck){
//                    createNewBook(txt_1.getText().trim(), Long.parseLong(bookManager.moneyConvert(bookManager.matConvert(bookManager.mathAnalysis(txt_2.getText().trim()))) , txt_3.getText().trim(), txt_4.getText().trim(), type , Integer.parseInt(bookManager.matConvert(bookManager.mathAnalysis(txt_6.getText().trim()))) );
//                    txt_1.setText("");
//                    txt_2.setText("");
//                    txt_3.setText("");
//                    txt_4.setText("");
//                    txt_6.setText("");
//                    tableReset();
//                    JOptionPane.showMessageDialog(null, "Tạo Sách mới thành công!!!");
//                }
            }
        });

        bt_exit = new JButton("exit");
        bt_exit.setForeground(Color_ForeG);
        bt_exit.setBackground(Color_left);
        bt_exit.setFont(Font_me_3);
        bt_exit.setBorder(BorderFactory.createLineBorder(Color_ForeG));
        bt_exit.setBounds(255, 582+32, 175, 39);
        bt_exit.addMouseListener(new MouseListener() {
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

        bt_reset = new JButton("reset");
        bt_reset.setForeground(Color_ForeG);
        bt_reset.setBackground(Color_left);
        bt_reset.setFont(Font_me_3);
        bt_reset.setBorder(BorderFactory.createLineBorder(Color_ForeG));
        bt_reset.setBounds(464, 582+32, 176, 39);
        bt_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_1.setText("");
                txt_2.setText("");
                txt_3.setText("");
                txt_4.setText("");
                txt_5.setText("");
                txt_6.setText("");
            }
        });

// set combo box
//        cb.setEnabled(false);

// add all properties on UI
        label.add(b1);
        label.add(b2);
        label.add(b3);
        label.add(b4);
        label.add(b5);
        label.add(b6);
        label.add(b7);
//        label.add(b8);

        label.add(txt_1);
        label.add(txt_2);
        label.add(txt_3);
        label.add(txt_4);
        label.add(txt_5);
        label.add(txt_6);
        label.add(cb);
//        label.add(txt_8);

        label.add(bt_save);
        label.add(bt_exit);
        label.add(bt_reset);

        main_Frame = new JFrame("Main_UI");
        main_Frame.add(label);
        main_Frame.setSize(bk_Icon.getIconWidth(),bk_Icon.getIconHeight());
        main_Frame.setResizable(false);
        main_Frame.setLayout(null);
        main_Frame.setDefaultCloseOperation(main_Frame.EXIT_ON_CLOSE);
        main_Frame.setLocationRelativeTo(null);
        main_Frame.setUndecorated(true);
        main_Frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AddStaff_UI();
    }
}

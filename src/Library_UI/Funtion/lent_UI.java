package Library_UI.Funtion;

import Library.Book_Manager.BookManager;
import Library.Check;
import Library.User_Manager.UserManager;
import Library_UI.Lib_UI.ManageUser_UI;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class lent_UI {
    private UserManager userManager;
    private JFrame main_Frame, lentBookFrame;
    private ImageIcon bk_Icon, notepad_Icon, login_Ani, login_ef;
    private JLabel label, notification_Label, login_Icon, logout_Label, exit_Label;
    private JButton button ,b1, b2, b3, b4, b5, b6, bt_save, bt_exit, bt_reset;
    private JTextField txt_1, txt_2, txt_3, txt_4, txt_5, txt_6, txt_8;
    private JButton logIn;
    private JPanel inFo;
    private BookManager bookManager;
    private DefaultTableModel defaultTableModelBook, defaultTableModelUser;
    private JComboBox gd;
    private JTable table;
    private Check check = new Check();
    private JDatePickerImpl datePicker, datePicker_birth;
    private String codeNumber;
    private String codeLetter;

    //Constructor
    public lent_UI(String code, UserManager userManager ,BookManager bookManager){
        this.userManager = userManager;
        this.bookManager = bookManager;
        codeNumber = check.codeConvert(code);
        codeLetter = check.codeLetter(code);
        gd = new JComboBox(userManager.userGender());
        content();
    }

    //Lent Books Side
    public void setLentBooksSide(JFrame frame ,DefaultTableModel defaultTableModelBook, DefaultTableModel defaultTableModelUser, JTable table){
        lentBookFrame = frame;
        this.defaultTableModelBook = defaultTableModelBook;
        this.defaultTableModelUser = defaultTableModelUser;
        this.table = table;
    }

    //Table Book Reset
    public void tableBookReset(){
        bookManager.setIsUpdate(true);
        defaultTableModelBook.setDataVector(bookManager.listBookBorrow(), bookManager.bookBorrowContent());
        userManager.setIsUpdate(false);
    }

    //Table User Reset
    public void tableUserReset(){
        userManager.setIsUpdate(true);
        defaultTableModelUser.setDataVector(userManager.listUser(), userManager.userContent());
        table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JComboBox(userManager.userGender())));
        userManager.setIsUpdate(false);
    }

    //Check Common Value
    public boolean checkValue(boolean inputCheck){
        if(txt_1.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Name Reader");
            inputCheck = false;
        }else
        {
            if(false){
                JOptionPane.showMessageDialog(null, "gender");
                inputCheck = false;
            }  else
            {
                if(txt_3.getText().trim().length() == 0){
                    JOptionPane.showMessageDialog(null, "Address");
                    inputCheck = false;
                }else
                {
                    if(txt_4.getText().trim().length() == 0){
                        JOptionPane.showMessageDialog(null, "Phone Number");
                        inputCheck = false;
                    }else
                    {
                        if(txt_5.getText().trim().length() == 0){
                            JOptionPane.showMessageDialog(null, "Email");
                            inputCheck = false;
                        }else
                        {
                            if(false){
                                JOptionPane.showMessageDialog(null, "Regis Date");
                                inputCheck = false;
                            }else {
                                if(datePicker.getJFormattedTextField().getText().length() == 0){
                                    JOptionPane.showMessageDialog(null, "Regis EXP");
                                    inputCheck = false;
                                }else {
                                    if (txt_8.getText().trim().length() == 0 || !check.isLong(txt_8.getText().trim())){
                                        JOptionPane.showMessageDialog(null, "Book Quantity");
                                        inputCheck = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return inputCheck;
    }

// get time
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();

    public void content(){
        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/Add_UI.png");
        label = new JLabel(bk_Icon);
        label.setSize(bk_Icon.getIconWidth(), bk_Icon.getIconHeight());

        Font Font_left = new Font("Lucida Calligraphy", Font.PLAIN, 42);
        Font Font_login = new Font("Lucida Calligraphy", Font.PLAIN, 20);
        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_me_3 = new Font("MV Boli", Font.PLAIN, 20);

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
        b1 = new JButton("name reader");
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

        b3 = new JButton("address");
        b3.setBounds(po_x, po_y+65*2, width, height);
        b3.setFont(Font_me_3);
        b3.setBorder(BorderFactory.createLineBorder(Color_me));
        b3.setForeground(Color_me);
        b3.setBackground(Color_left);

        b4 = new JButton("phone.No");
        b4.setBounds(po_x, po_y+65*3, width, height);
        b4.setFont(Font_me_3);
        b4.setBorder(BorderFactory.createLineBorder(Color_me));
        b4.setForeground(Color_me);
        b4.setBackground(Color_left);

        b5 = new JButton("email");
        b5.setBounds(po_x, po_y+65*4, width, height);
        b5.setFont(Font_me_3);
        b5.setBorder(BorderFactory.createLineBorder(Color_me));
        b5.setForeground(Color_me);
        b5.setBackground(Color_left);

        b6 = new JButton("regis.Date");
        b6.setBounds(po_x, po_y+65*5, width, height);
        b6.setFont(Font_me_3);
        b6.setBorder(BorderFactory.createLineBorder(Color_me));
        b6.setForeground(Color_me);
        b6.setBackground(Color_left);

        JButton b7 = new JButton("regis.Exp");
        b7.setBounds(po_x, po_y+65*6, width, height);
        b7.setFont(Font_me_3);
        b7.setBorder(BorderFactory.createLineBorder(Color_me));
        b7.setForeground(Color_me);
        b7.setBackground(Color_left);

        JButton b8 = new JButton("quantity.Books");
        b8.setBounds(po_x, po_y+65*7, width, height);
        b8.setFont(Font_me_3);
        b8.setBorder(BorderFactory.createLineBorder(Color_me));
        b8.setForeground(Color_me);
        b8.setBackground(Color_left);



// create 6 text fields
        txt_1 = new JTextField();
        txt_1.setBackground(Color_left);
        txt_1.setBounds(283, po_y, 337, height);
        txt_1.setForeground(Color_me);
        txt_1.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_1.setFont(Font_me_3);


        gd.setBackground(Color_left);
        gd.setBounds(283, po_y+65, 337, height);
        gd.setForeground(Color_me);
        gd.setBorder(BorderFactory.createLineBorder(Color_me));
        gd.setFont(Font_me_3);


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


        txt_6 = new JTextField(dtf.format(now));
        txt_6.setBackground(Color_left);
        txt_6.setBounds(283, po_y+65*5, 337, height);
        txt_6.setForeground(Color_me);
        txt_6.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_6.setFont(Font_me_3);
        txt_6.setEditable(false);


        txt_8 = new JTextField();
        txt_8.setBackground(Color_left);
        txt_8.setBounds(283, po_y+65*7, 337, height);
        txt_8.setForeground(Color_me);
        txt_8.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_8.setFont(Font_me_3);

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
                checkValue(inputCheck);
                Long quantityBorrow = Long.valueOf(txt_8.getText().trim());
                Long quantity = Long.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 6)).trim());
                Long result = quantity - quantityBorrow;
                if(inputCheck && result>=0){
                    //Add User
                    userManager.addUser(
                            userManager.createUser(
                                    txt_1.getText().trim(),
                                    (String) gd.getItemAt(gd.getSelectedIndex()),
                                    check.dateReConvert(datePicker_birth.getJFormattedTextField().getText()) ,
                                    txt_3.getText().trim(),
                                    txt_4.getText().trim(),
                                    txt_5.getText().trim(),
                                    check.dateReConvert(txt_6.getText().trim()),
                                    check.dateReConvert(datePicker.getJFormattedTextField().getText()) ,
                                     0L
                            )
                    );
                    if(defaultTableModelUser != null){
                        tableUserReset();
                    }

                    //Fix Quantity Of Book
                    switch (codeLetter){
                        case "C":
                            bookManager.editBookChild(codeNumber, 7, String.valueOf(result));
                            tableBookReset();
                            break;
                        case "N":
                            bookManager.editBookNoval(codeNumber, 7, String.valueOf(result));
                            tableBookReset();
                            break;
                        case "P":
                            bookManager.editBookPsychology(codeNumber, 7, String.valueOf(result));
                            tableBookReset();
                            break;
                        case "L":
                            bookManager.editBookLearning(codeNumber, 7, String.valueOf(result));
                            tableBookReset();
                            break;
                    }

                    //Exit Lent UI
                    lentBookFrame.setEnabled(true);
                    main_Frame.dispose();
                }
                if(result < 0 ){
                    JOptionPane.showMessageDialog(null, "Số lượng sách mượn quá lớn");
                }
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
                lentBookFrame.setEnabled(true);
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
//        gd.setEnabled(false);
//        datePicker.getJFormattedTextField().getText();

// add calendar
        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.day", "Day");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl panel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(panel, new JFormattedTextField.AbstractFormatter() {
            @Override
            public String valueToString(Object value) throws ParseException {
                if(value != null){
                Calendar cal = (Calendar) value;
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String strDate = format.format(cal.getTime());
                return strDate;}
                return "";
            }

            @Override
            public Object stringToValue(String text) throws ParseException {
                return "";
            }

        });

        datePicker.setBounds(283, po_y+65*6, 337, height);
        datePicker.setBackground(Color_left);
        datePicker.setForeground(Color_me);
        datePicker.setFont(Font_me_3);
        datePicker.getJFormattedTextField().setBackground(Color_left);
        datePicker.getJFormattedTextField().setFont(Font_me_3);
        datePicker.getJFormattedTextField().setForeground(Color_me);
        datePicker.getJFormattedTextField().setBorder(BorderFactory.createLineBorder(Color_me));


// create date of birth
// create button
        JButton b_bir = new JButton("date.birth");
        b_bir.setBounds(po_x, po_y+65*8, width, height);
        b_bir.setFont(Font_me_3);
        b_bir.setBorder(BorderFactory.createLineBorder(Color_me));
        b_bir.setForeground(Color_me);
        b_bir.setBackground(Color_left);
// create txt field
        SqlDateModel model_birth = new SqlDateModel();
        Properties p_birth = new Properties();
        p_birth.put("text.day", "Day");
        p_birth.put("text.month", "Month");
        p_birth.put("text.year", "Year");
        JDatePanelImpl panel_birth = new JDatePanelImpl(model, p);
        datePicker_birth = new JDatePickerImpl(panel, new JFormattedTextField.AbstractFormatter() {
            @Override
            public String valueToString(Object value) throws ParseException {
                if(value != null){
                    Calendar cal_1 = (Calendar) value;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    String strDate = format.format(cal_1.getTime());


                    return strDate;}

                return "";
            }

            @Override
            public Object stringToValue(String text) throws ParseException {
                return "";
            }

        });

        datePicker_birth.setBounds(283, po_y+65*8, 337, height);
        datePicker_birth.setBackground(Color_left);
        datePicker_birth.setForeground(Color_me);
        datePicker_birth.setFont(Font_me_3);
        datePicker_birth.getJFormattedTextField().setBackground(Color_left);
        datePicker_birth.getJFormattedTextField().setFont(Font_me_3);
        datePicker_birth.getJFormattedTextField().setForeground(Color_me);
        datePicker_birth.getJFormattedTextField().setBorder(BorderFactory.createLineBorder(Color_me));


// add all properties on UI
        label.add(b1);
        label.add(b2);
        label.add(b3);
        label.add(b4);
        label.add(b5);
        label.add(b6);
        label.add(b7);
        label.add(b8);

        label.add(txt_1);
        label.add(gd);
        label.add(txt_3);
        label.add(txt_4);
        label.add(txt_5);
        label.add(txt_6);
//        label.add(txt_7);
        label.add(txt_8);

        label.add(bt_save);
        label.add(bt_exit);
        label.add(bt_reset);
        label.add(datePicker);


        label.add(b_bir);
        label.add(datePicker_birth);

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
//        new lent_UI();
    }

}

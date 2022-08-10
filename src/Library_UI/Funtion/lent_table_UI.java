package Library_UI.Funtion;

import Library.Book_Manager.BookManager;
import Library.Check;
import Library.Human.User_Manager.User;
import Library.Human.User_Manager.UserManager;
import Library.LentBook_Manager.LentBookManager;
import Library.Table_Manager.TableManager;
import Library_UI.Lib_UI.Table_UI;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class lent_table_UI {
    private UserManager userManager;
    private BookManager bookManager;
    private JFrame main_Frame, lentBookFrame, userFrame;
    private JLabel label;
    private JButton button ,b1, b2, b3, b4, b5, b6,b7,b8, bt_save, bt_exit, bt_reset;
    private JTextField txt_1, txt_2, txt_3, txt_4, txt_5, txt_6,txt_7, txt_8;
    private JComboBox gd;
    private Check check = new Check();
    private JDatePickerImpl datePicker_birth;
    private DefaultTableModel defaultTableModelUser, defaultTableModelBook;
    private JTable tableUser, tableBook;
    private String codeNumber;
    private String codeLetter;
    private String code;
    private TableManager tableManager;
    private String readerName;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String regisDate, expDate, dateOfBirth;
    private int quantityBorrow;
    private int quantity;
    private int result;
    private LentBookManager lentBookManager;

    //Constructor
    public lent_table_UI(String code, UserManager userManager , BookManager bookManager, TableManager tableManager){
        this.code = code;
        codeNumber = check.codeConvert(code);
        codeLetter = check.codeLetter(code);
        this.userManager = userManager;
        this.bookManager = bookManager;
        this.tableManager = tableManager;
        gd = new JComboBox(userManager.userGender());
        content();
    }

    //Lent Books Side
    public void setLentBooksSide(JFrame frameLentBook, JFrame frameUser , DefaultTableModel defaultTableModelBook, DefaultTableModel defaultTableModelUser, JTable tableUser, JTable tableBook){
        lentBookFrame = frameLentBook;
        this.userFrame = frameUser;
        this.defaultTableModelBook = defaultTableModelBook;
        this.defaultTableModelUser = defaultTableModelUser;
        this.tableUser = tableUser;
        this.tableBook = tableBook;
    }

    //Check Common Value
    public boolean checkValue(){
        boolean inputCheck = true;
        if(txt_1.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Name Reader");
            inputCheck = false;
        }else
        {
            if(false){
                JOptionPane.showMessageDialog(null, "gender");
                inputCheck = false;
            }else {
                if (txt_3.getText().trim().length() == 0){
                    JOptionPane.showMessageDialog(null, "Address");
                    inputCheck = false;
                }else {
                    if (txt_4.getText().trim().length() == 0){
                        JOptionPane.showMessageDialog(null, "Phone Number");
                        inputCheck = false;
                    }else {
                        if (txt_5.getText().trim().length() == 0){
                            JOptionPane.showMessageDialog(null, "Email");
                            inputCheck = false;
                        }else {
                            if(txt_8.getText().trim().length() == 0 || !check.mathCheck(check.mathAnalysis(txt_6.getText().trim())) || Integer.parseInt(txt_8.getText()) <= 0){
                                JOptionPane.showMessageDialog(null, "Quantity");
                                inputCheck = false;
                            }
                        }
                    }
                }
            }
        }
        return inputCheck;
    }

    //Table User Reset
    public void tableUserReset(){
        userManager.setIsUpdate(true);
        defaultTableModelUser.setDataVector(userManager.listUser(), userManager.userContent());
        tableUser.getColumnModel().getColumn(userManager.userContentIndex("Gender")).setCellEditor(new DefaultCellEditor(new JComboBox(userManager.userGender())));
        userManager.setIsUpdate(false);
    }

    //Table Book Reset
    public void tableBookReset(){
        bookManager.setIsUpdate(true);
        defaultTableModelBook.setDataVector(bookManager.listBookBorrow(), bookManager.bookBorrowContent());
        bookManager.setIsUpdate(false);
    }

    //Fix quantity of book
    public void fixQuantityOfBook(int result){
        switch (codeLetter){
            case "C":
                bookManager.editBookChild(codeNumber, bookManager.bookContentChildrenIndex("Quantity"), String.valueOf(result));
                tableBookReset();
                break;
            case "N":
                bookManager.editBookNoval(codeNumber, bookManager.bookContentNovelIndex("Quantity"), String.valueOf(result));
                tableBookReset();
                break;
            case "P":
                bookManager.editBookPsychology(codeNumber, bookManager.bookContentPsychologyIndex("Quantity"), String.valueOf(result));
                tableBookReset();
                break;
            case "L":
                bookManager.editBookLearning(codeNumber, bookManager.bookContentLearningIndex("Quantity"), String.valueOf(result));
                tableBookReset();
                break;
        }
    }
    //
    public void lentBookDTB(User user, int quantityBorrow){
        lentBookManager = userManager.getLentBookManager(String.valueOf(user.getId()));
        userManager.addLentBook(
                lentBookManager,
                lentBookManager.createLentBook(
                        String.valueOf(tableBook.getValueAt(tableBook.getSelectedRow(), bookManager.bookBorrowContentIndex("Name"))),
                        quantityBorrow,
                        fineMoneyCalc(quantityBorrow),
                        check.dateReConvert(expDate),
                        String.valueOf(tableBook.getValueAt(tableBook.getSelectedRow(), bookManager.bookBorrowContentIndex("Serial Number")))
                ));
    }

    //Fine Mony Calc
    public Long fineMoneyCalc(int quantityBorrow){
        Long dayTillEnd = Long.valueOf(check.dateReConvert(expDate).get(Calendar.DATE) - check.dateReConvert(regisDate).get(Calendar.DATE));
        Long lentMoneyPlus = dayTillEnd * Long.valueOf(quantityBorrow) * Long.valueOf(check.moneyConvert(String.valueOf(tableBook.getValueAt(tableBook.getSelectedRow(), bookManager.bookBorrowContentIndex("Price"))))) / 10;
        return lentMoneyPlus;
    }

    //Fix total book of User
    public void fixtotalBook(User user, int quantityBorrow){
        userManager.editUser(String.valueOf(user.getId()) , userManager.userContentIndex("Total Books"), String.valueOf(user.getTotalBooks() + quantityBorrow));
    }

    //Fix Fine Money Of User
    public void fixFineMoney(User user,int quantityBorrow){
        Long lentMoneyPlus = fineMoneyCalc(quantityBorrow);
        userManager.editUser(
                String.valueOf(user.getId()),
                userManager.userContentIndex("Fine Money"),
                String.valueOf(user.getMoneyFine() + lentMoneyPlus)
        );
    }

    //Add Table To Table Manager
    public void addTableToTableManager(User user, int finalI){
        tableManager.addTable(
                tableManager.createTable(
                        readerName,
                        finalI,
                        String.valueOf(tableBook.getValueAt(tableBook.getSelectedRow(), bookManager.bookBorrowContentIndex("Name"))),
                        quantityBorrow,
                        userManager.getLentBookCode()
                )
        );
    }

    //Refresh
    public void refresh(){
        txt_1.setText("");
        txt_3.setText("");
        txt_6.setText("");
    }

    //Exit Frame
    public void exitFrame(){
        lentBookFrame.setEnabled(true);
        main_Frame.dispose();
    }


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
// get time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
// width and height of txt
        int width = 176;
        int height = 30;
        int po_x = 59;
        int po_y = 30;
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

        b7 = new JButton("regis.Exp");
        b7.setBounds(po_x, po_y+65*6, width, height);
        b7.setFont(Font_me_3);
        b7.setBorder(BorderFactory.createLineBorder(Color_me));
        b7.setForeground(Color_me);
        b7.setBackground(Color_left);

        b8 = new JButton("quantity.Books");
        b8.setBounds(po_x, po_y+65*7, width, height);
        b8.setFont(Font_me_3);
        b8.setBorder(BorderFactory.createLineBorder(Color_me));
        b8.setForeground(Color_me);
        b8.setBackground(Color_left);




// create 6 text fields
        if(userManager.getUseLentInfo() != null){
            txt_1 = new JTextField(userManager.getUseLentInfo()[1]);
            txt_1.setEnabled(false);
        }else {
            txt_1 = new JTextField();
        }
        txt_1.setBackground(Color_left);
        txt_1.setBounds(283, po_y, 337, height);
        txt_1.setForeground(Color_me);
        txt_1.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_1.setFont(Font_me_3);

        if(userManager.getUseLentInfo() != null){
            switch (userManager.getUseLentInfo()[2]){
                case "Male":
                    gd.setSelectedIndex(0);
                    gd.setEnabled(false);
                case "Female":
                    gd.setSelectedIndex(1);
                    gd.setEnabled(false);
                case "Other":
                    gd.setSelectedIndex(2);
                    gd.setEnabled(false);
            }
        }
        gd.setBackground(Color_left);
        gd.setBounds(283, po_y+65, 337, height);
        gd.setForeground(Color_me);
        gd.setBorder(BorderFactory.createLineBorder(Color_me));
        gd.setFont(Font_me_3);


        if(userManager.getUseLentInfo() != null){
            System.out.println("sdfsdf");
            txt_3 = new JTextField(userManager.getUseLentInfo()[4]);
            txt_3.setEnabled(false);
        }else {
            txt_3 = new JTextField();
        }
        txt_3.setBackground(Color_left);
        txt_3.setBounds(283, po_y+65*2, 337, height);
        txt_3.setForeground(Color_me);
        txt_3.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_3.setFont(Font_me_3);


        if(userManager.getUseLentInfo() != null){
            txt_4 = new JTextField(userManager.getUseLentInfo()[5]);
            txt_4.setEnabled(false);
        }else {
            txt_4 = new JTextField();
        }
        txt_4.setBackground(Color_left);
        txt_4.setBounds(283, po_y+65*3, 337, height);
        txt_4.setForeground(Color_me);
        txt_4.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_4.setFont(Font_me_3);


        if(userManager.getUseLentInfo() != null){
            txt_5 = new JTextField(userManager.getUseLentInfo()[6]);
            txt_5.setEnabled(false);
        }else {
            txt_5 = new JTextField();
        }
        txt_5.setBackground(Color_left);
        txt_5.setBounds(283, po_y+65*4, 337, height);
        txt_5.setForeground(Color_me);
        txt_5.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_5.setFont(Font_me_3);


        txt_6 = new JTextField(dtf.format(now));
        txt_6.getText().toString();
        txt_6.setBackground(Color_left);
        txt_6.setBounds(283, po_y+65*5, 337, height);
        txt_6.setForeground(Color_me);
        txt_6.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_6.setFont(Font_me_3);
        txt_6.setEditable(false);

        txt_7 = new JTextField(dtf.format(now));
        txt_7.getText().toString();
        txt_7.setBackground(Color_left);
        txt_7.setBounds(283, po_y+65*6, 337, height);
        txt_7.setForeground(Color_me);
        txt_7.setBorder(BorderFactory.createLineBorder(Color_me));
        txt_7.setFont(Font_me_3);
        txt_7.setEditable(false);

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
                //Value
                if(checkValue()){
                    //Valuation
                    readerName = txt_1.getText().trim();
                    gender = String.valueOf(gd.getItemAt(gd.getSelectedIndex()));
                    dateOfBirth = datePicker_birth.getJFormattedTextField().getText();
                    address = txt_3.getText().trim();
                    phoneNumber = txt_4.getText().trim();
                    email = txt_5.getText().trim();
                    regisDate = txt_6.getText();
                    expDate = txt_7.getText();
                    quantityBorrow = Integer.parseInt(txt_8.getText().trim());
                    quantity = Integer.parseInt(String.valueOf(tableBook.getValueAt(tableBook.getSelectedRow(), bookManager.bookBorrowContentIndex("Quantity"))).trim());
                    result = quantity - quantityBorrow;

                    //Action
                    if(userManager.getUseLentInfo() == null || userManager.getUser(Integer.parseInt(userManager.getUseLentInfo()[0])).getDeskNumber() == 0){
                        if(quantity - quantityBorrow >=0){
                            Table_UI table_ui = new Table_UI(code, userManager, bookManager,tableManager);
                            table_ui.setLentBooksSide(main_Frame,lentBookFrame,userFrame,defaultTableModelBook, defaultTableModelUser,tableUser, tableBook);
                            table_ui.setUserInput(
                                    readerName,
                                    gender,
                                    address,
                                    phoneNumber,
                                    email,
                                    regisDate,
                                    expDate,
                                    dateOfBirth,
                                    quantityBorrow,
                                    quantity
                            );
                            main_Frame.setEnabled(false);
                        }
                    }else {
                        User user;
                        if(userManager.getUseLentInfo() == null){
                            //Add User
                            user = userManager.createUser(
                                    readerName,
                                    gender,
                                    check.dateReConvert(dateOfBirth),
                                    address,
                                    phoneNumber,
                                    email,
                                    0,
                                    0L

                            );
                            userManager.addUser(user);

                            userManager.addLentBookManager();
                            lentBookDTB(user, quantityBorrow);
                        }else {
                            if(defaultTableModelUser != null){
                                user = userManager.getUser(Integer.parseInt(userManager.getUseLentInfo()[0]));
                                lentBookDTB(user, quantityBorrow);
                            }else {
                                user = userManager.getUser(userManager.totalUser());
                                lentBookDTB(user, quantityBorrow);
                            }
                        }
                        //Add To Table Manager
                        addTableToTableManager(user,user.getDeskNumber());
                        //Fix Total Books of User
                        fixtotalBook(user, quantityBorrow);
                        //Fix Fine Money of User
                        fixFineMoney(user,quantityBorrow);

                        //Reset User Table
                        if (defaultTableModelUser != null){
                            tableUserReset();
                        }

                        //Fix Quantity Of Book
                        fixQuantityOfBook(result);

                        //Exit Table UI
                        exitFrame();

                        //Continue or Not
                        if(defaultTableModelUser == null){
                            if (JOptionPane.showConfirmDialog(null, "Continue Adding ?", "Lent Books",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                if(userManager.getUseLentInfo() == null){
                                    String userID = Integer.toString(user.getId());
                                    String userName = readerName;
                                    String userGender = gender;
                                    String userDateOfBirth = dateOfBirth;
                                    String userAddress = address;
                                    String userPhoneNumber = phoneNumber;
                                    String userEmail = email;
                                    userManager.setUseLentInfo(new String[]{
                                            userID,
                                            userName,
                                            userGender,
                                            userDateOfBirth,
                                            userAddress,
                                            userPhoneNumber,
                                            userEmail
                                    });
                                }
                            } else {
                                userManager.setUseLentInfo(null);
                            }
                        }
                    }
                }
            }
        });


        bt_exit = new JButton("exit");
        bt_exit.setForeground(Color_ForeG);
        bt_exit.setBackground(Color_left);
        bt_exit.setFont(Font_me_3);
        bt_exit.setBorder(BorderFactory.createLineBorder(Color_ForeG));
        bt_exit.setBounds(255, 582+32, 175, 39);
        bt_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(defaultTableModelUser == null){
                    userManager.setUseLentInfo(null);
                }
                exitFrame();
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
                refresh();
            }
        });

        JButton b_bir = new JButton("date.birth");
        b_bir.setBounds(po_x, po_y+65*8, width, height);
        b_bir.setFont(Font_me_3);
        b_bir.setBorder(BorderFactory.createLineBorder(Color_me));
        b_bir.setForeground(Color_me);
        b_bir.setBackground(Color_left);

        SqlDateModel model_birth = new SqlDateModel();
        Properties p_birth = new Properties();
        p_birth.put("text.day", "Day");
        p_birth.put("text.month", "Month");
        p_birth.put("text.year", "Year");
        JDatePanelImpl panel_birth = new JDatePanelImpl(model_birth, p_birth);
        if(userManager.getUseLentInfo() != null){
            datePicker_birth = new JDatePickerImpl(panel_birth, new JFormattedTextField.AbstractFormatter() {
                @Override
                public Object stringToValue(String text) throws ParseException {
                    return "";
                }

                @Override
                public String valueToString(Object value) throws ParseException {
                    return userManager.getUseLentInfo()[3];
                }
            });
            datePicker_birth.setEnabled(false);
        }else {
            datePicker_birth = new JDatePickerImpl(panel_birth, new JFormattedTextField.AbstractFormatter() {
                @Override
                public String valueToString(Object value) throws ParseException {
                    if(value != null){
                        Calendar cal_1 = (Calendar) value;
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = format.format(cal_1.getTime());
                        return strDate;}

                    return "";
                }

                @Override
                public Object stringToValue(String text) throws ParseException {
                    return "";
                }

            });
        }


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
        label.add(txt_7);
        label.add(txt_8);

        label.add(bt_save);
        label.add(bt_exit);
        label.add(bt_reset);

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
//        new lent_table_UI();
    }

}
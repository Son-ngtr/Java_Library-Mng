package Library_UI.Lib_UI;

import Library.Book_Manager.BookManager;
import Library.Check;
import Library.HIstory_Manager.HistoryManager;
import Library.Human.User_Manager.User;
import Library.Human.User_Manager.UserManager;
import Library.LentBook_Manager.LentBookManager;
import Library.Table_Manager.Table;
import Library.Table_Manager.TableManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

public class Table_UI {

    private Check check = new Check();
    private JFrame main_Frame, lobbyFrame, lentFrame, lenBookFrame, userFrame;
    private DefaultTableModel defaultTableModelBook, defaultTableModelUser;
    private JTable jt, tableUser, tableBook;
    private DefaultTableModel defaultTableModel;
    private TableManager tableManager;
    private String code;
    private UserManager userManager;
    private BookManager bookManager;
    private LentBookManager lentBookManager;
    private String codeNumber;
    private String codeLetter;
    private String readerName;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String regisDate, expDate, dateOfBirth;
    private int quantityBorrow;
    private int quantity;
    private int result;

    //Constructor
    public Table_UI(TableManager tableManager){
        this.tableManager = tableManager;
        content();
    }
    public Table_UI(String code, UserManager userManager , BookManager bookManager, TableManager tableManager){
        this.code = code;
        codeNumber = check.codeConvert(code);
        codeLetter = check.codeLetter(code);
        this.userManager = userManager;
        this.bookManager = bookManager;
        this.tableManager = tableManager;

        content();
    }

    //Lent Books Side
    public void setLentBooksSide(JFrame frameLent,JFrame lenBookFrame ,JFrame frameUser , DefaultTableModel defaultTableModelBook, DefaultTableModel defaultTableModelUser, JTable tableUser, JTable tableBook){
        lentFrame = frameLent;
        this.userFrame = frameUser;
        this.lenBookFrame = lenBookFrame;
        this.defaultTableModelBook = defaultTableModelBook;
        this.defaultTableModelUser = defaultTableModelUser;
        this.tableUser = tableUser;
        this.tableBook = tableBook;
    }
    //Set User Input
    public void setUserInput(String readerName, String gender,String address, String phoneNumber,String email, String regisDate, String expDate,String dateOfBirth, int quantityBorrow, int quantity){
        this.readerName = readerName;
        this.gender = gender;
        this.address =address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.regisDate = regisDate;
        this.expDate = expDate;
        this.dateOfBirth = dateOfBirth;
        this.quantityBorrow = quantityBorrow;
        this.quantity = quantity;
        result = quantity - quantityBorrow;
    }

    //Add Lent Book
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
        userManager.setIsUpdate(false);
    }

    //Exit Frame
    public void exitFrame(){
        lenBookFrame.setEnabled(true);
        lentFrame.dispose();
        main_Frame.dispose();
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

    //Add Table To Table Manager
    public void addTableToTableManager(User user, int finalI){
        userManager.editUser(String.valueOf(user.getId()),  9, String.valueOf(finalI+1));
        tableManager.addTable(
                tableManager.createTable(
                        readerName,
                        finalI+1,
                        String.valueOf(tableBook.getValueAt(tableBook.getSelectedRow(), bookManager.bookBorrowContentIndex("Name"))),
                        quantityBorrow,
                        userManager.getLentBookCode()
                )
        );
    }

    //Fine Mony Calc
    public Long fineMoneyCalc(int quantityBorrow){
        Long dayTillEnd = Long.valueOf(check.dateReConvert(expDate).get(Calendar.DATE) - check.dateReConvert(regisDate).get(Calendar.DATE));
        Long lentMoneyPlus = dayTillEnd * Long.valueOf(quantityBorrow) * Long.valueOf(check.moneyConvert(String.valueOf(tableBook.getValueAt(tableBook.getSelectedRow(), bookManager.bookBorrowContentIndex("Price"))))) / 10;
        return lentMoneyPlus;
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
                if(userManager == null){
                    lobbyFrame.setEnabled(true);
                }else {
                    lentFrame.setEnabled(true);
                }
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
            if(bookManager != null){
                //Fill Table Color
                if(tableManager.checkUsedTable(i+1)){
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
                            JOptionPane.showMessageDialog(null,"Table " + (finalI+1) + " Is Full");
                        }else {
                            if (JOptionPane.showConfirmDialog(null, "Chose table " + (finalI+1) +". Are you sure ?",  "Table",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                                //Set Color
                                table_LB[finalI].setIcon(table_full_Icon);
                                table_Name[finalI].setForeground(Color_me);

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

                                    //LentBook DTB
                                    userManager.addLentBookManager();
                                    lentBookDTB(user, quantityBorrow);
                                }else {
                                    if(defaultTableModelUser != null){
                                        user = userManager.getUser(Integer.parseInt(userManager.getUseLentInfo()[0]));

                                        //Lent Book DTB
                                        lentBookDTB(user, quantityBorrow);
                                    }else {
                                        user = userManager.getUser(userManager.totalUser());

                                        //LentBook DTB
                                        lentBookDTB(user, quantityBorrow);
                                    }
                                }

                                //Add To Table Manager
                                addTableToTableManager(user,finalI);
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
                if(tableManager.checkUsedTable(i+1)){
                    table_LB[i].setIcon(table_full_Icon);
                    table_Name[i].setForeground(Color_me);
                }else {
                    table_LB[i].setIcon(table_Icon);
                    table_Name[i].setForeground(Color_ForeG);
                }
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
                //Fill Table Color
                if(tableManager.checkUsedTable(i+1)){
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
                            JOptionPane.showMessageDialog(null,"Table " + (finalI+1) + " Is Full");
                        }else {
                            if (JOptionPane.showConfirmDialog(null, "Chose table " + (finalI+1) +". Are you sure ?",  "Table",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                                //Set Color
                                table_LB[finalI].setIcon(table_full_Icon);
                                table_Name[finalI].setForeground(Color_me);

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

                                    //LentBook DTB
                                    userManager.addLentBookManager();
                                    lentBookDTB(user, quantityBorrow);
                                }else {
                                    if(defaultTableModelUser != null){
                                        user = userManager.getUser(Integer.parseInt(userManager.getUseLentInfo()[0]));

                                        //Lent Book DTB
                                        lentBookDTB(user, quantityBorrow);
                                    }else {
                                        user = userManager.getUser(userManager.totalUser());

                                        //LentBook DTB
                                        lentBookDTB(user, quantityBorrow);
                                    }
                                }

                                //Add To Table Manager
                                addTableToTableManager(user,finalI);
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
                if(tableManager.checkUsedTable(i+1)){
                    table_LB[i].setIcon(table_full_Icon);
                    table_Name[i].setForeground(Color_me);
                }else {
                    table_LB[i].setIcon(table_Icon);
                    table_Name[i].setForeground(Color_ForeG);
                }
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
                //Fill Table Color
                if(tableManager.checkUsedTable(i+1)){
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
                            JOptionPane.showMessageDialog(null,"Table " + (finalI+1) + " Is Full");
                        }else {
                            if (JOptionPane.showConfirmDialog(null, "Chose table " + (finalI+1) +". Are you sure ?",  "Table",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                                //Set Color
                                table_LB[finalI].setIcon(table_full_Icon);
                                table_Name[finalI].setForeground(Color_me);

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

                                    //LentBook DTB
                                    userManager.addLentBookManager();
                                    lentBookDTB(user, quantityBorrow);
                                }else {
                                    if(defaultTableModelUser != null){
                                        user = userManager.getUser(Integer.parseInt(userManager.getUseLentInfo()[0]));

                                        //Lent Book DTB
                                        lentBookDTB(user, quantityBorrow);
                                    }else {
                                        user = userManager.getUser(userManager.totalUser());

                                        //LentBook DTB
                                        lentBookDTB(user, quantityBorrow);
                                    }
                                }

                                //Add To Table Manager
                                addTableToTableManager(user,finalI);
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
                if(tableManager.checkUsedTable(i+1)){
                    table_LB[i].setIcon(table_full_Icon);
                    table_Name[i].setForeground(Color_me);
                }else {
                    table_LB[i].setIcon(table_Icon);
                    table_Name[i].setForeground(Color_ForeG);
                }
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
                //Fill Table Color
                if(tableManager.checkUsedTable(i+1)){
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
                            JOptionPane.showMessageDialog(null,"Table " + (finalI+1) + " Is Full");
                        }else {
                            if (JOptionPane.showConfirmDialog(null, "Chose table " + (finalI+1) +". Are you sure ?",  "Table",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                                //Set Color
                                table_LB[finalI].setIcon(table_full_Icon);
                                table_Name[finalI].setForeground(Color_me);

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

                                    //LentBook DTB
                                    userManager.addLentBookManager();
                                    lentBookDTB(user, quantityBorrow);
                                }else {
                                    if(defaultTableModelUser != null){
                                        user = userManager.getUser(Integer.parseInt(userManager.getUseLentInfo()[0]));

                                        //Lent Book DTB
                                        lentBookDTB(user, quantityBorrow);
                                    }else {
                                        user = userManager.getUser(userManager.totalUser());

                                        //LentBook DTB
                                        lentBookDTB(user, quantityBorrow);
                                    }
                                }

                                //Add To Table Manager
                                addTableToTableManager(user,finalI);
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
                if(tableManager.checkUsedTable(i+1)){
                    table_LB[i].setIcon(table_full_Icon);
                    table_Name[i].setForeground(Color_me);
                }else {
                    table_LB[i].setIcon(table_Icon);
                    table_Name[i].setForeground(Color_ForeG);
                }
            }

            label.add(table_Name[i]);
            label.add(table_LB[i]);
        }


// create information table of used table

        defaultTableModel = new DefaultTableModel(tableManager.listTable(), tableManager.tableContent());
        jt = new JTable(defaultTableModel){
            public boolean isCellEditable(int row, int column) {
                return false;
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

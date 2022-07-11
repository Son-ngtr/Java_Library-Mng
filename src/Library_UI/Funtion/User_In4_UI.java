package Library_UI.Funtion;

import Library.Book_Manager.Book;
import Library.Book_Manager.BookManager;
import Library.Check;
import Library.HIstory_Manager.HistoryReceive_Manager;
import Library.LentBook_Manager.LentBookManager;
import Library.Staff_Manager.CountDownStaff;
import Library.HIstory_Manager.HistoryManager;
import Library.User_Manager.UserManager;
import Library_UI.Lib_UI.LentBooks_UI;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class User_In4_UI {
    private Check check = new Check();
    private JFrame main_Frame, userFrame;
    private ImageIcon bk_Icon, notepad_Icon, login_Ani, login_ef;
    private JLabel label, notification_Label, login_Icon, logout_Label, exit_Label;
    private JButton button ,b1, b2, b3, b4, b5, b6,b7, bt_lent, bt_delete, bt_info;
    private JTextField txt_1, txt_3, txt_4, txt_5, txt_6;
    private JDatePickerImpl datePicker_staff;
    private DefaultTableModel defaultTableModelUser,defaultTableModel;
    private JTable tableUser, jt;
    private UserManager userManager;
    private BookManager bookManager;
    private HistoryManager historyManager;
    private HistoryReceive_Manager historyReceive_manager;
    private Calendar calendar = Calendar.getInstance()  ;
    private CountDownStaff countDown;
    private String UserId;
    private LentBookManager lentBookManager;
    private Calendar today = Calendar.getInstance();

    //Constructor
    public User_In4_UI(BookManager bookManager, UserManager userManager, HistoryManager historyManager, HistoryReceive_Manager historyReceive_manager){
        this.bookManager = bookManager;
        this.userManager = userManager;
        this.historyManager = historyManager;
        this.historyReceive_manager = historyReceive_manager;
        this.UserId = userManager.getUseLentInfo()[0];
        lentBookManager = userManager.getLentBookManager(userManager.getUseLentInfo()[0]);
        content();
    }

    //Fix Quantity Of Book
    public void fixQuantityOfBook(Book book, String serialNumber, int numberOfBook){
        //Fix Quantity Of Book
        String category = book.getCategory();
        switch (category){
            case "Learning Book":
                bookManager.editBookLearning(String.valueOf(book.learningCode()), bookManager.bookContentLearningIndex("Quantity"), String.valueOf(book.getQuantity() + numberOfBook));
                JOptionPane.showMessageDialog(null, "Books had been returned to " + serialNumber);
                break;
            case "Noval Book":
                bookManager.editBookNoval(String.valueOf(book.novelCode()), bookManager.bookContentNovelIndex("Quantity"), String.valueOf(book.getQuantity() + numberOfBook));
                JOptionPane.showMessageDialog(null, "Books had been returned to " + serialNumber);
                break;
            case "Children Book":
                bookManager.editBookChild(String.valueOf(book.childCode()), bookManager.bookContentChildrenIndex("Quantity"), String.valueOf(book.getQuantity() + numberOfBook));
                JOptionPane.showMessageDialog(null, "Books had been returned to " + serialNumber);
                break;
            case "Psychological Book":
                bookManager.editBookPsychology(String.valueOf(book.psychologyCode()), bookManager.bookContentPsychologyIndex("Quantity"), String.valueOf(book.getQuantity() + numberOfBook));
                JOptionPane.showMessageDialog(null, "Books had been returned to " + serialNumber);
                break;
        }
    }

    //Remove Lent Book
    public void removeLentBook(){
        lentBookManager.removeLentBook(String.valueOf(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("STT"))));
    }

    //Fix total Books of user
    public void fixTotalBookOfUser(){
        userManager.editUser(
                UserId ,
                userManager.userContentIndex("Total Books"),
                String.valueOf(userManager.getUser(Integer.parseInt(UserId)).getTotalBooks() - Integer.parseInt(String.valueOf(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("Quantity")))))
        );
    }

    //Fix Fine Money of user
    public void fixFineMoneyOfUser(){
        Long dayTillEnd = Long.valueOf(check.dateReConvert(String.valueOf(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("End Date")))).get(Calendar.DATE) - today.get(Calendar.DATE));
        Long lentMoneyMinus = dayTillEnd * Long.valueOf(String.valueOf(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("Quantity")))) * Long.valueOf(check.moneyConvert(String.valueOf(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("LentMoney"))))) / 10;
        userManager.editUser(
                UserId,
                userManager.userContentIndex("Fine Money"),
                String.valueOf(userManager.getUser(Integer.parseInt(UserId)).getMoneyFine() - lentMoneyMinus)
        );
    }

    //Add History Receive
    public void addHistoryReceive(){
        Calendar calendar = Calendar.getInstance();
        historyReceive_manager.addHistoryReceive(
                historyReceive_manager.createHistory(
                        userManager.getUser(Integer.parseInt(UserId)).getName(),
                        userManager.getUser(Integer.parseInt(UserId)).getPhoneNumber(),
                        check.dateReConvert(String.valueOf(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("End Date")))),
                        String.valueOf(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("Name Book"))),
                        Integer.parseInt(String.valueOf(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("Quantity")))),
                        calendar
                )
        );
    }

    //Table Reset
    public void tableReset(){
        defaultTableModel.setDataVector(lentBookManager.listLentBook(), lentBookManager.lentBookContent());
    }
    public void tableUserReset(){
        userManager.setIsUpdate(true);
        defaultTableModelUser.setDataVector(userManager.listUser(), userManager.userContent());
        tableUser.getColumnModel().getColumn(userManager.userContentIndex("Gender")).setCellEditor(new DefaultCellEditor(new JComboBox(userManager.userGender())));
        userManager.setIsUpdate(false);
    }

    //Manager User Side
    public void setManagerUserSide(JFrame frameUser, DefaultTableModel defaultTableModelUser, JTable tableUser){
        userFrame = frameUser;
        this.defaultTableModelUser = defaultTableModelUser;
        this.tableUser = tableUser;
    }

    public void content() {

        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/UserIn4_UI.png");
        label = new JLabel(bk_Icon);
        label.setSize(bk_Icon.getIconWidth(), bk_Icon.getIconHeight());

        Font Font_left = new Font("Lucida Calligraphy", Font.PLAIN, 42);
        Font Font_login = new Font("Lucida Calligraphy", Font.PLAIN, 20);
        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_me_3 = new Font("MV Boli", Font.PLAIN, 20);
        Font Font_me_4 = new Font("MV Boli", Font.PLAIN, 14);

        Color Color_1 = new Color(252, 248, 232);
        Color Color_2 = new Color(148, 180, 159);
        Color Color_3 = new Color(250, 183, 61);

// create 3 button
        ImageIcon notification_Icon = new ImageIcon("src/Image_Icon/icon/notification (1).png");
        notification_Label = new JLabel(notification_Icon);
        notification_Label.setSize(80,80);
        notification_Label.setBounds(850,290,45,45);
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
        logout_Label.setBounds(845,290+145,45,45);
        logout_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userManager.stopCountDown(UserId);
                userFrame.setEnabled(true);
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
        exit_Label.setBounds(850,290+145*2,45,45);
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
// create 6 button
        b1 = new JButton("Name");
        b1.setBounds(59, 52, 167, 40);
        b1.setFont(Font_me_3);
        b1.setBorder(BorderFactory.createLineBorder(Color_3));
        b1.setForeground(Color_2);
        b1.setBackground(Color_1);


// create 6 text fields
        txt_1 = new JTextField(userManager.getUseLentInfo()[1]);
        txt_1.setBackground(Color_1);
        txt_1.setBounds(277, 52, 470, 40);
        txt_1.setForeground(Color_2);
        txt_1.setBorder(BorderFactory.createLineBorder(Color_3));
        txt_1.setFont(Font_me_3);
        txt_1.setEditable(false);




//create table

        defaultTableModel = new DefaultTableModel(lentBookManager.listLentBook(), lentBookManager.lentBookContent());

        jt = new JTable(defaultTableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userManager.startCountDown(UserId,lentBookManager, jt, userManager, defaultTableModel);
        jt.getTableHeader().setReorderingAllowed(false);
        jt.setBackground(Color_1);
        jt.setForeground(Color_2);
        jt.setFont(Font_me_4);
        jt.setGridColor(Color_3);


        JTableHeader jth = jt.getTableHeader();
        jth.setForeground(Color_3);
        jth.setFont(Font_me_4);
        jth.setBackground(Color_2);

        JScrollPane jsp = new JScrollPane(jt);
        jsp.setBounds(58, 135, 690, 490);
        jsp.setBackground(Color_1);
        jsp.setFont(Font_me_4);
        jsp.setForeground(Color_2);


// create 3 function bt
        bt_lent = new JButton("lent book");
        bt_lent.setForeground(Color_2);
        bt_lent.setBackground(Color_1);
        bt_lent.setFont(Font_me_3);
        bt_lent.setBorder(BorderFactory.createLineBorder(Color_2));
        bt_lent.setBounds(163, 680, 166, 36);
        bt_lent.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userManager.stopCountDown(UserId);
                LentBooks_UI lentBooks_ui = new LentBooks_UI(bookManager, userManager,historyManager, historyReceive_manager);
                lentBooks_ui.setUserI4InfoSide(userFrame, defaultTableModelUser, tableUser);
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
                bt_lent.setBackground(Color_2);
                bt_lent.setForeground(Color_3);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_lent.setBackground(Color_1);
                bt_lent.setForeground(Color_2);
            }
        });

        bt_delete = new JButton("delete book");
        bt_delete.setForeground(Color_2);
        bt_delete.setBackground(Color_1);
        bt_delete.setFont(Font_me_3);
        bt_delete.setBorder(BorderFactory.createLineBorder(Color_2));
        bt_delete.setBounds(413, 680, 167, 36);
        bt_delete.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String serialNumber = String.valueOf(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("Serial Number")));
                Book book = bookManager.getBookBySeri(serialNumber);
                if(book != null){
                    //Fix Quantity Of Book
                    int numberOfBook = Integer.valueOf((String)(jt.getValueAt(jt.getSelectedRow(), lentBookManager.lentBookContentIndex("Quantity"))));
                    fixQuantityOfBook(book, serialNumber, numberOfBook);

                    //Fix total Books of user
                    fixTotalBookOfUser();

                    //Fix Fine Money of user
                    fixFineMoneyOfUser();

                    //Add History To History Receive
                    addHistoryReceive();

                    //Remove Lent Book
                    removeLentBook();

                    //Receive Book Back To Book Category (Reset Table)
                    tableReset();
                    tableUserReset();
                }else {
                    JOptionPane.showMessageDialog(null, "We can not find " + serialNumber + " please create one");
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
                bt_delete.setBackground(Color_2);
                bt_delete.setForeground(Color_3);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_delete.setBackground(Color_1);
                bt_delete.setForeground(Color_2);
            }
        });

        bt_info = new JButton("info_book");
        bt_info.setForeground(Color_2);
        bt_info.setBackground(Color_1);
        bt_info.setFont(Font_me_3);
        bt_info.setBorder(BorderFactory.createLineBorder(Color_2));
        bt_info.setBounds(663, 680, 167, 36);
        bt_info.addMouseListener(new MouseListener() {
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
                bt_info.setBackground(Color_2);
                bt_info.setForeground(Color_3);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_info.setBackground(Color_1);
                bt_info.setForeground(Color_2);
            }
        });




// add all properties on UI
        label.add(b1);

        label.add(txt_1);

        label.add(notification_Label);
        label.add(logout_Label);
        label.add(exit_Label);

        label.add(bt_lent);
        label.add(bt_delete);
        label.add(bt_info);
        label.add(jsp);

        main_Frame = new JFrame("Main_UI");
        main_Frame.add(label);
        main_Frame.setSize(bk_Icon.getIconWidth(), bk_Icon.getIconHeight());
        main_Frame.setResizable(false);
        main_Frame.setLayout(null);
        main_Frame.setDefaultCloseOperation(main_Frame.EXIT_ON_CLOSE);
        main_Frame.setLocationRelativeTo(null);
        main_Frame.setUndecorated(true);
        main_Frame.setVisible(true);
    }

    public static void main(String[] args) {
//        new User_In4_UI();
    }
}

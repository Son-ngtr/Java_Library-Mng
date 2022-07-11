package Library_UI.Lib_UI;

import Library.Book_Manager.BookManager;
import Library.Check;
import Library.HIstory_Manager.HistoryManager;
import Library.HIstory_Manager.HistoryReceive_Manager;
import Library.User_Manager.UserManager;
import Library_UI.Funtion.AddUser_UI;
import Library_UI.Funtion.User_In4_UI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ManageUser_UI {
    private JFrame main_Frame, lobbyFrame;
    private ImageIcon bk_Icon, notepad_Icon, login_Ani, login_ef;
    private JLabel label, notification_Label, login_Icon, logout_Label, exit_Label,brand;
    private JButton button, bt_add, bt_remove, bt_search;
    private JTextField txt_Group;
    private JButton logIn;
    private JPanel inFo;
    private JTable jt;
    private DefaultTableModel defaultTableModel;
    private UserManager userManager;
    private BookManager bookManager;
    private JComboBox cb;
    private Check check = new Check();
    private HistoryManager historyManager;
    private HistoryReceive_Manager historyReceive_manager;

    //Set Lobby Side
    public void setLobbySide(JFrame jFrameLobby){
        lobbyFrame = jFrameLobby;
    }

    //Constructor
    public ManageUser_UI(BookManager bookManager, UserManager userManager, HistoryManager historyManager, HistoryReceive_Manager historyReceive_manager){
        this.bookManager = bookManager;
        this.userManager = userManager;
        this.historyManager = historyManager;
        this.historyReceive_manager = historyReceive_manager;
        cb = new JComboBox(userManager.userGender());
        content();
    }

    //Table reset
    public void tableReset(){
        userManager.setIsUpdate(true);
        defaultTableModel.setDataVector(userManager.listUser(), userManager.userContent());
        jt.getColumnModel().getColumn(userManager.userContentIndex("Gender")).setCellEditor(new DefaultCellEditor(cb));
        userManager.setIsUpdate(false);
    }

    public void content(){
        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/_Reader_UI_.png");
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

        Font Font_left = new Font("MV Boli", Font.PLAIN, 16);
        Font Font_login = new Font("Lucida Calligraphy", Font.PLAIN, 20);
        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_me_3 = new Font("MV Boli", Font.ITALIC, 12);
        Font Font_Brand = new Font("MV Boli", Font.BOLD, 60);
        Font Font_Table = new Font("MV Boli", Font.PLAIN, 12);



        Color Color_me = new Color(250,183,61);
        Color Color_ForeG = new Color(13,54,57);
        Color Color_ForeG_2 = new Color(236,131,2);
        Color Color_left = new Color(84, 103, 71);

        txt_Group = new JTextField("designed by TropicalHorseTeam");
        txt_Group.setBackground(new Color(84, 103, 71));
        txt_Group.setFont(Font_me_3);
        txt_Group.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_Group.setForeground(Color_ForeG);
        txt_Group.setBounds(1200,910,230,20);
        txt_Group.setEditable(false);

// create brand
        brand = new JLabel("Users Management");
        brand.setBounds(750, 30, 900, 100);
        brand.setFont(Font_Brand);
        brand.setForeground(Color_me);

// create 3 button -> function
        bt_add = new JButton("Add user");
        bt_add.setBounds(75,287+8,190,42);
        bt_add.setFont(Font_left);
        bt_add.setBorder(BorderFactory.createLineBorder(Color_me));
        bt_add.setForeground(Color_me);
        bt_add.setBackground(Color_left);
        bt_add.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddUser_UI addUser_ui = new AddUser_UI(userManager);
                addUser_ui.setManagerUser(main_Frame, defaultTableModel, jt);
                main_Frame.setEnabled(false);
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

        bt_remove = new JButton("Remove user");
        bt_remove.setBounds(75,399+8,190,42);
        bt_remove.setFont(Font_left);
        bt_remove.setBorder(BorderFactory.createLineBorder(Color_me));
        bt_remove.setForeground(Color_me);
        bt_remove.setBackground(Color_left);
        bt_remove.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jt.getSelectedRow() != -1){
                    if(userManager.getLentBookManager(check.codeConvert(String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("ID")))) ).totalLentBook() == 0 ){
                        userManager.removeUser(check.codeConvert(String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("ID"))).trim()));
                        tableReset();
                    }else {
                        JOptionPane.showMessageDialog(null, "Please Return All The Book Before Delete User");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Please Chose A User Form The Table");
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
                bt_remove.setBackground(Color_ForeG);
                bt_remove.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_remove.setBackground(Color_me);
                bt_remove.setForeground(Color_ForeG);
            }
        });

        bt_search = new JButton("User _ in4");
        bt_search.setBounds(75,511+8,190,42);
        bt_search.setFont(Font_left);
        bt_search.setBorder(BorderFactory.createLineBorder(Color_me));
        bt_search.setForeground(Color_me);
        bt_search.setBackground(Color_left);
        bt_search.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jt.getSelectedRow() != -1){
                    //User Data
                    String useID = check.codeConvert(String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("ID"))));
                    String userName = String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("Name")));
                    String userGender = String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("Gender")));
                    String userDateOfBirth = String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("Date Of Birth")));
                    String userAddress = String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("Address")));
                    String userPhoneNumber = String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("Phone Number")));
                    String userEmail = String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("Email")));
                    userManager.setUseLentInfo(new String[]{
                            useID,
                            userName,
                            userGender,
                            userDateOfBirth,
                            userAddress,
                            userPhoneNumber,
                            userEmail
                    });

                    User_In4_UI user_in4_ui = new User_In4_UI( bookManager, userManager, historyManager,historyReceive_manager);
                    user_in4_ui.setManagerUserSide(main_Frame, defaultTableModel, jt);
                    main_Frame.setEnabled(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Chose an user form the table");
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
                bt_search.setBackground(Color_ForeG);
                bt_search.setForeground(Color_me);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt_search.setBackground(Color_me);
                bt_search.setForeground(Color_ForeG);
            }
        });

// create table content
        //Search Field
        JTextField bookFilter = new JTextField("  ",20);
        bookFilter.setBounds(400, 130, 1330, 35);
        Font filterFont = new Font("Arial", Font.PLAIN, 20);
        bookFilter.setFont(filterFont);
        bookFilter.setBackground(Color_left);
        bookFilter.setBorder(BorderFactory.createLineBorder(Color_me));
        bookFilter.setForeground(Color_me);

        //Table
        defaultTableModel = new DefaultTableModel(userManager.listUser(), userManager.userContent());
        jt = new JTable(defaultTableModel){
            public boolean isCellEditable(int row, int column) {
                if (column == userManager.userContentIndex("ID") || column == userManager.userContentIndex("Total Books") || column == userManager.userContentIndex("Fine Money")) return false;
                return true;
            }
        };
        jt.getTableHeader().setReorderingAllowed(false);
        jt.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cb));
        jt.setFont(Font_Table);
        jt.setGridColor(Color_ForeG);
        jt.setBackground(Color_me);
        jt.setForeground(Color_ForeG);

        JTableHeader jth = jt.getTableHeader();
        jth.setBackground(Color_ForeG);
        jth.setFont(Font_Table);
        jth.setForeground(Color_me);


        JScrollPane Jsc = new JScrollPane(jt);
        Jsc.setBounds(400, 165, 1330, 710);
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

        //Table Fĩx
        jt.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(!userManager.getIsUpdate()){
                    String codeValue = check.codeConvert(String.valueOf(jt.getValueAt(jt.getSelectedRow(), userManager.userContentIndex("ID"))).trim());
                    String newValue = String.valueOf(jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn())).trim();
                    switch (jt.getSelectedColumn()){
                        case 1:
                            if(!userManager.getIsUpdate()){
                                if(newValue.trim().length() > 0){
                                    userManager.editUser(codeValue, jt.getSelectedColumn(), newValue);
                                }else {
                                    JOptionPane.showMessageDialog(null, "Name");
                                    tableReset();
                                }
                            }
                            break;
                        case 2:
                            if(!userManager.getIsUpdate()){
                                if(newValue.trim().length() > 0){
                                    userManager.editUser(codeValue, jt.getSelectedColumn(), newValue);
                                }
                            }
                            break;
                        case 3:
                            if(!userManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 && check.isDateOrNot(newValue)){
                                    userManager.editUser(codeValue, jt.getSelectedColumn(), newValue);
                                }else {
                                    JOptionPane.showMessageDialog(null, "Date");
                                    tableReset();
                                }
                            }
                            break;
                        case 4:
                            if(!userManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 ){
                                    userManager.editUser(codeValue, jt.getSelectedColumn(), newValue);
                                }else {
                                    JOptionPane.showMessageDialog(null, "Address");
                                    tableReset();
                                }
                            }
                            break;
                        case 5:
                            if(!userManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 ){
                                    userManager.editUser(codeValue, jt.getSelectedColumn(), newValue);
                                }else {
                                    JOptionPane.showMessageDialog(null, "PhoneNumber");
                                    tableReset();
                                }
                            }
                            break;
                        case 6:
                            if(!userManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 ){
                                    userManager.editUser(codeValue, jt.getSelectedColumn(), newValue);
                                }else {
                                    JOptionPane.showMessageDialog(null, "Gmail");
                                    tableReset();
                                }
                            }
                            break;
                        case 7:
                            if(!userManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 && check.isLong(newValue)){
                                    userManager.editUser(codeValue, jt.getSelectedColumn(), newValue);
                                }else {
                                    JOptionPane.showMessageDialog(null, "Total Books");
                                    tableReset();
                                }
                            }
                            break;
                        case 8:
                            if(!userManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 && check.mathCheck(check.mathAnalysis(newValue))){
                                    userManager.editUser(codeValue, jt.getSelectedColumn(), check.moneyConvert(check.matConvert(check.mathAnalysis(newValue))) );
                                    tableReset();
                                }else {
                                    JOptionPane.showMessageDialog(null, "Fine Money");
                                    tableReset();
                                }
                            }
                            break;
                    }
                }
            }
        });

// add all properties on UI
        label.add(bookFilter);
        label.add(Jsc);
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
//        new ManageUser_UI();
    }
}

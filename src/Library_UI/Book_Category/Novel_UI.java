package Library_UI.Book_Category;

import Library.Check;
import Library.Book_Manager.BookManager;
import Library.Human.User_Manager.UserManager;
import Library_UI.Funtion.Add_novel_UI;

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

public class Novel_UI {
    //Constructor
    public Novel_UI(BookManager bookManager){
        this.bookManager = bookManager;
        cbNovelType = new JComboBox(bookManager.novelType());
        cbNovelRecommentForAge = new JComboBox(bookManager.novelAgeLimited());
        Content();
    }

    private JFrame main_Frame, lobbyFrame;
    private ImageIcon bk_Icon;
    private JLabel label, notification_Label, logout_Label, exit_Label;
    private JButton bt_add, bt_remove, bt_search;
    private JTextField txt_Group,txt_Reader,txt_NoBook, txt_NoBookBorrowed ;
    private JLabel brand;
    private UserManager userManager;
    private JTable jt;
    private DefaultTableModel defaultTableModel;
    private BookManager bookManager;
    private JComboBox cbNovelType ;
    private JComboBox cbNovelRecommentForAge;
    private Check check = new Check();

    //Set Lobby Side
    public void setLobbySide(JFrame jFrameLobby){
        lobbyFrame = jFrameLobby;
    }

    //Table add Combobox and CheckBox
    public void tableAddCombobox(){
        jt.getColumnModel().getColumn(bookManager.bookContentNovelIndex("Type")).setCellEditor(new DefaultCellEditor(cbNovelType));
        jt.getColumnModel().getColumn(bookManager.bookContentNovelIndex("Age Recomment")).setCellEditor(new DefaultCellEditor(cbNovelRecommentForAge));
    }

    //Table reset
    public void tableReset(){
        bookManager.setIsUpdate(true);
        defaultTableModel.setDataVector(bookManager.listBookNovel(), bookManager.bookContentNoval());
        tableAddCombobox();
        bookManager.setIsUpdate(false);
    }

    //Set lobby info
    public void setLobbyInfo(JTextField txt_Reader, JTextField txt_NoBook, JTextField txt_NoBookBorrowed, UserManager userManager){
        this.userManager = userManager;
        this.txt_Reader = txt_Reader;
        this.txt_NoBook = txt_NoBook;
        this.txt_NoBookBorrowed = txt_NoBookBorrowed;
    }

    //Reset lobby
    public void resetLobbyInfo(){
        txt_Reader.setText(String.valueOf(userManager.totalUser()));
        txt_NoBook.setText(String.valueOf(bookManager.numberOfBook()));
        txt_NoBookBorrowed.setText(String.valueOf(userManager.totalBookBorrow()));
    }

    public void Content(){
        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/_psyBook_UI_.png");
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
                resetLobbyInfo();
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

// next + back category
        ImageIcon left_Icon = new ImageIcon("src/Image_Icon/icon/left.png");
        JLabel left_Label = new JLabel(left_Icon);
        left_Label.setSize(45,45);
        left_Label.setBounds(900,876,45,45);
        left_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TextBook_UI textBook_ui = new TextBook_UI(bookManager);
                textBook_ui.setLobbySide(lobbyFrame);
                textBook_ui.setLobbyInfo(txt_Reader,txt_NoBook,txt_NoBookBorrowed,userManager);
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

        ImageIcon right_Icon = new ImageIcon("src/Image_Icon/icon/right.png");
        JLabel right_Label = new JLabel(right_Icon);
        right_Label.setSize(45,45);
        right_Label.setBounds(960,876,45,45);
        right_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PsyBook_UI psyBook_ui = new PsyBook_UI(bookManager);
                psyBook_ui.setLobbySide(lobbyFrame);
                psyBook_ui.setLobbyInfo(txt_Reader,txt_NoBook,txt_NoBookBorrowed,userManager);
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

        txt_Group = new JTextField("designed by TropicalHorseTeam");
        txt_Group.setBackground(new Color(84, 103, 71));
        txt_Group.setFont(Font_me_3);
        txt_Group.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_Group.setForeground(Color_ForeG);
        txt_Group.setBounds(1200,910,230,20);
        txt_Group.setEditable(false);

// create brand
        brand = new JLabel("NOVEL");
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
                Add_novel_UI add_novel_ui = new Add_novel_UI(bookManager);
                add_novel_ui.setManagerUser(main_Frame, defaultTableModel, jt);
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

        bt_remove = new JButton("Remove book");
        bt_remove.setBounds(75,399+8,190,42);
        bt_remove.setFont(Font_left);
        bt_remove.setBorder(BorderFactory.createLineBorder(Color_me));
        bt_remove.setForeground(Color_me);
        bt_remove.setBackground(Color_left);
        bt_remove.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jt.getSelectedRow() != -1){
                    bookManager.removeBookNoval(check.codeConvert(String.valueOf(jt.getValueAt(jt.getSelectedRow(), bookManager.bookContentNovelIndex("Code"))).trim()));
                    tableReset();
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
        defaultTableModel = new DefaultTableModel(bookManager.listBookNovel(), bookManager.bookContentNoval());
        jt = new JTable(defaultTableModel){
            public boolean isCellEditable(int row, int column) {
                if (column == bookManager.bookContentNovelIndex("Code") || column == bookManager.bookContentNovelIndex("Category") || column == bookManager.bookContentNovelIndex("Serial Number")) return false;
                return true;
            }
        };
        jt.getTableHeader().setReorderingAllowed(false);
        tableAddCombobox();
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
                if(!bookManager.getIsUpdate()){
                    String codeValue = check.codeConvert(String.valueOf(jt.getValueAt(jt.getSelectedRow(), bookManager.bookContentNovelIndex("Code"))).trim());
                    String newValue = String.valueOf(jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn())).trim();
                    switch (jt.getSelectedColumn()){
                        case 1:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), newValue);
                                }else {
                                    JOptionPane.showMessageDialog(null, "Name");
                                    tableReset();
                                }
                            }
                            break;
                        case 2:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 && check.isDateOrNot(newValue)){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), newValue);
                                    tableReset();
                                }else {
                                    JOptionPane.showMessageDialog(null, "Date");
                                    tableReset();
                                }
                            }
                            break;
                        case 3:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 && check.mathCheck(check.mathAnalysis(newValue))){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), check.moneyConvert(check.matConvert(check.mathAnalysis(newValue))) );
                                    tableReset();
                                }else {
                                    JOptionPane.showMessageDialog(null, "Price");
                                    tableReset();
                                }
                            }
                            break;
                        case 4:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 ){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), newValue);
                                }else {
                                    JOptionPane.showMessageDialog(null, "Author");
                                    tableReset();
                                }
                            }
                            break;
                        case 5:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 ){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), newValue);
                                }else {
                                    JOptionPane.showMessageDialog(null, "Publisher");
                                    tableReset();
                                }
                            }
                            break;
                        case 6:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 ){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), newValue);
                                }
                            }
                            break;
                        case 7:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 && check.mathCheck(check.mathAnalysis(newValue))){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), check.matConvert(check.mathAnalysis(newValue)));
                                    tableReset();
                                }else {
                                    JOptionPane.showMessageDialog(null, "Quantity");
                                    tableReset();
                                }
                            }
                            break;
                        case 8:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 ){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), newValue);
                                }
                            }
                            break;
                        case 9:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 ){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), newValue);
                                }
                            }
                            break;
                        case 10:
                            if(!bookManager.getIsUpdate()){
                                if(newValue.trim().length() > 0 ){
                                    bookManager.editBookNoval(codeValue, jt.getSelectedColumn(), newValue);
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
        label.add(left_Label);
        label.add(right_Label);
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

    }
}

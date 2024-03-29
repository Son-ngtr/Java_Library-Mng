package Library.Book_Manager;

import Database.*;
import Library.Check;
import Library.LentBook_Manager.LentBook;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

public class BookManager {


    //DataBase
    private ChildrenBook childrenBook = new ChildrenBook();
    private NovelBook novelBook = new NovelBook();
    private PsychologyBook psychologyBook = new PsychologyBook();
    private LearningBook learningBook = new LearningBook();
    private Connection connection;

    Check check = new Check();
    private boolean isUpdate = false;
    private int codeCountAll = 0;
    private int codeCountNovel = 0;
    private int codeCountChild = 0;
    private int codeCountLearning = 0;
    private int codeCountPsychology = 0;

    //Constructor
    public BookManager(Connection connection){
        this.connection = connection;
    }

    //Getter and Setter
    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean update) {
        isUpdate = update;
    }


    //Array List, Book List
    private ArrayList<String> lisBookSeri = new ArrayList<>();
    Book bookNovel = new Book_Novel();
    Book bookLearning = new Book_Learning();
    Book bookPsychology = new Book_Psychology();
    Book bookChild = new Book_Child();
    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<Book> booksLearning = new ArrayList<>();
    private final ArrayList<Book> booksNovel = new ArrayList<>();
    private final ArrayList<Book> booksPsychology = new ArrayList<>();
    private final ArrayList<Book> booksChild = new ArrayList<>();

    //Add Seri to ListBook Seri
    public void addSeri(String seri){
        lisBookSeri.add(seri);
    }
    public void removeSeri(String seri){
        lisBookSeri.remove(lisBookSeri.indexOf(seri));
    }
    public boolean seriCheck(String seri){
        if (lisBookSeri.contains(seri)){
            return false;
        }else {
            return true;
        }
    }

    //Book Category
    public String[] bookCategory(){
        return new String[]{"Learning Book", "Noval Book", "Children Book", "Psychological Book"};
    }

    //Tạo ra một cuốn sách
    public Book createBookNovel(String name, Calendar dateAdded, Long price, String author, String publisher, int quantity,String serialNumber ,String type, String ageLimited){
        codeCountAll++;
        codeCountNovel++;
        return new Book_Novel(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountNovel,serialNumber, type, ageLimited);
    }
    public Book createBookChild( String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String serialNumber ,String type, String recommentForAge){
        codeCountAll++;
        codeCountChild++;
        return new Book_Child(codeCountAll, name, dateAdded, price, author, publisher, quantity,codeCountChild,serialNumber, type, recommentForAge);
    }
    public Book createBookLearning( String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String serialNumber ,String education, String education_subjects){
        codeCountAll++;
        codeCountLearning++;
        return new Book_Learning(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountLearning,serialNumber, education, education_subjects);
    }
    public Book createBookPsychology( String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String serialNumber ,String type, String recommentForAge){
        codeCountAll++;
        codeCountPsychology++;
        return new Book_Psychology(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountPsychology,serialNumber, type, recommentForAge);
    }


    //Thêm Sách
    public void addBookNovel(Book book){
        addSeri(book.getSerialNumber());
        books.add(book);
        booksNovel.add(book);
        try {
            novelBook.addNewNovelBook(
                    connection,
                    book.getSTT(),
                    book.getName(),
                    book.dateConvert(),
                    String.valueOf(book.getPrice()),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getCategory(),
                    book.getQuantity(),
                    book.novelCode(),
                    book.getSerialNumber(),
                    book.novelGetType(),
                    book.novalGetAgeLimited()
                    );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addBookChild(Book book){
        addSeri(book.getSerialNumber());
        books.add(book);
        booksChild.add(book);
        try {
            childrenBook.addNewChildrenBook(
                    connection,
                    book.getSTT(),
                    book.getName(),
                    book.dateConvert(),
                    String.valueOf(book.getPrice()),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getCategory(),
                    book.getQuantity(),
                    book.childCode(),
                    book.getSerialNumber(),
                    book.childGetType(),
                    book.childGetRecommentForAge()
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addBookLearning(Book book){
        addSeri(book.getSerialNumber());
        books.add(book);
        booksLearning.add(book);
        try {
            learningBook.addNewLearningBook(
                    connection,
                    book.getSTT(),
                    book.getName(),
                    book.dateConvert(),
                    String.valueOf(book.getPrice()),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getCategory(),
                    book.getQuantity(),
                    book.learningCode(),
                    book.getSerialNumber(),
                    book.learningGetEducation(),
                    book.learningGetEducation_subjects()
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addBookPsychology(Book book){
        addSeri(book.getSerialNumber());
        books.add(book);
        booksPsychology.add(book);
        try {
            psychologyBook.addNewPsychologyBook(
                    connection,
                    book.getSTT(),
                    book.getName(),
                    book.dateConvert(),
                    String.valueOf(book.getPrice()),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getCategory(),
                    book.getQuantity(),
                    book.psychologyCode(),
                    book.getSerialNumber(),
                    book.psychologyGetType(),
                    book.psychologyGetRecommentForAge()
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Tổng số lượng sách (khác loại) của thư viên
    public int totalBookDifferent(){
        return codeCountAll;
    }
    public int totalBookDifferentNoval(){
        return codeCountNovel;
    }
    public int totalBookDifferentLearning(){
        return codeCountLearning;
    }
    public int totalBookDifferentChild(){
        return codeCountChild;
    }
    public int totalBookDifferentPsychology(){
        return codeCountPsychology;
    }

    //Number Of Book
    public int numberOfBook(){
        int sum = 0;
        for (Book book : books){
            sum+=book.getQuantity();
        }
        return sum;
    }

    //Dowload Book
    public void downloadAllBook(){
        ArrayList<Book> listBookAll = new ArrayList<>();

        ArrayList<Book> listBookLearning = downLoadBookLearning();
        ArrayList<Book> listBookNovel = downLoadBookNovel();
        ArrayList<Book> listBookChild = downLoadBookChild();
        ArrayList<Book> listBookPsychology = downLoadBookPsychology();

        listBookAll.addAll(listBookLearning);
        listBookAll.addAll(listBookNovel);
        listBookAll.addAll(listBookChild);
        listBookAll.addAll(listBookPsychology);

        //List Book Sort
        for (int i=0 ; i<listBookAll.size()-1; i++){
            for (int j=i+1; j<listBookAll.size(); j++){
                if(listBookAll.get(i).getSTT() > listBookAll.get(j).getSTT()){
                    Book book = listBookAll.get(i);
                    listBookAll.set(i, listBookAll.get(j));
                    listBookAll.set(j, book);
                }
            }
        }

        //List Book Add To UI
        for (Book book : listBookAll){
            addSeri(book.getSerialNumber());
            String category = book.getCategory();
            switch (category){
                case "Learning Book":
                    books.add(book);
                    booksLearning.add(book);
                    break;
                case "Noval Book":
                    books.add(book);
                    booksNovel.add(book);
                    break;
                case "Children Book":
                    books.add(book);
                    booksChild.add(book);
                    break;
                case "Psychological Book":
                    books.add(book);
                    booksPsychology.add(book);
                    break;
            }
        }
    }
    public ArrayList<Book> downLoadBookLearning(){
        ArrayList<Book> newArrayList = new ArrayList<>();

        Vector<Vector<Object>> vectors = null;
        try {
            vectors = learningBook.getAll(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Vector<Object> vector : vectors){
            codeCountAll++;
            codeCountLearning++;
            Book_Learning bookLearning = new Book_Learning(
                    Integer.parseInt(String.valueOf(vector.get(0))),
                    String.valueOf(vector.get(1)),
                    check.dateReConvert(String.valueOf(vector.get(2))),
                    Long.parseLong(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    Integer.parseInt(String.valueOf(vector.get(8))),
                    String.valueOf(vector.get(9)),
                    String.valueOf(vector.get(10)),
                    String.valueOf(vector.get(11))
            );
            newArrayList.add(bookLearning);
        }

        return newArrayList;
    }
    public ArrayList<Book> downLoadBookNovel(){
        ArrayList<Book> newArrayList = new ArrayList<>();

        Vector<Vector<Object>> vectors = null;
        try {
            vectors = novelBook.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Vector<Object> vector : vectors){
            codeCountAll++;
            codeCountNovel++;
            Book_Novel bookNovel = new Book_Novel(
                    Integer.parseInt(String.valueOf(vector.get(0))),
                    String.valueOf(vector.get(1)),
                    check.dateReConvert(String.valueOf(vector.get(2))),
                    Long.parseLong(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    Integer.parseInt(String.valueOf(vector.get(8))),
                    String.valueOf(vector.get(9)),
                    String.valueOf(vector.get(10)),
                    String.valueOf(vector.get(11))
            );
            newArrayList.add(bookNovel);
        }

        return newArrayList ;
    }
    public ArrayList<Book> downLoadBookChild(){
        ArrayList<Book> newArrayList = new ArrayList<>();

        Vector<Vector<Object>> vectors = null;
        try {
            vectors = childrenBook.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Vector<Object> vector : vectors){
            codeCountAll++;
            codeCountChild++;
            Book_Child bookChild = new Book_Child(
                    Integer.parseInt(String.valueOf(vector.get(0))),
                    String.valueOf(vector.get(1)),
                    check.dateReConvert(String.valueOf(vector.get(2))),
                    Long.parseLong(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    Integer.parseInt(String.valueOf(vector.get(8))),
                    String.valueOf(vector.get(9)),
                    String.valueOf(vector.get(10)),
                    String.valueOf(vector.get(11))
            );
            newArrayList.add(bookChild);
        }

        return newArrayList;
    }
    public ArrayList<Book> downLoadBookPsychology(){
        ArrayList<Book> newArrayList = new ArrayList<>();

        Vector<Vector<Object>> vectors = null;
        try {
            vectors = psychologyBook.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Vector<Object> vector : vectors){
            codeCountAll++;
            codeCountPsychology++;
            Book_Psychology bookPsychology = new Book_Psychology(
                    Integer.parseInt(String.valueOf(vector.get(0))),
                    String.valueOf(vector.get(1)),
                    check.dateReConvert(String.valueOf(vector.get(2))),
                    Long.parseLong(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    Integer.parseInt(String.valueOf(vector.get(8))),
                    String.valueOf(vector.get(9)),
                    String.valueOf(vector.get(10)),
                    String.valueOf(vector.get(11))
            );
            newArrayList.add(bookPsychology);
        }

        return newArrayList;
    }


    //List Book
    public String[][] listBookBorrow(){
        String[][] mainObj = new String[totalBookDifferent()][bookBorrowContent().length];
        int count = 0;
        for (Book book : books){
            for (int i=0; i<bookBorrowContent().length; i++){
                switch (i) {
                    case 0 ->{
                        String s;
                        switch (book.getCategory()) {
                            case "Learning Book" -> s = "L" + book.learningCode();
                            case "Noval Book" -> s = "N" + book.novelCode();
                            case "Children Book" -> s = "C" + book.childCode();
                            case "Psychological Book" -> s = "P" + book.psychologyCode();
                            default -> s = "";
                        }
                        mainObj[count][i] = s;

                    }
                    case 1 -> mainObj[count][i] = book.getName();
                    case 2 -> mainObj[count][i] = book.moneyConvert();
                    case 3 -> mainObj[count][i] = book.getAuthor();
                    case 4 -> mainObj[count][i] = book.getPublisher();
                    case 5 -> mainObj[count][i] = book.getCategory();
                    case 6 -> mainObj[count][i] = String.valueOf(book.getQuantity());
                    case 7 -> mainObj[count][i] = book.getSerialNumber();
                    default -> {
                    }
                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book All
    public String[][] listBookAll(){
        String[][] mainObj = new String[totalBookDifferent()][bookContent().length];
        int count = 0;
        for (Book book : books){
            for (int i=0; i<bookContent().length; i++){
                switch (i) {
                    case 0 -> mainObj[count][i] = String.valueOf(book.getSTT());
                    case 1 -> mainObj[count][i] = book.getName();
                    case 2 -> mainObj[count][i] = book.dateConvert();
                    case 3 -> mainObj[count][i] = book.moneyConvert();
                    case 4 -> mainObj[count][i] = book.getAuthor();
                    case 5 -> mainObj[count][i] = book.getPublisher();
                    case 6 -> mainObj[count][i] = book.getCategory();
                    case 7 -> mainObj[count][i] = String.valueOf(book.getQuantity());
                    case 8 -> mainObj[count][i] = book.getSerialNumber();
                    default -> {
                    }
                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Novel
    public String[][] listBookNovel(){
        String[][] mainObj = new String[totalBookDifferentNoval()][bookContentNoval().length];
        int count = 0;
        for (Book bookNovel : booksNovel){
            for (int i=0; i<bookContentNoval().length; i++){
                switch (i) {
                    case 0 -> {
                        String code = "N" + String.valueOf(bookNovel.novelCode());
                        mainObj[count][i] = code;
                    }
                    case 1 -> mainObj[count][i] = bookNovel.getName();
                    case 2 -> mainObj[count][i] = bookNovel.dateConvert();
                    case 3 -> mainObj[count][i] = bookNovel.moneyConvert();
                    case 4 -> mainObj[count][i] = bookNovel.getAuthor();
                    case 5 -> mainObj[count][i] = bookNovel.getPublisher();
                    case 6 -> mainObj[count][i] = bookNovel.getCategory();
                    case 7 -> mainObj[count][i] = String.valueOf(bookNovel.getQuantity());
                    case 8 -> mainObj[count][i] = bookNovel.getSerialNumber();
                    case 9 -> mainObj[count][i] = bookNovel.novelGetType();
                    case 10 -> mainObj[count][i] = bookNovel.novalGetAgeLimited();

                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Child
    public String[][] listBookChild(){
        String[][] mainObj = new String[totalBookDifferentChild()][bookContentChildren().length];
        int count = 0;
        for (Book bookChild : booksChild){
            for (int i=0; i<bookContentChildren().length; i++){
                switch (i) {
                    case 0 -> {
                        String code = "C" + String.valueOf(bookChild.childCode());
                        mainObj[count][i] = code;
                    }
                    case 1 -> mainObj[count][i] = bookChild.getName();
                    case 2 -> mainObj[count][i] = bookChild.dateConvert();
                    case 3 -> mainObj[count][i] = bookChild.moneyConvert();
                    case 4 -> mainObj[count][i] = bookChild.getAuthor();
                    case 5 -> mainObj[count][i] = bookChild.getPublisher();
                    case 6 -> mainObj[count][i] = bookChild.getCategory();
                    case 7 -> mainObj[count][i] = String.valueOf(bookChild.getQuantity());
                    case 8 -> mainObj[count][i] = bookChild.getSerialNumber();
                    case 9 -> mainObj[count][i] = bookChild.childGetType();
                    case 10 -> mainObj[count][i] = bookChild.childGetRecommentForAge();

                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Learning
    public String[][] listBookLearning(){
        String[][] mainObj = new String[totalBookDifferentLearning()][bookContentLearning().length];
        int count = 0;
        for (Book bookLearning: booksLearning){
            for (int i=0; i<bookContentLearning().length; i++){
                switch (i) {
                    case 0 -> {
                        String code = "L" + String.valueOf(bookLearning.learningCode());
                        mainObj[count][i] = code;
                    }
                    case 1 -> mainObj[count][i] = bookLearning.getName();
                    case 2 -> mainObj[count][i] = bookLearning.dateConvert();
                    case 3 -> mainObj[count][i] = bookLearning.moneyConvert();
                    case 4 -> mainObj[count][i] = bookLearning.getAuthor();
                    case 5 -> mainObj[count][i] = bookLearning.getPublisher();
                    case 6 -> mainObj[count][i] = bookLearning.getCategory();
                    case 7 -> mainObj[count][i] = String.valueOf(bookLearning.getQuantity());
                    case 8 -> mainObj[count][i] = bookLearning.getSerialNumber();
                    case 9 -> mainObj[count][i] = bookLearning.learningGetEducation();
                    case 10 -> mainObj[count][i] = bookLearning.learningGetEducation_subjects();

                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Psychology
    public String[][] listBookPsychology(){
        String[][] mainObj = new String[totalBookDifferentPsychology()][bookContentPsychology().length];
        int count = 0;
        for (Book bookPsychology : booksPsychology){
            for (int i=0; i<bookContentPsychology().length; i++){
                switch (i) {
                    case 0 -> {
                        String code = "P" + String.valueOf(bookPsychology.psychologyCode());
                        mainObj[count][i] = code;
                    }
                    case 1 -> mainObj[count][i] = bookPsychology.getName();
                    case 2 -> mainObj[count][i] = bookPsychology.dateConvert();
                    case 3 -> mainObj[count][i] = bookPsychology.moneyConvert();
                    case 4 -> mainObj[count][i] = bookPsychology.getAuthor();
                    case 5 -> mainObj[count][i] = bookPsychology.getPublisher();
                    case 6 -> mainObj[count][i] = bookPsychology.getCategory();
                    case 7 -> mainObj[count][i] = String.valueOf(bookPsychology.getQuantity());
                    case 8 -> mainObj[count][i] = bookPsychology.getSerialNumber();
                    case 9 -> mainObj[count][i] = bookPsychology.psychologyGetType();
                    case 10 -> mainObj[count][i] = bookPsychology.psychologyGetRecommentForAge();

                }
            }
            count++;
        }
        return mainObj;
    }


    //Tiêu Đề Danh Sách
    public String[] bookBorrowContent(){
        return new String[]{
                "Code", "Name", "Price", "Author", "Publisher", "Category", "Quantity", "Serial Number"
        };
    }
    public int bookBorrowContentIndex(String s){
        switch (s){
            case "Code":
                return 0;
            case "Name":
                return 1;
            case "Price":
                return 2;
            case "Author":
                return 3;
            case "Publisher":
                return 4;
            case "Category":
                return 5;
            case "Quantity":
                return 6;
            case "Serial Number":
                return 7;
        }
        return 100;
    }

    public String[] bookContent(){
        return new String[]{
              "Number", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity", "Serial Number"
        };
    }
    public int bookContentIndex(String s){
        switch (s){
            case "Number":
                return 0;
            case "Name":
                return 1;
            case "Date Added":
                return 2;
            case "Price":
                return 3;
            case "Author":
                return 4;
            case "Publisher":
                return 5;
            case "Category":
                return 6;
            case "Quantity":
                return 7;
            case "Serial Number":
                return 8;
        }
        return 100;
    }

    public String[] bookContentChildren(){
        return new String[]{
                "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity", "Serial Number","Type", "Age Recomment"
        };
    }
    public int bookContentChildrenIndex(String s){
        switch (s){
            case "Code":
                return 0;
            case "Name":
                return 1;
            case "Date Added":
                return 2;
            case "Price":
                return 3;
            case "Author":
                return 4;
            case "Publisher":
                return 5;
            case "Category":
                return 6;
            case "Quantity":
                return 7;
            case "Serial Number":
                return 8;
            case "Type":
                return 9;
            case "Age Recomment":
                return 10;
        }
        return 100;
    }
    public String[] bookContentNoval(){
        return new String[]{
                "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity" ,"Serial Number", "Type", "Age Recomment"
        };
    }
    public int bookContentNovelIndex(String s){
        switch (s){
            case "Code":
                return 0;
            case "Name":
                return 1;
            case "Date Added":
                return 2;
            case "Price":
                return 3;
            case "Author":
                return 4;
            case "Publisher":
                return 5;
            case "Category":
                return 6;
            case "Quantity":
                return 7;
            case "Serial Number":
                return 8;
            case "Type":
                return 9;
            case "Age Recomment":
                return 10;
        }
        return 100;
    }
    public String[] bookContentLearning(){
        return new String[]{
                "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity","Serial Number", "Education", "Subjects"
        };
    }
    public int bookContentLearningIndex(String s){
        switch (s){
            case "Code":
                return 0;
            case "Name":
                return 1;
            case "Date Added":
                return 2;
            case "Price":
                return 3;
            case "Author":
                return 4;
            case "Publisher":
                return 5;
            case "Category":
                return 6;
            case "Quantity":
                return 7;
            case "Serial Number":
                return 8;
            case "Education":
                return 9;
            case "Subjects":
                return 10;
        }
        return 100;
    }
    public String[] bookContentPsychology(){
        return new String[]{
                "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity","Serial Number","Type", "Age Recomment"
        };
    }
    public int bookContentPsychologyIndex(String s){
        switch (s){
            case "Code":
                return 0;
            case "Name":
                return 1;
            case "Date Added":
                return 2;
            case "Price":
                return 3;
            case "Author":
                return 4;
            case "Publisher":
                return 5;
            case "Category":
                return 6;
            case "Quantity":
                return 7;
            case "Serial Number":
                return 8;
            case "Type":
                return 9;
            case "Age Recomment":
                return 10;
        }
        return 100;
    }


    //Xóa Sách
    public void removeBook(String code){
        //New Version
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCountAll ){
            if(Integer.toString(books.get(intCode-1).getSTT()).equalsIgnoreCase(code.trim())){
                String cLearning = Integer.toString(books.get(intCode-1).learningCode());
                String cNoval = Integer.toString(books.get(intCode -1).novelCode());
                String cChild = Integer.toString(books.get(intCode -1).childCode());
                String cPsychology = Integer.toString(books.get(intCode - 1).psychologyCode());
                String category = books.get(intCode -1 ).getCategory().trim();
                String seri = books.get(intCode - 1).getSerialNumber().trim();
                lisBookSeri.remove(seri);
                books.remove(books.get(intCode - 1));
                codeCountAll--;
                switch (category) {
                    case "Learning Book" -> removeBookLearning(cLearning);
                    case "Noval Book" -> removeBookNoval(cNoval);
                    case "Children Book" -> removeBookChild(cChild);
                    case "Psychological Book" -> removeBookPsychology(cPsychology);
                }

                for (int i=intCode-1;i<books.size(); i++ ){
                    int newCode = books.get(i).getSTT() - 1;
                    books.get(i).setSTT(newCode);
                    category = books.get(i).getCategory().trim();
                    int typeCode;
                    switch (category){
                        case "Learning Book" -> {
                            typeCode = books.get(i).learningCode();
                            try {
                                learningBook.updateLearningBook(connection, typeCode, 0, String.valueOf(newCode));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        case "Noval Book" -> {
                            typeCode = books.get(i).novelCode();
                            try {
                                novelBook.updateNovelBook(connection, typeCode, 0, String.valueOf(newCode)) ;
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        case "Children Book" -> {
                            typeCode = books.get(i).childCode();
                            try {
                                childrenBook.updateChildrenBook(connection, typeCode, 0, String.valueOf(newCode));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        case "Psychological Book" -> {
                            typeCode = books.get(i).psychologyCode();
                            try {
                                psychologyBook.updatePsychologyBook(connection, typeCode, 0, String.valueOf(newCode));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
    public void removeBookNoval(String code){
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCountNovel ){
            if(Integer.toString(booksNovel.get(intCode-1).novelCode()).equalsIgnoreCase(code.trim())){
                String c = String.valueOf(booksNovel.get(intCode - 1).getSTT());
                booksNovel.remove(intCode - 1);

                //Delete in Database
                try {
                    novelBook.deleteNovelBook(connection, code);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                removeBook(c);
                codeCountNovel--;

                for (int i=intCode-1;i<booksNovel.size(); i++ ){
                    int newTypeCode = booksNovel.get(i).novelCode() - 1;

                    booksNovel.get(i).novelSetCode(newTypeCode);

                    //Update in database
                    try {
                        novelBook.updateNovelBook(connection,i+2, 8, String.valueOf(newTypeCode));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    public void removeBookChild(String code){
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCountChild ){
            if(Integer.toString(booksChild.get(intCode-1).childCode()).equalsIgnoreCase(code.trim())){
                String c = String.valueOf(booksChild.get(intCode - 1).getSTT());
                booksChild.remove(intCode - 1);

                //Delete in Database
                try {
                    childrenBook.deleteChildrenBook(connection, code);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                removeBook(c);
                codeCountChild--;

                for (int i=intCode-1;i<booksChild.size(); i++ ){
                    int newTypeCode = booksChild.get(i).childCode() - 1;
                    booksChild.get(i).childSetCode(newTypeCode);

                    //Update in database
                    try {
                        childrenBook.updateChildrenBook(connection,i+2, 8, String.valueOf(newTypeCode));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    public void removeBookLearning(String code){
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCountLearning ){
            if(Integer.toString(booksLearning.get(intCode-1).learningCode()).equalsIgnoreCase(code.trim())){
                String c = String.valueOf(booksLearning.get(intCode - 1).getSTT());
                booksLearning.remove(intCode - 1);
                //Delete in Database
                try {
                    learningBook.deleteLearningBook(connection, code);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                removeBook(c);
                codeCountLearning--;

                for (int i=intCode-1;i<booksLearning.size(); i++ ){
                    int newTypeCode = booksLearning.get(i).learningCode() - 1;
                    booksLearning.get(i).leaningSetCode(newTypeCode);

                    //Update in database
                    try {
                        learningBook.updateLearningBook(connection,i+2, 8, String.valueOf(newTypeCode));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    public void removeBookPsychology(String code){
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCountPsychology ){
            if(Integer.toString(booksPsychology.get(intCode-1).psychologyCode()).equalsIgnoreCase(code.trim())){
                String c = String.valueOf(booksPsychology.get(intCode - 1).getSTT());
                booksPsychology.remove(intCode - 1);
                //Delete in Database
                try {
                    psychologyBook.deletePsychologyBook(connection, code);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                removeBook(c);
                codeCountPsychology--;

                for (int i=intCode-1;i<booksPsychology.size(); i++ ){
                    int newTypeCode = booksPsychology.get(i).psychologyCode() - 1;
                    booksPsychology.get(i).psychologySetCode(newTypeCode);

                    //Update in database
                    try {
                        psychologyBook.updatePsychologyBook(connection,i+2, 8, String.valueOf(newTypeCode));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    //Sửa Thông Tin Sách
    public void editDataBase(int indentify,String code, int col, String value ){
        switch (indentify){
            case 1:
                try {
                    childrenBook.updateChildrenBook(connection, Integer.parseInt(code), col, value);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    psychologyBook.updatePsychologyBook(connection, Integer.parseInt(code), col, value);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    novelBook.updateNovelBook(connection, Integer.parseInt(code), col, value);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    learningBook.updateLearningBook(connection, Integer.parseInt(code), col, value);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    public void editCommonValue( int indentify,String code, int col, Book book, String value){
        switch (col) {
            case 1 -> {
                book.setName(value);
                editDataBase(indentify, code, col, value);
            }
            case 2 -> {
                book.dateReConvert(value);
                editDataBase(indentify, code, col, value);
            }
            case 3 -> {
                book.setPrice(Long.parseLong(value));
                editDataBase(indentify, code, col, value);
            }
            case 4 -> {
                book.setAuthor(value);
                editDataBase(indentify, code, col, value);
            }
            case 5 -> {
                book.setPublisher(value);
                editDataBase(indentify, code, col, value);
            }
            case 6 -> {
                book.setCategory(value);
                editDataBase(indentify, code, col, value);
            }
            case 7 -> {
                book.setQuantity(Integer.parseInt(value));
                editDataBase(indentify, code, col, value);
            }
            case 8 -> {
                book.setSerialNumber(value);
                editDataBase(indentify, code, col+1, value);
            }
        }
    }
    public void editBookChild(String code, int col, String value){
        for (Book bookChild: booksChild){
            if(Integer.toString(bookChild.childCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(1, code,col, bookChild, value);
                switch (col) {
                    case 9 -> {
                        bookChild.childSetType(value);
                        editDataBase(1, code, col+1, value);
                    }
                    case 10 -> {
                        bookChild.childSetRecommentForAge(value);
                        editDataBase(1, code, col+1, value);
                    }
                }
            }
        }
    }
    public void editBookPsychology(String code, int col, String value){
        for (Book bookPsychology: booksPsychology){
            if(Integer.toString(bookPsychology.psychologyCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(2, code,col, bookPsychology, value);
                switch (col) {
                    case 9 -> {
                        bookPsychology.psychologySetType(value);
                        editDataBase(2, code, col+1, value);
                    }
                    case 10 -> {
                        bookPsychology.psychologySetRecommentForAge(value);
                        editDataBase(2, code, col+1, value);
                    }
                }
            }
        }
    }
    public void editBookNoval(String code, int col, String value){
        for (Book bookNoval: booksNovel){
            if(Integer.toString(bookNoval.novelCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(3, code,col, bookNoval, value);
                switch (col) {
                    case 9 -> {
                        bookNoval.novalSetType(value);
                        editDataBase(3, code, col+1, value);
                    }
                    case 10 -> {
                        bookNoval.novelSetAgeLimited(value);
                        editDataBase(3, code, col+1, value);
                    }
                }
            }
        }
    }
    public void editBookLearning(String code, int col, String value){
        for (Book bookLearning: booksLearning){
            if(Integer.toString(bookLearning.learningCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(4, code,col, bookLearning, value);
                switch (col) {
                    case 9 -> {
                        bookLearning.learningSetEducation(value);
                        editDataBase(4, code, col+1, value);
                    }
                    case 10 -> {
                        bookLearning.learningSetEducation_subjects(value);
                        editDataBase(4, code, col+1, value);
                    }
                }
            }
        }
    }

    //Get Book By Seri
    public Book getBookBySeri(String seri){
        Book bookU = null;
        for (Book book : books){
            if(book.getSerialNumber().equals(seri)){
                return book;
            }
        }
        return bookU;
    }

    //Book Child Methods
    public String[] childType(){
        return bookChild.childType();
    }
    public String[] childRecommentForAge(){
        return bookChild.childRecommentForAge();
    }

    //Book Learning Methods
    public String[] learningEducation(){
        return bookLearning.learningEducation();
    }
    public String[] learningEducaitionType(){
        return bookLearning.learningEducaitionType();
    }

    //Book Novel Methods
    public String[] novelType(){
        return bookNovel.novelType();
    }
    public String[] novelAgeLimited(){
        return bookNovel.novelAgeLimited();
    }

    //Book Psychology Methods
    public String[] psychologyType(){
        return bookPsychology.psychologyType();
    }
    public String[] psychologyRecommentForAge(){
        return bookPsychology.psychologyRecommentForAge();
    }
}

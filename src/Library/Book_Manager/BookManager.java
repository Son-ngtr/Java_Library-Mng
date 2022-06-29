package Library.Book_Manager;

import Database.*;
import Library.Check;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class BookManager {
    Check check = new Check();

    //DataBase
    private ChildrenBook childrenBook = new ChildrenBook();
    private NovelBook novelBook = new NovelBook();
    private PsychologyBook psychologyBook = new PsychologyBook();
    private LearningBook learningBook = new LearningBook();
    private ConectionDTB conectionDTB = new ConectionDTB();
    private Connection connection = conectionDTB.getConnect();
    private String[] useLentInfo;


    private boolean isUpdate = false;
    private int codeCountAll = 0;
    private int codeCountNovel = 0;
    private int codeCountChild = 0;
    private int codeCountLearning = 0;
    private int codeCountPsychology = 0;

    //Getter and Setter
    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean update) {
        isUpdate = update;
    }

    public String[] getUseLentInfo() {
        return useLentInfo;
    }

    public void setUseLentInfo(String[] useLentInfo) {
        this.useLentInfo = useLentInfo;
    }

    //Array List, Book List
    Book bookNovel = new Book_Novel();
    Book bookLearning = new Book_Learning();
    Book bookPsychology = new Book_Psychology();
    Book bookChild = new Book_Child();
    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<Book> booksLearning = new ArrayList<>();
    private final ArrayList<Book> booksNovel = new ArrayList<>();
    private final ArrayList<Book> booksPsychology = new ArrayList<>();
    private final ArrayList<Book> booksChild = new ArrayList<>();

    //Book Category
    public String[] bookCategory(){
        return new String[]{"Learning Book", "Noval Book", "Children Book", "Psychological Book"};
    }

    //Tạo ra một cuốn sách
    public Book createBookNovel(String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String type, String ageLimited){
        codeCountAll++;
        codeCountNovel++;
        return new Book_Novel(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountNovel, type, ageLimited);
    }
    public Book createBookChild( String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String type, String recommentForAge){
        codeCountAll++;
        codeCountChild++;
        return new Book_Child(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountChild, type, recommentForAge);
    }
    public Book createBookLearning( String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String education, String education_subjects){
        codeCountAll++;
        codeCountLearning++;
        return new Book_Learning(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountLearning, education, education_subjects);
    }
    public Book createBookPsychology( String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String type, String recommentForAge){
        codeCountAll++;
        codeCountPsychology++;
        return new Book_Psychology(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountPsychology, type, recommentForAge);
    }


    //Thêm Sách
    public void addBookNovel(Book book){
        books.add(book);
        booksNovel.add(book);
        try {
            novelBook.addNewNovelBook(
                    connection,
                    book.getSTT(),
                    book.getName(),
                    book.dateConvert(),
                    Integer.parseInt(String.valueOf(book.getPrice())),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getCategory(),
                    book.getQuantity(),
                    book.novelCode(),
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
        books.add(book);
        booksChild.add(book);
        try {
            childrenBook.addNewChildrenBook(
                    connection,
                    book.getSTT(),
                    book.getName(),
                    book.dateConvert(),
                    Integer.parseInt(String.valueOf(book.getPrice())),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getCategory(),
                    book.getQuantity(),
                    book.childCode(),
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
        books.add(book);
        booksLearning.add(book);
        try {
            learningBook.addNewLearningBook(
                    connection,
                    book.getSTT(),
                    book.getName(),
                    book.dateConvert(),
                    Integer.parseInt(String.valueOf(book.getPrice())),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getCategory(),
                    book.getQuantity(),
                    book.learningCode(),
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
        books.add(book);
        booksPsychology.add(book);
        try {
            psychologyBook.addNewPsychologyBook(
                    connection,
                    book.getSTT(),
                    book.getName(),
                    book.dateConvert(),
                    Integer.parseInt(String.valueOf(book.getPrice())),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getCategory(),
                    book.getQuantity(),
                    book.psychologyCode(),
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

    //Tổng số lượng sách (cùng loại) của thư viên
    public int totalBookSame(){
        int total = 0;
        for (Book book : books){
            total += book.getQuantity();
        }
        return total;
    }

    //Dowload Book
    public void downloadAllBook(){
        downLoadBookLearning();
        downLoadBookChild();
        downLoadBookNovel();
        downLoadBookPsychology();
    }
    public void downLoadBookLearning(){
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
                    codeCountAll,
                    String.valueOf(vector.get(1)),
                    check.dateReConvert(String.valueOf(vector.get(2))),
                    Long.parseLong(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    codeCountLearning,
                    String.valueOf(vector.get(9)),
                    String.valueOf(vector.get(10))
            );
            books.add(bookLearning);
            booksLearning.add(bookLearning);
        }
    }
    public void downLoadBookNovel(){
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
            for (int i=0; i<11; i++){
                System.out.println(String.valueOf(vector.get(i)));
            }
            Book_Novel bookNovel = new Book_Novel(
                    codeCountAll,
                    String.valueOf(vector.get(1)),
                    check.dateReConvert(String.valueOf(vector.get(2))),
                    Long.parseLong(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    codeCountNovel,
                    String.valueOf(vector.get(9)),
                    String.valueOf(vector.get(10))
            );
            books.add(bookNovel);
            booksNovel.add(bookNovel);
        }
    }
    public void downLoadBookChild(){
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
                    codeCountAll,
                    String.valueOf(vector.get(1)),
                    check.dateReConvert(String.valueOf(vector.get(2))),
                    Long.parseLong(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    codeCountChild,
                    String.valueOf(vector.get(9)),
                    String.valueOf(vector.get(10))
            );
            books.add(bookChild);
            booksChild.add(bookChild);
        }
    }

    public void downLoadBookPsychology(){
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
                    codeCountAll,
                    String.valueOf(vector.get(1)),
                    check.dateReConvert(String.valueOf(vector.get(2))),
                    Long.parseLong(String.valueOf(vector.get(3))),
                    String.valueOf(vector.get(4)),
                    String.valueOf(vector.get(5)),
                    Integer.parseInt(String.valueOf(vector.get(7))),
                    codeCountPsychology,
                    String.valueOf(vector.get(9)),
                    String.valueOf(vector.get(10))
            );
            books.add(bookPsychology);
            booksPsychology.add(bookPsychology);
        }
    }

    //List Book
    public String[][] listBookBorrow(){
        String[][] mainObj = new String[totalBookDifferent()][7];
        int count = 0;
        for (Book book : books){
            for (int i=0; i<8; i++){
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
                    default -> {
                    }
                }
            }
            count++;
        }
        return mainObj;
    }
    public String[][] listBookAll(){
        String[][] mainObj = new String[totalBookDifferent()][8];
        int count = 0;
        for (Book book : books){
            for (int i=0; i<8; i++){
                switch (i) {
                    case 0 -> mainObj[count][i] = String.valueOf(book.getSTT());
                    case 1 -> mainObj[count][i] = book.getName();
                    case 2 -> mainObj[count][i] = book.dateConvert();
                    case 3 -> mainObj[count][i] = book.moneyConvert();
                    case 4 -> mainObj[count][i] = book.getAuthor();
                    case 5 -> mainObj[count][i] = book.getPublisher();
                    case 6 -> mainObj[count][i] = book.getCategory();
                    case 7 -> mainObj[count][i] = String.valueOf(book.getQuantity());
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
        String[][] mainObj = new String[totalBookDifferentNoval()][10];
        int count = 0;
        for (Book bookNovel : booksNovel){
            for (int i=0; i<10; i++){
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
                    case 8 -> mainObj[count][i] = bookNovel.novelGetType();
                    case 9 -> mainObj[count][i] = bookNovel.novalGetAgeLimited();
                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Child
    public String[][] listBookChild(){
        String[][] mainObj = new String[totalBookDifferentChild()][10];
        int count = 0;
        for (Book bookChild : booksChild){
            for (int i=0; i<10; i++){
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
                    case 8 -> mainObj[count][i] = bookChild.childGetType();
                    case 9 -> mainObj[count][i] = bookChild.childGetRecommentForAge();
                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Learning
    public String[][] listBookLearning(){
        String[][] mainObj = new String[totalBookDifferentLearning()][10];
        int count = 0;
        for (Book bookLearning: booksLearning){
            for (int i=0; i<10; i++){
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
                    case 8 -> mainObj[count][i] = bookLearning.learningGetEducation();
                    case 9 -> mainObj[count][i] = bookLearning.learningGetEducation_subjects();
                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Psychology
    public String[][] listBookPsychology(){
        String[][] mainObj = new String[totalBookDifferentPsychology()][10];
        int count = 0;
        for (Book bookPsychology : booksPsychology){
            for (int i=0; i<10; i++){
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
                    case 8 -> mainObj[count][i] = bookPsychology.psychologyGetType();
                    case 9 -> mainObj[count][i] = bookPsychology.psychologyGetRecommentForAge();
                }
            }
            count++;
        }
        return mainObj;
    }


    //Tiêu Đề Danh Sách

    public String[] bookBorrowContent(){
        return new String[]{
                "code", "name", "price", "author", "publisher", "category", "quantity"
        };
    }
    public String[] bookContent(){
        return new String[]{
              "Number", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity"
        };
    }
    public String[] bookContentChildren(){
        return new String[]{
                "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity", "Type", "Age Recomment"
        };
    }
    public String[] bookContentNoval(){
        return new String[]{
                "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity" , "Type", "Age Recomment"
        };
    }
    public String[] bookContentLearning(){
        return new String[]{
                "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity", "Education", "Subjects"
        };
    }
    public String[] bookContentPsychology(){
        return new String[]{
                "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity","Type", "Age Recomment"
        };
    }


    //Xóa Sách
    public void removeBook(String code){
        //New Version
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCountAll ){
            if(Integer.toString(books.get(intCode-1).getSTT()).equalsIgnoreCase(code.trim())){
                System.out.println(books.get(intCode -1 ).getSTT());
                String cLearning = Integer.toString(books.get(intCode-1).learningCode());
                String cNoval = Integer.toString(books.get(intCode -1).novelCode());
                String cChild = Integer.toString(books.get(intCode -1).childCode());
                String cPsychology = Integer.toString(books.get(intCode - 1).psychologyCode());
                String category = books.get(intCode -1 ).getCategory().trim();
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
                    switch (category){
                        case "Learning Book" -> {
                            try {
                                learningBook.updateLearningBook(connection, i+1, 0, String.valueOf(newCode));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        case "Noval Book" -> {
                            try {
                                novelBook.updateNovelBook(connection, i+1, 0, String.valueOf(newCode)) ;
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        case "Children Book" -> {
                            try {
                                childrenBook.updateChildrenBook(connection, i+1, 0, String.valueOf(newCode));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        case "Psychological Book" -> {
                            try {
                                psychologyBook.updatePsychologyBook(connection, i+1, 0, String.valueOf(newCode));
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
        }
    }
    public void editBookChild(String code, int col, String value){
        for (Book bookChild: booksChild){
            if(Integer.toString(bookChild.childCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(1, code,col, bookChild, value);
                switch (col) {
                    case 8 -> {
                        bookChild.childSetType(value);
                        editDataBase(1, code, col +1, value);
                    }
                    case 9 -> {
                        bookChild.childSetRecommentForAge(value);
                        editDataBase(1, code, col +1, value);
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
                    case 8 -> {
                        bookPsychology.psychologySetType(value);
                        editDataBase(2, code, col+1, value);
                    }
                    case 9 -> {
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
                    case 8 -> {
                        bookNoval.novalSetType(value);
                        editDataBase(3, code, col+1, value);
                    }
                    case 9 -> {
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
                    case 8 -> {
                        bookLearning.learningSetEducation(value);
                        editDataBase(4, code, col+1, value);
                    }
                    case 9 -> {
                        bookLearning.learningSetEducation_subjects(value);
                        editDataBase(4, code, col+1, value);
                    }
                }
            }
        }
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

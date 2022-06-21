package Library.Book_Manager;

import Library.Check;

import java.util.ArrayList;
import java.util.Calendar;

public class BookManager {
    private boolean isUpdate = false;
//    private int backGroundCode = 0;
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
    }
    public void addBookChild(Book book){
        books.add(book);
        booksChild.add(book);
    }
    public void addBookLearning(Book book){
        books.add(book);
        booksLearning.add(book);
    }
    public void addBookPsychology(Book book){
        books.add(book);
        booksPsychology.add(book);
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

    //Danh Sách All
    public String[][] listBookAll(){
        String[][] mainObj = new String[totalBookDifferent()][8];
        int count = 0;
        for (Book book : books){
            for (int i=0; i<8; i++){
                switch (i) {
                    case 0 -> mainObj[count][i] = String.valueOf(book.getCode());
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
                    case 0 -> mainObj[count][i] = String.valueOf(bookNovel.novelCode());
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
                    case 0 -> mainObj[count][i] = String.valueOf(bookChild.childCode());
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
                    case 0 -> mainObj[count][i] = String.valueOf(bookLearning.learningCode());
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
                    case 0 -> mainObj[count][i] = String.valueOf(bookPsychology.psychologyCode());
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
    public String[] bookContent(){
        return new String[]{
              "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity"
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
        boolean codeReduce = false;
        for (int i=0; i<books.size(); i++){
            if(Integer.toString(books.get(i).getCode()).equalsIgnoreCase(code.trim()) && !codeReduce){
                String cLearning = Integer.toString(books.get(i).learningCode());
                String cNoval = Integer.toString(books.get(i).novelCode());
                String cChild = Integer.toString(books.get(i).childCode());
                String cPsychology = Integer.toString(books.get(i).psychologyCode());
                String category = books.get(i).getCategory().trim();
                books.remove(books.get(i));
                switch (category) {
                    case "Learning Book" -> removeBookLearning(cLearning);
                    case "Noval Book" -> removeBookNoval(cNoval);
                    case "Children Book" -> removeBookChild(cChild);
                    case "Psychological Book" -> removeBookPsychology(cPsychology);
                }
                codeReduce = true;
                codeCountAll--;
            }
            if (codeReduce && i!=books.size()){
                books.get(i).setCode(books.get(i).getCode() - 1);
            }
        }
    }
    public void removeBookNoval(String code){
        boolean codeReduce = false;
        for (int i=0; i<booksNovel.size(); i++){
            if(Integer.toString(booksNovel.get(i).novelCode()).equalsIgnoreCase(code.trim()) && !codeReduce){
                String c = String.valueOf(booksNovel.get(i).getCode());
                booksNovel.remove(booksNovel.get(i));
                removeBook(c);
                codeReduce = true;
                codeCountNovel--;
            }
            if (codeReduce && i!=booksNovel.size()){
                booksNovel.get(i).novelSetCode(booksNovel.get(i).novelCode() - 1);
            }
        }
    }
    public void removeBookChild(String code){
        boolean codeReduce = false;
        for (int i=0; i<booksChild.size(); i++){
            if(Integer.toString(booksChild.get(i).childCode()).equalsIgnoreCase(code.trim()) && !codeReduce){
                String c = String.valueOf(booksChild.get(i).getCode());
                booksChild.remove(booksChild.get(i));
                removeBook(c);
                codeReduce = true;
                codeCountChild--;
            }
            if (codeReduce && i!=booksChild.size()){
                booksChild.get(i).childSetCode(booksChild.get(i).childCode() - 1);
            }
        }
    }
    public void removeBookLearning(String code){
        boolean codeReduce = false;
        for (int i=0; i<booksLearning.size(); i++){
            if(Integer.toString(booksLearning.get(i).learningCode()).equalsIgnoreCase(code.trim()) && !codeReduce){
                String c = String.valueOf(booksLearning.get(i).getCode());
                booksLearning.remove(booksLearning.get(i));
                removeBook(c);
                codeReduce = true;
                codeCountLearning--;
            }
            if (codeReduce && i!=booksLearning.size()){
                booksLearning.get(i).leaningSetCode(booksLearning.get(i).learningCode() - 1);
            }
        }
    }
    public void removeBookPsychology(String code){
        boolean codeReduce = false;
        for (int i=0; i<booksPsychology.size(); i++){
            if(Integer.toString(booksPsychology.get(i).psychologyCode()).equalsIgnoreCase(code.trim()) && !codeReduce){
                String c = String.valueOf(booksPsychology.get(i).getCode());
                booksPsychology.remove(booksPsychology.get(i));
                removeBook(c);
                codeReduce = true;
                codeCountPsychology--;
            }
            if (codeReduce && i!=booksPsychology.size()){
                booksPsychology.get(i).psychologySetCode(booksPsychology.get(i).psychologyCode() - 1);
            }
        }
    }


    //Sửa Thông Tin Sách
    public void editCommonValue(int col, Book book, String value){
        switch (col) {
            case 1 -> book.setName(value);
            case 2 -> book.dateReConvert(value);
            case 3 -> book.setPrice(Long.parseLong(value));
            case 4 -> book.setAuthor(value);
            case 5 -> book.setPublisher(value);
            case 6 -> book.setCategory(value);
            case 7 -> book.setQuantity(Integer.parseInt(value));
        }
    }
    public void editBook(String code, int col, String value){
        for (Book book: books){
            if(Integer.toString(book.getCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(col, book, value);
            }
        }
    }
    public void editBookChild(String code, int col, String value){
        for (Book bookChild: booksChild){
            if(Integer.toString(bookChild.childCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(col, bookChild, value);
                switch (col) {
                    case 8 -> bookChild.childSetType(value);
                    case 9 -> bookChild.childSetRecommentForAge(value);
                }
            }
        }
    }
    public void editBookPsychology(String code, int col, String value){
        for (Book bookPsychology: booksPsychology){
            if(Integer.toString(bookPsychology.psychologyCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(col, bookPsychology, value);
                switch (col) {
                    case 8 -> bookPsychology.psychologySetType(value);
                    case 9 -> bookPsychology.psychologySetRecommentForAge(value);
                }
            }
        }
    }
    public void editBookNoval(String code, int col, String value){
        for (Book bookNoval: booksNovel){
            if(Integer.toString(bookNoval.novelCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(col, bookNoval, value);
                switch (col) {
                    case 8 -> {
                        System.out.println("col ");
                        bookNoval.novalSetType(value);
                    }
                    case 9 -> bookNoval.novelSetAgeLimited(value);
                }
            }
        }
    }
    public void editBookLearning(String code, int col, String value){
        for (Book bookLearning: booksLearning){
            if(Integer.toString(bookLearning.learningCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(col, bookLearning, value);
                switch (col) {
                    case 8 -> bookLearning.learningSetEducation(value);
                    case 9 -> bookLearning.learningSetEducation_subjects(value);
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

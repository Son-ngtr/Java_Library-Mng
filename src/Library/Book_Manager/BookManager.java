package Library.Book_Manager;

import Library.Check;

import java.util.ArrayList;
import java.util.Calendar;

public class BookManager extends Check {
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
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> booksLearning = new ArrayList<>();
    private ArrayList<Book> booksNovel = new ArrayList<>();
    private ArrayList<Book> booksPsychology = new ArrayList<>();
    private ArrayList<Book> booksChild = new ArrayList<>();

    //Book Category
    public String[] bookCategory(){
        String bookCategory[] = {"Learning Book", "Noval Book", "Children Book", "Psychological Book"};
        return bookCategory;
    }

    //Tạo ra một cuốn sách
    public Book createBookNovel(String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String type, String ageLimited){
        codeCountAll++;
        codeCountNovel++;
        Book book = new Book_Novel(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountNovel, type, ageLimited);
        return book;
    }
    public Book createBookChild( String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String type, String recommentForAge){
        codeCountAll++;
        codeCountChild++;
        Book book = new Book_Child(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountChild, type, recommentForAge);
        return book;
    }
    public Book createBookLearning( String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String education, String education_subjects){
        codeCountAll++;
        codeCountLearning++;
        Book book = new Book_Learning(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountLearning, education, education_subjects);
        return book;
    }
    public Book createBookPsychology( String name, Calendar dateAdded, Long price, String author, String publisher, int quantity, String type, String recommentForAge){
        codeCountAll++;
        codeCountPsychology++;
        Book book = new Book_Psychology(codeCountAll, name, dateAdded, price, author, publisher, quantity, codeCountPsychology, type, recommentForAge);
        return book;
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
                switch (i){
                    case 0:
                        mainObj[count][i] = String.valueOf(book.getCode());
                        break;
                    case 1:
                        mainObj[count][i] = book.getName();
                        break;
                    case 2:
                        mainObj[count][i] = book.dateConvert();
                        break;
                    case 3:
                        mainObj[count][i] = book.moneyConvert();
                        break;
                    case 4:
                        mainObj[count][i] = book.getAuthor();
                        break;
                    case 5:
                        mainObj[count][i] = book.getPublisher();
                        break;
                    case 6:
                        mainObj[count][i] = book.getCategory();
                        break;
                    case 7:
                        mainObj[count][i] = String.valueOf(book.getQuantity());
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Novel
    public String[][] listBookNovel(){
        String[][] mainObj = new String[totalBookDifferentNoval()][8];
        int count = 0;
        for (Book bookNovel : booksNovel){
            for (int i=0; i<8; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = String.valueOf(bookNovel.novelCode());
                        break;
                    case 1:
                        mainObj[count][i] = bookNovel.getName();
                        break;
                    case 2:
                        mainObj[count][i] = bookNovel.dateConvert();
                        break;
                    case 3:
                        mainObj[count][i] = bookNovel.moneyConvert();
                        break;
                    case 4:
                        mainObj[count][i] = bookNovel.getAuthor();
                        break;
                    case 5:
                        mainObj[count][i] = bookNovel.getPublisher();
                        break;
                    case 6:
                        mainObj[count][i] = bookNovel.getCategory();
                        break;
                    case 7:
                        mainObj[count][i] = String.valueOf(bookNovel.getQuantity());
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Child
    public String[][] listBookChild(){
        String[][] mainObj = new String[totalBookDifferentChild()][8];
        int count = 0;
        for (Book bookChild : booksChild){
            for (int i=0; i<8; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = String.valueOf(bookChild.getCode());
                        break;
                    case 1:
                        mainObj[count][i] = bookChild.getName();
                        break;
                    case 2:
                        mainObj[count][i] = bookChild.dateConvert();
                        break;
                    case 3:
                        mainObj[count][i] = bookChild.moneyConvert();
                        break;
                    case 4:
                        mainObj[count][i] = bookChild.getAuthor();
                        break;
                    case 5:
                        mainObj[count][i] = bookChild.getPublisher();
                        break;
                    case 6:
                        mainObj[count][i] = bookChild.getCategory();
                        break;
                    case 7:
                        mainObj[count][i] = String.valueOf(bookChild.getQuantity());
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Learning
    public String[][] listBookLearning(){
        String[][] mainObj = new String[totalBookDifferentLearning()][8];
        int count = 0;
        for (Book bookLearning: booksLearning){
            for (int i=0; i<8; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = String.valueOf(bookLearning.getCode());
                        break;
                    case 1:
                        mainObj[count][i] = bookLearning.getName();
                        break;
                    case 2:
                        mainObj[count][i] = bookLearning.dateConvert();
                        break;
                    case 3:
                        mainObj[count][i] = bookLearning.moneyConvert();
                        break;
                    case 4:
                        mainObj[count][i] = bookLearning.getAuthor();
                        break;
                    case 5:
                        mainObj[count][i] = bookLearning.getPublisher();
                        break;
                    case 6:
                        mainObj[count][i] = bookLearning.getCategory();
                        break;
                    case 7:
                        mainObj[count][i] = String.valueOf(bookLearning.getQuantity());
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //List Book Psychology
    public String[][] listBookPsychology(){
        String[][] mainObj = new String[totalBookDifferentPsychology()][8];
        int count = 0;
        for (Book bookPsychology : booksPsychology){
            for (int i=0; i<8; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = String.valueOf(bookPsychology.getCode());
                        break;
                    case 1:
                        mainObj[count][i] = bookPsychology.getName();
                        break;
                    case 2:
                        mainObj[count][i] = bookPsychology.dateConvert();
                        break;
                    case 3:
                        mainObj[count][i] = bookPsychology.moneyConvert();
                        break;
                    case 4:
                        mainObj[count][i] = bookPsychology.getAuthor();
                        break;
                    case 5:
                        mainObj[count][i] = bookPsychology.getPublisher();
                        break;
                    case 6:
                        mainObj[count][i] = bookPsychology.getCategory();
                        break;
                    case 7:
                        mainObj[count][i] = String.valueOf(bookPsychology.getQuantity());
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }


    //Tiêu Đề Danh Sách
    public String[] bookContent(){
        String[] obj = {
              "Code", "Name", "Date Added", "Price", "Author", "Publisher", "Category", "Quantity"
        };
        return obj;
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
                switch (category){
                    case "Learning Book":
                        removeBookLearning(cLearning);
                        break;
                    case "Noval Book":
                        removeBookNoval(cNoval);
                        break;
                    case "Children Book":
                        removeBookChild(cChild);
                        break;
                    case "Psychological Book":
                        removeBookPsychology(cPsychology);
                        break;
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
                booksNovel.get(i).setCode(booksNovel.get(i).getCode() - 1);
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
                booksChild.get(i).setCode(booksChild.get(i).getCode() - 1);
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
                booksLearning.get(i).setCode(booksLearning.get(i).getCode() - 1);
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
                booksPsychology.get(i).setCode(booksPsychology.get(i).getCode() - 1);
            }
        }
    }


    //Sửa Thông Tin Sách
    public void editCommonValue(int col, Book book, String value){
        switch (col){
            case 1:
                book.setName(value);
                break;
            case 2:
                book.dateReConvert(value);
                break;
            case 3:
                book.setPrice(Long.parseLong(value));
                break;
            case 4:
                book.setAuthor(value);
                break;
            case 5:
                book.setPublisher(value);
                break;
            case 6:
                book.setCategory(value);
                break;
            case 7:
                book.setQuantity(Integer.parseInt(value));
                break;
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
                switch (col){
                    case 8:
                        bookChild.childSetType(value);
                        break;
                    case 9:
                        bookChild.childSetRecommentForAge(value);
                        break;

                }
            }
        }
    }
    public void editBookPsychology(String code, int col, String value){
        for (Book bookPsychology: booksPsychology){
            if(Integer.toString(bookPsychology.psychologyCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(col, bookPsychology, value);
                switch (col){
                    case 8:
                        bookPsychology.psychologySetType(value);
                        break;
                    case 9:
                        bookPsychology.psychologySetRecommentForAge(value);
                        break;
                }
            }
        }
    }
    public void editBookNoval(String code, int col, String value){
        for (Book bookNoval: booksNovel){
            if(Integer.toString(bookNoval.novelCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(col, bookNoval, value);
                switch (col){
                    case 8:
                        bookNoval.novalSetType(value);
                        break;
                    case 9:
                        bookNoval.novalSetAgeLimited(value);
                        break;
                }
            }
        }
    }
    public void editBookLearning(String code, int col, String value){
        for (Book bookLearning: booksLearning){
            if(Integer.toString(bookLearning.learningCode()).equalsIgnoreCase(code.trim())){
                editCommonValue(col, bookLearning, value);
                switch (col){
                    case 8:
                        bookLearning.learningSetEducation(value);
                        break;
                    case 9:
                        bookLearning.learningSetEducation_subjects(value);
                        break;
                }
            }
        }
    }
}

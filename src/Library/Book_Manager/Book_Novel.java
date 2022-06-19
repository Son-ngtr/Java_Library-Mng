package Library.Book_Manager;

import java.util.Calendar;

public class Book_Novel extends Book{
    private int typeCode;
    private String type;
    private String ageLimited;

    //Constructor
    public Book_Novel(){

    }

    public Book_Novel(int code, String name, Calendar dateAdded, Long price, String author, String publisher, int quantity,int typeCode, String type, String ageLimited) {
        super(code, name, dateAdded, price, author, publisher, "Noval Book", quantity);
        this.typeCode = typeCode;
        this.type = type;
        this.ageLimited = ageLimited;
    }

    //Getter and Setter
    public String getType() {
        return type;
    }

    @Override
    public void novalSetType(String type) {
        this.type = type;
    }

    public String getAgeLimited() {
        return ageLimited;
    }

    public void novalSetAgeLimited(String ageLimited) {
        this.ageLimited = ageLimited;
    }

    //Novel Code
    public int novelCode(){
        return typeCode;
    }

    //Novel Type
    public String[] novelType(){
        String[] type = {"Science Fiction", "Sports", "Horror", "Slice Of Life", "Fantasy", "Comedy", "Adult"};
        return type;
    }
    public String[] novelAgeLimited(){
        String age[] = {"6->10", "11->16", "16->18", "18+"};
        return age;
    }
}
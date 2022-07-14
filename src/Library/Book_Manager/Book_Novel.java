package Library.Book_Manager;

import java.util.Calendar;

public class Book_Novel extends Book{
    private int typeCode;
    private String type;
    private String ageLimited;

    //Constructor
    public Book_Novel(){

    }

    public Book_Novel(int code, String name, Calendar dateAdded, Long price, String author, String publisher, int quantity,int typeCode, String serialNumber,String type, String ageLimited) {
        super(code, name, dateAdded, price, author, publisher, "Noval Book", quantity, serialNumber);
        this.typeCode = typeCode;
        this.type = type;
        this.ageLimited = ageLimited;
    }

    //Getter and Setter
    public String novelGetType() {
        return type;
    }

    public void novalSetType(String type) {
        this.type = type;
    }

    public String novalGetAgeLimited() {
        return ageLimited;
    }

    public void novelSetAgeLimited(String ageLimited) {
        this.ageLimited = ageLimited;
    }

    //Novel Code
    public int novelCode(){
        return typeCode;
    }
    public void novelSetCode(int typeCode){
        this.typeCode = typeCode;
    }

    //Novel Type
    public String[] novelType(){
        return new String[]{"Science Fiction", "Sports", "Slice Of Life", "Fantasy", "Comedy", "Adult"};
    }
    public String[] novelAgeLimited(){
        return new String[]{"6->10", "11->16", "16->18", "18+"};
    }
}

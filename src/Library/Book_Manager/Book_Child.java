package Library.Book_Manager;

import java.util.Calendar;

public class Book_Child extends Book{
    private int typeCode;
    private String type ;
    private String recommentForAge;

    //Constructor
    public Book_Child(){

    }

    public Book_Child(int code, String name, Calendar dateAdded, Long price, String author, String publisher, int quantity,int typeCode, String type, String recommentForAge) {
        super(code, name, dateAdded, price, author, publisher, "Children Book", quantity);
        this.typeCode = typeCode;
        this.type = type;
        this.recommentForAge = recommentForAge;
    }

    //Getter and Setter
    public String getType() {
        return type;
    }

    public void childSetType(String type) {
        this.type = type;
    }

    public String getRecommentForAge() {
        return recommentForAge;
    }

    public void childSetRecommentForAge(String recommentForAge) {
        this.recommentForAge = recommentForAge;
    }

    //ChildCode
    public int childCode(){
        return typeCode;
    }

    //ChildType
    public String[] childType(){
        String[] type = {"Classic", "Modern"};
        return type;
    }

    //ChildRecommentForAge
    public String[] childRecommentForAge(){
        String[] recommentForAge = {"6->10", "11->16", "16+"};
        return recommentForAge;
    }
}

package Library.Book_Manager;

import java.util.Calendar;

public class Book_Psychology extends Book{
    private int typeCode;
    private String type ;
    private String recommentForAge;

    //Constructor
    public Book_Psychology(){}
    public Book_Psychology(int code, String name, Calendar dateAdded, Long price, String author, String publisher, int quantity,int typeCode, String type, String recommentForAge) {
        super(code, name, dateAdded, price, author, publisher, "Psychological Book", quantity);
        this.typeCode = typeCode;
        this.type = type;
        this.recommentForAge = recommentForAge;
    }

    //Getter and Setter
    public String getType() {
        return type;
    }

    @Override
    public void psychologySetType(String type) {
        this.type = type;
    }

    public String getRecommentForAge() {
        return recommentForAge;
    }

    @Override
    public void psychologySetRecommentForAge(String recommentForAge) {
        this.recommentForAge = recommentForAge;
    }

    //Psychology code
    public int psychologyCode(){
        return typeCode;
    }

    //Psychology Type
    public String[] psychologyType(){
        String[] type = {"Awareness", "Behavior", "Criminal"};
        return type;
    }

    //Psychology RecommentForAge
    public String[] psychologyRecommentForAge(){
        String[] recommentForAge = {"6->10", "11->16", "16->18", "18+"};
        return recommentForAge;
    }
}

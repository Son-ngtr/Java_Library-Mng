package Library.Book_Manager;

import java.util.Calendar;

public class Book_Learning extends Book{
    private int typeCode;
    private String education ;
    private String education_subjects;

    //Constructor
    public Book_Learning(){

    }

    public Book_Learning(int code, String name, Calendar dateAdded, Long price, String author, String publisher, int quantity,int typeCode, String education, String education_subjects) {
        super(code, name, dateAdded, price, author, publisher, "Learning Book", quantity);
        this.typeCode = typeCode;
        this.education = education;
        this.education_subjects = education_subjects;
    }

    //Getter and Setter
    public String learningGetEducation() {
        return education;
    }

    public void learningSetEducation(String education) {
        this.education = education;
    }

    public String learningGetEducation_subjects() {
        return education_subjects;
    }

    public void learningSetEducation_subjects(String education_subjects) {
        this.education_subjects = education_subjects;
    }

    //Learning Code
    public int learningCode(){
        return typeCode;
    }
    public void leaningSetCode(int typeCode){
        this.typeCode = typeCode;
    }

    //Education
    public String[] learningEducation(){
        return new String[]{"Kindergarten", "Primary School", "Secondary School", "High School", "College"};
    }

    //Education Type
    public String[] learningEducaitionType(){
        return new String[]{"Math", "Psychic", "Chemistry", "History", "Literary", "English", "Japanese", "Chinese", "Biological"};
    }
}

package Library.Table_Manager;

public class Table {
    private int STT;
    private String Name;
    private int Table;
    private String BookName;
    private int Quantity;

    //Constructor
    public Table(int STT, String name, int table, String bookName, int quantity) {
        this.STT = STT;
        Name = name;
        Table = table;
        BookName = bookName;
        Quantity = quantity;
    }

    //Getter and Setter
    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTable() {
        return Table;
    }

    public void setTable(int table) {
        Table = table;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}

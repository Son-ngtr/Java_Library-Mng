package Library.Table_Manager;

import Database.Staff_Database;
import Database.Table_DataBase;
import Library.Check;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class TableManager {
    private Check check = new Check();
    private boolean isUpdate = false;
    private int codeCount = 0;
    private Connection connection;
    private Table_DataBase table_dataBase = new Table_DataBase();

    //Constructor
    public TableManager(Connection connection){
        this.connection = connection;
    }

    //Getter and Setter
    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    //List Table
    ArrayList<Table> tables = new ArrayList<>();
    ArrayList<Integer> currentUseTable = new ArrayList<Integer>();

    //Table Content
    public String[] tableContent(){
        return new String[]{"STT", "Name", "Table", "Book Name", "Quantity"};
    }
    public int tableContentIndex(String s){
        switch (s){
            case "STT":
                return 0;
            case "Name":
                return 1;
            case "Table":
                return 2;
            case "Book Name":
                return 3;
            case "Quantity":
                return 4;
        }
        return 100;
    }

    //Max Table
    public int maxTable(){
        return 20;
    }

    //Check Used Table
    public boolean checkUsedTable(int table){
        for (Integer currentTable : currentUseTable){
            if(currentTable == table){
                return true;
            }
        }
        return false;
    }

    //Total Table Full
    public int totalTableFull(){
        return codeCount;
    }

    //Table Number
    public String[] tableCategory(){
        String[] t = new String[maxTable()];
        for (int i=1; i<=20; i++){
            t[i-1] = Integer.toString(i);
        }

        return t;
    }

    //Create Table
    public Table createTable(String name, int table, String bookName, int quantity){
        codeCount++;
        return new Table(codeCount, name, table, bookName, quantity);
    }
    //Add Table
    public void addTable(Table table){
        tables.add(table);
        currentUseTable.add(table.getTable());
        try {
            table_dataBase.addNewTable(
                    connection,
                    table.getSTT(),
                    table.getName(),
                    table.getTable(),
                    table.getBookName(),
                    table.getQuantity()
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //DownLoad Table
    public void downloadTable(){
        Vector<Vector<Object>> vectors = null;
        try {
            vectors = table_dataBase.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Vector<Object> vector : vectors){
            codeCount++;
            Table table = new Table(
                    codeCount,
                    String.valueOf(vector.get(1)),
                    Integer.parseInt(String.valueOf(vector.get(2))),
                    String.valueOf(vector.get(3)),
                    Integer.parseInt(String.valueOf(vector.get(4)))
            );
            tables.add(table);
            currentUseTable.add(Integer.parseInt(String.valueOf(vector.get(2))));
        }
    }

    //Table List
    public String[][] listTable(){
        String[][] mainObj = new String[totalTableFull()][tableContent().length];
        int count = 0;
        for (Table table : tables){
            for (int i=0; i<tableContent().length; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = String.valueOf(table.getSTT());
                        break;
                    case 1:
                        mainObj[count][i] = table.getName();
                        break;
                    case 2:
                        mainObj[count][i] = String.valueOf(table.getTable());
                        break;
                    case 3:
                        mainObj[count][i] = table.getBookName();
                        break;
                    case 4:
                        mainObj[count][i] = String.valueOf(table.getQuantity());
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //Table Delete
    public void removeTable(String code){
        int intCode = Integer.parseInt(code);
        if (intCode <= codeCount){
            if(Integer.toString(tables.get(intCode-1).getSTT()).equalsIgnoreCase(code.trim())){
                tables.remove(intCode - 1);
                try {
                    table_dataBase.deleteTable(connection, code);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                codeCount--;
                for (int i=intCode-1; i<tables.size(); i++){
                    int newCode = tables.get(i).getSTT() - 1;
                    tables.get(i).setSTT(newCode);

                    try {
                        table_dataBase.updateTable(connection, i+2, 0, String.valueOf(newCode));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //Table Fix
    public void editDatabase(String code, int col, String value){
        try {
            table_dataBase.updateTable(connection, Integer.parseInt(code), col, value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editTable(String code, int col, String value){
        for (Table table: tables){
            if(Integer.toString(table.getSTT()).equalsIgnoreCase(code.trim())){
                switch (col) {
                    case 1 -> {
                        table.setName(value);
                        editDatabase(code, col, value);
                    }
                    case 2 -> {
                        table.setTable(Integer.parseInt(value));
                        editDatabase(code, col, value);
                    }
                    case 3 -> {
                        table.setBookName(value);
                        editDatabase(code, col, value);
                    }
                    case 4 -> {
                        table.setQuantity(Integer.parseInt(value));
                        editDatabase(code, col, value);
                    }
                }
            }
        }
    }
}

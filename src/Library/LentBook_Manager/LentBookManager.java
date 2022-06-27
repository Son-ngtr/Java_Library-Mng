package Library.LentBook_Manager;

import Library.Staff_Manager.Staff;

import java.util.ArrayList;
import java.util.Calendar;

public class LentBookManager {
    private int codeCount = 0;

    //Lent Book List
    private final ArrayList<LentBook> lentBooks = new ArrayList<>();

    //LentBook Header
    public String[] lentBookContent(){
        return new String[]{"STT", "Name Book", "Quantity", "LentMoney/day" ,"Remain Time"};
    }

    //Create lentBook
    public LentBook createLentBook(String bookName, int numberOfBook, Long lentMoney, Long getLentMoney, Calendar endDate){
        codeCount++;
        return new LentBook(codeCount, bookName, numberOfBook, lentMoney, endDate);
    }

    //Add Lent Book
    public void addLentBook(LentBook lentBook){
        lentBooks.add(lentBook);
    }

    //Total Lent Book
    public int totalLentBook(){
        return lentBooks.size();
    }

    //Lent Book List
    public String[][] listLentBook(){
        String[][] mainObj = new String[totalLentBook()][lentBookContent().length];
        int count = 0;
        for (LentBook lentBook : lentBooks){
            for (int i=0; i<lentBookContent().length; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = Integer.toString(lentBook.getSTT());
                        break;
                    case 1:
                        mainObj[count][i] = lentBook.getBookName();
                        break;
                    case 2:
                        mainObj[count][i] = Integer.toString(lentBook.getNumberOfBook());
                        break;
                    case 3:
                        mainObj[count][i] = Long.toString(lentBook.getLentMoney());
                        break;
                    case 4:
                        mainObj[count][i] = "";
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //Lent Book Delete
    public void removeLentBook(String code){
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCount ){
            if(Integer.toString(lentBooks.get(intCode-1).getSTT()).equalsIgnoreCase(code.trim())){
                lentBooks.remove(intCode - 1);
                codeCount--;

                for (int i=intCode-1;i<lentBooks.size(); i++ ){
                    lentBooks.get(i).setSTT(lentBooks.get(i).getSTT() - 1);
                }
            }

        }
    }
}

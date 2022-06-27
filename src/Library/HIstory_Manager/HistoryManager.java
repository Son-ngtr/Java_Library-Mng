package Library.HIstory_Manager;

import Library.Staff_Manager.Staff;

import java.util.ArrayList;
import java.util.Calendar;

public class HistoryManager {
    private boolean isUpdate = false;
    private int codeCount = 0;

    //Getter and Setter
    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    //History list
    ArrayList<History> histories = new ArrayList<>();

    //History Header
    public String[] historyContent(){
        return new String[]{"ID", "Name", "Phone Number","RegisDate", "ExpDate", "Book Name", "Quantity", "Fine Money"};
    }

    //Create History
    public History createHistory(String name, String phoneNumber, Calendar regisDate, Calendar expDate, String bookName, int quantity, Long fineMoney){
        codeCount++;
        return new History(codeCount, name, phoneNumber, regisDate, expDate, bookName, quantity,fineMoney);
    }

    //Add History
    public void addHistory(History history){
        histories.add(history);
    }

    //Total History
    public int totalHistory(){
        return codeCount;
    }

    //History List
    public String[][] listHistory(){
        String[][] mainObj = new String[totalHistory()][historyContent().length];
        int count = 0;
        for (History history : histories){
            for (int i=0; i<historyContent().length; i++){
                switch (i){
                    case 0:
                        mainObj[count][i] = "HL" + String.valueOf(history.getID());
                        break;
                    case 1:
                        mainObj[count][i] = history.getName();
                        break;
                    case 2:
                        mainObj[count][i] = history.getPhoneNumber();
                        break;
                    case 3:
                        mainObj[count][i] = history.dateConvert(history.getRegisDate());
                        break;
                    case 4:
                        mainObj[count][i] = history.dateConvert(history.getExpDate());
                        break;
                    case 5:
                        mainObj[count][i] = history.getBookName();
                        break;
                    case 6:
                        mainObj[count][i] = Integer.toString(history.getQuantity()) ;
                        break;
                    case 7:
                        mainObj[count][i] = Long.toString(history.getFineMoney()) ;
                        break;
                    default:
                        break;
                }
            }
            count++;
        }
        return mainObj;
    }

    //History Delete
    public void removeHistory(String code){
        int intCode = Integer.parseInt(code);
        if(intCode <= codeCount ){
            if(Integer.toString(histories.get(intCode-1).getID()).equalsIgnoreCase(code.trim())){
                histories.remove(intCode - 1);
                codeCount--;

                for (int i=intCode-1;i<histories.size(); i++ ){
                    histories.get(i).setID(histories.get(i).getID() - 1);
                }
            }

        }
    }

    //History Fix
    public void editStaff(String code, int col, String value){
        for (History history: histories){
            if(Integer.toString(history.getID()).equalsIgnoreCase(code.trim())){
                switch (col) {
                    case 1 -> history.setName(value);
                    case 2 -> history.setPhoneNumber(value);
                    case 3 -> history.dateReConvert(value, true);
                    case 4 -> history.dateReConvert(value, false);
                    case 5 -> history.setBookName(value);
                    case 6 -> history.setQuantity(Integer.parseInt(value));
                    case 7 -> history.setFineMoney(Long.valueOf(value));
                    default -> {
                    }
                }
            }
        }
    }
}

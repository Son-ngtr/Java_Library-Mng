package Database;

import java.sql.*;
import java.util.Vector;

public class LentBook_DataBase {
    private String currentID;


    //Constructor
    public LentBook_DataBase(String ID){
        this.currentID = ID;
    }

    //Getter and Setter
    public String getCurrentID() {
        return currentID;
    }

    public void setCurrentID(String currentID) {
        this.currentID = currentID;
    }

    public boolean checkLentBook(Connection connection, String Code) throws SQLException, ClassNotFoundException {

        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from lentbook" +currentID+ " where STT = '" + Code + "'";
        Statement stm1 = connection.createStatement();
        ResultSet rs = stm1.executeQuery(sql);

        // Trả về kết quả
        if (!rs.next()) {
            return false;
        }
        return true;
    }

    public Vector<Vector<Object>> getAll(Connection connection) throws ClassNotFoundException, SQLException {
        Vector<Vector<Object>> data = new Vector<>();

        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from lentbook" + currentID);
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int Code = rs.getInt(1);
            String BookName = rs.getString(2);
            int NumberOfBook = rs.getInt(3);
            String LentMoney = rs.getString(4);
            String EndDate = rs.getString(5);
            String SerialNumber = rs.getString(6);
            String TimeLate = rs.getString(7);

            // Ghi vào vector
            Vector<Object> temp = new Vector<>();
            temp.add(Code);
            temp.add(BookName);
            temp.add(NumberOfBook);
            temp.add(LentMoney);
            temp.add(EndDate);
            temp.add(SerialNumber);
            temp.add(TimeLate);

            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public int updateLentBook(Connection connection, int Code, int col, String value)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        String sql;
        Statement stm1;

        switch (col){
            case 0:
                sql = "UPDATE lentbook"+ currentID +" set STT='" + Integer.parseInt(value) + "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 1:
                sql = "UPDATE lentbook"+ currentID +" set BookName='" + value +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 2:
                sql = "UPDATE lentbook"+ currentID +" set NumberOfBook='" + Integer.parseInt(value) +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 3:
                sql = "UPDATE lentbook"+ currentID +" set LentMoney='" + value +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 4:
                sql = "UPDATE lentbook"+ currentID +" set endDate='" + value +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                return updateStatus;
            case 5:
                sql = "UPDATE lentbook"+ currentID +" set SerialNumber='" + value +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                return updateStatus;
            case 6:
                sql = "UPDATE lentbook"+ currentID +" set TimeLate='" + value +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                return updateStatus;
        }
        return updateStatus;
    }

    public void addNewLentBook(Connection connection, int STT, String BookName, int NumberOfBook, String LentMoney, String endDate, String SerialNumber, String TimeLate)
            throws ClassNotFoundException, SQLException {

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO lentbook"+ currentID +"(STT,BookName,NumberOfBook,LentMoney,endDate,SerialNumber,TimeLate) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, STT);
        stmt.setString(2, BookName);
        stmt.setInt(3, NumberOfBook);
        stmt.setString(4, LentMoney);
        stmt.setString(5, endDate);
        stmt.setString(6, SerialNumber);
        stmt.setString(7, TimeLate);


        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối

    }

    public int deleteLentBook(Connection connection, String Code) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Xóa sinh viên
        String sql = "DELETE FROM lentbook"+ currentID +" WHERE STT='" + Code + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)

        return deleteStatus;
    }
}

package Database;

import java.sql.*;
import java.util.Vector;

public class HistoryReceive_DataBase {
    public boolean checkHistory(Connection connection, String ID) throws SQLException {
        // Kết nối database


        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from historyreceive where ID = '" + ID + "'";
        Statement stm1 = connection.createStatement();
        ResultSet rs = stm1.executeQuery(sql);

        // Trả về kết quả
        if (!rs.next()) {

            return false;
        }

        return true;
    }

    public Vector<Vector<Object>> getAll(Connection connection) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();

        // Kết nối database


        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from historyreceive");
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int ID = rs.getInt(1);
            String Name = rs.getString(2);
            String PhoneNumber = rs.getString(3);
            String ExpDate = rs.getString(4);
            String BookName = rs.getString(5);
            int Quantity = rs.getInt(6);
            String ReturnDate = rs.getString(7);


            // Ghi vào vector
            Vector<Object> temp = new Vector<>();
            temp.add(ID);
            temp.add(Name);
            temp.add(PhoneNumber);
            temp.add(ExpDate);
            temp.add(BookName);
            temp.add(Quantity);
            temp.add(ReturnDate);

            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public void addNewHistory(Connection connection, int ID, String Name, String PhoneNumber, String ExpDate, String BookName
            ,int Quantity, String ReturnDate)
            throws ClassNotFoundException, SQLException {

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO historyreceive(ID,Name,PhoneNumber,ExpDate,BookName,Quantity,ReturnDate) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, ID);
        stmt.setString(2, Name);
        stmt.setString(3, PhoneNumber);
        stmt.setString(4, ExpDate);
        stmt.setString(5, BookName);
        stmt.setInt(6, Quantity);
        stmt.setString(7, ReturnDate);



        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối

    }
}

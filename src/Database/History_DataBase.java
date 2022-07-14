package Database;

import java.sql.*;
import java.util.Vector;

public class History_DataBase {
    public boolean checkHistory(Connection connection, String ID) throws SQLException {
        // Kết nối database


        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from history where ID = '" + ID + "'";
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
        ResultSet rs = stmt.executeQuery("Select * from history");
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int ID = rs.getInt(1);
            String Name = rs.getString(2);
            String PhoneNumber = rs.getString(3);
            String RegisDate = rs.getString(4);
            String ExpDate = rs.getString(5);
            String BookName = rs.getString(6);
            int Quantity = rs.getInt(7);


            // Ghi vào vector
            Vector<Object> temp = new Vector<>();
            temp.add(ID);
            temp.add(Name);
            temp.add(PhoneNumber);
            temp.add(RegisDate);
            temp.add(ExpDate);
            temp.add(BookName);
            temp.add(Quantity);

            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public void addNewHistory(Connection connection, int ID, String Name, String PhoneNumber, String RegisDate, String ExpDate, String BookName
            ,int Quantity)
            throws ClassNotFoundException, SQLException {

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO history(ID,Name,PhoneNumber,RegisDate,ExpDate,BookName,Quantity) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, ID);
        stmt.setString(2, Name);
        stmt.setString(3, PhoneNumber);
        stmt.setString(4, RegisDate);
        stmt.setString(5, ExpDate);
        stmt.setString(6, BookName);
        stmt.setInt(7, Quantity);



        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối

    }
}

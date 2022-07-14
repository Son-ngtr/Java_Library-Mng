package Database;

import java.sql.*;
import java.util.Vector;

public class Table_DataBase {
    public boolean checkTable(Connection connection, String ID) throws SQLException {
        // Kết nối database


        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from desk where STT = '" + ID + "'";
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

        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from desk");
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int STT = rs.getInt(1);
            String Name = rs.getString(2);
            int Desk = rs.getInt(3);
            String BookName = rs.getString(4);
            int Quantity = rs.getInt(5);
            int Code = rs.getInt(6);


            // Ghi vào vector
            Vector<Object> temp = new Vector<>();
            temp.add(STT);
            temp.add(Name);
            temp.add(Desk);
            temp.add(BookName);
            temp.add(Quantity);
            temp.add(Code);

            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public int updateTable(Connection connection, int ID, int col, String value)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        String sql;
        Statement stm1;

        switch (col){
            case 0:
                sql = "UPDATE desk set STT='" + Integer.parseInt(value) + "' WHERE STT='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 1:
                sql = "UPDATE desk set Name='" + value +  "' WHERE STT='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 2:
                sql = "UPDATE desk set Desk='" + Integer.parseInt(value) +  "' WHERE STT='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 3:
                sql = "UPDATE desk set BookName='" + value +  "' WHERE STT='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 4:
                sql = "UPDATE desk set Quantity='" + Integer.parseInt(value) +  "' WHERE STT='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 5:
                sql = "UPDATE desk set Code='" + Integer.parseInt(value) +  "' WHERE STT='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;

            default:
                return updateStatus;
        }
    }

    public void addNewTable(Connection connection, int STT, String Name ,int Desk, String BookName, int Quantity , int Code)
            throws ClassNotFoundException, SQLException {

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO desk(STT,Name,Desk,BookName,Quantity,Code) VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, STT);
        stmt.setString(2, Name);
        stmt.setInt(3, Desk);
        stmt.setString(4, BookName);
        stmt.setInt(5, Quantity);
        stmt.setInt(6,Code);



        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối

    }

    public int deleteTable(Connection connection, String ID) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Xóa sinh viên
        String sql = "DELETE FROM desk WHERE STT='" + ID + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)

        return deleteStatus;
    }
}
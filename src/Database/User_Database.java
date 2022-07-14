package Database;

import java.sql.*;
import java.util.Vector;

public class User_Database {
<<<<<<< HEAD
    public boolean checkUser(Connection connection, int ID) throws SQLException {
=======
    public boolean checkUser(Connection connection, String ID) throws SQLException {
>>>>>>> TMQuang
        // Kết nối database


        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from user where ID = '" + ID + "'";
        Statement stm1 = connection.createStatement();
        ResultSet rs = stm1.executeQuery(sql);

        // Trả về kết quả
        if (!rs.next()) {
<<<<<<< HEAD
            connection.close();
            return false;
        }
        connection.close();
=======
            
            return false;
        }
        
>>>>>>> TMQuang
        return true;
    }

    public Vector<Vector<Object>> getAll(Connection connection) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();

        // Kết nối database


        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from user");
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int ID = rs.getInt(1);
            String Name = rs.getString(2);
            String Gender = rs.getString(3);
            String DateOfBirth = rs.getString(4);
            String Address = rs.getString(5);
            String PhoneNumber = rs.getString(6);
            String Email = rs.getString(7);
            int TotalBook = rs.getInt(8);
<<<<<<< HEAD
            int TotalFee = rs.getInt(9);
=======
            String TotalFee= rs.getString(9);
            int deskNumber = rs.getInt(10);
>>>>>>> TMQuang


            // Ghi vào vector
            Vector<Object> temp = new Vector<>();
            temp.add(ID);
            temp.add(Name);
            temp.add(Gender);
            temp.add(DateOfBirth);
            temp.add(Address);
            temp.add(PhoneNumber);
            temp.add(Email);
            temp.add(TotalBook);
            temp.add(TotalFee);
<<<<<<< HEAD

=======
            temp.add(deskNumber);
>>>>>>> TMQuang



            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

<<<<<<< HEAD
    public int updateUser(Connection connection, int ID, String Name, String DateOfBirth, String Address, String PhoneNumber, String Gender, int TotalBook,
                          String Email, int TotalFee)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        Connection conn = getConnect(accountData);

        // Tạo câu lệnh SQL
        String sql = "UPDATE User set ID='" + ID + "',Name='" + Name + "',Gender='" + Gender + "',DateOfBirth='"
                + DateOfBirth + "',Address='" + Address + "',PhoneNumber='" + PhoneNumber + "',Email='" + Email + "',TotalBook='" + TotalBook +
                "',TotalFee='" + TotalFee + "' WHERE ID='" + ID + "'";
        Statement stm1 = connection.createStatement();
        updateStatus = stm1.executeUpdate(sql);
        connection.close();
        return updateStatus;
    }

    public void addNewUser(Connection connection, int ID, String Name, String Gender, String DateOfBirth, String Address, String PhoneNumber, String Email,
                           int TotalBook, int TotalFee )
            throws ClassNotFoundException, SQLException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO user(ID,Name,Gender,DateOfBirth,Address,PhoneNumber,Email,TotalBook,TotalFee) VALUES(?,?,?,?,?,?,?,?,?)";
=======
    public int updateUser(Connection connection, int ID, int col, String value)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        String sql;
        Statement stm1;

        switch (col){
            case 0:
                sql = "UPDATE user set ID='" + Integer.parseInt(value) + "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 1:
                sql = "UPDATE user set Name='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 2:
                sql = "UPDATE user set Gender='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 3:
                sql = "UPDATE user set DateOfBirth='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 4:
                sql = "UPDATE user set Address='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 5:
                sql = "UPDATE user set PhoneNumber='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 6:
                sql = "UPDATE user set Email='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 7:
                sql = "UPDATE user set TotalBook='" + Integer.parseInt(value) +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 8:
                sql = "UPDATE user set TotalFee='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 9:
                sql = "UPDATE user set DeskNumber='" + Integer.parseInt(value) +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
        }
        return updateStatus;
    }

    public void addNewUser(Connection connection, int ID, String Name, String Gender, String DateOfBirth, String Address, String PhoneNumber, String Email
                           ,int TotalBook ,String TotalFee )
            throws ClassNotFoundException, SQLException {

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO user(ID,Name,Gender,DateOfBirth,Address,PhoneNumber,Email,TotalBook,TotalFee,DeskNumber) VALUES(?,?,?,?,?,?,?,?,?,?)";
>>>>>>> TMQuang
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, ID);
        stmt.setString(2, Name);
        stmt.setString(3, Gender);
        stmt.setString(4, DateOfBirth);
        stmt.setString(5, Address);
        stmt.setString(6, PhoneNumber);
        stmt.setString(7, Email);
        stmt.setInt(8, TotalBook);
<<<<<<< HEAD
        stmt.setInt(9, TotalFee);
=======
        stmt.setString(9, TotalFee);
        stmt.setInt(10, 0);
>>>>>>> TMQuang



        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối
<<<<<<< HEAD
        connection.close();
    }

    public int deleteUser(Connection connection, int ID) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);
=======
        
    }

    public int deleteUser(Connection connection, String ID) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

>>>>>>> TMQuang

        // Xóa sinh viên
        String sql = "DELETE FROM user WHERE ID='" + ID + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
<<<<<<< HEAD
        connection.close();
=======
        
>>>>>>> TMQuang
        return deleteStatus;
    }
}

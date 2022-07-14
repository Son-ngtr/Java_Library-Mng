package Database;

import java.sql.*;
import java.util.Vector;

public class NovelBook {
<<<<<<< HEAD
    public boolean checkNovelBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from novelbook where Name = '" + Name + "'";
=======
    public boolean checkNovelBook(Connection connection, String TypeCode) throws SQLException, ClassNotFoundException {

        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from novelbook where TypeCode = '" + TypeCode + "'";
>>>>>>> TMQuang
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

    public Vector<Vector<Object>> getAll(Connection connection) throws ClassNotFoundException, SQLException {
        Vector<Vector<Object>> data = new Vector<>();

<<<<<<< HEAD
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

=======
>>>>>>> TMQuang
        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from novelbook");
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int Code = rs.getInt(1);
            String Name = rs.getString(2);
            String DateAdded = rs.getString(3);
<<<<<<< HEAD
            int Price = rs.getInt(4);
=======
            String Price = rs.getString(4);
>>>>>>> TMQuang
            String Author = rs.getString(5);
            String Publisher = rs.getString(6);
            String Category = rs.getString(7);
            int Quantity = rs.getInt(8);
            int TypeCode = rs.getInt(9);
<<<<<<< HEAD
            String Type = rs.getString(10);
            String AgeLimitted = rs.getString(11);
=======
            String SerialNumber = rs.getString(10);
            String Type = rs.getString(11);
            String AgeLimitted = rs.getString(12);
>>>>>>> TMQuang

            // Ghi vào vector
            Vector<Object> temp = new Vector<>();
            temp.add(Code);
            temp.add(Name);
            temp.add(DateAdded);
            temp.add(Price);
            temp.add(Author);
            temp.add(Publisher);
            temp.add(Category);
            temp.add(Quantity);
            temp.add(TypeCode);
<<<<<<< HEAD
=======
            temp.add(SerialNumber);
>>>>>>> TMQuang
            temp.add(Type);
            temp.add(AgeLimitted);


            // Thêm dữ liệu vào data vector chính
            data.add(temp);

        }
        return data;
    }

<<<<<<< HEAD
    public int updateNovelBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                               int Quantity, int TypeCode, String Type, String AgeLimitted)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        Connection conn = getConnect(accountData);

        // Tạo câu lệnh SQL
        String sql = "UPDATE novelbook set Code='" + Code + "',Name'" + Name + "',DateAdded='" + DateAdded + "',Price='"
                + Price + "',Author='" + Author + "',Publisher='" + Publisher + "',Category='" + Category + "',Quantity='" + Quantity +
                "',TypeCode='" + TypeCode + "',Tpye='" + Type + "',AgeLimitted='" + AgeLimitted +
                "' WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        updateStatus = stm1.executeUpdate(sql);
        connection.close();
        return updateStatus;
    }

    public void addNewNovelBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                                int Quantity, int TypeCode, String Type, String AgeLimitted)
            throws ClassNotFoundException, SQLException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO novelbook(Code,Name,DateAdded,Price,Author,Publisher,Category,Quantity,TypeCode,Type,AgeLimitted) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
=======
    public int updateNovelBook(Connection connection, int TypeCode, int col, String value)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        String sql;
        Statement stm1;

        switch (col){
            case 0:
                sql = "UPDATE novelbook set Code='" + Integer.parseInt(value) + "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 1:
                sql = "UPDATE novelbook set Name='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 2:
                sql = "UPDATE novelbook set DateAdded='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 3:
                sql = "UPDATE novelbook set Price='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 4:
                sql = "UPDATE novelbook set Author='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 5:
                sql = "UPDATE novelbook set Publisher='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 6:
                sql = "UPDATE novelbook set Category='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 7:
                sql = "UPDATE novelbook set Quantity='" + Integer.parseInt(value) +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 8:
                sql = "UPDATE novelbook set TypeCode='" + Integer.parseInt(value) +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 9:
                sql = "UPDATE novelbook set SerialNumber='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 10:
                sql = "UPDATE novelbook set Type='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 11:
                sql = "UPDATE novelbook set AgeLimitted='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
        }
        return updateStatus;
    }

    public void addNewNovelBook(Connection connection, int Code, String Name, String DateAdded, String Price, String Author, String Publisher, String Category,
                                int Quantity, int TypeCode,String SerialNumber ,String Type, String AgeLimitted)
            throws ClassNotFoundException, SQLException {

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO novelbook(Code,Name,DateAdded,Price,Author,Publisher,Category,Quantity,TypeCode,SerialNumber,Type,AgeLimitted) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
>>>>>>> TMQuang
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, Code);
        stmt.setString(2, Name);
        stmt.setString(3, DateAdded);
<<<<<<< HEAD
        stmt.setInt(4, Price);
=======
        stmt.setString(4, Price);
>>>>>>> TMQuang
        stmt.setString(5, Author);
        stmt.setString(6, Publisher);
        stmt.setString(7, Category);
        stmt.setInt(8, Quantity);
        stmt.setInt(9, TypeCode);
<<<<<<< HEAD
        stmt.setString(10, Type);
        stmt.setString(11, AgeLimitted);
=======
        stmt.setString(10, SerialNumber);
        stmt.setString(11, Type);
        stmt.setString(12, AgeLimitted);
>>>>>>> TMQuang


        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối
<<<<<<< HEAD
        connection.close();
    }

    public int deleteNovelBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Xóa sinh viên
        String sql = "DELETE FROM novelbook WHERE Name='" + Name + "'";
=======
        
    }

    public int deleteNovelBook(Connection connection, String TypeCode) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Xóa sinh viên
        String sql = "DELETE FROM novelbook WHERE TypeCode='" + TypeCode + "'";
>>>>>>> TMQuang
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

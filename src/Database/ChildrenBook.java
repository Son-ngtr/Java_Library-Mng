package Database;

import java.sql.*;
import java.util.Vector;

public class ChildrenBook  extends LibraryManager{

    public boolean checkChildrenBook(Connection connection, int TypeCode) throws SQLException {
        // Kết nối database
        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from childrenbook where TypeCode = '" + TypeCode + "'";
        Statement stm1 = connection.createStatement();
        ResultSet rs = stm1.executeQuery(sql);

        // Trả về kết quả
        if (!rs.next()) {
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }

    public Vector<Vector<Object>> getAll(Connection connection) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();

        // Kết nối database
        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from childrenbook");
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int Code = rs.getInt(1);
            String Name = rs.getString(2);
            String DateAdded = rs.getString(3);
            int Price = rs.getInt(4);
            String Author = rs.getString(5);
            String Publisher = rs.getString(6);
            String Category = rs.getString(7);
            int Quantity = rs.getInt(8);
            int TypeCode = rs.getInt(9);
            String Type = rs.getString(10);
            String RecommentForAge = rs.getString(11);

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
            temp.add(Type);
            temp.add(RecommentForAge);


            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public int updateChildrenBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                                  int Quantity, int TypeCode, String Type, String RecommentForAge)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        Connection conn = getConnect(accountData);

        // Tạo câu lệnh SQL
        String sql = "UPDATE childrenbook set Code='" + Code + "',Name='" + Name + "',DateAdded='" + DateAdded + "',Price='"
                + Price + "',Author='" + Author + "',Publisher='" + Publisher + "',Category='" + Category + "',Quantity='" + Quantity +
                "',TypeCode='" + TypeCode + "',Type='" + Type + "',RecommentForAge='" + RecommentForAge +
                "' WHERE TypeCode='" + TypeCode + "'";
        Statement stm1 = connection.createStatement();
        updateStatus = stm1.executeUpdate(sql);
        connection.close();
        return updateStatus;
    }

    public void addNewChildrenBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                                   int Quantity, int TypeCode, String Type, String RecommentForAge)
            throws ClassNotFoundException, SQLException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO childrenbook(Code,Name,DateAdded,Price,Author,Publisher,Category,Quantity,TypeCode,Type,RecommentForAge) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, Code);
        stmt.setString(2, Name);
        stmt.setString(3, DateAdded);
        stmt.setInt(4, Price);
        stmt.setString(5, Author);
        stmt.setString(6, Publisher);
        stmt.setString(7, Category);
        stmt.setInt(8, Quantity);
        stmt.setInt(9, TypeCode);
        stmt.setString(10, Type);
        stmt.setString(11, RecommentForAge);


        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối
        connection.close();
    }

    public int deleteChildrenBook(Connection connection, int TypeCode) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Xóa sinh viên
        String sql = "DELETE FROM childrenbook WHERE TypeCode='" + TypeCode + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
        connection.close();
        return deleteStatus;
    }


}

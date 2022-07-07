package Database;

import java.sql.*;
import java.util.Vector;

public class LearningBook {
    public boolean checkLearingBook(Connection connection, String TypeCode) throws SQLException, ClassNotFoundException {

        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from learningbook where TypeCode = '" + TypeCode + "'";
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
        ResultSet rs = stmt.executeQuery("Select * from learningbook");
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int Code = rs.getInt(1);
            String Name = rs.getString(2);
            String DateAdded = rs.getString(3);
            String Price = rs.getString(4);
            String Author = rs.getString(5);
            String Publisher = rs.getString(6);
            String Category = rs.getString(7);
            int Quantity = rs.getInt(8);
            int TypeCode = rs.getInt(9);
            String SerialNumber = rs.getString(10);
            String Education = rs.getString(11);
            String EducationSubjects = rs.getString(12);

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
            temp.add(SerialNumber);
            temp.add(Education);
            temp.add(EducationSubjects);


            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public int updateLearningBook(Connection connection, int TypeCode, int col, String value)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        String sql;
        Statement stm1;


        switch (col){
            case 0:
                sql = "UPDATE learningbook set Code='" + Integer.parseInt(value) + "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 1:
                sql = "UPDATE learningbook set Name='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 2:
                sql = "UPDATE learningbook set DateAdded='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 3:
                sql = "UPDATE learningbook set Price='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 4:
                sql = "UPDATE learningbook set Author='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 5:
                sql = "UPDATE learningbook set Publisher='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 6:
                sql = "UPDATE learningbook set Category='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 7:
                sql = "UPDATE learningbook set Quantity='" + Integer.parseInt(value) +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 8:
                sql = "UPDATE learningbook set TypeCode='" + Integer.parseInt(value) +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 9:
                sql = "UPDATE learningbook set SerialNumber='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 10:
                sql = "UPDATE learningbook set Education='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
            case 11:
                sql = "UPDATE learningbook set EducationSubjects='" + value +  "' WHERE TypeCode='" + TypeCode + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                
                return updateStatus;
        }
        return updateStatus;
    }

    public void addNewLearningBook(Connection connection, int Code, String Name, String DateAdded, String Price, String Author, String Publisher, String Category,
                                   int Quantity, int TypeCode,String SerialNumber ,String Education, String EducationSubjects)
            throws ClassNotFoundException, SQLException {

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO learningbook(Code,Name,DateAdded,Price,Author,Publisher,Category,Quantity,TypeCode,SerialNumber,Education,EducationSubjects) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, Code);
        stmt.setString(2, Name);
        stmt.setString(3, DateAdded);
        stmt.setString(4, Price);
        stmt.setString(5, Author);
        stmt.setString(6, Publisher);
        stmt.setString(7, Category);
        stmt.setInt(8, Quantity);
        stmt.setInt(9, TypeCode);
        stmt.setString(10, SerialNumber);
        stmt.setString(11, Education);
        stmt.setString(12, EducationSubjects);


        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối
        
    }

    public int deleteLearningBook(Connection connection, String TypeCode) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Xóa sinh viên
        String sql = "DELETE FROM learningbook WHERE TypeCode='" + TypeCode + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
        
        return deleteStatus;
    }

}

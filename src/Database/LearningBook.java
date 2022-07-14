package Database;

import java.sql.*;
import java.util.Vector;

<<<<<<< HEAD

public class LearningBook {

    public boolean checkLearingBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from learningbook where Name = '" + Name + "'";
=======
public class LearningBook {
    public boolean checkLearingBook(Connection connection, String TypeCode) throws SQLException, ClassNotFoundException {

        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from learningbook where TypeCode = '" + TypeCode + "'";
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
        ResultSet rs = stmt.executeQuery("Select * from learningbook");
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
            String Education = rs.getString(10);
            String EducationSubjects = rs.getString(11);
=======
            String SerialNumber = rs.getString(10);
            String Education = rs.getString(11);
            String EducationSubjects = rs.getString(12);
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
            temp.add(Education);
            temp.add(EducationSubjects);


            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

<<<<<<< HEAD
    public int updateLearningBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                                  int Quantity, int TypeCode, String Education, String EducationSubjects)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//       Connection conn  = getConnect(accountData);

        // Tạo câu lệnh SQL
        String sql = "UPDATE learningbook set Code='" + Code + "',Name'" + Name + "',DateAdded='" + DateAdded + "',Price='"
                + Price + "',Author='" + Author + "',Publisher='" + Publisher + "',Category='" + Category + "',Quantity='" + Quantity +
                "',TypeCode='" + TypeCode + "',Education='" + Education + "',EducationSubjects='" + EducationSubjects +
                "' WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        updateStatus = stm1.executeUpdate(sql);
        connection.close();
        return updateStatus;
    }

    public void addNewLearningBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                                   int Quantity, int TypeCode, String Education, String EducationSubjects)
            throws ClassNotFoundException, SQLException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO learningbook(Code,Name,DateAdded,Price,Author,Publisher,Category,Quantity,TypeCode,Education,EducationSubjects) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
=======
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
        stmt.setString(10, Education);
        stmt.setString(11, EducationSubjects);
=======
        stmt.setString(10, SerialNumber);
        stmt.setString(11, Education);
        stmt.setString(12, EducationSubjects);
>>>>>>> TMQuang


        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối
<<<<<<< HEAD
        connection.close();
    }

    public int deleteLearningBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Xóa sinh viên
        String sql = "DELETE FROM learningbook WHERE Name='" + Name + "'";
=======
        
    }

    public int deleteLearningBook(Connection connection, String TypeCode) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Xóa sinh viên
        String sql = "DELETE FROM learningbook WHERE TypeCode='" + TypeCode + "'";
>>>>>>> TMQuang
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
<<<<<<< HEAD
        connection.close();
        return deleteStatus;
    }

    private Connection connection;
=======
        
        return deleteStatus;
    }

>>>>>>> TMQuang
}

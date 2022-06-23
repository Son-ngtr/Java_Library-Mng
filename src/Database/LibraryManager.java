package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class LibraryManager {
//    public LibraryManager() {
//
//    }

    private Connection connection;

//    public Connection getConn() {
//        return connection;
//    }
//
//    public void setConn(Connection conn) {
//        this.connection = conn;
//    }

    public static Connection getConnect(AccountData account) {

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseInfo.dbURL, account.getUsername(), account.getPassword());
            System.out.println("connect successfully!");
            //System.out.println("CONNECTTED!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;

    }


    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------
    public boolean checkChildrenBook(Connection connection, String Name) throws SQLException {
        // Kết nối database


        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from childrenbook where Name = '" + Name + "'";
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

    public Vector<Vector<Object>> getAll1(Connection connection) throws SQLException {
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
            Vector<Object> temp1 = new Vector<>();
            //temp1.add(Code);
            temp1.add(Name);
            temp1.add(DateAdded);
            //temp1.add(Price);
            temp1.add(Author);
            temp1.add(Publisher);
            temp1.add(Category);
            //temp1.add(Quantity);
            //temp1.add(TypeCode);
            temp1.add(Type);
            temp1.add(RecommentForAge);

            Vector<Object> temp2 = new Vector<>();
            temp2.add(Code);
            temp2.add(Price);
            temp2.add(Quantity);
            temp2.add(TypeCode);

            // Thêm dữ liệu vào data vector chính
            data.add(temp1);
            data.add(temp2);
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
                "' WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        updateStatus = stm1.executeUpdate(sql);
        connection.close();
        return updateStatus;
    }

    public void addNewChildrenBook(Connection connection,int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                       int Quantity, int TypeCode, String Type, String RecommentForAge)
            throws ClassNotFoundException, SQLException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO childrenbook(Code,Name,DateAdded,Price,Author,Publisher,Category,Quantity,TypeCode,Type,RecommentForAge) VALUES(?,?,?,?,?,?)";
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

    public int deleteChildrenBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Xóa sinh viên
        String sql = "DELETE FROM childrenbook WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
        connection.close();
        return deleteStatus;
    }

//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
    public boolean checkLearingBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from learningbook where Name = '" + Name + "'";
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

    public Vector<Vector<Object>> getAll2(Connection connection) throws ClassNotFoundException, SQLException {
        Vector<Vector<Object>> data = new Vector<>();

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from learningbook");
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
            String Education = rs.getString(10);
            String EducationSubjects = rs.getString(11);

            // Ghi vào vector
            Vector<Object> temp1 = new Vector<>();
            //temp1.add(Code);
            temp1.add(Name);
            temp1.add(DateAdded);
            //temp1.add(Price);
            temp1.add(Author);
            temp1.add(Publisher);
            temp1.add(Category);
            //temp1.add(Quantity);
            //temp1.add(TypeCode);
            temp1.add(Education);
            temp1.add(EducationSubjects);

            Vector<Object> temp2 = new Vector<>();
            temp2.add(Code);
            temp2.add(Price);
            temp2.add(Quantity);
            temp2.add(TypeCode);

            // Thêm dữ liệu vào data vector chính
            data.add(temp1);
            data.add(temp2);
        }
        return data;
    }

    public int updateLearningBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                      int Quantity, int TypeCode, String Education, String EducationSubjects)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//       Connection conn  = getConnect(accountData);

        // Tạo câu lệnh SQL
        String sql = "UPDATE childrenbook set Code='" + Code + "',Name'" + Name + "',DateAdded='" + DateAdded + "',Price='"
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
        String sql = "INSERT INTO childrenbook(Code,Name,DateAdded,Price,Author,Publisher,Category,Quantity,TypeCode,Education,EducationSubjects) VALUES(?,?,?,?,?,?)";
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
        stmt.setString(10, Education);
        stmt.setString(11, EducationSubjects);


        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối
        connection.close();
    }

    public int deleteLearningBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Xóa sinh viên
        String sql = "DELETE FROM learningbook WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
        connection.close();
        return deleteStatus;
    }

//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------

    public boolean checkNovelBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from novelbook where Name = '" + Name + "'";
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

    public Vector<Vector<Object>> getAll3(Connection connection) throws ClassNotFoundException, SQLException {
        Vector<Vector<Object>> data = new Vector<>();

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from novelbook");
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
            String AgeLimitted = rs.getString(11);

            // Ghi vào vector
            Vector<Object> temp1 = new Vector<>();
            //temp1.add(Code);
            temp1.add(Name);
            temp1.add(DateAdded);
            //temp1.add(Price);
            temp1.add(Author);
            temp1.add(Publisher);
            temp1.add(Category);
            //temp1.add(Quantity);
            //temp1.add(TypeCode);
            temp1.add(Type);
            temp1.add(AgeLimitted);

            Vector<Object> temp2 = new Vector<>();
            temp2.add(Code);
            temp2.add(Price);
            temp2.add(Quantity);
            temp2.add(TypeCode);

            // Thêm dữ liệu vào data vector chính
            data.add(temp1);
            data.add(temp2);
        }
        return data;
    }

    public int updateNovelBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                                  int Quantity, int TypeCode, String Type, String AgeLimitted)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        Connection conn = getConnect(accountData);

        // Tạo câu lệnh SQL
        String sql = "UPDATE childrenbook set Code='" + Code + "',Name'" + Name + "',DateAdded='" + DateAdded + "',Price='"
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
        String sql = "INSERT INTO novelbook(Code,Name,DateAdded,Price,Author,Publisher,Category,Quantity,TypeCode,Type,AgeLimitted) VALUES(?,?,?,?,?,?)";
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
        stmt.setString(11, AgeLimitted);


        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối
        connection.close();
    }

    public int deleteNovelBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Xóa sinh viên
        String sql = "DELETE FROM novelbook WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
        connection.close();
        return deleteStatus;
    }

}
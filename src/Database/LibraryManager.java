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
                "' WHERE Name='" + Name + "'";
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
            Vector<Object> temp = new Vector<>();
            //temp1.add(Code);
            temp.add(Name);
            temp.add(DateAdded);
            temp.add(Price);
            temp.add(Author);
            temp.add(Publisher);
            temp.add(Category);
            temp.add(Quantity);
            temp.add(TypeCode);
            temp.add(Education);
            temp.add(EducationSubjects);


            // Thêm dữ liệu vào data vector chính
            data.add(temp);
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
            temp.add(AgeLimitted);


            // Thêm dữ liệu vào data vector chính
            data.add(temp);

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

//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------

    public boolean checkPsychologyBook(Connection connection, String Name) throws SQLException {
        // Kết nối database


        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from psychologybook where Name = '" + Name + "'";
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

    public Vector<Vector<Object>> getAll4(Connection connection) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();

        // Kết nối database


        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from psychologybook");
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

    public int updatePsychologyBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                                    int Quantity, int TypeCode, String Type, String RecommentForAge)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        Connection conn = getConnect(accountData);

        // Tạo câu lệnh SQL
        String sql = "UPDATE psychologybook set Code='" + Code + "',Name='" + Name + "',DateAdded='" + DateAdded + "',Price='"
                + Price + "',Author='" + Author + "',Publisher='" + Publisher + "',Category='" + Category + "',Quantity='" + Quantity +
                "',TypeCode='" + TypeCode + "',Type='" + Type + "',RecommentForAge='" + RecommentForAge +
                "' WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        updateStatus = stm1.executeUpdate(sql);
        connection.close();
        return updateStatus;
    }

    public void addNewPsychologyBook(Connection connection, int Code, String Name, String DateAdded, int Price, String Author, String Publisher, String Category,
                                     int Quantity, int TypeCode, String Type, String RecommentForAge)
            throws ClassNotFoundException, SQLException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO psychologybook(Code,Name,DateAdded,Price,Author,Publisher,Category,Quantity,TypeCode,Type,RecommentForAge) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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

    public int deletePsychologyBook(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Xóa sinh viên
        String sql = "DELETE FROM psychologybook WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
        connection.close();
        return deleteStatus;
    }

    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------

    public boolean checkStaff(Connection connection, String Name) throws SQLException {
        // Kết nối database


        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from staff where Name = '" + Name + "'";
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

    public Vector<Vector<Object>> getAll5(Connection connection) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();

        // Kết nối database


        // Tạo câu lệnh SQL (Cách 1: dùng Statement)
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from staff");
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int ID = rs.getInt(1);
            String Name = rs.getString(2);
            String Gender = rs.getString(3);
            String DateOfBirth = rs.getString(4);
            String Address = rs.getString(5);
            String PhoneNumber = rs.getString(6);
            String Email = rs.getString(7);
            String Staff = rs.getString(8);
            int Salary = rs.getInt(9);
            String Attendance = rs.getString(10);


            // Ghi vào vector
            Vector<Object> temp = new Vector<>();
            temp.add(ID);
            temp.add(Name);
            temp.add(Gender);
            temp.add(DateOfBirth);
            temp.add(Address);
            temp.add(PhoneNumber);
            temp.add(Email);
            temp.add(Staff);
            temp.add(Salary);
            temp.add(Attendance);



            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public int updateStaff(Connection connection, int ID, String Name, String DateOfBirth, String Address, String PhoneNumber, String Gender, String Staff,
                           String Email, int Salary, String Attendance)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        Connection conn = getConnect(accountData);

        // Tạo câu lệnh SQL
        String sql = "UPDATE staff set ID='" + ID + "',Name='" + Name + "',Gender='" + Gender + "',DateOfBirth='"
                + DateOfBirth + "',Address='" + Address + "',PhoneNumber='" + PhoneNumber + "',Email='" + Email + "',Staff='" + Staff +
                "',Salary='" + Salary + "',Attendance='" + Attendance + "' WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        updateStatus = stm1.executeUpdate(sql);
        connection.close();
        return updateStatus;
    }

    public void addNewStaff(Connection connection, int ID, String Name, String Gender, String DateOfBirth, String Address, String PhoneNumber, String Email,
                            String Staff, int Salary, String Attendance )
            throws ClassNotFoundException, SQLException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO staff(ID,Name,Gender,DateOfBirth,Address,PhoneNumber,Email,Staff,Salary,Attendance) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, ID);
        stmt.setString(2, Name);
        stmt.setString(3, Gender);
        stmt.setString(4, DateOfBirth);
        stmt.setString(5, Address);
        stmt.setString(6, PhoneNumber);
        stmt.setString(7, Email);
        stmt.setString(8, Staff);
        stmt.setInt(9, Salary);
        stmt.setString(10, Attendance);



        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối
        connection.close();
    }

    public int deleteStaff(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Xóa sinh viên
        String sql = "DELETE FROM staff WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
        connection.close();
        return deleteStatus;
    }

//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------

    public boolean checkUser(Connection connection, String Name) throws SQLException {
        // Kết nối database


        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from user where Name = '" + Name + "'";
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

    public Vector<Vector<Object>> getAll6(Connection connection) throws SQLException {
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
            String ExpDate = rs.getString(8);
            String RegisDate = rs.getString(9);
            int MoneyFine = rs.getInt(10);


            // Ghi vào vector
            Vector<Object> temp = new Vector<>();
            temp.add(ID);
            temp.add(Name);
            temp.add(Gender);
            temp.add(DateOfBirth);
            temp.add(Address);
            temp.add(PhoneNumber);
            temp.add(Email);
            temp.add(ExpDate);
            temp.add(RegisDate);
            temp.add(MoneyFine);



            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public int updateUser(Connection connection, int ID, String Name, String DateOfBirth, String Address, String PhoneNumber, String Gender, String RegisDate,
                           String Email, int MoneyFine, String ExpDate)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        Connection conn = getConnect(accountData);

        // Tạo câu lệnh SQL
        String sql = "UPDATE User set ID='" + ID + "',Name='" + Name + "',Gender='" + Gender + "',DateOfBirth='"
                + DateOfBirth + "',Address='" + Address + "',PhoneNumber='" + PhoneNumber + "',Email='" + Email + "',ExpDate='" + ExpDate +
                "',RegisDate='" + RegisDate + "',MoneyFine='" + MoneyFine + "' WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        updateStatus = stm1.executeUpdate(sql);
        connection.close();
        return updateStatus;
    }

    public void addNewUser(Connection connection, int ID, String Name, String Gender, String DateOfBirth, String Address, String PhoneNumber, String Email,
                            String ExpDate, String RegisDate, int MoneyFine )
            throws ClassNotFoundException, SQLException {
        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO user(ID,Name,Gender,DateOfBirth,Address,PhoneNumber,Email,ExpDate,RegisDate,MoneyFine) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, ID);
        stmt.setString(2, Name);
        stmt.setString(3, Gender);
        stmt.setString(4, DateOfBirth);
        stmt.setString(5, Address);
        stmt.setString(6, PhoneNumber);
        stmt.setString(7, Email);
        stmt.setString(8, ExpDate);
        stmt.setString(9, RegisDate);
        stmt.setInt(10, MoneyFine);



        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối
        connection.close();
    }

    public int deleteUser(Connection connection, String Name) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Kết nối database
//        AccountData accountData = new AccountData("root", "");
//        connection = getConnect(accountData);

        // Xóa sinh viên
        String sql = "DELETE FROM user WHERE Name='" + Name + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
        connection.close();
        return deleteStatus;
    }
}


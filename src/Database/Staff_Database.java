package Database;

import java.sql.*;
import java.util.Vector;

public class Staff_Database {
    public boolean checkStaff(Connection connection, String ID) throws SQLException {
        // Kết nối database


        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from staff where ID = '" + ID + "'";
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

        // Tạo câu lệnh SQL
        String sql = "UPDATE staff set ID='" + ID + "',Name='" + Name + "',Gender='" + Gender + "',DateOfBirth='"
                + DateOfBirth + "',Address='" + Address + "',PhoneNumber='" + PhoneNumber + "',Email='" + Email + "',Staff='" + Staff +
                "',Salary='" + Salary + "',Attendance='" + Attendance + "' WHERE ID='" + ID + "'";
        Statement stm1 = connection.createStatement();
        updateStatus = stm1.executeUpdate(sql);
        
        return updateStatus;
    }

    public void addNewStaff(Connection connection, int ID, String Name, String Gender, String DateOfBirth, String Address, String PhoneNumber, String Email,
                            String Staff, int Salary, String Attendance )
            throws ClassNotFoundException, SQLException {

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
        
    }

    public int deleteStaff(Connection connection, String ID) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Xóa sinh viên
        String sql = "DELETE FROM staff WHERE ID='" + ID + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)
        
        return deleteStatus;
    }

}

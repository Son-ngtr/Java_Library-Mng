package Database;

import Library.Check;

import java.sql.*;
import java.util.Calendar;
import java.util.Vector;

public class Staff_Database {
    Check check = new Check();

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
            String Salary = rs.getString(9);
            String Attendance = rs.getString(10);
            String TimeAttendance = rs.getString(11);


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
            temp.add(TimeAttendance);

            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public int updateTimeAttendance(Connection connection , int ID, String value) throws ClassNotFoundException, SQLException{
        Calendar time = Calendar.getInstance();
        String timeConvert = check.dateConvert(time);
        int updateStatus = 0;
        String sql;
        Statement stm1;

        switch (value){
            case "None":
                sql = "UPDATE staff set TimeAttendance='" + "0" +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case "Attendance":
                sql = "UPDATE staff set TimeAttendance='" + timeConvert +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case "Absent":
                sql = "UPDATE staff set TimeAttendance='" + timeConvert +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case "Late":
                sql = "UPDATE staff set TimeAttendance='" + timeConvert +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case "Leave of Absence Letter":
                sql = "UPDATE staff set TimeAttendance='" + "0" +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case "Quit":
                sql = "UPDATE staff set TimeAttendance='" + "0" +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
        }

        return updateStatus;
    }

    public int updateStaff(Connection connection, int ID, int col, String value)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        String sql;
        Statement stm1;

        switch (col){
            case 0:
                sql = "UPDATE staff set ID='" + Integer.parseInt(value) + "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 1:
                sql = "UPDATE staff set Name='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 2:
                sql = "UPDATE staff set Gender='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 3:
                sql = "UPDATE staff set DateOfBirth='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 4:
                sql = "UPDATE staff set Address='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 5:
                sql = "UPDATE staff set PhoneNumber='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 6:
                sql = "UPDATE staff set Email='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 7:
                sql = "UPDATE staff set Staff='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 8:
                sql = "UPDATE staff set Salary='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 9:
                sql = "UPDATE staff set Attendance='" + value +  "' WHERE ID='" + ID + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                updateTimeAttendance(connection, ID, value);

                return updateStatus;
            default:
                return updateStatus;
        }
    }

    public void addNewStaff(Connection connection, int ID, String Name, String Gender, String DateOfBirth, String Address, String PhoneNumber, String Email,
                            String Staff, String Salary, String Attendance )
            throws ClassNotFoundException, SQLException {

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO staff(ID,Name,Gender,DateOfBirth,Address,PhoneNumber,Email,Staff,Salary,Attendance,TimeAttendance) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, ID);
        stmt.setString(2, Name);
        stmt.setString(3, Gender);
        stmt.setString(4, DateOfBirth);
        stmt.setString(5, Address);
        stmt.setString(6, PhoneNumber);
        stmt.setString(7, Email);
        stmt.setString(8, Staff);
        stmt.setString(9, Salary);
        stmt.setString(10, Attendance);
        stmt.setString(11, "0");



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

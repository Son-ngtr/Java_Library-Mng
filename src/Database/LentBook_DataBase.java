package Database;

import java.sql.*;
import java.util.Vector;

public class LentBook_DataBase {
    public boolean checkLentBook(Connection connection, String Code) throws SQLException, ClassNotFoundException {

        // Kiểm tra sinh viên có trong database hay chưa
        String sql = "Select * from lentbook where STT = '" + Code + "'";
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
        ResultSet rs = stmt.executeQuery("Select * from lentbook");
        while (rs.next()) {

            // Lấy dữ liệu từ ResultSet
            int Code = rs.getInt(1);
            String BookName = rs.getString(2);
            int NumberOfBook = rs.getInt(3);
            int LentMoney = rs.getInt(4);
            String EndDate = rs.getString(5);

            // Ghi vào vector
            Vector<Object> temp = new Vector<>();
            temp.add(Code);
            temp.add(BookName);
            temp.add(NumberOfBook);
            temp.add(LentMoney);
            temp.add(EndDate);

            // Thêm dữ liệu vào data vector chính
            data.add(temp);
        }
        return data;
    }

    public int updateLentBook(Connection connection, int Code, int col, String value)
            throws ClassNotFoundException, SQLException {
        int updateStatus = 0;
        String sql;
        Statement stm1;

        switch (col){
            case 0:
                sql = "UPDATE lentbook set STT='" + Integer.parseInt(value) + "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 1:
                sql = "UPDATE lentbook set BookName='" + value +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 2:
                sql = "UPDATE lentbook set NumberOfBook='" + Integer.parseInt(value) +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 3:
                sql = "UPDATE lentbook set LentMoney='" + Integer.parseInt(value) +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);

                return updateStatus;
            case 4:
                sql = "UPDATE lentbook set endDate='" + value +  "' WHERE STT='" + Code + "'";
                stm1 = connection.createStatement();
                updateStatus = stm1.executeUpdate(sql);
                return updateStatus;
        }
        return updateStatus;
    }

    public void addNewLentBook(Connection connection, int STT, String BookName, int NumberOfBook, int LentMoney, String endDate)
            throws ClassNotFoundException, SQLException {

        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO lentbook(STT,BookName,NumberOfBook,LentMoney,endDate) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, STT);
        stmt.setString(2, BookName);
        stmt.setInt(3, NumberOfBook);
        stmt.setInt(4, LentMoney);
        stmt.setString(5, endDate);


        // Thực hiện lệnh SQL
        stmt.executeUpdate();

        // Đóng kết nối

    }

    public int deleteLentBook(Connection connection, String Code) throws SQLException, ClassNotFoundException {
        int deleteStatus = 0;

        // Xóa sinh viên
        String sql = "DELETE FROM lentbook WHERE STT='" + Code + "'";
        Statement stm1 = connection.createStatement();
        deleteStatus = stm1.executeUpdate(sql);

        // Trả về kết quả int (có xóa thành công hay không)

        return deleteStatus;
    }
}

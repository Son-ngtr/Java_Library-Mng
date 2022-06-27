package Database;

import java.sql.*;

public class LibraryManager {
    private Connection connection;

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

}


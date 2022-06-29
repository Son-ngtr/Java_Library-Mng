package Database;

import java.sql.Connection;

public class ConectionDTB {
    public Connection getConnect(){
        AccountData accountData = new AccountData("root", "");
        Connection conn = LibraryManager.getConnect(accountData);
        return conn;
    }
}
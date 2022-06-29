package Database;

import java.sql.Connection;

public class ConnectionDTB {
    private Connection connection;

    public int ConnectionDTB(Connection conn){

        AccountData accountData = new AccountData("root", "");
        conn = LibraryManager.getConnect(accountData);
        return 0;
    }
}

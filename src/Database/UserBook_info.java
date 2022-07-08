package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserBook_info {
    public int createTable(Connection connection, String ID) throws SQLException {
        int createStatus = 0;
        String sql = "CREATE TABLE library.lentbook" + ID + "(STT INT(20) NOT NULL,BookName VARCHAR(255) NOT NULL,NumberOfBook INT(20) NOT NULL,LentMoney BIGINT(20) NOT NULL,endDate VARCHAR(255) NOT NULL,SerialNumber VARCHAR(255) NOT NULL,TimeLate BIGINT(20) NOT NULL)ENGINE=InnoDB" ;
        Statement stm1 = connection.createStatement();

        createStatus = stm1.executeUpdate(sql);
        return createStatus;
    }

    public int deleteTable(Connection connection, String ID) throws SQLException {
        int deleteStatus = 0;
        String sql = "DROP TABLE library." + "lentbook" + ID;
        Statement stm1 = connection.createStatement();

        deleteStatus = stm1.executeUpdate(sql);
        return deleteStatus;
    }

    public int renameTable(Connection connection, String ID) throws SQLException {
        String backStepID = String.valueOf(Integer.parseInt(ID) - 1);
        int renameStatus = 0;
        String sql = "RENAME TABLE library." + "lentbook" + ID + " TO library." + "lentbook" + backStepID;

        Statement stm1 = connection.createStatement();
        renameStatus = stm1.executeUpdate(sql);
        return renameStatus;
    }

    public void deleteAndSort(Connection connection, String ID, String maxID) throws SQLException {
        deleteTable(connection, ID);
        for (int i=Integer.parseInt(ID)+1 ; i<=Integer.parseInt(maxID); i++){
            String currentID = String.valueOf(i);
            renameTable(connection, currentID);
        }
    }
}

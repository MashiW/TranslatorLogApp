package mytranslator;

import java.sql.*;

public class Database {

    /**
     * Database connection class
     */

    private static Connection conn;

    public Database(String dburl, String database, String dbUname, String dbPasswd) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.conn = DriverManager.getConnection(dburl+ database, dbUname, dbPasswd);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public ResultSet runSql(String sql) throws SQLException {
        PreparedStatement sta = conn.prepareStatement(sql);
        return sta.executeQuery(sql);
    }
}

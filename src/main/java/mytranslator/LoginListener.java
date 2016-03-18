package mytranslator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginListener implements ServletContextListener {
    /**
     * coventntext listener class for database connection
     */

    public void contextInitialized(ServletContextEvent event) {

        ServletContext sc = event.getServletContext();

        String dburl = sc.getInitParameter("dburl");//  url of the database
        String dbUname = sc.getInitParameter("db_uname");// user name for database
        String dbPasswd = sc.getInitParameter("db_pswd");// password for the database
        String databse = sc.getInitParameter("database");//database name
        Database db = new Database(dburl + databse, dbUname, dbPasswd);
        sc.setAttribute("db", db);
    }

    public void contextDestroyed(ServletContextEvent arg1) {
        /**
         * Database connection close in the centext destroy
         */
        Connection conn = Database.getConn();
        if (conn != null){
            try {
                conn.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

    }

}
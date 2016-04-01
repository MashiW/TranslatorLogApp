package mytranslator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by hsenid on 3/14/16.
 */
public class Login extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        /*new obj creation of Translation class*/
        Translation translate = null;

        response.setContentType("text/html");

        /**
         *username = get the name entered by user's input
         * password = get the password entered by user's input
         */
        String username = request.getParameter("txtUn");
        String password = request.getParameter("txtPw");

        if (loginValidate(username, password)) {

            /**
             Setting session by user name
             */
            HttpSession session = request.getSession();
            session.setAttribute("sessionname", username);

            /*
            * get the languages to an array list as langlist
            * set attribute of the langlist as 'list'
            * */
            translate = new Translation();
            ArrayList<String> langlist = translate.getLangs();
            request.setAttribute("list", langlist);

            /** send result to the translate page
            */
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/logintranslate.jsp");
            rd.forward(request, response);

        } else {
            request.setAttribute("errorMessage", "Invalid credentials. Try again !");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

    public boolean loginValidate(String uname, String pswd) throws ServletException {

        /**
         * sql - query for the data extraction from tbl_user and compare with user's input name and password
         * password is compared after hashing
         */
        String sql = "select usrName, usrPass from tbl_user where usrName=binary\"" + uname + "\" and usrPass=md5(\"" + pswd + "\");";

        ResultSet rs = null;
        Connection con;
        PreparedStatement st = null;
        boolean result = false;

        try {
            con = Database.getConn();
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            if (rs.first()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        return result;
    }
}

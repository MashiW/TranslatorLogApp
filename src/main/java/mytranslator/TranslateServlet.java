package mytranslator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by hsenid on 3/16/16.
 */
public class TranslateServlet extends HttpServlet {

    @Deprecated

    protected static final String Encoding = "UTF-8";

    Translation getreply = null;

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response){

        response.setContentType("text/html");

        String fromText = request.getParameter("txtFromText"); // text field's value enter by user
        String fromLang = request.getParameter("frmType"); // from language
        String toLang = request.getParameter("toType"); //  language to be translated

        try {
            getreply = new Translation();
            String textreply = getreply.textTranslate(fromLang,toLang,fromText);

            //set the language list in the page redirect
            ArrayList<String> list = new ArrayList<String>();
            list = getreply.getLangs();
            request.setAttribute("list", list);

            request.setAttribute("selectedFrom", fromLang);
            request.setAttribute("selectedTo", toLang);

            // set the attribute for translated text
            request.setAttribute("textreply", textreply);

            //set the attribute for input text
            request.setAttribute("fromText", fromText);

            //send the result to logintranslate.jsp page
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/logintranslate.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            try {
                throw new ServletException(ex);
            } catch (ServletException e) {
                e.printStackTrace();
            }

        }
    }

}

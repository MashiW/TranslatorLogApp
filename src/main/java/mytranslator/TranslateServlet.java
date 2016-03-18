package mytranslator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hsenid on 3/16/16.
 */
public class TranslateServlet extends HttpServlet {

    @Deprecated

    protected static final String Encoding = "UTF-8";

    protected static String apiKey;
    public String fromText;
    public String fromLang;
    public String toLang;
    public static String url;

    Translation getreply=null;

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, SecurityException, ServletException {

        response.setContentType("text/html");

        fromText =  request.getParameter("txtFromText"); // text field's value enter by user
        fromLang = request.getParameter("frmType"); // from language
        toLang =request.getParameter("toType"); //  language to be translated

        apiKey = "trnsl.1.1.20160311T110648Z.2843309257351b77.503eb0ab4fee6d8e09936972b4bc73810e4b12b4"; // your API key

        url = "https://translate.yandex.net/api/v1.5/tr/translate?key="+apiKey+"&text="+fromText+"&lang="+fromLang+"-"+toLang;

        try {
            getreply= new Translation();
            String textreply = getreply.textTranslate();

            //set the language list in the page redirect
            ArrayList<String> list=new ArrayList<String>();
            list=getreply.getLangs();
            request.setAttribute("list",list);

           // set the attribute for translated text
            request.setAttribute("textreply",textreply);

            //send the result to logintranslate.jsp page
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/logintranslate.jsp");
            rd.forward(request,response);

        }catch (Exception ex){
            throw new ServletException(ex);

        }
    }
}

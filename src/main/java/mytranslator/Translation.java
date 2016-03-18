package mytranslator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;

public class Translation {

@Deprecated
    public ArrayList<String> getLangs() throws Exception {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://translate.yandex.net/api/v1.5/tr/getLangs?key=trnsl.1.1.20160311T110648Z.2843309257351b77.503eb0ab4fee6d8e09936972b4bc73810e4b12b4&ui=en");
        HttpResponse response = httpClient.execute(request);

        // Get the response
        InputStream input = response.getEntity().getContent();

        //creating DOM object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

        Document doc = builder.parse(input);

        //Element root = doc.getDocumentElement();
        NodeList nameNodesList = doc.getElementsByTagName("Item"); //tagname =Item

        //get result to an array list
        ArrayList<String> listValues = new ArrayList<String>();

        //get the Item tag's attribute value
        for (int i = 0; i < nameNodesList.getLength(); i++) {
            listValues.add(nameNodesList.item(i).getAttributes().getNamedItem("value").getNodeValue());
        }

        return listValues;
    }

    public String textTranslate() throws Exception{

        Translation objtrans ;
        String transtext;

        HttpClient client= new DefaultHttpClient();
        objtrans =new Translation();
        HttpGet rq = new HttpGet(TranslateServlet.url);
        HttpResponse resp = client.execute(rq);

        //get response
        InputStream input2 = resp.getEntity().getContent();

        //creating DOM
        DocumentBuilderFactory dbf2 =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder2 = dbf2.newDocumentBuilder();

        Document doc2 = builder2.parse(input2);

        NodeList textNodelist = doc2.getElementsByTagName("text");
        transtext = String.valueOf(textNodelist.item(0).getTextContent());

        return transtext;
    }
}


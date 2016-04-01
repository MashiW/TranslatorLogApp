package mytranslator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Translation {
    /*
    * getLangs = method for getting the language list
    * */

    @Deprecated
    public ArrayList<String> getLangs() {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://translate.yandex.net/api/v1.5/tr/getLangs?key=trnsl.1.1.20160311T110648Z.2843309257351b77.503eb0ab4fee6d8e09936972b4bc73810e4b12b4&ui=en");
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the response
        InputStream input = null;
        try {
            input = response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creating DOM object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = builder.parse(input);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public String textTranslate(String fromLang,String toLang,String fromText){

        PropertyReader propobj2 =new PropertyReader();

        String yndxUrl = propobj2.getproperty("yandex.url");
        String apiKey =propobj2.getproperty("yandex.api.key");
        String transtext;
        String url = yndxUrl + apiKey + "&text=" + fromText + "&lang=" + fromLang + "-" + toLang;
        HttpClient client = new DefaultHttpClient();

        HttpGet rq = new HttpGet(url);
        HttpResponse resp = null;
        try {
            resp = client.execute(rq);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get response
        InputStream input2 = null;
        try {
            input2 = resp.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creating DOM
        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder2 = null;
        try {
            builder2 = dbf2.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc2 = null;
        try {
            doc2 = builder2.parse(input2);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList textNodelist = doc2.getElementsByTagName("text");
        transtext = String.valueOf(textNodelist.item(0).getTextContent());

        return transtext;
    }
}


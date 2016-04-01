package mytranslator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hsenid on 3/25/16.
 */
public class PropertyReader {

    Properties properties = new Properties();
    ClassLoader classLoader = getClass().getClassLoader();

    InputStream stream = classLoader.getResourceAsStream("system.properties");

        public String getproperty(String prop){

            try {
                properties.load(stream);
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            return properties.getProperty(prop);
        }

    public void closeStream(){

        if (stream != null)
        {
            try {
                stream.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }

    }
}

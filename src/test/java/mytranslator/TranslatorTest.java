package mytranslator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class TranslatorTest {

    Translation translation = new Translation();
    boolean rep =false;

    /*
    * Using data provider for testcase
    * */
    @DataProvider(name = "text")
    public Object[][] text() {

        return new Object[][]{
                {"en", "fr", "child","enfant"}, // correct language selection
        };
    }

    @Test(dataProvider = "text")
    public void testTranslation(String fromLang, String toLang, String fromText, String exp) {

        Translation translation = new Translation();
        try {
            String rep = translation.textTranslate(fromLang, toLang, fromText);
            Assert.assertEquals(rep, exp, "Correct translation");
            System.out.println(fromText);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void  wrongFromLanguage(){

        try{
             rep= Boolean.parseBoolean(translation.textTranslate("fr", "en", "child"));
        }catch (Exception ex){
            fail();
        }
        Assert.assertEquals(rep,false,"Wrong FromLanguage selected");
    }

    @Test
    public void  wrongToLanguage(){

        try{
            rep= Boolean.parseBoolean(translation.textTranslate("en", "fr", "enfant"));
        }catch (Exception ex){
            fail();
        }
        Assert.assertEquals(rep,false,"Wrong ToLanguage selected");
    }

    @Test
    public void  emptyInputText(){

        try{
            rep= Boolean.parseBoolean(translation.textTranslate("en", "fr", ""));
        }catch (Exception ex){
            fail();
        }
        Assert.assertEquals(rep,false,"Empty input text");
    }
    @Test
    public void  invalidCharacters(){

        try{
            rep= Boolean.parseBoolean(translation.textTranslate("en", "fr", "u*6frrr"));
        }catch (Exception ex){
            fail();
        }
        Assert.assertEquals(rep,false,"Invalid Input Characters");
    }

  /*
        @Test(expectedExceptions = NullPointerException.class)
        public void testNullPointerException() {
            List list = null;
            int size = list.size();
        }*//*

   *//* @Test
    public void testNullPointerException() {
        try {
            TranslateServlet trans = new TranslateServlet();
            trans.validateTranslt(frmtext);
           if()
            fail("The test should have failed");
        } catch (NullPointerException e) {
            // success, do nothing: the test will pass
        }
    }*/
}


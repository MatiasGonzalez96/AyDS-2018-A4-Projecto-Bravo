package ayds.dictionary.bravo.fulllogic.Model;

import android.content.Context;
import android.text.Html;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ayds.dictionary.bravo.fulllogic.DataBase;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DictionaryModelImpl implements DictionaryModel
{
    private Dictionary dictionary;
    private DictionaryModelListener listener;
    private final WikipediaAPI wikiAPI;
    private String lastDef;

    DictionaryModelImpl(Context context)
    {
        //Crea la base de datos
        init(context);

        //Crear metodo para que haga esto
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        wikiAPI = retrofit.create(WikipediaAPI.class);


    }

    @Override public void setListener(DictionaryModelListener listener) {
        this.listener = listener;
    }

    @Override public Dictionary getDictionary() {
        return dictionary;
    }

    @Override public void buscarTermino(String input) {
        buscarTerminoNow(input);
        notifyListener();
    }

    private void buscarTerminoNow(final String input)
    {
        //Logica del buscar
        new Thread(new Runnable() {
            public void run() {

                String text = DataBase.getMeaning(input);

                if (text != null) { // exists in db

                    text = "[*]" + text;
                } else {
                    Response<String> callResponse;
                    try {
                        callResponse = wikiAPI.getTerm(input).execute();

                        Log.e("**", "XML: " + callResponse.body());

                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        DocumentBuilder db = dbf.newDocumentBuilder();
                        Document doc = db.parse(new InputSource(new StringReader(callResponse.body())));


                        NodeList nodes = doc.getDocumentElement().getElementsByTagName("extract");

                        Node extract = nodes.item(0);

                        Log.e("**", "XML " + extract);

                        if (extract == null) {
                            text = "No Results";
                        } else {
                            lastDef=extract.getTextContent();
                            text = extract.getTextContent().replace("\\n", "\n");
                            text = textToHtml(text, input);

                            // save to DB  <o/
                            DataBase.saveTerm(input, text);
                        }

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                }



            }
        }).start();
    }

    public String getLastDefinition()
    {
        return lastDef;
    }

    private void notifyListener() {
        if (listener != null) {
            listener.didUpdateDictionary();
        }
    }

    private void init(final Context context) {

        new Thread(new Runnable() {
            @Override public void run() {
                Dictionary.createNewDatabase(context);
                Dictionary.saveTerm("test", "sarasa");

                Log.e("**", "" + DataBase.getMeaning("test"));
                Log.e("**", "" + DataBase.getMeaning("nada"));
            }
        }).start();
    }

    public static String textToHtml(String text, String term) {

        StringBuilder builder = new StringBuilder();

        String textWithBold = text.replaceAll("(?i)" + term, "<b>" + term + "</b>");

        builder.append(textWithBold);

        return builder.toString();
    }
}

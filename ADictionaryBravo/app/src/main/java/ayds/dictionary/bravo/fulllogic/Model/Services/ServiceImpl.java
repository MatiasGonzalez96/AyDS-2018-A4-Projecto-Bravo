package ayds.dictionary.bravo.fulllogic.Model.Services;

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

import ayds.dictionary.bravo.fulllogic.Model.DictionaryModelModule;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceImpl implements Service
{
    private WikipediaAPI wikiAPI;
    private final String wikiAPIUrl="https://en.wikipedia.org/w/";

    public ServiceImpl()
    {
        connectAPI();
    }

    public void connectAPI()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(wikiAPIUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        wikiAPI = retrofit.create(WikipediaAPI.class);
        Log.e("**", ""+wikiAPI);
    }

    public String getMeaning(String input)
    {
        try
        {
            Response<String> callResponse= wikiAPI.getTerm(input).execute();
            Node extract = getExtract(callResponse);
            if(extract==null)
            {
                return null;
            }
            else
            {
                return extract.getTextContent().replace("\\n", "\n");
            }
        }
        catch (IOException e)
        {
            Log.e("**","error de conexion");
            DictionaryModelModule.getInstance(null).getDictionaryErrorListener().didFindError();
        }
        return null;
    }

    private Node getExtract(Response<String> callResponse)
    {
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(callResponse.body())));
            NodeList nodes = doc.getDocumentElement().getElementsByTagName("extract");
            Node extract = nodes.item(0);
            return extract;
        }
        catch(ParserConfigurationException | IOException | SAXException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

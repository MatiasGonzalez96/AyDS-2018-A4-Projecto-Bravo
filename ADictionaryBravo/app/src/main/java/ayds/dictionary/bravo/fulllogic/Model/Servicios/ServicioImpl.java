package ayds.dictionary.bravo.fulllogic.Model.Servicios;



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

import ayds.dictionary.bravo.fulllogic.Model.Servicios.Servicio;
import ayds.dictionary.bravo.fulllogic.Model.WikipediaAPI;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ServicioImpl implements Servicio {

    private WikipediaAPI wikiAPI;

    public ServicioImpl()
    {
        conectar();
    }

    public void conectar()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        wikiAPI = retrofit.create(WikipediaAPI.class);

    }

    public String getMeaning(String input) {
        try {
            Response<String> callResponse= wikiAPI.getTerm(input).execute();
            Node extract = getExtract(callResponse);
            if(extract==null)
            {
                return "no results";
            }
            else
            {
                Log.e("**", "XML: " + callResponse.body());
                Log.e("**", "XML " + extract);
                return extract.getTextContent().replace("\\n", "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
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

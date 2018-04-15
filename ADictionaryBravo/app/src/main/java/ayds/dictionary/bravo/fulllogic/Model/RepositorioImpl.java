package ayds.dictionary.bravo.fulllogic.Model;

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

import ayds.dictionary.bravo.fulllogic.Model.BDD.Dictionary;
import ayds.dictionary.bravo.fulllogic.Model.Servicios.Servicio;
import retrofit2.Response;

public class RepositorioImpl implements Repositorio
{

    String toRet;
    Servicio servicioImpl;

    public RepositorioImpl(Servicio servicioImpl)
    {
        this.servicioImpl=servicioImpl;
    }

    public String buscarTermino(final String input) {
        new Thread(new Runnable() {
            public void run() {

                String text = Dictionary.getMeaning(input);
                if (text != null) { // exists in db

                    text = "[*]" + text;
                    toRet=text;
                } else {
                    Response<String> callResponse = servicioImpl.getMeaning(input);

                    Log.e("**", "XML: " + callResponse.body());

                    Node extract = getExtract(callResponse);

                    Log.e("**", "XML " + extract);

                    if (extract == null) {
                        text = "No Results";
                        toRet=text;
                    } else {

                        text = extract.getTextContent().replace("\\n", "\n");
                        toRet=text;

                        // save to DB  <o/
                        Dictionary.saveTerm(input, text);
                    }
                }
            }
        }).start();

        return toRet;
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


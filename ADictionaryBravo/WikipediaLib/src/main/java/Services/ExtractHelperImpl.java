package Services;

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

class ExtractHelperImpl implements ExtractHelper
{
    @Override
    public String getExtract(String input)
    {
        return getExtractContext(input);
    }

    private String getExtractContext(String callResponse)
    {
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(callResponse)));
            NodeList nodes = doc.getDocumentElement().getElementsByTagName("extract");
            Node extract = nodes.item(0);
            if(extract!=null) {
                return extract.getTextContent();
            }
            }
        catch(ParserConfigurationException | IOException | SAXException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

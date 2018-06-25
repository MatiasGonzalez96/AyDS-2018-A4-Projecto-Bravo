package ayds.dictionary.bravo.Model.Service;

import java.util.Map;

import ayds.dictionary.bravo.Model.Source;

public interface ServiceFactory
{
    Map<Source, ServiceDef> getServices();
}

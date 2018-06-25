package ayds.dictionary.bravo.Model.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ayds.dictionary.bravo.Model.Source;

class ServicesDefImpl implements ServicesDef
{
    @Override
    public String getMeaning(String input, Source source) throws Exception
    {
        ServiceFactory serviceFactory = ServicesModule.getInstance().getServiceFactory();
        ServiceDef serviceDef = serviceFactory.getServices().get(source);
        String definition = serviceDef.getMeaning(input);
        return definition;
    }

    @Override
    public List<Source> getSources()
    {
        ServiceFactory serviceFactory = ServicesModule.getInstance().getServiceFactory();
        Map<Source, ServiceDef> serviceMap = serviceFactory.getServices();
        List<Source> sourceList = new LinkedList<>();
        for (Source source : serviceMap.keySet())
        {
            sourceList.add(source);
        }
        return sourceList;
    }
}

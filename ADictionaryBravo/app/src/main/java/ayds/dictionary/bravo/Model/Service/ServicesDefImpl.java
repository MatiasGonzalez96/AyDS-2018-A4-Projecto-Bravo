package ayds.dictionary.bravo.Model.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ayds.dictionary.bravo.Model.Source;

class ServicesDefImpl implements ServicesDef
{
    private ServiceFactory serviceFactory;

    ServicesDefImpl()
    {
        serviceFactory = ServicesModule.getInstance().getServiceFactory();
    }

    @Override
    public String getMeaning(String input, Source source) throws Exception
    {
        ServiceDef serviceDef = serviceFactory.getServices().get(source);
        String definition = serviceDef.getMeaning(input);
        return definition;
    }

    @Override
    public List<Source> getSources()
    {
        Map<Source, ServiceDef> serviceMap = serviceFactory.getServices();
        List<Source> sourceList = new LinkedList<>();
        for (Source source : serviceMap.keySet())
        {
            sourceList.add(source);
        }
        return sourceList;
    }
}

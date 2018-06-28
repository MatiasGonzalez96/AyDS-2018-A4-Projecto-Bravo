package ayds.dictionary.bravo.Model.Service;

import com.example.yandex.service.ServiceModule;
import com.example.yandex.service.TranslatorService;
import SearchService.SearchService;
import SearchService.SearchServiceModule;
import ayds.dictionary.bravo.Model.StringHelper;
import wikipedia.service.WikipediaService;
import wikipedia.service.WikipediaServiceModule;

public class ServicesModule
{
    private static ServicesModule instance;

    private  ServicesModule(){}

    public static ServicesModule getInstance()
    {
        if(instance == null)
        {
            instance =  new  ServicesModule();
        }
        return instance;
    }

    public ServiceFactory getServiceFactory()
    {
        WikipediaService wikipediaService = WikipediaServiceModule.getInstance().getService();
        SearchService bigHugeLabsService = SearchServiceModule.getInstance().getSearchService();
        TranslatorService yandexService = ServiceModule.getInstance().getTranslatorService();
        StringHelper stringHelper = StringHelper.getInstance();
        ServiceFactory serviceFactory = new ServiceFactoryImpl(wikipediaService, bigHugeLabsService, yandexService, stringHelper);
        return serviceFactory;
    }

    public ServicesDef getServicesDef()
    {
        ServiceFactory serviceFactory = getServiceFactory();
        ServicesDef servicesDef = new ServicesDefImpl(serviceFactory);
        return servicesDef;
    }
}

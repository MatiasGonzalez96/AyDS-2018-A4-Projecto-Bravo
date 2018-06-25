package ayds.dictionary.bravo.Model.Service;

import com.example.yandex.service.ServiceModule;
import com.example.yandex.service.TranslatorService;

import SearchService.SearchService;
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
        SearchService bigHugeLabsService = SearchService.SearchServiceModule.getInstance().getSearchService();
        TranslatorService yandexService = ServiceModule.getInstance().getTranslatorService();

        ServiceFactory serviceFactory = new ServiceFactoryImpl(wikipediaService, bigHugeLabsService, yandexService);
        return serviceFactory;
    }

    public ServicesDef getServicesDef()
    {
        ServicesDef servicesDef = new ServicesDefImpl();
        return servicesDef;
    }
}

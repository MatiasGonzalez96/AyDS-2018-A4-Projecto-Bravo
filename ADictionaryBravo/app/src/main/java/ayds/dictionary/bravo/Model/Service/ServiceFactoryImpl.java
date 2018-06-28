package ayds.dictionary.bravo.Model.Service;

import com.example.yandex.service.TranslatorService;
import java.util.HashMap;
import java.util.Map;
import ayds.dictionary.bravo.Model.Source;
import ayds.dictionary.bravo.Model.StringHelper;
import wikipedia.service.WikipediaService;
import SearchService.SearchService;

class ServiceFactoryImpl implements ServiceFactory
{
    private Map<Source, ServiceDef> serviceMap;

    ServiceFactoryImpl(WikipediaService wikipediaService, SearchService bigHugeLabsService, TranslatorService yandexService, StringHelper stringHelper)
    {
        serviceMap = new HashMap<>();

        ServiceDef wikipediaServiceAdapter = new WikipediaServiceAdapter(wikipediaService, stringHelper);
        ServiceDef yandexServiceAdapter = new YandexServiceAdapter(yandexService, stringHelper);
        ServiceDef bigHugeLabsServiceAdapter = new BigHugeLabsServiceAdapter(bigHugeLabsService, stringHelper);

        serviceMap.put(Source.WIKIPEDIA, wikipediaServiceAdapter);
        serviceMap.put(Source.YANDEX, yandexServiceAdapter);
        serviceMap.put(Source.BIGHUGELABS, bigHugeLabsServiceAdapter);
    }

    @Override
    public Map<Source, ServiceDef> getServices()
    {
        return serviceMap;
    }
}

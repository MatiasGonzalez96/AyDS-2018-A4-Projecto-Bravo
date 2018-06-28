package ayds.dictionary.bravo.Model.Service;

import com.example.yandex.service.TranslatorService;

class YandexServiceAdapter implements ServiceDef
{
    private TranslatorService yandexService;

    YandexServiceAdapter(TranslatorService yandexService)
    {
        this.yandexService = yandexService;
    }

    @Override
    public String getMeaning(String input) throws Exception
    {
        String result = yandexService.callCreateTranslatedWord(input);
        return result;
    }
}

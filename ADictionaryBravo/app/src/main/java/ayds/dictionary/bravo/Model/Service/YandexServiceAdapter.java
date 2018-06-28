package ayds.dictionary.bravo.Model.Service;

import com.example.yandex.service.NoConnectionException;
import com.example.yandex.service.TranslatorService;
import ayds.dictionary.bravo.Model.Exception.InvalidInputException;
import ayds.dictionary.bravo.Model.Exception.ModelNoConnectionException;
import ayds.dictionary.bravo.Model.StringHelper;

class YandexServiceAdapter implements ServiceDef
{
    private TranslatorService yandexService;
    private StringHelper stringHelper;

    YandexServiceAdapter(TranslatorService yandexService, StringHelper stringHelper)
    {
        this.yandexService = yandexService;
        this.stringHelper = stringHelper;
    }

    @Override
    public String getMeaning(String input) throws Exception
    {
        String result = "";
        try
        {
            checkValidFormat(input);
            result = yandexService.callCreateTranslatedWord(input);
        }
        catch(NoConnectionException exception)
        {
            throw new ModelNoConnectionException();
        }
        return result;
    }

    private void checkValidFormat(String input) throws InvalidInputException
    {
        if (stringHelper.invalidInput(input))
        {
            throw new InvalidInputException();
        }
    }
}

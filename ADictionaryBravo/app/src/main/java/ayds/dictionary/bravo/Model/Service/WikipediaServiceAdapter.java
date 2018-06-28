package ayds.dictionary.bravo.Model.Service;

import java.io.IOException;
import ayds.dictionary.bravo.Model.Exception.InvalidInputException;
import ayds.dictionary.bravo.Model.Exception.ModelNoConnectionException;
import ayds.dictionary.bravo.Model.StringHelper;
import wikipedia.service.WikipediaService;

class WikipediaServiceAdapter implements ServiceDef
{
    private WikipediaService wikipediaService;
    private StringHelper stringHelper;

    WikipediaServiceAdapter(WikipediaService wikipediaService, StringHelper stringHelper)
    {
        this.wikipediaService = wikipediaService;
        this.stringHelper = stringHelper;
    }

    @Override
    public String getMeaning(String input) throws Exception
    {
        String result = "";
        try
        {
            checkValidFormat(input);
            result = wikipediaService.getMeaning(input);
        }
        catch(IOException exception)
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

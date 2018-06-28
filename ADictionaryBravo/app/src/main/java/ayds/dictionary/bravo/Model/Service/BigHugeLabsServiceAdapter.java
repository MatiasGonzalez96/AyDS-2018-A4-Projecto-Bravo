package ayds.dictionary.bravo.Model.Service;

import SearchService.*;
import ayds.dictionary.bravo.Model.Exception.InvalidInputException;
import ayds.dictionary.bravo.Model.Exception.ModelNoConnectionException;
import ayds.dictionary.bravo.Model.StringHelper;

class BigHugeLabsServiceAdapter implements ServiceDef
{
    private SearchService bighugeService;
    private StringHelper stringHelper;

    BigHugeLabsServiceAdapter(SearchService bighugeService, StringHelper stringHelper)
    {
        this.bighugeService = bighugeService;
        this.stringHelper = stringHelper;
    }

    @Override
    public String getMeaning(String input) throws Exception
    {
        String result = "";
        try
        {
            checkValidFormat(input);
            result = bighugeService.searchTerm(input);
        }
        catch(ServiceNotReachableException exception)
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

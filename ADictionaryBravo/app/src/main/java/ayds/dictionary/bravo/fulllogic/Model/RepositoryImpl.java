package ayds.dictionary.bravo.fulllogic.Model;

import android.util.Log;

import ayds.dictionary.bravo.fulllogic.Model.DataBase.DictionaryDataBase;
import ayds.dictionary.bravo.fulllogic.Model.Services.Service;

class RepositoryImpl implements Repository
{
    private Service serviceImpl;
    private DictionaryDataBase dataBase;
    private final String dataBaseSavedPrefix="[*]";
    private final String noResultsMessage="No results";

    RepositoryImpl(Service serviceImpl, DictionaryDataBase dataBase)
    {
        this.serviceImpl = serviceImpl;
        this.dataBase = dataBase;
    }

    public String getTerm(final String input)
    {
        String returnText;
        if(invalidInput(input))
        {
            Log.e("**","Entre al if");
            return null;

        }
        else {
            returnText = dataBase.getMeaning(input);
            if (returnText != null) {
                // exists in db
                returnText = dataBaseSavedPrefix + returnText;
            } else {
                returnText = serviceImpl.getMeaning(input);
                if (returnText != null) {
                    dataBase.saveTerm(input, returnText);
                } else {
                    returnText = noResultsMessage;
                }
            }
        }
        return returnText;
    }

    private boolean invalidInput(String input)
    {
        boolean result;
        if(!StringHelper.getInstance().onlyLetters(input) || input.isEmpty())
        {
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }
}
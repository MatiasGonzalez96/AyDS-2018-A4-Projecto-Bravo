package ayds.dictionary.bravo.fulllogic.Model;

import ayds.dictionary.bravo.fulllogic.Model.DataBase.DictionaryDataBase;
import ayds.dictionary.bravo.fulllogic.Model.Services.Service;

public class RepositoryImpl implements Repository
{
    Service serviceImpl;
    DictionaryDataBase dataBase;

    public RepositoryImpl(Service serviceImpl, DictionaryDataBase dataBase)
    {
        this.serviceImpl = serviceImpl;
        this.dataBase = dataBase;
    }

    public String searchTerm(final String input)
    {

        String returnText = dataBase.getMeaning(input);
        if (returnText != null)
        {
            // exists in db
            returnText = "[*]" + returnText;
        }
        else
        {
            returnText= serviceImpl.getMeaning(input);
            dataBase.saveTerm(input, returnText);
        }
        return returnText;
    }
}


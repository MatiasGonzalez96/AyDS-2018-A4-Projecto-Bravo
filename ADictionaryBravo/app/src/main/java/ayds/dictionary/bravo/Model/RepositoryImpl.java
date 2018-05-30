package ayds.dictionary.bravo.Model;

import java.io.IOException;
import ayds.dictionary.bravo.Model.DataBase.DictionaryDataBase;
import Services.Service;
import ayds.dictionary.bravo.Model.Exception.ApplicationException;
import ayds.dictionary.bravo.Model.Exception.ErrorHandler;

class RepositoryImpl implements Repository
{
    private Service service;
    private DictionaryDataBase dataBase;
    private ErrorHandler errorHandler;
    private final String dataBaseSavedPrefix= "[*]";
    private final String noResultsMessage= "No Results";
    private final String incorrectInputMessage= "Incorrect Input";
    private final String connectionErrorMessage= "Connection Error";

    RepositoryImpl(Service service, DictionaryDataBase dataBase, ErrorHandler errorHandler)
    {
        this.service = service;
        this.dataBase = dataBase;
        this.errorHandler = errorHandler;
    }

    public Definition getTerm(final String input)
    {
        Definition definition = null;
        try {
            if (invalidInput(input)) {
                errorHandler.notifyError(new ApplicationException(incorrectInputMessage));
                return null;
            } else {
                definition = dataBase.getMeaning(input);
                if (definition != null) {
                    String result = dataBaseSavedPrefix + definition.getMeaning();
                    definition.setMeaning(result);
                } else {
                    String result = service.getMeaning(input);
                    if (result != null && result != "") {
                        definition = new Definition();
                        definition.setTerm(input);
                        definition.setMeaning(result);
                        definition.setSource(Source.WIKIPEDIA);
                        dataBase.saveTerm(definition);
                    } else {
                        definition = new Definition();
                        definition.setMeaning(noResultsMessage);
                        definition.setSource(Source.WIKIPEDIA);
                    }
                }
            }
        }
        catch(IOException e)
        {
            errorHandler.notifyError(new ApplicationException(connectionErrorMessage));
        }
        catch (Exception e) {
            errorHandler.notifyError(e);
        }
        return definition;
    }

    private boolean invalidInput(String input)
    {
        boolean result;
        if(!StringHelper.getInstance().onlyLetters(input) || input.isEmpty() || input == null)
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
package ayds.dictionary.bravo.Model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import ayds.dictionary.bravo.Model.DataBase.DictionaryDataBase;
import ayds.dictionary.bravo.Model.Exception.ApplicationException;
import ayds.dictionary.bravo.Model.Exception.ErrorHandler;
import ayds.dictionary.bravo.Model.Service.ServicesDef;
import ayds.dictionary.bravo.Model.Service.ServicesModule;

class RepositoryImpl implements Repository
{
    private DictionaryDataBase dataBase;
    private ErrorHandler errorHandler;
    private final String dataBaseSavedPrefix= "[*]";
    private final String noResultsMessage= "No Results";
    private final String incorrectInputMessage= "Incorrect Input";
    private final String connectionErrorMessage= "Connection Error";

    RepositoryImpl(DictionaryDataBase dataBase, ErrorHandler errorHandler)
    {
        this.dataBase = dataBase;
        this.errorHandler = errorHandler;
    }

    public List<Definition> getTerm(final String input)
    {
        ServicesDef servicesDef = ServicesModule.getInstance().getServicesDef();
        List<Source> sourceList = servicesDef.getSources();
        List<Definition> definitionList = new LinkedList<>();

        for (Source source : sourceList)
        {
            try {
                if (invalidInput(input)) {
                    errorHandler.notifyError(new ApplicationException(incorrectInputMessage));
                    return null;
                } else {
                    Definition definition = dataBase.getMeaning(input, source);
                    if (definition != null) {
                        String result = dataBaseSavedPrefix + definition.getMeaning();
                        definition.setMeaning(result);
                        definitionList.add(definition);
                    } else {
                        String result = servicesDef.getMeaning(input, source);
                        if (result != null && result != "") {
                            definition = new Definition();
                            definition.setTerm(input);
                            definition.setMeaning(result);
                            definition.setSource(source);
                            dataBase.saveTerm(definition);
                            definitionList.add(definition);
                        } else {
                            definition = new Definition();
                            definition.setMeaning(noResultsMessage);
                            definition.setSource(source);
                            definitionList.add(definition);
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
        }
        return definitionList;
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
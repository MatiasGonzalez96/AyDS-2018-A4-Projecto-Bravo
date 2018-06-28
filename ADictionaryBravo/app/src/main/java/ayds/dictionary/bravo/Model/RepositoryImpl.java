package ayds.dictionary.bravo.Model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ayds.dictionary.bravo.Model.DataBase.DictionaryDataBase;
import ayds.dictionary.bravo.Model.Exception.ErrorHandler;
import ayds.dictionary.bravo.Model.Service.ServicesDef;

class RepositoryImpl implements Repository
{
    private DictionaryDataBase dataBase;
    private ErrorHandler errorHandler;
    private final String dataBaseSavedPrefix= "[*]";
    private final String noResultsMessage= "No Results";
    private ServicesDef servicesDef;

    RepositoryImpl(DictionaryDataBase dataBase, ErrorHandler errorHandler, ServicesDef servicesDef)
    {
        this.dataBase = dataBase;
        this.errorHandler = errorHandler;
        this.servicesDef = servicesDef;
    }

    public List<Definition> getTerm(final String input)
    {
        List<Source> sourceList = servicesDef.getSources();
        List<Definition> definitionList = new LinkedList<>();
        Map<Source, Exception> exceptions = new HashMap<>();
        for (Source source : sourceList)
        {
            try
            {
                Definition definition = dataBase.getMeaning(input.trim(), source);
                if (definition != null) {
                    String result = dataBaseSavedPrefix + definition.getMeaning();
                    definition.setMeaning(result);
                    definitionList.add(definition);
                } else {
                    String result = servicesDef.getMeaning(input.trim(), source);
                    if (!result.isEmpty()) {
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
            catch(Exception exception)
            {
                exceptions.put(source,exception);
            }
        }
        if(!exceptions.isEmpty())
            errorHandler.notifyExceptions(exceptions);
        return definitionList;
    }
}
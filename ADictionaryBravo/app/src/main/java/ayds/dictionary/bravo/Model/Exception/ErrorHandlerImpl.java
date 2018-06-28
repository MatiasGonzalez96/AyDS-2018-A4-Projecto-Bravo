package ayds.dictionary.bravo.Model.Exception;

import java.util.Map;
import ayds.dictionary.bravo.Model.DictionaryModelModule;
import ayds.dictionary.bravo.Model.Source;

class ErrorHandlerImpl implements ErrorHandler
{
    @Override
    public void notifyError(String message)
    {
        DictionaryModelModule.getInstance(null).getDictionaryErrorListener().didFindError(message);
    }

    public void notifyExceptions(Map<Source, Exception> exceptions)
    {
        String exceptionMessage = "";
        for(Map.Entry<Source,Exception> entry : exceptions.entrySet()){
            if(entry.getValue() instanceof InvalidInputException || entry.getValue() instanceof ModelNoConnectionException)
                exceptionMessage += entry.getKey() + ": "+entry.getValue().getMessage()+"\n";
            else
                exceptionMessage += entry.getKey() + ": Error inesperado\n";
        }
        notifyError(exceptionMessage);
    }
}

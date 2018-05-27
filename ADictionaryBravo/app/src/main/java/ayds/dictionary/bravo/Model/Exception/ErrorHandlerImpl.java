package ayds.dictionary.bravo.Model.Exception;

import ayds.dictionary.bravo.Model.DictionaryModelModule;

class ErrorHandlerImpl implements ErrorHandler
{
    @Override
    public void notifyError(Exception e)
    {
        if (e instanceof ApplicationException)
        {
            DictionaryModelModule.getInstance(null).getDictionaryErrorListener().didFindError(e.getMessage());
        }
        else
        {
            DictionaryModelModule.getInstance(null).getDictionaryErrorListener().didFindError("Unexpected error has occurred");
        }
    }
}

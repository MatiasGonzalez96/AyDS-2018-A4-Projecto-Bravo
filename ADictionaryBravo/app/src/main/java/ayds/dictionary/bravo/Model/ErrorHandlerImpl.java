package ayds.dictionary.bravo.Model;

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

package ayds.dictionary.bravo.Model;

class DictionaryModelImpl implements DictionaryModel

{
    private DictionaryModelListener modelListener;
    private DictionaryErrorListener errorListener;
    private Repository repositoryImpl;

    DictionaryModelImpl(Repository repositoryImpl)
    {
        this.repositoryImpl = repositoryImpl;
    }

    @Override public void setModelListener(DictionaryModelListener modelListener)
    {
        this.modelListener = modelListener;
    }

    @Override public void setErrorListener(DictionaryErrorListener errorListener)
    {
        this.errorListener = errorListener;
    }

    @Override
    public DictionaryErrorListener getDictionaryErrorListener() {
        return errorListener;
    }


    @Override public void searchTerm(final String input)
    {
        String definition=searchTermNow(input);
        if(definition != null)
        {
            notifyModelListener(definition);
        }
        else
        {
            notifyErrorListener();
        }
    }

    private String searchTermNow(final String input)
    {
        return repositoryImpl.getTerm(input);
    }

    private void notifyModelListener(String definition)
    {
        if (modelListener != null)
        {
            modelListener.didUpdateDefinition(definition);
        }
    }

    private void notifyErrorListener()
    {
        if (errorListener != null)
        {
            errorListener.didFindError();
        }
    }
}

package ayds.dictionary.bravo.fulllogic.Model;

public class DictionaryModelImpl implements DictionaryModel
{
    private DictionaryModelListener listener;
    private Repository repositoryImpl;
    private String lastDefinition;

    DictionaryModelImpl(Repository repositoryImpl)
    {
        this.repositoryImpl = repositoryImpl;
    }

    @Override public void setListener(DictionaryModelListener listener)
    {
        this.listener = listener;
    }

    @Override public void searchTerm(final String input)
    {
        searchTermNow(input);
        notifyListener();
    }

    private void searchTermNow(final String input)
    {
        lastDefinition = repositoryImpl.searchTerm(input);
    }

    private void notifyListener()
    {
        if (listener != null)
        {
            listener.didUpdateDictionary(lastDefinition);
        }
    }
}

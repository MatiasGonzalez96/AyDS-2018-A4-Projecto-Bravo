package ayds.dictionary.bravo.Model;

import java.util.List;

import ayds.dictionary.bravo.Model.Listener.DictionaryErrorListener;
import ayds.dictionary.bravo.Model.Listener.DictionaryModelListener;

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
        List<Definition> definition = searchTermNow(input);
        if(definition != null)
        {
            notifyModelListener(definition);
        }
    }

    private List<Definition> searchTermNow(String input)
    {
        return repositoryImpl.getTerm(input);
    }

    private void notifyModelListener(List<Definition> definition)
    {
        if (modelListener != null)
        {
            modelListener.didUpdateDefinition(definition);
        }
    }
}

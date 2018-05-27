package ayds.dictionary.bravo.Model;

import ayds.dictionary.bravo.Model.Listener.DictionaryErrorListener;
import ayds.dictionary.bravo.Model.Listener.DictionaryModelListener;

public interface DictionaryModel
{
    void searchTerm(String input);
    void setModelListener(DictionaryModelListener listener);
    void setErrorListener(DictionaryErrorListener listener);
    DictionaryErrorListener getDictionaryErrorListener();
}

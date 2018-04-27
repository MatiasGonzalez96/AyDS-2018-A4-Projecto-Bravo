package ayds.dictionary.bravo.fulllogic.Model;

public interface DictionaryModel
{
    void searchTerm(String input);
    void setModelListener(DictionaryModelListener listener);
    void setErrorListener(DictionaryErrorListener listener);
    DictionaryErrorListener getDictionaryErrorListener();
}

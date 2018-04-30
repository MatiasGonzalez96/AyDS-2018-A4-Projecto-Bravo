package ayds.dictionary.bravo.Model;

public interface DictionaryModel
{
    void searchTerm(String input);
    void setModelListener(DictionaryModelListener listener);
    void setErrorListener(DictionaryErrorListener listener);
    DictionaryErrorListener getDictionaryErrorListener();
}

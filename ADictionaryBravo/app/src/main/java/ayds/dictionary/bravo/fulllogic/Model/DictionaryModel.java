package ayds.dictionary.bravo.fulllogic.Model;

public interface DictionaryModel
{
    void searchTerm(String input);
    void setListener(DictionaryModelListener listener);
}

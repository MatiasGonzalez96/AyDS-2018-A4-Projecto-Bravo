package ayds.dictionary.bravo.fulllogic.Model;

public interface DictionaryModel
{
    Dictionary getDictionary();
    void buscarTermino(String input);
    void setListener(DictionaryModelListener listener);
    String getLastDefinition();
}

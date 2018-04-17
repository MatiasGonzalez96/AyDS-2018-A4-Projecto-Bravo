package ayds.dictionary.bravo.fulllogic.Model.DataBase;

public interface DictionaryDataBase
{
    String getMeaning(String term);
    void saveTerm(String term, String meaning);
}

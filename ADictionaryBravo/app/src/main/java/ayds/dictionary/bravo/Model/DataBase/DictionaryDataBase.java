package ayds.dictionary.bravo.Model.DataBase;

public interface DictionaryDataBase
{
    String getMeaning(String term);
    void saveTerm(String term, String meaning);
}

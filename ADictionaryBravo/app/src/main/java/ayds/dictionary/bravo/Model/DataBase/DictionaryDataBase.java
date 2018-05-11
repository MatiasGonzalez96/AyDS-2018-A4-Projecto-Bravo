package ayds.dictionary.bravo.Model.DataBase;

import ayds.dictionary.bravo.Model.Definition;

public interface DictionaryDataBase
{
    Definition getMeaning(String input);
    void saveTerm(Definition definition);
}

package ayds.dictionary.bravo.Model.DataBase;

import ayds.dictionary.bravo.Model.Definition;
import ayds.dictionary.bravo.Model.Source;

public interface DictionaryDataBase
{
    Definition getMeaning(String input, Source source);
    void saveTerm(Definition definition);
}

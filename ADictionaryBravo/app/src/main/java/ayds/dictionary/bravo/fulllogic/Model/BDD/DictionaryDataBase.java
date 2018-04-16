package ayds.dictionary.bravo.fulllogic.Model.BDD;

public interface DictionaryDataBase {
    public String getMeaning(String term);
    public void saveTerm(String term, String meaning);
}

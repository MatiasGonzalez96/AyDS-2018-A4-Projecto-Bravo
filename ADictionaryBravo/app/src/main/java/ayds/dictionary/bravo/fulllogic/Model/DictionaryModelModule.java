package ayds.dictionary.bravo.fulllogic.Model;

public class DictionaryModelModule
{
    private static DictionaryModelModule instance;
    private DictionaryModel dictionaryModel;

    private DictionaryModelModule()
    {
        dictionaryModel =  new DictionaryModelImpl();
    }

    public static DictionaryModelModule getInstance() {
        if(instance == null) {
            instance =  new DictionaryModelModule();
        }
        return instance;
    }

    public DictionaryModel getDictionaryModel()
    {
        return dictionaryModel;
    }
}

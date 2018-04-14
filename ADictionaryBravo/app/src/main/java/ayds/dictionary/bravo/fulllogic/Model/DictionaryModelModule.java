package ayds.dictionary.bravo.fulllogic.Model;

import android.content.Context;

public class DictionaryModelModule
{
    private static DictionaryModelModule instance;
    private DictionaryModel dictionaryModel;

    private DictionaryModelModule(Context context)
    {
        dictionaryModel =  new DictionaryModelImpl(context);
    }

    public static DictionaryModelModule getInstance(Context context) {
        if(instance == null) {
            instance =  new DictionaryModelModule(context);
        }
        return instance;
    }

    public DictionaryModel getDictionaryModel()
    {
        return dictionaryModel;
    }
}

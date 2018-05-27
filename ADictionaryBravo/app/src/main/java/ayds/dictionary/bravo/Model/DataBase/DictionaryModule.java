package ayds.dictionary.bravo.Model.DataBase;

import android.content.Context;

public class DictionaryModule
{
    private static DictionaryModule instance;
    private DictionaryDataBase dictionaryDataBase;

    private DictionaryModule(Context context)
    {
        dictionaryDataBase = new DictionaryDataBaseImpl(context);
    }

    public static DictionaryModule getInstance(Context context)
    {
        if(instance == null)
        {
            instance =  new DictionaryModule(context);
        }
        return instance;
    }

    public DictionaryDataBase getDictionaryDataBaseImpl()
    {
        return dictionaryDataBase;
    }
}

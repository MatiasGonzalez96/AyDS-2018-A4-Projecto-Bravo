package ayds.dictionary.bravo.Model.DataBase;

import android.content.Context;

public class DictionaryModule
{
    private static DictionaryModule instance;
    private DictionaryDataBase dictionaryDataBaseImpl;

    private DictionaryModule(Context context)
    {
        dictionaryDataBaseImpl = new DictionaryDataBaseImpl(context);
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
        return dictionaryDataBaseImpl;
    }
}

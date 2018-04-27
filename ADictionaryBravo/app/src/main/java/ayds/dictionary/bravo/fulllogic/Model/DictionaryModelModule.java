package ayds.dictionary.bravo.fulllogic.Model;

import android.content.Context;
import android.util.Log;

import ayds.dictionary.bravo.fulllogic.Model.DataBase.Dictionary;
import ayds.dictionary.bravo.fulllogic.Model.DataBase.DictionaryDataBase;
import ayds.dictionary.bravo.fulllogic.Model.Services.Service;
import ayds.dictionary.bravo.fulllogic.Model.Services.ServiceImpl;

public class DictionaryModelModule
{
    private static DictionaryModelModule instance;
    private DictionaryModel dictionaryModel;

    private DictionaryModelModule(Context context)
    {
        Dictionary dataBase = new Dictionary(context);
        Service serviceImpl =new ServiceImpl();
        Repository repositoryImpl =new RepositoryImpl(serviceImpl,dataBase);
        dictionaryModel =  new DictionaryModelImpl(repositoryImpl);
    }

    public static DictionaryModelModule getInstance(Context context)
    {
        if(instance == null)
        {
            instance =  new DictionaryModelModule(context);
        }
        return instance;
    }

    public DictionaryModel getDictionaryModel()
    {
        return dictionaryModel;
    }

    public DictionaryErrorListener getDictionaryErrorListener()
    {
        Log.e("**","estoy en el modulo");
        return dictionaryModel.getDictionaryErrorListener();
    }
}

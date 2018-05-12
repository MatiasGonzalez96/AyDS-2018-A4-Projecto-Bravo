package ayds.dictionary.bravo.Model;

import android.content.Context;

import ayds.dictionary.bravo.Model.DataBase.DictionaryDataBase;
import Services.Service;
import Services.ServiceImpl;
import ayds.dictionary.bravo.Model.DataBase.DictionaryModule;
import ayds.dictionary.bravo.Model.Listener.DictionaryErrorListener;

public class DictionaryModelModule
{
    private static DictionaryModelModule instance;
    private DictionaryModel dictionaryModel;

    private DictionaryModelModule(Context context)
    {
        DictionaryDataBase dataBase = DictionaryModule.getInstance(context).getDictionaryDataBaseImpl();
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
        return dictionaryModel.getDictionaryErrorListener();
    }
}

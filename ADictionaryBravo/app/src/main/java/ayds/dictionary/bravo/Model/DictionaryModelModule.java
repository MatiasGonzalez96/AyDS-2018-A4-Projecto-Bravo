package ayds.dictionary.bravo.Model;

import android.content.Context;
import ayds.dictionary.bravo.Model.DataBase.DictionaryDataBase;
import ayds.dictionary.bravo.Model.DataBase.DictionaryModule;
import ayds.dictionary.bravo.Model.Exception.ErrorHandler;
import ayds.dictionary.bravo.Model.Exception.ExceptionModule;
import ayds.dictionary.bravo.Model.Listener.DictionaryErrorListener;

public class DictionaryModelModule
{
    private static DictionaryModelModule instance;
    private DictionaryModel dictionaryModel;

    private DictionaryModelModule(Context context)
    {
        DictionaryDataBase dataBase = DictionaryModule.getInstance(context).getDictionaryDataBaseImpl();
        ErrorHandler errorHandler = ExceptionModule.getInstance().getErrorHandler();
        Repository repositoryImpl = new RepositoryImpl(dataBase, errorHandler);
        dictionaryModel = new DictionaryModelImpl(repositoryImpl);
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

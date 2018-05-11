package ayds.dictionary.bravo.Model;

import android.content.Context;
import ayds.dictionary.bravo.Model.DataBase.Dictionary;
import Services.Service;
import Services.ServiceImpl;

public class DictionaryModelModule
{
    private static DictionaryModelModule instance;
    private DictionaryModel dictionaryModel;
    private ErrorHandler errorHandler;

    private DictionaryModelModule(Context context)
    {
        Dictionary dataBase = new Dictionary(context);
        Service serviceImpl =new ServiceImpl();
        Repository repositoryImpl =new RepositoryImpl(serviceImpl,dataBase);
        dictionaryModel =  new DictionaryModelImpl(repositoryImpl);
        errorHandler = new ErrorHandlerImpl();
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

    public ErrorHandler getErrorHandler()
    {
       return errorHandler;
    }
}

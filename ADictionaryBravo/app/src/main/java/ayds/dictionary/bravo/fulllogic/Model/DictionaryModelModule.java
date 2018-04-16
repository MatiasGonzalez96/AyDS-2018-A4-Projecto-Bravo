package ayds.dictionary.bravo.fulllogic.Model;

import android.content.Context;
import android.util.Log;

import ayds.dictionary.bravo.fulllogic.Model.BDD.Dictionary;
import ayds.dictionary.bravo.fulllogic.Model.BDD.DictionaryDataBase;
import ayds.dictionary.bravo.fulllogic.Model.Servicios.Servicio;
import ayds.dictionary.bravo.fulllogic.Model.Servicios.ServicioImpl;

public class DictionaryModelModule
{
    private static DictionaryModelModule instance;
    private DictionaryModel dictionaryModel;
    private DictionaryDataBase dataBase;

    private DictionaryModelModule(Context context)
    {

        dataBase=new Dictionary(context);
        init(context);
        Servicio servicioImpl=new ServicioImpl();
        Repositorio repositorioImpl=new RepositorioImpl(servicioImpl,dataBase);
        dictionaryModel =  new DictionaryModelImpl(repositorioImpl);
    }

    private void init(final Context context) {

        new Thread(new Runnable() {
            @Override public void run() {

                dataBase.saveTerm("test", "sarasa");

                Log.e("**", "" + dataBase.getMeaning("test"));
                Log.e("**", "" + dataBase.getMeaning("nada"));
            }
        }).start();
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

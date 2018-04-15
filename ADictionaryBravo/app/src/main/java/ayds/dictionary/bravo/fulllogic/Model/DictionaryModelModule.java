package ayds.dictionary.bravo.fulllogic.Model;

import android.content.Context;
import android.util.Log;

import ayds.dictionary.bravo.fulllogic.Model.BDD.Dictionary;
import ayds.dictionary.bravo.fulllogic.Model.Servicios.Servicio;
import ayds.dictionary.bravo.fulllogic.Model.Servicios.ServicioImpl;

public class DictionaryModelModule
{
    private static DictionaryModelModule instance;
    private DictionaryModel dictionaryModel;

    private DictionaryModelModule(Context context)
    {
        init(context);
        Servicio servicioImpl=new ServicioImpl();
        Repositorio repositorioImpl=new RepositorioImpl(servicioImpl);
        dictionaryModel =  new DictionaryModelImpl(repositorioImpl);
    }

    private void init(final Context context) {

        new Thread(new Runnable() {
            @Override public void run() {
                Dictionary.createNewDatabase(context);
                Dictionary.saveTerm("test", "sarasa");

                Log.e("**", "" + Dictionary.getMeaning("test"));
                Log.e("**", "" + Dictionary.getMeaning("nada"));
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

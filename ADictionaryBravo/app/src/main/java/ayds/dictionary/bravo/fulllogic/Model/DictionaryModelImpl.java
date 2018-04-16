package ayds.dictionary.bravo.fulllogic.Model;
import android.util.Log;



public class DictionaryModelImpl implements DictionaryModel
{
    private DictionaryModelListener listener;
    private Repositorio repositorioImpl;
    private String lastDef;

    DictionaryModelImpl(Repositorio repositorioImpl)
    {
        this.repositorioImpl=repositorioImpl;

    }

    @Override public void setListener(DictionaryModelListener listener) {
        this.listener = listener;
    }


    @Override public void buscarTermino(final String input) {

                buscarTerminoNow(input);
                notifyListener();
            }


    private void buscarTerminoNow(final String input)
    {
     lastDef=repositorioImpl.buscarTermino(input);
    }


    private void notifyListener() {
        if (listener != null) {
            Log.e("**", "ENTRE AL LISTENER");
            listener.didUpdateDictionary(lastDef);
        }
    }

}

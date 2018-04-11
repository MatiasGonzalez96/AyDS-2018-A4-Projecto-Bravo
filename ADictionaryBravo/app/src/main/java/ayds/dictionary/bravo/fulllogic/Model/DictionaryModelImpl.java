package ayds.dictionary.bravo.fulllogic.Model;

public class DictionaryModelImpl implements DictionaryModel
{
    private Dictionary dictionary;
    private DictionaryModelListener listener;

    DictionaryModelImpl()
    {
        //Crea la base de datos con init()
    }

    @Override public void setListener(DictionaryModelListener listener) {
        this.listener = listener;
    }

    @Override public Dictionary getDictionary() {
        return dictionary;
    }

    @Override public void buscarTermino(String input) {
        buscarTerminoNow(input);
        notifyListener();
    }

    private void buscarTerminoNow(String input)
    {
        //Logica del buscar
    }

    private void notifyListener() {
        if (listener != null) {
            listener.didUpdateDictionary();
        }
    }
}

package ayds.dictionary.bravo.fulllogic.Controller;

import android.content.Context;

import ayds.dictionary.bravo.fulllogic.Model.DictionaryModelModule;
import ayds.dictionary.bravo.fulllogic.View.DictionaryViewModule;

public class DictionaryControllerModule
{
    private static DictionaryControllerModule instance;
    private Context contexto;

    private DictionaryControllerModule() { }

    public static DictionaryControllerModule getInstance() {
        if (instance == null) {
            instance = new DictionaryControllerModule();
        }
        return instance;
    }

    public void startApplication(Context contexto) {
        this.contexto = contexto;
        EditDictionaryController controller = getEditDictionaryController();
        setEditDictionaryController(controller);

    }

    private EditDictionaryController getEditDictionaryController() {
        return new EditDictionaryControllerImpl(DictionaryModelModule.getInstance(contexto).getDictionaryModel());
    }

    private void setEditDictionaryController(EditDictionaryController editDictionaryController) {
        DictionaryViewModule.getInstance().setEditDictionaryController(editDictionaryController);
    }
}

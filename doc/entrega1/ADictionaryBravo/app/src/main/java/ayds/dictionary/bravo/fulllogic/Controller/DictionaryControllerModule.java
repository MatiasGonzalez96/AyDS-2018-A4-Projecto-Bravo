package ayds.dictionary.bravo.fulllogic.Controller;

import android.content.Context;
import ayds.dictionary.bravo.fulllogic.Model.DictionaryModelModule;
import ayds.dictionary.bravo.fulllogic.View.DictionaryViewModule;

public class DictionaryControllerModule
{
    private static DictionaryControllerModule instanceDictionaryControllerModule;
    private Context context;

    private DictionaryControllerModule()
    {
    }

    public static DictionaryControllerModule getInstance()
    {
        if (instanceDictionaryControllerModule == null)
        {
            instanceDictionaryControllerModule = new DictionaryControllerModule();
        }
        return instanceDictionaryControllerModule;
    }

    public void startApplication(Context contexto)
    {
        this.context = contexto;
        EditDictionaryController controller = getEditDictionaryController();
        setEditDictionaryController(controller);
    }

    private EditDictionaryController getEditDictionaryController()
    {
        return new EditDictionaryControllerImpl(DictionaryModelModule.getInstance(context).getDictionaryModel());
    }

    private void setEditDictionaryController(EditDictionaryController editDictionaryController)
    {
        DictionaryViewModule.getInstance().setEditDictionaryController(editDictionaryController);
    }
}

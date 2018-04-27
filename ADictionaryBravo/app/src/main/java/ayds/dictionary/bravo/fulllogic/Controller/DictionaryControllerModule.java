package ayds.dictionary.bravo.fulllogic.Controller;

import android.content.Context;
import ayds.dictionary.bravo.fulllogic.Model.DictionaryModelModule;
import ayds.dictionary.bravo.fulllogic.View.DictionaryViewModule;

public class DictionaryControllerModule
{
    private static DictionaryControllerModule instanceDictionaryControllerModule;
    private Context context;
    private EditDictionaryController controller;

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

    public void saveContextAndStartController(Context context)
    {
        this.context = context;
        controller = getEditDictionaryController();
    }

    private EditDictionaryController getEditDictionaryController()
    {
        return new EditDictionaryControllerImpl(DictionaryModelModule.getInstance(context).getDictionaryModel());
    }

    public EditDictionaryController getController() {
        return controller;
    }
}

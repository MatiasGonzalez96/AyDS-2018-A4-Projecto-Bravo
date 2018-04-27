package ayds.dictionary.bravo.fulllogic.View;

import ayds.dictionary.bravo.fulllogic.Controller.DictionaryControllerModule;
import ayds.dictionary.bravo.fulllogic.Controller.EditDictionaryController;

public class DictionaryViewModule {
    private static DictionaryViewModule instance;
    private EditDictionaryController editDictionaryController;

    private DictionaryViewModule() {
        editDictionaryController= DictionaryControllerModule.getInstance().getController();
    }

    public static DictionaryViewModule getInstance() {
        if (instance == null) {
            instance = new DictionaryViewModule();
        }
        return instance;
    }

    public EditDictionaryController getEditDictionaryController() {

        return editDictionaryController;
    }

    public TranslateHelper getTranslateHelper() {
        return new TranslateHelperImpl();
    }
}
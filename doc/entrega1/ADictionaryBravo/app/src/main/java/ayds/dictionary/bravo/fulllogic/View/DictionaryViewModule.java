package ayds.dictionary.bravo.fulllogic.View;

import ayds.dictionary.bravo.fulllogic.Controller.EditDictionaryController;

public class DictionaryViewModule
{
    private static DictionaryViewModule instance;
    private EditDictionaryController editDictionaryController;

    private DictionaryViewModule() 
    {
    }

    public static DictionaryViewModule getInstance()
    {
        if (instance == null)
        {
            instance = new DictionaryViewModule();
        }
        return instance;
    }

    public void setEditDictionaryController(EditDictionaryController editDictionaryController)
    {
        this.editDictionaryController = editDictionaryController;
    }

    public EditDictionaryController getEditDictionaryController()
    {
        return editDictionaryController;
    }
}
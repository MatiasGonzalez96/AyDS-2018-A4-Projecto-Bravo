package ayds.dictionary.bravo.fulllogic.Controller;

public class EditDictionaryControllerImpl
{
    private DictionaryModel dictionaryModel;
    private EditDictionaryView editUserView;

    EditDictionaryControllerImpl(DictionaryModel dictionaryModel)
    {
        this.dictionaryModel = dictionaryModel;
    }

    @Override public void buscarTermino(String input)
    {
        //Aca se llama al modelo
    }

    @Override public void setEditDictionaryView(EditDictionaryView editDictionaryView)
    {
        //this.editUserView = editUserView;
    }
}

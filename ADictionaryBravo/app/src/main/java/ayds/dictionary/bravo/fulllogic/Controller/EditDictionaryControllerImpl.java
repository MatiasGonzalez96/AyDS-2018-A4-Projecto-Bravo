package ayds.dictionary.bravo.fulllogic.Controller;

import ayds.dictionary.bravo.fulllogic.Model.DictionaryModel;

public class EditDictionaryControllerImpl implements EditDictionaryController
{
    private DictionaryModel dictionaryModel;

    EditDictionaryControllerImpl(DictionaryModel dictionaryModel)
    {
        this.dictionaryModel = dictionaryModel;
    }

    @Override
    public void askForTerm(String input)
    {
        dictionaryModel.searchTerm(input);
    }
}

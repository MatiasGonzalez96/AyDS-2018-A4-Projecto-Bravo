package ayds.dictionary.bravo.fulllogic.Controller;

import ayds.dictionary.bravo.fulllogic.Model.DictionaryModel;

class EditDictionaryControllerImpl implements EditDictionaryController
{
    private DictionaryModel dictionaryModel;

    EditDictionaryControllerImpl(DictionaryModel dictionaryModel)
    {
        this.dictionaryModel = dictionaryModel;
    }

    @Override
    public void searchTerm(String input)
    {
        dictionaryModel.searchTerm(input);
    }
}

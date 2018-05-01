package ayds.dictionary.bravo.Controller;

import ayds.dictionary.bravo.Model.DictionaryModel;

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

package ayds.dictionary.bravo.Model.Service;

import java.io.IOException;

import wikipedia.service.WikipediaService;

class WikipediaServiceAdapter implements ServiceDef
{
    private WikipediaService wikipediaService;

    WikipediaServiceAdapter(WikipediaService wikipediaService)
    {
        this.wikipediaService = wikipediaService;
    }

    @Override
    public String getMeaning(String input) throws IOException{
        String result = wikipediaService.getMeaning(input);
        return result;
    }
}

package ayds.dictionary.bravo.Model.Service;

import SearchService.*;

class BigHugeLabsServiceAdapter implements ServiceDef
{
    private SearchService bighugeService;

    BigHugeLabsServiceAdapter(SearchService bighugeService)
    {
        this.bighugeService = bighugeService;
    }

    @Override
    public String getMeaning(String input) throws ServiceNotReachableException
    {
        String result = bighugeService.searchTerm(input); ;
        return result;
    }
}

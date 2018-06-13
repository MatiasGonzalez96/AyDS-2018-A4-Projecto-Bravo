package ayds.dictionary.bravo.Model.Service;

import SearchService.*;

public class BigHugeLabsServiceAdapter implements ServiceDef
{
    private SearchService bighugeService;

    public BigHugeLabsServiceAdapter(SearchService bighugeService)
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

package ayds.dictionary.bravo.Model.Services;

import java.io.IOException;
import ayds.dictionary.bravo.Model.DictionaryModelModule;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceImpl implements Service
{
    private WikipediaAPI wikiAPI;
    private final String wikiAPIUrl="https://en.wikipedia.org/w/";
    private ExtractHelper extractHelper;

    public ServiceImpl()
    {
        connectAPI();
        extractHelper = ServiceModule.getInstance().getExtractHelper();
    }

    public void connectAPI()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(wikiAPIUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        wikiAPI = retrofit.create(WikipediaAPI.class);
    }

    public String getMeaning(String input)
    {
        try
        {
            Response<String> callResponse = wikiAPI.getTerm(input).execute();
            String extract = extractHelper.getExtract(callResponse.body());
            if(extract == null)
            {
                return null;
            }
            else
            {
                return extract.replace("\\n", "\n");
            }
        }
        catch (IOException e)
        {
            DictionaryModelModule.getInstance(null).getDictionaryErrorListener().didFindError();
        }
        return null;
    }
}

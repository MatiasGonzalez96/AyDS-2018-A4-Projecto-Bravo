package Services;

import java.io.IOException;

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

    public String getMeaning(String input) throws IOException
    {
        Response<String> callResponse = wikiAPI.getTerm(input).execute();
        if (isBadFormed(callResponse.body()))
        {
            return null;
        }
        else {
            String extract = extractHelper.getExtract(callResponse.body());
            if (extract == null) {
                return null;
            } else {
                return extract.replace("\\n", "\n");
            }
        }
    }

    private boolean isBadFormed(String input)
    {
        if (input != null && !input.isEmpty())
        {
            return false;
        }
        return true;
    }
}

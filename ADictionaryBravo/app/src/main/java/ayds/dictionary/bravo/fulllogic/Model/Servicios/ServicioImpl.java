package ayds.dictionary.bravo.fulllogic.Model.Servicios;



import java.io.IOException;

import ayds.dictionary.bravo.fulllogic.Model.Servicios.Servicio;
import ayds.dictionary.bravo.fulllogic.Model.WikipediaAPI;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ServicioImpl implements Servicio {

    private WikipediaAPI wikiAPI;

    public ServicioImpl()
    {
        conectar();
    }

    public void conectar()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        wikiAPI = retrofit.create(WikipediaAPI.class);

    }

    public  Response<String> getMeaning(String input) {
        try {
            return  wikiAPI.getTerm(input).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

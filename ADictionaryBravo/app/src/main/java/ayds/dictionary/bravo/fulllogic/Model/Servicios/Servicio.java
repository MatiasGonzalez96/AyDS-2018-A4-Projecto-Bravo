package ayds.dictionary.bravo.fulllogic.Model.Servicios;

import retrofit2.Response;

public interface Servicio {
    void conectar();
    String getMeaning(String input);
}

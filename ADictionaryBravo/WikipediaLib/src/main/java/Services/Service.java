package Services;

import java.io.IOException;

public interface Service
{
    void connectAPI();
    String getMeaning(String input) throws IOException;
}

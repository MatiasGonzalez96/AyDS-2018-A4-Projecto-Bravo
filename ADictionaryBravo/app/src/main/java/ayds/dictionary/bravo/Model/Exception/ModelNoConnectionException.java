package ayds.dictionary.bravo.Model.Exception;

public class ModelNoConnectionException extends Exception
{
    private static final String ERROR_MESSAGE = "Connection Error";

    public ModelNoConnectionException()
    {
        super(ERROR_MESSAGE);
    }
}

package ayds.dictionary.bravo.Model.Exception;

public class InvalidInputException extends Exception
{
    private static final String ERROR_MESSAGE = "Input Error";

    public InvalidInputException()
    {
        super(ERROR_MESSAGE);
    }
}


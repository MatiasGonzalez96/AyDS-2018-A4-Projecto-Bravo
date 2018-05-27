package ayds.dictionary.bravo.Model.Exception;

public class ExceptionModule
{
    private static ExceptionModule instance;
    private ErrorHandler errorHandler;

    private ExceptionModule()
    {
        errorHandler = new ErrorHandlerImpl();
    }

    public static ExceptionModule getInstance()
    {
        if(instance == null)
        {
            instance =  new ExceptionModule();
        }
        return instance;
    }

    public ErrorHandler getErrorHandler()
    {
        return errorHandler;
    }
}

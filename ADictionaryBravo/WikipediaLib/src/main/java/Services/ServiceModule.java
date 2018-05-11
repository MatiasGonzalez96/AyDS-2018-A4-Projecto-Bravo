package Services;

public class ServiceModule
{
    private static ServiceModule instance;

    private ServiceModule() {}

    public static ServiceModule getInstance()
    {
        if(instance == null)
        {
            instance =  new ServiceModule();
        }
        return instance;
    }

    public ExtractHelper getExtractHelper()
    {
        return new ExtractHelperImpl();
    }
}

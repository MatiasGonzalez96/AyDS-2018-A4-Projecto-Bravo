package Services;

public class ServiceModule
{
    private static ServiceModule instance;
    private ExtractHelper extractHelper;

    private ServiceModule()
    {
        extractHelper = new ExtractHelperImpl();
    }

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
        return extractHelper;
    }
}

package Services;

public class ServiceModule
{
    private static ServiceModule instance;
    private ExtractHelper extractHelper;
    private Service service;

    private ServiceModule()
    {
        extractHelper = new ExtractHelperImpl();
        service = new ServiceImpl(extractHelper);
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

    public Service getService()
    {
        return service;
    }
}

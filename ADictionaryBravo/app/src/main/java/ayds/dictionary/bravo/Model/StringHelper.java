package ayds.dictionary.bravo.Model;

public class StringHelper
{
    private static StringHelper instance;

    private StringHelper() {}

    public static StringHelper getInstance()
    {
        if(instance == null)
        {
            instance =  new StringHelper();
        }
        return instance;
    }

    public boolean onlyLetters(String input)
    {
        return input.matches("[a-zA-Z_0-9 ]+");
    }
}

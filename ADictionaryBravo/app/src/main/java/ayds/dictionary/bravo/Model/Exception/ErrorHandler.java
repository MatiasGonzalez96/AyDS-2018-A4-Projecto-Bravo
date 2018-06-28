package ayds.dictionary.bravo.Model.Exception;

import java.util.Map;
import ayds.dictionary.bravo.Model.Source;

public interface ErrorHandler
{
    void notifyError(String message);
    void notifyExceptions(Map<Source, Exception> exceptions);
}

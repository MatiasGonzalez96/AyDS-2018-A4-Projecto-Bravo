package ayds.dictionary.bravo.Model.Service;

import java.util.List;

import ayds.dictionary.bravo.Model.Source;

public interface ServicesDef
{
    String getMeaning(String input, Source source) throws Exception;
    List<Source> getSources();
}

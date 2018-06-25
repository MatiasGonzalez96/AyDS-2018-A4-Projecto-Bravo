package ayds.dictionary.bravo.Model.Listener;

import java.util.List;
import ayds.dictionary.bravo.Model.Definition;

public interface DictionaryModelListener
{
    void didUpdateDefinition(List<Definition> definition);
}

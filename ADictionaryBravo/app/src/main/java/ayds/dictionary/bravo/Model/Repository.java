package ayds.dictionary.bravo.Model;

import java.util.List;

public interface Repository
{
    List<Definition> getTerm(String input);
}

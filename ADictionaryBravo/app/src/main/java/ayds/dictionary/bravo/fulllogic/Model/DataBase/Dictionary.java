package ayds.dictionary.bravo.fulllogic.Model.DataBase;

import android.arch.persistence.room.Room;
import android.content.Context;
import ayds.dictionary.bravo.fulllogic.Model.DataBase.room.Concept;
import ayds.dictionary.bravo.fulllogic.Model.DataBase.room.ConceptDataBase;

public class Dictionary implements DictionaryDataBase{

  private  ConceptDataBase conceptDataBase;
  private final int WIKIPEDIA=1;
  public Dictionary(Context context)
  {
    conceptDataBase = Room.databaseBuilder(context,ConceptDataBase.class, "dictionary.db").build();
  }

  public void saveTerm(String term, String meaning)
  {
    Concept concept =  new Concept();
    concept.setTerm(term);
    concept.setMeaning(meaning);
    concept.setSource(WIKIPEDIA);
    conceptDataBase.termDao().insert(concept);
  }

  public String getMeaning(String term)
  {
    Concept concept = conceptDataBase.termDao().findByName(term);
    if (concept != null)
    {
      return concept.getMeaning();
    }
    return null;
  }
}

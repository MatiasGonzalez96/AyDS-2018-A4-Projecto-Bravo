package ayds.dictionary.bravo.fulllogic.Model.BDD;

import android.arch.persistence.room.Room;
import android.content.Context;


import ayds.dictionary.bravo.fulllogic.room.Concept;
import ayds.dictionary.bravo.fulllogic.room.ConceptDataBase;

public class Dictionary implements DictionaryDataBase{

  private  ConceptDataBase db;

  public Dictionary(Context context) {
    db = Room.databaseBuilder(context,
                              ConceptDataBase.class, "dictionary.db").build();
  }



  public void saveTerm(String term, String meaning) {
    Concept concept =  new Concept();
    concept.setTerm(term);
    concept.setMeaning(meaning);
    concept.setSource(1);
    db.termDao().insert(concept);
  }

  public String getMeaning(String term) {

    Concept concept = db.termDao().findByName(term);

    if (concept != null) {
      return concept.getMeaning();
    }
    return null;
  }
}

package ayds.dictionary.bravo.Model.DataBase;

import android.arch.persistence.room.Room;
import android.content.Context;
import ayds.dictionary.bravo.Model.DataBase.room.Concept;
import ayds.dictionary.bravo.Model.DataBase.room.ConceptDataBase;
import ayds.dictionary.bravo.Model.Definition;
import ayds.dictionary.bravo.Model.Source;

class DictionaryDataBaseImpl implements DictionaryDataBase{

  private  ConceptDataBase conceptDataBase;

  DictionaryDataBaseImpl(Context context)
  {
    conceptDataBase = Room.databaseBuilder(context,ConceptDataBase.class, "dictionary.db").build();
  }

  public void saveTerm(Definition definition)
  {
    Concept concept =  new Concept();
    concept.setTerm(definition.getTerm());
    concept.setMeaning(definition.getMeaning());
    concept.setSource(definition.getSource().ordinal());
    conceptDataBase.termDao().insert(concept);
  }

  public Definition getMeaning(String term)
  {
    Concept concept = conceptDataBase.termDao().findByName(term);
    if (concept != null)
    {
      Definition definition = new Definition();
      definition.setTerm(concept.getTerm());
      definition.setMeaning(concept.getMeaning());
      definition.setSource(Source.values()[concept.getSource()]);
      return definition;
    }
    return null;
  }
}

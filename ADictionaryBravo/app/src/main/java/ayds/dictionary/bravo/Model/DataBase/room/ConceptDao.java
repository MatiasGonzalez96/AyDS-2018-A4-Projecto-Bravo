package ayds.dictionary.bravo.Model.DataBase.room;

import android.arch.persistence.room.*;
import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ConceptDao
{
  @Query("SELECT * FROM Concept")
  List<Concept> getAll();

  @Query("SELECT * FROM Concept WHERE term LIKE :term AND source LIKE :source LIMIT 1")
  Concept findByNameAndSource(String term, int source);

  @Insert(onConflict = REPLACE)
  void insert(Concept concept);
}

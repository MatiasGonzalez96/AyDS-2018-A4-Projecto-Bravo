package ayds.dictionary.bravo.fulllogic.room;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class ConceptDao_Impl implements ConceptDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfConcept;

  public ConceptDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfConcept = new EntityInsertionAdapter<Concept>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Concept`(`id`,`term`,`meaning`,`source`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Concept value) {
        stmt.bindLong(1, value.getId());
        if (value.getTerm() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTerm());
        }
        if (value.getMeaning() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMeaning());
        }
        stmt.bindLong(4, value.getSource());
      }
    };
  }

  @Override
  public void insert(Concept concept) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfConcept.insert(concept);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Concept> getAll() {
    final String _sql = "SELECT * FROM Concept";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTerm = _cursor.getColumnIndexOrThrow("term");
      final int _cursorIndexOfMeaning = _cursor.getColumnIndexOrThrow("meaning");
      final int _cursorIndexOfSource = _cursor.getColumnIndexOrThrow("source");
      final List<Concept> _result = new ArrayList<Concept>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Concept _item;
        _item = new Concept();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpTerm;
        _tmpTerm = _cursor.getString(_cursorIndexOfTerm);
        _item.setTerm(_tmpTerm);
        final String _tmpMeaning;
        _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
        _item.setMeaning(_tmpMeaning);
        final int _tmpSource;
        _tmpSource = _cursor.getInt(_cursorIndexOfSource);
        _item.setSource(_tmpSource);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Concept findByName(String term) {
    final String _sql = "SELECT * FROM Concept WHERE term LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (term == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, term);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTerm = _cursor.getColumnIndexOrThrow("term");
      final int _cursorIndexOfMeaning = _cursor.getColumnIndexOrThrow("meaning");
      final int _cursorIndexOfSource = _cursor.getColumnIndexOrThrow("source");
      final Concept _result;
      if(_cursor.moveToFirst()) {
        _result = new Concept();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpTerm;
        _tmpTerm = _cursor.getString(_cursorIndexOfTerm);
        _result.setTerm(_tmpTerm);
        final String _tmpMeaning;
        _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
        _result.setMeaning(_tmpMeaning);
        final int _tmpSource;
        _tmpSource = _cursor.getInt(_cursorIndexOfSource);
        _result.setSource(_tmpSource);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}

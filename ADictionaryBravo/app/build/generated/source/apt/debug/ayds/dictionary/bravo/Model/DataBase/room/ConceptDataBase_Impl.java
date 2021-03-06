package ayds.dictionary.bravo.Model.DataBase.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class ConceptDataBase_Impl extends ConceptDataBase {
  private volatile ConceptDao _conceptDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Concept` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `term` TEXT, `meaning` TEXT, `source` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"1c6a19cd46fc393dc2a62a2bbc7c4145\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Concept`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsConcept = new HashMap<String, TableInfo.Column>(4);
        _columnsConcept.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsConcept.put("term", new TableInfo.Column("term", "TEXT", false, 0));
        _columnsConcept.put("meaning", new TableInfo.Column("meaning", "TEXT", false, 0));
        _columnsConcept.put("source", new TableInfo.Column("source", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysConcept = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesConcept = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoConcept = new TableInfo("Concept", _columnsConcept, _foreignKeysConcept, _indicesConcept);
        final TableInfo _existingConcept = TableInfo.read(_db, "Concept");
        if (! _infoConcept.equals(_existingConcept)) {
          throw new IllegalStateException("Migration didn't properly handle Concept(ayds.dictionary.bravo.Model.DataBase.room.Concept).\n"
                  + " Expected:\n" + _infoConcept + "\n"
                  + " Found:\n" + _existingConcept);
        }
      }
    }, "1c6a19cd46fc393dc2a62a2bbc7c4145");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Concept");
  }

  @Override
  public ConceptDao termDao() {
    if (_conceptDao != null) {
      return _conceptDao;
    } else {
      synchronized(this) {
        if(_conceptDao == null) {
          _conceptDao = new ConceptDao_Impl(this);
        }
        return _conceptDao;
      }
    }
  }
}

package app.technotech.koncpt.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import app.technotech.koncpt.data.local.entites.SuggestedMasterClassEntity;

@Dao
public interface SuggestedMasterClassDao {

    @Query("SELECT * FROM table_suggested_master_class")
    LiveData<SuggestedMasterClassEntity> getAllMasterClass();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMasterClassData(SuggestedMasterClassEntity suggestedMasterClassEntity);

    @Query("DELETE FROM table_suggested_master_class")
    void deleteAllMasterClass();
}

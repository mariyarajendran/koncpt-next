package app.technotech.koncpt.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import app.technotech.koncpt.data.local.entites.SuggestedCourseEntity;

@Dao
public interface SuggestedCourseDao {

    @Query("SELECT * FROM table_suggested_course")
    LiveData<SuggestedCourseEntity> getAllCourse();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourseData(SuggestedCourseEntity suggestedCourseEntity);

    @Query("DELETE FROM table_suggested_course")
    void deleteAllCourse();
}

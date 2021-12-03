package app.technotech.koncpt.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import app.technotech.koncpt.data.local.entites.DownloadVideoEntity;

@Dao
public interface VideoDownloadDao {

    @Query("SELECT * FROM  table_download_video_offline")
    LiveData<DownloadVideoEntity> getAllVideos();

    @Query("DELETE FROM table_download_video_offline WHERE video_id = :id")
    void deleteVideoById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVideoData(DownloadVideoEntity entity);

}

package app.technotech.koncpt.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import app.technotech.koncpt.data.local.dao.SuggestedCourseDao;
import app.technotech.koncpt.data.local.dao.VideoDownloadDao;
import app.technotech.koncpt.data.local.entites.DownloadVideoEntity;
import app.technotech.koncpt.data.local.entites.SuggestedCourseEntity;

import static app.technotech.koncpt.utils.AppConstants.DATABASE_NAME;

@Database(entities = {SuggestedCourseEntity.class, DownloadVideoEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract SuggestedCourseDao suggestedCourseDao();
    public abstract VideoDownloadDao videoDownloadDao();


    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME)
                        .build();
            }
        }
        return INSTANCE;
    }
}

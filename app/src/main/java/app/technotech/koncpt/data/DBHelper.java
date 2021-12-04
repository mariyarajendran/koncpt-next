package app.technotech.koncpt.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import app.technotech.koncpt.data.network.model.DownloadModel;


public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = DBHelper.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "multiTableDB";

    private static String  VIDEO_URL="video_url";
    private static final String VIDEO_ID="video_id";
    private static final String KEY_SR_NO = "srNo";
    private static final String SUBJECT_TITLE = "subject_title";
    private static final String DESCRIPTION = "description";

    // Table Names
    private static final String TABLE_VIDEOS = "videos";


    private static final String CREATE_TABLE_VIDEO = "CREATE TABLE "
            + TABLE_VIDEOS + "(" + VIDEO_ID + " VARCHAR(255) PRIMARY KEY," +  VIDEO_URL + " VARCHAR(255),  "  +  SUBJECT_TITLE + " VARCHAR(255)," + DESCRIPTION + " VARCHAR(255) " + ")";





    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_VIDEO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEOS);

    }

    /**
     * Creating a Video
     */

    public void createVideos(DownloadModel downloadModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VIDEO_ID, downloadModel.getVideo_id());
        values.put(VIDEO_URL, downloadModel.getVideo_url());
        values.put(SUBJECT_TITLE, downloadModel.getSubject_title());
        values.put(DESCRIPTION, downloadModel.getDescription());
           // insert row

        db.insertWithOnConflict(TABLE_VIDEOS, null, values, SQLiteDatabase.CONFLICT_REPLACE);


    }

    /****
     * Get All videos
     * @return
     */

    public ArrayList<DownloadModel> getAllVideos() {

        ArrayList<DownloadModel> homeModelArrayList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_VIDEOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (c.moveToFirst()) {

            do {
                DownloadModel downloadModel = new DownloadModel();
                downloadModel.setVideo_id(c.getString((c.getColumnIndex(VIDEO_ID))));
                downloadModel.setVideo_url(c.getString((c.getColumnIndex(VIDEO_URL))));
                downloadModel.setSubject_title(c.getString((c.getColumnIndex(SUBJECT_TITLE))));
                downloadModel.setDescription(c.getString((c.getColumnIndex(DESCRIPTION))));

                // adding to Home list

                homeModelArrayList.add(downloadModel);
            } while (c.moveToNext());

        }

        return homeModelArrayList;
    }


    /**
     * Delete video from database
     */

    /**
     * Deleting a room
     */

    public void deleteVideo(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // now delete the tag
        db.delete(TABLE_VIDEOS, VIDEO_ID + " = ?",
                new String[]{id});

    }

    public boolean deleteClass(String video_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_VIDEOS, VIDEO_ID + "=" + video_id, null) > 0;

    }

}

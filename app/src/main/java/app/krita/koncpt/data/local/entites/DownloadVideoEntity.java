package app.technotech.koncpt.data.local.entites;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_download_video_offline")
public class DownloadVideoEntity {

    @ColumnInfo(name = "video_id")
    @PrimaryKey(autoGenerate = false)
    int video_id;

    @ColumnInfo(name = "video_url")
    String video_url;

    @ColumnInfo(name = "subject_title")
    String subject_title;

    @ColumnInfo(name = "description")
    String description;


    public DownloadVideoEntity(int video_id, String video_url, String subject_title, String description) {
        this.video_id = video_id;
        this.video_url = video_url;
        this.subject_title = subject_title;
        this.description = description;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getSubject_title() {
        return subject_title;
    }

    public void setSubject_title(String subject_title) {
        this.subject_title = subject_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

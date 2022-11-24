package app.technotech.koncpt.data.local.entites;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_suggested_course")
public class SuggestedCourseEntity {

    @ColumnInfo(name = "course_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long mCourseId;

    @ColumnInfo(name = "course_title")
    private String mCourseTitle;

    @ColumnInfo(name = "course_image")
    private String mCourseImage;

    /*public SuggestedCourseEntity(@NonNull Long mCourseId, String mCourseTitle, String mCourseImage) {
        this.mCourseId = mCourseId;
        this.mCourseTitle = mCourseTitle;
        this.mCourseImage = mCourseImage;
    }*/

    public SuggestedCourseEntity(String mCourseTitle, String mCourseImage) {
        this.mCourseTitle = mCourseTitle;
        this.mCourseImage = mCourseImage;
    }

    @NonNull
    public Long getCourseId() {
        return mCourseId;
    }

    public void setCourseId(@NonNull Long mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getCourseTitle() {
        return mCourseTitle;
    }

    public void setCourseTitle(String mCourseTitle) {
        this.mCourseTitle = mCourseTitle;
    }

    public String getCourseImage() {
        return mCourseImage;
    }

    public void setCourseImage(String mCourseImage) {
        this.mCourseImage = mCourseImage;
    }

}

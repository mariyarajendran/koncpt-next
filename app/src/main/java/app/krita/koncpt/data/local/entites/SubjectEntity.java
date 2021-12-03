package app.technotech.koncpt.data.local.entites;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_subject_details")
public class SubjectEntity {

    @ColumnInfo(name = "subject_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long mSubjectId;

    @ColumnInfo(name = "subject_title")
    private String mSubjectTitle;

    @ColumnInfo(name = "subject_chapter_title")
    private String mChapterTitle;

    @ColumnInfo(name = "subject_chapter_ratting")
    private String mChapterRatting;

    @ColumnInfo(name = "subject_chapter_mcq_count")
    private String mMcqCount;

    @ColumnInfo(name = "Subject_chapter_thumb")
    private String mChapterThumb;

    @ColumnInfo(name = "subject_chapter_is_pro")
    private boolean mChapterIsPro;

    @Ignore
    public SubjectEntity(String mChapterTitle) {
        this.mChapterTitle = mChapterTitle;
    }

    public SubjectEntity(@NonNull Long mSubjectId, String mSubjectTitle, String mChapterTitle,
                         String mChapterRatting, String mMcqCount, String mChapterThumb, 
                         boolean mChapterIsPro) {
        this.mSubjectId = mSubjectId;
        this.mSubjectTitle = mSubjectTitle;
        this.mChapterTitle = mChapterTitle;
        this.mChapterRatting = mChapterRatting;
        this.mMcqCount = mMcqCount;
        this.mChapterThumb = mChapterThumb;
        this.mChapterIsPro = mChapterIsPro;
    }

    @NonNull
    public Long getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(@NonNull Long mSubjectId) {
        this.mSubjectId = mSubjectId;
    }

    public String getSubjectTitle() {
        return mSubjectTitle;
    }

    public void setSubjectTitle(String mSubjectTitle) {
        this.mSubjectTitle = mSubjectTitle;
    }

    public String getChapterTitle() {
        return mChapterTitle;
    }

    public void setChapterTitle(String mChapterTitle) {
        this.mChapterTitle = mChapterTitle;
    }

    public String getChapterRatting() {
        return mChapterRatting;
    }

    public void setChapterRatting(String mChapterRatting) {
        this.mChapterRatting = mChapterRatting;
    }

    public String getmMcqCount() {
        return mMcqCount;
    }

    public void setmMcqCount(String mMcqCount) {
        this.mMcqCount = mMcqCount;
    }

    public String getChapterThumb() {
        return mChapterThumb;
    }

    public void setChapterThumb(String mChapterThumb) {
        this.mChapterThumb = mChapterThumb;
    }

    public boolean ismChapterIsPro() {
        return mChapterIsPro;
    }

    public void setChapterIsPro(boolean mChapterIsPro) {
        this.mChapterIsPro = mChapterIsPro;
    }
}

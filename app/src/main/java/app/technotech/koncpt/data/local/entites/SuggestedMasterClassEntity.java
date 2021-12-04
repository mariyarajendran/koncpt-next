package app.technotech.koncpt.data.local.entites;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_suggested_master_class")
public class SuggestedMasterClassEntity {

    @ColumnInfo(name = "master_class_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long mMasterClassId;

    @ColumnInfo(name = "master_class_title")
    private String mMasterClassTitle;

    @ColumnInfo(name = "master_class_thumb_image")
    private String mMasterClassThumbImage;

    @ColumnInfo(name = "master_class_faculty")
    private String mMasterClassFaculty;

    public SuggestedMasterClassEntity(@NonNull Long mMasterClassId, String mMasterClassTitle,
                                      String mMasterClassThumbImage, String mMasterClassFaculty) {
        this.mMasterClassId = mMasterClassId;
        this.mMasterClassTitle = mMasterClassTitle;
        this.mMasterClassThumbImage = mMasterClassThumbImage;
        this.mMasterClassFaculty = mMasterClassFaculty;
    }

    public SuggestedMasterClassEntity(String mMasterClassTitle,
                                      String mMasterClassThumbImage, String mMasterClassFaculty) {
        this.mMasterClassTitle = mMasterClassTitle;
        this.mMasterClassThumbImage = mMasterClassThumbImage;
        this.mMasterClassFaculty = mMasterClassFaculty;
    }

    @NonNull
    public Long getMasterClassId() {
        return mMasterClassId;
    }

    public void setMasterClassId(@NonNull Long mMasterClassId) {
        this.mMasterClassId = mMasterClassId;
    }

    public String getMasterClassTitle() {
        return mMasterClassTitle;
    }

    public void setMasterClassTitle(String mMasterClassTitle) {
        this.mMasterClassTitle = mMasterClassTitle;
    }

    public String getMasterClassThumbImage() {
        return mMasterClassThumbImage;
    }

    public void setMasterClassThumbImage(String mMasterClassThumbImage) {
        this.mMasterClassThumbImage = mMasterClassThumbImage;
    }

    public String getMasterClassFaculty() {
        return mMasterClassFaculty;
    }

    public void setMasterClassFaculty(String mMasterClassFaculty) {
        this.mMasterClassFaculty = mMasterClassFaculty;
    }
}

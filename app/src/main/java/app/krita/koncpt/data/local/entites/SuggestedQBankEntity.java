package app.technotech.koncpt.data.local.entites;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_suggested_q_bank")
public class SuggestedQBankEntity {

    @ColumnInfo(name = "q_bank_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long mQBankId;

    @ColumnInfo(name = "q_bank_count")
    private String mQBankCount;

    @ColumnInfo(name = "q_bank_title")
    private String mQBankTitle;

    @ColumnInfo(name = "q_bank_thumb_image")
    private String mQBankThumbImage;


    public SuggestedQBankEntity(@NonNull Long mQBankId, String mQBankCount, String mQBankTitle, String mQBankThumbImage) {
        this.mQBankId = mQBankId;
        this.mQBankCount = mQBankCount;
        this.mQBankTitle = mQBankTitle;
        this.mQBankThumbImage = mQBankThumbImage;
    }

    public SuggestedQBankEntity(String mQBankCount, String mQBankTitle, String mQBankThumbImage) {
        this.mQBankCount = mQBankCount;
        this.mQBankTitle = mQBankTitle;
        this.mQBankThumbImage = mQBankThumbImage;
    }

    @NonNull
    public Long getQBankId() {
        return mQBankId;
    }

    public void setQBankId(@NonNull Long mQBankId) {
        this.mQBankId = mQBankId;
    }

    public String getQBankCount() {
        return mQBankCount;
    }

    public void setQBankCount(String mQBankCount) {
        this.mQBankCount = mQBankCount;
    }

    public String getQBankTitle() {
        return mQBankTitle;
    }

    public void setQBankTitle(String mQBankTitle) {
        this.mQBankTitle = mQBankTitle;
    }

    public String getQBankThumbImage() {
        return mQBankThumbImage;
    }

    public void setQBankThumbImage(String mQBankThumbImage) {
        this.mQBankThumbImage = mQBankThumbImage;
    }
}

package app.technotech.koncpt.data.local.entites;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_test")
public class TestEntity {

    @ColumnInfo(name = "test_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long mTestId;

    @ColumnInfo(name = "total_question_count")
    private String mTotalQCount;

    @ColumnInfo(name = "test_time")
    private String mTestTimeInMin;

    @ColumnInfo(name = "test_title")
    private String mTestTitle;

    @ColumnInfo(name = "test_expired_date")
    private String mTestExpiredDate;

    @ColumnInfo(name = "test_thumb")
    private String mTestThumb;

    @ColumnInfo(name = "test_is_pro")
    private boolean mTestIsPro;

    @Ignore
    public TestEntity(String mTestTitle, boolean mTestIsPro) {
        this.mTestTitle = mTestTitle;
        this.mTestIsPro = mTestIsPro;
    }

    public TestEntity(@NonNull Long mTestId, String mTotalQCount, String mTestTimeInMin, 
                      String mTestTitle, String mTestExpiredDate, String mTestThumb, boolean mTestIsPro) {
        
        this.mTestId = mTestId;
        this.mTotalQCount = mTotalQCount;
        this.mTestTimeInMin = mTestTimeInMin;
        this.mTestTitle = mTestTitle;
        this.mTestExpiredDate = mTestExpiredDate;
        this.mTestThumb = mTestThumb;
        this.mTestIsPro = mTestIsPro;
    }

    @NonNull
    public Long getTestId() {
        return mTestId;
    }

    public void setTestId(@NonNull Long mTestId) {
        this.mTestId = mTestId;
    }

    public String getTotalQCount() {
        return mTotalQCount;
    }

    public void setTotalQCount(String mTotalQCount) {
        this.mTotalQCount = mTotalQCount;
    }

    public String getTestTimeInMin() {
        return mTestTimeInMin;
    }

    public void setTestTimeInMin(String mTestTimeInMin) {
        this.mTestTimeInMin = mTestTimeInMin;
    }

    public String getTestTitle() {
        return mTestTitle;
    }

    public void setTestTitle(String mTestTitle) {
        this.mTestTitle = mTestTitle;
    }

    public String getTestExpiredDate() {
        return mTestExpiredDate;
    }

    public void setTestExpiredDate(String mTestExpiredDate) {
        this.mTestExpiredDate = mTestExpiredDate;
    }

    public String getTestThumb() {
        return mTestThumb;
    }

    public void setTestThumb(String mTestThumb) {
        this.mTestThumb = mTestThumb;
    }

    public boolean ismTestIsPro() {
        return mTestIsPro;
    }

    public void setTestIsPro(boolean mTestIsPro) {
        this.mTestIsPro = mTestIsPro;
    }
}

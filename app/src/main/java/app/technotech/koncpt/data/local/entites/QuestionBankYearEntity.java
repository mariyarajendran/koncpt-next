package app.technotech.koncpt.data.local.entites;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.List;

public class QuestionBankYearEntity {

    @ColumnInfo(name = "year_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long mYearId;

    @ColumnInfo(name = "year_title")
    private String mYearTitle;

    @ColumnInfo(name = "q_bank_list")
    private List<QuestionBankEntity> mQuestionBankEntities;

    public QuestionBankYearEntity(@NonNull Long mYearId, String mYearTitle, List<QuestionBankEntity> mQuestionBankEntities) {
        this.mYearId = mYearId;
        this.mYearTitle = mYearTitle;
        this.mQuestionBankEntities = mQuestionBankEntities;
    }

    @NonNull
    public Long getYearId() {
        return mYearId;
    }

    public void setYearId(@NonNull Long mYearId) {
        this.mYearId = mYearId;
    }

    public String getYearTitle() {
        return mYearTitle;
    }

    public void setYearTitle(String mYearTitle) {
        this.mYearTitle = mYearTitle;
    }

    public List<QuestionBankEntity> getQuestionBankEntities() {
        return mQuestionBankEntities;
    }

    public void setQuestionBankEntities(List<QuestionBankEntity> mQuestionBankEntities) {
        this.mQuestionBankEntities = mQuestionBankEntities;
    }
}

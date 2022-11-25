package app.technotech.koncpt.data.local.entites;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

public class QuestionBankEntity {

    @ColumnInfo(name = "q_bank_id")
    @PrimaryKey()
    @NonNull
    private String mQBankId;

    @ColumnInfo(name = "q_bank_year_id")
    @ForeignKey(entity = QuestionBankYearEntity.class,
            parentColumns = "year_id",
            childColumns = "q_bank_year_id",
            onDelete = CASCADE)
    @NonNull
    private String mQBankYearId;

    @ColumnInfo(name = "q_bank_image")
    private String mQuestionBankImage;

    @ColumnInfo(name = "q_bank_subject_name")
    private String mQuestionBankSubject;

    @ColumnInfo(name = "q_bank_total")
    private String mQuestionBankTotal;

    @ColumnInfo(name = "q_bank_complete")
    private String mQuestionBankComplete;


    public QuestionBankEntity(@NonNull String mQBankId, @NonNull String mQBankYearId, 
                              String mQuestionBankImage, String mQuestionBankSubject, 
                              String mQuestionBankTotal, String mQuestionBankComplete) {
        this.mQBankId = mQBankId;
        this.mQBankYearId = mQBankYearId;
        this.mQuestionBankImage = mQuestionBankImage;
        this.mQuestionBankSubject = mQuestionBankSubject;
        this.mQuestionBankTotal = mQuestionBankTotal;
        this.mQuestionBankComplete = mQuestionBankComplete;
    }

    @NonNull
    public String getQBankId() {
        return mQBankId;
    }

    public void setQBankId(@NonNull String mQBankId) {
        this.mQBankId = mQBankId;
    }

    @NonNull
    public String getQBankYearId() {
        return mQBankYearId;
    }

    public void setQBankYearId(@NonNull String mQBankYearId) {
        this.mQBankYearId = mQBankYearId;
    }

    public String getQuestionBankImage() {
        return mQuestionBankImage;
    }

    public void setQuestionBankImage(String mQuestionBankImage) {
        this.mQuestionBankImage = mQuestionBankImage;
    }

    public String getQuestionBankSubject() {
        return mQuestionBankSubject;
    }

    public void setQuestionBankSubject(String mQuestionBankSubject) {
        this.mQuestionBankSubject = mQuestionBankSubject;
    }

    public String getQuestionBankTotal() {
        return mQuestionBankTotal;
    }

    public void setQuestionBankTotal(String mQuestionBankTotal) {
        this.mQuestionBankTotal = mQuestionBankTotal;
    }

    public String getQuestionBankComplete() {
        return mQuestionBankComplete;
    }

    public void setQuestionBankComplete(String mQuestionBankComplete) {
        this.mQuestionBankComplete = mQuestionBankComplete;
    }
}

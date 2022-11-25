package app.technotech.koncpt.data.local.entites;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_question_details")
public class QuestionDetailsEntity implements Serializable {

    @ColumnInfo(name = "question_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long mQuestionId;

    @ColumnInfo(name = "question")
    private String mQuestion;

    @ColumnInfo(name = "question_option_a")
    private String mOptionA;

    @ColumnInfo(name = "question_option_b")
    private String mOptionB;

    @ColumnInfo(name = "question_option_c")
    private String mOptionC;

    @ColumnInfo(name = "question_option_d")
    private String mOptionD;


    @ColumnInfo(name = "question_correct_answer")
    private String mCorrectAnswer;

    @ColumnInfo(name = "itemData")
    private String mQuestionData;


    @ColumnInfo(name = "question_attempt")
    private boolean mQuestionAttempt;

    @ColumnInfo(name = "question_status")
    private int mQuestionState;

    @ColumnInfo(name = "question_marks")
    private int mQuestionMarks;

    @ColumnInfo(name = "bookmark_id")
    private int mBookmarkId;

    @ColumnInfo(name = "bookmark_status")
    private int mBookmarkStatus;

    @ColumnInfo(name = "explanation")
    private String mExplanation;

    @ColumnInfo(name = "explanation_file")
    private String mExplanation_file;

    @ColumnInfo(name = "myAnswer")
    private String myAnswer;

    @ColumnInfo(name = "refrence_from")
    private String refrence_from;

    @ColumnInfo(name = "refrence_file")
    private String refrence_file;

    @Ignore
    public QuestionDetailsEntity() {
    }

    @Ignore
    public QuestionDetailsEntity(String mQuestion, String mOptionA, String mOptionB, String mOptionC, String mOptionD) {
        this.mQuestion = mQuestion;
        this.mOptionA = mOptionA;
        this.mOptionB = mOptionB;
        this.mOptionC = mOptionC;
        this.mOptionD = mOptionD;
    }

    public QuestionDetailsEntity(@NonNull Long mQuestionId, String mQuestion,
                                 String mOptionA, String mOptionB, String mOptionC,
                                 String mOptionD, String mCorrectAnswer, String mQuestionData,
                                 boolean mQuestionAttempt, int mQuestionState, int mQuestionMarks, int mBookmarkId, int mBookmarkStatus, String mExplanation, String mExplanation_file, String myAnswer,
                                 String refrence_from, String refrence_file) {
        this.mQuestionId = mQuestionId;
        this.mQuestion = mQuestion;
        this.mOptionA = mOptionA;
        this.mOptionB = mOptionB;
        this.mOptionC = mOptionC;
        this.mOptionD = mOptionD;
        this.mCorrectAnswer = mCorrectAnswer;
        this.mQuestionData = mQuestionData;
        this.mQuestionMarks = mQuestionMarks;
        this.mBookmarkId = mBookmarkId;
        this.mBookmarkStatus = mBookmarkStatus;
        this.mExplanation = mExplanation;
        this.mExplanation_file = mExplanation_file;
        this.myAnswer = myAnswer;
        this.refrence_from = refrence_from;
        this.refrence_file = refrence_file;
    }

    @NonNull
    public Long getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(@NonNull Long mQuestionId) {
        this.mQuestionId = mQuestionId;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getOptionA() {
        return mOptionA;
    }

    public void setOptionA(String mOptionA) {
        this.mOptionA = mOptionA;
    }

    public String getOptionB() {
        return mOptionB;
    }

    public void setOptionB(String mOptionB) {
        this.mOptionB = mOptionB;
    }

    public String getOptionC() {
        return mOptionC;
    }

    public void setOptionC(String mOptionC) {
        this.mOptionC = mOptionC;
    }

    public String getOptionD() {
        return mOptionD;
    }

    public void setOptionD(String mOptionD) {
        this.mOptionD = mOptionD;
    }

    public String getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(String mCorrectAnswer) {
        this.mCorrectAnswer = mCorrectAnswer;
    }

    public String getmQuestionData() {
        return mQuestionData;
    }

    public void setmQuestionData(String mQuestionData) {
        this.mQuestionData = mQuestionData;
    }

    @NonNull
    public Long getmQuestionId() {
        return mQuestionId;
    }

    public void setmQuestionId(@NonNull Long mQuestionId) {
        this.mQuestionId = mQuestionId;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmOptionA() {
        return mOptionA;
    }

    public void setmOptionA(String mOptionA) {
        this.mOptionA = mOptionA;
    }

    public String getmOptionB() {
        return mOptionB;
    }

    public void setmOptionB(String mOptionB) {
        this.mOptionB = mOptionB;
    }

    public String getmOptionC() {
        return mOptionC;
    }

    public void setmOptionC(String mOptionC) {
        this.mOptionC = mOptionC;
    }

    public String getmOptionD() {
        return mOptionD;
    }

    public void setmOptionD(String mOptionD) {
        this.mOptionD = mOptionD;
    }

    public String getmCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setmCorrectAnswer(String mCorrectAnswer) {
        this.mCorrectAnswer = mCorrectAnswer;
    }

    public boolean ismQuestionAttempt() {
        return mQuestionAttempt;
    }

    public void setmQuestionAttempt(boolean mQuestionAttempt) {
        this.mQuestionAttempt = mQuestionAttempt;
    }

    public int getmQuestionState() {
        return mQuestionState;
    }

    public void setmQuestionState(int mQuestionState) {
        this.mQuestionState = mQuestionState;
    }

    public int getmQuestionMarks() {
        return mQuestionMarks;
    }

    public void setmQuestionMarks(int mQuestionMarks) {
        this.mQuestionMarks = mQuestionMarks;
    }

    public int getmBookmarkId() {
        return mBookmarkId;
    }

    public void setmBookmarkId(int mBookmarkId) {
        this.mBookmarkId = mBookmarkId;
    }

    public int getmBookmarkStatus() {
        return mBookmarkStatus;
    }

    public void setmBookmarkStatus(int mBookmarkStatus) {
        this.mBookmarkStatus = mBookmarkStatus;
    }


    public String getmExplanation() {
        return mExplanation;
    }

    public void setmExplanation(String mExplanation) {
        this.mExplanation = mExplanation;
    }


    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    public String getmExplanation_file() {
        return mExplanation_file;
    }

    public void setmExplanation_file(String mExplanation_file) {
        this.mExplanation_file = mExplanation_file;
    }

    public String getRefrence_from() {
        return refrence_from;
    }

    public void setRefrence_from(String refrence_from) {
        this.refrence_from = refrence_from;
    }

    public String getRefrence_file() {
        return refrence_file;
    }

    public void setRefrence_file(String refrence_file) {
        this.refrence_file = refrence_file;
    }
}

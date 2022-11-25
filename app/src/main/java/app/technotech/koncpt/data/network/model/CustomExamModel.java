package app.technotech.koncpt.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomExamModel implements Parcelable {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("total")
    @Expose
    private Integer total;

    private String extraData;

    public final static Parcelable.Creator<CustomExamModel> CREATOR = new Creator<CustomExamModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CustomExamModel createFromParcel(Parcel in) {
            return new CustomExamModel(in);
        }

        public CustomExamModel[] newArray(int size) {
            return (new CustomExamModel[size]);
        }

    };


    protected CustomExamModel(Parcel in) {
        in.readList(this.data, (Datum.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.extraData = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CustomExamModel() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(message);
        dest.writeValue(status);
        dest.writeValue(total);
        dest.writeValue(extraData);
    }

    public int describeContents() {
        return 0;
    }


    public class Datum implements Parcelable {

        @SerializedName("answers")
        @Expose
        private List<Answer> answers = null;
        @SerializedName("correct_answers")
        @Expose
        private String correctAnswers;
        @SerializedName("difficulty_level")
        @Expose
        private String difficultyLevel;
        @SerializedName("explanation")
        @Expose
        private String explanation;
        @SerializedName("explanation_file")
        @Expose
        private String explanationFile;
        @SerializedName("hint")
        @Expose
        private String hint;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("marks")
        @Expose
        private Integer marks;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("question_file")
        @Expose
        private String questionFile;
        @SerializedName("question_file_is_url")
        @Expose
        private Integer questionFileIsUrl;
        @SerializedName("question_tags")
        @Expose
        private String questionTags;
        @SerializedName("question_type")
        @Expose
        private String questionType;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("time_to_spend")
        @Expose
        private Integer timeToSpend;
        @SerializedName("topic_id")
        @Expose
        private Integer topicId;
        @SerializedName("total_answers")
        @Expose
        private Integer totalAnswers;
        @SerializedName("total_correct_answers")
        @Expose
        private Integer totalCorrectAnswers;

        @SerializedName("refrence_from")
        @Expose
        private String refrenceFrom;
        @SerializedName("refrence_file")
        @Expose
        private String refrenceFile;


        private String selectedAnswer;
        private int attemptMarks = 0;
        private boolean attemptQuestion = false;
        private int bookmarkStatus = 0;
        private String bookmarkId;

        // 0 - not selected, 1 - correct answer, 2 - wrong answer
        private int myAnswerStatus = 0;


        public final Parcelable.Creator<Datum> CREATOR = new Creator<Datum>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Datum createFromParcel(Parcel in) {
                return new Datum(in);
            }

            public Datum[] newArray(int size) {
                return (new Datum[size]);
            }

        };

        protected Datum(Parcel in) {
            in.readList(this.answers, (Answer.class.getClassLoader()));
            this.correctAnswers = ((String) in.readValue((String.class.getClassLoader())));
            this.difficultyLevel = ((String) in.readValue((String.class.getClassLoader())));
            this.explanation = ((String) in.readValue((String.class.getClassLoader())));
            this.explanationFile = ((String) in.readValue((String.class.getClassLoader())));
            this.hint = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.marks = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.question = ((String) in.readValue((String.class.getClassLoader())));
            this.questionFile = ((String) in.readValue((String.class.getClassLoader())));
            this.questionFileIsUrl = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.questionTags = ((String) in.readValue((String.class.getClassLoader())));
            this.questionType = ((String) in.readValue((String.class.getClassLoader())));
            this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.subjectId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.timeToSpend = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.topicId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalAnswers = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalCorrectAnswers = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.selectedAnswer = ((String) in.readValue((String.class.getClassLoader())));
            this.refrenceFrom = ((String) in.readValue((String.class.getClassLoader())));
            this.refrenceFile = ((String) in.readValue((String.class.getClassLoader())));
            this.attemptQuestion = ((boolean) in.readValue((String.class.getClassLoader())));
            this.attemptMarks = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.myAnswerStatus = ((Integer) in.readValue((String.class.getClassLoader())));
            this.bookmarkStatus = ((Integer) in.readValue((String.class.getClassLoader())));
            this.bookmarkId = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Datum() {
        }

        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }

        public String getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers(String correctAnswers) {
            this.correctAnswers = correctAnswers;
        }

        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        public void setDifficultyLevel(String difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
        }

        public String getExplanation() {
            return explanation;
        }

        public void setExplanation(String explanation) {
            this.explanation = explanation;
        }

        public String getExplanationFile() {
            return explanationFile;
        }

        public void setExplanationFile(String explanationFile) {
            this.explanationFile = explanationFile;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getMarks() {
            return marks;
        }

        public void setMarks(Integer marks) {
            this.marks = marks;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getQuestionFile() {
            return questionFile;
        }

        public void setQuestionFile(String questionFile) {
            this.questionFile = questionFile;
        }

        public Integer getQuestionFileIsUrl() {
            return questionFileIsUrl;
        }

        public void setQuestionFileIsUrl(Integer questionFileIsUrl) {
            this.questionFileIsUrl = questionFileIsUrl;
        }

        public String getQuestionTags() {
            return questionTags;
        }

        public void setQuestionTags(String questionTags) {
            this.questionTags = questionTags;
        }

        public String getQuestionType() {
            return questionType;
        }

        public void setQuestionType(String questionType) {
            this.questionType = questionType;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public Integer getTimeToSpend() {
            return timeToSpend;
        }

        public void setTimeToSpend(Integer timeToSpend) {
            this.timeToSpend = timeToSpend;
        }

        public Integer getTopicId() {
            return topicId;
        }

        public void setTopicId(Integer topicId) {
            this.topicId = topicId;
        }

        public Integer getTotalAnswers() {
            return totalAnswers;
        }

        public void setTotalAnswers(Integer totalAnswers) {
            this.totalAnswers = totalAnswers;
        }

        public Integer getTotalCorrectAnswers() {
            return totalCorrectAnswers;
        }

        public void setTotalCorrectAnswers(Integer totalCorrectAnswers) {
            this.totalCorrectAnswers = totalCorrectAnswers;
        }

        public String getSelectedAnswer() {
            return selectedAnswer;
        }

        public void setSelectedAnswer(String selectedAnswer) {
            this.selectedAnswer = selectedAnswer;
        }

        public boolean isAttemptQuestion() {
            return attemptQuestion;
        }

        public void setAttemptQuestion(boolean attemptQuestion) {
            this.attemptQuestion = attemptQuestion;
        }

        public int getAttemptMarks() {
            return attemptMarks;
        }

        public void setAttemptMarks(int attemptMarks) {
            this.attemptMarks = attemptMarks;
        }

        public int getMyAnswerStatus() {
            return myAnswerStatus;
        }

        public void setMyAnswerStatus(int myAnswerStatus) {
            this.myAnswerStatus = myAnswerStatus;
        }


        public int getBookmarkStatus() {
            return bookmarkStatus;
        }

        public void setBookmarkStatus(int bookmarkStatus) {
            this.bookmarkStatus = bookmarkStatus;
        }

        public String getBookmarkId() {
            return bookmarkId;
        }

        public void setBookmarkId(String bookmarkId) {
            this.bookmarkId = bookmarkId;
        }


        public String getRefrenceFrom() {
            return refrenceFrom;
        }

        public void setRefrenceFrom(String refrenceFrom) {
            this.refrenceFrom = refrenceFrom;
        }

        public String getRefrenceFile() {
            return refrenceFile;
        }

        public void setRefrenceFile(String refrenceFile) {
            this.refrenceFile = refrenceFile;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(answers);
            dest.writeValue(correctAnswers);
            dest.writeValue(difficultyLevel);
            dest.writeValue(explanation);
            dest.writeValue(explanationFile);
            dest.writeValue(hint);
            dest.writeValue(id);
            dest.writeValue(marks);
            dest.writeValue(question);
            dest.writeValue(questionFile);
            dest.writeValue(questionFileIsUrl);
            dest.writeValue(questionTags);
            dest.writeValue(questionType);
            dest.writeValue(status);
            dest.writeValue(subjectId);
            dest.writeValue(timeToSpend);
            dest.writeValue(topicId);
            dest.writeValue(totalAnswers);
            dest.writeValue(totalCorrectAnswers);
            dest.writeValue(selectedAnswer);
            dest.writeValue(refrenceFrom);
            dest.writeValue(refrenceFile);
            dest.writeValue(attemptQuestion);
            dest.writeValue(attemptMarks);
            dest.writeValue(myAnswerStatus);
            dest.writeValue(bookmarkStatus);
            dest.writeValue(bookmarkId);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class Answer implements Parcelable {

        @SerializedName("file_name")
        @Expose
        private String fileName;
        @SerializedName("has_file")
        @Expose
        private Integer hasFile;
        @SerializedName("option_value")
        @Expose
        private String optionValue;
        @SerializedName("optionl2_value")
        @Expose
        private String optionl2Value;
        public final Parcelable.Creator<Answer> CREATOR = new Creator<Answer>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Answer createFromParcel(Parcel in) {
                return new Answer(in);
            }

            public Answer[] newArray(int size) {
                return (new Answer[size]);
            }

        };

        protected Answer(Parcel in) {
            this.fileName = ((String) in.readValue((String.class.getClassLoader())));
            this.hasFile = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.optionValue = ((String) in.readValue((String.class.getClassLoader())));
            this.optionl2Value = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Answer() {
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public Integer getHasFile() {
            return hasFile;
        }

        public void setHasFile(Integer hasFile) {
            this.hasFile = hasFile;
        }

        public String getOptionValue() {
            return optionValue;
        }

        public void setOptionValue(String optionValue) {
            this.optionValue = optionValue;
        }

        public String getOptionl2Value() {
            return optionl2Value;
        }

        public void setOptionl2Value(String optionl2Value) {
            this.optionl2Value = optionl2Value;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(fileName);
            dest.writeValue(hasFile);
            dest.writeValue(optionValue);
            dest.writeValue(optionl2Value);
        }

        public int describeContents() {
            return 0;
        }

    }


}

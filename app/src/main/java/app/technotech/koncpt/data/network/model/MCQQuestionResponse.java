package app.technotech.koncpt.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MCQQuestionResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("total")
    @Expose
    private Integer total;
    public final static Parcelable.Creator<MCQQuestionResponse> CREATOR = new Creator<MCQQuestionResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MCQQuestionResponse createFromParcel(Parcel in) {
            return new MCQQuestionResponse(in);
        }

        public MCQQuestionResponse[] newArray(int size) {
            return (new MCQQuestionResponse[size]);
        }

    }
            ;

    protected MCQQuestionResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
        this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MCQQuestionResponse() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeList(data);
        dest.writeValue(total);
    }

    public int describeContents() {
        return 0;
    }


    public class Datum implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("subject_id")
        @Expose
        private String subjectId;
        @SerializedName("topic_id")
        @Expose
        private String topicId;
        @SerializedName("question_tags")
        @Expose
        private String questionTags;
        @SerializedName("question_type")
        @Expose
        private String questionType;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("answers")
        @Expose
        private List<Answer> answers = null;
        @SerializedName("question_file")
        @Expose
        private String questionFile;
        @SerializedName("question_file_is_url")
        @Expose
        private String questionFileIsUrl;
        @SerializedName("total_answers")
        @Expose
        private String totalAnswers;
        @SerializedName("total_correct_answers")
        @Expose
        private String totalCorrectAnswers;
        @SerializedName("correct_answers")
        @Expose
        private String correctAnswers;
        @SerializedName("marks")
        @Expose
        private String marks;
        @SerializedName("time_to_spend")
        @Expose
        private String timeToSpend;
        @SerializedName("difficulty_level")
        @Expose
        private String difficultyLevel;
        @SerializedName("hint")
        @Expose
        private String hint;
        @SerializedName("explanation")
        @Expose
        private String explanation;
        @SerializedName("explanation_file")
        @Expose
        private String explanationFile;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("refrence_from")
        @Expose
        private String refrenceFrom;
        @SerializedName("refrence_file")
        @Expose
        private String refrenceFile;

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

        public final  Parcelable.Creator<Datum> CREATOR = new Creator<Datum>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Datum createFromParcel(Parcel in) {
                return new Datum(in);
            }

            public Datum[] newArray(int size) {
                return (new Datum[size]);
            }

        }
                ;

        protected Datum(Parcel in) {
            this.id = ((String) in.readValue((String.class.getClassLoader())));
            this.subjectId = ((String) in.readValue((String.class.getClassLoader())));
            this.topicId = ((String) in.readValue((String.class.getClassLoader())));
            this.questionTags = ((String) in.readValue((String.class.getClassLoader())));
            this.questionType = ((String) in.readValue((String.class.getClassLoader())));
            this.question = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.answers, (Answer.class.getClassLoader()));
            this.questionFile = ((String) in.readValue((String.class.getClassLoader())));
            this.questionFileIsUrl = ((String) in.readValue((String.class.getClassLoader())));
            this.totalAnswers = ((String) in.readValue((String.class.getClassLoader())));
            this.totalCorrectAnswers = ((String) in.readValue((String.class.getClassLoader())));
            this.correctAnswers = ((String) in.readValue((String.class.getClassLoader())));
            this.marks = ((String) in.readValue((String.class.getClassLoader())));
            this.timeToSpend = ((String) in.readValue((String.class.getClassLoader())));
            this.difficultyLevel = ((String) in.readValue((String.class.getClassLoader())));
            this.hint = ((String) in.readValue((String.class.getClassLoader())));
            this.explanation = ((String) in.readValue((String.class.getClassLoader())));
            this.explanationFile = ((String) in.readValue((String.class.getClassLoader())));
            this.status = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Datum() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
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

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }

        public String getQuestionFile() {
            return questionFile;
        }

        public void setQuestionFile(String questionFile) {
            this.questionFile = questionFile;
        }

        public String getQuestionFileIsUrl() {
            return questionFileIsUrl;
        }

        public void setQuestionFileIsUrl(String questionFileIsUrl) {
            this.questionFileIsUrl = questionFileIsUrl;
        }

        public String getTotalAnswers() {
            return totalAnswers;
        }

        public void setTotalAnswers(String totalAnswers) {
            this.totalAnswers = totalAnswers;
        }

        public String getTotalCorrectAnswers() {
            return totalCorrectAnswers;
        }

        public void setTotalCorrectAnswers(String totalCorrectAnswers) {
            this.totalCorrectAnswers = totalCorrectAnswers;
        }

        public String getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers(String correctAnswers) {
            this.correctAnswers = correctAnswers;
        }

        public String getMarks() {
            return marks;
        }

        public void setMarks(String marks) {
            this.marks = marks;
        }

        public String getTimeToSpend() {
            return timeToSpend;
        }

        public void setTimeToSpend(String timeToSpend) {
            this.timeToSpend = timeToSpend;
        }

        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        public void setDifficultyLevel(String difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(subjectId);
            dest.writeValue(topicId);
            dest.writeValue(questionTags);
            dest.writeValue(questionType);
            dest.writeValue(question);
            dest.writeList(answers);
            dest.writeValue(questionFile);
            dest.writeValue(questionFileIsUrl);
            dest.writeValue(totalAnswers);
            dest.writeValue(totalCorrectAnswers);
            dest.writeValue(correctAnswers);
            dest.writeValue(marks);
            dest.writeValue(timeToSpend);
            dest.writeValue(difficultyLevel);
            dest.writeValue(hint);
            dest.writeValue(explanation);
            dest.writeValue(explanationFile);
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class Answer implements Parcelable
    {

        @SerializedName("option_value")
        @Expose
        private String optionValue;
        @SerializedName("optionl2_value")
        @Expose
        private String optionl2Value;
        @SerializedName("has_file")
        @Expose
        private Integer hasFile;
        @SerializedName("file_name")
        @Expose
        private String fileName;
        public final  Parcelable.Creator<Answer> CREATOR = new Creator<Answer>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Answer createFromParcel(Parcel in) {
                return new Answer(in);
            }

            public Answer[] newArray(int size) {
                return (new Answer[size]);
            }

        }
                ;

        protected Answer(Parcel in) {
            this.optionValue = ((String) in.readValue((String.class.getClassLoader())));
            this.optionl2Value = ((String) in.readValue((String.class.getClassLoader())));
            this.hasFile = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.fileName = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Answer() {
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

        public Integer getHasFile() {
            return hasFile;
        }

        public void setHasFile(Integer hasFile) {
            this.hasFile = hasFile;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(optionValue);
            dest.writeValue(optionl2Value);
            dest.writeValue(hasFile);
            dest.writeValue(fileName);
        }

        public int describeContents() {
            return 0;
        }

    }

}

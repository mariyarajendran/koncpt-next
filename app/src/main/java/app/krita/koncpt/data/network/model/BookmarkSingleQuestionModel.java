package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookmarkSingleQuestionModel {


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


    public class Answer {

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

    }


    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("topic_id")
        @Expose
        private Integer topicId;
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
        private Integer questionFileIsUrl;
        @SerializedName("total_answers")
        @Expose
        private Integer totalAnswers;
        @SerializedName("total_correct_answers")
        @Expose
        private Integer totalCorrectAnswers;
        @SerializedName("correct_answers")
        @Expose
        private String correctAnswers;
        @SerializedName("marks")
        @Expose
        private Integer marks;
        @SerializedName("time_to_spend")
        @Expose
        private Integer timeToSpend;
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
        private Integer status;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public Integer getTopicId() {
            return topicId;
        }

        public void setTopicId(Integer topicId) {
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

        public Integer getQuestionFileIsUrl() {
            return questionFileIsUrl;
        }

        public void setQuestionFileIsUrl(Integer questionFileIsUrl) {
            this.questionFileIsUrl = questionFileIsUrl;
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

        public String getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers(String correctAnswers) {
            this.correctAnswers = correctAnswers;
        }

        public Integer getMarks() {
            return marks;
        }

        public void setMarks(Integer marks) {
            this.marks = marks;
        }

        public Integer getTimeToSpend() {
            return timeToSpend;
        }

        public void setTimeToSpend(Integer timeToSpend) {
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

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

    }
}

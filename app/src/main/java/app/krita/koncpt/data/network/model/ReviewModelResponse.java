package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewModelResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("click_answer")
    @Expose
    private List<ClickAnswer> clickAnswer = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public List<ClickAnswer> getClickAnswer() {
        return clickAnswer;
    }

    public void setClickAnswer(List<ClickAnswer> clickAnswer) {
        this.clickAnswer = clickAnswer;
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
        private int hasFile;
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

        public int getHasFile() {
            return hasFile;
        }

        public void setHasFile(int hasFile) {
            this.hasFile = hasFile;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

    }

    public class ClickAnswer {

        @SerializedName("answers")
        @Expose
        private String answers;

        public String getAnswers() {
            return answers;
        }

        public void setAnswers(String answers) {
            this.answers = answers;
        }

    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("answers")
        @Expose
        private List<Answer> answers = null;
        @SerializedName("correct_answers")
        @Expose
        private String correctAnswers;
        @SerializedName("explanation")
        @Expose
        private String explanation;

        @SerializedName("explanation_file")
        @Expose
        private String explanationFile;

        @SerializedName("question_file")
        @Expose
        private String questionFile;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers(String correctAnswers) {
            this.correctAnswers = correctAnswers;
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

        public String getQuestionFile() {
            return questionFile;
        }

        public void setQuestionFile(String questionFile) {
            this.questionFile = questionFile;
        }
    }

}

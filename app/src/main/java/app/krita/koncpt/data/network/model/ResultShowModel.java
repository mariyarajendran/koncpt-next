package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultShowModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;

    }


    public class Data {

        @SerializedName("percentage")
        @Expose
        private String percentage;
        @SerializedName("qcorrect_answer_questions")
        @Expose
        private Integer qcorrectAnswerQuestions;
        @SerializedName("not_answered_questions")
        @Expose
        private Integer notAnsweredQuestions;
        @SerializedName("wrong_answer_questions")
        @Expose
        private Integer wrongAnswerQuestions;

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public Integer getQcorrectAnswerQuestions() {
            return qcorrectAnswerQuestions;
        }

        public void setQcorrectAnswerQuestions(Integer qcorrectAnswerQuestions) {
            this.qcorrectAnswerQuestions = qcorrectAnswerQuestions;
        }

        public Integer getNotAnsweredQuestions() {
            return notAnsweredQuestions;
        }

        public void setNotAnsweredQuestions(Integer notAnsweredQuestions) {
            this.notAnsweredQuestions = notAnsweredQuestions;
        }

        public Integer getWrongAnswerQuestions() {
            return wrongAnswerQuestions;
        }

        public void setWrongAnswerQuestions(Integer wrongAnswerQuestions) {
            this.wrongAnswerQuestions = wrongAnswerQuestions;
        }

    }

}

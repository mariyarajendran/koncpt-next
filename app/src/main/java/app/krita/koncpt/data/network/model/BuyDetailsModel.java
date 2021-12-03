package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BuyDetailsModel {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("package_id")
        @Expose
        private Integer packageId;
        @SerializedName("label")
        @Expose
        private String label;
        @SerializedName("number_of_question")
        @Expose
        private Integer numberOfQuestion;
        @SerializedName("number_of_subject_or_test")
        @Expose
        private Integer numberOfSubjectOrTest;
        @SerializedName("subject_or_mocktest")
        @Expose
        private String subjectOrMocktest;
        @SerializedName("create_at")
        @Expose
        private String createAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPackageId() {
            return packageId;
        }

        public void setPackageId(Integer packageId) {
            this.packageId = packageId;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Integer getNumberOfQuestion() {
            return numberOfQuestion;
        }

        public void setNumberOfQuestion(Integer numberOfQuestion) {
            this.numberOfQuestion = numberOfQuestion;
        }

        public Integer getNumberOfSubjectOrTest() {
            return numberOfSubjectOrTest;
        }

        public void setNumberOfSubjectOrTest(Integer numberOfSubjectOrTest) {
            this.numberOfSubjectOrTest = numberOfSubjectOrTest;
        }

        public String getSubjectOrMocktest() {
            return subjectOrMocktest;
        }

        public void setSubjectOrMocktest(String subjectOrMocktest) {
            this.subjectOrMocktest = subjectOrMocktest;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }
    }
}

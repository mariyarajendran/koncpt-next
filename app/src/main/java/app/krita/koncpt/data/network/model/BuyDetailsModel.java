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
        @SerializedName("validity")
        @Expose
        private String validity;
        @SerializedName("validity_type")
        @Expose
        private String validity_type;
        @SerializedName("subscription_start_date")
        @Expose
        private String subscription_start_date;
        @SerializedName("level_id")
        @Expose
        private String level_id;
        @SerializedName("plan_id")
        @Expose
        private String plan_id;
        @SerializedName("level_active")
        @Expose
        private Integer level_active;
        @SerializedName("amount")
        @Expose
        private String amount;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public String getValidity_type() {
            return validity_type;
        }

        public void setValidity_type(String validity_type) {
            this.validity_type = validity_type;
        }

        public String getSubscription_start_date() {
            return subscription_start_date;
        }

        public void setSubscription_start_date(String subscription_start_date) {
            this.subscription_start_date = subscription_start_date;
        }

        public String getLevel_id() {
            return level_id;
        }

        public void setLevel_id(String level_id) {
            this.level_id = level_id;
        }

        public String getPlan_id() {
            return plan_id;
        }

        public void setPlan_id(String plan_id) {
            this.plan_id = plan_id;
        }

        public Integer getLevel_active() {
            return level_active;
        }

        public void setLevel_active(Integer level_active) {
            this.level_active = level_active;
        }


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

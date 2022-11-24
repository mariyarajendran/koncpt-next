package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GrandTestModelResponse {


    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

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

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("is_given")
        @Expose
        private String isGiven;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("dueration")
        @Expose
        private String dueration;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("is_paid")
        @Expose
        private String isPaid;
        @SerializedName("cost")
        @Expose
        private String cost;
        @SerializedName("validity")
        @Expose
        private String validity;
        @SerializedName("total_marks")
        @Expose
        private String totalMarks;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("total_questions")
        @Expose
        private String totalQuestions;
        @SerializedName("start_date")
        @Expose
        private String startDate;
        @SerializedName("end_date")
        @Expose
        private String endDate;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("test_type")
        @Expose
        private String testType;
        @SerializedName("sub_image")
        @Expose
        private String subImage;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsGiven() {
            return isGiven;
        }

        public void setIsGiven(String isGiven) {
            this.isGiven = isGiven;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDueration() {
            return dueration;
        }

        public void setDueration(String dueration) {
            this.dueration = dueration;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getIsPaid() {
            return isPaid;
        }

        public void setIsPaid(String isPaid) {
            this.isPaid = isPaid;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public String getTotalMarks() {
            return totalMarks;
        }

        public void setTotalMarks(String totalMarks) {
            this.totalMarks = totalMarks;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTotalQuestions() {
            return totalQuestions;
        }

        public void setTotalQuestions(String totalQuestions) {
            this.totalQuestions = totalQuestions;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getTestType() {
            return testType;
        }

        public void setTestType(String testType) {
            this.testType = testType;
        }

        public String getSubImage() {
            return subImage;
        }

        public void setSubImage(String subImage) {
            this.subImage = subImage;
        }

    }
}

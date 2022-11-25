package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Example> data = null;

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

    public List<Example> getData() {
        return data;
    }

    public void setData(List<Example> data) {
        this.data = data;
    }

    public class Example{
        @SerializedName("plan")
        @Expose
        private String plan;
        @SerializedName("subscription_ends_at")
        @Expose
        private String subscriptionEndsAt;
        @SerializedName("fb_google_id")
        @Expose
        private String fbGoogleId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("curr_acad_year")
        @Expose
        private String currAcadYear;
        @SerializedName("state_id")
        @Expose
        private String stateId;
        @SerializedName("login_enabled")
        @Expose
        private Integer loginEnabled;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("statename")
        @Expose
        private String statename;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("college_name")
        @Expose
        private String collegeName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("course")
        @Expose
        private String course;
        @SerializedName("image")
        @Expose
        private String image;

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }

        public String getSubscriptionEndsAt() {
            return subscriptionEndsAt;
        }

        public void setSubscriptionEndsAt(String subscriptionEndsAt) {
            this.subscriptionEndsAt = subscriptionEndsAt;
        }

        public String getFbGoogleId() {
            return fbGoogleId;
        }

        public void setFbGoogleId(String fbGoogleId) {
            this.fbGoogleId = fbGoogleId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCurrAcadYear() {
            return currAcadYear;
        }

        public void setCurrAcadYear(String currAcadYear) {
            this.currAcadYear = currAcadYear;
        }

        public String getStateId() {
            return stateId;
        }

        public void setStateId(String stateId) {
            this.stateId = stateId;
        }

        public Integer getLoginEnabled() {
            return loginEnabled;
        }

        public void setLoginEnabled(Integer loginEnabled) {
            this.loginEnabled = loginEnabled;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatename() {
            return statename;
        }

        public void setStatename(String statename) {
            this.statename = statename;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getCollegeName() {
            return collegeName;
        }

        public void setCollegeName(String collegeName) {
            this.collegeName = collegeName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }



}

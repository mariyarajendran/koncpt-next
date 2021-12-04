package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchModel {


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

    public class Subject {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("course_id")
        @Expose
        private Integer courseId;
        @SerializedName("subject_title")
        @Expose
        private String subjectTitle;
        @SerializedName("subject_code")
        @Expose
        private String subjectCode;
        @SerializedName("subj_of_year")
        @Expose
        private String subjOfYear;
        @SerializedName("subject_image")
        @Expose
        private String subjectImage;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("maximum_marks")
        @Expose
        private Integer maximumMarks;
        @SerializedName("pass_marks")
        @Expose
        private Integer passMarks;
        @SerializedName("is_lab")
        @Expose
        private Integer isLab;
        @SerializedName("is_elective_type")
        @Expose
        private Integer isElectiveType;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("show_in_front")
        @Expose
        private Integer showInFront;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCourseId() {
            return courseId;
        }

        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        public String getSubjectTitle() {
            return subjectTitle;
        }

        public void setSubjectTitle(String subjectTitle) {
            this.subjectTitle = subjectTitle;
        }

        public String getSubjectCode() {
            return subjectCode;
        }

        public void setSubjectCode(String subjectCode) {
            this.subjectCode = subjectCode;
        }

        public String getSubjOfYear() {
            return subjOfYear;
        }

        public void setSubjOfYear(String subjOfYear) {
            this.subjOfYear = subjOfYear;
        }

        public String getSubjectImage() {
            return subjectImage;
        }

        public void setSubjectImage(String subjectImage) {
            this.subjectImage = subjectImage;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public Integer getMaximumMarks() {
            return maximumMarks;
        }

        public void setMaximumMarks(Integer maximumMarks) {
            this.maximumMarks = maximumMarks;
        }

        public Integer getPassMarks() {
            return passMarks;
        }

        public void setPassMarks(Integer passMarks) {
            this.passMarks = passMarks;
        }

        public Integer getIsLab() {
            return isLab;
        }

        public void setIsLab(Integer isLab) {
            this.isLab = isLab;
        }

        public Integer getIsElectiveType() {
            return isElectiveType;
        }

        public void setIsElectiveType(Integer isElectiveType) {
            this.isElectiveType = isElectiveType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getShowInFront() {
            return showInFront;
        }

        public void setShowInFront(Integer showInFront) {
            this.showInFront = showInFront;
        }

    }


    public class Data {

        @SerializedName("subject")
        @Expose
        private List<Subject> subject = null;

        public List<Subject> getSubject() {
            return subject;
        }

        public void setSubject(List<Subject> subject) {
            this.subject = subject;
        }

    }
}

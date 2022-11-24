package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubjectQuestionBank {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("course_id")
    @Expose
    private String courseId;
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
    private String maximumMarks;
    @SerializedName("pass_marks")
    @Expose
    private String passMarks;
    @SerializedName("is_lab")
    @Expose
    private String isLab;
    @SerializedName("is_elective_type")
    @Expose
    private String isElectiveType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("total_module")
    @Expose
    private String totalModule;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
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

    public String getMaximumMarks() {
        return maximumMarks;
    }

    public void setMaximumMarks(String maximumMarks) {
        this.maximumMarks = maximumMarks;
    }

    public String getPassMarks() {
        return passMarks;
    }

    public void setPassMarks(String passMarks) {
        this.passMarks = passMarks;
    }

    public String getIsLab() {
        return isLab;
    }

    public void setIsLab(String isLab) {
        this.isLab = isLab;
    }

    public String getIsElectiveType() {
        return isElectiveType;
    }

    public void setIsElectiveType(String isElectiveType) {
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

    public String getTotalModule() {
        return totalModule;
    }

    public void setTotalModule(String totalModule) {
        this.totalModule = totalModule;
    }
}

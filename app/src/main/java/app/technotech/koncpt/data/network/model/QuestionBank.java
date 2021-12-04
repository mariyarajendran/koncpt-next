package app.technotech.koncpt.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionBank implements Parcelable {


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
    public final static Parcelable.Creator<QuestionBank> CREATOR = new Creator<QuestionBank>() {


        @SuppressWarnings({
                "unchecked"
        })
        public QuestionBank createFromParcel(Parcel in) {
            return new QuestionBank(in);
        }

        public QuestionBank[] newArray(int size) {
            return (new QuestionBank[size]);
        }

    }
            ;

    public QuestionBank(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.courseId = ((String) in.readValue((String.class.getClassLoader())));
        this.subjectTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.subjectCode = ((String) in.readValue((String.class.getClassLoader())));
        this.subjOfYear = ((String) in.readValue((String.class.getClassLoader())));
        this.subjectImage = ((String) in.readValue((String.class.getClassLoader())));
        this.slug = ((String) in.readValue((String.class.getClassLoader())));
        this.maximumMarks = ((String) in.readValue((String.class.getClassLoader())));
        this.passMarks = ((String) in.readValue((String.class.getClassLoader())));
        this.isLab = ((String) in.readValue((String.class.getClassLoader())));
        this.isElectiveType = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.totalModule = ((String) in.readValue((String.class.getClassLoader())));
    }

    public QuestionBank() {
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(courseId);
        dest.writeValue(subjectTitle);
        dest.writeValue(subjectCode);
        dest.writeValue(subjOfYear);
        dest.writeValue(subjectImage);
        dest.writeValue(slug);
        dest.writeValue(maximumMarks);
        dest.writeValue(passMarks);
        dest.writeValue(isLab);
        dest.writeValue(isElectiveType);
        dest.writeValue(status);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(totalModule);
    }

    public int describeContents() {
        return 0;
    }
}

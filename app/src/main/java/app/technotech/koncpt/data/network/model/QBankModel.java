package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QBankModel {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("first_year_subject")
    @Expose
    private List<FirstYearSubject> firstYearSubject = null;
    @SerializedName("second_year_subject")
    @Expose
    private List<SecondYearSubject> secondYearSubject = null;
    @SerializedName("third_year_subject")
    @Expose
    private List<ThirdYearSubject> thirdYearSubject = null;

    @SerializedName("fourth_year_subject")
    @Expose
    private List<FourthYearSubject> fourthYearSubject = null;

//    fourth_year_subject

    @SerializedName("tota_bookmarks")
    @Expose
    private Integer totaBookmarks;

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

    public List<FirstYearSubject> getFirstYearSubject() {
        return firstYearSubject;
    }

    public void setFirstYearSubject(List<FirstYearSubject> firstYearSubject) {
        this.firstYearSubject = firstYearSubject;
    }

    public List<SecondYearSubject> getSecondYearSubject() {
        return secondYearSubject;
    }

    public void setSecondYearSubject(List<SecondYearSubject> secondYearSubject) {
        this.secondYearSubject = secondYearSubject;
    }

    public List<ThirdYearSubject> getThirdYearSubject() {
        return thirdYearSubject;
    }

    public void setThirdYearSubject(List<ThirdYearSubject> thirdYearSubject) {
        this.thirdYearSubject = thirdYearSubject;
    }

    public List<FourthYearSubject> getFourthYearSubject() {
        return fourthYearSubject;
    }

    public void setFourthYearSubject(List<FourthYearSubject> fourthYearSubject) {
        this.fourthYearSubject = fourthYearSubject;
    }


    public Integer getTotaBookmarks() {
        return totaBookmarks;
    }

    public void setTotaBookmarks(Integer totaBookmarks) {
        this.totaBookmarks = totaBookmarks;
    }


    public class FirstYearSubject {
        @SerializedName("subject_id")
        @Expose
        private String subject_id;
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
        @SerializedName("total_module")
        @Expose
        private Integer totalModule;

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
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

        public Integer getTotalModule() {
            return totalModule;
        }

        public void setTotalModule(Integer totalModule) {
            this.totalModule = totalModule;
        }

    }

    public class SecondYearSubject {
        @SerializedName("subject_id")
        @Expose
        private String subject_id;
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

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
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

    public class ThirdYearSubject {
        @SerializedName("subject_id")
        @Expose
        private String subject_id;
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

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
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


    public class FourthYearSubject {
        @SerializedName("subject_id")
        @Expose
        private String subject_id;
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

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
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

}

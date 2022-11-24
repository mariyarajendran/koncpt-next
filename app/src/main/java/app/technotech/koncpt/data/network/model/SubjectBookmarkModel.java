package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubjectBookmarkModel {



    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("total_bookmark")
    @Expose
    private Integer totalBookmark;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getTotalBookmark() {
        return totalBookmark;
    }

    public void setTotalBookmark(Integer totalBookmark) {
        this.totalBookmark = totalBookmark;
    }



    public static class Datum {

        public Datum(){}

        public Datum(Integer id, Integer subjectId, String subjectTitle, Integer userId, Integer totalMcq) {
            this.id = id;
            this.subjectId = subjectId;
            this.subjectTitle = subjectTitle;
            this.userId = userId;
            this.totalMcq = totalMcq;
        }

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("subject_title")
        @Expose
        private String subjectTitle;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("total_mcq")
        @Expose
        private Integer totalMcq;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectTitle() {
            return subjectTitle;
        }

        public void setSubjectTitle(String subjectTitle) {
            this.subjectTitle = subjectTitle;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getTotalMcq() {
            return totalMcq;
        }

        public void setTotalMcq(Integer totalMcq) {
            this.totalMcq = totalMcq;
        }

    }
}

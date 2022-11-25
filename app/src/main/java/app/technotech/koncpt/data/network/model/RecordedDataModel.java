package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecordedDataModel {
    @SerializedName("status")
    @Expose
    private Integer status;

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

    public List<RecordedData> getData() {
        return data;
    }

    public void setData(List<RecordedData> data) {
        this.data = data;
    }

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("recorded_data")
    @Expose
    private List<RecordedData> data = null;

    public class RecordedData {
        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("faculty_name")
        @Expose
        private String facultyName;

        @SerializedName("from_time")
        @Expose
        private String fromTime;

        @SerializedName("to_time")
        @Expose
        private String toTime;

        @SerializedName("to_date")
        @Expose
        private String toDate;

        @SerializedName("video_url")
        @Expose
        private String videoUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFacultyName() {
            return facultyName;
        }

        public void setFacultyName(String facultyName) {
            this.facultyName = facultyName;
        }

        public String getFromTime() {
            return fromTime;
        }

        public void setFromTime(String fromTime) {
            this.fromTime = fromTime;
        }

        public String getToTime() {
            return toTime;
        }

        public void setToTime(String toTime) {
            this.toTime = toTime;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

    }
}

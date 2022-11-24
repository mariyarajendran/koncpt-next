package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveClassesListModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("live_class_data")
    @Expose
    private List<LiveClassDatum> liveClassData = null;
    @SerializedName("enroll_data")
    @Expose
    private List<EnrollDatum> enrollData = null;

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

    public List<LiveClassDatum> getLiveClassData() {
        return liveClassData;
    }

    public void setLiveClassData(List<LiveClassDatum> liveClassData) {
        this.liveClassData = liveClassData;
    }

    public List<EnrollDatum> getEnrollData() {
        return enrollData;
    }

    public void setEnrollData(List<EnrollDatum> enrollData) {
        this.enrollData = enrollData;
    }



    public class EnrollDatum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("faculty_id")
        @Expose
        private Integer facultyId;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("zoom_username")
        @Expose
        private String zoomUsername;
        @SerializedName("zoom_password")
        @Expose
        private String zoomPassword;
        @SerializedName("from_time")
        @Expose
        private String fromTime;
        @SerializedName("to_time")
        @Expose
        private String toTime;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("to_date")
        @Expose
        private String toDate;
        @SerializedName("duration")
        @Expose
        private String duration;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("create_at")
        @Expose
        private String createAt;
        @SerializedName("update_at")
        @Expose
        private String updateAt;
        @SerializedName("ip")
        @Expose
        private String ip;
        @SerializedName("name")
        @Expose
        private Object name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getFacultyId() {
            return facultyId;
        }

        public void setFacultyId(Integer facultyId) {
            this.facultyId = facultyId;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getZoomUsername() {
            return zoomUsername;
        }

        public void setZoomUsername(String zoomUsername) {
            this.zoomUsername = zoomUsername;
        }

        public String getZoomPassword() {
            return zoomPassword;
        }

        public void setZoomPassword(String zoomPassword) {
            this.zoomPassword = zoomPassword;
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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

    }

    public class LiveClassDatum {

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("faculty_id")
        @Expose
        private Integer facultyId;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("zoom_username")
        @Expose
        private String zoomUsername;
        @SerializedName("zoom_password")
        @Expose
        private String zoomPassword;
        @SerializedName("from_time")
        @Expose
        private String fromTime;
        @SerializedName("to_time")
        @Expose
        private String toTime;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("to_date")
        @Expose
        private String toDate;
        @SerializedName("duration")
        @Expose
        private String duration;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("create_at")
        @Expose
        private String createAt;
        @SerializedName("update_at")
        @Expose
        private String updateAt;
        @SerializedName("ip")
        @Expose
        private String ip;
        @SerializedName("name")
        @Expose
        private String name;


        public Integer getFacultyId() {
            return facultyId;
        }

        public void setFacultyId(Integer facultyId) {
            this.facultyId = facultyId;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getZoomUsername() {
            return zoomUsername;
        }

        public void setZoomUsername(String zoomUsername) {
            this.zoomUsername = zoomUsername;
        }

        public String getZoomPassword() {
            return zoomPassword;
        }

        public void setZoomPassword(String zoomPassword) {
            this.zoomPassword = zoomPassword;
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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}

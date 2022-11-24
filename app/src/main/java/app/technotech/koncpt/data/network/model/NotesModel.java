package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotesModel {

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


    //    ---


    public class Data {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("class_video")
        @Expose
        private String classVideo;
        @SerializedName("video_cut_time_topic")
        @Expose
        private List<VideoCutTimeTopic> data = null;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("faculty_name")
        @Expose
        private String facultyName;
        @SerializedName("editor")
        @Expose
        private String editor;
        @SerializedName("subject_title")
        @Expose
        private String subjectTitle;
        @SerializedName("class_title")
        @Expose
        private String classTitle;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClassVideo() {
            return classVideo;
        }

        public void setClassVideo(String classVideo) {
            this.classVideo = classVideo;
        }



        public List<VideoCutTimeTopic> getData() {
            return data;
        }

        public void setData(List<VideoCutTimeTopic> data) {
            this.data = data;
        }



        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFacultyName() {
            return facultyName;
        }

        public void setFacultyName(String facultyName) {
            this.facultyName = facultyName;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public String getSubjectTitle() {
            return subjectTitle;
        }

        public void setSubjectTitle(String subjectTitle) {
            this.subjectTitle = subjectTitle;
        }

        public String getClassTitle() {
            return classTitle;
        }

        public void setClassTitle(String classTitle) {
            this.classTitle = classTitle;
        }
//done run
    }

    public class VideoCutTimeTopic {

        @SerializedName("video_time")
        @Expose
        private String videoTime;
        @SerializedName("video_cut_topic")
        @Expose
        private String videoCutTopic;

        public String getVideoTime() {
            return videoTime;
        }

        public void setVideoTime(String videoTime) {
            this.videoTime = videoTime;
        }

        public String getVideoCutTopic() {
            return videoCutTopic;
        }

        public void setVideoCutTopic(String videoCutTopic) {
            this.videoCutTopic = videoCutTopic;
        }

    }


}
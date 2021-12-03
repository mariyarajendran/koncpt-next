package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoModel {
    //status & message & data
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


    public class Data {

        //module_name & module_data
        @SerializedName("module_name")
        @Expose
        private List<ModuleName> moduleName = null;
        @SerializedName("module_data")
        @Expose
        private List<ModuleDatum> moduleData = null;

        public List<ModuleName> getModuleName() {
            return moduleName;
        }

        public void setModuleName(List<ModuleName> moduleName) {
            this.moduleName = moduleName;
        }

        public List<ModuleDatum> getModuleData() {
            return moduleData;
        }

        public void setModuleData(List<ModuleDatum> moduleData) {
            this.moduleData = moduleData;
        }

    }

    public class ModuleName {

        // id & subject_id & subject_id  & module_name

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("module_name")
        @Expose
        private String moduleName;

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

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }
    }
    public class ModuleDatum {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("class_title")
        @Expose
        private String classTitle;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("faculty_id")
        @Expose
        private Integer facultyId;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("module_id")
        @Expose
        private Integer moduleId;
        @SerializedName("topic_id")
        @Expose
        private Integer topicId;
        @SerializedName("class_video")
        @Expose
        private String classVideo;
        @SerializedName("video_cut_time_topic")
        @Expose
        private String videoCutTimeTopic;
        @SerializedName("video_duration")
        @Expose
        private String videoDuration;
        @SerializedName("class_slides")
        @Expose
        private String classSlides;
        @SerializedName("class_pdf")
        @Expose
        private String classPdf;
        @SerializedName("is_free_for_users")
        @Expose
        private String isFreeForUsers;
        @SerializedName("is_video_for_plan_B")
        @Expose
        private String isVideoForPlanB;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("paushed_time")
        @Expose
        private String paushedTime;
        @SerializedName("faculty_image")
        @Expose
        private String facultyImage;

        @SerializedName("video_rate")
        @Expose
        private String videoRate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getClassTitle() {
            return classTitle;
        }

        public void setClassTitle(String classTitle) {
            this.classTitle = classTitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public Integer getModuleId() {
            return moduleId;
        }

        public void setModuleId(Integer moduleId) {
            this.moduleId = moduleId;
        }

        public Integer getTopicId() {
            return topicId;
        }

        public void setTopicId(Integer topicId) {
            this.topicId = topicId;
        }

        public String getClassVideo() {
            return classVideo;
        }

        public void setClassVideo(String classVideo) {
            this.classVideo = classVideo;
        }

        public String getVideoCutTimeTopic() {
            return videoCutTimeTopic;
        }

        public void setVideoCutTimeTopic(String videoCutTimeTopic) {
            this.videoCutTimeTopic = videoCutTimeTopic;
        }

        public String getVideoDuration() {
            return videoDuration;
        }

        public void setVideoDuration(String videoDuration) {
            this.videoDuration = videoDuration;
        }

        public String getClassSlides() {
            return classSlides;
        }

        public void setClassSlides(String classSlides) {
            this.classSlides = classSlides;
        }

        public String getClassPdf() {
            return classPdf;
        }

        public void setClassPdf(String classPdf) {
            this.classPdf = classPdf;
        }

        public String getIsFreeForUsers() {
            return isFreeForUsers;
        }

        public void setIsFreeForUsers(String isFreeForUsers) {
            this.isFreeForUsers = isFreeForUsers;
        }

        public String getIsVideoForPlanB() {
            return isVideoForPlanB;
        }

        public void setIsVideoForPlanB(String isVideoForPlanB) {
            this.isVideoForPlanB = isVideoForPlanB;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPaushedTime() {
            return paushedTime;
        }

        public void setPaushedTime(String paushedTime) {
            this.paushedTime = paushedTime;
        }

        public String getFacultyImage() {
            return facultyImage;
        }

        public void setFacultyImage(String facultyImage) {
            this.facultyImage = facultyImage;
        }

        public String getVideoRate() {
            return videoRate;
        }

        public void setVideoRate(String videoRate) {
            this.videoRate = videoRate;
        }
    }
}

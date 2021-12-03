package app.technotech.koncpt.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeScreenModel implements Parcelable {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<HomeScreenModel> CREATOR = new Creator<HomeScreenModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HomeScreenModel createFromParcel(Parcel in) {
            return new HomeScreenModel(in);
        }

        public HomeScreenModel[] newArray(int size) {
            return (new HomeScreenModel[size]);
        }

    };

    protected HomeScreenModel(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public HomeScreenModel() {
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }


    public class Data implements Parcelable {

        @SerializedName("live_class")
        @Expose
        private LiveClass liveClass;
        @SerializedName("mcq_of_the_day")
        @Expose
        private List<McqOfTheDay> mcqOfTheDay = null;
        @SerializedName("test_series")
        @Expose
        private List<TestSeries> testSeries = null;
        @SerializedName("suggested_video")
        @Expose
        private List<SuggestedVideo> suggestedVideo = null;
        @SerializedName("popular_mcq")
        @Expose
        private List<PopularMcq> popularMcq = null;
        @SerializedName("leaders")
        @Expose
        private List<Leader> leaders = null;
        @SerializedName("progress")
        @Expose
        private Progress progress;
        @SerializedName("mcq_answer_status")
        @Expose
        private McqAnswerStatus mcqAnswerStatus = null;
        public final Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            public Data[] newArray(int size) {
                return (new Data[size]);
            }

        };

        protected Data(Parcel in) {
            this.liveClass = ((LiveClass) in.readValue((LiveClass.class.getClassLoader())));
            in.readList(this.mcqOfTheDay, (java.lang.Object.class.getClassLoader()));
            in.readList(this.testSeries, (TestSeries.class.getClassLoader()));
            in.readList(this.suggestedVideo, (SuggestedVideo.class.getClassLoader()));
            in.readList(this.popularMcq, (PopularMcq.class.getClassLoader()));
            in.readList(this.leaders, (Leader.class.getClassLoader()));
            this.progress = ((Progress) in.readValue((Progress.class.getClassLoader())));
            this.mcqAnswerStatus = ((McqAnswerStatus) in.readValue((McqAnswerStatus.class.getClassLoader())));
        }

        public Data() {
        }

        public LiveClass getLiveClass() {
            return liveClass;
        }

        public void setLiveClass(LiveClass liveClass) {
            this.liveClass = liveClass;
        }

        public List<McqOfTheDay> getMcqOfTheDay() {
            return mcqOfTheDay;
        }

        public void setMcqOfTheDay(List<McqOfTheDay> mcqOfTheDay) {
            this.mcqOfTheDay = mcqOfTheDay;
        }

        public List<TestSeries> getTestSeries() {
            return testSeries;
        }

        public void setTestSeries(List<TestSeries> testSeries) {
            this.testSeries = testSeries;
        }

        public List<SuggestedVideo> getSuggestedVideo() {
            return suggestedVideo;
        }

        public void setSuggestedVideo(List<SuggestedVideo> suggestedVideo) {
            this.suggestedVideo = suggestedVideo;
        }

        public List<PopularMcq> getPopularMcq() {
            return popularMcq;
        }

        public void setPopularMcq(List<PopularMcq> popularMcq) {
            this.popularMcq = popularMcq;
        }

        public List<Leader> getLeaders() {
            return leaders;
        }

        public void setLeaders(List<Leader> leaders) {
            this.leaders = leaders;
        }

        public Progress getProgress() {
            return progress;
        }

        public void setProgress(Progress progress) {
            this.progress = progress;
        }

        public McqAnswerStatus getMcqAnswerStatus() {
            return mcqAnswerStatus;
        }

        public void setMcqAnswerStatus(McqAnswerStatus mcqAnswerStatus) {
            this.mcqAnswerStatus = mcqAnswerStatus;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(liveClass);
            dest.writeList(mcqOfTheDay);
            dest.writeList(testSeries);
            dest.writeList(suggestedVideo);
            dest.writeList(popularMcq);
            dest.writeList(leaders);
            dest.writeValue(progress);
            dest.writeValue(mcqAnswerStatus);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Leader implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("percentage")
        @Expose
        private String percentage;
        @SerializedName("test_type")
        @Expose
        private String testType;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("user_profile_pic")
        @Expose
        private String userProfilePic;
        public final Parcelable.Creator<Leader> CREATOR = new Creator<Leader>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Leader createFromParcel(Parcel in) {
                return new Leader(in);
            }

            public Leader[] newArray(int size) {
                return (new Leader[size]);
            }

        };

        protected Leader(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.percentage = ((String) in.readValue((String.class.getClassLoader())));
            this.testType = ((String) in.readValue((String.class.getClassLoader())));
            this.name = ((String) in.readValue((String.class.getClassLoader())));
            this.userProfilePic = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Leader() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getTestType() {
            return testType;
        }

        public void setTestType(String testType) {
            this.testType = testType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserProfilePic() {
            return userProfilePic;
        }

        public void setUserProfilePic(String userProfilePic) {
            this.userProfilePic = userProfilePic;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(percentage);
            dest.writeValue(testType);
            dest.writeValue(name);
            dest.writeValue(userProfilePic);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class PopularMcq implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("topic_id")
        @Expose
        private Integer topicId;
        @SerializedName("topic_name")
        @Expose
        private String topicName;
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
        @SerializedName("is_paid")
        @Expose
        private Integer isPaid;
        @SerializedName("total_mcq")
        @Expose
        private Integer totalMcq;
        public final Parcelable.Creator<PopularMcq> CREATOR = new Creator<PopularMcq>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public PopularMcq createFromParcel(Parcel in) {
                return new PopularMcq(in);
            }

            public PopularMcq[] newArray(int size) {
                return (new PopularMcq[size]);
            }

        };

        protected PopularMcq(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.topicId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.topicName = ((String) in.readValue((String.class.getClassLoader())));
            this.courseId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.subjectTitle = ((String) in.readValue((String.class.getClassLoader())));
            this.subjectCode = ((String) in.readValue((String.class.getClassLoader())));
            this.subjOfYear = ((String) in.readValue((String.class.getClassLoader())));
            this.subjectImage = ((String) in.readValue((String.class.getClassLoader())));
            this.slug = ((String) in.readValue((String.class.getClassLoader())));
            this.maximumMarks = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.passMarks = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.isLab = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.isElectiveType = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.status = ((String) in.readValue((String.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.showInFront = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.isPaid = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalMcq = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public PopularMcq() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getTopicId() {
            return topicId;
        }

        public void setTopicId(Integer topicId) {
            this.topicId = topicId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
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

        public Integer getIsPaid() {
            return isPaid;
        }

        public void setIsPaid(Integer isPaid) {
            this.isPaid = isPaid;
        }

        public Integer getTotalMcq() {
            return totalMcq;
        }

        public void setTotalMcq(Integer totalMcq) {
            this.totalMcq = totalMcq;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(topicId);
            dest.writeValue(topicName);
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
            dest.writeValue(showInFront);
            dest.writeValue(isPaid);
            dest.writeValue(totalMcq);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class Progress implements Parcelable {

        @SerializedName("progress_percentange")
        @Expose
        private Integer progressPercentange;
        public final Parcelable.Creator<Progress> CREATOR = new Creator<Progress>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Progress createFromParcel(Parcel in) {
                return new Progress(in);
            }

            public Progress[] newArray(int size) {
                return (new Progress[size]);
            }

        };

        protected Progress(Parcel in) {
            this.progressPercentange = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Progress() {
        }

        public Integer getProgressPercentange() {
            return progressPercentange;
        }

        public void setProgressPercentange(Integer progressPercentange) {
            this.progressPercentange = progressPercentange;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(progressPercentange);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class SelectedAnswer implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("mcq_of_the_day_id")
        @Expose
        private Integer mcqOfTheDayId;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("create_at")
        @Expose
        private String createAt;
        @SerializedName("answer")
        @Expose
        private Integer answer;
        public final Parcelable.Creator<SelectedAnswer> CREATOR = new Creator<SelectedAnswer>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public SelectedAnswer createFromParcel(Parcel in) {
                return new SelectedAnswer(in);
            }

            public SelectedAnswer[] newArray(int size) {
                return (new SelectedAnswer[size]);
            }

        };

        protected SelectedAnswer(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.mcqOfTheDayId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.createAt = ((String) in.readValue((String.class.getClassLoader())));
            this.answer = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public SelectedAnswer() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getMcqOfTheDayId() {
            return mcqOfTheDayId;
        }

        public void setMcqOfTheDayId(Integer mcqOfTheDayId) {
            this.mcqOfTheDayId = mcqOfTheDayId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public Integer getAnswer() {
            return answer;
        }

        public void setAnswer(Integer answer) {
            this.answer = answer;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(mcqOfTheDayId);
            dest.writeValue(userId);
            dest.writeValue(createAt);
            dest.writeValue(answer);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class SuggestedVideo implements Parcelable {

        @SerializedName("subject_title")
        @Expose
        private String subjectTitle;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("class_title")
        @Expose
        private String classTitle;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("faculty_id")
        @Expose
        private Integer facultyId;
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
        private List<VideoCutTimeTopic> videoCutTimeTopic = null;
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
        public final Parcelable.Creator<SuggestedVideo> CREATOR = new Creator<SuggestedVideo>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public SuggestedVideo createFromParcel(Parcel in) {
                return new SuggestedVideo(in);
            }

            public SuggestedVideo[] newArray(int size) {
                return (new SuggestedVideo[size]);
            }

        };

        protected SuggestedVideo(Parcel in) {
            this.subjectTitle = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.classTitle = ((String) in.readValue((String.class.getClassLoader())));
            this.description = ((String) in.readValue((String.class.getClassLoader())));
            this.subjectId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.facultyId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.moduleId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.topicId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.classVideo = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.videoCutTimeTopic, (VideoCutTimeTopic.class.getClassLoader()));
            this.videoDuration = ((String) in.readValue((String.class.getClassLoader())));
            this.classSlides = ((String) in.readValue((String.class.getClassLoader())));
            this.classPdf = ((String) in.readValue((String.class.getClassLoader())));
            this.isFreeForUsers = ((String) in.readValue((String.class.getClassLoader())));
            this.isVideoForPlanB = ((String) in.readValue((String.class.getClassLoader())));
            this.type = ((String) in.readValue((String.class.getClassLoader())));
            this.paushedTime = ((String) in.readValue((String.class.getClassLoader())));
            this.facultyImage = ((String) in.readValue((String.class.getClassLoader())));
            this.videoRate = ((String) in.readValue((String.class.getClassLoader())));
        }

        public SuggestedVideo() {
        }

        public String getSubjectTitle() {
            return subjectTitle;
        }

        public void setSubjectTitle(String subjectTitle) {
            this.subjectTitle = subjectTitle;
        }

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

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public Integer getFacultyId() {
            return facultyId;
        }

        public void setFacultyId(Integer facultyId) {
            this.facultyId = facultyId;
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

        public List<VideoCutTimeTopic> getVideoCutTimeTopic() {
            return videoCutTimeTopic;
        }

        public void setVideoCutTimeTopic(List<VideoCutTimeTopic> videoCutTimeTopic) {
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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(subjectTitle);
            dest.writeValue(id);
            dest.writeValue(classTitle);
            dest.writeValue(description);
            dest.writeValue(subjectId);
            dest.writeValue(facultyId);
            dest.writeValue(moduleId);
            dest.writeValue(topicId);
            dest.writeValue(classVideo);
            dest.writeList(videoCutTimeTopic);
            dest.writeValue(videoDuration);
            dest.writeValue(classSlides);
            dest.writeValue(classPdf);
            dest.writeValue(isFreeForUsers);
            dest.writeValue(isVideoForPlanB);
            dest.writeValue(type);
            dest.writeValue(paushedTime);
            dest.writeValue(facultyImage);
            dest.writeValue(videoRate);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class TestSeries implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("dueration")
        @Expose
        private Integer dueration;
        @SerializedName("category_id")
        @Expose
        private Integer categoryId;
        @SerializedName("subject_id")
        @Expose
        private String subjectId;
        @SerializedName("is_paid")
        @Expose
        private Integer isPaid;
        @SerializedName("cost")
        @Expose
        private String cost;
        @SerializedName("validity")
        @Expose
        private Integer validity;
        @SerializedName("total_marks")
        @Expose
        private String totalMarks;
        @SerializedName("having_negative_mark")
        @Expose
        private Integer havingNegativeMark;
        @SerializedName("negative_mark")
        @Expose
        private String negativeMark;
        @SerializedName("pass_percentage")
        @Expose
        private String passPercentage;
        @SerializedName("tags")
        @Expose
        private String tags;
        @SerializedName("publish_results_immediately")
        @Expose
        private Integer publishResultsImmediately;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("total_questions")
        @Expose
        private Integer totalQuestions;
        @SerializedName("instructions_page_id")
        @Expose
        private Integer instructionsPageId;
        @SerializedName("start_date")
        @Expose
        private String startDate;
        @SerializedName("end_date")
        @Expose
        private String endDate;
        @SerializedName("record_updated_by")
        @Expose
        private Integer recordUpdatedBy;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("show_in_front")
        @Expose
        private Integer showInFront;
        @SerializedName("exam_type")
        @Expose
        private String examType;
        @SerializedName("section_data")
        @Expose
        private String sectionData;
        @SerializedName("has_language")
        @Expose
        private Integer hasLanguage;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("language_name")
        @Expose
        private String languageName;
        @SerializedName("is_popular")
        @Expose
        private Integer isPopular;
        @SerializedName("test_type")
        @Expose
        private String testType;
        @SerializedName("sub_image")
        @Expose
        private String subImage;
        public final Parcelable.Creator<TestSeries> CREATOR = new Creator<TestSeries>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public TestSeries createFromParcel(Parcel in) {
                return new TestSeries(in);
            }

            public TestSeries[] newArray(int size) {
                return (new TestSeries[size]);
            }

        };

        protected TestSeries(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.slug = ((String) in.readValue((String.class.getClassLoader())));
            this.dueration = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.categoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.subjectId = ((String) in.readValue((String.class.getClassLoader())));
            this.isPaid = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.cost = ((String) in.readValue((String.class.getClassLoader())));
            this.validity = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalMarks = ((String) in.readValue((String.class.getClassLoader())));
            this.havingNegativeMark = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.negativeMark = ((String) in.readValue((String.class.getClassLoader())));
            this.passPercentage = ((String) in.readValue((String.class.getClassLoader())));
            this.tags = ((String) in.readValue((String.class.getClassLoader())));
            this.publishResultsImmediately = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.description = ((String) in.readValue((String.class.getClassLoader())));
            this.totalQuestions = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.instructionsPageId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.startDate = ((String) in.readValue((String.class.getClassLoader())));
            this.endDate = ((String) in.readValue((String.class.getClassLoader())));
            this.recordUpdatedBy = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.showInFront = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.examType = ((String) in.readValue((String.class.getClassLoader())));
            this.sectionData = ((String) in.readValue((String.class.getClassLoader())));
            this.hasLanguage = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.image = ((String) in.readValue((String.class.getClassLoader())));
            this.languageName = ((String) in.readValue((String.class.getClassLoader())));
            this.isPopular = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.testType = ((String) in.readValue((String.class.getClassLoader())));
            this.subImage = ((String) in.readValue((String.class.getClassLoader())));
        }

        public TestSeries() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public Integer getDueration() {
            return dueration;
        }

        public void setDueration(Integer dueration) {
            this.dueration = dueration;
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public Integer getIsPaid() {
            return isPaid;
        }

        public void setIsPaid(Integer isPaid) {
            this.isPaid = isPaid;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public Integer getValidity() {
            return validity;
        }

        public void setValidity(Integer validity) {
            this.validity = validity;
        }

        public String getTotalMarks() {
            return totalMarks;
        }

        public void setTotalMarks(String totalMarks) {
            this.totalMarks = totalMarks;
        }

        public Integer getHavingNegativeMark() {
            return havingNegativeMark;
        }

        public void setHavingNegativeMark(Integer havingNegativeMark) {
            this.havingNegativeMark = havingNegativeMark;
        }

        public String getNegativeMark() {
            return negativeMark;
        }

        public void setNegativeMark(String negativeMark) {
            this.negativeMark = negativeMark;
        }

        public String getPassPercentage() {
            return passPercentage;
        }

        public void setPassPercentage(String passPercentage) {
            this.passPercentage = passPercentage;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public Integer getPublishResultsImmediately() {
            return publishResultsImmediately;
        }

        public void setPublishResultsImmediately(Integer publishResultsImmediately) {
            this.publishResultsImmediately = publishResultsImmediately;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getTotalQuestions() {
            return totalQuestions;
        }

        public void setTotalQuestions(Integer totalQuestions) {
            this.totalQuestions = totalQuestions;
        }

        public Integer getInstructionsPageId() {
            return instructionsPageId;
        }

        public void setInstructionsPageId(Integer instructionsPageId) {
            this.instructionsPageId = instructionsPageId;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Integer getRecordUpdatedBy() {
            return recordUpdatedBy;
        }

        public void setRecordUpdatedBy(Integer recordUpdatedBy) {
            this.recordUpdatedBy = recordUpdatedBy;
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

        public String getExamType() {
            return examType;
        }

        public void setExamType(String examType) {
            this.examType = examType;
        }

        public String getSectionData() {
            return sectionData;
        }

        public void setSectionData(String sectionData) {
            this.sectionData = sectionData;
        }

        public Integer getHasLanguage() {
            return hasLanguage;
        }

        public void setHasLanguage(Integer hasLanguage) {
            this.hasLanguage = hasLanguage;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLanguageName() {
            return languageName;
        }

        public void setLanguageName(String languageName) {
            this.languageName = languageName;
        }

        public Integer getIsPopular() {
            return isPopular;
        }

        public void setIsPopular(Integer isPopular) {
            this.isPopular = isPopular;
        }

        public String getTestType() {
            return testType;
        }

        public void setTestType(String testType) {
            this.testType = testType;
        }

        public String getSubImage() {
            return subImage;
        }

        public void setSubImage(String subImage) {
            this.subImage = subImage;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(title);
            dest.writeValue(slug);
            dest.writeValue(dueration);
            dest.writeValue(categoryId);
            dest.writeValue(subjectId);
            dest.writeValue(isPaid);
            dest.writeValue(cost);
            dest.writeValue(validity);
            dest.writeValue(totalMarks);
            dest.writeValue(havingNegativeMark);
            dest.writeValue(negativeMark);
            dest.writeValue(passPercentage);
            dest.writeValue(tags);
            dest.writeValue(publishResultsImmediately);
            dest.writeValue(description);
            dest.writeValue(totalQuestions);
            dest.writeValue(instructionsPageId);
            dest.writeValue(startDate);
            dest.writeValue(endDate);
            dest.writeValue(recordUpdatedBy);
            dest.writeValue(createdAt);
            dest.writeValue(updatedAt);
            dest.writeValue(showInFront);
            dest.writeValue(examType);
            dest.writeValue(sectionData);
            dest.writeValue(hasLanguage);
            dest.writeValue(image);
            dest.writeValue(languageName);
            dest.writeValue(isPopular);
            dest.writeValue(testType);
            dest.writeValue(subImage);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class LiveClass implements Parcelable {

        @SerializedName("class_marques")
        @Expose
        private String classMarques;
        public final Parcelable.Creator<LiveClass> CREATOR = new Creator<LiveClass>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public LiveClass createFromParcel(Parcel in) {
                return new LiveClass(in);
            }

            public LiveClass[] newArray(int size) {
                return (new LiveClass[size]);
            }

        };

        protected LiveClass(Parcel in) {
            this.classMarques = ((String) in.readValue((String.class.getClassLoader())));
        }

        public LiveClass() {
        }

        public String getClassMarques() {
            return classMarques;
        }

        public void setClassMarques(String classMarques) {
            this.classMarques = classMarques;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(classMarques);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class McqAnswerStatus implements Parcelable {

        @SerializedName("is_answer_for_mcq")
        @Expose
        private Integer isAnswerForMcq;
        @SerializedName("selected_answer")
        @Expose
        private List<SelectedAnswer> selectedAnswer = null;
        public final Parcelable.Creator<McqAnswerStatus> CREATOR = new Creator<McqAnswerStatus>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public McqAnswerStatus createFromParcel(Parcel in) {
                return new McqAnswerStatus(in);
            }

            public McqAnswerStatus[] newArray(int size) {
                return (new McqAnswerStatus[size]);
            }

        };

        protected McqAnswerStatus(Parcel in) {
            this.isAnswerForMcq = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(this.selectedAnswer, (SelectedAnswer.class.getClassLoader()));
        }

        public McqAnswerStatus() {
        }

        public Integer getIsAnswerForMcq() {
            return isAnswerForMcq;
        }

        public void setIsAnswerForMcq(Integer isAnswerForMcq) {
            this.isAnswerForMcq = isAnswerForMcq;
        }

        public List<SelectedAnswer> getSelectedAnswer() {
            return selectedAnswer;
        }

        public void setSelectedAnswer(List<SelectedAnswer> selectedAnswer) {
            this.selectedAnswer = selectedAnswer;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(isAnswerForMcq);
            dest.writeList(selectedAnswer);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class McqOfTheDay implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("topic_id")
        @Expose
        private Integer topicId;
        @SerializedName("question_tags")
        @Expose
        private String questionTags;
        @SerializedName("question_type")
        @Expose
        private String questionType;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("answers")
        @Expose
        private List<Answer> answers = null;
        @SerializedName("question_file")
        @Expose
        private String questionFile;
        @SerializedName("question_file_is_url")
        @Expose
        private Integer questionFileIsUrl;
        @SerializedName("total_answers")
        @Expose
        private Integer totalAnswers;
        @SerializedName("total_correct_answers")
        @Expose
        private Integer totalCorrectAnswers;
        @SerializedName("correct_answers")
        @Expose
        private String correctAnswers;
        @SerializedName("marks")
        @Expose
        private Integer marks;
        @SerializedName("time_to_spend")
        @Expose
        private Integer timeToSpend;
        @SerializedName("difficulty_level")
        @Expose
        private String difficultyLevel;
        @SerializedName("hint")
        @Expose
        private String hint;
        @SerializedName("explanation")
        @Expose
        private String explanation;
        @SerializedName("explanation_file")
        @Expose
        private String explanationFile;
        @SerializedName("status")
        @Expose
        private Integer status;
        public final Parcelable.Creator<McqOfTheDay> CREATOR = new Creator<McqOfTheDay>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public McqOfTheDay createFromParcel(Parcel in) {
                return new McqOfTheDay(in);
            }

            public McqOfTheDay[] newArray(int size) {
                return (new McqOfTheDay[size]);
            }

        };

        protected McqOfTheDay(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.subjectId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.topicId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.questionTags = ((String) in.readValue((String.class.getClassLoader())));
            this.questionType = ((String) in.readValue((String.class.getClassLoader())));
            this.question = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.answers, (Answer.class.getClassLoader()));
            this.questionFile = ((String) in.readValue((String.class.getClassLoader())));
            this.questionFileIsUrl = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalAnswers = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalCorrectAnswers = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.correctAnswers = ((String) in.readValue((String.class.getClassLoader())));
            this.marks = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.timeToSpend = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.difficultyLevel = ((String) in.readValue((String.class.getClassLoader())));
            this.hint = ((String) in.readValue((String.class.getClassLoader())));
            this.explanation = ((String) in.readValue((String.class.getClassLoader())));
            this.explanationFile = ((String) in.readValue((String.class.getClassLoader())));
            this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public McqOfTheDay() {
        }

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

        public Integer getTopicId() {
            return topicId;
        }

        public void setTopicId(Integer topicId) {
            this.topicId = topicId;
        }

        public String getQuestionTags() {
            return questionTags;
        }

        public void setQuestionTags(String questionTags) {
            this.questionTags = questionTags;
        }

        public String getQuestionType() {
            return questionType;
        }

        public void setQuestionType(String questionType) {
            this.questionType = questionType;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }

        public String getQuestionFile() {
            return questionFile;
        }

        public void setQuestionFile(String questionFile) {
            this.questionFile = questionFile;
        }

        public Integer getQuestionFileIsUrl() {
            return questionFileIsUrl;
        }

        public void setQuestionFileIsUrl(Integer questionFileIsUrl) {
            this.questionFileIsUrl = questionFileIsUrl;
        }

        public Integer getTotalAnswers() {
            return totalAnswers;
        }

        public void setTotalAnswers(Integer totalAnswers) {
            this.totalAnswers = totalAnswers;
        }

        public Integer getTotalCorrectAnswers() {
            return totalCorrectAnswers;
        }

        public void setTotalCorrectAnswers(Integer totalCorrectAnswers) {
            this.totalCorrectAnswers = totalCorrectAnswers;
        }

        public String getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers(String correctAnswers) {
            this.correctAnswers = correctAnswers;
        }

        public Integer getMarks() {
            return marks;
        }

        public void setMarks(Integer marks) {
            this.marks = marks;
        }

        public Integer getTimeToSpend() {
            return timeToSpend;
        }

        public void setTimeToSpend(Integer timeToSpend) {
            this.timeToSpend = timeToSpend;
        }

        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        public void setDifficultyLevel(String difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getExplanation() {
            return explanation;
        }

        public void setExplanation(String explanation) {
            this.explanation = explanation;
        }

        public String getExplanationFile() {
            return explanationFile;
        }

        public void setExplanationFile(String explanationFile) {
            this.explanationFile = explanationFile;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(subjectId);
            dest.writeValue(topicId);
            dest.writeValue(questionTags);
            dest.writeValue(questionType);
            dest.writeValue(question);
            dest.writeList(answers);
            dest.writeValue(questionFile);
            dest.writeValue(questionFileIsUrl);
            dest.writeValue(totalAnswers);
            dest.writeValue(totalCorrectAnswers);
            dest.writeValue(correctAnswers);
            dest.writeValue(marks);
            dest.writeValue(timeToSpend);
            dest.writeValue(difficultyLevel);
            dest.writeValue(hint);
            dest.writeValue(explanation);
            dest.writeValue(explanationFile);
            dest.writeValue(status);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class Answer implements Parcelable {

        @SerializedName("option_value")
        @Expose
        private String optionValue;
        @SerializedName("optionl2_value")
        @Expose
        private String optionl2Value;
        @SerializedName("has_file")
        @Expose
        private Integer hasFile;
        @SerializedName("file_name")
        @Expose
        private String fileName;
        public final Parcelable.Creator<Answer> CREATOR = new Creator<Answer>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Answer createFromParcel(Parcel in) {
                return new Answer(in);
            }

            public Answer[] newArray(int size) {
                return (new Answer[size]);
            }

        };

        protected Answer(Parcel in) {
            this.optionValue = ((String) in.readValue((String.class.getClassLoader())));
            this.optionl2Value = ((String) in.readValue((String.class.getClassLoader())));
            this.hasFile = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.fileName = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Answer() {
        }

        public String getOptionValue() {
            return optionValue;
        }

        public void setOptionValue(String optionValue) {
            this.optionValue = optionValue;
        }

        public String getOptionl2Value() {
            return optionl2Value;
        }

        public void setOptionl2Value(String optionl2Value) {
            this.optionl2Value = optionl2Value;
        }

        public Integer getHasFile() {
            return hasFile;
        }

        public void setHasFile(Integer hasFile) {
            this.hasFile = hasFile;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(optionValue);
            dest.writeValue(optionl2Value);
            dest.writeValue(hasFile);
            dest.writeValue(fileName);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class VideoCutTimeTopic implements Parcelable {

        @SerializedName("video_time")
        @Expose
        private String videoTime;
        @SerializedName("video_cut_topic")
        @Expose
        private String videoCutTopic;
        public final Parcelable.Creator<VideoCutTimeTopic> CREATOR = new Creator<VideoCutTimeTopic>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public VideoCutTimeTopic createFromParcel(Parcel in) {
                return new VideoCutTimeTopic(in);
            }

            public VideoCutTimeTopic[] newArray(int size) {
                return (new VideoCutTimeTopic[size]);
            }

        };

        protected VideoCutTimeTopic(Parcel in) {
            this.videoTime = ((String) in.readValue((String.class.getClassLoader())));
            this.videoCutTopic = ((String) in.readValue((String.class.getClassLoader())));
        }

        public VideoCutTimeTopic() {
        }

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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(videoTime);
            dest.writeValue(videoCutTopic);
        }

        public int describeContents() {
            return 0;
        }

    }

//
//    @SerializedName("status")
//    @Expose
//    private Integer status;
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("data")
//    @Expose
//    private Data data;
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Data getData() {
//        return data;
//    }
//
//    public void setData(Data data) {
//        this.data = data;
//    }
//
//
//
//
//    public class Data {
//
//        @SerializedName("live_class")
//        @Expose
//        private LiveClass liveClass;
//        @SerializedName("mcq_of_the_day")
//        @Expose
//        private List<McqOfTheDay> mcqOfTheDay = null;
//        @SerializedName("test_series")
//        @Expose
//        private List<TestSeries> testSeries = null;
//        @SerializedName("suggested_video")
//        @Expose
//        private List<SuggestedVideo> suggestedVideo = null;
//        @SerializedName("popular_mcq")
//        @Expose
//        private List<PopularMcq> popularMcq = null;
//        @SerializedName("leaders")
//        @Expose
//        private List<Leader> leaders = null;
//        @SerializedName("progress")
//        @Expose
//        private Progress progress;
//        @SerializedName("mcq_answer_status")
//        @Expose
//        private McqAnswerStatus mcqAnswerStatus;
//
//        public LiveClass getLiveClass() {
//            return liveClass;
//        }
//
//        public void setLiveClass(LiveClass liveClass) {
//            this.liveClass = liveClass;
//        }
//
//        public List<McqOfTheDay> getMcqOfTheDay() {
//            return mcqOfTheDay;
//        }
//
//        public void setMcqOfTheDay(List<McqOfTheDay> mcqOfTheDay) {
//            this.mcqOfTheDay = mcqOfTheDay;
//        }
//
//        public List<TestSeries> getTestSeries() {
//            return testSeries;
//        }
//
//        public void setTestSeries(List<TestSeries> testSeries) {
//            this.testSeries = testSeries;
//        }
//
//        public List<SuggestedVideo> getSuggestedVideo() {
//            return suggestedVideo;
//        }
//
//        public void setSuggestedVideo(List<SuggestedVideo> suggestedVideo) {
//            this.suggestedVideo = suggestedVideo;
//        }
//
//        public List<PopularMcq> getPopularMcq() {
//            return popularMcq;
//        }
//
//        public void setPopularMcq(List<PopularMcq> popularMcq) {
//            this.popularMcq = popularMcq;
//        }
//
//        public List<Leader> getLeaders() {
//            return leaders;
//        }
//
//        public void setLeaders(List<Leader> leaders) {
//            this.leaders = leaders;
//        }
//
//        public Progress getProgress() {
//            return progress;
//        }
//
//        public void setProgress(Progress progress) {
//            this.progress = progress;
//        }
//
//        public McqAnswerStatus getMcqAnswerStatus() {
//            return mcqAnswerStatus;
//        }
//
//        public void setMcqAnswerStatus(McqAnswerStatus mcqAnswerStatus) {
//            this.mcqAnswerStatus = mcqAnswerStatus;
//        }
//
//    }
//
//    public class Answer {
//
//        @SerializedName("option_value")
//        @Expose
//        private String optionValue;
//        @SerializedName("optionl2_value")
//        @Expose
//        private String optionl2Value;
//        @SerializedName("has_file")
//        @Expose
//        private Integer hasFile;
//        @SerializedName("file_name")
//        @Expose
//        private String fileName;
//
//        public String getOptionValue() {
//            return optionValue;
//        }
//
//        public void setOptionValue(String optionValue) {
//            this.optionValue = optionValue;
//        }
//
//        public String getOptionl2Value() {
//            return optionl2Value;
//        }
//
//        public void setOptionl2Value(String optionl2Value) {
//            this.optionl2Value = optionl2Value;
//        }
//
//        public Integer getHasFile() {
//            return hasFile;
//        }
//
//        public void setHasFile(Integer hasFile) {
//            this.hasFile = hasFile;
//        }
//
//        public String getFileName() {
//            return fileName;
//        }
//
//        public void setFileName(String fileName) {
//            this.fileName = fileName;
//        }
//
//    }
//
//    public class Leader {
//
//        @SerializedName("percentage")
//        @Expose
//        private String percentage;
//        @SerializedName("test_type")
//        @Expose
//        private String testType;
//        @SerializedName("name")
//        @Expose
//        private String name;
//        @SerializedName("user_profile_pic")
//        @Expose
//        private String userProfilePic;
//
//        public String getPercentage() {
//            return percentage;
//        }
//
//        public void setPercentage(String percentage) {
//            this.percentage = percentage;
//        }
//
//        public String getTestType() {
//            return testType;
//        }
//
//        public void setTestType(String testType) {
//            this.testType = testType;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getUserProfilePic() {
//            return userProfilePic;
//        }
//
//        public void setUserProfilePic(String userProfilePic) {
//            this.userProfilePic = userProfilePic;
//        }
//
//    }
//    public class LiveClass {
//
//        @SerializedName("class_marques")
//        @Expose
//        private String classMarques;
//
//        public String getClassMarques() {
//            return classMarques;
//        }
//
//        public void setClassMarques(String classMarques) {
//            this.classMarques = classMarques;
//        }
//
//    }
//
//    public class McqAnswerStatus {
//
//        @SerializedName("is_answer_for_mcq")
//        @Expose
//        private Integer isAnswerForMcq;
//        @SerializedName("selected_answer")
//        @Expose
//        private List<SelectedAnswer> selectedAnswer = null;
//
//        public Integer getIsAnswerForMcq() {
//            return isAnswerForMcq;
//        }
//
//        public void setIsAnswerForMcq(Integer isAnswerForMcq) {
//            this.isAnswerForMcq = isAnswerForMcq;
//        }
//
//        public List<SelectedAnswer> getSelectedAnswer() {
//            return selectedAnswer;
//        }
//
//        public void setSelectedAnswer(List<SelectedAnswer> selectedAnswer) {
//            this.selectedAnswer = selectedAnswer;
//        }
//
//    }
//
//    public class McqOfTheDay {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("subject_id")
//        @Expose
//        private Integer subjectId;
//        @SerializedName("topic_id")
//        @Expose
//        private Integer topicId;
//        @SerializedName("question_tags")
//        @Expose
//        private String questionTags;
//        @SerializedName("question_type")
//        @Expose
//        private String questionType;
//        @SerializedName("question")
//        @Expose
//        private String question;
//        @SerializedName("answers")
//        @Expose
//        private List<Answer> answers = null;
//        @SerializedName("question_file")
//        @Expose
//        private String questionFile;
//        @SerializedName("question_file_is_url")
//        @Expose
//        private Integer questionFileIsUrl;
//        @SerializedName("total_answers")
//        @Expose
//        private Integer totalAnswers;
//        @SerializedName("total_correct_answers")
//        @Expose
//        private Integer totalCorrectAnswers;
//        @SerializedName("correct_answers")
//        @Expose
//        private String correctAnswers;
//        @SerializedName("marks")
//        @Expose
//        private Integer marks;
//        @SerializedName("time_to_spend")
//        @Expose
//        private Integer timeToSpend;
//        @SerializedName("difficulty_level")
//        @Expose
//        private String difficultyLevel;
//        @SerializedName("hint")
//        @Expose
//        private String hint;
//        @SerializedName("explanation")
//        @Expose
//        private String explanation;
//        @SerializedName("explanation_file")
//        @Expose
//        private String explanationFile;
//        @SerializedName("status")
//        @Expose
//        private Integer status;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public Integer getSubjectId() {
//            return subjectId;
//        }
//
//        public void setSubjectId(Integer subjectId) {
//            this.subjectId = subjectId;
//        }
//
//        public Integer getTopicId() {
//            return topicId;
//        }
//
//        public void setTopicId(Integer topicId) {
//            this.topicId = topicId;
//        }
//
//        public String getQuestionTags() {
//            return questionTags;
//        }
//
//        public void setQuestionTags(String questionTags) {
//            this.questionTags = questionTags;
//        }
//
//        public String getQuestionType() {
//            return questionType;
//        }
//
//        public void setQuestionType(String questionType) {
//            this.questionType = questionType;
//        }
//
//        public String getQuestion() {
//            return question;
//        }
//
//        public void setQuestion(String question) {
//            this.question = question;
//        }
//
//        public List<Answer> getAnswers() {
//            return answers;
//        }
//
//        public void setAnswers(List<Answer> answers) {
//            this.answers = answers;
//        }
//
//        public String getQuestionFile() {
//            return questionFile;
//        }
//
//        public void setQuestionFile(String questionFile) {
//            this.questionFile = questionFile;
//        }
//
//        public Integer getQuestionFileIsUrl() {
//            return questionFileIsUrl;
//        }
//
//        public void setQuestionFileIsUrl(Integer questionFileIsUrl) {
//            this.questionFileIsUrl = questionFileIsUrl;
//        }
//
//        public Integer getTotalAnswers() {
//            return totalAnswers;
//        }
//
//        public void setTotalAnswers(Integer totalAnswers) {
//            this.totalAnswers = totalAnswers;
//        }
//
//        public Integer getTotalCorrectAnswers() {
//            return totalCorrectAnswers;
//        }
//
//        public void setTotalCorrectAnswers(Integer totalCorrectAnswers) {
//            this.totalCorrectAnswers = totalCorrectAnswers;
//        }
//
//        public String getCorrectAnswers() {
//            return correctAnswers;
//        }
//
//        public void setCorrectAnswers(String correctAnswers) {
//            this.correctAnswers = correctAnswers;
//        }
//
//        public Integer getMarks() {
//            return marks;
//        }
//
//        public void setMarks(Integer marks) {
//            this.marks = marks;
//        }
//
//        public Integer getTimeToSpend() {
//            return timeToSpend;
//        }
//
//        public void setTimeToSpend(Integer timeToSpend) {
//            this.timeToSpend = timeToSpend;
//        }
//
//        public String getDifficultyLevel() {
//            return difficultyLevel;
//        }
//
//        public void setDifficultyLevel(String difficultyLevel) {
//            this.difficultyLevel = difficultyLevel;
//        }
//
//        public String getHint() {
//            return hint;
//        }
//
//        public void setHint(String hint) {
//            this.hint = hint;
//        }
//
//        public String getExplanation() {
//            return explanation;
//        }
//
//        public void setExplanation(String explanation) {
//            this.explanation = explanation;
//        }
//
//        public String getExplanationFile() {
//            return explanationFile;
//        }
//
//        public void setExplanationFile(String explanationFile) {
//            this.explanationFile = explanationFile;
//        }
//
//        public Integer getStatus() {
//            return status;
//        }
//
//        public void setStatus(Integer status) {
//            this.status = status;
//        }
//
//    }
//
//    public class PopularMcq {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("course_id")
//        @Expose
//        private Integer courseId;
//        @SerializedName("subject_title")
//        @Expose
//        private String subjectTitle;
//        @SerializedName("subject_code")
//        @Expose
//        private String subjectCode;
//        @SerializedName("subj_of_year")
//        @Expose
//        private String subjOfYear;
//        @SerializedName("subject_image")
//        @Expose
//        private String subjectImage;
//        @SerializedName("slug")
//        @Expose
//        private String slug;
//        @SerializedName("maximum_marks")
//        @Expose
//        private Integer maximumMarks;
//        @SerializedName("pass_marks")
//        @Expose
//        private Integer passMarks;
//        @SerializedName("is_lab")
//        @Expose
//        private Integer isLab;
//        @SerializedName("is_elective_type")
//        @Expose
//        private Integer isElectiveType;
//        @SerializedName("status")
//        @Expose
//        private String status;
//        @SerializedName("created_at")
//        @Expose
//        private String createdAt;
//        @SerializedName("updated_at")
//        @Expose
//        private String updatedAt;
//        @SerializedName("show_in_front")
//        @Expose
//        private Integer showInFront;
//        @SerializedName("total_mcq")
//        @Expose
//        private Integer totalMcq;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public Integer getCourseId() {
//            return courseId;
//        }
//
//        public void setCourseId(Integer courseId) {
//            this.courseId = courseId;
//        }
//
//        public String getSubjectTitle() {
//            return subjectTitle;
//        }
//
//        public void setSubjectTitle(String subjectTitle) {
//            this.subjectTitle = subjectTitle;
//        }
//
//        public String getSubjectCode() {
//            return subjectCode;
//        }
//
//        public void setSubjectCode(String subjectCode) {
//            this.subjectCode = subjectCode;
//        }
//
//        public String getSubjOfYear() {
//            return subjOfYear;
//        }
//
//        public void setSubjOfYear(String subjOfYear) {
//            this.subjOfYear = subjOfYear;
//        }
//
//        public String getSubjectImage() {
//            return subjectImage;
//        }
//
//        public void setSubjectImage(String subjectImage) {
//            this.subjectImage = subjectImage;
//        }
//
//        public String getSlug() {
//            return slug;
//        }
//
//        public void setSlug(String slug) {
//            this.slug = slug;
//        }
//
//        public Integer getMaximumMarks() {
//            return maximumMarks;
//        }
//
//        public void setMaximumMarks(Integer maximumMarks) {
//            this.maximumMarks = maximumMarks;
//        }
//
//        public Integer getPassMarks() {
//            return passMarks;
//        }
//
//        public void setPassMarks(Integer passMarks) {
//            this.passMarks = passMarks;
//        }
//
//        public Integer getIsLab() {
//            return isLab;
//        }
//
//        public void setIsLab(Integer isLab) {
//            this.isLab = isLab;
//        }
//
//        public Integer getIsElectiveType() {
//            return isElectiveType;
//        }
//
//        public void setIsElectiveType(Integer isElectiveType) {
//            this.isElectiveType = isElectiveType;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public String getCreatedAt() {
//            return createdAt;
//        }
//
//        public void setCreatedAt(String createdAt) {
//            this.createdAt = createdAt;
//        }
//
//        public String getUpdatedAt() {
//            return updatedAt;
//        }
//
//        public void setUpdatedAt(String updatedAt) {
//            this.updatedAt = updatedAt;
//        }
//
//        public Integer getShowInFront() {
//            return showInFront;
//        }
//
//        public void setShowInFront(Integer showInFront) {
//            this.showInFront = showInFront;
//        }
//
//        public Integer getTotalMcq() {
//            return totalMcq;
//        }
//
//        public void setTotalMcq(Integer totalMcq) {
//            this.totalMcq = totalMcq;
//        }
//
//    }
//
//    public class Progress {
//
//        @SerializedName("progress_percentange")
//        @Expose
//        private Integer progressPercentange;
//
//        public Integer getProgressPercentange() {
//            return progressPercentange;
//        }
//
//        public void setProgressPercentange(Integer progressPercentange) {
//            this.progressPercentange = progressPercentange;
//        }
//
//    }
//
//    public class SelectedAnswer {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("mcq_of_the_day_id")
//        @Expose
//        private Integer mcqOfTheDayId;
//        @SerializedName("user_id")
//        @Expose
//        private Integer userId;
//        @SerializedName("create_at")
//        @Expose
//        private String createAt;
//        @SerializedName("answer")
//        @Expose
//        private Integer answer;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public Integer getMcqOfTheDayId() {
//            return mcqOfTheDayId;
//        }
//
//        public void setMcqOfTheDayId(Integer mcqOfTheDayId) {
//            this.mcqOfTheDayId = mcqOfTheDayId;
//        }
//
//        public Integer getUserId() {
//            return userId;
//        }
//
//        public void setUserId(Integer userId) {
//            this.userId = userId;
//        }
//
//        public String getCreateAt() {
//            return createAt;
//        }
//
//        public void setCreateAt(String createAt) {
//            this.createAt = createAt;
//        }
//
//        public Integer getAnswer() {
//            return answer;
//        }
//
//        public void setAnswer(Integer answer) {
//            this.answer = answer;
//        }
//
//    }
//
//    public class SuggestedVideo {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("schedule_id")
//        @Expose
//        private Integer scheduleId;
//        @SerializedName("is_play_for_free")
//        @Expose
//        private Integer isPlayForFree;
//        @SerializedName("is_popular")
//        @Expose
//        private Integer isPopular;
//        @SerializedName("faculty_id")
//        @Expose
//        private Integer facultyId;
//        @SerializedName("subject_id")
//        @Expose
//        private Integer subjectId;
//        @SerializedName("from_time")
//        @Expose
//        private String fromTime;
//        @SerializedName("to_time")
//        @Expose
//        private String toTime;
//        @SerializedName("date")
//        @Expose
//        private String date;
//        @SerializedName("duration")
//        @Expose
//        private String duration;
//        @SerializedName("create_at")
//        @Expose
//        private String createAt;
//        @SerializedName("update_at")
//        @Expose
//        private String updateAt;
//        @SerializedName("ip")
//        @Expose
//        private String ip;
//        @SerializedName("subject_title")
//        @Expose
//        private String subjectTitle;
//        @SerializedName("faculty_name")
//        @Expose
//        private String facultyName;
//        @SerializedName("full_url_video_file")
//        @Expose
//        private String fullUrlVideoFile;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public Integer getScheduleId() {
//            return scheduleId;
//        }
//
//        public void setScheduleId(Integer scheduleId) {
//            this.scheduleId = scheduleId;
//        }
//
//        public Integer getIsPlayForFree() {
//            return isPlayForFree;
//        }
//
//        public void setIsPlayForFree(Integer isPlayForFree) {
//            this.isPlayForFree = isPlayForFree;
//        }
//
//        public Integer getIsPopular() {
//            return isPopular;
//        }
//
//        public void setIsPopular(Integer isPopular) {
//            this.isPopular = isPopular;
//        }
//
//        public Integer getFacultyId() {
//            return facultyId;
//        }
//
//        public void setFacultyId(Integer facultyId) {
//            this.facultyId = facultyId;
//        }
//
//        public Integer getSubjectId() {
//            return subjectId;
//        }
//
//        public void setSubjectId(Integer subjectId) {
//            this.subjectId = subjectId;
//        }
//
//        public String getFromTime() {
//            return fromTime;
//        }
//
//        public void setFromTime(String fromTime) {
//            this.fromTime = fromTime;
//        }
//
//        public String getToTime() {
//            return toTime;
//        }
//
//        public void setToTime(String toTime) {
//            this.toTime = toTime;
//        }
//
//        public String getDate() {
//            return date;
//        }
//
//        public void setDate(String date) {
//            this.date = date;
//        }
//
//        public String getDuration() {
//            return duration;
//        }
//
//        public void setDuration(String duration) {
//            this.duration = duration;
//        }
//
//        public String getCreateAt() {
//            return createAt;
//        }
//
//        public void setCreateAt(String createAt) {
//            this.createAt = createAt;
//        }
//
//        public String getUpdateAt() {
//            return updateAt;
//        }
//
//        public void setUpdateAt(String updateAt) {
//            this.updateAt = updateAt;
//        }
//
//        public String getIp() {
//            return ip;
//        }
//
//        public void setIp(String ip) {
//            this.ip = ip;
//        }
//
//        public String getSubjectTitle() {
//            return subjectTitle;
//        }
//
//        public void setSubjectTitle(String subjectTitle) {
//            this.subjectTitle = subjectTitle;
//        }
//
//        public String getFacultyName() {
//            return facultyName;
//        }
//
//        public void setFacultyName(String facultyName) {
//            this.facultyName = facultyName;
//        }
//
//        public String getFullUrlVideoFile() {
//            return fullUrlVideoFile;
//        }
//
//        public void setFullUrlVideoFile(String fullUrlVideoFile) {
//            this.fullUrlVideoFile = fullUrlVideoFile;
//        }
//
//    }
//
//    public class TestSeries {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("title")
//        @Expose
//        private String title;
//        @SerializedName("slug")
//        @Expose
//        private String slug;
//        @SerializedName("dueration")
//        @Expose
//        private Integer dueration;
//        @SerializedName("category_id")
//        @Expose
//        private Integer categoryId;
//        @SerializedName("subject_id")
//        @Expose
//        private String subjectId;
//        @SerializedName("is_paid")
//        @Expose
//        private Integer isPaid;
//        @SerializedName("cost")
//        @Expose
//        private String cost;
//        @SerializedName("validity")
//        @Expose
//        private Integer validity;
//        @SerializedName("total_marks")
//        @Expose
//        private String totalMarks;
//        @SerializedName("having_negative_mark")
//        @Expose
//        private Integer havingNegativeMark;
//        @SerializedName("negative_mark")
//        @Expose
//        private String negativeMark;
//        @SerializedName("pass_percentage")
//        @Expose
//        private String passPercentage;
//        @SerializedName("tags")
//        @Expose
//        private String tags;
//        @SerializedName("publish_results_immediately")
//        @Expose
//        private Integer publishResultsImmediately;
//        @SerializedName("description")
//        @Expose
//        private String description;
//        @SerializedName("total_questions")
//        @Expose
//        private Integer totalQuestions;
//        @SerializedName("instructions_page_id")
//        @Expose
//        private Integer instructionsPageId;
//        @SerializedName("start_date")
//        @Expose
//        private String startDate;
//        @SerializedName("end_date")
//        @Expose
//        private String endDate;
//        @SerializedName("record_updated_by")
//        @Expose
//        private Integer recordUpdatedBy;
//        @SerializedName("created_at")
//        @Expose
//        private String createdAt;
//        @SerializedName("updated_at")
//        @Expose
//        private String updatedAt;
//        @SerializedName("show_in_front")
//        @Expose
//        private Integer showInFront;
//        @SerializedName("exam_type")
//        @Expose
//        private String examType;
//        @SerializedName("section_data")
//        @Expose
//        private String sectionData;
//        @SerializedName("has_language")
//        @Expose
//        private Integer hasLanguage;
//        @SerializedName("image")
//        @Expose
//        private String image;
//        @SerializedName("language_name")
//        @Expose
//        private String languageName;
//        @SerializedName("is_popular")
//        @Expose
//        private Integer isPopular;
//        @SerializedName("test_type")
//        @Expose
//        private String testType;
//        @SerializedName("sub_image")
//        @Expose
//        private String subImage;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getSlug() {
//            return slug;
//        }
//
//        public void setSlug(String slug) {
//            this.slug = slug;
//        }
//
//        public Integer getDueration() {
//            return dueration;
//        }
//
//        public void setDueration(Integer dueration) {
//            this.dueration = dueration;
//        }
//
//        public Integer getCategoryId() {
//            return categoryId;
//        }
//
//        public void setCategoryId(Integer categoryId) {
//            this.categoryId = categoryId;
//        }
//
//        public String getSubjectId() {
//            return subjectId;
//        }
//
//        public void setSubjectId(String subjectId) {
//            this.subjectId = subjectId;
//        }
//
//        public Integer getIsPaid() {
//            return isPaid;
//        }
//
//        public void setIsPaid(Integer isPaid) {
//            this.isPaid = isPaid;
//        }
//
//        public String getCost() {
//            return cost;
//        }
//
//        public void setCost(String cost) {
//            this.cost = cost;
//        }
//
//        public Integer getValidity() {
//            return validity;
//        }
//
//        public void setValidity(Integer validity) {
//            this.validity = validity;
//        }
//
//        public String getTotalMarks() {
//            return totalMarks;
//        }
//
//        public void setTotalMarks(String totalMarks) {
//            this.totalMarks = totalMarks;
//        }
//
//        public Integer getHavingNegativeMark() {
//            return havingNegativeMark;
//        }
//
//        public void setHavingNegativeMark(Integer havingNegativeMark) {
//            this.havingNegativeMark = havingNegativeMark;
//        }
//
//        public String getNegativeMark() {
//            return negativeMark;
//        }
//
//        public void setNegativeMark(String negativeMark) {
//            this.negativeMark = negativeMark;
//        }
//
//        public String getPassPercentage() {
//            return passPercentage;
//        }
//
//        public void setPassPercentage(String passPercentage) {
//            this.passPercentage = passPercentage;
//        }
//
//        public String getTags() {
//            return tags;
//        }
//
//        public void setTags(String tags) {
//            this.tags = tags;
//        }
//
//        public Integer getPublishResultsImmediately() {
//            return publishResultsImmediately;
//        }
//
//        public void setPublishResultsImmediately(Integer publishResultsImmediately) {
//            this.publishResultsImmediately = publishResultsImmediately;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public Integer getTotalQuestions() {
//            return totalQuestions;
//        }
//
//        public void setTotalQuestions(Integer totalQuestions) {
//            this.totalQuestions = totalQuestions;
//        }
//
//        public Integer getInstructionsPageId() {
//            return instructionsPageId;
//        }
//
//        public void setInstructionsPageId(Integer instructionsPageId) {
//            this.instructionsPageId = instructionsPageId;
//        }
//
//        public String getStartDate() {
//            return startDate;
//        }
//
//        public void setStartDate(String startDate) {
//            this.startDate = startDate;
//        }
//
//        public String getEndDate() {
//            return endDate;
//        }
//
//        public void setEndDate(String endDate) {
//            this.endDate = endDate;
//        }
//
//        public Integer getRecordUpdatedBy() {
//            return recordUpdatedBy;
//        }
//
//        public void setRecordUpdatedBy(Integer recordUpdatedBy) {
//            this.recordUpdatedBy = recordUpdatedBy;
//        }
//
//        public String getCreatedAt() {
//            return createdAt;
//        }
//
//        public void setCreatedAt(String createdAt) {
//            this.createdAt = createdAt;
//        }
//
//        public String getUpdatedAt() {
//            return updatedAt;
//        }
//
//        public void setUpdatedAt(String updatedAt) {
//            this.updatedAt = updatedAt;
//        }
//
//        public Integer getShowInFront() {
//            return showInFront;
//        }
//
//        public void setShowInFront(Integer showInFront) {
//            this.showInFront = showInFront;
//        }
//
//        public String getExamType() {
//            return examType;
//        }
//
//        public void setExamType(String examType) {
//            this.examType = examType;
//        }
//
//        public String getSectionData() {
//            return sectionData;
//        }
//
//        public void setSectionData(String sectionData) {
//            this.sectionData = sectionData;
//        }
//
//        public Integer getHasLanguage() {
//            return hasLanguage;
//        }
//
//        public void setHasLanguage(Integer hasLanguage) {
//            this.hasLanguage = hasLanguage;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public String getLanguageName() {
//            return languageName;
//        }
//
//        public void setLanguageName(String languageName) {
//            this.languageName = languageName;
//        }
//
//        public Integer getIsPopular() {
//            return isPopular;
//        }
//
//        public void setIsPopular(Integer isPopular) {
//            this.isPopular = isPopular;
//        }
//
//        public String getTestType() {
//            return testType;
//        }
//
//        public void setTestType(String testType) {
//            this.testType = testType;
//        }
//
//        public String getSubImage() {
//            return subImage;
//        }
//
//        public void setSubImage(String subImage) {
//            this.subImage = subImage;
//        }
//
//    }
//
}

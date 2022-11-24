package app.technotech.koncpt.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExamResultResponseModel implements Parcelable {


    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("record")
    @Expose
    private Record record;
    public final static Parcelable.Creator<ExamResultResponseModel> CREATOR = new Creator<ExamResultResponseModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ExamResultResponseModel createFromParcel(Parcel in) {
            return new ExamResultResponseModel(in);
        }

        public ExamResultResponseModel[] newArray(int size) {
            return (new ExamResultResponseModel[size]);
        }

    };

    protected ExamResultResponseModel(Parcel in) {
        this.status = ((int) in.readValue((int.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.record = ((Record) in.readValue((Record.class.getClassLoader())));
    }

    public ExamResultResponseModel() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(record);
    }

    public int describeContents() {
        return 0;
    }


    public class Quiz implements Parcelable {

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
        private Object subjectId;
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
        public final Parcelable.Creator<Quiz> CREATOR = new Creator<Quiz>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Quiz createFromParcel(Parcel in) {
                return new Quiz(in);
            }

            public Quiz[] newArray(int size) {
                return (new Quiz[size]);
            }

        };

        protected Quiz(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.slug = ((String) in.readValue((String.class.getClassLoader())));
            this.dueration = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.categoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.subjectId = ((Object) in.readValue((Object.class.getClassLoader())));
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
        }

        public Quiz() {
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

        public Object getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Object subjectId) {
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
        }

        public int describeContents() {
            return 0;
        }

    }


    public class Record implements Parcelable {

        @SerializedName("isUserTopper")
        @Expose
        private Boolean isUserTopper;
        @SerializedName("rank_details")
        @Expose
        private Boolean rankDetails;
        @SerializedName("quiz")
        @Expose
        private Quiz quiz;
        @SerializedName("active_class")
        @Expose
        private String activeClass;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("record")
        @Expose
        private Record_ record;
        public final Parcelable.Creator<Record> CREATOR = new Creator<Record>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Record createFromParcel(Parcel in) {
                return new Record(in);
            }

            public Record[] newArray(int size) {
                return (new Record[size]);
            }

        };

        protected Record(Parcel in) {
            this.isUserTopper = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.rankDetails = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.quiz = ((Quiz) in.readValue((Quiz.class.getClassLoader())));
            this.activeClass = ((String) in.readValue((String.class.getClassLoader())));
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.record = ((Record_) in.readValue((Record_.class.getClassLoader())));
        }

        public Record() {
        }

        public Boolean getIsUserTopper() {
            return isUserTopper;
        }

        public void setIsUserTopper(Boolean isUserTopper) {
            this.isUserTopper = isUserTopper;
        }

        public Boolean getRankDetails() {
            return rankDetails;
        }

        public void setRankDetails(Boolean rankDetails) {
            this.rankDetails = rankDetails;
        }

        public Quiz getQuiz() {
            return quiz;
        }

        public void setQuiz(Quiz quiz) {
            this.quiz = quiz;
        }

        public String getActiveClass() {
            return activeClass;
        }

        public void setActiveClass(String activeClass) {
            this.activeClass = activeClass;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Record_ getRecord() {
            return record;
        }

        public void setRecord(Record_ record) {
            this.record = record;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(isUserTopper);
            dest.writeValue(rankDetails);
            dest.writeValue(quiz);
            dest.writeValue(activeClass);
            dest.writeValue(title);
            dest.writeValue(record);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Record_ implements Parcelable {

        @SerializedName("quiz_id")
        @Expose
        private Integer quizId;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("marks_obtained")
        @Expose
        private Integer marksObtained;
        @SerializedName("total_marks")
        @Expose
        private String totalMarks;
        @SerializedName("percentage")
        @Expose
        private Double percentage;
        @SerializedName("exam_status")
        @Expose
        private String examStatus;
        @SerializedName("answers")
        @Expose
        private String answers;
        @SerializedName("subject_analysis")
        @Expose
        private String subjectAnalysis;
        @SerializedName("correct_answer_questions")
        @Expose
        private List<Integer> correctAnswerQuestions = null;
        @SerializedName("wrong_answer_questions")
        @Expose
        private List<Integer> wrongAnswerQuestions = null;
        @SerializedName("not_answered_questions")
        @Expose
        private List<Integer> notAnsweredQuestions = null;
        @SerializedName("time_spent_correct_answer_questions")
        @Expose
        private String timeSpentCorrectAnswerQuestions;
        @SerializedName("time_spent_wrong_answer_questions")
        @Expose
        private String timeSpentWrongAnswerQuestions;
        @SerializedName("time_spent_not_answered_questions")
        @Expose
        private String timeSpentNotAnsweredQuestions;
        @SerializedName("is_given")
        @Expose
        private Integer isGiven;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private Integer id;
        public final Parcelable.Creator<Record_> CREATOR = new Creator<Record_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Record_ createFromParcel(Parcel in) {
                return new Record_(in);
            }

            public Record_[] newArray(int size) {
                return (new Record_[size]);
            }

        };

        protected Record_(Parcel in) {
            this.quizId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.marksObtained = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalMarks = ((String) in.readValue((String.class.getClassLoader())));
            this.percentage = ((Double) in.readValue((Integer.class.getClassLoader())));
            this.examStatus = ((String) in.readValue((String.class.getClassLoader())));
            this.answers = ((String) in.readValue((String.class.getClassLoader())));
            this.subjectAnalysis = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.correctAnswerQuestions, (java.lang.Object.class.getClassLoader()));
            in.readList(this.wrongAnswerQuestions, (java.lang.Integer.class.getClassLoader()));
            in.readList(this.notAnsweredQuestions, (java.lang.Object.class.getClassLoader()));
            this.timeSpentCorrectAnswerQuestions = ((String) in.readValue((String.class.getClassLoader())));
            this.timeSpentWrongAnswerQuestions = ((String) in.readValue((String.class.getClassLoader())));
            this.timeSpentNotAnsweredQuestions = ((String) in.readValue((String.class.getClassLoader())));
            this.isGiven = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.slug = ((String) in.readValue((String.class.getClassLoader())));
            this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Record_() {
        }

        public Integer getQuizId() {
            return quizId;
        }

        public void setQuizId(Integer quizId) {
            this.quizId = quizId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getMarksObtained() {
            return marksObtained;
        }

        public void setMarksObtained(Integer marksObtained) {
            this.marksObtained = marksObtained;
        }

        public String getTotalMarks() {
            return totalMarks;
        }

        public void setTotalMarks(String totalMarks) {
            this.totalMarks = totalMarks;
        }

        public Double getPercentage() {
            return percentage;
        }

        public void setPercentage(Double percentage) {
            this.percentage = percentage;
        }

        public String getExamStatus() {
            return examStatus;
        }

        public void setExamStatus(String examStatus) {
            this.examStatus = examStatus;
        }

        public String getAnswers() {
            return answers;
        }

        public void setAnswers(String answers) {
            this.answers = answers;
        }

        public String getSubjectAnalysis() {
            return subjectAnalysis;
        }

        public void setSubjectAnalysis(String subjectAnalysis) {
            this.subjectAnalysis = subjectAnalysis;
        }

        public List<Integer> getCorrectAnswerQuestions() {
            return correctAnswerQuestions;
        }

        public void setCorrectAnswerQuestions(List<Integer> correctAnswerQuestions) {
            this.correctAnswerQuestions = correctAnswerQuestions;
        }

        public List<Integer> getWrongAnswerQuestions() {
            return wrongAnswerQuestions;
        }

        public void setWrongAnswerQuestions(List<Integer> wrongAnswerQuestions) {
            this.wrongAnswerQuestions = wrongAnswerQuestions;
        }

        public List<Integer> getNotAnsweredQuestions() {
            return notAnsweredQuestions;
        }

        public void setNotAnsweredQuestions(List<Integer> notAnsweredQuestions) {
            this.notAnsweredQuestions = notAnsweredQuestions;
        }

        public String getTimeSpentCorrectAnswerQuestions() {
            return timeSpentCorrectAnswerQuestions;
        }

        public void setTimeSpentCorrectAnswerQuestions(String timeSpentCorrectAnswerQuestions) {
            this.timeSpentCorrectAnswerQuestions = timeSpentCorrectAnswerQuestions;
        }

        public String getTimeSpentWrongAnswerQuestions() {
            return timeSpentWrongAnswerQuestions;
        }

        public void setTimeSpentWrongAnswerQuestions(String timeSpentWrongAnswerQuestions) {
            this.timeSpentWrongAnswerQuestions = timeSpentWrongAnswerQuestions;
        }

        public String getTimeSpentNotAnsweredQuestions() {
            return timeSpentNotAnsweredQuestions;
        }

        public void setTimeSpentNotAnsweredQuestions(String timeSpentNotAnsweredQuestions) {
            this.timeSpentNotAnsweredQuestions = timeSpentNotAnsweredQuestions;
        }

        public Integer getIsGiven() {
            return isGiven;
        }

        public void setIsGiven(Integer isGiven) {
            this.isGiven = isGiven;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(quizId);
            dest.writeValue(userId);
            dest.writeValue(marksObtained);
            dest.writeValue(totalMarks);
            dest.writeValue(percentage);
            dest.writeValue(examStatus);
            dest.writeValue(answers);
            dest.writeValue(subjectAnalysis);
            dest.writeList(correctAnswerQuestions);
            dest.writeList(wrongAnswerQuestions);
            dest.writeList(notAnsweredQuestions);
            dest.writeValue(timeSpentCorrectAnswerQuestions);
            dest.writeValue(timeSpentWrongAnswerQuestions);
            dest.writeValue(timeSpentNotAnsweredQuestions);
            dest.writeValue(isGiven);
            dest.writeValue(slug);
            dest.writeValue(updatedAt);
            dest.writeValue(createdAt);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }
}

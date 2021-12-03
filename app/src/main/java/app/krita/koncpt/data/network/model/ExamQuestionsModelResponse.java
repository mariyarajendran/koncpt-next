package app.technotech.koncpt.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExamQuestionsModelResponse implements Parcelable{

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("time_hours")
    @Expose
    private String timeHours;
    @SerializedName("time_minutes")
    @Expose
    private String timeMinutes;
    @SerializedName("time_seconds")
    @Expose
    private String timeSeconds;
    @SerializedName("atime_hours")
    @Expose
    private String atimeHours;
    @SerializedName("atime_minutes")
    @Expose
    private String atimeMinutes;
    @SerializedName("atime_seconds")
    @Expose
    private String atimeSeconds;
    @SerializedName("quiz")
    @Expose
    private Quiz quiz;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("current_state")
    @Expose
    private String currentState;
    @SerializedName("current_question_id")
    @Expose
    private String currentQuestionId;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("bookmarks")
    @Expose
    private List<Integer> bookmarks = null;
    @SerializedName("section_timings")
    @Expose
    private List<Object> sectionTimings = null;
    public final static Parcelable.Creator<ExamQuestionsModelResponse> CREATOR = new Creator<ExamQuestionsModelResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ExamQuestionsModelResponse createFromParcel(Parcel in) {
            return new ExamQuestionsModelResponse(in);
        }

        public ExamQuestionsModelResponse[] newArray(int size) {
            return (new ExamQuestionsModelResponse[size]);
        }

    }
            ;

    protected ExamQuestionsModelResponse(Parcel in) {
        this.status = ((int) in.readValue((int.class.getClassLoader())));
        this.timeHours = ((String) in.readValue((String.class.getClassLoader())));
        this.timeMinutes = ((String) in.readValue((String.class.getClassLoader())));
        this.timeSeconds = ((String) in.readValue((String.class.getClassLoader())));
        this.atimeHours = ((String) in.readValue((String.class.getClassLoader())));
        this.atimeMinutes = ((String) in.readValue((String.class.getClassLoader())));
        this.atimeSeconds = ((String) in.readValue((String.class.getClassLoader())));
        this.quiz = ((Quiz) in.readValue((Quiz.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.currentState = ((String) in.readValue((String.class.getClassLoader())));
        this.currentQuestionId = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.questions, (Question.class.getClassLoader()));
        in.readList(this.bookmarks, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.sectionTimings, (java.lang.Object.class.getClassLoader()));
    }

    public ExamQuestionsModelResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimeHours() {
        return timeHours;
    }

    public void setTimeHours(String timeHours) {
        this.timeHours = timeHours;
    }

    public String getTimeMinutes() {
        return timeMinutes;
    }

    public void setTimeMinutes(String timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    public String getTimeSeconds() {
        return timeSeconds;
    }

    public void setTimeSeconds(String timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public String getAtimeHours() {
        return atimeHours;
    }

    public void setAtimeHours(String atimeHours) {
        this.atimeHours = atimeHours;
    }

    public String getAtimeMinutes() {
        return atimeMinutes;
    }

    public void setAtimeMinutes(String atimeMinutes) {
        this.atimeMinutes = atimeMinutes;
    }

    public String getAtimeSeconds() {
        return atimeSeconds;
    }

    public void setAtimeSeconds(String atimeSeconds) {
        this.atimeSeconds = atimeSeconds;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getCurrentQuestionId() {
        return currentQuestionId;
    }

    public void setCurrentQuestionId(String currentQuestionId) {
        this.currentQuestionId = currentQuestionId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Integer> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Integer> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public List<Object> getSectionTimings() {
        return sectionTimings;
    }

    public void setSectionTimings(List<Object> sectionTimings) {
        this.sectionTimings = sectionTimings;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(timeHours);
        dest.writeValue(timeMinutes);
        dest.writeValue(timeSeconds);
        dest.writeValue(atimeHours);
        dest.writeValue(atimeMinutes);
        dest.writeValue(atimeSeconds);
        dest.writeValue(quiz);
        dest.writeValue(title);
        dest.writeValue(currentState);
        dest.writeValue(currentQuestionId);
        dest.writeList(questions);
        dest.writeList(bookmarks);
        dest.writeList(sectionTimings);
    }

    public int describeContents() {
        return 0;
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
        private int hasFile;
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
            this.hasFile = ((int) in.readValue((int.class.getClassLoader())));
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

        public int getHasFile() {
            return hasFile;
        }

        public void setHasFile(int hasFile) {
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


    public class Question implements Parcelable {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("subject_id")
        @Expose
        private String subjectId;
        @SerializedName("topic_id")
        @Expose
        private String topicId;
        @SerializedName("question_tags")
        @Expose
        private QuestionTags questionTags;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("question_type")
        @Expose
        private String questionType;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("question_file")
        @Expose
        private String questionFile;
        @SerializedName("question_file_is_url")
        @Expose
        private String questionFileIsUrl;
        @SerializedName("total_answers")
        @Expose
        private String totalAnswers;
        @SerializedName("answers")
        @Expose
        private List<Answer> answers = null;
        @SerializedName("total_correct_answers")
        @Expose
        private String totalCorrectAnswers;
        @SerializedName("correct_answers")
        @Expose
        private String correctAnswers;
        @SerializedName("marks")
        @Expose
        private String marks;
        @SerializedName("time_to_spend")
        @Expose
        private String timeToSpend;
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
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("question_l2")
        @Expose
        private String questionL2;
        @SerializedName("explanation_l2")
        @Expose
        private String explanationL2;
        @SerializedName("user_submitted")
        @Expose
        private String userSubmitted;

        private String selectedAnswer;

        private boolean attemptQuestion = false;

        public final Parcelable.Creator<Question> CREATOR = new Creator<Question>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Question createFromParcel(Parcel in) {
                return new Question(in);
            }

            public Question[] newArray(int size) {
                return (new Question[size]);
            }

        };

        protected Question(Parcel in) {
            this.id = ((int) in.readValue((int.class.getClassLoader())));
            this.subjectId = ((String) in.readValue((String.class.getClassLoader())));
            this.topicId = ((String) in.readValue((String.class.getClassLoader())));
            this.questionTags = ((QuestionTags) in.readValue((QuestionTags.class.getClassLoader())));
            this.slug = ((String) in.readValue((String.class.getClassLoader())));
            this.questionType = ((String) in.readValue((String.class.getClassLoader())));
            this.question = ((String) in.readValue((String.class.getClassLoader())));
            this.questionFile = ((String) in.readValue((String.class.getClassLoader())));
            this.questionFileIsUrl = ((String) in.readValue((String.class.getClassLoader())));
            this.totalAnswers = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.answers, (Answer.class.getClassLoader()));
            this.totalCorrectAnswers = ((String) in.readValue((String.class.getClassLoader())));
            this.correctAnswers = ((String) in.readValue((String.class.getClassLoader())));
            this.marks = ((String) in.readValue((String.class.getClassLoader())));
            this.timeToSpend = ((String) in.readValue((String.class.getClassLoader())));
            this.difficultyLevel = ((String) in.readValue((String.class.getClassLoader())));
            this.hint = ((String) in.readValue((String.class.getClassLoader())));
            this.explanation = ((String) in.readValue((String.class.getClassLoader())));
            this.explanationFile = ((String) in.readValue((String.class.getClassLoader())));
            this.status = ((String) in.readValue((String.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.questionL2 = ((String) in.readValue((String.class.getClassLoader())));
            this.explanationL2 = ((String) in.readValue((String.class.getClassLoader())));
            this.userSubmitted = ((String) in.readValue((String.class.getClassLoader())));
            this.selectedAnswer = ((String) in.readValue((String.class.getClassLoader())));
            this.attemptQuestion = ((boolean) in.readValue((String.class.getClassLoader())));
        }

        public Question() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public QuestionTags getQuestionTags() {
            return questionTags;
        }

        public void setQuestionTags(QuestionTags questionTags) {
            this.questionTags = questionTags;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
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

        public String getQuestionFile() {
            return questionFile;
        }

        public void setQuestionFile(String questionFile) {
            this.questionFile = questionFile;
        }

        public String getQuestionFileIsUrl() {
            return questionFileIsUrl;
        }

        public void setQuestionFileIsUrl(String questionFileIsUrl) {
            this.questionFileIsUrl = questionFileIsUrl;
        }

        public String getTotalAnswers() {
            return totalAnswers;
        }

        public void setTotalAnswers(String totalAnswers) {
            this.totalAnswers = totalAnswers;
        }

        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }

        public String getTotalCorrectAnswers() {
            return totalCorrectAnswers;
        }

        public void setTotalCorrectAnswers(String totalCorrectAnswers) {
            this.totalCorrectAnswers = totalCorrectAnswers;
        }

        public String getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers(String correctAnswers) {
            this.correctAnswers = correctAnswers;
        }

        public String getMarks() {
            return marks;
        }

        public void setMarks(String marks) {
            this.marks = marks;
        }

        public String getTimeToSpend() {
            return timeToSpend;
        }

        public void setTimeToSpend(String timeToSpend) {
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

        public String getQuestionL2() {
            return questionL2;
        }

        public void setQuestionL2(String questionL2) {
            this.questionL2 = questionL2;
        }

        public String getExplanationL2() {
            return explanationL2;
        }

        public void setExplanationL2(String explanationL2) {
            this.explanationL2 = explanationL2;
        }

        public String getUserSubmitted() {
            return userSubmitted;
        }

        public void setUserSubmitted(String userSubmitted) {
            this.userSubmitted = userSubmitted;
        }

        public String getSelectedAnswer() {
            return selectedAnswer;
        }

        public void setSelectedAnswer(String selectedAnswer) {
            this.selectedAnswer = selectedAnswer;
        }

        public boolean isAttemptQuestion() {
            return attemptQuestion;
        }

        public void setAttemptQuestion(boolean attemptQuestion) {
            this.attemptQuestion = attemptQuestion;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(subjectId);
            dest.writeValue(topicId);
            dest.writeValue(questionTags);
            dest.writeValue(slug);
            dest.writeValue(questionType);
            dest.writeValue(question);
            dest.writeValue(questionFile);
            dest.writeValue(questionFileIsUrl);
            dest.writeValue(totalAnswers);
            dest.writeList(answers);
            dest.writeValue(totalCorrectAnswers);
            dest.writeValue(correctAnswers);
            dest.writeValue(marks);
            dest.writeValue(timeToSpend);
            dest.writeValue(difficultyLevel);
            dest.writeValue(hint);
            dest.writeValue(explanation);
            dest.writeValue(explanationFile);
            dest.writeValue(status);
            dest.writeValue(createdAt);
            dest.writeValue(updatedAt);
            dest.writeValue(questionL2);
            dest.writeValue(explanationL2);
            dest.writeValue(userSubmitted);
            dest.writeValue(selectedAnswer);
            dest.writeValue(attemptQuestion);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class QuestionTags implements Parcelable {

        @SerializedName("sno")
        @Expose
        private int sno;
        @SerializedName("is_bookmarked")
        @Expose
        private int isBookmarked;
        public final Parcelable.Creator<QuestionTags> CREATOR = new Creator<QuestionTags>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public QuestionTags createFromParcel(Parcel in) {
                return new QuestionTags(in);
            }

            public QuestionTags[] newArray(int size) {
                return (new QuestionTags[size]);
            }

        };

        protected QuestionTags(Parcel in) {
            this.sno = ((int) in.readValue((int.class.getClassLoader())));
            this.isBookmarked = ((int) in.readValue((int.class.getClassLoader())));
        }

        public QuestionTags() {
        }

        public int getSno() {
            return sno;
        }

        public void setSno(int sno) {
            this.sno = sno;
        }

        public int getIsBookmarked() {
            return isBookmarked;
        }

        public void setIsBookmarked(int isBookmarked) {
            this.isBookmarked = isBookmarked;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(sno);
            dest.writeValue(isBookmarked);
        }

        public int describeContents() {
            return 0;
        }




    }

    public class Quiz implements Parcelable {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("dueration")
        @Expose
        private String dueration;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("subject_id")
        @Expose
        private Object subjectId;
        @SerializedName("is_paid")
        @Expose
        private String isPaid;
        @SerializedName("cost")
        @Expose
        private String cost;
        @SerializedName("validity")
        @Expose
        private String validity;
        @SerializedName("total_marks")
        @Expose
        private String totalMarks;
        @SerializedName("having_negative_mark")
        @Expose
        private String havingNegativeMark;
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
        private String publishResultsImmediately;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("total_questions")
        @Expose
        private String totalQuestions;
        @SerializedName("instructions_page_id")
        @Expose
        private String instructionsPageId;
        @SerializedName("start_date")
        @Expose
        private String startDate;
        @SerializedName("end_date")
        @Expose
        private String endDate;
        @SerializedName("record_updated_by")
        @Expose
        private String recordUpdatedBy;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("show_in_front")
        @Expose
        private String showInFront;
        @SerializedName("exam_type")
        @Expose
        private String examType;
        @SerializedName("section_data")
        @Expose
        private String sectionData;
        @SerializedName("has_language")
        @Expose
        private String hasLanguage;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("language_name")
        @Expose
        private String languageName;
        @SerializedName("is_popular")
        @Expose
        private String isPopular;
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
            this.id = ((int) in.readValue((int.class.getClassLoader())));
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.slug = ((String) in.readValue((String.class.getClassLoader())));
            this.dueration = ((String) in.readValue((String.class.getClassLoader())));
            this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
            this.subjectId = ((Object) in.readValue((Object.class.getClassLoader())));
            this.isPaid = ((String) in.readValue((String.class.getClassLoader())));
            this.cost = ((String) in.readValue((String.class.getClassLoader())));
            this.validity = ((String) in.readValue((String.class.getClassLoader())));
            this.totalMarks = ((String) in.readValue((String.class.getClassLoader())));
            this.havingNegativeMark = ((String) in.readValue((String.class.getClassLoader())));
            this.negativeMark = ((String) in.readValue((String.class.getClassLoader())));
            this.passPercentage = ((String) in.readValue((String.class.getClassLoader())));
            this.tags = ((String) in.readValue((String.class.getClassLoader())));
            this.publishResultsImmediately = ((String) in.readValue((String.class.getClassLoader())));
            this.description = ((String) in.readValue((String.class.getClassLoader())));
            this.totalQuestions = ((String) in.readValue((String.class.getClassLoader())));
            this.instructionsPageId = ((String) in.readValue((String.class.getClassLoader())));
            this.startDate = ((String) in.readValue((String.class.getClassLoader())));
            this.endDate = ((String) in.readValue((String.class.getClassLoader())));
            this.recordUpdatedBy = ((String) in.readValue((String.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.showInFront = ((String) in.readValue((String.class.getClassLoader())));
            this.examType = ((String) in.readValue((String.class.getClassLoader())));
            this.sectionData = ((String) in.readValue((String.class.getClassLoader())));
            this.hasLanguage = ((String) in.readValue((String.class.getClassLoader())));
            this.image = ((String) in.readValue((String.class.getClassLoader())));
            this.languageName = ((String) in.readValue((String.class.getClassLoader())));
            this.isPopular = ((String) in.readValue((String.class.getClassLoader())));
            this.testType = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Quiz() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public String getDueration() {
            return dueration;
        }

        public void setDueration(String dueration) {
            this.dueration = dueration;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public Object getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Object subjectId) {
            this.subjectId = subjectId;
        }

        public String getIsPaid() {
            return isPaid;
        }

        public void setIsPaid(String isPaid) {
            this.isPaid = isPaid;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public String getTotalMarks() {
            return totalMarks;
        }

        public void setTotalMarks(String totalMarks) {
            this.totalMarks = totalMarks;
        }

        public String getHavingNegativeMark() {
            return havingNegativeMark;
        }

        public void setHavingNegativeMark(String havingNegativeMark) {
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

        public String getPublishResultsImmediately() {
            return publishResultsImmediately;
        }

        public void setPublishResultsImmediately(String publishResultsImmediately) {
            this.publishResultsImmediately = publishResultsImmediately;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTotalQuestions() {
            return totalQuestions;
        }

        public void setTotalQuestions(String totalQuestions) {
            this.totalQuestions = totalQuestions;
        }

        public String getInstructionsPageId() {
            return instructionsPageId;
        }

        public void setInstructionsPageId(String instructionsPageId) {
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

        public String getRecordUpdatedBy() {
            return recordUpdatedBy;
        }

        public void setRecordUpdatedBy(String recordUpdatedBy) {
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

        public String getShowInFront() {
            return showInFront;
        }

        public void setShowInFront(String showInFront) {
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

        public String getHasLanguage() {
            return hasLanguage;
        }

        public void setHasLanguage(String hasLanguage) {
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

        public String getIsPopular() {
            return isPopular;
        }

        public void setIsPopular(String isPopular) {
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
}

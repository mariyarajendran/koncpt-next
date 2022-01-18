package app.technotech.koncpt.utils;

public enum EnumApiAction {
    action("action"),
    Registration("registration"),
    SendOtp("send_otp"),
    VerifyOtp("verify_otp"),
    ResendOtp("resend_otp"),
    states("states"),
    HomeScreen("home_screen"),
    SearchHome("search_home"),
    CheckEmail("check_email"),
    AllSubject("qbank_subject"),
    Topics("qbank_subject_topics"),
    QbankModuleQuestion("qbank_module_question"),
    AllPlans("all_plans"),
    PlanWiseLevel("plan_wise_level"),
    UserSubscription("user_level_subscription"),
    SaveBookMark("save_bookmark"),
    SubjectBookmarks("subject_bookmarks"),
    Bookmarks("bookmarks"),
    BookmarkSingleQuestion("bookmark_single_question"),
    BookmarkQuesSubWise("bookmarks_ques_sub_wise"),
    TopicBookMarkUser("topic_bookmark_user"),
    QBankResult("qbank_result"),
    TopicRatting("topic_ratting"),
    FAQS("faqs"),
    FacultyList("faculty_list"),
    QuestionTags("question_tags"),
    AllSubjectList("all_subject_list"),
    VideoSubject("video_subject"),
    VideoTopicList("video_topic_list"),
    TopicVideo("topic_video"),
    VideoAction("video_action"),
    VideoClassFile("video_class_file"),
    VideoRatting("video_ratting"),
    SaveTransaction("save_transaction"),
    AboutUs("about_us"),
    LiveClassList("live_classess_list"),
    LiveClassSubject("live_class_subject"),
    EnrollementForLiveClass("enrollement_for_live_class"),
    DailyHunt("daily_hunt"),
    DeleteBookmark("delete_bookmark"),
    ProfileDetail("profile_detail");
    private String strValue;

    public String getValue() {
        return strValue;
    }

    EnumApiAction() {

    }

    EnumApiAction(String value) {
        this.strValue = value;
    }
}
/*
 *
 * issue all and pause
 * need to add button
 * level design to buy now
 *
 * label-- level name bind --- completed
 * amount and buy now space  ---- completed
 * book mark pre fill  ----  completed
 *
 *
 * */



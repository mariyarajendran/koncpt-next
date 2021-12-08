package app.technotech.koncpt.remote;

import java.util.Map;

import app.technotech.koncpt.data.network.model.UserSubscriptionModel;
import app.technotech.koncpt.data.network.model.AboutusModel;
import app.technotech.koncpt.data.network.model.AnswerMcqOfTheDayModel;
import app.technotech.koncpt.data.network.model.BookmarkSaveModel;
import app.technotech.koncpt.data.network.model.BookmarkSingleQuestionModel;
import app.technotech.koncpt.data.network.model.BuyDetailsModel;
import app.technotech.koncpt.data.network.model.BuyNowModel;
import app.technotech.koncpt.data.network.model.ChangePhoneModule;
import app.technotech.koncpt.data.network.model.CheckEmailModel;
import app.technotech.koncpt.data.network.model.CollegeListModel;
import app.technotech.koncpt.data.network.model.CompleteVideoModel;
import app.technotech.koncpt.data.network.model.ContactusModel;
import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.data.network.model.DailyHuntModel;
import app.technotech.koncpt.data.network.model.DeleteBookmarkModel;
import app.technotech.koncpt.data.network.model.ExamQuestionsModelResponse;
import app.technotech.koncpt.data.network.model.ExamResultResponseModel;
import app.technotech.koncpt.data.network.model.FacebookResponseModel;
import app.technotech.koncpt.data.network.model.FacultyModel;
import app.technotech.koncpt.data.network.model.FaqModel;
import app.technotech.koncpt.data.network.model.GrandTestModelResponse;
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.data.network.model.LiveClassesListModel;
import app.technotech.koncpt.data.network.model.MCQQuestionResponse;
import app.technotech.koncpt.data.network.model.MessageModel;
import app.technotech.koncpt.data.network.model.ModelNotes;
import app.technotech.koncpt.data.network.model.NotesModel;
import app.technotech.koncpt.data.network.model.PaymentSuccessModel;
import app.technotech.koncpt.data.network.model.ProfileModel;
import app.technotech.koncpt.data.network.model.QBankModel;
import app.technotech.koncpt.data.network.model.QuestionBookmarkModel;
import app.technotech.koncpt.data.network.model.QuestionsResultResponse;
import app.technotech.koncpt.data.network.model.RatingModel;
import app.technotech.koncpt.data.network.model.RatingResponse;
import app.technotech.koncpt.data.network.model.ResultShowModel;
import app.technotech.koncpt.data.network.model.ReviewModelResponse;
import app.technotech.koncpt.data.network.model.SearchModel;
import app.technotech.koncpt.data.network.model.SlidesModel;
import app.technotech.koncpt.data.network.model.StateListModel;
import app.technotech.koncpt.data.network.model.SubjectBookmarkModel;
import app.technotech.koncpt.data.network.model.SubjectListDataModel;
import app.technotech.koncpt.data.network.model.SubjectListModel;
import app.technotech.koncpt.data.network.model.SubjectModel;
import app.technotech.koncpt.data.network.model.TNCModel;
import app.technotech.koncpt.data.network.model.TagsModel;
import app.technotech.koncpt.data.network.model.UpdatePasswordModel;
import app.technotech.koncpt.data.network.model.UserBookmarkTopicModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.data.network.model.UserModelLogin2;
import app.technotech.koncpt.data.network.model.VideoCompleteModel;
import app.technotech.koncpt.data.network.model.VideoModel;
import app.technotech.koncpt.data.network.model.WelcomeModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiConnections {

    @GET("api.php")
    public Call<StateListModel> getStateListResponse(@Query("action") String states);

    @FormUrlEncoded
    @POST("college-list")
    public Call<CollegeListModel> getCollegeListResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<WelcomeModel> getOtpResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<UserSubscriptionModel> getUserSubscriptionResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<WelcomeModel> getResendOtpResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<UserModelLogin> getRegisterResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<CheckEmailModel> getEmailCheckResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<UserModelLogin> getOTPVerificationResponse(@FieldMap Map<String, String> data);

    @GET("daily-hunt")
    public Call<DailyHuntModel> getDailyHuntResponse();

    @FormUrlEncoded
    @POST("login")
    public Call<UserModelLogin2> getSignInWithPasswordResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<QBankModel> getAllSubjectResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<SubjectModel> getTopicsBySubject(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<UserBookmarkTopicModel> getUserTopicModelResponse(@FieldMap Map<String, String> data);


    @FormUrlEncoded
    @POST("api.php")
    public Call<BookmarkSingleQuestionModel> getSingleAnswerBookmarkResponse(@FieldMap Map<String, String> data);


    @FormUrlEncoded
    @POST("api.php")
    public Call<MCQQuestionResponse> getMCQsResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("social_fb_login")
    public Call<FacebookResponseModel> getSocialLogin(@FieldMap Map<String, String> data);


    @FormUrlEncoded
    @POST("api.php")
    public Call<QuestionsResultResponse> getQuestionBankResult(@FieldMap Map<String, String> data);


    @FormUrlEncoded
    @POST("api.php")
    public Call<RatingResponse> getRatingResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("view-test-items")
    public Call<GrandTestModelResponse> getGrandTestResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("get-exam-questions")
    public Call<ExamQuestionsModelResponse> getExamQuestionResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("finish-exam")
    public Call<ExamResultResponseModel> getExamResultResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("finish_exam_result")
    public Call<ReviewModelResponse> getReviewQuestionsResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("test_result_show")
    public Call<ResultShowModel> getShowResultResponse(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("api.php")
    public Call<BookmarkSaveModel> getBookmarkSavedResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    public Call<SubjectBookmarkModel> getSubjectBookmarkResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    public Call<QuestionBookmarkModel> getQuestionsBookmarkResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    public Call<QuestionBookmarkModel> getAllBookmarks(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("bookmarks/delete")
    public Call<DeleteBookmarkModel> getDeleteBookmarkResponse(@FieldMap Map<String, String> params);

    @GET("api.php")
    public Call<SubjectListModel> getSubjectListResponse(@Query("action") String subjectList);

    @GET("api.php")
    public Call<TagsModel> getQuestionTagsResponse(@Query("action") String questionTags);

    @FormUrlEncoded
    @POST("filter-qbank")
    public Call<CustomExamModel> getCustomExamQuestions(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("filter-qbank")
    public Call<String> getCustomExamQuestionsString(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("api.php")
    public Call<HomeScreenModel> getHomeScreenResponse(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("answer_mcq_of_the_day")
    public Call<AnswerMcqOfTheDayModel> getMcqOfTheDayAnswerResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    public Call<SubjectListDataModel> getSubjectListDataResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    public Call<MessageModel> getSaveBookmarkResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    public Call<SearchModel> getSearchResponse(@FieldMap Map<String, String> params);


//    @FormUrlEncoded
//    @POST("video_topic_list")
//    Call<VideoModel> getVideoModelresponse(@FieldMap Map<String, String> params);
//
//    @FormUrlEncoded
//    @POST("video_topic_list")
//    Call<VideoModel> getCompleteModelresponse(@FieldMap Map<String, String> params);
//
//    @FormUrlEncoded
//    @POST("topic_video")
//    Call<NotesModel> getNotesModelresponse(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("api.php")
    public Call<BuyNowModel> getBuyNowModelresponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    Call<BuyDetailsModel> getBuyDetailsResponse(@FieldMap Map<String, String> params);

//
//    @FormUrlEncoded
//    @POST("save-transaction")
//    Call<PaymentSuccessModel> getPaymentresponse(@FieldMap Map<String, String> params);


    @Multipart
    @POST("users/edit")
    Call<UserModelLogin> getUpdateUserProfile(@Part("user_id") String user_id,
                                              @Part("fullname") String fullname,
                                              @Part("curr_acad_year") String curr_acad_year,
                                              @Part("phone") String phone,
                                              @Part MultipartBody.Part image);


    @FormUrlEncoded
    @POST("users/edit")
    Call<UserModelLogin> getUpdateUserProfile2(@FieldMap Map<String, String> params);


    @POST("users/edit")
    Call<UserModelLogin> getUpdateUserProfile3(@Body RequestBody file);

    @FormUrlEncoded
    @POST("api.php")
    Call<NotesModel> getNotesModelresponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    Call<SlidesModel> getModelSlideresponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    Call<ModelNotes> getModelNotesresponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    Call<VideoModel> getCompleteModelresponse(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("api.php")
    Call<VideoModel> getVideoModelresponse(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("api.php")
    Call<CompleteVideoModel> getCompleteResponse(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("save-transaction")
    Call<PaymentSuccessModel> getPaymentresponse(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("api.php")
    Call<UserModelLogin> getUserDetail(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("users/reset-password")
    Call<MessageModel> getForgotPasswordResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("change-phone")
    Call<ChangePhoneModule> getChageModelResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("update-password")
    Call<UpdatePasswordModel> getUpdateModelResponse(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("api.php")
    Call<RatingModel> getRatingModelResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    Call<VideoCompleteModel> getVideoModeResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("api.php")
    Call<ProfileModel> getProfileModelResponse(@FieldMap Map<String, String> params);


    @GET("api.php")
    public Call<FacultyModel> getFacultyResponse(@Query("action") String facultyList);

    @GET("about-us")
    public Call<AboutusModel> getAboutusResponse();

    @GET("api.php")
    public Call<FaqModel> getFaqResponse(@Query("action") String faqs);

    @GET("pages/terms-conditions")
    public Call<TNCModel> getTermsNConditionResponse();

    @FormUrlEncoded
    @POST("contact-us")
    Call<ContactusModel> getContactusResponse(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST
    public Call<String> getPauseResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("live_classess_list")
    public Call<LiveClassesListModel> getLiveClassListResponse(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("enrollement_for_live_class")
    public Call<MessageModel> getEnrollResponse(@FieldMap Map<String, String> params);


}

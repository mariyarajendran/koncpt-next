package app.technotech.koncpt.remote;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.Map;

import app.technotech.koncpt.data.network.model.UserSubscriptionModel;
import app.technotech.koncpt.utils.EnumApiAction;
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
import app.technotech.koncpt.utils.DebugLog;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class Repository {

    private static String TAG = "APIService";
    private String Response = "response";
    private String Error = "Error";

    ApiConnections connection = RetrofitClient.getRetrofitInstance().create(ApiConnections.class);

    public MutableLiveData<WelcomeModel> getOtpRepository(Map<String, String> params) {

        final MutableLiveData<WelcomeModel> data = new MutableLiveData<WelcomeModel>();

        connection.getOtpResponse(params).enqueue(new Callback<WelcomeModel>() {
            @Override
            public void onResponse(Call<WelcomeModel> call, retrofit2.Response<WelcomeModel> response) {

                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<WelcomeModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }


    public MutableLiveData<WelcomeModel> getResendOtpRepository(Map<String, String> params) {

        final MutableLiveData<WelcomeModel> data = new MutableLiveData<WelcomeModel>();

        connection.getResendOtpResponse(params).enqueue(new Callback<WelcomeModel>() {
            @Override
            public void onResponse(Call<WelcomeModel> call, retrofit2.Response<WelcomeModel> response) {

                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<WelcomeModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }


    public MutableLiveData<UserSubscriptionModel> getUserSubscriptionRepository(Map<String, String> params) {
        final MutableLiveData<UserSubscriptionModel> data = new MutableLiveData<UserSubscriptionModel>();
        connection.getUserSubscriptionResponse(params).enqueue(new Callback<UserSubscriptionModel>() {
            @Override
            public void onResponse(Call<UserSubscriptionModel> call, retrofit2.Response<UserSubscriptionModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserSubscriptionModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return data;
    }


    public MutableLiveData<StateListModel> getStateListRepository() {

        final MutableLiveData<StateListModel> data = new MutableLiveData<StateListModel>();

        connection.getStateListResponse(EnumApiAction.states.getValue()).enqueue(new Callback<StateListModel>() {
            @Override
            public void onResponse(Call<StateListModel> call, retrofit2.Response<StateListModel> response) {

                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<StateListModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }


    public MutableLiveData<CollegeListModel> getCollegeListRepository(Map<String, String> params) {

        final MutableLiveData<CollegeListModel> data = new MutableLiveData<CollegeListModel>();

        connection.getCollegeListResponse(params).enqueue(new Callback<CollegeListModel>() {
            @Override
            public void onResponse(Call<CollegeListModel> call, retrofit2.Response<CollegeListModel> response) {

                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<CollegeListModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }


    public MutableLiveData<UserModelLogin> getRegisterRepository(Map<String, String> params) {

        final MutableLiveData<UserModelLogin> data = new MutableLiveData<UserModelLogin>();

        connection.getRegisterResponse(params).enqueue(new Callback<UserModelLogin>() {
            @Override
            public void onResponse(Call<UserModelLogin> call, retrofit2.Response<UserModelLogin> response) {

                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<UserModelLogin> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }


    public MutableLiveData<CheckEmailModel> getMailCheckRepository(Map<String, String> params) {

        final MutableLiveData<CheckEmailModel> data = new MutableLiveData<CheckEmailModel>();

        connection.getEmailCheckResponse(params).enqueue(new Callback<CheckEmailModel>() {
            @Override
            public void onResponse(Call<CheckEmailModel> call, retrofit2.Response<CheckEmailModel> response) {

                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<CheckEmailModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }


    public MutableLiveData<UserModelLogin> getOtpVerificationRepository(Map<String, String> params) {

        final MutableLiveData<UserModelLogin> data = new MutableLiveData<UserModelLogin>();

        connection.getOTPVerificationResponse(params).enqueue(new Callback<UserModelLogin>() {
            @Override
            public void onResponse(Call<UserModelLogin> call, retrofit2.Response<UserModelLogin> response) {

                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<UserModelLogin> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }


    public MutableLiveData<UserModelLogin2> getSignInWithRepository(Map<String, String> params) {

        final MutableLiveData<UserModelLogin2> data = new MutableLiveData<UserModelLogin2>();

        connection.getSignInWithPasswordResponse(params).enqueue(new Callback<UserModelLogin2>() {
            @Override
            public void onResponse(Call<UserModelLogin2> call, retrofit2.Response<UserModelLogin2> response) {

                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<UserModelLogin2> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }


    public MutableLiveData<DailyHuntModel> getDailyHuntRepository() {

        final MutableLiveData<DailyHuntModel> data = new MutableLiveData<>();

        connection.getDailyHuntResponse().enqueue(new Callback<DailyHuntModel>() {
            @Override
            public void onResponse(Call<DailyHuntModel> call, retrofit2.Response<DailyHuntModel> response) {

                try {
                    data.setValue(response.body());
                    Log.e("onResponse :", response.body().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DailyHuntModel> call, Throwable t) {
                try {
                    data.setValue(null);
                    Log.e("onResponse :", t.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<QBankModel> getQbankRepository(Map<String, String> params) {

        final MutableLiveData<QBankModel> data = new MutableLiveData<>();

        connection.getAllSubjectResponse(params).enqueue(new Callback<QBankModel>() {
            @Override
            public void onResponse(Call<QBankModel> call, retrofit2.Response<QBankModel> response) {
                try {
                    String json = new Gson().toJson(response.body());
                    DebugLog.e("Response  => " + json);
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<QBankModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<SubjectModel> getSubjectDataRepository(Map<String, String> params) {
        final MutableLiveData<SubjectModel> data = new MutableLiveData<SubjectModel>();
        connection.getTopicsBySubject(params).enqueue(new Callback<SubjectModel>() {
            @Override
            public void onResponse(Call<SubjectModel> call, retrofit2.Response<SubjectModel> response) {
                try {
                    String jsondata = new Gson().toJson(response.body());
                    DebugLog.e("Response Status => " + jsondata);
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SubjectModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return data;
    }


    public MutableLiveData<UserBookmarkTopicModel> getUserBookmarkTopicRepository(Map<String, String> params) {
        final MutableLiveData<UserBookmarkTopicModel> data = new MutableLiveData<UserBookmarkTopicModel>();
        connection.getUserTopicModelResponse(params).enqueue(new Callback<UserBookmarkTopicModel>() {
            @Override
            public void onResponse(Call<UserBookmarkTopicModel> call, retrofit2.Response<UserBookmarkTopicModel> response) {
                try {

                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserBookmarkTopicModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return data;
    }


    public MutableLiveData<BookmarkSingleQuestionModel> getSingleAnswerBookmarkRepository(Map<String, String> params) {
        final MutableLiveData<BookmarkSingleQuestionModel> data = new MutableLiveData<BookmarkSingleQuestionModel>();
        connection.getSingleAnswerBookmarkResponse(params).enqueue(new Callback<BookmarkSingleQuestionModel>() {
            @Override
            public void onResponse(Call<BookmarkSingleQuestionModel> call, retrofit2.Response<BookmarkSingleQuestionModel> response) {
                try {

                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BookmarkSingleQuestionModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return data;
    }


    public MutableLiveData<MCQQuestionResponse> getMCQsRepository(Map<String, String> params) {

        final MutableLiveData<MCQQuestionResponse> data = new MutableLiveData<MCQQuestionResponse>();

        connection.getMCQsResponse(params).enqueue(new Callback<MCQQuestionResponse>() {
            @Override
            public void onResponse(Call<MCQQuestionResponse> call, retrofit2.Response<MCQQuestionResponse> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<MCQQuestionResponse> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<FacebookResponseModel> getSocialRepository(Map<String, String> params) {

        final MutableLiveData<FacebookResponseModel> data = new MutableLiveData<>();

        connection.getSocialLogin(params).enqueue(new Callback<FacebookResponseModel>() {
            @Override
            public void onResponse(Call<FacebookResponseModel> call, retrofit2.Response<FacebookResponseModel> response) {

                try {

                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FacebookResponseModel> call, Throwable t) {


                try {

                    data.setValue(null);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        });

        return data;
    }


    public MutableLiveData<QuestionsResultResponse> getResultModel(Map<String, String> params) {

        final MutableLiveData<QuestionsResultResponse> data = new MutableLiveData<QuestionsResultResponse>();


        connection.getQuestionBankResult(params).enqueue(new Callback<QuestionsResultResponse>() {
            @Override
            public void onResponse(Call<QuestionsResultResponse> call, retrofit2.Response<QuestionsResultResponse> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<QuestionsResultResponse> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<RatingResponse> getRatingRepository(Map<String, String> params) {

        final MutableLiveData<RatingResponse> data = new MutableLiveData<RatingResponse>();

        connection.getRatingResponse(params).enqueue(new Callback<RatingResponse>() {
            @Override
            public void onResponse(Call<RatingResponse> call, retrofit2.Response<RatingResponse> response) {
                try {

                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RatingResponse> call, Throwable t) {

                try {

                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<GrandTestModelResponse> getGrandTestRepository(Map<String, String> params) {

        final MutableLiveData<GrandTestModelResponse> data = new MutableLiveData<GrandTestModelResponse>();

        connection.getGrandTestResponse(params).enqueue(new Callback<GrandTestModelResponse>() {
            @Override
            public void onResponse(Call<GrandTestModelResponse> call, retrofit2.Response<GrandTestModelResponse> response) {
                try {

                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GrandTestModelResponse> call, Throwable t) {
                try {

                    data.setValue(null);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<ExamQuestionsModelResponse> getExamQuestionRepository(Map<String, String> params) {

        final MutableLiveData<ExamQuestionsModelResponse> data = new MutableLiveData<ExamQuestionsModelResponse>();

        connection.getExamQuestionResponse(params).enqueue(new Callback<ExamQuestionsModelResponse>() {
            @Override
            public void onResponse(Call<ExamQuestionsModelResponse> call, retrofit2.Response<ExamQuestionsModelResponse> response) {
                try {

                    data.setValue(response.body());

                    String jsonData = new Gson().toJson(response.body());
                    DebugLog.e("Data => " + jsonData);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ExamQuestionsModelResponse> call, Throwable t) {
                try {

                    data.setValue(null);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<ExamResultResponseModel> getExamResultRepository(Map<String, String> params) {

        final MutableLiveData<ExamResultResponseModel> data = new MutableLiveData<ExamResultResponseModel>();

        connection.getExamResultResponse(params).enqueue(new Callback<ExamResultResponseModel>() {
            @Override
            public void onResponse(Call<ExamResultResponseModel> call, retrofit2.Response<ExamResultResponseModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ExamResultResponseModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return data;
    }

    public MutableLiveData<ReviewModelResponse> getReviewQuestionRepository(Map<String, String> params) {

        final MutableLiveData<ReviewModelResponse> data = new MutableLiveData<ReviewModelResponse>();
        connection.getReviewQuestionsResponse(params).enqueue(new Callback<ReviewModelResponse>() {
            @Override
            public void onResponse(Call<ReviewModelResponse> call, retrofit2.Response<ReviewModelResponse> response) {
                try {
                    data.setValue(response.body());
                    DebugLog.e("JSON => " + new Gson().toJson(response.body()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ReviewModelResponse> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<ResultShowModel> getResultShowRepository(Map<String, String> params) {

        final MutableLiveData<ResultShowModel> data = new MutableLiveData<ResultShowModel>();

        connection.getShowResultResponse(params).enqueue(new Callback<ResultShowModel>() {
            @Override
            public void onResponse(Call<ResultShowModel> call, retrofit2.Response<ResultShowModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResultShowModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return data;
    }

    public MutableLiveData<BookmarkSaveModel> getBookmarkSavedModel(Map<String, String> params) {
        final MutableLiveData<BookmarkSaveModel> data = new MutableLiveData<BookmarkSaveModel>();

        connection.getBookmarkSavedResponse(params).enqueue(new Callback<BookmarkSaveModel>() {
            @Override
            public void onResponse(Call<BookmarkSaveModel> call, retrofit2.Response<BookmarkSaveModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BookmarkSaveModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<SubjectBookmarkModel> getSubjectBookmarkRepository(Map<String, String> params) {

        final MutableLiveData<SubjectBookmarkModel> data = new MutableLiveData<SubjectBookmarkModel>();

        connection.getSubjectBookmarkResponse(params).enqueue(new Callback<SubjectBookmarkModel>() {
            @Override
            public void onResponse(Call<SubjectBookmarkModel> call, retrofit2.Response<SubjectBookmarkModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SubjectBookmarkModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<QuestionBookmarkModel> getQuestionsBookmarkRepository(Map<String, String> params) {

        final MutableLiveData<QuestionBookmarkModel> data = new MutableLiveData<QuestionBookmarkModel>();

        connection.getQuestionsBookmarkResponse(params).enqueue(new Callback<QuestionBookmarkModel>() {
            @Override
            public void onResponse(Call<QuestionBookmarkModel> call, retrofit2.Response<QuestionBookmarkModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<QuestionBookmarkModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<QuestionBookmarkModel> getAllBookmarkRepository(Map<String, String> params) {

        final MutableLiveData<QuestionBookmarkModel> data = new MutableLiveData<QuestionBookmarkModel>();

        connection.getAllBookmarks(params).enqueue(new Callback<QuestionBookmarkModel>() {
            @Override
            public void onResponse(Call<QuestionBookmarkModel> call, retrofit2.Response<QuestionBookmarkModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<QuestionBookmarkModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<DeleteBookmarkModel> getDeleteBookmarkRepository(Map<String, String> params) {

        final MutableLiveData<DeleteBookmarkModel> data = new MutableLiveData<DeleteBookmarkModel>();

        connection.getDeleteBookmarkResponse(params).enqueue(new Callback<DeleteBookmarkModel>() {
            @Override
            public void onResponse(Call<DeleteBookmarkModel> call, retrofit2.Response<DeleteBookmarkModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DeleteBookmarkModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<SubjectListModel> getSubjectListRepository() {

        final MutableLiveData<SubjectListModel> data = new MutableLiveData<SubjectListModel>();

        connection.getSubjectListResponse(EnumApiAction.AllSubjectList.getValue()).enqueue(new Callback<SubjectListModel>() {
            @Override
            public void onResponse(Call<SubjectListModel> call, retrofit2.Response<SubjectListModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SubjectListModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<TagsModel> getQuestionTagsRepository() {

        final MutableLiveData<TagsModel> data = new MutableLiveData<TagsModel>();

        connection.getQuestionTagsResponse(EnumApiAction.QuestionTags.getValue()).enqueue(new Callback<TagsModel>() {
            @Override
            public void onResponse(Call<TagsModel> call, retrofit2.Response<TagsModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<TagsModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<CustomExamModel> getCustomExamQuestionsRepository(Map<String, String> params) {

        final MutableLiveData<CustomExamModel> data = new MutableLiveData<CustomExamModel>();

        connection.getCustomExamQuestions(params).enqueue(new Callback<CustomExamModel>() {
            @Override
            public void onResponse(Call<CustomExamModel> call, retrofit2.Response<CustomExamModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CustomExamModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<String> getCustomExamQuestionsRepositoryString(Map<String, String> params) {

        final MutableLiveData<String> data = new MutableLiveData<String>();

        connection.getCustomExamQuestionsString(params).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<HomeScreenModel> getHomeScreenRepository(Map<String, String> params) {

        final MutableLiveData<HomeScreenModel> data = new MutableLiveData<HomeScreenModel>();

        connection.getHomeScreenResponse(params).enqueue(new Callback<HomeScreenModel>() {
            @Override
            public void onResponse(Call<HomeScreenModel> call, retrofit2.Response<HomeScreenModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<HomeScreenModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<AnswerMcqOfTheDayModel> getAnswerOfTheDayMcqRepository(Map<String, String> params) {

        final MutableLiveData<AnswerMcqOfTheDayModel> data = new MutableLiveData<AnswerMcqOfTheDayModel>();

        connection.getMcqOfTheDayAnswerResponse(params).enqueue(new Callback<AnswerMcqOfTheDayModel>() {
            @Override
            public void onResponse(Call<AnswerMcqOfTheDayModel> call, retrofit2.Response<AnswerMcqOfTheDayModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AnswerMcqOfTheDayModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<SubjectListDataModel> getSubjectListDataRepository(Map<String, String> params) {

        final MutableLiveData<SubjectListDataModel> data = new MutableLiveData<SubjectListDataModel>();

        connection.getSubjectListDataResponse(params).enqueue(new Callback<SubjectListDataModel>() {
            @Override
            public void onResponse(Call<SubjectListDataModel> call, retrofit2.Response<SubjectListDataModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SubjectListDataModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<MessageModel> getSaveBookmarkRepository(Map<String, String> params) {

        final MutableLiveData<MessageModel> data = new MutableLiveData<MessageModel>();

        connection.getSaveBookmarkResponse(params).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, retrofit2.Response<MessageModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<SearchModel> getSearchRepository(Map<String, String> params) {

        final MutableLiveData<SearchModel> data = new MutableLiveData<SearchModel>();

        connection.getSearchResponse(params).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, retrofit2.Response<SearchModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<BuyNowModel> getBuyModelRespository(Map<String, String> params) {

        final MutableLiveData<BuyNowModel> data = new MutableLiveData<>();

        connection.getBuyNowModelresponse(params).enqueue(new Callback<BuyNowModel>() {
            @Override
            public void onResponse(Call<BuyNowModel> call, retrofit2.Response<BuyNowModel> response) {

                try {
                    data.setValue(response.body());
                    Log.e("onResponse :", response.body().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BuyNowModel> call, Throwable t) {
                try {
                    data.setValue(null);
                    Log.e("onResponse :", t.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<BuyDetailsModel> getBuyDetailsRepository(Map<String, String> params) {

        final MutableLiveData<BuyDetailsModel> data = new MutableLiveData<>();

        connection.getBuyDetailsResponse(params).enqueue(new Callback<BuyDetailsModel>() {


            @Override
            public void onResponse(Call<BuyDetailsModel> call, retrofit2.Response<BuyDetailsModel> response) {
                try {
                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BuyDetailsModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }
//
//
//    public MutableLiveData<PaymentSuccessModel> getPaymentSuccessRepository(Map<String, String> params){
//
//        final MutableLiveData<PaymentSuccessModel> data = new MutableLiveData<>();
//
//        connection.getPaymentresponse(params).enqueue(new Callback<PaymentSuccessModel>(){
//
//            @Override
//            public void onResponse(Call<PaymentSuccessModel> call, retrofit2.Response<PaymentSuccessModel> response) {
//                try{
//                    data.setValue(response.body());
//                }catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PaymentSuccessModel> call, Throwable t) {
//                try{
//                    data.setValue(null);
//                }catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        return data;
//    }


    public MutableLiveData<UserModelLogin> getUpdateUserProfileRepository(String user_id, String name, String acadmic_year, String mobile, MultipartBody.Part image) {

        final MutableLiveData<UserModelLogin> data = new MutableLiveData<>();

        connection.getUpdateUserProfile(user_id, name, acadmic_year, mobile, image).enqueue(new Callback<UserModelLogin>() {


            @Override
            public void onResponse(Call<UserModelLogin> call, retrofit2.Response<UserModelLogin> response) {
                try {
                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserModelLogin> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<UserModelLogin> getUpdateUserProfileRepository2(Map<String, String> params) {
        final MutableLiveData<UserModelLogin> data = new MutableLiveData<>();
        connection.getUpdateUserProfile2(params).enqueue(new Callback<UserModelLogin>() {


            @Override
            public void onResponse(Call<UserModelLogin> call, retrofit2.Response<UserModelLogin> response) {
                try {
                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserModelLogin> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<UserModelLogin> getUpdateUserProfileRepository3(RequestBody body) {
        final MutableLiveData<UserModelLogin> data = new MutableLiveData<>();
        connection.getUpdateUserProfile3(body).enqueue(new Callback<UserModelLogin>() {


            @Override
            public void onResponse(Call<UserModelLogin> call, retrofit2.Response<UserModelLogin> response) {
                try {
                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserModelLogin> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<NotesModel> getNotesModelRespository(Map<String, String> params) {

        final MutableLiveData<NotesModel> data = new MutableLiveData<>();

        connection.getNotesModelresponse(params).enqueue(new Callback<NotesModel>() {


            @Override
            public void onResponse(Call<NotesModel> call, retrofit2.Response<NotesModel> response) {
                try {
                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NotesModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<SlidesModel> getModelSlidesResponse(Map<String, String> params) {

        final MutableLiveData<SlidesModel> data = new MutableLiveData<>();

        connection.getModelSlideresponse(params).enqueue(new Callback<SlidesModel>() {

            @Override
            public void onResponse(Call<SlidesModel> call, retrofit2.Response<SlidesModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SlidesModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<ModelNotes> getModelNotesResponse(Map<String, String> params) {

        final MutableLiveData<ModelNotes> data = new MutableLiveData<>();

        connection.getModelNotesresponse(params).enqueue(new Callback<ModelNotes>() {

            @Override
            public void onResponse(Call<ModelNotes> call, retrofit2.Response<ModelNotes> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ModelNotes> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<VideoModel> getCompleteModelRepository(Map<String, String> params) {
        final MutableLiveData<VideoModel> data = new MutableLiveData<>();
        connection.getCompleteModelresponse(params).enqueue(new Callback<VideoModel>() {

            @Override
            public void onResponse(Call<VideoModel> call, retrofit2.Response<VideoModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<VideoModel> getVideoModelRepository(Map<String, String> params) {

        final MutableLiveData<VideoModel> data = new MutableLiveData<VideoModel>();

        connection.getVideoModelresponse(params).enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, retrofit2.Response<VideoModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    //video complete

    public MutableLiveData<CompleteVideoModel> getCompleVideoResponse(Map<String, String> params) {

        final MutableLiveData<CompleteVideoModel> data = new MutableLiveData<>();

        connection.getCompleteResponse(params).enqueue(new Callback<CompleteVideoModel>() {

            @Override
            public void onResponse(Call<CompleteVideoModel> call, retrofit2.Response<CompleteVideoModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CompleteVideoModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<VideoModel> getAllModelRepository(Map<String, String> params) {

        final MutableLiveData<VideoModel> data = new MutableLiveData<>();

        connection.getCompleteModelresponse(params).enqueue(new Callback<VideoModel>() {

            @Override
            public void onResponse(Call<VideoModel> call, retrofit2.Response<VideoModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<VideoModel> getUnattemptedModelRepository(Map<String, String> params) {

        final MutableLiveData<VideoModel> data = new MutableLiveData<>();

        connection.getCompleteModelresponse(params).enqueue(new Callback<VideoModel>() {

            @Override
            public void onResponse(Call<VideoModel> call, retrofit2.Response<VideoModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

//    public MutableLiveData<CompleteVideoModel> getCompleVideoResponse(Map<String, String> params){
//
//        final MutableLiveData<CompleteVideoModel> data = new MutableLiveData<>();
//
//        connection.getCompleteResponse(params).enqueue(new Callback<CompleteVideoModel>(){
//
//            @Override
//            public void onResponse(Call<CompleteVideoModel> call, retrofit2.Response<CompleteVideoModel> response) {
//                try{
//                    data.setValue(response.body());
//                }catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CompleteVideoModel> call, Throwable t) {
//                try{
//                    data.setValue(null);
//                }catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        return data;
//    }


    public MutableLiveData<PaymentSuccessModel> getPaymentSuccessRepository(Map<String, String> params) {

        final MutableLiveData<PaymentSuccessModel> data = new MutableLiveData<>();

        connection.getPaymentresponse(params).enqueue(new Callback<PaymentSuccessModel>() {

            @Override
            public void onResponse(Call<PaymentSuccessModel> call, retrofit2.Response<PaymentSuccessModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PaymentSuccessModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<UserModelLogin> getUserRepository(Map<String, String> params) {

        final MutableLiveData<UserModelLogin> data = new MutableLiveData<>();

        connection.getUserDetail(params).enqueue(new Callback<UserModelLogin>() {

            @Override
            public void onResponse(Call<UserModelLogin> call, retrofit2.Response<UserModelLogin> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserModelLogin> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return data;
    }


    public MutableLiveData<MessageModel> getForgotPasswordRepository(Map<String, String> params) {

        final MutableLiveData<MessageModel> data = new MutableLiveData<>();

        connection.getForgotPasswordResponse(params).enqueue(new Callback<MessageModel>() {

            @Override
            public void onResponse(Call<MessageModel> call, retrofit2.Response<MessageModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<ChangePhoneModule> getChangePhoneResponse(Map<String, String> params) {

        final MutableLiveData<ChangePhoneModule> data = new MutableLiveData<>();

        connection.getChageModelResponse(params).enqueue(new Callback<ChangePhoneModule>() {

            @Override
            public void onResponse(Call<ChangePhoneModule> call, retrofit2.Response<ChangePhoneModule> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ChangePhoneModule> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<UpdatePasswordModel> getUpadatePasswordResponse(Map<String, String> params) {

        final MutableLiveData<UpdatePasswordModel> data = new MutableLiveData<>();

        connection.getUpdateModelResponse(params).enqueue(new Callback<UpdatePasswordModel>() {

            @Override
            public void onResponse(Call<UpdatePasswordModel> call, retrofit2.Response<UpdatePasswordModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UpdatePasswordModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<VideoCompleteModel> getVideoModelCompleteResponse(Map<String, String> params) {

        final MutableLiveData<VideoCompleteModel> data = new MutableLiveData<>();

        connection.getVideoModeResponse(params).enqueue(new Callback<VideoCompleteModel>() {

            @Override
            public void onResponse(Call<VideoCompleteModel> call, retrofit2.Response<VideoCompleteModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VideoCompleteModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<RatingModel> getRatingModelResponse(Map<String, String> params) {

        final MutableLiveData<RatingModel> data = new MutableLiveData<>();

        connection.getRatingModelResponse(params).enqueue(new Callback<RatingModel>() {

            @Override
            public void onResponse(Call<RatingModel> call, retrofit2.Response<RatingModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RatingModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    public MutableLiveData<ProfileModel> getProfileModelResponse(Map<String, String> params) {

        final MutableLiveData<ProfileModel> data = new MutableLiveData<>();

        connection.getProfileModelResponse(params).enqueue(new Callback<ProfileModel>() {

            @Override
            public void onResponse(Call<ProfileModel> call, retrofit2.Response<ProfileModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    //Faculty
    public MutableLiveData<FacultyModel> getFacultyModelResponse() {

        final MutableLiveData<FacultyModel> data = new MutableLiveData<>();

        connection.getFacultyResponse(EnumApiAction.FacultyList.getValue()).enqueue(new Callback<FacultyModel>() {
            @Override
            public void onResponse(Call<FacultyModel> call, retrofit2.Response<FacultyModel> response) {

                try {
                    data.setValue(response.body());
                    Log.e("onResponse :", response.body().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FacultyModel> call, Throwable t) {
                try {
                    data.setValue(null);
                    Log.e("onResponse :", t.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    //about us

    public MutableLiveData<AboutusModel> getAboutusModelRepsonse() {

        final MutableLiveData<AboutusModel> data = new MutableLiveData<>();

        connection.getAboutusResponse(EnumApiAction.AboutUs.getValue()).enqueue(new Callback<AboutusModel>() {
            @Override
            public void onResponse(Call<AboutusModel> call, retrofit2.Response<AboutusModel> response) {

                try {
                    data.setValue(response.body());
                    Log.e("onResponse :", response.body().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AboutusModel> call, Throwable t) {
                try {
                    data.setValue(null);
                    Log.e("onResponse :", t.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    //faq response

    public MutableLiveData<FaqModel> getFaqModelResponse() {

        final MutableLiveData<FaqModel> data = new MutableLiveData<>();

        connection.getFaqResponse(EnumApiAction.FAQS.getValue()).enqueue(new Callback<FaqModel>() {
            @Override
            public void onResponse(Call<FaqModel> call, retrofit2.Response<FaqModel> response) {

                try {
                    data.setValue(response.body());
                    Log.e("onResponse :", response.body().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FaqModel> call, Throwable t) {
                try {
                    data.setValue(null);
                    Log.e("onResponse :", t.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }


    //faq response

    public MutableLiveData<TNCModel> getTermsNConditionRepository() {

        final MutableLiveData<TNCModel> data = new MutableLiveData<>();

        connection.getTermsNConditionResponse().enqueue(new Callback<TNCModel>() {
            @Override
            public void onResponse(Call<TNCModel> call, retrofit2.Response<TNCModel> response) {

                try {
                    data.setValue(response.body());
                    Log.e("onResponse :", response.body().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<TNCModel> call, Throwable t) {
                try {
                    data.setValue(null);
                    Log.e("onResponse :", t.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    //contact us
    public MutableLiveData<ContactusModel> getContactusResponse(Map<String, String> params) {

        final MutableLiveData<ContactusModel> data = new MutableLiveData<>();

        connection.getContactusResponse(params).enqueue(new Callback<ContactusModel>() {

            @Override
            public void onResponse(Call<ContactusModel> call, retrofit2.Response<ContactusModel> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ContactusModel> call, Throwable t) {
                try {
                    data.setValue(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return data;
    }

    public MutableLiveData<LiveClassesListModel> getLiveClassRepository(Map<String, String> params) {

        final MutableLiveData<LiveClassesListModel> data = new MutableLiveData<>();

        connection.getLiveClassListResponse(params).enqueue(new Callback<LiveClassesListModel>() {
            @Override
            public void onResponse(Call<LiveClassesListModel> call, retrofit2.Response<LiveClassesListModel> response) {

                try {

                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LiveClassesListModel> call, Throwable t) {

                try {
                    data.setValue(null);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }


    public MutableLiveData<MessageModel> getEnrollRepository(Map<String, String> params) {

        final MutableLiveData<MessageModel> data = new MutableLiveData<>();

        connection.getEnrollResponse(params).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, retrofit2.Response<MessageModel> response) {

                try {

                    data.setValue(response.body());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {

                try {
                    data.setValue(null);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        return data;
    }

}

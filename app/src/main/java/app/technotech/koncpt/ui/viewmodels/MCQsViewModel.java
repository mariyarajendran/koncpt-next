package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.BookmarkSingleQuestionModel;
import app.technotech.koncpt.data.network.model.MCQQuestionResponse;
import app.technotech.koncpt.data.network.model.MessageModel;
import app.technotech.koncpt.data.network.model.QuestionsResultResponse;
import app.technotech.koncpt.data.network.model.RatingResponse;
import app.technotech.koncpt.data.network.model.UserBookmarkTopicModel;
import app.technotech.koncpt.remote.Repository;

public class MCQsViewModel extends ViewModel {


    private Repository repository = new Repository();

    public MutableLiveData<MCQQuestionResponse> getMCQs(Map<String, String> params) {
        return repository.getMCQsRepository(params);
    }

    public MutableLiveData<QuestionsResultResponse> submiMCQResult(Map<String, String> params) {
        return repository.getResultModel(params);
    }


    public MutableLiveData<RatingResponse> submitrating(Map<String, String> params) {
        return repository.getRatingRepository(params);
    }

    public MutableLiveData<MessageModel> saveBookmarks(Map<String, String> params) {
        return repository.getSaveBookmarkRepository(params);
    }

    public MutableLiveData<UserBookmarkTopicModel> getBookmarkTopicData(Map<String, String> params) {
        return repository.getUserBookmarkTopicRepository(params);
    }

    public MutableLiveData<BookmarkSingleQuestionModel> getSingleAnswerBookmark(Map<String, String> params) {
        return repository.getSingleAnswerBookmarkRepository(params);
    }

}

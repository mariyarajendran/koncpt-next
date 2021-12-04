package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.BookmarkSaveModel;
import app.technotech.koncpt.data.network.model.DeleteBookmarkModel;
import app.technotech.koncpt.data.network.model.QuestionBookmarkModel;
import app.technotech.koncpt.data.network.model.SubjectBookmarkModel;
import app.technotech.koncpt.remote.Repository;

public class BookmarkViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<BookmarkSaveModel> addItemBookmark(Map<String,String> params){
        return repository.getBookmarkSavedModel(params);
    }

    public MutableLiveData<SubjectBookmarkModel> getSubjectData(Map<String, String> params){
        return repository.getSubjectBookmarkRepository(params);
    }

    public MutableLiveData<QuestionBookmarkModel> getBookmarkedQuestions(Map<String, String> params){
        return repository.getQuestionsBookmarkRepository(params);
    }

    public MutableLiveData<QuestionBookmarkModel> getAllBookmarks(Map<String, String> params){
        return repository.getAllBookmarkRepository(params);
    }

    public MutableLiveData<DeleteBookmarkModel> deleteBookmark(Map<String, String> params){
        return repository.getDeleteBookmarkRepository(params);
    }

}

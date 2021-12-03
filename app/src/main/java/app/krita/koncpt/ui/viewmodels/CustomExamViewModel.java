package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.MessageModel;
import app.technotech.koncpt.remote.Repository;

public class CustomExamViewModel extends ViewModel {

    Repository repository = new Repository();

    public MutableLiveData<MessageModel> saveBookmarks(Map<String, String> params) {
        return repository.getSaveBookmarkRepository(params);
    }
}

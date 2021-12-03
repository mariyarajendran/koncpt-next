package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.SearchModel;
import app.technotech.koncpt.remote.Repository;

public class SearchViewModel extends ViewModel {


    private Repository repository = new Repository();

    public MutableLiveData<SearchModel> getSearchResult(Map<String, String> params) {
        return repository.getSearchRepository(params);
    }
}

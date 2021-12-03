package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.ResultShowModel;
import app.technotech.koncpt.remote.Repository;

public class ResultShowViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<ResultShowModel> getResultData(Map<String, String> params){
        return repository.getResultShowRepository(params);
    }

}

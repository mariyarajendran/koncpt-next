package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.CompleteVideoModel;
import app.technotech.koncpt.remote.Repository;

public class CompleteVideoViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<CompleteVideoModel> getCompleteData(Map<String, String> params){
        return repository.getCompleVideoResponse(params);
    }


}
package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;


import app.technotech.koncpt.data.network.model.VideoModel;
import app.technotech.koncpt.remote.Repository;

public class LiveCompleteViewModel extends ViewModel {

    //completed
    private Repository repository = new Repository();

    public MutableLiveData<VideoModel> getCompleteData(Map<String, String> params){
        return repository.getCompleteModelRepository(params);
    }

}

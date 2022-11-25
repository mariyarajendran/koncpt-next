package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.VideoModel;
import app.technotech.koncpt.remote.Repository;

public class LiveVideoViewModel extends ViewModel {

    //paused
    private Repository repository = new Repository();

    public MutableLiveData<VideoModel> getVideoData(Map<String, String> params){
        return repository.getVideoModelRepository(params);
    }



}

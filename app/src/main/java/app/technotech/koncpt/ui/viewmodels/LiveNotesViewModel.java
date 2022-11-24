package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.NotesModel;
import app.technotech.koncpt.data.network.model.RatingModel;
import app.technotech.koncpt.data.network.model.VideoCompleteModel;
import app.technotech.koncpt.remote.Repository;

public class LiveNotesViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<NotesModel> getNoteData(Map<String, String> params){
        return repository.getNotesModelRespository(params);
    }

    public MutableLiveData<VideoCompleteModel> getCompleteData(Map<String, String> params){
        return repository.getVideoModelCompleteResponse(params);
    }


    public MutableLiveData<RatingModel> getRatingData(Map<String, String> params){
        return repository.getRatingModelResponse(params);
    }


}
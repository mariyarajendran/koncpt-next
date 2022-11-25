package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.ModelNotes;
import app.technotech.koncpt.remote.Repository;

public class ModelNotesViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<ModelNotes> getCompleteData(Map<String, String> params){
        return repository.getModelNotesResponse(params);
    }


}

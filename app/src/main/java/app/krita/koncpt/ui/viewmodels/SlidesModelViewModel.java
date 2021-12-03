package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.SlidesModel;
import app.technotech.koncpt.remote.Repository;

public class SlidesModelViewModel  extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<SlidesModel> getCompleteData(Map<String, String> params){
        return repository.getModelSlidesResponse(params);
    }


}

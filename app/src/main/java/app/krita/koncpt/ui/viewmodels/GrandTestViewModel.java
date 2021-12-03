package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.GrandTestModelResponse;
import app.technotech.koncpt.remote.Repository;

public class GrandTestViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<GrandTestModelResponse> getGrandTestData(Map<String,String> params){

        return repository.getGrandTestRepository(params);
    }
}

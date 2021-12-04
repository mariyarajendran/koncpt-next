package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.ChangePhoneModule;
import app.technotech.koncpt.remote.Repository;

public class ChangePhoneViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<ChangePhoneModule> getCompleteData(Map<String, String> params){
        return repository.getChangePhoneResponse(params);
    }




}

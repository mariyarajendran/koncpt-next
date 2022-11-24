package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.UpdatePasswordModel;
import app.technotech.koncpt.remote.Repository;

public class UpdatePasswordViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<UpdatePasswordModel> getCompleteData(Map<String, String> params){
        return repository.getUpadatePasswordResponse(params);
    }

}

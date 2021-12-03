package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.BuyNowModel;
import app.technotech.koncpt.data.network.model.ContactusModel;
import app.technotech.koncpt.data.network.model.SubjectListDataModel;
import app.technotech.koncpt.data.network.model.VideoModel;
import app.technotech.koncpt.remote.Repository;

public class ContactusViewModel extends ViewModel {

    //completed
    private Repository repository = new Repository();

    public MutableLiveData<ContactusModel> getContactUsModel(Map<String, String> params){
        return repository.getContactusResponse(params);
    }
}

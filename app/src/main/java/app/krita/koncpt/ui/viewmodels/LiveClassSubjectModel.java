package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.ProfileModel;
import app.technotech.koncpt.data.network.model.SubjectListDataModel;
import app.technotech.koncpt.remote.Repository;

public class LiveClassSubjectModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<SubjectListDataModel> getSubjectData(Map<String, String> params){
        return repository.getSubjectListDataRepository(params);
    }


    public MutableLiveData<ProfileModel> getProfileData(Map<String, String> params){
        return repository.getProfileModelResponse(params);
    }


}

package app.technotech.koncpt.ui.viewmodels;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.SubjectModel;
import app.technotech.koncpt.remote.Repository;

public class SubjectViewModel extends ViewModel {

    private Repository repository = new Repository();
    public MutableLiveData<SubjectModel> getSubjectModel(Map<String, String> params){
        return repository.getSubjectDataRepository(params);
    }



}

package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.technotech.koncpt.data.network.model.FacultyModel;
import app.technotech.koncpt.remote.Repository;

public class FacultyViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<FacultyModel> getFacultyData() {
        return repository.getFacultyModelResponse();
    }


}

package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.CheckEmailModel;
import app.technotech.koncpt.data.network.model.CollegeListModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.data.network.model.StateListModel;
import app.technotech.koncpt.remote.Repository;

public class RegisterViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<StateListModel> getStateList() {
        return repository.getStateListRepository();
    }


    public MutableLiveData<CollegeListModel> getCollegeList(Map<String, String> params) {
        return repository.getCollegeListRepository(params);
    }

    public MutableLiveData<UserModelLogin> getUserRegister(Map<String, String> params) {
        return repository.getRegisterRepository(params);
    }

    public MutableLiveData<CheckEmailModel> getMailCheck(Map<String, String> params) {
        return repository.getMailCheckRepository(params);
    }

}

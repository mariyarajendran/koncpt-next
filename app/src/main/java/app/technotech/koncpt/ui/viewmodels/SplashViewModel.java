package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.ProfileModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.remote.Repository;

public class SplashViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<UserModelLogin> getUserData(Map<String, String> params){
        return repository.getUserRepository(params);
    }

}

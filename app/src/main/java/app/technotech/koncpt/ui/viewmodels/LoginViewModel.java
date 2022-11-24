package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.FacebookResponseModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.data.network.model.UserModelLogin2;
import app.technotech.koncpt.remote.Repository;

public class LoginViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<UserModelLogin2> SignInWithPassword(Map<String, String> params) {
        return repository.getSignInWithRepository(params);
    }

    public MutableLiveData<UserModelLogin> getUserData(Map<String, String> params){
        return repository.getUserRepository(params);
    }

    public MutableLiveData<FacebookResponseModel> fbSocialLogin(Map<String, String> params){
        return repository.getSocialRepository(params);
    }

}

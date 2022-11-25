package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.data.network.model.WelcomeModel;
import app.technotech.koncpt.remote.Repository;

public class OtpViewModel extends ViewModel {

    private Repository repository = new Repository();
    public MutableLiveData<WelcomeModel> sendOtp(Map<String, String> params) {
        return repository.getOtpRepository(params);
    }


    public MutableLiveData<WelcomeModel> reSendOtp(Map<String, String> params) {
        return repository.getResendOtpRepository(params);
    }


    public MutableLiveData<UserModelLogin> verifyOTP(Map<String, String> params) {
        return repository.getOtpVerificationRepository(params);
    }

    public MutableLiveData<UserModelLogin> getUserData(Map<String, String> params){
        return repository.getUserRepository(params);
    }

}

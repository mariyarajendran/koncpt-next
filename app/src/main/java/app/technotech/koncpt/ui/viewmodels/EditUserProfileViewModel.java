package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.remote.Repository;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditUserProfileViewModel extends ViewModel {


    private Repository repository = new Repository();

    public MutableLiveData<UserModelLogin> getUpdateProfile(String user_id, String name, String acadmic_year, String mobile, MultipartBody.Part image) {
        return repository.getUpdateUserProfileRepository(user_id, name, acadmic_year, mobile, image);
    }

    public MutableLiveData<UserModelLogin> getUpdateProfile2(Map<String, String> params) {
        return repository.getUpdateUserProfileRepository2(params);
    }

    public MutableLiveData<UserModelLogin> getUpdateProfile3(RequestBody body) {
        return repository.getUpdateUserProfileRepository3(body);
    }

}

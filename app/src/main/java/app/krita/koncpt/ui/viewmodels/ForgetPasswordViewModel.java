package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.MessageModel;
import app.technotech.koncpt.remote.Repository;

public class ForgetPasswordViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<MessageModel> sendPasswordMail(Map<String, String> params){
        return repository.getForgotPasswordRepository(params);
    }
}

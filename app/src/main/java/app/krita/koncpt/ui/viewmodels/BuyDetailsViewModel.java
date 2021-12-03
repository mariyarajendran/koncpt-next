package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.BuyDetailsModel;
import app.technotech.koncpt.data.network.model.PaymentSuccessModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.remote.Repository;

public class BuyDetailsViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<BuyDetailsModel> getBuyDetailsData(Map<String, String> params) {
        return repository.getBuyDetailsRepository(params);
    }

    public MutableLiveData<PaymentSuccessModel> getCompleteData(Map<String, String> params){
        return repository.getPaymentSuccessRepository(params);
    }

    public MutableLiveData<UserModelLogin> getUserData(Map<String, String> params){
        return repository.getUserRepository(params);
    }
}
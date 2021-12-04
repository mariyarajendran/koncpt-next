package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.BuyNowModel;
import app.technotech.koncpt.remote.Repository;

public class BuyNowViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<BuyNowModel> getHuntData(Map<String, String> params) {
        return repository.getBuyModelRespository(params);
    }
}
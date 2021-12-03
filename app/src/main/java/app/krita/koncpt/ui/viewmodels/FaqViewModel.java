package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.technotech.koncpt.data.network.model.FaqModel;
import app.technotech.koncpt.remote.Repository;

public class FaqViewModel extends ViewModel {


    private Repository repository = new Repository();

    public MutableLiveData<FaqModel> getFaqData() {
        return repository.getFaqModelResponse();
    }



}

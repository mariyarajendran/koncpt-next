package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.QBankModel;
import app.technotech.koncpt.remote.Repository;

public class QuestionsBankViewModel extends ViewModel {

    private Repository repository =  new Repository();

    public MutableLiveData<QBankModel> getQBank(Map<String, String> params){
        return repository.getQbankRepository(params);
    }

}

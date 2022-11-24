package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.technotech.koncpt.data.network.model.TNCModel;
import app.technotech.koncpt.remote.Repository;

public class TermsNConditionViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<TNCModel> getTermsNConditionsData(){
        return repository.getTermsNConditionRepository();
    }
}

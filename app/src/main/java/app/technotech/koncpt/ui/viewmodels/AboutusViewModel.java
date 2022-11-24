package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.technotech.koncpt.data.network.model.AboutusModel;
import app.technotech.koncpt.remote.Repository;

public class AboutusViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<AboutusModel> getAboutusData() {
        return repository.getAboutusModelRepsonse();
    }

}

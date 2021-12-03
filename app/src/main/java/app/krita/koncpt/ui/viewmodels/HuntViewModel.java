package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.technotech.koncpt.data.network.model.DailyHuntModel;
import app.technotech.koncpt.remote.Repository;

public class HuntViewModel extends ViewModel {


    private Repository repository = new Repository();

    public MutableLiveData<DailyHuntModel> getHuntData(){
        return repository.getDailyHuntRepository();
    }

}

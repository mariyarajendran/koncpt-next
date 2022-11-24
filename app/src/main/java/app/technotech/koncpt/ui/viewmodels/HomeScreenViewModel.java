package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.AnswerMcqOfTheDayModel;
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.remote.Repository;

public class HomeScreenViewModel extends ViewModel {
    private Repository repository = new Repository();

    private MutableLiveData<HomeScreenModel> data =null;

    public MutableLiveData<HomeScreenModel> getHoeScreenData(Map<String, String> params){
        return repository.getHomeScreenRepository(params);
    }

    public MutableLiveData<AnswerMcqOfTheDayModel> submitAnswerOfTheDay(Map<String, String> params){
        return repository.getAnswerOfTheDayMcqRepository(params);
    }


    public void setHomeData(HomeScreenModel homeScreenModel){
        data.setValue(homeScreenModel);
    }


    public MutableLiveData<HomeScreenModel> getHomeData(){
        return data;
    }

}

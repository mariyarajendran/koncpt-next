package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

import app.technotech.koncpt.data.network.model.BuyDetailsModel;
import app.technotech.koncpt.data.network.model.LiveClassesListModel;
import app.technotech.koncpt.data.network.model.MessageModel;
import app.technotech.koncpt.data.network.model.RecordedDataModel;
import app.technotech.koncpt.remote.Repository;

public class OnLiveViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<LiveClassesListModel> getOnLiveData(Map<String, String> params){
        return repository.getLiveClassRepository(params);
    }

    public MutableLiveData<MessageModel> getEnrollClass(Map<String, String> params){
        return repository.getEnrollRepository(params);
    }

    public MutableLiveData<RecordedDataModel> getRecordedVideo(Map<String, String> params) {
        return repository.getRecordedVideo(params);
    }
}

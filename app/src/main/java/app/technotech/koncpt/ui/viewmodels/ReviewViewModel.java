package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.ReviewModelResponse;
import app.technotech.koncpt.remote.Repository;

public class ReviewViewModel extends ViewModel {


    private Repository repository = new Repository();

    public MutableLiveData<ReviewModelResponse> getReviewQuestions(Map<String, String> params){
        return repository.getReviewQuestionRepository(params);
    }

}

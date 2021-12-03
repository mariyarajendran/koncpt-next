package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.ExamQuestionsModelResponse;
import app.technotech.koncpt.data.network.model.ExamResultResponseModel;
import app.technotech.koncpt.remote.Repository;

public class ExamQuestionsViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<ExamQuestionsModelResponse> getExamQuestions(Map<String, String> params) {
        return repository.getExamQuestionRepository(params);
    }

    public MutableLiveData<ExamResultResponseModel> getExamResult(Map<String, String> params){
        return repository.getExamResultRepository(params);
    }
}

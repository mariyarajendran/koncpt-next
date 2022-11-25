package app.technotech.koncpt.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.data.network.model.SubjectListModel;
import app.technotech.koncpt.data.network.model.TagsModel;
import app.technotech.koncpt.remote.Repository;

public class CustomModuleViewModel extends ViewModel {

    private Repository repository = new Repository();

    public MutableLiveData<SubjectListModel> getSubjectList() {
        return repository.getSubjectListRepository();
    }

    public MutableLiveData<TagsModel> getTags() {
        return repository.getQuestionTagsRepository();
    }

    public MutableLiveData<CustomExamModel> getCustomModelData(Map<String, String> params) {
        return repository.getCustomExamQuestionsRepository(params);
    }


    public MutableLiveData<String> getCustomModelDataString(Map<String, String> params) {
        return repository.getCustomExamQuestionsRepositoryString(params);
    }

}

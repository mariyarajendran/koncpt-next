package app.technotech.koncpt.data.network.model;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class SubjectQuestionBankGroup extends ExpandableGroup<SubjectModel.ModuleDatum> {


    public SubjectQuestionBankGroup(String title, List<SubjectModel.ModuleDatum> items) {
        super(title, items);
    }

    protected SubjectQuestionBankGroup(Parcel in) {
        super(in);
    }
}

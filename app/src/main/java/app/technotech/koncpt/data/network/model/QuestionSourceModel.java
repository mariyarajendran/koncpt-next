package app.technotech.koncpt.data.network.model;

public class QuestionSourceModel {

    private String id;
    private String subject_name;
    private boolean isSelected;


    public QuestionSourceModel(){}

    public QuestionSourceModel(String id, String subject_name, boolean isSelected) {
        this.id = id;
        this.subject_name = subject_name;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

package app.technotech.koncpt.data.network.model;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.R;

public class LiveClassesModel {

    private String id;
    private int image_class;
    private String subject_name;
    private String watched_status;


    public LiveClassesModel(String id, int image_class, String subject_name, String watched_status) {
        this.id = id;
        this.image_class = image_class;
        this.subject_name = subject_name;
        this.watched_status = watched_status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage_class() {
        return image_class;
    }

    public void setImage_class(int image_class) {
        this.image_class = image_class;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getWatched_status() {
        return watched_status;
    }

    public void setWatched_status(String watched_status) {
        this.watched_status = watched_status;
    }


    public static List<LiveClassesModel> getClassesData(){

        List<LiveClassesModel> modelList = new ArrayList<>();

        modelList.add(new LiveClassesModel("1", R.drawable.img_question_bank, "Anatomy", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("2", R.drawable.img_question_bank, "Physiology", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("3", R.drawable.img_question_bank, "Bio Chemistry", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("4", R.drawable.img_question_bank, "Pharmacology", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("5", R.drawable.img_question_bank, "Pathology", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("6", R.drawable.img_question_bank, "Micro Biology", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("7", R.drawable.img_question_bank, "Forensic Medicine", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("8", R.drawable.img_question_bank, "SPM", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("9", R.drawable.img_question_bank, "Ent", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("10", R.drawable.img_question_bank, "Ophthalmology", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("11", R.drawable.img_question_bank, "General Medicine", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("12", R.drawable.img_question_bank, "Surgery", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("13", R.drawable.img_question_bank, "Obg", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("14", R.drawable.img_question_bank, "Paediatrics", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("15", R.drawable.img_question_bank, "Orthopaedics", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("16", R.drawable.img_question_bank, "Psychiatry", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("17", R.drawable.img_question_bank, "Dermatology", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("18", R.drawable.img_question_bank, "radiology", "09/90 classes watched"));
        modelList.add(new LiveClassesModel("19", R.drawable.img_question_bank, "Anaesthesia", "09/90 classes watched"));


        return modelList;

    }
}

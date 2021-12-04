package app.technotech.koncpt.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.data.network.model.FacebookResponseModel;
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;

public class AppSharedPreference {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public AppSharedPreference(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    private void openEditor() {
        editor = preferences.edit();
    }


    public void addUserData(String data) {
        openEditor();
        editor.putString("userData", data);
        editor.apply();

    }


    public UserModelLogin.Data getUserResponse() {

        String response = preferences.getString("userData", null);
        Gson gson = new Gson();
        UserModelLogin.Data user = gson.fromJson(response, new TypeToken<UserModelLogin.Data>() {
        }.getType());
        return user;
    }


    public FacebookResponseModel.Data getFacebookUserResponse() {
        String response = preferences.getString("userData", null);
        Gson gson = new Gson();
        FacebookResponseModel.Data user = gson.fromJson(response, new TypeToken<FacebookResponseModel.Data>() {
        }.getType());
        return user;
    }


    public void setLogin(boolean b){

        openEditor();
        editor.putBoolean("login", b);
        editor.commit();

    }


    public boolean isLogin(){
        return preferences.getBoolean("login", false);
    }



    public void setSubjectId(String subjectId){
        openEditor();
        editor.putString("subject_id", subjectId);
        editor.commit();
    }


    public String getSubjectId(){
        return preferences.getString("subject_id", null);
    }



    public void setMcqOfTheDayId(String id){
        openEditor();
        editor.putString("mcq_of_the_day", id);
        editor.commit();
    }

    public String getMcqOfTheDay(){
        return preferences.getString("mcq_of_the_day", "0");
    }


    public void clearAllData(){

        openEditor();
        editor.clear();
        editor.commit();

    }



    public void saveCustomModule(String response){
        openEditor();
        editor.putString("custom_module", response);
        editor.commit();
    }


    public CustomExamModel retrieveCustomModule(){
        CustomExamModel examModel = new Gson().fromJson(preferences.getString("custom_module", null), new TypeToken<CustomExamModel>(){}.getType());
        return examModel;
    }


    public boolean isCustomModuleGenerated(){
        if (retrieveCustomModule() != null){
            return true;
        } else {
            return false;
        }
    }


    public void saveSubjectIdName(String id, String name){
        openEditor();
        editor.putString("mcq_subject_id", id);
        editor.putString("mcq_subject_name", name);
        editor.commit();
    }

    public String getMcqSubjectId(){
        return preferences.getString("mcq_subject_id", null);
    }


    public String getMcqSubjectName(){
        return preferences.getString("mcq_subject_name", null);
    }


    public void clearCustomModule(){
        openEditor();
        editor.putString("custom_module", null);
        editor.commit();
    }




    public void saveHomeScreenData(String response){

        openEditor();
        editor.putString("HomeData", response);
        editor.commit();

    }


    public HomeScreenModel getHomeScreenData(){
        return new Gson().fromJson(preferences.getString("HomeData", null), new TypeToken<HomeScreenModel>(){}.getType());
    }

    public void saveFcmToken(String token) {
        openEditor();
        editor.putString("token", token);
        editor.apply();
    }

    public String getSavedToken() {
        return preferences.getString("token", null);
    }


}

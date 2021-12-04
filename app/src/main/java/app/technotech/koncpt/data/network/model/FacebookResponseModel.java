package app.technotech.koncpt.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacebookResponseModel implements Parcelable{


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<FacebookResponseModel> CREATOR = new Creator<FacebookResponseModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FacebookResponseModel createFromParcel(Parcel in) {
            return new FacebookResponseModel(in);
        }

        public FacebookResponseModel[] newArray(int size) {
            return (new FacebookResponseModel[size]);
        }

    }
            ;

    protected FacebookResponseModel(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public FacebookResponseModel() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }


    public class Data implements Parcelable
    {

        @SerializedName("fb_google_id")
        @Expose
        private String fbGoogleId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("curr_acad_year")
        @Expose
        private String currAcadYear;
        @SerializedName("state_id")
        @Expose
        private String stateId;
        @SerializedName("login_enabled")
        @Expose
        private String loginEnabled;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("statename")
        @Expose
        private String statename;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("college_name")
        @Expose
        private String collegeName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("course")
        @Expose
        private String course;
        public final  Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            public Data[] newArray(int size) {
                return (new Data[size]);
            }

        }
                ;

        protected Data(Parcel in) {
            this.fbGoogleId = ((String) in.readValue((String.class.getClassLoader())));
            this.name = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.currAcadYear = ((String) in.readValue((String.class.getClassLoader())));
            this.stateId = ((String) in.readValue((String.class.getClassLoader())));
            this.loginEnabled = ((String) in.readValue((String.class.getClassLoader())));
            this.phone = ((String) in.readValue((String.class.getClassLoader())));
            this.address = ((String) in.readValue((String.class.getClassLoader())));
            this.statename = ((String) in.readValue((String.class.getClassLoader())));
            this.dob = ((String) in.readValue((String.class.getClassLoader())));
            this.collegeName = ((String) in.readValue((String.class.getClassLoader())));
            this.gender = ((String) in.readValue((String.class.getClassLoader())));
            this.course = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Data() {
        }

        public String getFbGoogleId() {
            return fbGoogleId;
        }

        public void setFbGoogleId(String fbGoogleId) {
            this.fbGoogleId = fbGoogleId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCurrAcadYear() {
            return currAcadYear;
        }

        public void setCurrAcadYear(String currAcadYear) {
            this.currAcadYear = currAcadYear;
        }

        public String getStateId() {
            return stateId;
        }

        public void setStateId(String stateId) {
            this.stateId = stateId;
        }

        public String getLoginEnabled() {
            return loginEnabled;
        }

        public void setLoginEnabled(String loginEnabled) {
            this.loginEnabled = loginEnabled;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatename() {
            return statename;
        }

        public void setStatename(String statename) {
            this.statename = statename;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getCollegeName() {
            return collegeName;
        }

        public void setCollegeName(String collegeName) {
            this.collegeName = collegeName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(fbGoogleId);
            dest.writeValue(name);
            dest.writeValue(email);
            dest.writeValue(id);
            dest.writeValue(currAcadYear);
            dest.writeValue(stateId);
            dest.writeValue(loginEnabled);
            dest.writeValue(phone);
            dest.writeValue(address);
            dest.writeValue(statename);
            dest.writeValue(dob);
            dest.writeValue(collegeName);
            dest.writeValue(gender);
            dest.writeValue(course);
        }

        public int describeContents() {
            return 0;
        }

    }
}

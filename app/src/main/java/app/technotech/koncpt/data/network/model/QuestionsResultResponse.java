package app.technotech.koncpt.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionsResultResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<QuestionsResultResponse> CREATOR = new Creator<QuestionsResultResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public QuestionsResultResponse createFromParcel(Parcel in) {
            return new QuestionsResultResponse(in);
        }

        public QuestionsResultResponse[] newArray(int size) {
            return (new QuestionsResultResponse[size]);
        }

    };

    protected QuestionsResultResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public QuestionsResultResponse() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}

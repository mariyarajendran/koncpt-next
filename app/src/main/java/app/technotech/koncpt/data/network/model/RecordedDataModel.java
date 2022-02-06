package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecordedDataModel {
    @SerializedName("status")
    @Expose
    private Integer status;

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

    public List<RecordedData> getData() {
        return data;
    }

    public void setData(List<RecordedData> data) {
        this.data = data;
    }

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("recorded_data")
    @Expose
    private List<RecordedData> data = null;

    public class RecordedData {
        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        @SerializedName("video_url")
        @Expose
        private String videoUrl;
    }
}

package app.technotech.koncpt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacultyModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<FacultyDatum> data = null;

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

    public List<FacultyDatum> getData() {
        return data;
    }

    public void setData(List<FacultyDatum> data) {
        this.data = data;
    }



    public class FacultyDatum{
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("faculty_name")
        @Expose
        private String facultyName;
        @SerializedName("faculty_image")
        @Expose
        private String facultyImage;
        @SerializedName("editor")
        @Expose
        private String editor;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("slug")
        @Expose
        private String slug;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFacultyName() {
            return facultyName;
        }

        public void setFacultyName(String facultyName) {
            this.facultyName = facultyName;
        }

        public String getFacultyImage() {
            return facultyImage;
        }

        public void setFacultyImage(String facultyImage) {
            this.facultyImage = facultyImage;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }
    }

}

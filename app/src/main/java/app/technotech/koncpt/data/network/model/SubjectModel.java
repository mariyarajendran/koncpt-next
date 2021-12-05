package app.technotech.koncpt.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubjectModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<SubjectModel> CREATOR = new Parcelable.Creator<SubjectModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SubjectModel createFromParcel(Parcel in) {
            return new SubjectModel(in);
        }

        public SubjectModel[] newArray(int size) {
            return (new SubjectModel[size]);
        }

    };

    protected SubjectModel(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public SubjectModel() {
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


    public class ModuleDatum implements Parcelable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("module_id")
        @Expose
        private String moduleId;
        @SerializedName("subject_id")
        @Expose
        private String subjectId;
        @SerializedName("parent_id")
        @Expose
        private String parentId;
        @SerializedName("plan_id")
        @Expose
        private String planId;
        @SerializedName("topic_name")
        @Expose
        private String topicName;
        @SerializedName("topic_image")
        @Expose
        private String topicImage;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("sort_order")
        @Expose
        private String sortOrder;
        @SerializedName("topic_rates")
        @Expose
        private String topicRates;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("total_mcq")
        @Expose
        private String totalMcq;
        @SerializedName("is_paid")
        @Expose
        private Integer isPaid;


        public final Parcelable.Creator<ModuleDatum> CREATOR = new Creator<ModuleDatum>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ModuleDatum createFromParcel(Parcel in) {
                return new ModuleDatum(in);
            }

            public ModuleDatum[] newArray(int size) {
                return (new ModuleDatum[size]);
            }

        };

        protected ModuleDatum(Parcel in) {
            this.id = ((String) in.readValue((String.class.getClassLoader())));
            this.moduleId = ((String) in.readValue((String.class.getClassLoader())));
            this.subjectId = ((String) in.readValue((String.class.getClassLoader())));
            this.parentId = ((String) in.readValue((String.class.getClassLoader())));
            this.planId = ((String) in.readValue((String.class.getClassLoader())));
            this.topicName = ((String) in.readValue((String.class.getClassLoader())));
            this.topicImage = ((String) in.readValue((String.class.getClassLoader())));
            this.slug = ((String) in.readValue((String.class.getClassLoader())));
            this.description = ((String) in.readValue((String.class.getClassLoader())));
            this.sortOrder = ((String) in.readValue((String.class.getClassLoader())));
            this.topicRates = ((String) in.readValue((String.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.totalMcq = ((String) in.readValue((String.class.getClassLoader())));
        }

        public ModuleDatum() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModuleId() {
            return moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getTopicImage() {
            return topicImage;
        }

        public void setTopicImage(String topicImage) {
            this.topicImage = topicImage;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(String sortOrder) {
            this.sortOrder = sortOrder;
        }

        public String getTopicRates() {
            return topicRates;
        }

        public void setTopicRates(String topicRates) {
            this.topicRates = topicRates;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getTotalMcq() {
            return totalMcq;
        }

        public void setTotalMcq(String totalMcq) {
            this.totalMcq = totalMcq;
        }


        public Integer getIsPaid() {
            return isPaid;
        }

        public void setIsPaid(Integer isPaid) {
            this.isPaid = isPaid;
        }


        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(moduleId);
            dest.writeValue(subjectId);
            dest.writeValue(parentId);
            dest.writeValue(planId);
            dest.writeValue(topicName);
            dest.writeValue(topicImage);
            dest.writeValue(slug);
            dest.writeValue(description);
            dest.writeValue(sortOrder);
            dest.writeValue(topicRates);
            dest.writeValue(createdAt);
            dest.writeValue(updatedAt);
            dest.writeValue(totalMcq);
            dest.writeValue(isPaid);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class ModuleName implements Parcelable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("subject_id")
        @Expose
        private String subjectId;
        @SerializedName("module_name")
        @Expose
        private String moduleName;
        public final Parcelable.Creator<ModuleName> CREATOR = new Creator<ModuleName>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ModuleName createFromParcel(Parcel in) {
                return new ModuleName(in);
            }

            public ModuleName[] newArray(int size) {
                return (new ModuleName[size]);
            }

        };

        protected ModuleName(Parcel in) {
            this.id = ((String) in.readValue((String.class.getClassLoader())));
            this.subjectId = ((String) in.readValue((String.class.getClassLoader())));
            this.moduleName = ((String) in.readValue((String.class.getClassLoader())));
        }

        public ModuleName() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(subjectId);
            dest.writeValue(moduleName);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class Data implements Parcelable {

        @SerializedName("module_name")
        @Expose
        private List<ModuleName> moduleName = null;
        @SerializedName("module_data")
        @Expose
        private List<ModuleDatum> moduleData = null;
        public final Parcelable.Creator<Data> CREATOR = new Creator<Data>() {
            @SuppressWarnings({
                    "unchecked"
            })
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            public Data[] newArray(int size) {
                return (new Data[size]);
            }
        };

        protected Data(Parcel in) {
            in.readList(this.moduleName, (ModuleName.class.getClassLoader()));
            in.readList(this.moduleData, (ModuleDatum.class.getClassLoader()));
        }

        public Data() {
        }

        public List<ModuleName> getModuleName() {
            return moduleName;
        }

        public void setModuleName(List<ModuleName> moduleName) {
            this.moduleName = moduleName;
        }

        public List<ModuleDatum> getModuleData() {
            return moduleData;
        }

        public void setModuleData(List<ModuleDatum> moduleData) {
            this.moduleData = moduleData;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(moduleName);
            dest.writeList(moduleData);
        }

        public int describeContents() {
            return 0;
        }

    }
}

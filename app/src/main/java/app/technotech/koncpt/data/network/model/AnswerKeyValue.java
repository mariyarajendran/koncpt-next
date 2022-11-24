package app.technotech.koncpt.data.network.model;

public class AnswerKeyValue {

    private String key;
    private String value;

    public AnswerKeyValue() {
    }

    public AnswerKeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

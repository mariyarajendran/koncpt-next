package app.krita.koncpt.utils;

public class TextUtil {
    public static String cutNull(Object format) {
        String cut;
        if (format == null) {
            cut = "";
        } else if (format.toString().trim().equalsIgnoreCase("")) {
            cut = "";
        } else if (format.toString().trim().equalsIgnoreCase("null")) {
            cut = "";
        } else {
            cut = format.toString();
        }
        return cut;
    }

}

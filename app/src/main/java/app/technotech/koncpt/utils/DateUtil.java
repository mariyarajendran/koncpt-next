package app.technotech.koncpt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    Date currentDate = new Date();
    SimpleDateFormat dateFormatYmd = new SimpleDateFormat(YYYY_MM_DD);

    public DateUtil() {
    }

    public String getCurrentDate() {
        return dateFormatYmd.format(currentDate);
    }
}

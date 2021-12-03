package app.technotech.koncpt.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import app.technotech.koncpt.R;

public class FileUtils {
    private static String APP_DIR = "Koncpt";
    private static FileUtils instance;
    private static Context mContext;

    public static FileUtils getInstance(Context context) {
        APP_DIR = context.getResources().getString(R.string.app_name);
        if (instance == null) {
            synchronized (FileUtils.class) {
                if (instance == null) {
                    mContext = context;
                    instance = new FileUtils();
                }
            }
        }
        return instance;
    }

    private FileUtils() {
        if (isSDCanWrite()) {
            creatSDDir(APP_DIR);
        }
    }

    public boolean isSDCanWrite() {
        return Environment.getExternalStorageState().equals("mounted") && Environment.getExternalStorageDirectory().canWrite() && Environment.getExternalStorageDirectory().canRead();
    }

    public File creatSDDir(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getLocalPath());
        sb.append(str);
        File file = new File(sb.toString());
        file.mkdirs();
        return file;
    }

    public File createTempFile(String str, String str2) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(getAppDirPath());
        sb.append("/");
        sb.append(str);
        sb.append(System.currentTimeMillis());
        sb.append(str2);
        File file = new File(sb.toString());
        file.createNewFile();
        return file;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public File createVideoFileWithName(String str, String str2) throws IOException {
        String replaceAll = str.trim().replaceAll("  ", " ").replaceAll("[-+.^:,]", "");
        StringBuilder sb = new StringBuilder();
        sb.append(getAppDirPath());
        String str3 = "/";
        sb.append(str3);
        sb.append(replaceAll);
        sb.append(str2);
        File file = new File(sb.toString());
        if (file.exists()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getAppDirPath());
            sb2.append(str3);
            sb2.append(replaceAll);
            sb2.append("_");
            sb2.append(System.currentTimeMillis());
            sb2.append(str2);
            file = new File(sb2.toString());
        } else {
            file.createNewFile();
        }
        Log.e("Fileaaa: ", file.toString());
        return file;
    }





    public String getAppDirPath() {
        if (getLocalPath() == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getLocalPath());
        sb.append(APP_DIR);
        sb.append("/");
        return sb.toString();
    }

    private static String getLocalPath() {
        StringBuilder sb = new StringBuilder();
        if (Build.VERSION.SDK_INT >= 29){
            sb.append(mContext.getExternalFilesDir(Environment.DIRECTORY_MOVIES));
        } else {
            sb.append(Environment.getExternalStorageDirectory());
        }

        sb.append("/");
        return sb.toString();
    }

    public static boolean deleteFile(String str) {
        return new File(str).delete();
    }

    public File[] getListVideo() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(APP_DIR);
        return new File(sb.toString()).listFiles();
    }

    public static String getFileName(String str) {
        return str.substring(0, str.lastIndexOf("."));
    }

    public static String getFileType(String str) {
        return str.substring(str.lastIndexOf(".") + 1, str.length());
    }

    public static Date getFileLastModified(String str) {
        return new Date(new File(str).lastModified());
    }
}

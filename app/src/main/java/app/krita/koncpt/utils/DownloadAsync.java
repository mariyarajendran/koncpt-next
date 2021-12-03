package app.technotech.koncpt.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.DBHelper;
import app.technotech.koncpt.data.network.model.DownloadModel;
import app.technotech.koncpt.ui.activity.MainActivity;

public class DownloadAsync extends AsyncTask<Void, Void, String> {
    private NotificationCompat.Builder builder;
    private int id;
    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private NotificationManager manager;
    private String pathVideo = "";
    private String strUrl;
    private String title;
    private DBHelper dbHelper;
    private String description;
    private String displayTitle;

    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;

    public DownloadAsync(Context context, int i, String str, String displayTitle, String description, String download_url, DBHelper dbHelper) {
        this.mContext = context;
        this.id = i;
        String str3 = "_";
//        this.title = str.replace("/", str3).replace("*", str3).replace("(", str3).replace(")", str3).replace("!", str3).replace("@", str3).replace("#", str3).replace("$", str3).replace("%", str3).substring(0, Math.min(str.length(), 20));
        this.title = str;
        this.strUrl = download_url;
        this.displayTitle = displayTitle;
        this.description = description;
        this.dbHelper = dbHelper;
        generalUtils = new GeneralUtils(context);
        progressDialog  = generalUtils.showProgressDialog();
    }

    /* access modifiers changed from: protected */
    @SuppressLint("WrongConstant")
    public void onPreExecute() {
        super.onPreExecute();
        try {
            pathVideo = FileUtils.getInstance(mContext).createVideoFileWithName(title, ".mp4").getPath();
            DebugLog.e("Path Video => " + pathVideo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        progressDialog.show();
        String CHANNEL_ID = "1";
        if (VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "VidSave_channel", 2);
            notificationChannel.setDescription("Downloading");
            notificationChannel.enableVibration(false);
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager = manager;
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(mContext, CHANNEL_ID);
        }
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("pathVideo", pathVideo);
        intent.setAction("CANCEL_ACTION");
        builder.setSmallIcon(R.drawable.downloadarrow).setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.downloadarrow)).setContentTitle(title).setContentText("download").setPriority(2).addAction(R.drawable.downloadarrow, "Cancel", PendingIntent.getBroadcast(mContext, 0, intent, 134217728)).setAutoCancel(true);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Void... voidArr) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strUrl).openConnection();
            httpURLConnection.connect();
            int contentLength = httpURLConnection.getContentLength();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(pathVideo);
            byte[] bArr = new byte[5 * 1024];
            long j = 0;
            int i = 0;
            do {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                j += (long) read;
                i++;
                if (i > 5) {
                    int i2 = (int) ((100 * j) / ((long) contentLength));
                    builder.setProgress(100, i2, false);
                    NotificationCompat.Builder builder2 = builder;
                    String sb = "downloading " +
                            i2 +
                            "%";
                    builder2.setContentText(sb);
                    manager.notify(id, builder.build());
                    i = 0;
                }
                fileOutputStream.write(bArr, 0, read);
            } while (!isCancelled());
            fileOutputStream.flush();
            fileOutputStream.close();
            bufferedInputStream.close();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
    }

    /* access modifiers changed from: protected */
    @SuppressLint("ShowToast")
    public void onPostExecute(String str) {
        super.onPostExecute(str);

        progressDialog.dismiss();
        @SuppressLint("WrongConstant") PendingIntent activity = PendingIntent.getActivity(mContext, 0, new Intent(mContext, MainActivity.class), 134217728);
        manager.cancel(id);
        builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.downloadarrow)).setContentIntent(activity).setAutoCancel(true);
        String str2 = mContext.getResources().getString(R.string.app_name);
        if (str != null) {
            addNotification(str2, "Success");
            Toast.makeText(mContext, "File downloaded successfully!", Toast.LENGTH_SHORT).show();
            MediaScannerConnection.scanFile(mContext, new String[]{pathVideo}, null, null);

            DownloadModel downloadModel = new DownloadModel();
            downloadModel.setVideo_id(title);
            downloadModel.setVideo_url(pathVideo);
            downloadModel.setSubject_title(displayTitle);
            downloadModel.setDescription(description);
            dbHelper.createVideos(downloadModel);



            return;
        }
        addNotification(str2, "Failed");
        Toast.makeText(mContext, "Download Failed!", Toast.LENGTH_SHORT).show();
        FileUtils.deleteFile(pathVideo);
    }

    private void addNotification(String str, String str2) {
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        String str3 = "my_channel_01";
        if (VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(str3, "my_channel", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(str2);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationChannel.setShowBadge(true);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        Intent intent = new Intent(mContext, MainActivity.class);
        TaskStackBuilder create = TaskStackBuilder.create(mContext);
        create.addParentStack(MainActivity.class);
        create.addNextIntent(intent);
        @SuppressLint("ResourceType") NotificationCompat.Builder color = new NotificationCompat.Builder(mContext, str3).setContentTitle(str).setContentText(str2).setSmallIcon(R.drawable.downloadarrow).setPriority(0).setContentIntent(create.getPendingIntent(0, 134217728)).setAutoCancel(true).setColor(mContext.getResources().getColor(17170455));
        if (notificationManager != null) {
            notificationManager.notify(234, color.build());
        }
    }
}

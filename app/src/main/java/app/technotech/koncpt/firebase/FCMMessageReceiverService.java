package app.technotech.koncpt.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import app.technotech.koncpt.R;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;


public class FCMMessageReceiverService extends FirebaseMessagingService {


    String CHANNEL_ID = "channel_koncpt";


    private AppSharedPreference preference;
    private NotificationManager mNotificationManager;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    boolean flag = false;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {

            try {


                String json = remoteMessage.getData().toString();
                DebugLog.e("JSON : " + json);

                String title = remoteMessage.getData().get("title");
                String message = remoteMessage.getData().get("message");
                String req_id = remoteMessage.getData().get("req_id");




                generateNotification(title, message, req_id);


            } catch (Exception ex) {

                DebugLog.e("JSON ERROR: No MSG");
                ex.printStackTrace();
            }


        } else {
            String json = remoteMessage.getNotification().getBody();
//            String json2 = remoteMessage.getFrom();
//            String json3 = remoteMessage.getTo();
            DebugLog.e("JSON : " + json );
//            DebugLog.e("JSON2 : "+ json2 );
//            DebugLog.e("JSON3 : " + json3);
        }


    }


    private void generateNotification(String title, String message, String req_id) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("data", message);
        intent.putExtra("req_id", req_id);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.bigText(message);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.zm_bell)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setStyle(bigTextStyle)
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setColorized(true);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mNotifyBuilder.setChannelId(CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0, mNotifyBuilder.build());
    }


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        preference = new AppSharedPreference(FCMMessageReceiverService.this);
        preference.saveFcmToken(s);
        Log.d("Token", s);
        Log.d("Messageing Service", "Refreshed token: " + s);
        sendRegistrationToServer(s);
    }

    private void sendRegistrationToServer(String token) {
        DebugLog.e("TOKEN : " + token);
        // TODO: Implement this method to send token to your app server.
    }


}

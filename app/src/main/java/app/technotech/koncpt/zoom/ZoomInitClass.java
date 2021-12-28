package app.technotech.koncpt.zoom;

import android.content.Context;

import app.technotech.koncpt.ui.activity.MainActivity;
import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKInitializeListener;

public class ZoomInitClass {


    private Context context;

    public ZoomInitClass(Context context) {
        this.context = context;
        initializeSdk(context);
    }


    private void initializeSdk(Context context) {
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        ZoomSDKInitParams initParams = new ZoomSDKInitParams();
        initParams.appKey = AuthConstants.APP_KEY;
        initParams.appSecret = AuthConstants.APP_SECRET;
        initParams.domain = AuthConstants.WEB_DOMAIN;
        initParams.enableLog = true;


        ZoomSDKInitializeListener listener = new ZoomSDKInitializeListener() {
            @Override
            public void onZoomSDKInitializeResult(int i, int i1) {

            }

            @Override
            public void onZoomAuthIdentityExpired() {

            }
        };

        zoomSDK.initialize(context, listener, initParams);
    }


    public int joinMeeting(String title, String meetingId, String passcode){
        MeetingService meetingService = ZoomSDK.getInstance().getMeetingService();
        JoinMeetingOptions meetingOptions = new JoinMeetingOptions();
        JoinMeetingParams meetingParams = new JoinMeetingParams();
        meetingParams.displayName = title;
        meetingParams.meetingNo = meetingId;
        meetingParams.password = passcode;
        return meetingService.joinMeetingWithParams(context, meetingParams, meetingOptions);
    }
}

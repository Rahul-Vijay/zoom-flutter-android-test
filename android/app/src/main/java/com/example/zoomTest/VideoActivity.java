package com.example.zoomTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.MeetingServiceListener;
import us.zoom.sdk.MeetingStatus;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKAuthenticationListener;
import us.zoom.sdk.ZoomSDKInitializeListener;

public class VideoActivity extends AppCompatActivity implements Constants, ZoomSDKInitializeListener, MeetingServiceListener, ZoomSDKAuthenticationListener {

    private EditText joinMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        joinMeeting = this.findViewById(R.id.meetingNumberInput);

        // initialize zoom sdk object
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        if(savedInstanceState == null) {
            zoomSDK.initialize(this, APP_KEY, APP_SECRET, WEB_DOMAIN, this);
        }
    }

    @Override
    public void onMeetingStatusChanged(MeetingStatus meetingStatus, int i, int i1) {

    }

    @Override
    public void onZoomSDKLoginResult(long l) {

    }

    @Override
    public void onZoomSDKLogoutResult(long l) {

    }

    @Override
    public void onZoomIdentityExpired() {

    }

    @Override
    public void onZoomSDKInitializeResult(int i, int i1) {

    }

    @Override
    public void onZoomAuthIdentityExpired() {

    }

    public void onTapToJoin(View view){

        // Step 1: Get meeting number from input field.
        String meetingNumber = joinMeeting.getText().toString().trim();

        // Check if the meeting number is empty.
        if(meetingNumber.length() == 0) {
            Toast.makeText(this, "You need to enter the correct meeting number",
                    Toast.LENGTH_LONG).show();
        }

        // Step 2: Get Zoom SDK instance.
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        // Check if the zoom SDK is initialized
        if(!zoomSDK.isInitialized()) {
            Toast.makeText(this, "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show();
        }

        // Step 3: Get meeting service from zoom SDK instance.
        MeetingService meetingService = zoomSDK.getMeetingService();

        // Step 4: Configure meeting options.
        JoinMeetingOptions opts = new JoinMeetingOptions();

        opts.no_share = true;

        // Step 5: Setup join meeting parameters
        JoinMeetingParams params = new JoinMeetingParams();

        params.displayName = "Hello World From Zoom SDK";
        params.meetingNo = meetingNumber;

        // Step 6: Call meeting service to join meeting
        meetingService.joinMeetingWithParams(this, params, opts);
    }
}

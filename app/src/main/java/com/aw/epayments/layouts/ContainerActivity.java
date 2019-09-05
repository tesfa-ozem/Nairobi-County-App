package com.aw.epayments.layouts;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.EditText;

import com.aw.epayments.api.Api;

public class ContainerActivity extends Application {

    EditText editText;
    private int SpannedLength = 0, chipLength = 4;
    private int lastInteractionTime;
    private Boolean isScreenOff = false;

    public void onCreate() {
        super.onCreate();
        // ......
        startUserInactivityDetectThread(); // start the thread to detect inactivity
        new ScreenReceiver();  // creating receive SCREEN_OFF and SCREEN_ON broadcast msgs from the device.
    }

    public void startUserInactivityDetectThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(15000); // checks every 15sec for inactivity
                    } catch (Exception e) {
                        Log.e("", e.getLocalizedMessage());
                    }
                    if (isScreenOff || getLastInteractionTime() > 120000) {
                        Api.INSTANCE.clearValue(ContainerActivity.this, "LoginStatus");
                        startActivity(new Intent(getApplicationContext(), naypayDasboard.class)); //Go back to home page


                    }
                }
            }
        }).start();
    }

    public long getLastInteractionTime() {
        return lastInteractionTime;
    }

    public void setLastInteractionTime(int lastInteractionTime) {
        this.lastInteractionTime = lastInteractionTime;
    }

    private class ScreenReceiver extends BroadcastReceiver {

        protected ScreenReceiver() {
            // register receiver that handles screen on and screen off logic
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            registerReceiver(this, filter);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                isScreenOff = true;
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                isScreenOff = false;
            }
        }
    }
}




package com.example.android.appscrape;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyBroadcast extends BroadcastReceiver {
    private static final String TAG = "myReciver";

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        Log.e(TAG, "onReceive: custom intent recieved : " + log );

        Bundle bucket = intent.getExtras();

        if (bucket.get("instruction").equals("home")) {
            context.startActivity(new Intent(context, MainActivity.class));
        }
    }
}

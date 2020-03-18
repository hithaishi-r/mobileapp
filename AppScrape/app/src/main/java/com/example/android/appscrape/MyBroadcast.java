package com.example.android.appscrape;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("it","has recieved braodcast");
        Intent it = new Intent("intent.my.action");
        it.setComponent(new ComponentName(context.getPackageName(), MainActivity.class.getName()));
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(it);
    }
}

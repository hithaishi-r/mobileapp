package com.example.android.appscrape;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button permission;
    private Button collect;
    private Button showdata;
    private static final String TAG = "MyActivity";
    public final static String appPackageName = "com.flipkart.android";
    private static final String CUSTOM_SWITCH = "com.example.CUSTOM_SWITCH";

    public static Boolean startFlag = Boolean.FALSE;
    public static Boolean getStartFlag() {
        return startFlag;
    }
    public static void setStartFlag(Boolean startFlag) {
        MainActivity.startFlag = startFlag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permission= (Button) findViewById(R.id.permission);
        permission.setOnClickListener(this);

        collect=(Button) findViewById(R.id.collect);
        collect.setOnClickListener(this);

        showdata=(Button) findViewById(R.id.showdata);
        showdata.setOnClickListener(this);

        BroadcastReceiver br = new MyBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(CUSTOM_SWITCH);
        this.registerReceiver(br, filter);
    }

    private boolean openActivity (String applicationPackageName) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(applicationPackageName);
        if (launchIntent != null) {
            startActivity(launchIntent);
            setStartFlag(Boolean.TRUE);
            startService(new Intent(MainActivity.this, AppScrape.class));
        }

        return Boolean.TRUE;
    }



    private void checkAppExistence(PackageManager packageManager){
        try {
            if(packageManager.getApplicationInfo(appPackageName, 0).enabled){
                openActivity(appPackageName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            }
            catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }

    }

    @Override
    public void onClick(View view) {
        if ( view == permission ) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if ( view == collect ) {
            AppScrape.setCount(0);
            PackageManager pm = getPackageManager();
            checkAppExistence(pm);
        } else if ( view == showdata ) {
            Intent intent=new Intent(this,flipkart_data.class);
            startActivity(intent);
        }
    }
}

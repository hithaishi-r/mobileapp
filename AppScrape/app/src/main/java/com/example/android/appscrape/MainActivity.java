package com.example.android.appscrape;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,  AdapterView.OnItemSelectedListener {

    private Button permission;
    private Spinner app_spinner;
    private Button showdata;
    ArrayAdapter<String> adapter;
    private static final String TAG = "MyActivity";
    public static String appPackageName;
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

        app_spinner = (Spinner)findViewById(R.id.app_select);
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.app_names));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        app_spinner.setAdapter(adapter);
        app_spinner.setOnItemSelectedListener(this);

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



    private void checkAppExistence(PackageManager packageManager) {
        if (appPackageName != null) {
            try {
                if (packageManager.getApplicationInfo(appPackageName, 0).enabled) {
                    openActivity(appPackageName);
                }
            } catch (PackageManager.NameNotFoundException e) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }

        }
    }

    private void permissionCheck(){
        if ( AppScrape.getInstance() != null ) {
            AppScrape.setCount(0);
            PackageManager pm = getPackageManager();
            checkAppExistence(pm);
        } else {
            Toast.makeText(this, "Please give permission", Toast.LENGTH_SHORT).show();
        }
    }

   @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        switch (position) {
            case 0:
                break;
            case 1:
                appPackageName="com.flipkart.android";
                permissionCheck();
                app_spinner.setSelection(0);
                break;
            case 2:
                appPackageName="in.amazon.mShop.android.shopping";
                permissionCheck();
                app_spinner.setSelection(0);
                break;
            case 3:
                appPackageName="com.olacabs.customer";
                permissionCheck();
                app_spinner.setSelection(0);
                break;
            case 4:
                appPackageName="com.ubercab";
                permissionCheck();
                app_spinner.setSelection(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onClick(View view) {
        if ( view == permission ) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if ( view == showdata ) {
            Intent intent = new Intent(this, ShowData.class);
            startActivity(intent);
        }
    }
}

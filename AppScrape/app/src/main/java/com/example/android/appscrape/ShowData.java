package com.example.android.appscrape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

public class ShowData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        TextView dataView=(TextView) findViewById(R.id.myData);
        Object myData = AppScrape.getInstance().getData();
        dataView.setText(getJSON(myData));
    }

    public static String getJSON( Object obj ) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        Log.e("Gson", "getJSON: " + json );
        return json;
    }
}

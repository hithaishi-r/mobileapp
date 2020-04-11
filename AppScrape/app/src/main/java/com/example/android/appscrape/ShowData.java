package com.example.android.appscrape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class ShowData extends AppCompatActivity {

    private static String message;
    public static String getMessage() { return message; }
    public static void setMessage(String message) { ShowData.message = message; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        TextView dataView=(TextView) findViewById(R.id.myData);
        Object myData = AppScrape.getInstance().getData();
        dataView.setText(getJSON(myData));

        if(getMessage()!=null){
            Toast.makeText(this, getMessage() , Toast.LENGTH_SHORT).show();
        }
        message=null;
    }

    public static String getJSON( Object obj ) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        Log.e("Gson", "getJSON: " + json );
        return json;
    }
}

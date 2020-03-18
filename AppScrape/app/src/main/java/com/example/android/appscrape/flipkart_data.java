package com.example.android.appscrape;
import com.example.android.appNav.flipkart.scrapeflipkart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class flipkart_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipkart_data);

        TextView _name=(TextView) findViewById(R.id._name);
        TextView _number=(TextView) findViewById(R.id._number);
        TextView _email=(TextView) findViewById(R.id._email);

        String name=scrapeflipkart.getName();
        String number=scrapeflipkart.getNumber();
        String email=scrapeflipkart.getEmail();

        _name.setText(name);
        _number.setText(number);
        _email.setText(email);
    }
}

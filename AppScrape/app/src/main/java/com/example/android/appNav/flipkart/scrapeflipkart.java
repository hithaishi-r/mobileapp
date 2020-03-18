package com.example.android.appNav.flipkart;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;
import com.example.android.appscrape.MainActivity;

import java.lang.String;

import java.util.Iterator;

public class scrapeflipkart {



    public static boolean scrape(){
        Boolean value = Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();
        while (iterator.hasNext()) {
            primeNode = iterator.next();
            if ( primeNode != null ) {
                String nodeName = "" + primeNode.getText();
                //Log.e(TAG,"reached");
                if ( nodeName.equals("editprofile") ) {
                    data flipkartData = new data();
                    Log.e("scrape", "findMyButton: " + primeNode.toString() );
                    primeNode = iterator.next();
                    primeNode = iterator.next();
                    primeNode = iterator.next();
                    primeNode = iterator.next();
                    flipkartData.setName("" + primeNode.getText().toString());
                    primeNode = iterator.next();
                    flipkartData.setNumber("" + primeNode.getText().toString());
                    primeNode = iterator.next();
                    flipkartData.setEmail("" + primeNode.getText().toString());

                    AppScrape.getInstance().setData(flipkartData);
                    value =  Boolean.TRUE;
                    break;
                }
            }
        }
        return value;
    }

}

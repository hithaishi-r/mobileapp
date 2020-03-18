package com.example.android.appNav.flipkart;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;
import java.lang.String;

import java.util.Iterator;

public class scrapeflipkart {
    private static String name;
    private static String number;
    private static String email;
    private static String address;
    public static String getName(){return name;}
    public static String getNumber(){return number;}
    public static String getEmail(){return email;}
    public static String getAddress(){return address;}
    public static boolean scrape(){
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();
        while (iterator.hasNext()) {

            primeNode = iterator.next();
            String nodeName = ("" + primeNode.getText()).toString();
            //Log.e(TAG,"reached");
            if ( nodeName.equals("editprofile") ) {
                Log.e("scrape", "findMyButton: " + primeNode.toString() );
                break;
            }
        }
        if(primeNode.getText().equals("editprofile")) {
            primeNode = iterator.next();
            primeNode = iterator.next();
            primeNode = iterator.next();
            primeNode = iterator.next();
            name = (primeNode.getText()).toString();
            primeNode = iterator.next();
            number = primeNode.getText().toString();
            primeNode = iterator.next();
            email = primeNode.getText().toString();
            Log.e("scrape", "data" + name + number + email);
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

}

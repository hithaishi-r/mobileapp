package com.example.android.appNav.ola;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import com.example.android.appscrape.AppScrape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class scrapeOla {

    private static Boolean startFlag = Boolean.FALSE;
    public static Boolean getStartFlag() { return startFlag; }
    public static void setStartFlag(Boolean flag) { scrapeOla.startFlag = flag; }

    private static int index=-1;

    private static com.example.android.appNav.ola.data olaData = new data();

    private static List<Map<String, String>> rides = new ArrayList<Map<String, String>>();

    public static boolean scrapePersonalInfo() {
        Boolean flag=Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();

        while (iterator.hasNext()) {
            primeNode = iterator.next();
            if(primeNode!=null && primeNode.getText()!=null && (!primeNode.getText().toString().isEmpty())){
                if(primeNode.getText().toString().equals("Your account")){
                    Log.e("scrape", "findMyButton: " + primeNode.toString() );
                    flag=Boolean.TRUE;
                    primeNode=iterator.next();
                    primeNode=iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        olaData.setName("" + primeNode.getText().toString());
                    }
                    primeNode=iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        olaData.setNumber("" + primeNode.getText().toString());
                    }
                    primeNode=iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        olaData.setEmail("" + primeNode.getText().toString());
                    }
                    break;
                }
            }
        }
        return flag;
    }

    public static boolean scrapeRides() {
        index++;
        Boolean value = Boolean.FALSE;
        if (!getStartFlag()) {
            AccessibilityNodeInfo primeNode = null;
            Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();
            Map<String, String> details = new HashMap<String, String>();
            String tripDate = "", fare = "", paymentMethod = "", vehicle = "";
            while (iterator.hasNext()) {
                primeNode = iterator.next();
                if(primeNode.getContentDescription()!=null && primeNode.getContentDescription().toString().equals("Back")){
                    primeNode=iterator.next();
                    if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        tripDate = primeNode.getText().toString();
                    }
                }
                if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                    if (primeNode.getText().toString().equals("You rated")){
                        primeNode=iterator.next();
                        primeNode=iterator.next();
                        if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                            vehicle = primeNode.getText().toString();
                        }
                        primeNode=iterator.next();
                        if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                            fare = primeNode.getText().toString();
                        }
                    }
                    if (primeNode.getText().toString().equals("Payment")){
                        primeNode=iterator.next();
                        if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                            paymentMethod = primeNode.getText().toString().split("\n")[0];
                        }
                    }
                }

            }
            if(!iterator.hasNext()){
                value=Boolean.TRUE;
            }
            details.put("trip date",tripDate);
            details.put("vehicle",vehicle);
            details.put("fare",fare);
            details.put("payment method",paymentMethod);
            rides.add(index,details);
        }
        if(getStartFlag()){
            olaData.setRides(rides);
            AppScrape.getInstance().setData(olaData);
            value =  Boolean.TRUE;
        }
        return value;
    }
}

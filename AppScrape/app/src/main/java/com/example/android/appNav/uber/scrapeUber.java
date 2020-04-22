package com.example.android.appNav.uber;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class scrapeUber {

    private static Boolean startFlag = Boolean.FALSE;
    public static Boolean getStartFlag() { return startFlag; }
    public static void setStartFlag(Boolean flag) { scrapeUber.startFlag = flag; }

    private static int index=0;

    private static String date="";

    private static com.example.android.appNav.uber.data uberData = new data();

    private static List<Map<String, String>> rides = new ArrayList<Map<String, String>>();

    public static boolean scrapePersonalInfo() {
        Boolean flag = Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();
        String fname="",lname="";

        while (iterator.hasNext()) {
            primeNode = iterator.next();
            if (primeNode != null && primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                if (primeNode.getText().toString().equals("First Name")){
                    primeNode=iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        fname=primeNode.getText().toString();
                    }
                }else if (primeNode.getText().toString().equals("Last Name")){
                    primeNode=iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        lname=primeNode.getText().toString();
                        uberData.setName(fname+" "+lname);
                    }
                }else if (primeNode.getText().toString().equals("Phone Number")){
                    primeNode=iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        uberData.setNumber(""+primeNode.getText().toString());
                    }
                }else if (primeNode.getText().toString().equals("Email")){
                    primeNode=iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        uberData.setEmail(""+primeNode.getText().toString());
                    }
                }
            }
        }
        if (!iterator.hasNext()){
            flag=Boolean.TRUE;
        }
        return flag;
    }

    public static boolean scrapeRides() {
        Boolean value = Boolean.FALSE;
        if (!getStartFlag()) {
            AccessibilityNodeInfo firstNode = null , secondNode=null , thirdNode=null , fourthNode=null;

            Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();

            while (iterator.hasNext()) {
                firstNode = iterator.next();
                if (firstNode!=null && firstNode.getText()!=null && (!firstNode.getText().toString().isEmpty())){
                    if(firstNode.getText().toString().equals("Past")){
                        while(true){
                            try{
                                firstNode=iterator.next();
                                secondNode=iterator.next();
                                thirdNode=iterator.next();
                                fourthNode=iterator.next();
                            }catch(NoSuchElementException e){
                                break;
                            }
                            if (thirdNode.getText()==null){
                                fourthNode=iterator.next();
                                continue;
                            }else{
                                if (firstNode.getText()!=null && (!firstNode.getText().toString().equals(date))) {
                                    value=Boolean.TRUE;
                                    Map<String, String> details = new HashMap<String, String>();
                                    if (firstNode.getText() != null) {
                                        date = firstNode.getText().toString();
                                        details.put("trip date", date);
                                    }
                                    if (secondNode.getText() != null) {
                                        details.put("fare", secondNode.getText().toString());
                                    }
                                    if (thirdNode.getText() != null) {
                                        details.put("vehicle", thirdNode.getText().toString());
                                    }
                                    if (fourthNode.getText() != null) {
                                        details.put("payment method", fourthNode.getText().toString());
                                    } else {
                                        details.put("payment method", "UPI/Card");
                                    }
                                    rides.add(index,details);
                                    index++;
                                }else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
            if (index>=4){
                value=Boolean.FALSE;
            }else {
                value=Boolean.TRUE;
            }
        }
        if(getStartFlag()){
            uberData.setRides(rides);
            AppScrape.getInstance().setData(uberData);
            value =  Boolean.TRUE;
        }
        return value;
    }
}

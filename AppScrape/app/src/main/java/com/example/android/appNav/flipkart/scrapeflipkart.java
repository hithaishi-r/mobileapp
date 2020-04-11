package com.example.android.appNav.flipkart;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;

import java.lang.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class scrapeflipkart {

    private static Boolean startFlag = Boolean.FALSE;
    public static Boolean getStartFlag() { return startFlag; }
    public static void setStartFlag(Boolean flag) { scrapeflipkart.startFlag = flag; }

    private static com.example.android.appNav.flipkart.data flipkartData = new data();

    private static List<Map<String, String>> orders = new ArrayList<Map<String, String>>();

    public static boolean scrapePersonalInfo(){
        Boolean value = Boolean.FALSE;
        Boolean flag=Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();

        while (iterator.hasNext()) {
            primeNode = iterator.next();
            if ( primeNode != null ) {
                String nodeName = "" + primeNode.getText();
                //Log.e(TAG,"reached");
                if ( nodeName.equals("editprofile") ) {
                    flag=Boolean.TRUE;
                    Log.e("scrape", "findMyButton: " + primeNode.toString() );
                    primeNode = iterator.next();
                    primeNode = iterator.next();
                    primeNode = iterator.next();
                    primeNode = iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        flipkartData.setName("" + primeNode.getText().toString());
                    }
                    primeNode = iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        flipkartData.setNumber("" + primeNode.getText().toString());
                    }
                    primeNode = iterator.next();
                    if(primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                        flipkartData.setEmail("" + primeNode.getText().toString());
                    }

                    //AppScrape.getInstance().setData(flipkartData);
                    value =  Boolean.TRUE;
                    break;
                }
            }
        }
        if(!flag){
            value=Boolean.TRUE;
        }
        return value;
    }

    public static boolean scrapeOrders(){
        Boolean value = Boolean.FALSE;
        if(!getStartFlag()) {
            AccessibilityNodeInfo primeNode = null;
            Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();
            Map<String, String> details = new HashMap<String, String>();
            String itemName="",orderDate="",orderTotal="",paymentMethod="",status="";
            String[] date;
            while (iterator.hasNext()) {
                primeNode = iterator.next();
                if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                    if (primeNode.getText().toString().contains("Order ID")) {
                        primeNode = iterator.next();
                        if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                            itemName = primeNode.getText().toString();
                        }
                    } else if (primeNode.getText().toString().equals("Ordered And Approved")) {
                        primeNode=iterator.next();
                        if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                            date = primeNode.getText().toString().split("\\'");
                            orderDate=date[0]+date[1];
                        }
                    } else if (primeNode.getText().toString().equals("Delivered")) {
                        status = "Delivered";
                    } else if (primeNode.getText().toString().equals("Return")) {
                        status = "Returned";
                    } else if (primeNode.getText().toString().equals("Total Amount")) {
                        primeNode = iterator.next();
                        if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                            orderTotal = primeNode.getText().toString();
                        }

                    } else if (!iterator.hasNext()) {
                        if (primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                            if (itemName.contains("Supermart Basket")) {
                                paymentMethod = primeNode.getText().toString().split("\\:")[1].substring(2);
                            }else {
                                paymentMethod = primeNode.getText().toString().split("\\:")[0];
                            }
                        }
                    }
                }
            }
            if(!iterator.hasNext()){
                value=Boolean.TRUE;
            }
            details.put("item name",itemName);
            details.put("order date",orderDate);
            details.put("order total",orderTotal);
            details.put("status",status);
            details.put("payment method",paymentMethod);
            orders.add(flipkartNavUtil.getCounter(),details);
            if(value){
                flipkartNavUtil.counterIncrement(1);
                Log.e("data","is"+orders.toString());
            }
        }
        if(getStartFlag()){
            flipkartData.setOrders(orders);
            AppScrape.getInstance().setData(flipkartData);
            value =  Boolean.TRUE;
        }
        return value;
    }
}

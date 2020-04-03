package com.example.android.appNav.amazon;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import com.example.android.appscrape.AppScrape;
import com.example.android.appscrape.utility.navUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class scrapeamazon {

    private static Boolean startFlag = Boolean.FALSE;
    public static Boolean getStartFlag() { return startFlag; }
    public static void setStartFlag(Boolean flag) { scrapeamazon.startFlag = flag; }

    private static com.example.android.appNav.amazon.data amazonData = new data();

    private static List<Map<String, String>> orders = new ArrayList<Map<String, String>>();

    public static boolean scrapeName() {
        Boolean value = Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        AccessibilityNodeInfo nextNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();

        while (iterator.hasNext()) {
            primeNode = nextNode;
            nextNode = iterator.next();
            if (primeNode != null && nextNode != null) {
                String nodeName = "" + nextNode.getText();
                //Log.e(TAG,"reached");
                if (nodeName.equals("Home")) {
                    Log.e("scrape", "findMyButton: " + primeNode.toString());
                    String name = primeNode.getText().toString().substring(6);
                    Log.e("scrape","name"+name);
                    amazonData.setName("" + name);
                    value =  Boolean.TRUE;
                    break;
                }
            }
        }
        return value;
    }

    public static boolean scrapeOrders() {
        Boolean value = Boolean.FALSE;
        if(!getStartFlag()){
            AccessibilityNodeInfo primeNode = null;
            Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();
            Map<String,String> details = new HashMap<String, String>();
            String itemName="",orderDate="",orderTotal="",paymentMethod="",status="";
            while (iterator.hasNext()) {
                primeNode = iterator.next();
                if(primeNode.getText()!=null && (!primeNode.getText().toString().isEmpty())){
                    switch(primeNode.getText().toString()){
                        case "Order date":
                            primeNode=iterator.next();
                            orderDate=primeNode.getText().toString();
                            break;
                        case "Order total":
                            primeNode=iterator.next();
                            orderTotal=primeNode.getText().toString();
                            break;
                        case "Payment Method":
                            primeNode=iterator.next();
                            paymentMethod=primeNode.getText().toString();
                            break;
                        case "Purchase Deatils":
                            primeNode=iterator.next();
                            primeNode=iterator.next();
                            status=primeNode.getText().toString();
                            primeNode=iterator.next();
                            primeNode=iterator.next();
                            if(primeNode.getText()!=null && (!primeNode.getText().toString().isEmpty())){
                                status="";
                                itemName=primeNode.getText().toString();
                            }else{
                                primeNode=iterator.next();
                                primeNode=iterator.next();
                                itemName=primeNode.getText().toString();
                            }
                            break;
                        case "Delivered":
                        case "On the way":
                            status=primeNode.getText().toString();
                            for(int i=0;i<6;i++) {
                                primeNode = iterator.next();
                            }
                            if(primeNode.getText()!=null && (!primeNode.getText().toString().isEmpty())){
                                itemName=primeNode.getText().toString();

                            }
                            break;
                        case "Dispatched":
                            status=primeNode.getText().toString();
                            for(int i=0;i<5;i++) {
                                primeNode = iterator.next();
                            }
                            if(primeNode.getText()!=null && (!primeNode.getText().toString().isEmpty())){
                                itemName=primeNode.getText().toString();
                            }
                            break;
                        case "Return Details":
                            for(int i=0;i<4;i++) {
                                primeNode = iterator.next();
                            }
                            if(primeNode.getText()!=null && (!primeNode.getText().toString().isEmpty())){
                                status=primeNode.getText().toString();
                            }
                            for(int i=0;i<11;i++) {
                                primeNode = iterator.next();
                            }
                            if(primeNode.getText()!=null && (!primeNode.getText().toString().isEmpty())){
                                itemName=primeNode.getText().toString();
                            }
                            break;
                        default:
                            break;
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
            orders.add(amazonNavUtil.getCounter()-1,details);
            if(value){
                amazonNavUtil.counterIncrement(1);
                Log.e("data","is"+orders.toString());
            }
        }
        if(getStartFlag()){
            amazonData.setOrders(orders);
            AppScrape.getInstance().setData(amazonData);
            value =  Boolean.TRUE;
        }
        return value;
    }

}

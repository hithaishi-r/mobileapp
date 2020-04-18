package com.example.android.appNav.ola;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;
import com.example.android.appscrape.utility.navUtil;

import java.util.Iterator;

public class olaNavUtil {

    private static int counter = -1;
    public static void setCounter(int count) {
        olaNavUtil.counter = count;
    }
    public static int getCounter() {
        return counter;
    }
    public static void counterIncrement(int value) {
        counter+=value;
    }

    private static int maxcount=5;
    public static void maxcountIncrement() { maxcount++; }
    public static int getMaxcount() { return maxcount; }

    private static String id,firstid;
    public static String getId() { return id;}
    public static String getFirstid() {return firstid;}

    public static Boolean clickOrders() {
        int count=-1;
        Boolean flag = Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();

        while (iterator.hasNext() && counter<maxcount) {
            primeNode=iterator.next();
            if (primeNode != null && primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                if (primeNode.getText().toString().contains("CRN") || primeNode.getText().toString().contains("OSN")) {

                    Log.e("node", "array" + primeNode.toString());
                    count++;
                }
            }
            if (count == getCounter()) {
                if (primeNode.getText().toString().equals(id)){
                    counter++;
                    maxcount++;
                }else {
                    navUtil.clickButton(primeNode);
                    id = primeNode.getText().toString();
                    flag = Boolean.TRUE;
                    break;
                }
            }
        }
        if(counter==1){
            firstid=id;
        }
        if(!iterator.hasNext()){
            counter-=count;
            maxcount-=count;
        }
        if(iterator.hasNext() && (!flag)){
            AppScrape.setCount(8);
        }
        return flag;
    }
}

package com.example.android.appNav.amazon;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;
import com.example.android.appscrape.utility.navUtil;

import java.util.Iterator;

public class amazonNavUtil {

    /*private static String filter="Last 6 months";
    public static String getFilter() { return filter; }
    public static void setFilter(String filter) { amazonNavUtil.filter = filter; }*/

    public static int counter = -1;
    public static void setCounter(int count) {
        amazonNavUtil.counter = count;
    }
    public static int getCounter() {
        return counter;
    }
    public static void counterIncrement(int value) {
        counter+=value;
    }

    /*public static boolean checkbutton() {
        Boolean flag = Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();

        while (iterator.hasNext()) {
            primeNode = iterator.next();
            if (getFilter().equals(primeNode.getText())) {
                primeNode = iterator.next();
                setFilter(primeNode.getText().toString());
                if (primeNode.isClickable()) {
                    primeNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    flag=Boolean.TRUE;
                    break;
                } else {
                    setFilter("Last 6 months");
                }
            }
        }
        primeNode = navUtil.findMyButton("Apply");
        if ( navUtil.getBtnFindFlag() ) {
            navUtil.clickButton(primeNode);
        }
        return flag;
    }*/

    public static Boolean clickOrder(){
        int count=0;
        Boolean value = Boolean.FALSE;
        Boolean flag= Boolean.FALSE;
        String filter="Last 6 months";
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();

        while (iterator.hasNext()) {
            primeNode = iterator.next();
            if (filter.equals(primeNode.getText())) {
                flag = Boolean.TRUE;
                Log.e("first if",primeNode.getText().toString());
                primeNode = iterator.next();
            }
            if (flag && (primeNode.getText()!=null) && (!primeNode.getText().toString().isEmpty())) {
                count++;
                Log.e("second if","counter and count "+getCounter()+ ""+count+ ""+primeNode.getText().toString());
            }
            if (count == getCounter()) {
                navUtil.clickButton(primeNode);
                value = Boolean.TRUE;
                break;
            }
        }
        return value;
    }

    public static Boolean clickNthButton(String textField){
        int count=0;
        Boolean value = Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();

        while (iterator.hasNext()) {
            primeNode = iterator.next();
            if (textField.equals(primeNode.getText())) {
                count++;
            }
            if (count == amazonNavUtil.getCounter()) {
                navUtil.clickButton(primeNode);
                value = Boolean.TRUE;
                break;
            }
        }
        return value;
    }
}

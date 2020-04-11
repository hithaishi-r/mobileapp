package com.example.android.appNav.flipkart;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;
import com.example.android.appscrape.utility.navUtil;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class flipkartNavUtil {

    public static int counter = -1;
    public static void setCounter(int count) {
        flipkartNavUtil.counter = count;
    }
    public static int getCounter() {
        return counter;
    }
    public static void counterIncrement(int value) {
        counter+=value;
    }

    public static Boolean clickOrders(){
        int count=-1;
        Boolean flag=Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        AccessibilityNodeInfo prevNode = null;
        AccessibilityNodeInfo pprevNode = null;
        AccessibilityNodeInfo node=null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();

        while (iterator.hasNext() && counter<5) {
            pprevNode = prevNode;
            prevNode = primeNode;
            primeNode = iterator.next();
            if (primeNode != null && primeNode.getText() != null && (!primeNode.getText().toString().isEmpty())) {
                if (primeNode.getText().toString().contains("Supermart Basket")) {
                    Log.e("node","array"+primeNode.toString());
                    count++;
                    node=primeNode;
                } else if (primeNode.getText().toString().contains("Delivered on") || primeNode.getText().toString().equals("Returned")) {
                    if (pprevNode != null && pprevNode.getText() != null && (!pprevNode.getText().toString().isEmpty()) && (!pprevNode.getText().toString().equals("Cancelled"))) {
                        Log.e("node","array"+pprevNode.toString());
                        count++;
                        node=pprevNode;
                    }
                }
            }
            if (count == getCounter()) {
                navUtil.clickButton(node);
                flag = Boolean.TRUE;
                break;
            }
        }
        return flag;
    }
}

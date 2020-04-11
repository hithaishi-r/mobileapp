// Copyright 2016 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example.android.appscrape;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import com.example.android.appscrape.utility.navUtil;

public class AppScrape extends AccessibilityService {

    public static int count = -1;
    public static void setCount(int count) {
        AppScrape.count = count;
    }
    public static int getCount() {
        return count;
    }
    public static void countIncrement(int value) {
        count+=value;
    }
    public static void stopCount() {
        getInstance().broadcastIntent("instruction","home");
        count = -1;
    }

    private static Deque<AccessibilityNodeInfo> leafNodes = new ArrayDeque<>();
    public static Deque<AccessibilityNodeInfo> getLeafNodes() {
        return leafNodes;
    }
    public static void appendLeafNodes(AccessibilityNodeInfo _node) {
        leafNodes.add(_node);
    }
    public static void clearLeafNodes() {
        leafNodes.clear();
    }
    public static void setLeafNodes(Deque<AccessibilityNodeInfo> leafNodes) {
        AppScrape.leafNodes = leafNodes;
    }

    public static AppScrape navObj = null;
    public static AppScrape getInstance() {
        return navObj;
    }

    private Object data = null;
    public Object getData() { return data; }
    public void setData(Object data) {
        this.data = data;
    }


    private static final String TAG = "myDebug";
    private static final String CUSTOM_SWITCH = "com.example.CUSTOM_SWITCH";

    @Override
    public void onCreate() {
        // passing current instance to main activity
        navObj = this;
        if ( leafNodes == null ) {
            leafNodes = new ArrayDeque<AccessibilityNodeInfo>();
        }
        super.onCreate();
    }

    @Override
    protected void onServiceConnected() {
        Log.e(TAG, "onServiceConnected: " + getServiceInfo().toString() );
        super.onServiceConnected();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent){
        Log.e(TAG, "onAccessibilityEvent: counter value : " + count);

        if ( MainActivity.getStartFlag() == Boolean.TRUE ) {
            AccessibilityNodeInfo root = getRootInActiveWindow();

            if ( root != null ) {
                if (root.getPackageName().equals(MainActivity.appPackageName)) {
                    Log.e(TAG, "onAccessibilityEvent: " + accessibilityEvent.getSource());
                    clearLeafNodes();
                    AccessibilityNodeInfo root1 = getRootInActiveWindow();
                    if(MainActivity.appPackageName.equals("com.flipkart.android")) {
                        com.example.android.appNav.flipkart.navCode.stepsExecutor(root1);
                    }else if(MainActivity.appPackageName.equals("in.amazon.mShop.android.shopping")){
                        com.example.android.appNav.amazon.navCode.stepsExecutor(root1);
                    }
                }
            }
        }
    }

    public static void findNodes(AccessibilityNodeInfo _root) {
        AccessibilityNodeInfo root = _root;
        try{
            Thread.sleep(700);
            if (root != null) {
                Log.e(TAG, "onAccessibilityEvent: " + root.getChildCount());
                leafNodes.add(root);
                navUtil.findChildElement(root);
                Log.e(TAG, "onAccessibilityEvent: >>" + leafNodes.size());
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void onInterrupt() {

    }

    // broadcast a custom intent.
    public void broadcastIntent( String name, String broadcastType ){
        Intent intent = new Intent();
        intent.setAction(CUSTOM_SWITCH);
        intent.putExtra(name, broadcastType );
        sendBroadcast(intent);
        Log.e(TAG, "broadcastIntent: sending custom broadcast: " + broadcastType );
    }
}

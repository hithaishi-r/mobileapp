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

package com.example.android.globalactionbarservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GlobalActionBarService extends AccessibilityService {

    private static final String TAG = "myDebug";
    private static Deque<AccessibilityNodeInfo> leafNodes = new ArrayDeque<>();

    @Override
    protected void onServiceConnected() {
        Log.e(TAG, "onServiceConnected: " + getServiceInfo() );

        super.onServiceConnected();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

        AccessibilityNodeInfo root = getRootInActiveWindow();

        boolean flag = Boolean.FALSE;
        if ( AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED == accessibilityEvent.getEventType() ) { flag = Boolean.TRUE; }

        if ( root != null && flag ) {
            if ( root.getChildCount() > 0) {
                Log.e(TAG, "onAccessibilityEvent: " + root.getChildCount());
                leafNodes = new ArrayDeque<>();
                leafNodes.add(root);
                findChildElement(root);

                Log.e(TAG, "onAccessibilityEvent: >>" + leafNodes.size());
            }
        }

    }


    private static void findChildElement( AccessibilityNodeInfo parent ) {
        if(parent!=null) {
            leafNodes.add(parent);
            Log.e(TAG, "node:" + parent.getText());
            if (parent.getChildCount() != 0) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    findChildElement(parent.getChild(i));
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}

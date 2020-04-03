package com.example.android.appscrape.utility;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class navUtil {

    private static final String TAG = "myDebug";

    private static Boolean btnFindFlag = Boolean.FALSE;

    public static Boolean getBtnFindFlag() {
        return btnFindFlag;
    }


    public static AccessibilityNodeInfo findMyButton(String fieldString,String field) {
        btnFindFlag = Boolean.FALSE;
        AccessibilityNodeInfo primeNode = null;
        Iterator<AccessibilityNodeInfo> iterator = AppScrape.getLeafNodes().iterator();
        while (iterator.hasNext()) {

            primeNode = iterator.next();
            String nodeDesc = ("" + primeNode.getContentDescription()).toString();
            String nodeName = ("" + primeNode.getText()).toString();
            Log.e(TAG,"reached");
            if (field.equals("text") && nodeName.equals(fieldString) ) {
                Log.e(TAG, "findMyButton: " + primeNode.toString() );
                btnFindFlag = Boolean.TRUE;
                break;
            } else if (field.equals("content_description") && nodeDesc.equals(fieldString)) {
                Log.e(TAG, "findMyButton: " + primeNode.toString() );
                btnFindFlag = Boolean.TRUE;
                break;
            }
        }

        return primeNode;
    }

    public static Boolean scrollView(AccessibilityNodeInfo root){
        if (root.isScrollable()) {
            return root.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        }

        for (int i = 0; i < root.getChildCount(); i++) {
            if (scrollView(root.getChild(i))) {
                return true;
            }
        }

        return false;
    }

    public static void clickButton(AccessibilityNodeInfo node) {
        if (node != null) {
            if (node.isClickable()) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            } else {
                clickButton(node.getParent());
            }
        }
    }

    public static void findChildElement( AccessibilityNodeInfo parent ) {
        if ( parent != null ) {
            if (parent.getChildCount() == 0) {
                AppScrape.appendLeafNodes(parent);
                Log.e(TAG, "node:" + parent.toString());
            } else {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    findChildElement(parent.getChild(i));
                }
            }
        }
    }

}

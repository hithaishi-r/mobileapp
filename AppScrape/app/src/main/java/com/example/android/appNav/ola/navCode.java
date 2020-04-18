package com.example.android.appNav.ola;

import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;
import com.example.android.appscrape.utility.navUtil;

public class navCode {

    public static void stepsExecutor (AccessibilityNodeInfo root) {
        AccessibilityNodeInfo node = null;
        switch (AppScrape.getCount()) {
            case 0:
                AppScrape.findNodes(root);
                node= navUtil.findMyButton("Open navigation menu","content_description");
                if(navUtil.getBtnFindFlag()){
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 1:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("My Profile","text");
                if(navUtil.getBtnFindFlag()){
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 2:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Safety and Privacy","text");
                if(navUtil.getBtnFindFlag()){
                    if(scrapeOla.scrapePersonalInfo()){
                        node=navUtil.findMyButton("Open navigation menu","content_description");
                        if(navUtil.getBtnFindFlag()){
                            navUtil.clickButton(node);
                            AppScrape.countIncrement(1);
                        }
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 3:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("My Profile","text");
                if(navUtil.getBtnFindFlag()){
                    node=navUtil.findMyButton("Your Rides","text");
                    if(navUtil.getBtnFindFlag()) {
                        navUtil.clickButton(node);
                        olaNavUtil.setCounter(0);
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 4:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("CRN","text_part");
                if(navUtil.getBtnFindFlag()){
                    if(olaNavUtil.clickOrders()) {
                        AppScrape.countIncrement(2);
                    }else{
                        if(navUtil.scrollView(root)) {
                            AppScrape.countIncrement(1);
                        }
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 5:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton(olaNavUtil.getFirstid(),"text");
                if(navUtil.getBtnFindFlag()){
                }else{
                    AppScrape.setCount(4);
                }
                AppScrape.clearLeafNodes();
                break;
            case 6:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("SUPPORT","text");
                if(navUtil.getBtnFindFlag()){
                    node=navUtil.findMyButton("Bill Details","text");
                    if (navUtil.getBtnFindFlag()){
                        if (scrapeOla.scrapeRides()){
                            AppScrape.countIncrement(1);
                        }
                    }else{
                        olaNavUtil.maxcountIncrement();
                        AppScrape.countIncrement(1);
                    }
                    olaNavUtil.counterIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 7:
                AppScrape.findNodes(root);
                node= navUtil.findMyButton("Back","content_description");
                if(navUtil.getBtnFindFlag()){
                    navUtil.clickButton(node);
                    AppScrape.setCount(4);
                }
                AppScrape.clearLeafNodes();
                break;
            case 8:
                scrapeOla.setStartFlag(Boolean.TRUE);
                if(scrapeOla.scrapeRides()) {
                    olaNavUtil.setCounter(-1);
                    scrapeOla.setStartFlag(Boolean.FALSE);
                    AppScrape.countIncrement(1);
                }
                break;
            case 9:
                AppScrape.findNodes(root);
                node= navUtil.findMyButton("android.widget.ImageButton","className");
                if(navUtil.getBtnFindFlag()){
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 10:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Book Your Ride","text");
                if (navUtil.getBtnFindFlag()){
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 11:
                AppScrape.stopCount();
                break;
            default:
        }
    }
}

package com.example.android.appNav.uber;

import android.view.accessibility.AccessibilityNodeInfo;

import com.example.android.appscrape.AppScrape;
import com.example.android.appscrape.utility.navUtil;

public class navCode {

    public static void stepsExecutor (AccessibilityNodeInfo root) {
        AccessibilityNodeInfo node = null;
        switch (AppScrape.getCount()) {
            case 0:
                AppScrape.findNodes(root);
                node= navUtil.findMyButton("Where to?","text");
                if(navUtil.getBtnFindFlag()){
                    node=navUtil.findMyButton("Menu","content_description");
                    if(navUtil.getBtnFindFlag()){
                        navUtil.clickButton(node);
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 1:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Settings","text");
                if (navUtil.getBtnFindFlag()){
                    node=navUtil.findMyButton("Edit Account","content_description");
                    if (navUtil.getBtnFindFlag()){
                        navUtil.clickButton(node);
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 2:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Verified","text");
                if (navUtil.getBtnFindFlag()){
                    if(scrapeUber.scrapePersonalInfo()){
                        node=navUtil.findMyButton("Back","content_description");
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
                node= navUtil.findMyButton("Where to?","text");
                if(navUtil.getBtnFindFlag()){
                    node=navUtil.findMyButton("Menu","content_description");
                    if(navUtil.getBtnFindFlag()){
                        navUtil.clickButton(node);
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 4:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Settings","text");
                if (navUtil.getBtnFindFlag()){
                    node=navUtil.findMyButton("Your Trips","text");
                    if (navUtil.getBtnFindFlag()){
                        navUtil.clickButton(node);
                        try {
                            Thread.sleep(1000);
                        }catch (Exception e) {
                            System.out.println(e);
                        }
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 5:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Choose a trip","text");
                if (navUtil.getBtnFindFlag()){
                    if(AppScrape.getLeafNodes().size()<6){
                    } else {
                        if (AppScrape.getLeafNodes().size() >= 8) {
                            if (scrapeUber.scrapeRides()) {
                                AppScrape.countIncrement(1);
                            } else {
                                AppScrape.countIncrement(2);
                            }
                        } else {
                            AppScrape.countIncrement(2);
                        }
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 6:
                AppScrape.findNodes(root);
                if(navUtil.scrollView(root)){
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e) {
                        System.out.println(e);
                    }
                    AppScrape.setCount(5);
                }else {
                    AppScrape.countIncrement(1);
                }
                break;
            case 7:
                scrapeUber.setStartFlag(Boolean.TRUE);
                if (scrapeUber.scrapeRides()){
                    scrapeUber.setStartFlag(Boolean.FALSE);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 8:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Back","content_description");
                if (navUtil.getBtnFindFlag()){
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 9:
                AppScrape.stopCount();
                break;
            default:
        }
    }

}

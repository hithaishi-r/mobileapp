package com.example.android.appNav.flipkart;


import android.view.accessibility.AccessibilityNodeInfo;
import com.example.android.appscrape.AppScrape;
import com.example.android.appscrape.ShowData;
import com.example.android.appscrape.utility.navUtil;



public class navCode {


    /*static Context context;

    public navCode (Context context){
        this.context=context;
    }*/

    public static void stepsExecutor (AccessibilityNodeInfo root) {
        AccessibilityNodeInfo node = null;
        switch(AppScrape.getCount()){
            case 0:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Open Drawer","content_description");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }else{
                    node = navUtil.findMyButton("Drawer","content_description");
                    if ( navUtil.getBtnFindFlag() ) {
                        navUtil.clickButton(node);
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 1:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Fashion","text");
                if ( navUtil.getBtnFindFlag() ) {
                    if((navUtil.scrollView(root)));
                }else{
                    AppScrape.clearLeafNodes();
                    AppScrape.findNodes(root);
                    node = navUtil.findMyButton("My Account","text");
                    if ( navUtil.getBtnFindFlag() ) {
                        navUtil.clickButton(node);
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 2:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Flipkart Plus","text");
                if ( navUtil.getBtnFindFlag() ) {
                    if(scrapeFlipkart.scrapePersonalInfo()){
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 3:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Open Drawer","content_description");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 4:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Drawer","content_description");
                if(navUtil.getBtnFindFlag()){
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 5:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("My Orders","text");
                if(navUtil.getBtnFindFlag()){
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 6:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Select the item you want to track, return or need help with","text");
                if(navUtil.getBtnFindFlag()){
                    node=navUtil.findMyButton("You have no orders","text");
                    if(navUtil.getBtnFindFlag()) {
                        ShowData.setMessage("No orders made");
                        scrapeFlipkart.setStartFlag(Boolean.TRUE);
                        if(scrapeFlipkart.scrapeOrders()) {
                            flipkartNavUtil.setCounter(-1);
                            scrapeFlipkart.setStartFlag(Boolean.FALSE);
                            AppScrape.countIncrement(4);
                        }
                    }else{
                        flipkartNavUtil.setCounter(0);
                        AppScrape.countIncrement(1);
                        }
                }
                AppScrape.clearLeafNodes();
                break;
            case 7:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Select the item you want to track, return or need help with","text");
                if(navUtil.getBtnFindFlag()) {
                    if (flipkartNavUtil.clickOrders()){
                        AppScrape.countIncrement(1);
                    } else {
                        scrapeFlipkart.setStartFlag(Boolean.TRUE);
                        if (scrapeFlipkart.scrapeOrders()) {
                            flipkartNavUtil.setCounter(-1);
                            scrapeFlipkart.setStartFlag(Boolean.FALSE);
                            AppScrape.countIncrement(3);
                        }
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 8:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Total Amount","text");
                if(navUtil.getBtnFindFlag()){
                    if(scrapeFlipkart.scrapeOrders()){
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 9:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Order Details","text");
                if(navUtil.getBtnFindFlag()) {
                    node = navUtil.findMyButton("Open Drawer", "content_description");
                    if (navUtil.getBtnFindFlag()) {
                        navUtil.clickButton(node);
                        AppScrape.setCount(7);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 10:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Select the item you want to track, return or need help with","text");
                if(navUtil.getBtnFindFlag()) {
                    node = navUtil.findMyButton("Open Drawer", "content_description");
                    if (navUtil.getBtnFindFlag()) {
                        navUtil.clickButton(node);
                        AppScrape.countIncrement(1);
                    }
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

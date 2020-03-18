package com.example.android.appNav.flipkart;


import android.view.accessibility.AccessibilityNodeInfo;
import com.example.android.appscrape.AppScrape;
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
                node = navUtil.findMyButton("Open Drawer");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.clearLeafNodes();
                    AppScrape.countIncrement(1);
                }
                break;
            case 1:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Fashion");
                if ( navUtil.getBtnFindFlag() ) {
                    AppScrape.clearLeafNodes();
                    AppScrape.countIncrement(1);
                }else{
                    node = navUtil.findMyButton("My Account");
                    if ( navUtil.getBtnFindFlag() ) {
                        AppScrape.clearLeafNodes();
                        AppScrape.countIncrement(2);
                    }
                }
                break;
            case 2:
                AppScrape.findNodes(root);
                if (navUtil.scrollView(root)) {
                    AppScrape.countIncrement(1);
                }
                break;
            case 3:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("My Account");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.clearLeafNodes();
                    AppScrape.countIncrement(1);
                }
                break;
            case 4:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Flipkart Plus");
                if ( navUtil.getBtnFindFlag() ) {
                    AppScrape.clearLeafNodes();
                    AppScrape.findNodes(root);
                    if(scrapeflipkart.scrape()){
                        AppScrape.clearLeafNodes();
                        AppScrape.countIncrement(1);
                    }
                }
                break;
            /*case 5:
                Intent intent=new Intent();
                intent.setAction("CUSTOM_SWITCH");
                context.sendBroadcast(intent);
                AppScrape.countIncrement(1);
                MainActivity.setStartFlag(Boolean.FALSE);
                break;*/
            default:
        }
    }
}

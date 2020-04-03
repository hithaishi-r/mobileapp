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
                node = navUtil.findMyButton("Open Drawer","content_description");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.clearLeafNodes();
                    AppScrape.countIncrement(1);
                }else{
                    node = navUtil.findMyButton("Drawer","content_description");
                    if ( navUtil.getBtnFindFlag() ) {
                        navUtil.clickButton(node);
                        AppScrape.clearLeafNodes();
                        AppScrape.countIncrement(1);
                    }
                }
                break;
            case 1:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Fashion","text");
                if ( navUtil.getBtnFindFlag() ) {
                    AppScrape.clearLeafNodes();
                    AppScrape.countIncrement(1);
                }else{
                    node = navUtil.findMyButton("My Account","text");
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
                node = navUtil.findMyButton("My Account","text");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.clearLeafNodes();
                    AppScrape.countIncrement(1);
                }
                break;
            case 4:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Flipkart Plus","text");
                if ( navUtil.getBtnFindFlag() ) {
                    AppScrape.clearLeafNodes();
                    AppScrape.findNodes(root);
                    if(scrapeflipkart.scrape()){
                        AppScrape.clearLeafNodes();
                        AppScrape.countIncrement(1);
                    }
                }
                break;
            case 5:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Open Drawer","content_description");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.clearLeafNodes();
                    AppScrape.countIncrement(1);
                }
                break;
            case 6:
                AppScrape.stopCount();
                break;
            default:
        }
    }
}

package com.example.android.appNav.amazon;

import android.view.accessibility.AccessibilityNodeInfo;
import com.example.android.appscrape.AppScrape;
import com.example.android.appscrape.ShowData;
import com.example.android.appscrape.utility.navUtil;

public class navCode {
    public static void stepsExecutor (AccessibilityNodeInfo root) {
        AccessibilityNodeInfo node = null;
        switch (AppScrape.getCount()) {
            case 0:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Navigation panel, button, double tap to open side panel","content_description");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 1:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Your Orders","text");
                if ( navUtil.getBtnFindFlag() ) {
                    if(scrapeAmazon.scrapeName()){
                        navUtil.clickButton(node);
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 2:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Filter","text");
                if ( navUtil.getBtnFindFlag() ){
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 3:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("View order details","text");
                if(navUtil.getBtnFindFlag()){
                    AppScrape.countIncrement(7);
                    amazonNavUtil.setCounter(1);
                }else{
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 4:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("No orders or transactions found. Please select a different time period or order type.","text");
                if(navUtil.getBtnFindFlag()){
                    ShowData.setMessage("No orders in the last 6 months");
                    scrapeAmazon.setStartFlag(Boolean.TRUE);
                    if(scrapeAmazon.scrapeOrders()) {
                        scrapeAmazon.setStartFlag(Boolean.FALSE);
                        AppScrape.countIncrement(10);
                    }
                }else{
                    AppScrape.countIncrement(1);
                    amazonNavUtil.setCounter(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 5:
                AppScrape.findNodes(root);
                if(amazonNavUtil.clickOrder()){
                    AppScrape.countIncrement(1);
                }else{
                    scrapeAmazon.setStartFlag(Boolean.TRUE);
                    if(scrapeAmazon.scrapeOrders()) {
                        amazonNavUtil.setCounter(-1);
                        scrapeAmazon.setStartFlag(Boolean.FALSE);
                        AppScrape.countIncrement(9);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 6:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("View order details","text");
                if(navUtil.getBtnFindFlag()){
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 7:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Order date","text");
                if(navUtil.getBtnFindFlag()){
                    if(scrapeAmazon.scrapeOrders()){
                        node = navUtil.findMyButton("Navigation panel, button, double tap to open side panel","content_description");
                        if ( navUtil.getBtnFindFlag() ) {
                            navUtil.clickButton(node);
                            AppScrape.countIncrement(1);
                        }
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 8:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Your Account","text");
                if ( navUtil.getBtnFindFlag() ) {
                    node=navUtil.findMyButton("Your Orders","text");
                    if(navUtil.getBtnFindFlag()){
                        navUtil.clickButton(node);
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 9:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Filter","text");
                if(navUtil.getBtnFindFlag()){
                    AppScrape.setCount(5);
                }
                AppScrape.clearLeafNodes();
                break;
            case 10:
                AppScrape.findNodes(root);
                if(amazonNavUtil.clickNthButton("View order details")){
                    AppScrape.countIncrement(1);
                }else{
                    scrapeAmazon.setStartFlag(Boolean.TRUE);
                    if(scrapeAmazon.scrapeOrders()) {
                        amazonNavUtil.setCounter(-1);
                        scrapeAmazon.setStartFlag(Boolean.FALSE);
                        AppScrape.countIncrement(4);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 11:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Order date","text");
                if(navUtil.getBtnFindFlag()) {
                    if (scrapeAmazon.scrapeOrders()) {
                        node = navUtil.findMyButton("Navigation panel, button, double tap to open side panel","content_description");
                        if ( navUtil.getBtnFindFlag() ) {
                            navUtil.clickButton(node);
                            AppScrape.countIncrement(1);
                        }
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 12:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Your Account","text");
                if ( navUtil.getBtnFindFlag() ) {
                    node=navUtil.findMyButton("Your Orders","text");
                    if(navUtil.getBtnFindFlag()) {
                        navUtil.clickButton(node);
                        AppScrape.countIncrement(1);
                    }
                }
                AppScrape.clearLeafNodes();
                break;
            case 13:
                AppScrape.findNodes(root);
                node=navUtil.findMyButton("Filter","text");
                if(navUtil.getBtnFindFlag()){
                    AppScrape.setCount(10);
                }
                AppScrape.clearLeafNodes();
                break;
            case 14:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Navigation panel, button, double tap to open side panel","content_description");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 15:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Home","text");
                if ( navUtil.getBtnFindFlag() ) {
                    navUtil.clickButton(node);
                    AppScrape.countIncrement(1);
                }
                AppScrape.clearLeafNodes();
                break;
            case 16:
                AppScrape.findNodes(root);
                node = navUtil.findMyButton("Search","text");
                if ( navUtil.getBtnFindFlag() ) {
                    AppScrape.stopCount();
                }
                break;
            default:
                break;
        }
    }
}

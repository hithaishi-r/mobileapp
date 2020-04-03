package com.example.android.appNav.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class data {
    private String name;
    private List<Map<String, String>> orders = new ArrayList<Map<String, String>>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, String>> getOrders() {
        return orders;
    }

    public void setOrders(List<Map<String, String>> orders) {
        this.orders = orders;
    }
}

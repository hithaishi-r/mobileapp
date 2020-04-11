package com.example.android.appNav.flipkart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class data {

    private String name;
    private String number;
    private String email;
    private List<Map<String, String>> orders = new ArrayList<Map<String, String>>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Map<String, String>> getOrders() {
        return orders;
    }

    public void setOrders(List<Map<String, String>> orders) {
        this.orders = orders;
    }
}

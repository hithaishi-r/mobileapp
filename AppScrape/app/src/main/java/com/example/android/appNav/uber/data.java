package com.example.android.appNav.uber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class data {

    private String name;
    private String number;
    private String email;
    private List<Map<String, String>> rides = new ArrayList<Map<String, String>>();


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

    public List<Map<String, String>> getRides() {
        return rides;
    }

    public void setRides(List<Map<String, String>> rides) {
        this.rides = rides;
    }

}

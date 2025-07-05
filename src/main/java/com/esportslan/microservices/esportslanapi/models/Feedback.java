/*
 * Copyright (c) 2025.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.esportslan.microservices.esportslanapi.models;

public class Feedback {
    private String date;
    private boolean flag;
    private String email;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Feedback(String date, boolean flag, String email) {
        this.date = date;
        this.flag = flag;
        this.email = email;
    }

    public Feedback() {
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "date='" + date + '\'' +
                ", flag=" + flag +
                ", email='" + email + '\'' +
                '}';
    }
}

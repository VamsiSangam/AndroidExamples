package com.vamsisangam.androidexamples;

/**
 * Created by Vamsi on 31-05-2017.
 */

public class Company {
    private String name, phone, url, logo;

    public Company(String name, String phone, String url, String logo) {
        this.name = name;
        this.phone = phone;
        this.url = url;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

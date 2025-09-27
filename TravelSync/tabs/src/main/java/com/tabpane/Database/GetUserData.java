
package com.tabpane.Database;

import java.time.LocalDate;

public class GetUserData {
    private String name;
    private String date;
    private String gender;
    private String address;
    private String postalCode;
    private String username;
    private String profession;
    private String password;



    public GetUserData(String name, String date, String gender, String address, String postalCode, String username, String profession, String password) {
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.address = address;
        this.postalCode = postalCode;
        this.username = username;
        this.profession = profession;
        this.password = password;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


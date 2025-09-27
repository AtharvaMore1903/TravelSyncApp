package com.tabpane.controller;

import javax.print.DocFlavor.STRING;

public class GetPostData {
    private String place;
    private String dateStr;
    private int buddiesRequired;
    private int totalMembers;
    private int noOfDays;
    private String category;
    private boolean experience;
    private boolean vehicleAvailable;
    private String vehicleName;
    private String caption;
    private String USERNAME;


    public GetPostData(String place, String dateStr, int buddiesRequired, int totalMembers, int noOfDays, String category,
            boolean experience, boolean vehicleAvailable, String vehicleName, String caption,String USERNAME) {
        this.place = place;
        this.dateStr = dateStr;
        this.buddiesRequired = buddiesRequired;
        this.totalMembers = totalMembers;
        this.noOfDays = noOfDays;
        this.category = category;
        this.experience = experience;
        this.vehicleAvailable = vehicleAvailable;
        this.vehicleName = vehicleAvailable ? vehicleName : null;
        this.caption = caption;
        this.USERNAME = USERNAME;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
        System.out.println("PlaceName: "+ place);
    }

    public String getDate() {
        return dateStr;
    }

    public void setDate(String dateStr) {
        this.dateStr = dateStr;
        System.out.println("Date: "+ dateStr);
    }

    public int getBuddiesRequired() {
        return buddiesRequired;

    }

    public void setBuddiesRequired(int buddiesRequired) {
        this.buddiesRequired = buddiesRequired;
        System.out.println("Required Buddies: "+ buddiesRequired);
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
        System.out.println("Total Members: "+ totalMembers);
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
        System.out.println("No of Days : " + noOfDays);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        System.out.println("Category of Place: " + category);
    }

    public boolean isExperience() {
        return experience;
    }

    public void setExperience(boolean experience) {
        this.experience = experience;
        System.out.println("Is Experienced: " + experience);
    }

    public boolean isVehicleAvailable() {
        return vehicleAvailable;
    }

    public void setVehicleAvailable(boolean vehicleAvailable) {
        this.vehicleAvailable = vehicleAvailable;
        System.out.println("is Vehicle availabe:  "+ vehicleAvailable);
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
        System.out.println("Vehicle Name: "+ vehicleName);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;

    }

    public String getUSERNAME(){
        return USERNAME;
    }
    public void setUSERNAME(String USERNAME){
        this.USERNAME = USERNAME;

    }
}

package com.tabpane.controller;

import javafx.application.Application;
public class Main {
    public static void main(String[] args) {
        try{
        Class.forName("com.tabpane.Database.FirebaseInitialization");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Hello world!");
        Application.launch(InitializeApp.class,args);
    }
}
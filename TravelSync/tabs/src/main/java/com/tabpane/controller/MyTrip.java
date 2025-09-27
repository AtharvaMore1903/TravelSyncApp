package com.tabpane.controller;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyTrip {
        private Stage primaryStage;
        private VBox mainVBox;

    public MyTrip(Stage primaryStage){
        this.primaryStage = primaryStage;


    }

    private void initMyTripScene(){
        
    }

    public VBox getRoot(){
        return mainVBox;
    }


}

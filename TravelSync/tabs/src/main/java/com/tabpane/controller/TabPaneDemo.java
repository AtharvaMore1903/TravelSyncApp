package com.tabpane.controller;

import java.io.FileInputStream;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TabPaneDemo {

    private Stage primaryStage;
    private Scene scene;
    private VBox contenBox;
    private String username;
    private String place;
    private String dateStr;
    private int buddiesRequired;
    private int totalMembers;
    private int noOfDaysINt;
    private String category;
    private boolean experience;
    private boolean vehicleAvailable;
    private String vehicleName;
    private String caption;


    public TabPaneDemo(Stage primaryStage,String username, String place,String dateStr,int buddiesRequired,int totalMembers,int noOfDaysINt,String category,boolean experience,boolean vehicleAvailable,String vehicleName,String caption) {
        this.primaryStage = primaryStage;
        this.username = username;
        this.place = place;
        this.dateStr = dateStr;
        this.buddiesRequired = buddiesRequired;
        this.totalMembers = totalMembers;
        this.noOfDaysINt = noOfDaysINt;
        this.category = category;
        this.experience = experience;
        this.vehicleAvailable = vehicleAvailable;
        this.vehicleName = vehicleAvailable ? vehicleName : null;
        this.caption = caption;
        initTabScene();
    }

    public Scene initTabScene() {

        String cs = "-fx-background-radius: 10; -fx-border-radius: 10; -fx-padding: 5;-fx-background-color:BLACK";

        Label travelSyncLabel = new Label("TravelSync");
        travelSyncLabel.setStyle("-fx-background-color:transparent; -fx-text-fill:black;-fx-padding:50;-fx-font-size:30px;-fx-font-family:'century';");

        Image mainImage = new Image("travelsync.jpg");

        ImageView iv = new ImageView();
        iv.setImage(mainImage);
        iv.setFitWidth(300);
        iv.setFitHeight(200);
        iv.setPreserveRatio(true);

        HBox labelHBox = new HBox(travelSyncLabel);
        labelHBox.setAlignment(Pos.CENTER);

        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(150);
        searchButton.setPrefHeight(60);
        searchButton.setStyle("-fx-background-color:#2C3E50");
        searchButton.setFont(Font.font(null, FontWeight.BOLD, 25));
        searchButton.setTextFill(Color.WHITE);
        searchButton.setStyle(cs);
        
        Button createButton = new Button("Create");
        createButton.setPrefWidth(150);
        createButton.setPrefHeight(60);
        createButton.setStyle("-fx-background-color:#2C3E50");
        createButton.setFont(Font.font(null, FontWeight.BOLD, 25));
        createButton.setTextFill(Color.WHITE);
        createButton.setStyle(cs);

        Button profileButton = new Button("Profile");
        profileButton.setPrefWidth(150);
        profileButton.setPrefHeight(60);
        profileButton.setStyle("-fx-background-color:#2C3E50");
        profileButton.setFont(Font.font(null, FontWeight.BOLD, 25));
        profileButton.setTextFill(Color.WHITE);
        profileButton.setStyle(cs);

        Button aboutButton = new Button("About us");
        aboutButton.setPrefWidth(150);
        aboutButton.setPrefHeight(60);
        aboutButton.setStyle("-fx-background-color:#2C3E50");
        aboutButton.setFont(Font.font(null, FontWeight.BOLD, 25));
        aboutButton.setTextFill(Color.WHITE);
        aboutButton.setStyle(cs);

        SearchController2 searchController2 = new SearchController2(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
        PostController postController = new PostController(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
        ProfileUserTrial profileUserTrial = new ProfileUserTrial(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
        AboutUs aboutUs = new AboutUs(primaryStage);

        VBox buttonBox = new VBox(20,iv,searchButton,createButton,profileButton,aboutButton);
        buttonBox.setPadding(new Insets(20));
        buttonBox.setMinWidth(330);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-background-color:transparent");
        buttonBox.setOpacity(0.8);
        contenBox = new VBox();
        contenBox.setMinSize(1500, 1200);
        //contenBox.setAlignment(Pos.CENTER);
        contenBox.getChildren().add(searchController2.getRoot());
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                contenBox.getChildren().clear();
                contenBox.getChildren().add(searchController2.getRoot());
                
            }
        });
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                contenBox.getChildren().clear();
                //contenBox.setMinSize(postController.getRoot().getWidth(), postController.getRoot().getHeight());
                contenBox.getChildren().add(postController.getRoot());
                postController.getRoot().setMinSize(1300, primaryStage.getHeight());
            }
        });
        profileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                contenBox.getChildren().clear();
                contenBox.getChildren().add(profileUserTrial.getRoot());
                profileUserTrial.getRoot().setMinSize(1500, primaryStage.getHeight());
            }
        });
        aboutButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                contenBox.getChildren().clear();
                contenBox.getChildren().add(aboutUs.getRoot());
                //contenBox.setMinSize(1500, 1200);
                aboutUs.getRoot().setMinSize(1300, primaryStage.getHeight());
            }
        });
        
        VBox leftHVBox = new VBox(buttonBox);
        leftHVBox.setMinHeight(primaryStage.getHeight());
        leftHVBox.setStyle("-fx-background-color:white");
        labelHBox.setOpacity(0.8);

        HBox mainHBox = new HBox(leftHVBox,contenBox); 
        Image image = new Image("bus.jpg", primaryStage.getWidth(), primaryStage.getHeight(), false, false);
        BackgroundImage bkimg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, null);

        //mainHBox.setAlignment(Pos.CENTER);                

        mainHBox.setBackground(new Background(bkimg));
        
        scene = new Scene(mainHBox);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        return scene;
        // primaryStage.setMaximized(true);
        // primaryStage.setFullScreen(true);
    }

    public VBox getConotenVBox(){
        return contenBox;
    }

}

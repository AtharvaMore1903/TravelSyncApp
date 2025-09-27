package com.tabpane.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PostSuccess  {

    private Stage primaryStage;
    private Scene scene;
    private VBox vb;
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

    public PostSuccess(Stage primaryStage,String usernamae, String place,String dateStr,int buddiesRequired,int totalMembers,int noOfDaysINt,String category,boolean experience,boolean vehicleAvailable,String vehicleName,String caption){
        this.username = usernamae;
        this.primaryStage = primaryStage;
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
        initPostSuccessScene();
    }

    
    public Scene initPostSuccessScene ( )   {
        //primaryStage.setTitle("Account Created Successfully");
       // primaryStage.setFullScreen(true);
        
        Label welcomelb = new Label("Post Created Successfully !!!"); 
        welcomelb.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        welcomelb.setTextFill(Color.BLACK);
        welcomelb.setAlignment(Pos.CENTER);

        ImageView tick = new ImageView("tickpost.jpeg");
        tick.setPreserveRatio(true);
        tick.setFitHeight(150);
        tick.setFitWidth(150);

        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nextButton.setPrefSize(120, 50);
        nextButton.setStyle("-fx-background-color: #007BFF;");
        nextButton.setTextFill(Color.WHITE);
        nextButton.setOnMouseEntered(e->{
            nextButton.setScaleX(1.10);
        });
        nextButton.setOnMouseExited(e->{
            nextButton.setScaleX(1.05);
        });

        TabPaneDemo tabPaneDemo = new TabPaneDemo(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                primaryStage.setScene(tabPaneDemo.initTabScene());
            }
        });

        

         vb= new VBox(50,tick,welcomelb,nextButton);
        vb.setStyle("-fx-background-color:WHITE;");
        vb.setAlignment(Pos.CENTER);

        scene = new Scene(vb);

        primaryStage.setScene(scene);
        return scene;
        
    }

    
    
}

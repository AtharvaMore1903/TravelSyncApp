package com.tabpane.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LandingPage  {

    private Scene scene;
    private Stage primaryStage;
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
   
    public LandingPage(Stage primaryStage,String username, String place,String dateStr,int buddiesRequired,int totalMembers,int noOfDaysINt,String category,boolean experience,boolean vehicleAvailable,String vehicleName,String caption){
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
        initLandingScene();


    }

    
    private void initLandingScene() {
        //primaryStage.setTitle("LANDING PAGE");
        //primaryStage.setFullScreen(true);

        String cs = "-fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 5;-fx-background-color:BLACK";


        // Load the background image
        Image backgroundImage = new Image("/bus.jpg");

        // Create an ImageView for the background image
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setFitWidth(1200);
        backgroundImageView.setFitHeight(800);

        // Create the main content
        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20));
        contentBox.setAlignment(Pos.CENTER);

        // Create a StackPane to hold the background image and the main content
        BackgroundFill bkfill = new BackgroundFill(Color.NAVY, new CornerRadii(20), null);

        Button loginButton = new Button("Login");
        loginButton.setMinSize(100, 40);
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle(cs);
        loginButton.setBackground(new Background(bkfill));
        loginButton.setOnMouseEntered(e->{
            loginButton.setScaleX(1.05);
            loginButton.setScaleY(1.05);
        });
        loginButton.setOnMouseExited(e->{
          loginButton.setScaleX(1);
          loginButton.setScaleY(1);
        });

        LoginTrial2 loginTrial2 = new LoginTrial2(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                primaryStage.setScene(loginTrial2.getLoginScene2());
            }
        });

        
        

        Button signUpButton = new Button("Create Account");
        signUpButton.setMinSize(120, 40);
        signUpButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        signUpButton.setTextFill(Color.WHITE);
        signUpButton.setStyle(cs);
        signUpButton.setBackground(new Background(bkfill));
        signUpButton.setOnMouseEntered(e->{
            signUpButton.setScaleX(1.05);
            signUpButton.setScaleY(1.05);
        });
        signUpButton.setOnMouseExited(e->{
            signUpButton.setScaleX(1);
            signUpButton.setScaleY(1);
        });

        CreateAcc createAcc = new CreateAcc(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                primaryStage.setScene(createAcc.initCreateScene());
            }
        });



        HBox buttonBox = new HBox(30, loginButton, signUpButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setPadding(new Insets(20));

        ImageView natureImage1 = new ImageView(new Image("Image1.jpg"));
        natureImage1.setFitWidth(350);
        natureImage1.setStyle("-fx-border-width:3px;-fx-border-radius:20px;-fx-background-radius:10px;");
        natureImage1.setFitHeight(250);
    
        
        ImageView natureImage2 = new ImageView(new Image("image2.jpg"));
        natureImage2.setFitWidth(350);
        natureImage2.setFitHeight(250);
        
        ImageView natureImage3 = new ImageView(new Image("image3.jpg"));
        natureImage3.setFitWidth(350);
        natureImage3.setFitHeight(250);
        
        // DropShadow dropShadow = new DropShadow();
        // dropShadow.setOffsetY(8.0f);
        // dropShadow.setColor(Color.web("BLACK"));

        Text mainText = new Text("Find Your ");
        mainText.setFont(Font.font("Arisl",FontWeight.BOLD, 50));
        mainText.setFill(Color.DARKRED);
       // mainText.setEffect(dropShadow);
       
        Text mainText2 = new Text("       TRAVEL BUDDY !!! ");
        mainText2.setFont(Font.font("Arial", FontWeight.BOLD, 90));
        mainText2.setFill(Color.DARKRED);
        //mainText2.setEffect(dropShadow);
       
        VBox mTBox = new VBox(mainText,mainText2);
        mTBox.setPadding(new Insets(0, 20, 100, 100));
        
        Text travelText1 = new Text("     With");
        travelText1.setFont(Font.font("Comic Sans MS",FontWeight.BOLD, 50));
        travelText1.setFill(Color.GREY);
        //travelText1.setEffect(dropShadow);

        Text travelText2 = new Text("                TravelSync");
        travelText2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 100));
        travelText2.setFill(Color.BLACK);
        //travelText2.setEffect(dropShadow);
        Text travText3=new Text("                                                Travel Beyond Boundaries...!");
        travText3.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        travText3.setFill(Color.GREY);


        VBox textBox = new VBox(10,mTBox, travelText1, travelText2,travText3);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(0, 0, 500, 0));

        VBox imagesBox = new VBox(30, natureImage1, natureImage2,natureImage3);
        imagesBox.setAlignment(Pos.CENTER_RIGHT);
        imagesBox.setPadding(new Insets(20, 0200, 150, 0));
        

        HBox hb1 = new HBox(400,textBox,imagesBox);


        StackPane root = new StackPane(backgroundImageView,hb1,buttonBox);
        //root.getChildren().addAll();

        // Create the scene and set it on the stage
        scene = new Scene(root);
        // primaryStage.setScene(scene);
        // Rectangle2D full = Screen.getPrimary().getVisualBounds();
        // primaryStage.setHeight(full.getHeight());
        // primaryStage.setWidth(full.getWidth());
        // //primaryStage.setMaximized(true);
        //  primaryStage.show();

        // Bind the background image size to the scene size
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());

        primaryStage.setScene(scene);
        primaryStage.setTitle("TravelSync");
        primaryStage.show();
    }

    public Scene getLandingScene(){
        return scene;
    }

    

    // public static void main(String[] args) {
    //     launch(args);
    // }
}
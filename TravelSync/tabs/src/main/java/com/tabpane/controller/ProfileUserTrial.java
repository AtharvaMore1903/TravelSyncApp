package com.tabpane.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ProfileUserTrial {

    private ImageView profileImageView;
    private Image defaultImage;
    private Button uploadRemoveButton;
    private Button logoutButton;
    private boolean isPhotoUploaded = false;
    private Stage primaryStage;
    private VBox mainLayout;
    private String username;
    public static Firestore dbFirestore;

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

    public ProfileUserTrial(Stage primaryStage, String username, String place, String dateStr, int buddiesRequired,
            int totalMembers, int noOfDaysINt, String category, boolean experience, boolean vehicleAvailable,
            String vehicleName, String caption) {

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
        inittrialprofile();
    }

    private void inittrialprofile() {
        // primaryStage.setResizable(true);
        //primaryStage.setTitle("User Profile");

        // Load default image
        defaultImage = loadImage("/profile.jpg");
        if (defaultImage == null) {
            System.err.println("Default image not found.");
            return;
        }

        // Profile Section
        profileImageView = new ImageView(defaultImage);
        profileImageView.setFitHeight(200);
        profileImageView.setFitWidth(200);
        profileImageView.setPreserveRatio(false);

        Circle clip = new Circle(100, 100, 90);
        profileImageView.setClip(clip);

        uploadRemoveButton = new Button("Upload Photo");
        uploadRemoveButton.setFont(javafx.scene.text.Font.font("century", FontWeight.BOLD, 15));
        uploadRemoveButton.setTextFill(Color.WHITE);
        uploadRemoveButton.setPrefWidth(150);
        uploadRemoveButton.setStyle("-fx-background-color: #2C3E50;");
        uploadRemoveButton.setOnMouseEntered(e -> {
            uploadRemoveButton.setScaleX(1.05);
            uploadRemoveButton.setScaleY(1.05);
        });
        uploadRemoveButton.setOnMouseExited(e -> {
            uploadRemoveButton.setScaleX(1);
            uploadRemoveButton.setScaleY(1);
        });
        uploadRemoveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isPhotoUploaded) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters()
                            .addAll(new FileChooser.ExtensionFilter("Files", "*.jpg", "*.jpeg", "*.gif"));
                    File selectedFile = fileChooser.showOpenDialog(primaryStage);
                    if (selectedFile != null) {
                        try {
                            FileInputStream inputStream = new FileInputStream(selectedFile);
                            Image image = new Image(inputStream);
                            profileImageView.setImage(image);
                            uploadRemoveButton.setText("Remove Photo");
                            isPhotoUploaded = true;
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    profileImageView.setImage(defaultImage);
                    uploadRemoveButton.setText("Upload Photo");
                    isPhotoUploaded = false;
                }
            }
        });

        logoutButton = new Button("Logout");
        logoutButton.setFont(javafx.scene.text.Font.font("century", FontWeight.BOLD, 15));
        logoutButton.setTextFill(Color.WHITE);
        logoutButton.setPrefWidth(150);
        logoutButton.setStyle("-fx-background-color:BLACK ;");
        logoutButton.setOnMouseEntered(e -> {
            logoutButton.setScaleX(1.05);
            logoutButton.setScaleY(1.05);
        });
        logoutButton.setOnMouseExited(e -> {
            logoutButton.setScaleX(1);
            logoutButton.setScaleY(1);
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                LoginTrial2 loginTrial2 = new LoginTrial2(primaryStage, username, place, dateStr, buddiesRequired, totalMembers, noOfDaysINt, category, experience, vehicleAvailable, vehicleName, caption);
                primaryStage.setScene(loginTrial2.getLoginScene2());
            }
        });

        VBox profileBox = new VBox(10, profileImageView, uploadRemoveButton,logoutButton);
        profileBox.setAlignment(Pos.CENTER);
        // profileBox.setMinWidth(500);
        profileBox.setPadding(new Insets(10));
        profileBox.setStyle("-fx-background-color:transparent; -fx-background-radius: 10px;");

        Label usernameLabel = new Label();
        Label nameLabel = new Label();
        try{
        
            DocumentReference c2w_pi_docRef = dbFirestore.collection("Users").document(username); 
    
            ApiFuture<DocumentSnapshot> c2w_pi_future = c2w_pi_docRef.get();
            DocumentSnapshot doc = c2w_pi_future.get();
            usernameLabel = new Label((String)doc.getData().get("username"));
            nameLabel = new Label((String)doc.getData().get("name"));
            
            }
            catch(Exception e){
                e.printStackTrace();
        }
        usernameLabel.setFont(javafx.scene.text.Font.font("elephant", FontWeight.EXTRA_BOLD, 20));
        usernameLabel.setTextFill(Color.web("#2C3E50"));

       
        nameLabel.setFont(javafx.scene.text.Font.font("century", FontWeight.BOLD, 18));
        nameLabel.setTextFill(Color.web("#2C3E50"));

        Label bioLabel = new Label("Bio:");
        bioLabel.setFont(javafx.scene.text.Font.font("century", FontWeight.EXTRA_BOLD, 20));
        bioLabel.setTextFill(Color.web("#2C3E50"));

        TextArea bioTextArea = new TextArea("Travel Enthusiast");
        bioTextArea.setWrapText(true);
        bioTextArea.setPrefRowCount(0);
        bioTextArea.setEditable(true);
        bioTextArea.setDisable(true);
        bioTextArea.setOpacity(0);
        bioTextArea.setStyle("-fx-control-inner-background: transparent; -fx-text-fill: #2C3E50;");

        Label bioTextLabel = new Label(bioTextArea.getText());
        bioTextLabel.setFont(javafx.scene.text.Font.font("century", FontWeight.EXTRA_BOLD, 20));
        bioTextLabel.setTextFill(Color.web("#2C3E50"));
        bioTextLabel.setOpacity(1);

        StackPane biostp = new StackPane(bioTextArea, bioTextLabel);
        biostp.setAlignment(Pos.CENTER_LEFT);
        // biostp.setStyle("-fx-background-color:red");

        Button bioButton = new Button("Edit Bio");
        bioButton.setFont(javafx.scene.text.Font.font("century", FontWeight.BOLD, 12));
        bioButton.setTextFill(Color.WHITE);
        bioButton.setStyle("-fx-background-color: #2C3E50;");
        bioButton.setOnMouseEntered(e -> {
            bioButton.setScaleX(1.05);
            bioButton.setScaleY(1.05);
        });
        bioButton.setOnMouseExited(e -> {
            bioButton.setScaleX(1);
            bioButton.setScaleY(1);
        });

        Button saveButton = new Button("Save");
        saveButton.setFont(javafx.scene.text.Font.font("century", FontWeight.BOLD, 12));
        saveButton.setTextFill(Color.WHITE);
        saveButton.setDisable(true);
        saveButton.setOpacity(0);
        saveButton.setStyle("-fx-background-color: #2C3E50;");
        saveButton.setOnMouseEntered(e -> {
            saveButton.setScaleX(1.05);
            saveButton.setScaleY(1.05);
        });
        saveButton.setOnMouseExited(e -> {
            saveButton.setScaleX(1);
            saveButton.setScaleY(1);
        });

        bioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bioTextArea.setEditable(true);
                bioTextLabel.setDisable(true);
                bioTextLabel.setOpacity(0);
                bioTextArea.setOpacity(1);
                bioTextArea.setDisable(false);
                saveButton.setOpacity(1);
                saveButton.setDisable(false);
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                bioTextLabel.setText(bioTextArea.getText());
                saveButton.setOpacity(0);
                saveButton.setDisable(true);
                bioTextArea.setOpacity(0);
                bioTextArea.setDisable(true);
                bioTextLabel.setOpacity(1);
            }
        });
        HBox buttonHBox = new HBox(30, bioButton, saveButton);

        VBox userInfoBox = new VBox(20, usernameLabel, nameLabel, bioLabel, biostp, buttonHBox);
        userInfoBox.setPadding(new Insets(30, 0, 0, 0));
        // userInfoBox.setMinSize(0, 400);
        userInfoBox.setStyle("-fx-background-color: transparent;  -fx-background-radius: 10px;");
        userInfoBox.setPadding(new Insets(100, 0, 0, 0));

        HBox profileSection = new HBox(110, userInfoBox, profileBox);
        profileSection.setPadding(new Insets(10));
        profileSection.setAlignment(Pos.CENTER);

        // Trip Section
        Label tripLabel = new Label("Future Trips");
        tripLabel.setFont(javafx.scene.text.Font.font("century", FontWeight.BOLD, 20));
        tripLabel.setTextFill(Color.web("#2C3E50"));

        HBox hBox = new HBox(20);
        
        List<QueryDocumentSnapshot> documents = new ArrayList<>();
        CollectionReference collection = dbFirestore.collection("Users").document(username).collection("posts");

        // Async call to get all documents in the collection
        ApiFuture<QuerySnapshot> query = collection.get();

        try {
            // Query Firestore asynchronously
            QuerySnapshot querySnapshot = query.get();

            // Iterate through each document
            documents = querySnapshot.getDocuments();
            

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        for (QueryDocumentSnapshot documentSnapshot : documents) {
            hBox.getChildren().add(createCard(documentSnapshot));
        }

        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color:transparent");

        Button trip1 = new Button("+");
        trip1.setFont(new Font(120));
        // trip1.setMinSize(100, 100);
        trip1.setMinHeight(460);
        trip1.setMaxWidth(250);
        trip1.setStyle(
                "-fx-background-color: rgba(236, 240, 241, 0.9); -fx-border-color: transparent; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        trip1.setOnMouseEntered(e -> {
            trip1.setScaleX(1.05);
            trip1.setScaleY(1.05);
        });
        trip1.setOnMouseExited(e -> {
            trip1.setScaleX(1);
            trip1.setScaleY(1);
        });

        Button trip2 = new Button();
        trip2.setMinSize(100, 100);
        // trip2.getChildrenUnmodifiable().add(createCard());
        trip2.setStyle(
                "-fx-background-color: rgba(236, 240, 241, 0.9); -fx-border-color: transparent; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        Button trip3 = new Button();
        trip3.setMinSize(100, 100);
        trip3.setStyle(
                "-fx-background-color: rgba(236, 240, 241, 0.9); -fx-border-color: transparent; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        Button trip4 = new Button();
        trip4.setMinSize(100, 100);
        trip4.setStyle(
                "-fx-background-color: rgba(236, 240, 241, 0.9); -fx-border-color: transparent; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        ScrollPane scrollPane = new ScrollPane(hBox);
        scrollPane.setStyle("-fx-background-color:transparent");

        HBox mediaBox = new HBox(20, scrollPane, trip1);
        mediaBox.setPadding(new Insets(10));        

        VBox tripSection = new VBox(10, tripLabel, mediaBox);
        tripSection.setPadding(new Insets(10));
        tripSection.setStyle(
                "-fx-background-color:transparent; -fx-border-color: transparent; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        // // Travel History Section
        // Label travelHistoryLabel = new Label("Travel History");
        // travelHistoryLabel.setFont(javafx.scene.text.Font.font("century", FontWeight.BOLD, 20));
        // travelHistoryLabel.setTextFill(Color.web("#2C3E50"));

        // TextArea travelHistoryTextArea = new TextArea();
        // //travelHistoryTextArea.setWrapText(true);
        // travelHistoryTextArea.setStyle("-fx-control-inner-background: transparent;
        // -fx-text-fill: transparent; -fx-border-color: transparent; -fx-border-radius:
        // 10px;");

        // VBox travelHistorySection = new VBox(10, travelHistoryLabel,
        // travelHistoryTextArea);
        // travelHistorySection.setPadding(new Insets(10));
        // travelHistorySection.setStyle("-fx-background-color: transparent;
        // -fx-border-color: transparent; -fx-border-width: 2px; -fx-border-radius:
        // 10px; -fx-background-radius: 10px;");

        // Main Layout
        mainLayout = new VBox(120, profileSection, tripSection);
        // mainLayout.setMaxSize(800, 600);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-size: cover;");

        // scene = new Scene(mainLayout);
        // Rectangle2D screenBounds2 = Screen.getPrimary().getVisualBounds();
        // primaryStage.setWidth(screenBounds2.getWidth());
        // primaryStage.setHeight(screenBounds2.getHeight());
        // primaryStage.setScene(scene);
        // primaryStage.show();
    }

    private VBox createCard(QueryDocumentSnapshot document) {
        VBox card1 = new VBox(10);
        // Create a label for the date and style it
        Label dateLabel = new Label();
        Label reqBuddLabel = new Label();
        Label useerLabel = new Label();
        Label expLabel = new Label();
        Label categoryButton = new Label();
        Label locationLabel = new Label();
        Label noOfDaysLabel = new Label();
        Label captionLabel = new Label();
        Label membersLabel = new Label();
        Label vehicleNameLabel = new Label();
        Label vehicleAvailabilityLabel = new Label();
        reqBuddLabel= new Label(String.valueOf(document.getData().get("buddiesRequired")));
        reqBuddLabel.setTextFill(Color.BLACK);
        //System.out.println((String)document.getData().get("buddiesRequired"));
        try{
        
        DocumentReference c2w_pi_docRef = dbFirestore.collection("Users").document(username); 

        ApiFuture<DocumentSnapshot> c2w_pi_future = c2w_pi_docRef.get();
        DocumentSnapshot doc = c2w_pi_future.get();
        useerLabel = new Label((String)doc.getData().get("username"));
        
        }
        catch(Exception e){
            e.printStackTrace();
        }

        dateLabel = new Label((String)(document.getData().get("date")));
        expLabel = new Label("Experienced:- "+String.valueOf(document.getData().get("experience")));
        categoryButton =  new Label((String)document.getData().get("category"));
        locationLabel = new Label((String)document.getData().get("place"));
        noOfDaysLabel = new Label("Days:- "+String.valueOf(document.getData().get("noOfDays")));
        captionLabel = new Label((String)document.getData().get("caption"));
        membersLabel = new Label("Total Members:- "+String.valueOf(document.getData().get("totalMembers")));
        vehicleAvailabilityLabel = new Label("Vehicle Availibility:- "+String.valueOf(document.getData().get("vehicleAvailable")));
        vehicleNameLabel = new Label("("+(String)document.getData().get("vehicleName")+")");
        card1.setPadding(new Insets(10, 0, 0, 0));
        card1.setStyle(
                "-fx-background-color: #F5F5F5;-fx-background-radius: 15;-fx-border-color: #ddd; -fx-border-width: 1; -fx-border-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 0);");
        card1.setPrefWidth(250);
        card1.setPrefHeight(500);
        card1.setAlignment(Pos.CENTER);
        card1.setMaxHeight(400);
        card1.setPadding(new Insets(10, 00, 10, 0));
        card1.setOnMouseEntered(e -> {
            card1.setScaleX(1.05);
            card1.setScaleY(1.05);
        });
        card1.setOnMouseExited(e -> {
            card1.setScaleX(1);
            card1.setScaleY(1);
        });

        // Create a StackPane to overlay the date on the image
        StackPane imagePane = new StackPane();
        ImageView imageView = new ImageView();
        imageView.setFitWidth(220);
        imageView.setFitHeight(250);
        try {
            // Use the provided image path
            Image image ;
            
            String Category= (String)document.getData().get("category");
            Image image2 = new Image("hillstation.jpg");
            switch (Category) {
                case "Beach":
                    image = new Image(new FileInputStream("tabs\\src\\main\\resources\\beach.jpg"));
                    break;
                case "HillStation":
                    image = new Image(new FileInputStream("tabs\\src\\main\\resources\\hillstation.jpg"));
                    break;
                // Add cases for other categories
                case "RoadTrip":
                    image = new Image(new FileInputStream("tabs\\src\\main\\resources\\roadtrip.jpg"));
                    break;
                case "Temple":
                    image = new Image(new FileInputStream("tabs\\src\\main\\resources\\Tempple.jpg"));
                    break;
                case "Activity Resort":
                    image = new Image(new FileInputStream("tabs\\src\\main\\resources\\resort.jpg"));
                    break;
                case "Trek":
                    image = new Image(new FileInputStream("tabs\\src\\main\\resources\\trek.jpg"));
                    break; 
                   
                    

                default:
                    image = new Image(new FileInputStream("tabs\\src\\main\\resources\\beach.jpg"));
                    break;
            }
            imageView.setImage(image);
            // Apply rounded corners to the image
            Rectangle clip2 = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
            clip2.setArcWidth(15);
            clip2.setArcHeight(15);
            imageView.setClip(clip2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        
        dateLabel.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BLACK, 12));
        dateLabel.setTextFill(Color.WHITE);
        dateLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 5; -fx-background-radius: 5;");

       
        reqBuddLabel.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BLACK, 15));
        reqBuddLabel.setTextFill(Color.BLACK);
        reqBuddLabel.setStyle("-fx-background-color: white; -fx-padding:5; -fx-background-radius: 15;");

        StackPane.setMargin(dateLabel, new Insets(5, 140, 210, 5));
        StackPane.setMargin(reqBuddLabel, new Insets(5, 0, 210, 170));
        imagePane.getChildren().addAll(imageView,dateLabel,reqBuddLabel);

       
        useerLabel.setFont(javafx.scene.text.Font.font("1", javafx.scene.text.FontWeight.BLACK, 15));
        useerLabel.setTextFill(Color.BLACK);

       
        expLabel.setFont(javafx.scene.text.Font.font("1", javafx.scene.text.FontWeight.NORMAL, 14));
        //expLabel.setTextFill(Color.BLACK);

        HBox userHb = new HBox(17, useerLabel, expLabel);
        userHb.setAlignment(Pos.CENTER_LEFT);
        userHb.setPadding(new Insets(0, 0, 0, 20));

      
        categoryButton.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BLACK, 14));
        categoryButton.setStyle("-fx-background-color:red; -fx-text-fill: white; -fx-background-radius: 15;");
        categoryButton.setPadding(new Insets(5));

        // Create Labels for location, caption, number of members, and vehicle
        // availability
       
        locationLabel.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BLACK, 15));
        locationLabel.setTextFill(Color.DARKMAGENTA);
        locationLabel.setMinWidth(160);
       
        noOfDaysLabel.setFont(javafx.scene.text.Font.font("Calibri", javafx.scene.text.FontWeight.BLACK, 16));
        noOfDaysLabel.setTextFill(Color.DARKSLATEBLUE);

        HBox locDaysHBox = new HBox(locationLabel,noOfDaysLabel);
        locDaysHBox.setAlignment(Pos.CENTER_LEFT);
        locDaysHBox.setPadding(new Insets(0, 0, 0, 20));
       
        captionLabel.setFont(javafx.scene.text.Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        captionLabel.setWrapText(true);
        captionLabel.setTextFill(Color.BLACK);
        captionLabel.setStyle("-fx-background-color:#f1f1f1; -fx-padding: 5; -fx-background-radius: 7;");

       
        membersLabel.setFont(javafx.scene.text.Font.font("Calibri", javafx.scene.text.FontWeight.SEMI_BOLD, 17));
        membersLabel.setTextFill(Color.DARKSLATEGRAY);  
        membersLabel.setAlignment(Pos.BOTTOM_LEFT);

        HBox memHBox = new HBox(membersLabel);
        memHBox.setAlignment(Pos.CENTER_LEFT);
        memHBox.setPadding(new Insets(0, 0, 0, 20));

        // Create a label for vehicle availability with a symbol
       
        vehicleAvailabilityLabel.setFont(javafx.scene.text.Font.font("Calibri", javafx.scene.text.FontWeight.BLACK, 13));
        vehicleAvailabilityLabel.setTextFill(Color.DARKSLATEGRAY);

        
        vehicleNameLabel.setFont(javafx.scene.text.Font.font("Calibri", javafx.scene.text.FontWeight.BLACK, 13));
        vehicleNameLabel.setTextFill(Color.DARKSLATEGRAY);

        HBox vehicHBox = new HBox(20,vehicleAvailabilityLabel,vehicleNameLabel);
        vehicHBox.setAlignment(Pos.CENTER_LEFT);
        vehicHBox.setPadding(new Insets(0, 0, 0, 20));

        // Add the components to the VBox
       
        
        // StackPane.setMargin(dateLabel, new Insets(5, 140, 210, 5));
        // //StackPane.setMargin(imagePane, new Insets(0, 0, 0, 0));
        // StackPane.setMargin(reqBuddLabel, new Insets(5, 0, 210, 170));
        // //imagePane.getChildren().addAll(imageView, dateLabel, reqBuddLabel);
        // HBox memHBox = new HBox(membersLabel);
        // HBox locDaysHBox = new HBox(0, locationLabel, noOfDaysLabel);
        // locDaysHBox.setAlignment(Pos.CENTER_LEFT);
        // locDaysHBox.setPadding(new Insets(0, 0, 0, 20));

        
        // HBox vehicHBox = new HBox(vehicleAvailabilityLabel, vehicleNameLabel);
        // memHBox.setAlignment(Pos.CENTER_LEFT);
        // memHBox.setPadding(new Insets(0, 0, 0, 20));
        // vehicHBox.setAlignment(Pos.CENTER_LEFT);
        // vehicHBox.setPadding(new Insets(0, 0, 0, 20));
        // card1.getChildren().addAll(imagePane, locDaysHBox, categoryButton, userHb, captionLabel, memHBox, vehicHBox);
        //card1.getChildren().addAll(imagePane,reqBuddLabel, useerLabel, expLabel);
       
        VBox vb = new VBox();
        vb.setPrefSize(100, 200);
        card1.getChildren().addAll(imagePane,locDaysHBox,categoryButton,userHb,captionLabel,memHBox,vehicHBox);
        //vb.getChildren().addAll(reqBuddLabel,useerLabel,expLabel,captionLabel,membersLabel,vehicleAvailabilityLabel,vehicleNameLabel,noOfDaysLabel,locationLabel);
        vb.setStyle("-fx-background-color : AQUA");
        return card1;

    }

    private Image loadImage(String path) {
        InputStream inputStream = getClass().getResourceAsStream(path);
        if (inputStream == null) {
            System.err.println("Could not find resource: " + path);
            return null;
        }
        return new Image(inputStream);
    }

    public VBox getRoot() {
        // mainLayout.setMinSize(primaryStage.getWidth(), primaryStage.getHeight());
        return mainLayout;
    }

}
package com.tabpane.controller;

import java.net.URL;
import java.time.LocalDate;

import org.omg.PortableServer.POA;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.tabpane.Database.GetUserData;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import java.awt.Desktop;
import java.net.URI;
import javafx.util.Callback;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class PostController {

    private Stage stage;
    private Scene scene;
    private VBox mainVBox;
    public String username;
    // private static String username =CreateAcc.username;
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
    private String USERNAME;

    public PostController(Stage stage, String username, String place,String dateStr,int buddiesRequired,int totalMembers,int noOfDaysINt,String category,boolean experience,boolean vehicleAvailable,String vehicleName,String caption){
        this.stage = stage;
        this.username = username ;
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
        initPostCreateScene();
    }
    boolean exp = false;
    boolean vExp = false;

   
    public Scene initPostCreateScene() {
        //stage.setTitle("Post Your Future Trip");

        //BackgroundFill bkf = new BackgroundFill(Color.TRANSPARENT, null, null);

        BorderStroke bkstr = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(15), null);

        Image img = new Image("correct.png", 200, 50, true, false);
        BackgroundImage bkImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, null, null, null);

        Image img2 = new Image("wrong.png", 200, 50, true, false);
        BackgroundImage bk2 = new BackgroundImage(img2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null,
                null);

        // LinearGradient pinkGradient = new LinearGradient(
        //         0, 0, 1, 0, true, CycleMethod.REPEAT,
        //         new Stop(0, Color.rgb(255, 192, 203)), // Light pink
        //         new Stop(0.5, Color.rgb(255, 105, 180)), // Medium pink
        //         new Stop(1, Color.rgb(255, 20, 147)) // Dark pink
        // );

        LinearGradient blueGradient = new LinearGradient(
                0, 1, 0, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(135, 206, 250, 1)), // Sky blue, fully saturated
                new Stop(1, Color.rgb(240, 248, 255, 1)) // Very light sky blue, almost white
        );
        String borStyle = "-fx-background-color:White;-fx-text-fill:BLACK;-fx-background-radius:15; -fx-font-size:20";
      //  BackgroundFill bleubk = new BackgroundFill(blueGradient, null, null);
        
        Label nameLabel = new Label("Place : ");
        nameLabel.setFont(new Font(20));
        nameLabel.setFont(Font.font("century", FontWeight.EXTRA_BOLD, 20));
        nameLabel.setTextFill(Color.BLACK);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setStyle("-fx-background-color:TRANSPARENT");
        nameLabel.setPadding(new Insets(0, 0, 0, 10));
        //nameLabel.setTextFill(Color.DARKGREY);;

        
        TextField placeNametf = new TextField();
        placeNametf.setFont(new Font(20));
        placeNametf.setPromptText("Where you want to go?");
        placeNametf.setStyle(borStyle);
        //placeNametf.setBorder(new Border(bkstr));
        placeNametf.setOnMouseEntered(e->{
            placeNametf.setScaleX(1.03);
        });
        placeNametf.setOnMouseExited(e->{
            placeNametf.setScaleX(1);
        });
 

        Label reqBuddies = new Label("Buddies required: ");
        reqBuddies.setFont(new Font(20));
        reqBuddies.setFont(Font.font("century", FontWeight.BOLD, 20));
        reqBuddies.setTextFill(Color.BLACK);
        reqBuddies.setAlignment(Pos.CENTER);
        reqBuddies.setStyle("-fx-background-color:TRANSPARENT");
        reqBuddies.setPadding(new Insets(0, 0, 0, 10));

        TextField reqBudTf = new TextField();
        reqBudTf.setFont(new Font(20));
        reqBudTf.setPromptText("How many Buddies you required ");
        reqBudTf.setStyle(borStyle);
        //reqBudTf.setBorder(new Border(bkstr));
        reqBudTf.setOnMouseEntered(e->{
            reqBudTf.setScaleX(1.03);
        });
        reqBudTf.setOnMouseExited(e->{
            reqBudTf.setScaleX(1);
        });

        reqBudTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) { // Regex to match only digits
                    reqBudTf.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digit characters
                }
            }
        });

        Label totalMem = new Label("Total Members: ");
        totalMem.setFont(new Font(20));
        totalMem.setFont(Font.font("1", FontWeight.BOLD, 20));
        totalMem.setTextFill(Color.BLACK);
        totalMem.setAlignment(Pos.CENTER);
        totalMem.setStyle("-fx-background-color:TRANSPARENT");
        totalMem.setPadding(new Insets(0, 0, 0, 10));

        TextField totalMemTF = new TextField();
        totalMemTF.setFont(new Font(20));
        totalMemTF.setPromptText("Buddies required + Your members ");
        totalMemTF.setStyle(borStyle);
        //totalMemTF.setBorder(new Border(bkstr));
        totalMemTF.setOnMouseEntered(e->{
            totalMemTF.setScaleX(1.03);
        });
        totalMemTF.setOnMouseExited(e->{
            totalMemTF.setScaleX(1);
        });

        // totalMemTF.textProperty().addListener(new ChangeListener<String>() {
        //     @Override
        //     public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        //         if (!newValue.matches("\\d*")) { // Regex to match only digits
        //             totalMemTF.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digit characters
        //         }
        //     }
        // });


        Label noOfDays = new Label("No of Days: ");
        noOfDays.setFont(new Font(20));
        noOfDays.setFont(Font.font("1", FontWeight.BOLD, 20));
        noOfDays.setTextFill(Color.BLACK);
        noOfDays.setAlignment(Pos.CENTER);
        noOfDays.setStyle("-fx-background-color:TRANSPARENT");
        noOfDays.setPadding(new Insets(0, 0, 0, 10));

        TextField noOfDaysTF = new TextField();
        noOfDaysTF.setFont(new Font(20));
        noOfDaysTF.setPromptText("How many days will you enjoy ");
        noOfDaysTF.setStyle(borStyle);
        //noOfDaysTF.setBorder(new Border(bkstr));
        noOfDaysTF.setOnMouseEntered(e->{
            noOfDaysTF.setScaleX(1.03);
        });
        noOfDaysTF.setOnMouseExited(e->{
            noOfDaysTF.setScaleX(1);
        });

        noOfDaysTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) { // Regex to match only digits
                    noOfDaysTF.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digit characters
                }
            }
        });

        Label dateLabel = new Label("Date: ");
        dateLabel.setFont(new Font(20));
        dateLabel.setFont(Font.font("1", FontWeight.BOLD, 20));
        dateLabel.setTextFill(Color.BLACK);
        dateLabel.setAlignment(Pos.CENTER);
        dateLabel.setStyle("-fx-background-color:TRANSPARENT");
        dateLabel.setPadding(new Insets(0, 0, 0, 10));

        DatePicker date = new DatePicker();
        date.setStyle(borStyle);
        date.setStyle("-fx-background-color:Transparent;-fx-background-color:TRANSPARENT;");
        //date.setBorder(new Border(bkstr));
        date.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        // Customize each cell here
                        this.setStyle("-fx-background-color: Black; -fx-text-fill: White;");
                        
                        // For hovered dates
                        this.setOnMouseEntered(event -> this.setStyle("-fx-background-color: White; -fx-text-fill: Black;"));
                        this.setOnMouseExited(event -> this.setStyle("-fx-background-color: Black; -fx-text-fill: White;"));
                        
                        // For selected dates
                        if (item.equals(datePicker.getValue())) {
                            this.setStyle("-fx-background-color: White; -fx-text-fill: Black;");
                        }
                    }
                };
            }
        });

        // Customize the DatePicker editor (input field)
        date.getEditor().setStyle("-fx-background-color: white; -fx-text-fill: black;");
        date.setPromptText("dd/mm/YYYY");
        date.setMinHeight(50);
        date.setMinWidth(400);
        date.setOnMouseEntered(e->{
            date.setScaleX(1.03);
        });
        date.setOnMouseExited(e->{
            date.setScaleX(1);
        });

        //HBox dateHb = new HBox(20, date);
        // dateHb.setStyle("-fx-background-color:BLACK");

        Label vehicLabel = new Label("Vehicle: ");
        vehicLabel.setFont(new Font(20));
        vehicLabel.setFont(Font.font("1", FontWeight.BOLD, 20));
        vehicLabel.setTextFill(Color.BLACK);
        vehicLabel.setAlignment(Pos.CENTER);
        vehicLabel.setStyle("-fx-background-color:TRANSPARENT");
        vehicLabel.setPadding(new Insets(0, 0, 0, 10));

        BorderStroke btYesStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(15, 0, 0, 15, false), null);

        Button vehicYesButton = new Button("Yes");
        vehicYesButton.setTextFill(Color.BLACK);
        vehicYesButton.setMinSize(200, 50);
        //vehicYesButton.setBorder(new Border(btYesStroke));
        //vehicYesButton.setStyle("-fx-background-Image:url('correct.png');");
        vehicYesButton.setStyle(borStyle);

        vehicYesButton.setOnMouseEntered(e->{
            vExp = true;
            vehicYesButton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, null, null, null)));
            vehicYesButton.setStyle("-fx-background-color:#1F1F1F");
            vehicYesButton.setFont(Font.font("1", FontWeight.BOLD, 17));
            vehicYesButton.setScaleX(1.05);
            vehicYesButton.setScaleY(1.05);
        });
        vehicYesButton.setOnMouseExited(e->{
            vExp=true;
            //vehicYesButton.setBorder(new Border(btYesStroke));
            vehicYesButton.setStyle(borStyle);
            vehicYesButton.setFont(Font.font("1", FontWeight.NORMAL, 20));
            vehicYesButton.setScaleX(1.0);
            vehicYesButton.setScaleY(1.0);
        });
 

        BorderStroke btNoStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(0, 15, 15, 0, false), null);

        Button vehicNoButton = new Button("No");
        vehicNoButton.setTextFill(Color.BLACK);
        vehicNoButton.setMinSize(200, 50);
        //vehicNoButton.setBorder(new Border(btNoStroke));
        vehicNoButton.setStyle(borStyle);
        //vehicYesButton.setBackground(new Background(new BackgroundFill[0]));
        vehicNoButton.setOnMouseEntered(e->{
            vehicNoButton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, null, null, null)));
            vehicNoButton.setStyle("-fx-background-color:#1F1F1F");
            vehicNoButton.setFont(Font.font("1", FontWeight.BOLD, 17));
            vehicNoButton.setScaleX(1.05);
            vehicNoButton.setScaleY(1.05);
        });
        vehicNoButton.setOnMouseExited(e->{
            vExp=true;
            //vehicNoButton.setBorder(new Border(btNoStroke));
            vehicNoButton.setStyle(borStyle);
            vehicNoButton.setFont(Font.font("1", FontWeight.NORMAL, 20));
            vehicNoButton.setScaleX(1.0);
            vehicNoButton.setScaleY(1.0);
        });
        
      
        Label VEHICLELABEL = new Label();
        VEHICLELABEL.setFont(new Font(20));
        VEHICLELABEL.setFont(Font.font("1", FontWeight.BOLD, 20));
        VEHICLELABEL.setTextFill(Color.BLACK);
        VEHICLELABEL.setAlignment(Pos.CENTER);
        VEHICLELABEL.setStyle("-fx-background-color:TRANSPARENT");
        VEHICLELABEL.setPadding(new Insets(0, 0, 0, 10));

        TextField vehicle = new TextField();
        vehicle.setPromptText("Which vehicle do you have?");
        vehicle.setMinSize(400, 50);
        //vehicle.setBorder(new Border(bkstr));
        vehicle.setStyle(borStyle);
        vehicle.setOpacity(0);
        HBox vehicHb = new HBox(vehicYesButton, vehicNoButton);
        vehicHb.setMinSize(400, 50);
        // vehicHb.setBorder(new Border(bkstr));
        vehicYesButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //vehicYesButton.setBackground(new Background(bkImg));
                vehicle.setOpacity(1);
                
            }
        });

        Hyperlink hyperlink = new Hyperlink("Rent your vehicle with Ola");
        hyperlink.setOpacity(0);
        hyperlink.setStyle("-fx-background-color:transparent;-fx-text-fill:BLUE");
        
        vehicNoButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                vExp=false;
                vehicle.setOpacity(0);
                //expTf.setText("No");
               vehicHb.setBackground(new Background(bk2));
               vehicYesButton.setOpacity(0);
               vehicNoButton.setOpacity(0);
               
                hyperlink.setOpacity(1);
               
            }
        });

        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                try{
                    Desktop.getDesktop().browse(new URI("https://www.olacabs.com/"));
                }
                catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });


        Label expLabel = new Label("Have you been there before ");
        expLabel.setFont(new Font(20));
        expLabel.setFont(Font.font("1", FontWeight.BOLD, 20));
        expLabel.setTextFill(Color.BLACK);
        expLabel.setAlignment(Pos.CENTER);
        expLabel.setStyle("-fx-background-color:TRANSPARENT");
        expLabel.setPadding(new Insets(0, 0, 0, 10));

        Button expYesButton = new Button("Yes");
        expYesButton.setTextFill(Color.BLACK);
        expYesButton.setMinSize(200, 50);
        //expYesButton.setBorder(new Border(btYesStroke));
        expYesButton.setStyle(borStyle);

        expYesButton.setOnMouseEntered(e->{
            exp=true;
            //expYesButton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, null, null, null)));
            expYesButton.setStyle("-fx-background-color:#1F1F1F");
            expYesButton.setFont(Font.font("1", FontWeight.BOLD, 17));
            expYesButton.setScaleX(1.05);
            expYesButton.setScaleY(1.05);
            
        });
        expYesButton.setOnMouseExited(e->{
            exp=true;
            //expYesButton.setBorder(new Border(btYesStroke));
            expYesButton.setStyle(borStyle);
            expYesButton.setFont(Font.font("1", FontWeight.NORMAL, 20));
            expYesButton.setScaleX(1.0);
            expYesButton.setScaleY(1.0);
        });
 

        Button expNobButton = new Button("No");
        expNobButton.setTextFill(Color.BLACK);
        expNobButton.setMinSize(200, 50);
        //expNobButton.setBorder(new Border(btNoStroke));
        expNobButton.setStyle(borStyle);
        
        expNobButton.setOnMouseEntered(e->{
            exp=true;
            expNobButton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, null, null, null)));
            expNobButton.setStyle("-fx-background-color:#1F1F1F");
            expNobButton.setFont(Font.font("1", FontWeight.BOLD, 17));
            expNobButton.setScaleX(1.05);
            expNobButton.setScaleY(1.05);
        });
        expNobButton.setOnMouseExited(e->{
            exp=false;
            //expNobButton.setBorder(new Border(btNoStroke));
            expNobButton.setStyle(borStyle);
            expNobButton.setFont(Font.font("1", FontWeight.NORMAL, 20));
            expNobButton.setScaleX(1.0);
            expNobButton.setScaleY(1.0);
        });
 
        TextField expTf = new TextField();
       
        HBox expHb = new HBox(expYesButton,expNobButton);
        vehicHb.setMinSize(400, 50);
       
        expYesButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                expTf.setText("Yes");
                expHb.setBackground(new Background(bkImg));
                expYesButton.setOpacity(0);
                expNobButton.setOpacity(0);
                //expHb.setAlignment(Pos.CENTER);
                
            }
        });
        expNobButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
               expTf.setText("No");
               expHb.setBackground(new Background(bk2));
               expYesButton.setOpacity(0);
               expNobButton.setOpacity(0);
            }
        });

        Label categLabel = new Label("Select Category of the Destination ");
        categLabel.setFont(new Font(20));
        categLabel.setFont(Font.font("1", FontWeight.BOLD, 20));
        categLabel.setTextFill(Color.BLACK);
        categLabel.setAlignment(Pos.CENTER);
        categLabel.setStyle("-fx-background-color:TRANSPARENT");
        categLabel.setPadding(new Insets(0, 0, 0, 10));

        Button categButton = new Button("Click to select");
        categButton.setTextFill(Color.BLACK);
        categButton.setMinSize(400, 40);
       // categButton.setBorder(new Border(bkstr));
        categButton.setStyle(borStyle);

        Label captionLabel = new Label("Caption : ");
        captionLabel.setFont(new Font(20));
        captionLabel.setFont(Font.font("1", FontWeight.BOLD, 20));
        captionLabel.setTextFill(Color.BLACK);
        captionLabel.setAlignment(Pos.CENTER);
        captionLabel.setStyle("-fx-background-color:TRANSPARENT");
        captionLabel.setPadding(new Insets(0, 0, 0, 10));
        //captionLabel.setTextFill(Color.DARKGREY);;

        
        TextField captionTf = new TextField();
        captionTf.setFont(new Font(20));
        captionTf.setPromptText("Caption your post...");
        captionTf.setStyle(borStyle);
        //captionTf.setBorder(new Border(bkstr));
        captionTf.setOnMouseEntered(e->{
            captionTf.setScaleX(1.03);
        });
        captionTf.setOnMouseExited(e->{
            captionTf.setScaleX(1);
        });
       

        Button submitButton = new Button("Post");
        submitButton.setPrefSize(200, 50);
        submitButton.setTextFill(Color.BLACK);
        //submitButton.setLayoutX(100);
        submitButton.setBorder(new Border(bkstr));
        submitButton.setStyle("-fx-background-color:#2C3E50;-fx-background-radius:15px;-fx-text-fill:white");
        //submitButton.setStyle("-fx-background-color:RED");
        
        submitButton.setOnMouseEntered(e->{
            submitButton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, null, null, null)));
            submitButton.setStyle("-fx-background-color:#2C3E50;-fx-text-fill:white");
            submitButton.setFont(Font.font("arial", FontWeight.BOLD, 20));
        });
        submitButton.setOnMouseExited(e->{
            submitButton.setBorder(new Border(bkstr));
            submitButton.setStyle("-fx-background-color:#2C3E50;-fx-background-radius:15px;");
            submitButton.setFont(Font.font("1", FontWeight.NORMAL, 20));
        });



        //extracting data from post and storing in a class named GETPOSTDATA

        submitButton.setOnAction(e -> {
            place = placeNametf.getText();
            dateStr = date.getValue() != null ? date.getValue().toString() : "";
            buddiesRequired = Integer.parseInt(reqBudTf.getText());
            totalMembers = Integer.parseInt(totalMemTF.getText());
            noOfDaysINt = Integer.parseInt(noOfDaysTF.getText());
            category = categButton.getText();
            experience = exp;
            vehicleAvailable = vExp;
            vehicleName = vehicleAvailable ? vehicle.getText() : null;
            caption = captionTf.getText();
            USERNAME = username;
            
                try{
                    // FirebaseInitialization.initializeFirebase();
                    // Firestore dbFirestore = FirebaseInitialization.getFirestore();
                    System.out.println(dbFirestore);
                    System.out.println(username);
                    System.out.println(place+dateStr);
                    DocumentReference docRef = dbFirestore.collection("Users").document(username).collection("posts").document(place+dateStr);
                    ApiFuture<DocumentSnapshot> future = docRef.get();
                    DocumentSnapshot document = future.get();
                    

                    System.out.println("mmmmmmmmmmmmmmmmmmm" + USERNAME);

                    if(document.exists()){
                        showAlert("Post Creation Failed","Post with this Place and Date already exists  ");
                    }
                    else{
                        dbFirestore.collection("Users").document(username).collection("posts").document(place+dateStr).set(new GetPostData(place, dateStr, buddiesRequired, totalMembers, noOfDaysINt, category, experience, vehicleAvailable, vehicleName, caption,USERNAME));
                        dbFirestore.collection("All posts").document().set(new GetPostData(place, dateStr, buddiesRequired, totalMembers, noOfDaysINt, category, experience, vehicleAvailable, vehicleName,caption,USERNAME));
                        
                        PostSuccess postSuccess = new PostSuccess(stage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
                        stage.setScene(postSuccess.initPostSuccessScene());
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            ProfileUserTrial profileUserTrial = new ProfileUserTrial(stage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);

            GetPostData tripData = new GetPostData(place, dateStr, buddiesRequired, totalMembers, noOfDaysINt, category, experience, vehicleAvailable, vehicleName, caption,USERNAME);
            tripData.setPlace(place);
            tripData.setDate(dateStr);  
            tripData.setBuddiesRequired(buddiesRequired);
            tripData.setTotalMembers(totalMembers);
            tripData.setNoOfDays(noOfDaysINt);
            tripData.setCategory(category);
            tripData.setExperience(experience);
            tripData.setVehicleAvailable(vehicleAvailable);
            tripData.setVehicleName(vehicleName);
            tripData.setCaption(caption);
            tripData.setUSERNAME(USERNAME);


        });
    
        TextField categTf = new TextField();
        

        VBox container = new VBox(20, nameLabel,placeNametf, dateLabel,date, reqBuddies,reqBudTf,
                totalMem,totalMemTF,  noOfDays,noOfDaysTF);
        container.setStyle("-fx-background-color:Transparent");
        container.setPadding(new Insets(30, 30, 30, 30));

        VBox container2 = new VBox(20,categLabel,categButton,expLabel,expHb ,vehicLabel,vehicHb,vehicle,hyperlink,captionLabel,captionTf);
        container2.setStyle("-fx-background-color:Transparent");
        container2.setPadding(new Insets(30, 30, 30, 30));

        
        HBox containerHBox = new HBox(20,container,container2);
        containerHBox.setStyle("-fx-background-color:Transparent");
        containerHBox.setAlignment(Pos.CENTER);

        HBox submitHBox = new HBox(submitButton);
        submitHBox.setStyle("-fx-background-color:Transparent; -fx-pref-width : 800");
        submitHBox.setAlignment(Pos.CENTER);

        // VBox VB = new VBox(40,containerHBox,submitHBox);
        // VB.setStyle("-fx-background-color:Transparent; ");
        // VB.setAlignment(Pos.CENTER);


        BorderStroke categStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT);
        Button categ1 = new Button("Beach");
        categ1.setTextFill(Color.BLACK);
        categ1.setMinSize(300, 50);
        categ1.setStyle(borStyle);
        //categ1.setBorder(new Border(categStroke));
        
        Button categ2= new Button("Hillstation");
        categ2.setTextFill(Color.BLACK);
        categ2.setMinSize(300, 50);
        categ2.setStyle(borStyle);
      
        //categ2.setBorder(new Border(categStroke));

        Button categ3= new Button("Activity Resort");
        categ3.setTextFill(Color.BLACK);
        categ3.setMinSize(300, 50);
        categ3.setStyle(borStyle);
        //categ3.setBorder(new Border(categStroke));

        Button categ4= new Button("RoadTrip");
        categ4.setTextFill(Color.BLACK);
        categ4.setMinSize(300, 50);
        categ4.setStyle(borStyle);
        //categ4.setBorder(new Border(categStroke));

        Button categ5= new Button("Temple");
        categ5.setTextFill(Color.BLACK);
        categ5.setMinSize(300, 50);
        categ5.setStyle(borStyle);
        //categ5.setBorder(new Border(categStroke));
       

        Button categ6= new Button("Trek");
        categ6.setMinSize(300, 50);
        categ6.setTextFill(Color.BLACK);
        categ6.setStyle(borStyle);
        //categ6.setBorder(new Border(categStroke));
       

        VBox categVb = new VBox(4,categ1,categ2,categ3,categ4,categ5,categ6);
        //categVb.setMaxSize(500, 320);
        categVb.setAlignment(Pos.CENTER);
        categVb.setPadding(new Insets(10, 0, 10, 0));
        //categVb.setBackground(new Background(bleubk));
        categVb.setStyle("-fx-background-color:transparent");
        categVb.setOpacity(0);
        categVb.setDisable(true);

        categ1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                categButton.setText("Beach");
                categTf.setText("Beach");
                categVb.setDisable(true);
                categVb.setOpacity(0);
                containerHBox.setDisable(false);
                containerHBox.setOpacity(1);

                // VB.setDisable(false);
                // VB.setOpacity(1);
            }
        });

        categ2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                categButton.setText("HillStation");
                categTf.setText("Hillstation");
                categVb.setDisable(true);
                categVb.setOpacity(0);
                containerHBox.setDisable(false);
                containerHBox.setOpacity(1);
                // VB.setDisable(false);
                // VB.setOpacity(1);      
            }
        });

        categ3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                categButton.setText("Activity Resort");
                categTf.setText("Activity Resort");
                categVb.setDisable(true);
                categVb.setOpacity(0);
                containerHBox.setDisable(false);
                containerHBox.setOpacity(1);
                // VB.setDisable(false);
                // VB.setOpacity(1);         
            }
        });

        categ4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                categButton.setText("RoadTrip");
                categTf.setText("RoadTrip"); 
                categVb.setDisable(true);
                categVb.setOpacity(0);
                containerHBox.setDisable(false);
                containerHBox.setOpacity(1);
                // VB.setDisable(false);
                // VB.setOpacity(1);  
            }
        });

        categ5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                categButton.setText("Temple");
                categTf.setText("Temple");
                categVb.setDisable(true);
                categVb.setOpacity(0);
                containerHBox.setDisable(false);
                containerHBox.setOpacity(1);
                // VB.setDisable(false);
                // VB.setOpacity(1);
            }
        });

        categ6.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                categButton.setText("Trek");
                categTf.setText("Trek");
                categVb.setDisable(true);
                categVb.setOpacity(0);
                containerHBox.setDisable(false);
                containerHBox.setOpacity(1);
                // VB.setDisable(false);
                // VB.setOpacity(1);
            }
        });
        
        categButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                categVb.setOpacity(1);
                categVb.setDisable(false);
                containerHBox.setDisable(true);
                containerHBox.setOpacity(0);
                // VB.setDisable(true);
                // VB.setOpacity(0);
            }
            
        });


        categButton.setOnMouseEntered(e->{
            categButton.setScaleX(1.05);
            categButton.setScaleY(1.05);
            categButton.setTextFill(Color.WHITE);
            categButton.setFont(Font.font("1", FontWeight.BOLD,17));
            categButton.setStyle("-fx-background-color:#1F1F1F");
            
        });

        categButton.setOnMouseExited(e->{
            categButton.setScaleX(1.0);
            categButton.setScaleY(1.0);
            
            categButton.setStyle(borStyle);
            categButton.setFont(Font.font("1", FontWeight.NORMAL,13));
           
        });

        Image image = new Image("bus.jpg", 1980, 1200, false, false);
        BackgroundImage bkIamge = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        StackPane stp = new StackPane(containerHBox,categVb);
        //stp.setBackground(new Background(bkIamge));
        
        //BorderStroke hbBkstr = new BorderStroke(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(20), new Insets(20));
        HBox hBox = new HBox(stp,submitHBox);
        hBox.setAlignment(Pos.CENTER);
        //hBox.setBorder(new Border(hbBkstr));
        hBox.setMaxWidth(800);
        hBox.setPadding(new Insets(20, 20, 20, 20));

       

        //hBox.setBackground(new Background(bkIamge));
        mainVBox = new VBox(stp,submitHBox);
        mainVBox.setAlignment(Pos.CENTER);
        
        //mainVBox.setBackground(new Background(bleubk));
        //mainVBox.setBorder(new Border(hbBkstr));
        mainVBox.setStyle("-fx-background-color:transparent");
        //mainVBox.setBorder(new Border(bkstr));
        //mainVBox.setBackground(new Background(bkIamge));

        //Group group = new Group(mainVBox);
        //group.setLayoutX(500);
        //group.setLayoutY(300);

        scene = new Scene(mainVBox);

        return scene;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public VBox getRoot(){
        return mainVBox;
    }


}

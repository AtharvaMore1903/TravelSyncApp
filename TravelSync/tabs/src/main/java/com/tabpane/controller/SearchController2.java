package com.tabpane.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;

public class SearchController2 {

    private List<HBox> postList = new ArrayList<>();
    
    private VBox bottomVBox = new VBox(10);
    private HBox mainHBox;
    private BorderStroke bkstr;
    private Stage primaryStage;
    private Scene scene;
    private VBox mainVBox;
    private String username;
    public static Firestore dbFirestore;

    private VBox card;

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

    public SearchController2(Stage primaryStage,String username, String place,String dateStr,int buddiesRequired,int totalMembers,int noOfDaysINt,String category,boolean experience,boolean vehicleAvailable,String vehicleName,String caption) {
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
        initSearchScene();
    }

    private void initSearchScene() {
        //primaryStage.setTitle("Post_Search");

        

        bkstr = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT);
        // Top horizontal box started:-
        TextField tf = new TextField();
        tf.setPromptText("Search...");

        tf.setStyle("-fx-prompt-text-fill: gray;");
        tf.setMinSize(500, 50);
        tf.setStyle("-fx-background-color:white;-fx-border-radius:15px;-fx-background-radius:15px;");
        // tf.setOnMouseEntered(e->{

        // //tf.setScaleX(1.05);
        // tf.setStyle("-fx-background-color:#1F1F1F");
        // BorderStroke bk = new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
        // new CornerRadii(555), BorderWidths.DEFAULT);
        // tf.setBorder(new Border(bk));
        // }
        // );
        // tf.setOnMouseExited(e->{
        // tf.setScaleX(1);
        // tf.setStyle("-fx-background-color:transparent");
        // tf.setBorder(new Border(bkstr));
        // });

        // combobox is used to filter an texts .
       //// ComboBox<String> filterComboBox = new ComboBox<>();
       // filterComboBox.getItems().addAll("Date", "Place", "Members", "Buddies Required", "Number of Days", "Vehicle");
       // filterComboBox.setValue("Filters");
       // filterComboBox.setMinSize(100, 50);
       // filterComboBox.setStyle("-fx-background-color:white;-fx-border-radius:15px;-fx-background-radius:15px");
        // filterComboBox.setBorder(new Border(bkstr));

        // want symbol of search instead of text
       Button bt = new Button("Search");
       bt.setStyle("-fx-background-color: #2C3E50; -fx-text-fill: white;");

        HBox topHb = new HBox(10);
        topHb.setPadding(new Insets(10));
        topHb.getChildren().addAll(tf);
        topHb.setAlignment(Pos.CENTER);
        topHb.setStyle("-fx-background-color: transparent;");
        // Top horizontal box ended

        // Bottom vertical boxes:-
        // for (int i = 1; i <= 10; i++) {
        //     HBox bottomvBox = createContentHBox("2023-01-" + i, "Location " + i, String.valueOf(i * 10),
        //             "Buddies Required " + i, String.valueOf(i), i % 2 == 0 ? "Yes" : "No");
        //     postList.add(bottomvBox);
        //     bottomVBox.getChildren().add(bottomvBox);
        //     // bottomvBox.setBorder(new Border(bkstr));
        // }

        
        // mainContainer.setAlignment(Pos.CENTER);
        List<QueryDocumentSnapshot> documents = new ArrayList<>();
        List<QueryDocumentSnapshot> documents2 = new ArrayList<>();
        CollectionReference collection = dbFirestore.collection("All posts");
        CollectionReference collection2 = dbFirestore.collection("Users");

        // Async call to get all documents in the collection
        ApiFuture<QuerySnapshot> query = collection.get();
        ApiFuture<QuerySnapshot> query2 = collection2.get();

        try {
            // Query Firestore asynchronously
            QuerySnapshot querySnapshot = query.get();
            QuerySnapshot querySnapshot2 = query2.get();

            // Iterate through each document
            documents = querySnapshot.getDocuments();
            documents2 = querySnapshot2.getDocuments();
            

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // for (QueryDocumentSnapshot documentSnapshot : documents) {
        //     mainContainer.getChildren().add(createHBox(documentSnapshot));
        // }

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        int i=0;
        int j=0;
        for (QueryDocumentSnapshot documentSnapshot:documents ) {
            j++;
            gridPane.add(createCard(documentSnapshot), j, i);
            if(j==5){
                j=0;
                i++;
            }
            
            
        }
        VBox mainContainer = new VBox(20,gridPane);
        mainContainer.setPadding(new Insets(10));
        mainContainer.setStyle("-fx-background-color: transparent;");
        
        
        // ScrollPane scrollPane = new ScrollPane();
        // scrollPane.setContent(mainContainer);
        // scrollPane.setFitToWidth(true);
        // scrollPane.setMinWidth(primaryStage.getWidth());
        // scrollPane.setStyle("-fx-background-color: transparent;");
        // scrollPane.setBorder(new Border(bkstr));
        // scrollPane.setPannable(true);
        
        // for scrolling

        // Add four cards to the main container


        // GridPane gridPane = new GridPane();
        
        // for (int i = 0; i < 2; i++) {
        //     for (int j = 0; j < 5; j++) {
        //         gridPane.add(gridPane, i, j);
        //     }
        // }


        mainVBox = new VBox(10);
        mainVBox.setPadding(new Insets(10));
        mainVBox.getChildren().addAll(topHb, mainContainer);
        mainVBox.setStyle("-fx-background-color:transparent;");
        Image image = new Image("bus.jpg", mainVBox.getWidth(), mainVBox.getHeight(), false, false);
        BackgroundImage bkimg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, null);

        //mainVBox.setBackground(new Background(bkimg));
        // Search button action
      /*  bt.setOnAction(e -> {
            String searchText = tf.getText().toLowerCase();
           // String filter = filterComboBox.getValue();

            List<HBox> filteredList = postList.stream()
                    .filter(hBox -> {
                        for (javafx.scene.Node node : hBox.getChildren()) {
                            if (node instanceof VBox) {
                                for (javafx.scene.Node vboxNode : ((VBox) node).getChildren()) {
                                    if (vboxNode instanceof Text) {
                                        Text textNode = (Text) vboxNode;
                                       // if (filter.equals("Vehicle")) {
                                            if (textNode.getText().toLowerCase().startsWith(filter.toLowerCase())
                                                    && textNode.getText().toLowerCase().contains(searchText)) {
                                                return true;
                                            }
                                        } else if (textNode.getText().toLowerCase().contains(filter.toLowerCase() + ":")
                                                && textNode.getText().toLowerCase().contains(searchText)) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());

            bottomVBox.getChildren().setAll(filteredList);
            // bt.setDisable(false);
            mainHBox.setDisable(false);

        });*/

        // stage
        //scene = new Scene(mainVBox);
    }

    private HBox createHBox(QueryDocumentSnapshot document) {
        HBox mainContainer = new HBox(30);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setStyle("-fx-background-color: transparent;");
        // mainContainer.setAlignment(Pos.CENTER);

        // Add four cards to the main container

        for (int i = 0; i < 1; i++) {
            mainContainer.getChildren().add(createCard(document));
        }

        return mainContainer;
    }

    private VBox createCard(QueryDocumentSnapshot document) {
        card = new VBox(10);
        Label dateLabel = new Label();
        Label reqBuddLabel = new Label();
        Label usernameLabel = new Label();
        Label expLabel = new Label();
        Label categoryButton = new Label();
        Label locationLabel = new Label();
        Label noOfDaysLabel = new Label();
        Label captionLabel = new Label("Rising with the sun over South Africa");
        Label membersLabel = new Label("Total Members:4");
        Label vehicleAvailabilityLabel = new Label("Vehicle: \u2713");
        Label vehiclenamelLabel = new Label();


        reqBuddLabel= new Label(String.valueOf(document.getData().get("buddiesRequired")));
        reqBuddLabel.setTextFill(Color.BLACK);

        //  try{
        
        // DocumentReference c2w_pi_docRef = dbFirestore.collection("Users").document(username); 

        // ApiFuture<DocumentSnapshot> c2w_pi_future = c2w_pi_docRef.get();
        // DocumentSnapshot doc = c2w_pi_future.get();
        // usernameLabel = new Label((String)(doc.getData().get("username")));
        
        // }
        // catch(Exception e){
        //     e.printStackTrace();
        // }
        //System.out.println((String)document.getData().get("buddiesRequired"));
        dateLabel = new Label((String)(document.getData().get("date")));
        usernameLabel = new Label((String)(document.getData().get("username")));
        expLabel = new Label("Experienced:- "+String.valueOf(document.getData().get("experience")));
        categoryButton =  new Label((String)document.getData().get("category"));
        locationLabel = new Label((String)document.getData().get("place"));
        noOfDaysLabel = new Label("Days:- "+String.valueOf(document.getData().get("noOfDays")));
        captionLabel = new Label((String)document.getData().get("caption"));
        membersLabel = new Label("Total Members:- "+String.valueOf(document.getData().get("totalMembers")));
        vehicleAvailabilityLabel = new Label("Vehicle Availibility:- "+String.valueOf(document.getData().get("vehicleAvailable")));
        vehiclenamelLabel = new Label((String)document.getData().get("vehicleName"));


        card.setStyle(
                "-fx-background-color: #F5F5F5;-fx-background-radius: 15;-fx-border-color: #ddd; -fx-border-width: 1; -fx-border-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 0);");
        card.setPrefWidth(280);
        card.setPrefHeight(500);
        card.setAlignment(Pos.CENTER);
        card.setMaxHeight(400);
        card.setPadding(new Insets(10, 0, 10, 0));
        card.setOnMouseEntered(e -> {
            card.setScaleX(1.05);
            card.setScaleY(1.05);
        });
        card.setOnMouseExited(e -> {
            card.setScaleX(1);
            card.setScaleY(1);
        });

        StackPane imagePane = new StackPane();
        ImageView imageView = new ImageView();
        imageView.setFitWidth(220);
        imageView.setFitHeight(250);

        try {
            
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
            Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            imageView.setClip(clip);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dateLabel.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        dateLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        dateLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 5; -fx-background-radius: 5;");

       
        reqBuddLabel.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 15));
        reqBuddLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        reqBuddLabel.setStyle("-fx-background-color: white; -fx-padding:5; -fx-background-radius: 15;");

        StackPane.setMargin(dateLabel, new Insets(5, 140, 210, 5));
        StackPane.setMargin(reqBuddLabel, new Insets(5, 0, 210, 170));
        imagePane.getChildren().addAll(imageView, dateLabel, reqBuddLabel);

     
        usernameLabel.setFont(javafx.scene.text.Font.font("1", javafx.scene.text.FontWeight.BOLD, 17));
        usernameLabel.setTextFill(javafx.scene.paint.Color.BLACK);

        
        expLabel.setFont(javafx.scene.text.Font.font("1", javafx.scene.text.FontWeight.NORMAL, 14));
        expLabel.setTextFill(javafx.scene.paint.Color.BLACK);

        HBox userHb = new HBox(12, usernameLabel, expLabel);
        userHb.setAlignment(Pos.CENTER_LEFT);
        userHb.setPadding(new Insets(0, 0, 0, 20));

        
        categoryButton.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
        categoryButton.setStyle("-fx-background-color:red; -fx-text-fill: white; -fx-background-radius: 15;");
        categoryButton.setPadding(new Insets(5));

        
        locationLabel.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 15));
        locationLabel.setTextFill(javafx.scene.paint.Color.DARKMAGENTA);
        locationLabel.setMinWidth(160);

        
        noOfDaysLabel.setFont(javafx.scene.text.Font.font("Calibri", javafx.scene.text.FontWeight.BOLD, 16));
        noOfDaysLabel.setTextFill(javafx.scene.paint.Color.DARKSLATEBLUE);

        HBox locDaysHBox = new HBox(0, locationLabel, noOfDaysLabel);
        locDaysHBox.setAlignment(Pos.CENTER_LEFT);
        locDaysHBox.setPadding(new Insets(0, 0, 0, 20));
        
        
        captionLabel.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.SEMI_BOLD, 14));
        captionLabel.setWrapText(true);
        captionLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        captionLabel.setStyle("-fx-background-color:#f1f1f1; -fx-padding: 5; -fx-background-radius: 7;");

        
        membersLabel.setFont(javafx.scene.text.Font.font("Calibri", javafx.scene.text.FontWeight.BLACK, 17));
        membersLabel.setTextFill(javafx.scene.paint.Color.DARKSLATEGRAY);
        membersLabel.setAlignment(Pos.BOTTOM_LEFT);

        HBox memHBox = new HBox(membersLabel);
        memHBox.setAlignment(Pos.CENTER_LEFT);
        memHBox.setPadding(new Insets(0, 0, 0, 20));

        
        vehicleAvailabilityLabel
                .setFont(javafx.scene.text.Font.font("Calibri", javafx.scene.text.FontWeight.BLACK, 17));
        vehicleAvailabilityLabel.setTextFill(javafx.scene.paint.Color.DARKSLATEGRAY);

        
        vehiclenamelLabel.setFont(javafx.scene.text.Font.font("Calibri", javafx.scene.text.FontWeight.BLACK, 17));
        vehiclenamelLabel.setTextFill(javafx.scene.paint.Color.DARKSLATEGRAY);

        HBox vehicHBox = new HBox(20,vehicleAvailabilityLabel,vehiclenamelLabel);
        vehicHBox.setAlignment(Pos.CENTER_LEFT);
        vehicHBox.setPadding(new Insets(0, 0, 0, 20));

        Button submitButton = new Button("Confirm");
        submitButton.setStyle("-fx-background-color:#2C3E50;-fx-text-fill:white;-fx-background-radius:3px;");
        submitButton.setFont(javafx.scene.text.Font.font("Calibri",FontWeight.SEMI_BOLD,15));
        String str;
        
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                
                     
                    showAlert("Congratulations...!", "You are added to this amazing trip Successfully");
                    
            }
        });
        
        

        card.getChildren().addAll(imagePane, locDaysHBox, categoryButton, userHb,captionLabel, memHBox, vehicHBox,submitButton);

        return card;
    }

    public VBox getRoot() {
        return mainVBox;
    }

      private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    }

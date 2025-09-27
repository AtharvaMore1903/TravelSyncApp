package com.tabpane.controller;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;

public class LoginTrial2 {

    private Scene scene;
    public Stage primaryStage;

    public static Firestore dbFirestore;
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

    public LoginTrial2(Stage primaryStage,String username, String place,String dateStr,int buddiesRequired,int totalMembers,int noOfDaysINt,String category,boolean experience,boolean vehicleAvailable,String vehicleName,String caption){
        this.primaryStage = primaryStage;

        this.place = place;
        this.username = username;
        this.dateStr = dateStr;
        this.buddiesRequired = buddiesRequired;
        this.totalMembers = totalMembers;
        this.noOfDaysINt = noOfDaysINt;
        this.category = category;
        this.experience = experience;
        this.vehicleAvailable = vehicleAvailable;
        this.vehicleName = vehicleAvailable ? vehicleName : null;
        this.caption = caption;
        initLoginScene2();
    }

    public void initLoginScene2() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);

        String cs = "-fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 5;-fx-background-color:BLACK";

        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, new CornerRadii(10, 0, 0, 10, false), null);
        BackgroundFill backgroundFill2 = new BackgroundFill(Color.WHITE, new CornerRadii(0, 10, 10, 0, false), null);
        BackgroundFill bkfill = new BackgroundFill(Color.WHITE, new CornerRadii(10, 10, 10, 10, false), null);

        Text sceneTitle = new Text("Welcome!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 70));
        grid.add(sceneTitle, 0, 0, 5, 1);

        Label emailLabel = new Label("Username");
        grid.add(emailLabel, 0, 1);
        emailLabel.setFont(Font.font(null, FontWeight.BOLD, 20));

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("username");
        grid.add(emailTextField, 1, 1);
        emailTextField.setPrefWidth(350);
        emailTextField.setPrefHeight(45);
        // emailTextField.setStyle(curveStyle);
        emailTextField.setBackground(new Background(bkfill));

        Label pwLabel = new Label("Password");
        grid.add(pwLabel, 0, 2);
        pwLabel.setFont(Font.font(null, FontWeight.BOLD, 20));

        PasswordField passwordField = new PasswordField();

        TextField textField = new TextField();
        textField.setMinSize(350, 45);
        textField.setManaged(false); // Hide text field initially
        textField.setVisible(false); // Hide text field initially
        textField.setBackground(new Background(backgroundFill));

        // Bind the textField text property to passwordField text property
        textField.textProperty().bindBidirectional(passwordField.textProperty());
        passwordField.setMinSize(350, 45);
        passwordField.setDisable(false);
        passwordField.setPromptText("Password ");
        passwordField.setBackground(new Background(backgroundFill));

        // Create the eye button
        Button eyeButton = new Button("👁"); // You can use an eye icon here
        eyeButton.setFocusTraversable(false); // Prevent the button from gaining focus
        eyeButton.setMinHeight(45);
        eyeButton.setBackground(new Background(backgroundFill2));

        eyeButton.setOnMouseEntered(e -> {
            eyeButton.setScaleX(1.05);
        });
        eyeButton.setOnMouseExited(e -> {
            eyeButton.setScaleX(1);
        });

        // Add a listener to the eye button to toggle visibility
        eyeButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            textField.setVisible(true);
            textField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
        });

        eyeButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            textField.setVisible(false);
            textField.setManaged(false);
            passwordField.setVisible(true);
            passwordField.setManaged(true);
        });

        // Layout the components in an HBox
        HBox hbox = new HBox(passwordField, textField, eyeButton);
        // hbox.setMinSize(200, 50);

        VBox emailHBox = new VBox(10, emailLabel, emailTextField);
        VBox passHBox = new VBox(15, pwLabel, hbox);

        Button loginButton = new Button("Login");
        loginButton.setFont(new Font(16));
        loginButton.setPrefWidth(150);
        loginButton.setPrefHeight(30);
        loginButton.setStyle("-fx-background-color:black");
        loginButton.setFont(Font.font(null, FontWeight.BOLD, 20));
        loginButton.setTextFill(Color.WHITE);
        // loginButton.setBackground(new Background(bkfill));
        loginButton.setStyle(cs);

        Button createButton = new Button("Create acount");
        createButton.setFont(new Font(16));
        createButton.setPrefWidth(350);
        createButton.setPrefHeight(30);
        // createButton.setBackground(new Background(bkfill));
        createButton.setStyle("-fx-background-color:Black");
        createButton.setFont(Font.font(null, FontWeight.BOLD, 20));
        createButton.setTextFill(Color.WHITE);
        createButton.setStyle(cs);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = emailTextField.getText();
                String password = passwordField.getText();
                if (!emailTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    if(authenticateUser(username, password)) {
                    
                        TabPaneDemo tabPaneDemo = new TabPaneDemo(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
                        primaryStage.setScene(tabPaneDemo.initTabScene());
                        emailTextField.clear(); // Clear
                        passwordField.clear(); // Clear
                        PostController postController = new PostController(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);

                    } else {
                    //c2w_pi_output.setText("Invalid Username or password");
                    }
                    
                }else {
                    //c2w_pi_output.setText("Please Enter Username and Password"); // Show error message

                }
                
            }

            });
            

        loginButton.setOnMouseEntered(e -> {
            loginButton.setScaleX(1.05);
        });
        loginButton.setOnMouseExited(e -> {
            loginButton.setScaleX(1);
        });
        CreateAcc createAcc = new CreateAcc(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Account Created");
                primaryStage.setScene(createAcc.initCreateScene());
            }
        });
        createButton.setOnMouseEntered(e -> {
            createButton.setScaleX(1.05);
        });
        createButton.setOnMouseExited(e -> {
            createButton.setScaleX(1);
        });


        Text or = new Text("---------OR---------");
        or.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        or.setFill(Color.BLACK);

        Region leftLine = new Region();
        Region rightLine = new Region();
        // Set the preferred height of the lines
        leftLine.setPrefHeight(1);
        rightLine.setPrefHeight(1);

        // Use HBox to arrange the elements horizontally
        HBox hb = new HBox(10); // 10 is the spacing between elements
        hb.setAlignment(Pos.CENTER);

        // Ensure the lines grow to fill the available space
        HBox.setHgrow(leftLine, Priority.ALWAYS);
        HBox.setHgrow(rightLine, Priority.ALWAYS);

        // Add the lines and the label to the HBox
        hb.getChildren().addAll(leftLine, or, rightLine);

        // Add some padding around the HBox
        hb.setPadding(new Insets(10));

        VBox vBox = new VBox(25, sceneTitle, emailHBox, passHBox, loginButton, hb, createButton);
        vBox.setAlignment(Pos.CENTER);

        HBox pageHBox = new HBox(vBox);
        grid.add(pageHBox, 1, 1);

        Pane viewPane = new Pane(grid);

        grid.setLayoutX(200);
        grid.setLayoutY(0);
        grid.setPrefWidth(1800);
        grid.setPrefHeight(1000);

        BackgroundImage myBI = new BackgroundImage(new Image("bus.jpg", primaryStage.getWidth(), primaryStage.getHeight(), false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        viewPane.setBackground(new Background(myBI));
        scene = new Scene(viewPane);
    }

    private boolean authenticateUser(String username, String password) {
        
            try {
                    // FirebaseInitialization.initializeFirebase();
                    // Firestore dbFirestore = FirebaseInitialization.getFirestore();
                DocumentReference c2w_pi_docRef = dbFirestore.collection("Users").document(username); 

                ApiFuture<DocumentSnapshot> c2w_pi_future = c2w_pi_docRef.get();
                DocumentSnapshot doc = c2w_pi_future.get();
                
                    if (doc.exists()) {
                        
                        String c2w_pi_storedPassword = doc.getString("password"); 
        
                        return password.equals(c2w_pi_storedPassword); 
                    }
                    else{
                        showAlert("Login unsuccessful ", "Invalid username or password");
                    }


            } catch (Exception e) {
                e.printStackTrace(); 
            }
            return false;
    }

    public Scene getLoginScene2() {
        return scene;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
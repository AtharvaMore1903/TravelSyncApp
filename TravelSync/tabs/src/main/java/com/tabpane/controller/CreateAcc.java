package com.tabpane.controller;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Callback;
import java.time.LocalDate;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.tabpane.Database.GetUserData;

public class CreateAcc  {

    private Stage primaryStage;
    private Scene scene;
    public static Firestore dbFirestore;
    public static String username;

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

    public CreateAcc (Stage primaryStage,String username, String place, String dateStr, int buddiesRequired,int totalMembers, int noOfDaysINt, String category, boolean experience, boolean vehicleAvailable,
    String vehicleName, String caption) {
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

    }
    public Scene initCreateScene() {
        //primaryStage.setTitle("Registration Form");

        // Creating the grid pane layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        String curveStyle = "-fx-background-radius: 9; -fx-border-radius: 9; -fx-padding: 5;";

        Label nameLabel = new Label("Name");
        GridPane.setHalignment(nameLabel, HPos.CENTER);
        nameLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.WHITE);

        BorderStroke bkstr = new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 0, 2, 0));
        TextField nameField = new TextField();
        nameField.setPromptText(" Enter Your Name & Surname");
        nameField.setStyle("-fx-background-color:transparent;-fx-prompt-text-fill:BLack; -fx-text-fill:Black;-fx-padding:0;-fx-font-size:20px;-fx-font-family:'century';");
        nameField.setBorder(new Border(bkstr));
        nameField.setMinHeight(60);
        nameField.setMinWidth(400);

        Label mainLabel = new Label("Register");
        mainLabel.setStyle(" -fx-text-fill:Black;-fx-padding:0;-fx-font-size:60px;-fx-font-family:'georgia';"); 

        TextField userNameTf = new TextField();
        userNameTf.setPromptText(" Enter Username");
        userNameTf.setStyle("-fx-background-color:transparent;-fx-prompt-text-fill:black; -fx-text-fill:black;-fx-padding:0;-fx-font-size:20px;-fx-font-family:'century';");
        userNameTf.setBorder(new Border(bkstr));
        userNameTf.setMinHeight(60);
        userNameTf.setMinWidth(400);

        TextField emailField = new TextField();
        emailField.setPromptText(" @gmail.com");
        emailField.setStyle(nameField.getStyle());
        emailField.setBorder(new Border(bkstr));
        emailField.setMinHeight(60);
        emailField.setMinWidth(400);

        PasswordField passTextField = new PasswordField();
        passTextField.setPromptText("Password");
        passTextField.setStyle("-fx-background-color:transparent;-fx-prompt-text-fill:black; -fx-text-fill:black;-fx-padding:0;-fx-font-size:20px;-fx-font-family:'century';");
        //passTextField.setFont(Font.font("century", FontWeight.BOLD, 20));
        //passTextField.setStyle(nameField.getStyle());
        passTextField.setBorder(new Border(bkstr));
        passTextField.setDisable(false);
        passTextField.setMinHeight(60);
        passTextField.setMinWidth(400);

        TextField textField = new TextField();
        textField.setMinSize(350, 45);
        textField.setManaged(false); 
        textField.setVisible(false); 
        textField.setMinSize(400, 60);
        textField.setStyle(nameField.getStyle());
        textField.setBorder(new Border(bkstr));

        // Bind the textField text property to passTextField text property
        textField.textProperty().bindBidirectional(passTextField.textProperty());
        
        // Create the eye button
        Button eyeButton = new Button("👁"); 
        eyeButton.setFocusTraversable(false); 
        eyeButton.setMinHeight(45);
        eyeButton.setStyle("-fx-background-color:Transparent");



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
            passTextField.setVisible(false);
            passTextField.setManaged(false);
        });

        eyeButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            textField.setVisible(false);
            textField.setManaged(false);
            passTextField.setVisible(true);
            passTextField.setManaged(true);
        });

        PasswordField confpass = new PasswordField();
        confpass.setPromptText("Confirm Password ");
        confpass.setStyle(nameField.getStyle());
        confpass.setBorder(new Border(bkstr));
        confpass.setMinHeight(60);
        confpass.setMinWidth(400);

        TextField textField1 = new TextField();
        textField1.setMinSize(350, 45);
        textField1.setManaged(false); 
        textField1.setVisible(false); 
        textField1.setMinSize(400, 60);
        textField1.setStyle(nameField.getStyle());
        textField1.setBorder(new Border(bkstr));

        // Bind the textField1 text property to confpass text property
        textField1.textProperty().bindBidirectional(confpass.textProperty());
        
        // Create the second eye button
        Button eyeButton2 = new Button("👁"); 
        eyeButton2.setFocusTraversable(false); 
        eyeButton2.setMinHeight(45);
        eyeButton2.setStyle("-fx-background-color:Transparent");

        eyeButton2.setOnMouseEntered(e -> {
            eyeButton2.setScaleX(1.05);
        });
        eyeButton2.setOnMouseExited(e -> {
            eyeButton2.setScaleX(1);
        });

        // Add a listener to the eye button to toggle visibility
        eyeButton2.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            textField1.setVisible(true);
            textField1.setManaged(true);
            confpass.setVisible(false);
            confpass.setManaged(false);      
        });

        eyeButton2.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            textField1.setVisible(false);
            textField1.setManaged(false);
            confpass.setVisible(true);
            confpass.setManaged(true);
        });

        HBox hbox = new HBox(textField, passTextField, eyeButton);
        HBox hbox2 = new HBox(textField1, confpass, eyeButton2);

        TextField mobileField = new TextField();
        mobileField.setPromptText("Enter mobile number");
        mobileField.setStyle(curveStyle);
        mobileField.setMinHeight(60);
        mobileField.setMinWidth(400);

        TextField birthDateField = new TextField();
        birthDateField.setPromptText("mm-dd-yyyy");
        birthDateField.setStyle(nameField.getStyle());
        birthDateField.setBorder(new Border(bkstr));
        birthDateField.setMinHeight(60);
        birthDateField.setMinWidth(400);

        DatePicker datepicker = new DatePicker();
        datepicker.setPromptText("DD-MM-YYYY");
        //datepicker.setStyle(borStyle);
        //datepicker.setStyle("-fx-background-color:;");
        //datepicker.setBorder(new Border(bkstr));
        datepicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
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
        datepicker.getEditor().setStyle("-fx-background-color: skyblue; -fx-text-fill: black;");
        //datepicker.setPromptText("dd/mm/YYYY");
        datepicker.setMinHeight(50);
        datepicker.setMinWidth(400);
        datepicker.setOnMouseEntered(e->{
            datepicker.setScaleX(1.03);
        });
        datepicker.setOnMouseExited(e->{
            datepicker.setScaleX(1);
        });


        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Prefer not to say");
        genderComboBox.setPromptText("Select gender");
        genderComboBox.setStyle(nameField.getStyle());
        genderComboBox.setBorder(new Border(bkstr));
        genderComboBox.setPadding(new Insets(0, 0, 0, 0));
        genderComboBox.setMinHeight(60);
        genderComboBox.setMinWidth(400);

        TextField addressField = new TextField();
        addressField.setPromptText("Enter address");
        addressField.setStyle(nameField.getStyle());
        addressField.setBorder(new Border(bkstr));
        addressField.setMinHeight(60);
        addressField.setMinWidth(400);

        ComboBox<String> professionComboBox = new ComboBox<>();
        professionComboBox.getItems().addAll("Student","Private Job","Businessman", "Govt Employee", "Teacher", "Other");
        professionComboBox.setPromptText("Select a profession");
        professionComboBox.setStyle("-fx-background-color:transparent;-fx-prompt-text-fill:RED; -fx-text-fill:WHITE;-fx-padding:0;-fx-font-size:20px;-fx-font-family:'century'");
        professionComboBox.setBorder(new Border(bkstr));
        professionComboBox.setMinHeight(60);
        professionComboBox.setMinWidth(400);

        TextField postalCodeField = new TextField();
        postalCodeField.setPromptText("Enter postal code");
        postalCodeField.setStyle(nameField.getStyle());
        postalCodeField.setBorder(new Border(bkstr));
        postalCodeField.setMinHeight(60);
        postalCodeField.setMinWidth(400);

        Button submitButton = new Button("Submit");
        GridPane.setHalignment(submitButton, HPos.CENTER);
        submitButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        submitButton.setMinHeight(60);
        submitButton.setMinWidth(300);

        submitButton.setOnMouseEntered(e -> {
            submitButton.setScaleX(1.05);
            submitButton.setScaleY(1.05);
        });
        submitButton.setOnMouseExited(e -> {
            submitButton.setScaleX(1);
            submitButton.setScaleY(1);
        });

        AccSuccessController accSuccessController = new AccSuccessController(primaryStage,username,place,dateStr,buddiesRequired,totalMembers,noOfDaysINt,category,experience,vehicleAvailable,vehicleName,caption);
        //PostController postController = new PostController(userNameTf.getText()); 

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String name = nameField.getText();
                String date = datepicker.getValue().toString();
                String gender = genderComboBox.getValue();
                String address = addressField.getText();
                String postalCode = postalCodeField.getText();
                username = userNameTf.getText();
                String profession = professionComboBox.getValue();
                String password = passTextField.getText();
                String confirmPassword = confpass.getText();

                try{
                    // FirebaseInitialization.initializeFirebase();
                    // Firestore dbFirestore = FirebaseInitialization.getFirestore();

                    DocumentReference docRef = dbFirestore.collection("Users").document(username);
                    ApiFuture<DocumentSnapshot> future = docRef.get();
                    DocumentSnapshot document = future.get();   
                //    PostController postController = new PostController(username);


                    if(document.exists()){
                        showAlert("SignUp Failed","Username already Exists");
                    }
                    else{
                        dbFirestore.collection("Users").document(username).set(new GetUserData(name, date, gender, address, postalCode, username, profession, confirmPassword));
                        primaryStage.setScene(accSuccessController.initAccSuccessScene());
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                GetUserData getUserData = new GetUserData(name, date, gender, address, postalCode, username, profession,password);
                getUserData.setName(name);
                getUserData.setDate(date);
                getUserData.setGender(gender);
                getUserData.setAddress(address);
                getUserData.setPostalCode(postalCode);
                getUserData.setUsername(username);
                getUserData.setProfession(profession);
                getUserData.setPassword(password);        

                // primaryStage.setScene(accSuccessController.getAccSuccesScene());
            }
        });

        
        // submitButton.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         // String fullName = nameField.getText();
        //         // String email = emailField.getText();
        //         // String mobile = mobileField.getText();
        //         // String birthDate = birthDateField.getText();
        //         // String gender = genderComboBox.getValue();
        //         // String address = addressField.getText();
        //         // String profession = professionComboBox.getValue();
        //         // String postalCode = postalCodeField.getText();

        //         // System.out.println("Full Name: " + fullName);
        //         // System.out.println("Email Address: " + email);
        //         // System.out.println("Mobile Number: " + mobile);
        //         // System.out.println("Birth Date: " + birthDate);
        //         // System.out.println("Gender: " + gender);
        //         // System.out.println("Address: " + address);
        //         // System.out.println("Profession: " + profession);
        //         // System.out.println("Postal Code: " + postalCode);


        //         primaryStage.setScene(accSuccessController.initAccSuccessScene());
        //     }
        // });

        HBox titleHBox = new HBox(mainLabel);
        titleHBox.setAlignment(Pos.CENTER);
        VBox formBox = new VBox(20, nameField, datepicker, genderComboBox, addressField, postalCodeField);
        formBox.setAlignment(Pos.CENTER_LEFT);

        VBox box1Box = new VBox(20, userNameTf, professionComboBox, emailField, hbox, hbox2);
        box1Box.setAlignment(Pos.CENTER_LEFT);

        HBox submitHBox = new HBox(submitButton);
        submitHBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(60, formBox, box1Box);
        hBox.setAlignment(Pos.CENTER);

        // Adding gradient background
        Stop[] stops = new Stop[]{new Stop(0, Color.LIGHTBLUE), new Stop(1, Color.DARKBLUE)};
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill3Fill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);

        Image image = new Image("bus.jpg", primaryStage.getWidth(), primaryStage.getHeight(), false, false);
        BackgroundImage bkimg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, null);

        VBox mainVBox = new VBox(60, titleHBox, hBox, submitHBox);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setBackground(new Background(bkimg));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        scene = new Scene(mainVBox);
        //primaryStage.setScene(scene);
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
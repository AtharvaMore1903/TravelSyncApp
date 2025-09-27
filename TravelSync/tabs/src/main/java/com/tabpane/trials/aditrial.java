package com.tabpane.trials;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class aditrial extends Application {

    private Scene createForgotPasswordScene(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);

        Text sceneTitle = new Text("Forgot Password");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 70));
        grid.add(sceneTitle, 0, 0, 5, 1);

        Label phoneLabel = new Label("Phone Number");
        phoneLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        grid.add(phoneLabel, 0, 1);

        TextField phoneTextField = new TextField();
        phoneTextField.setPromptText("Enter your phone number");
        phoneTextField.setPrefWidth(350);
        phoneTextField.setPrefHeight(45);
        grid.add(phoneTextField, 1, 1);

        Button sendOTPButton = new Button("Send OTP");
        sendOTPButton.setFont(Font.font(null, FontWeight.BOLD, 20));
        sendOTPButton.setPrefWidth(150);
        sendOTPButton.setPrefHeight(45);
        grid.add(sendOTPButton, 1, 2);

        Label otpLabel = new Label("OTP");
        otpLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        grid.add(otpLabel, 0, 3);

        TextField otpTextField = new TextField();
        otpTextField.setPromptText("Enter OTP");
        otpTextField.setPrefWidth(350);
        otpTextField.setPrefHeight(45);
        grid.add(otpTextField, 1, 3);

        Button verifyOTPButton = new Button("Verify OTP");
        verifyOTPButton.setFont(Font.font(null, FontWeight.BOLD, 20));
        verifyOTPButton.setPrefWidth(150);
        verifyOTPButton.setPrefHeight(45);
        grid.add(verifyOTPButton, 1, 4);

        Button backButton = new Button("Back");
        backButton.setFont(Font.font(null, FontWeight.BOLD, 20));
        backButton.setPrefWidth(150);
        backButton.setPrefHeight(45);
        backButton.setOnAction(e -> primaryStage.setScene(createLoginScene(primaryStage)));
        grid.add(backButton, 1, 5);

        return new Scene(grid, 800, 600);
    }

    private Scene createLoginScene(Stage primaryStage) {
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

        Label emailLabel = new Label("Email");
        grid.add(emailLabel, 0, 1);
        emailLabel.setFont(Font.font(null, FontWeight.BOLD, 20));

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("@email.com");
        grid.add(emailTextField, 1, 1);
        emailTextField.setPrefWidth(350);
        emailTextField.setPrefHeight(45);
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

        textField.textProperty().bindBidirectional(passwordField.textProperty());
        passwordField.setMinSize(350, 45);
        passwordField.setPromptText("Password ");
        passwordField.setBackground(new Background(backgroundFill));

        Button eyeButton = new Button("👁");
        eyeButton.setFocusTraversable(false);
        eyeButton.setMinHeight(45);
        eyeButton.setBackground(new Background(backgroundFill2));

        eyeButton.setOnMouseEntered(e -> {
            eyeButton.setScaleX(1.05);
        });
        eyeButton.setOnMouseExited(e -> {
            eyeButton.setScaleX(1);
        });

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

        HBox hbox = new HBox(passwordField, textField, eyeButton);

        VBox emailHBox = new VBox(10, emailLabel, emailTextField);
        VBox passHBox = new VBox(15, pwLabel, hbox);

        Button loginButton = new Button("Login");
        loginButton.setFont(new Font(16));
        loginButton.setPrefWidth(150);
        loginButton.setPrefHeight(30);
        loginButton.setStyle("-fx-background-color:black");
        loginButton.setFont(Font.font(null, FontWeight.BOLD, 20));
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle(cs);

        Button createButton = new Button("Create account");
        createButton.setFont(new Font(16));
        createButton.setPrefWidth(350);
        createButton.setPrefHeight(30);
        createButton.setStyle("-fx-background-color:Black");
        createButton.setFont(Font.font(null, FontWeight.BOLD, 20));
        createButton.setTextFill(Color.WHITE);
        createButton.setStyle(cs);

        Button forgotButton = new Button("Forgot Password");
        forgotButton.setTextFill(Color.BLACK);
        forgotButton.setStyle("-fx-background-color:Transparent");
        forgotButton.setUnderline(true);
        forgotButton.setOnMouseEntered(e -> {
            forgotButton.setFont(Font.font(null, FontWeight.BOLD, 15));
        });
        forgotButton.setOnMouseExited(e -> {
            forgotButton.setFont(Font.font(null, FontWeight.NORMAL, 15));
        });

        forgotButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(createForgotPasswordScene(primaryStage));
            }
        });
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                // firebaseService.login ();
            }
        });

        loginButton.setOnMouseEntered(e->{
            loginButton.setScaleX(1.05);
        });
        loginButton.setOnMouseExited(e->{
            loginButton.setScaleX(1);
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Account Created");
            }
        });
        createButton.setOnMouseEntered(e->{
            createButton.setScaleX(1.05);
        });
        createButton.setOnMouseExited(e->{
            createButton.setScaleX(1);
        });

        Text or = new Text("OR");
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

        VBox vBox = new VBox(25, sceneTitle, emailHBox, passHBox, loginButton, forgotButton, hb, createButton);
        vBox.setAlignment(Pos.CENTER);

        HBox pageHBox = new HBox(vBox);
        grid.add(pageHBox, 1, 1);

        Pane viewPane = new Pane(grid);

        grid.setLayoutX(200);
        grid.setLayoutY(0);
        grid.setPrefWidth(1800);
        grid.setPrefHeight(1000);

        BackgroundImage myBI = new BackgroundImage(new Image("bus.jpg", 1980, 1080, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        viewPane.setBackground(new Background(myBI));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.setWidth(screenBounds.getWidth());
        Scene scene = new Scene(viewPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(createLoginScene(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
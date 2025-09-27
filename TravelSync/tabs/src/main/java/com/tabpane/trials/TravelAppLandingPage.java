package com.tabpane.trials;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TravelAppLandingPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        //primaryStage.setTitle("Travel App");

        // Header
        HBox header = new HBox();
        header.setSpacing(20);
        header.setAlignment(Pos.CENTER);
        Label appName = new Label("TravelApp");
        appName.setFont(new Font("Arial", 24));
        header.getChildren().add(appName);

        // Hero Section
        VBox heroSection = new VBox();
        heroSection.setAlignment(Pos.CENTER);
        heroSection.setSpacing(10);
        ImageView heroImage = new ImageView(new Image("bus.jpg"));
        heroImage.setFitWidth(1800);
        heroImage.setPreserveRatio(true);
        Label heroText = new Label("Explore the World with Us");
        heroText.setFont(new Font("Arial", 36));
        heroText.setTextFill(Color.WHITE);
        Button getStartedButton = new Button("Get Started");
        getStartedButton.setStyle("-fx-background-color: #ff7f50; -fx-text-fill: white; -fx-font-size: 18px;");

        heroSection.getChildren().addAll(heroImage, heroText, getStartedButton);

        // Features Section
        VBox featuresSection = new VBox();
        featuresSection.setAlignment(Pos.CENTER);
        featuresSection.setSpacing(20);
        Label featuresTitle = new Label("Features");
        featuresTitle.setFont(new Font("Arial", 24));
        HBox features = new HBox();
        features.setAlignment(Pos.CENTER);
        features.setSpacing(30);
        // Add feature icons and descriptions here
        featuresSection.getChildren().addAll(featuresTitle, features);

        // Testimonials Section
        VBox testimonialsSection = new VBox();
        testimonialsSection.setAlignment(Pos.CENTER);
        testimonialsSection.setSpacing(10);
        Label testimonialsTitle = new Label("Testimonials");
        testimonialsTitle.setFont(new Font("Arial", 24));
        // Add testimonials content here
        testimonialsSection.getChildren().addAll(testimonialsTitle);

        // Footer
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        Label footerText = new Label("© 2024 TravelApp");
        footerText.setFont(new Font("Arial", 12));
        footer.getChildren().add(footerText);

        // Main Layout
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setSpacing(20);
        mainLayout.getChildren().addAll(header, heroSection, featuresSection, testimonialsSection, footer);

        // Scene
        Scene scene = new Scene(mainLayout, 2000, 1200);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Animations
        FadeTransition fadeInText = new FadeTransition(Duration.seconds(3), heroText);
        fadeInText.setFromValue(0.0);
        fadeInText.setToValue(1.0);

        FadeTransition fadeInButton = new FadeTransition(Duration.seconds(3), getStartedButton);
        fadeInButton.setFromValue(0.0);
        fadeInButton.setToValue(1.0);

        ScaleTransition scaleButton = new ScaleTransition(Duration.seconds(0.5), getStartedButton);
        scaleButton.setFromX(1.0);
        scaleButton.setFromY(1.0);
        scaleButton.setToX(1.1);
        scaleButton.setToY(1.1);
        scaleButton.setCycleCount(ScaleTransition.INDEFINITE);
        scaleButton.setAutoReverse(true);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeInText, fadeInButton);
        sequentialTransition.play();
        scaleButton.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
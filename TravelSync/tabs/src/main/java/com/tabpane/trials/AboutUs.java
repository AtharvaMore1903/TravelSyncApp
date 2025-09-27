package com.tabpane.trials;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AboutUs {

    private Stage primaryStage;
    private Scene scene;
    private VBox vBox;

    public AboutUs(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initAboutUsScene();
    }

    public Scene initAboutUsScene() {

        //primaryStage.setTitle("About Us");

        // Message
        Label lb1 = new Label("Thank you Sir");
        lb1.setFont(new Font(40));
        lb1.setStyle("-fx-font-family:Tohima; -fx-font-weight:900; -fx-text-fill: Blue");

        // Sir Img
        Image ig = new Image("images/ sir.jpg");

        ImageView imView = new ImageView(ig);
        imView.setPreserveRatio(true);
        imView.setFitWidth(350);
        imView.setFitHeight(350);

        Text text = new Text(
                "Welcome to our team! We are a group of passionate and dedicated professionals who thrive on creativity and innovation. With a strong commitment to excellence, we aim to deliver high-quality solutions that make a difference. Our team comprises skilled developers who are not only proficient in their craft but also genuinely enjoy what they do. Together, we collaborate, innovate, and strive to exceed expectations in every project we undertake. Thank you for visiting our page and getting to know us better. We look forward to the opportunity to work with you and create something remarkable.\n");
        text.setFont(new Font("Verdana", 20));
        text.setWrappingWidth(1000);
        text.setFill(Color.BLACK);
        text.setTextAlignment(TextAlignment.JUSTIFY);

        // Title
        Label head1 = new Label("We're a team of professional people who love what they do.");
        head1.setFont(new Font("IMPACT", 40));
        head1.setStyle("-fx-font-weight:900; -fx-text-fill: Blue");
        head1.setAlignment(Pos.CENTER);

        // Team Title
        Label teamHeader = new Label("MEET OUR TEAM");
        teamHeader.setStyle("-fx-font-size: 40px; -fx-text-fill: DarkGreen; -fx-font-weight: Bold ");

        VBox thanksBox = new VBox(20, lb1);
        thanksBox.setAlignment(Pos.CENTER);
        thanksBox.setMinHeight(20);
        thanksBox.setMinWidth(80);

        // Team Members
        VBox vimg1 = createTeamMember("Ravi", "Developer", "images/RaviAi.jpg");
        VBox vimg2 = createTeamMember("Aditya", "Developer", "images/AdityaAii.jpg");
        VBox vimg3 = createTeamMember("Ajinkya", "Developer", "images/AJinkyAi.jpg");
        VBox vimg4 = createTeamMember("Pratik", "Developer", "images/PratikAi.jpg");

        HBox imageHbox = new HBox(40, thanksBox, vimg1, vimg2, vimg3, vimg4);
        imageHbox.setAlignment(Pos.CENTER);

        VBox fullScreen = new VBox(30, lb1, imView, text, head1, teamHeader, imageHbox);
        fullScreen.setPrefWidth(1800);
        fullScreen.setPrefHeight(950);
        fullScreen.setAlignment(Pos.CENTER);

        // Add ScrollPane to FullScreen
        ScrollPane fullScreenScrollPane = new ScrollPane(fullScreen);
        fullScreenScrollPane.setFitToWidth(true);

        HBox main = new HBox(fullScreenScrollPane);
        main.setPrefWidth(1800);
        main.setPrefHeight(850);
        main.setAlignment(Pos.CENTER);

        // Add another ScrollPane to cover the entire stage
        ScrollPane stageScrollPane = new ScrollPane(main);
        stageScrollPane.setFitToWidth(true);
        stageScrollPane.setFitToHeight(true);

        StackPane background = new StackPane(stageScrollPane);
        background.setStyle("-fx-background-color: #17201B");

        Scene scene = new Scene(background, 1800, 850);
        scene.setFill(Color.ROYALBLUE);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        // Add animations
        addFadeInAnimation(head1);
        addFadeInAnimation(teamHeader);
        addSlideInAnimation(vimg1);
        addSlideInAnimation(vimg2);
        addSlideInAnimation(vimg3);
        addSlideInAnimation(vimg4);
        addSlideInAnimation(imView); // Added animation for imView

        return scene;
    }

    private VBox createTeamMember(String name, String role, String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);

        // Create a border around the image
        Rectangle border = new Rectangle(300, 300);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(8);

        StackPane imageWithBorder = new StackPane(imageView, border);

        Label nameLabel = new Label(name);
        nameLabel.setFont(new Font("Tahoma", 30));
        nameLabel.setStyle("-fx-text-fill:BLUE ; -fx-font-size:30px; -fx-font-weight:Bold");

        Label roleLabel = new Label(role);
        roleLabel.setFont(new Font("Verdana", 20));

        vBox = new VBox(15, imageWithBorder, nameLabel, roleLabel);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    private void addFadeInAnimation(Label label) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), label);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void addSlideInAnimation(VBox vbox) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), vbox);
        translateTransition.setFromX(-500);
        translateTransition.setToX(0);
        translateTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), vbox);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void addSlideInAnimation(ImageView imageView) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), imageView);
        translateTransition.setFromX(-500);
        translateTransition.setToX(0);
        translateTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), imageView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

}

package com.tabpane.controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AboutUs {

    private static final double WINDOW_WIDTH = 2000;
    private static final double WINDOW_HEIGHT = 1000;

    private  Scene scene;
    private StackPane background;

    private Stage primaryStage;

    public AboutUs(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initAboutUsScene();
    }

    public Scene initAboutUsScene() {
        //primaryStage.setTitle("About Us");
        

        VBox fullScreen = createFullScreenContent();
        ScrollPane fullScreenScrollPane = new ScrollPane(fullScreen);
        fullScreenScrollPane.setFitToWidth(true);

        HBox main = new HBox(fullScreenScrollPane);
        main.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        main.setAlignment(Pos.CENTER);

        ScrollPane stageScrollPane = new ScrollPane(main);
        stageScrollPane.setFitToWidth(true);
        stageScrollPane.setFitToHeight(true);

        background = new StackPane(stageScrollPane);
        background.setStyle("-fx-background-color:transparent");

        scene = new Scene(background, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        return scene;
    }

    public StackPane getRoot(){
        return background;
    }

    private VBox createFullScreenContent() {
        Label thankYouLabel = createLabel("Thank you Shashi Sir", "Tahoma", 40, "Blue", 900);
        ImageView sirImageView = createImageView("sir.jpg", 350, 350);
        Text welcomeText = createText(
                "Welcome to our team! We are a group of passionate and dedicated professionals who thrive on creativity and innovation...",
                "Verdana", 20,  0, Color.BLACK);
        Label titleLabel = createLabel("We're a team of professional people who love what they do.", "IMPACT", 40, "Blue", 900);
        Label teamHeaderLabel = createLabel("MEET OUR TEAM", "Verdana", 40, "DarkGreen", 900);

        VBox membersBox = createMembersBox();
        VBox mentorBox = createMentorBox();

        VBox fullScreen = new VBox(30, thankYouLabel, sirImageView, welcomeText, titleLabel, teamHeaderLabel, membersBox, mentorBox);
        //fullScreen.setStyle("-fx-background-color:Lightblue");
        fullScreen.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        fullScreen.setAlignment(Pos.CENTER);

        addFadeInAnimation(titleLabel);
        addFadeInAnimation(teamHeaderLabel);
        addSlideInAnimation(sirImageView);

        return fullScreen;
    }

    private VBox createMembersBox() {
        VBox membersBox = new VBox(15);
        membersBox.setAlignment(Pos.CENTER);

        Label membersHeader = createLabel("Meet Our Members", "Verdana", 30, "DarkGreen", 900);
        membersBox.getChildren().add(membersHeader);

        membersBox.getChildren().addAll(
            createEmptyLabel("Ravikiran Shinde", "Verdana", 20),
            createEmptyLabel("Aditya More", "Verdana", 20),
            createEmptyLabel("Ajinkya Temak", "Verdana", 20),
            createEmptyLabel("Partik Shewale", "Verdana", 20)
        );

        return membersBox;
    }

    private VBox createMentorBox() {
        VBox mentorBox = new VBox(15);
        mentorBox.setAlignment(Pos.CENTER);

        Label mentorHeader = createLabel("Mentors", "Verdana", 30, "DarkGreen", 900);
        mentorBox.getChildren().add(mentorHeader);

        mentorBox.getChildren().addAll(
            createEmptyLabel("Sachin Patil", "Verdana", 20),
            createEmptyLabel("Pramod Bansode ", "Verdana", 20),
            createEmptyLabel("Shivkumar Tengse", "Verdana", 20),
            createEmptyLabel("Subodh Yelgandharwar", "Verdana", 20),
            createEmptyLabel("Rahul Hatkar", "Verdana", 20)
        );

        return mentorBox;
    }

    private Label createEmptyLabel(String text, String font, double fontSize) {
        Label label = new Label(text);
        label.setFont(new Font(font, fontSize));
        return label;
    }

    private Label createLabel(String text, String fontFamily, double fontSize, String textColor, int fontWeight) {
        Label label = new Label(text);
       // label.setFont(new Font(fontFamily, fontSize));
        label.setStyle("-fx-text-fill: "+textColor+"; -fx-font-weight: "+fontWeight+"; -fx-font-family:"+fontFamily+";-fx-font-size:"+fontSize);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private ImageView createImageView(String imagePath, double fitWidth, double fitHeight) {
        ImageView imageView = new ImageView(imagePath);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
        return imageView;
    }

    private Text createText(String content, String fontFamily, double fontSize, double wrappingWidth, Color color) {
        Text text = new Text(content);
        text.setFont(new Font(fontFamily, fontSize));
        text.setWrappingWidth(wrappingWidth);
        text.setFill(color);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setOnMouseClicked(event -> resetFontSize(text, fontFamily, fontSize));  
        return text;
    }

    private void resetFontSize(Text text, String fontFamily, double fontSize) {
        text.setFont(new Font(fontFamily, fontSize));
    }

    private void addFadeInAnimation(Label label) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), label);
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

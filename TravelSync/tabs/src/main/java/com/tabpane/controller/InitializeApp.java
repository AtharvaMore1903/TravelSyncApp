package com.tabpane.controller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class InitializeApp extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.setWidth(screenBounds.getWidth());
        // LoginTrial2 loginTrial2 = new LoginTrial2(primaryStage);
        // primaryStage.setScene(loginTrial2.getLoginScene2());
        //primaryStage.setScene(loginTrial2.getLoginScene2());

        Image icon = new Image("logo.jpg");

        // Set the icon to the stage
        primaryStage.getIcons().add(icon);

        LandingPage landingPage = new LandingPage(primaryStage, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, 0, 0, 0, STYLESHEET_CASPIAN, false, false, STYLESHEET_MODENA, STYLESHEET_CASPIAN);
        primaryStage.setScene(landingPage.getLandingScene());



         //PostController postController = new PostController(primaryStage);
        // primaryStage.setScene(postController.initPostCreateScene());
        // PostSuccess postSuccess = new PostSuccess(primaryStage, STYLESHEET_CASPIAN);
        // primaryStage.setScene(postSuccess.initPostSuccessScene());

        // //AboutUs aboutUs = new AboutUs(primaryStage);
        // //primaryStage.setScene(aboutUs.initAboutUsScene());
        // primaryStage.setTitle("Trotter it together");
        // primaryStage.show();
        
    }
}

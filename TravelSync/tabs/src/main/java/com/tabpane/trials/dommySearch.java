package com.tabpane.trials;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.firestore.Firestore;

public class dommySearch {

    private List<HBox> postList = new ArrayList<>();
    private VBox bottomVBox = new VBox(0);
    private Stage stg;
    private Scene scene;
    private VBox mainVBox;
    public static Firestore dbFirestore;

    public dommySearch(Stage stg) {
        this.stg = stg;
        initSearchScene();
    }

    private void initSearchScene() {
        //stg.setTitle("Post_Search");

        // Top horizontal box started:-
        TextField tf = new TextField();
        tf.setPromptText("Search...");
        tf.setStyle(" -fx-background-color: lightblue;");
        tf.setStyle("-fx-prompt-text-fill: gray;");

        Button bt = new Button("Search");
        bt.setStyle("-fx-background-color: Blue; -fx-text-fill: white;");

        Button btPost = new Button("Post");
        btPost.setStyle("-fx-background-color: Blue; -fx-text-fill: white;");

        HBox topHb = new HBox(10);
        topHb.setPadding(new Insets(10));
        topHb.getChildren().addAll(tf, bt);
        topHb.setStyle("-fx-background-color: Black;");

        // Top horizontal box ended

        // Bottom vertical boxes:-
        for (int i = 1; i <= 10; i++) {
            HBox bottomvBox = createContentHBox("2023-01-" + i, "Location " + i, String.valueOf(i * 10));
            postList.add(bottomvBox);
            bottomVBox.getChildren().add(bottomvBox);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(bottomVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: Yellow;");

        // for scrolling
        mainVBox = new VBox(10);
        mainVBox.setPadding(new Insets(10));
        mainVBox.getChildren().addAll(topHb, scrollPane);
        mainVBox.setStyle("-fx-background-color:Black;");

        // stage
        scene = new Scene(mainVBox, 500, 500, Color.BLACK);
        stg.setScene(scene);
        stg.show();

    }

    private HBox createContentHBox(String date, String place, String members) {
        HBox MainHBox = new HBox(10);
        MainHBox.setStyle(
                "-fx-background-color: Black; -fx-padding: 10; -fx-border-color: white; -fx-border-width: 1;");

        Image img = new Image("croco.jpg");
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        VBox labelsVBox = new VBox(5);
        Text dateLabel = new Text("Date: " + date);
        dateLabel.setStyle("-fx-font-size: 14; -fx-fill: White;");
        Text placeLabel = new Text("Place: " + place);
        placeLabel.setStyle("-fx-font-size: 14; -fx-fill: White");
        Text membersLabel = new Text("Members: " + members);
        membersLabel.setStyle("-fx-font-size: 14; -fx-fill: White");
        labelsVBox.getChildren().addAll(dateLabel, placeLabel, membersLabel);

        MainHBox.getChildren().addAll(imageView, labelsVBox);
        return MainHBox;
       
    }

    public VBox getRoot() {
        return mainVBox;
    }

    public Scene getSearchScene() {
        return scene;
    }

}
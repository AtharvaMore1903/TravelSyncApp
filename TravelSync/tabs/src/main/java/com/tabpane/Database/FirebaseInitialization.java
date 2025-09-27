package com.tabpane.Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.tabpane.controller.CreateAcc;
import com.tabpane.controller.LoginTrial2;
import com.tabpane.controller.PostController;
import com.tabpane.controller.ProfileUserTrial;
import com.tabpane.controller.SearchController2;
import com.tabpane.trials.dommySearch;

import java.io.FileInputStream;
import java.io.IOException;


public class FirebaseInitialization {
    private static Firestore dbFirestore;

    static{
        try{

            initializeFirebase();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void initializeFirebase() throws IOException {

        FileInputStream serviceAccount = new FileInputStream("tabs\\src\\main\\resources\\Jasuu.json");

        @SuppressWarnings("deprecation")
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)) // Set credentials from JSON file
                .setDatabaseUrl("https://trotter-together-default-rtdb.asia-southeast1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);
        dbFirestore = FirestoreClient.getFirestore();
        LoginTrial2.dbFirestore = dbFirestore;
        CreateAcc.dbFirestore = dbFirestore;
        PostController.dbFirestore = dbFirestore;
        ProfileUserTrial.dbFirestore = dbFirestore;
        SearchController2.dbFirestore = dbFirestore;
        
    }

    public static Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
}

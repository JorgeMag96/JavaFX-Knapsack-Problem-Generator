package com.launcher;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene (root, 720, 480);
        stage.setScene(scene);
        stage.setTitle("Homework 2 - Selected Topics on Optimization - Jorge A. Villarreal Magaña");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
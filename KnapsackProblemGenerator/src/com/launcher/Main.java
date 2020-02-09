package com.launcher;

import com.controller.Controller;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene (root, 720, 480);
        stage.setScene(scene);
        stage.setTitle("Homework 2 - Selected Topics on Optimization - Jorge A. Villarreal Magaña");
        stage.setResizable(false);
        Controller controller = (Controller)loader.getController();
        controller.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
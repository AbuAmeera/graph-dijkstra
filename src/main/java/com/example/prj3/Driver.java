package com.example.prj3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Driver extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("hello.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 722, 700);
        stage.setTitle("MAP");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) throws FileNotFoundException {

       launch();
    }
    }

package com.example.course_project_client;

import Connectors.Connector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 490, 453);
        stage.setTitle(" ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        Connector.socket = new Socket("localhost", 8089);
        Connector.oos = new ObjectOutputStream(Connector.socket.getOutputStream());
        Connector.ois = new ObjectInputStream(Connector.socket.getInputStream());
        launch();
    }
}
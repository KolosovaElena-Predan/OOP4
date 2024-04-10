/**Запускает приложение*/
package org.example.chatbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //вызов окна для задания имени пользователя
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 240, 238); //задаёт размер окна
        stage.setTitle("Chatbot"); //задаёт заголовок окна
        stage.setScene(scene);
        Image ico = new Image("https://cdn-icons-png.flaticon.com/512/2040/2040946.png"); //задаёт иконку приложения
        stage.getIcons().add(ico);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}
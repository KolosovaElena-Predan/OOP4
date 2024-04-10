package org.example.chatbot;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import java.io.IOException;


public class LoginWindow {
    final String color_error = "#ffc3bf";

    public TextField TextField_login;
    public Label Label_status;

    // Создаёт и показывает новое (основное) окно программы
    // вызывается по нажатию на кнопку
    public void new_window() throws IOException {

        // Загрузка окна из FXML такая же, как и в методе Start главного класса (MainApplication) программы
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 475,535);
        Stage stage = new Stage();
        Image ico = new Image("https://cdn-icons-png.flaticon.com/512/2040/2040946.png");
        stage.getIcons().add(ico);
        stage.setTitle("Chatbot");
        stage.setScene(scene);
        stage.show();

        // Обращение к классу с обработчиками
        MainWindow main_window = fxmlLoader.getController();
        main_window.setLogin( TextField_login.getText() );
        main_window.initialize();

        // обращение к окну (можно сделать через любой элемент интерфейса) чтобы скрыть его
        TextField_login.getScene().getWindow().hide();
    }

    // Обработчик события TextFiled - нажатие на кнопку
    // должен быть указан в fxml файле
    public void onTextField_login_key_pressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            new_window();
        }
    }
}

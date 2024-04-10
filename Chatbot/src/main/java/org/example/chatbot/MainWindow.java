/**Главное окно программы*/
package org.example.chatbot;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainWindow {

    /**Имя пользователя*/
    private String Login;

    /**Кнопка для отправки ссобщения*/
    @FXML
    private Button Button_ok;

    /**Текстовое поле для вывода истории сообщений*/
    @FXML
    private TextArea TextArea_answer;

    /**Текстовое поле для ввода сообщения*/
    @FXML
    private TextField TextFieled_messege;


    /**Задаёт значение имени пользователя*/
    public void setLogin(String login) {
        this.Login=login;
    }

    /**Возвращает значение имени пользователя*/
    public String getLogin() {
        return Login;
    }

    //для инициализации истории
    ArrayList<String> p = new ArrayList<String>();

    /**Объект класса Chatbot для вызова логических методов*/
    Chatbot new_user = new Chatbot(this.getLogin(), p);


    /**Обработчик события нажания на кнопку "Отправить"*/
    public void onButtonClick() throws IOException {
        String M=(String)TextFieled_messege.getText(); //считывается текст с поля ввода
        TextArea_answer.setText(new_user.Answer(M));   //выводится ответ в поле вывода
        TextFieled_messege.setText("");
    }

    /**Запускается при открытии окна.*/
    public void initialize(){
        new_user.set_UserName(Login);
        String FileName=this.getLogin()+".txt"; //имя файла
        File f = new File(FileName);
        if (f.exists()){//проверка на существование
            new_user.FillingFromFileHistory();
            TextArea_answer.setText(new_user.toString());
        }
    }


    //горячяя клавиша Enter, вызывает метод onButtonClick()
    public void onTextField_message_key_pressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            onButtonClick();
        }
    }

}
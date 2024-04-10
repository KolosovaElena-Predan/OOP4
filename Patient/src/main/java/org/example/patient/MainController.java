package org.example.patient;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.Year;

public class MainController implements Initializable {
    @FXML
    private TableView<Patient> table;   /** представление данных: будет показывать данные из ObservableList; */
    @FXML
    private TextField TF_change;

    @FXML
    private TextField TF_m;

    @FXML
    private TextField TF_n;

    @FXML
    private TextField TF_n1;

    @FXML
    private TextField category;

    @FXML
    private Button del;

    @FXML
    private Text error;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField patr;

    @FXML
    private TextField surname;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField year;

    // Будут отображать отдельные поля класса Student в ячейки соответствующих строк
    // <Student, String> - из какого класса брать данные, в каком типе представлять в TableView
    // см. связывание метода из Student и колонки, добавление колонки в таблицу ниже
    private TableColumn<Patient, Integer> col_id = new TableColumn<>("ID");
    private TableColumn<Patient, String> col_surname = new TableColumn<>("Surname");
    private TableColumn<Patient, String> col_name = new TableColumn<>("Name");;
    private TableColumn<Patient, String> col_patronymic = new TableColumn<>("Patronymic");;
    private TableColumn<Patient, Integer> col_yearOfBirth = new TableColumn<>("Year of birth");
    private TableColumn<Patient, Integer> col_categoryOfCitizens = new TableColumn<>("Category of citizens");



    private DataBase db = new DataBase();       /** содержит модель данных (ObservableList) */

    /** Вызывается после того как форма создана. Можно обращаться к её содержимому */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // связывает колонку и метод из Student, с помощью которого колонка будет получать значения для каждой ячейки данных
        // аргумента PropertyValueFactory должен быь таким, чтобы получить име геттера и сеттера добавив get и set соответственно
        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        col_patronymic.setCellValueFactory(new PropertyValueFactory<>("Patronymic"));
        col_yearOfBirth.setCellValueFactory(new PropertyValueFactory<>("YearOfBirth"));
        col_categoryOfCitizens.setCellValueFactory(new PropertyValueFactory<>("CategoryOfCitizens"));


        // добавление колонок в таблицу
        table.getColumns().add( col_id );
        table.getColumns().add( col_surname );
        table.getColumns().add( col_name );
        table.getColumns().add( col_patronymic );
        table.getColumns().add( col_yearOfBirth );
        table.getColumns().add( col_categoryOfCitizens );

        // связывание таблицы и модели данных
        table.setItems( db.getList_studs() );
    }



    @FXML
    protected void onHelloButtonClick() throws IOException {

        String s1 = id.getText();
        String s2 = surname.getText();
        String s3 = name.getText();
        String s4 = patr.getText();
        String s5 = year.getText();
        String s6 = category.getText();
        try {
            if (Integer.parseInt(s1) < 0|| s1.isEmpty()) throw new IllegalArgumentException("Must be greater than or equal to zero");
            else if (s2.isEmpty()) throw new IllegalArgumentException("Surname field cannot be empty");
            else if (s3.isEmpty()) throw new IllegalArgumentException("Name field cannot be empty");
            else if (Integer.parseInt(s5) < 1900 || Integer.parseInt(s5) > Year.now().getValue()||s5.isEmpty())
                throw new IllegalArgumentException("Incorrect year");
            else db.Add(Integer.parseInt(s1), s2, s3, s4, Integer.parseInt(s5), s6);
        }
        catch (IllegalArgumentException e){
            error.setText(e.getLocalizedMessage());
        }
    }

    /**  */
    @FXML
    protected void onDel() {
        try {
            db.Del(Integer.parseInt(TF_n.getText()));
        }
        catch (IllegalArgumentException e){ error.setText("There is no such row in the table");}
    }

    /**  */
    @FXML
    protected void onChange() {
        try {
            db.Change(Integer.parseInt(TF_n1.getText()), Integer.parseInt(TF_m.getText()), TF_change.getText());
        }
        catch (IllegalArgumentException e){
            error.setText(e.getLocalizedMessage());}
    }

}
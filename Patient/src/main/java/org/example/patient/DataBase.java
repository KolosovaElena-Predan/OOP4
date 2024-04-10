package org.example.patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Year;

public class DataBase {

    /**Переменная, хранящая базу данных пациент*/
    private ObservableList<Patient> list_patient = FXCollections.observableArrayList();
    // FXCollections.observableArrayList() -- метод, создающий экземпляр класса ObservableArrayList


    public void Add(Integer id, String surname, String name, String patronymic, Integer year, String category){
        Patient s = new Patient(id, surname, name, patronymic, year, category);
        list_patient.add(s);
    }

    public void Del(int number){
        if (number>=0&&number<list_patient.size())
            list_patient.remove(number);
        else throw new IllegalArgumentException("There is no row with this number");
    }

    public void ChangeID(Integer number, Integer new_value){
        Patient s = list_patient.get(number);
        s.setID(new_value);
        list_patient.set(number, s);
    }


    public void ChangeSurname(Integer number, String new_value){
        Patient s = list_patient.get(number);
        s.setSurname(new_value);
        list_patient.set(number, s);
    }

    public void ChangeName(Integer number, String new_value){
        Patient s = list_patient.get(number);
        s.setName(new_value);
        list_patient.set(number, s);
    }

    public void ChangePatronymic(Integer number, String new_value){
        Patient s = list_patient.get(number);
        s.setPatronymic(new_value);
        list_patient.set(number, s);
    }

    public void ChangeCategory(Integer number, String new_value){
        Patient s = list_patient.get(number);
        s.setCategoryOfCitizens(new_value);
        list_patient.set(number, s);
    }

    public void ChangeYear(Integer number, Integer new_value){
        if (new_value > 1900&&new_value < Year.now().getValue()){
            Patient s = list_patient.get(number);
            s.setYearOfBirth(new_value);
            list_patient.set(number, s);}
        else throw new IllegalArgumentException("Incorrect year");
    }

    public void Change(Integer num_row, Integer num_col, String new_value){

            if (num_row >= 0 && num_row < list_patient.size()) {
                if (num_col == 0) ChangeID(num_row, Integer.parseInt(new_value));
                else if (num_col == 1) ChangeSurname(num_row, new_value);
                else if (num_col == 2) ChangeName(num_row, new_value);
                else if (num_col == 3) ChangePatronymic(num_row, new_value);
                else if (num_col == 4) ChangeYear(num_row, Integer.parseInt(new_value));
                else if (num_col == 5) ChangeCategory(num_row, new_value);
                else throw new IllegalArgumentException("There is no column with this number");
            } else throw new IllegalArgumentException("There is no row with this number");
    }


    public void load(){
        // todo: загрузка из файла
    }

    public void save(){
        // todo: сохранение в файл
    }

    public ObservableList<Patient> getList_studs() {
        return list_patient;
    }
}

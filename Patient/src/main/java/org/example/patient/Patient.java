/** Хранит данные о пациенте*/
package org.example.patient;
import java.time.Year;

public class Patient {

    /**Поля класса*/

    private Integer ID;                     /**ID*/
    private String Surname;                 /**Фамилия*/
    private String Name;                    /**Имя*/
    private String Patronymic;              /** Отчество*/
    private Integer YearOfBirth;                /**Год рождения*/
    private String CategoryOfCitizens;      /**Категория граждан*/

    /**Конструктор без параметров*/
    public Patient() {
    }

    public Patient(Integer id, String surname, String name, String patronymic, Integer year, String category) {
        setID(id);
        setPatronymic(patronymic);
        setName(name);
        setSurname(surname);
        setYearOfBirth(year);
        setCategoryOfCitizens(category);
    }

    /**Возврашает значение поля Name*/
    public String getName() {
        return Name;
    }

    /**Задаёт значение поля Name*/
    public void setName(String name) {
        this.Name = name;
    }

    /**Возврашает значение поля Surname*/
    public String getSurname() {
        return Surname;
    }

    /**Задаёт значение поля Surname*/
    public void setSurname(String surname) {
        this.Surname = surname;
    }

    /**Возврашает значение поля Patronymic*/
    public String getPatronymic() {
        return Patronymic;
    }

    /**Задаёт значение поля Patronymic*/
    public void setPatronymic(String patronymic) {
        this.Patronymic = patronymic;
    }

    /**Возврашает значение поля ID*/
    public Integer getID() {
        return ID;
    }

    /**Задаёт значение поля ID*/
    public void setID(Integer id) {
        if (id >= 0)
            this.ID = id;
        else throw new IllegalArgumentException("Must be greater than or equal to zero");
    }

    /**Возврашает значение поля YearOfBirth*/
    public Integer getYearOfBirth() {
        return YearOfBirth;
    }

    /**Задаёт значение поля YearOfBirth*/
    public void setYearOfBirth(Integer year) {
        if (year > 1900||year < Year.now().getValue())
            this.YearOfBirth=year;
        else throw new IllegalArgumentException("Incorrect year");

    }

    /**Возврашает значение поля CategoryOfCitizens*/
    public String getCategoryOfCitizens() {
        return CategoryOfCitizens;
    }

    /**Задаёт значение поля CategoryOfCitizens*/
    public void setCategoryOfCitizens(String category) {
        this.CategoryOfCitizens = category;
    }



}
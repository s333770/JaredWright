package sample;

import javafx.beans.property.SimpleStringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

import java.time.LocalDate;

public class Person {
    // Når du bruker tabeller må du bruke SimpleStringProperty som gjør at du kan endre på stringene
    private SimpleStringProperty firstName,lastName;
    private LocalDate birthday; // Data uten timezone

    public Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName =new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
    }
    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName =new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName=new SimpleStringProperty(lastName);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}


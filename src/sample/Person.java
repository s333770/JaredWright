package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import sun.java2d.pipe.SpanShapeRenderer;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    // Når du bruker tabeller må du bruke SimpleStringProperty som gjør at du kan endre på stringene
    private SimpleStringProperty firstName,lastName;
    private LocalDate birthday; // Data uten timezone
    private Image photo; // Mulighet for å legge til foto

    public Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName =new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        photo=new Image("defaultImage.jpg");
    }

    public Person(String firstName, String lastName, LocalDate birthday, Image photo) {
        this.firstName =new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        this.photo=photo;
    }
    public Image getImage(){
        return photo;
    }

    public void setImage(Image newPicture){
        this.photo=newPicture;
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

    public int getAge(){
        return Period.between(birthday,LocalDate.now()).getYears();
    }

}


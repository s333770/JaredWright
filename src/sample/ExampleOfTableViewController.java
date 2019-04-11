package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class ExampleOfTableViewController implements Initializable {
    // Configure the table
    @FXML
    private TableView<Person> tableView;
    @FXML
    TableColumn<Person, String> firstNameColumn;
    @FXML
    TableColumn<Person, String> lastNameColumn;
    @FXML
    TableColumn<Person, LocalDate> birthdayColumn;

    //These instance variables are used to create new Person Objects
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private DatePicker birthdayPicker;

    // Vi skal nå gjøre det mulig å gjøre at man ikke kan klikke på detailed person view button uten å først ha
    //valgt en mann
    @FXML private Button detailedPersonViewButton;


    /*
    THis method will allow the user to double click on a Cell and update the first name of the Person
     */
    public void changeFirstNameCellEvent(TableColumn.CellEditEvent edittedCell){
        Person personSelected=tableView.getSelectionModel().getSelectedItem(); // Tar det elementet du trykker på
        personSelected.setFirstName(edittedCell.getNewValue().toString());
    }
    public void changeLastNameCellEvent(TableColumn.CellEditEvent edittedCell){
        Person personSelected=tableView.getSelectionModel().getSelectedItem(); // Tar det elementet du trykker på
        personSelected.setLastName(edittedCell.getNewValue().toString());
    }

    /*
    Her skal vi kunne enable button når person er valgt fra tabell:
     */
    public void userClickedOnTable(){
        this.detailedPersonViewButton.setDisable(false);
    }



    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent= FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    /*When this method is called it will pass the selected Person object to a detailed view*/
    public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/PersonView.fxml"));
        Parent tableViewParent=loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        PersonViewController controller=loader.getController(); // Får den andre kontrolleren
        controller.initData(tableView.getSelectionModel().getSelectedItem());

        //This line gets the Stage information
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Person,LocalDate>("birthday"));

        //Load dummy data
        tableView.setItems(getPeople());

        //Set columns so that the first and last name can be edited
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //THis will allow the table to select multiple rows at once
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Disable the detailed Person View button and the row selected
        this.detailedPersonViewButton.setDisable(true);
    }
    /*
    This method will remove the selected row(s) from the Table
     */
    public void deleteButtonPushed(){
        ObservableList<Person> selectedRows,allPeople;
        allPeople=tableView.getItems();
        //THis gives us the rows that were selected
        selectedRows= tableView.getSelectionModel().getSelectedItems();
        for (Person person: selectedRows){
            allPeople.remove(person);
        }


    }

    /*
    This method will create a new Person and add it to the table
     */
    public void newPersonButtonPushed(){
        Person newPerson=new Person(firstNameTextField.getText(),lastNameTextField.getText(),birthdayPicker.getValue());

        //Få alle verdiene fra tabellen som en liste, så kan vi legge til Person
        tableView.getItems().add(newPerson);
    }

    //This method will return an Observable List of Peolpe objects
    public ObservableList<Person> getPeople(){
        ObservableList<Person> people= FXCollections.observableArrayList();
        people.add(new Person("Frank","Sinatra", LocalDate.of(1915, Month.DECEMBER,12),new Image("FrankSinatra.jpg")));
        people.add(new Person("Rebecca","Fergusson", LocalDate.of(1986, Month.JULY,21)));
        people.add(new Person("Mr.","T", LocalDate.of(1952, Month.MAY,21)));

        return people;
    }
}

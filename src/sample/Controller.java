package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //These items are for the COmbobox example
    @FXML private ComboBox comboBox;
    @FXML private Label comboBoxLabel;


    //These itemes were for the checkbox
    @FXML private Label pizzaOrderLabel;
    @FXML private CheckBox pepperoniCheckBox;
    @FXML private CheckBox pineappleCheckBox;
    @FXML private CheckBox baconCheckBox;

    //These items is for the ChoiceBoxExample
    @FXML private ChoiceBox choiceBox;
    @FXML private Label choiceBoxLabel;

    //These items are for the radioButton
    @FXML private RadioButton phpRadioButton;
    @FXML private RadioButton javaRadioButton;
    @FXML private RadioButton cSharpRadioButton;
    @FXML private RadioButton cPlusPlusRadioButton;
    @FXML private Label radioButtonLabel;

    private ToggleGroup favLangToggleGroup; // ToggleGroup

    //These items are for the listView and TextArea example
    @FXML private ListView listView;
    @FXML private TextArea golfTextArea;

    /*
    When this button is pushed it will change the scene
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent= FXMLLoader.load(getClass().getResource("/sample/ExampleOfTableViewController.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void choiceBoxButtonPushed(){
        choiceBoxLabel.setText("My favourite fruit is: \n"+choiceBox.getValue().toString());
    }


    public void pizzaOrderButtonPushed(){
        String order="Toppings are: ";
        if (pineappleCheckBox.isSelected()){
            order+="\n pineapple";
        }
        if (pepperoniCheckBox.isSelected()){
            order+="\n pepperoni";
        }
        if (baconCheckBox.isSelected()){
            order+="\n bacon";
        }
        this.pizzaOrderLabel.setText(order);
    }

    /*
    Denne metoden skal oppdatere label til radiobutton når en av de er trykket ned
     */
    public void radioButtonChanged(){
        if(this.favLangToggleGroup.getSelectedToggle().equals(this.cPlusPlusRadioButton)){
            radioButtonLabel.setText("The selected item is C++");
        }
        if(this.favLangToggleGroup.getSelectedToggle().equals(this.cSharpRadioButton)){
            radioButtonLabel.setText("The selected item is C#");
        }
        if(this.favLangToggleGroup.getSelectedToggle().equals(this.javaRadioButton)){
            radioButtonLabel.setText("The selected item is Java");
        }
        if(this.favLangToggleGroup.getSelectedToggle().equals(this.phpRadioButton)){
            radioButtonLabel.setText("The selected item is PHP");
        }
    }

    //This will update the ComboBoxLabel when the combobox is changed

    public void comboBoxWasUpdated(){
        this.comboBoxLabel.setText("Course selected: \n"+comboBox.getValue().toString());
    }

    /*
    THis method will copy the strings from ListView and put them to the Text area
     */
    public void listViewButtonPushed(){
        String textAreaString="";
        ObservableList listOfItems=listView.getSelectionModel().getSelectedItems();//Ganske lik arrayList
        for(Object item:listOfItems){
            textAreaString+=String.format("%s%n",(String)item); // s betyr String og n betyr new line
        }
        this.golfTextArea.setText(textAreaString);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pizzaOrderLabel.setText("");

        //THis items are for configuring the choicebox example
        choiceBoxLabel.setText("");
        choiceBox.getItems().add("apples");
        choiceBox.getItems().add("bananas");
        choiceBox.getItems().addAll("Oranges","pears","Strawberries");
        choiceBox.setValue("apples");

        //These items are for configuring the ComboBox
        comboBox.getItems().add("COMP1030");
        comboBox.getItems().addAll("COMP1008", "MGMT2003","MGMT2010");
        comboBoxLabel.setText("");

        //THese are the items for Radiobutton
        radioButtonLabel.setText("");
        favLangToggleGroup=new ToggleGroup(); // Gjør at vi kan velge hvem som er togglet
        this.cPlusPlusRadioButton.setToggleGroup(favLangToggleGroup);
        this.cSharpRadioButton.setToggleGroup(favLangToggleGroup);
        this.phpRadioButton.setToggleGroup(favLangToggleGroup);
        this.javaRadioButton.setToggleGroup(favLangToggleGroup);

        //THese items are for configuring the ListArea
        listView.getItems().addAll("Golf balls", "Wedges", "Irons","Tees", "Driver", "Putter");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);// Gjør at vi kan velge flere



    }
}

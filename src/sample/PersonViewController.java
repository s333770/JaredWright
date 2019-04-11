package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonViewController implements Initializable {

    private Person selectedPerson;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label ageLabel;

    @FXML private ImageView photo;

    private FileChooser fileChooser; // Filechooser
    private File filePath;

    /*
    THis method will the user to change the Image on the screen
     */
    public void chooseImageButtonPushed(ActionEvent event){
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        fileChooser=new FileChooser();
        fileChooser.setTitle("Open Image");

        //Set to users directory or go to the default C drive if cannot access
        String userDirectoryString=System.getProperty("user.home")+"\\Pictures";
        File userDirectory=new File(userDirectoryString);
        if(!userDirectory.canRead()){
            userDirectory=new File("c:/");
        }
        else{
            this.filePath=fileChooser.showOpenDialog(stage);
        }
        this.filePath=fileChooser.showOpenDialog(stage);

        //Try to update the image by loading the new Image
        try{
            BufferedImage bufferedImage= ImageIO.read(filePath);
            Image image= SwingFXUtils.toFXImage(bufferedImage,null);
            selectedPerson.setImage(image);
            this.photo.setImage(selectedPerson.getImage());
        }
        catch(IOException e){
            System.err.println(e.getMessage());

        }
    }

    /*
    THis method accepts a person to initialize the view
     */
    public void initData(Person person){
        selectedPerson=person;
        firstNameLabel.setText(selectedPerson.getFirstName());
        lastNameLabel.setText(selectedPerson.getFirstName());
        birthdayLabel.setText(selectedPerson.getBirthday().toString());
        ageLabel.setText(Integer.toString(selectedPerson.getAge()));
        photo.setImage(selectedPerson.getImage());
    }
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent= FXMLLoader.load(getClass().getResource("/sample/ExampleOfTableViewController.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

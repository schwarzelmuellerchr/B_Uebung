package controllerView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;
import model.PersonException;
import serial.Database;

import java.io.IOException;

public class PersonC {
    public static void show(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(PersonC.class.getResource("PersonList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personlist");
            primaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
            Platform.exit();
        }

    }
    public static void showInputView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PersonC.class.getResource("PersonInput.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("PersonInput");
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
            Platform.exit();
        }
    }

    @FXML
    private Button btEinfuegen;

    @FXML
    private Button btRefresh;

    @FXML
    private ListView<Person> lvPersonlist;

    @FXML
    private Button btOK;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfWohnort;

    Person person;

    @FXML
    void einfuegen(ActionEvent event) {
        showInputView();
    }
    @FXML
    void save(ActionEvent event) {
        try {
            if(tfID.getText()==null){
                throw new PersonException("Ungültige Eingabe!");
            }
            person = new Person(Integer.parseInt(tfID.getText()), tfName.getText(), tfWohnort.getText());
            person.save();
            info("Gespeichert!");
        }catch(PersonException e){
            error(e.getMessage());
        }
    }

    private void info(String s) {
        Alert info = new Alert(Alert.AlertType.INFORMATION,s);
        info.showAndWait();
    }

    @FXML
    void refresh(ActionEvent event) {
        Database.getInstance().refresh();
        lvPersonlist.setItems(Database.getInstance().getObservablePerson());

    }

    private void error(String message) {
        Alert error = new Alert(Alert.AlertType.ERROR,message);
        error.showAndWait();
    }
}

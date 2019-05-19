package controllers.UserController;

import Utils.Constants;
import controllers.MainViewController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSideMenuController implements Initializable {

    @FXML
    private void reserver(ActionEvent event) {
        switchPane(Constants.RESERVERVIEW);
    }
    @FXML
    private void consulterReservation(ActionEvent event){
        switchPane(Constants.CONSULTERRESERVATIONVIEW);
    }
    @FXML
    private void annulerReservation(ActionEvent event){
        switchPane(Constants.ANNULERRESERVATIONVIEW);
    }
    @FXML
    private void modifierReservation(ActionEvent event){
        switchPane(Constants.MODIFERRESERVATIONVIEW);
    }
    @FXML
    private void logOut(ActionEvent event){

    }
    @FXML
    private void exit(ActionEvent event){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void switchPane(String pane){
        //Clearing the temporaryPane
        try {
            MainViewController.temporaryPane.getChildren().clear();
            StackPane pane2 = FXMLLoader.load(getClass().getResource(pane));
            ObservableList<Node> elements = pane2.getChildren();
            MainViewController.temporaryPane.getChildren().setAll(elements);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

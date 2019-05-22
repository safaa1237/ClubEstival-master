package controllers.UserController;

import Utils.Constants;
import com.jfoenix.controls.JFXButton;
import controllers.MainViewControlleruser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSideMenuController implements Initializable {

    @FXML
    private JFXButton sgn ;
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
    private void logOut(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("/views/accueil.fxml"));

        Stage stage = (Stage) sgn.getScene().getWindow();
        // do what you have to do
        stage.close();

        Scene login = new Scene(pane,1000,800);
        Stage primaryStage = new  Stage();
        primaryStage.setScene(login);

        primaryStage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void switchPane(String pane){
        //Clearing the temporaryPane
        try {
            MainViewControlleruser.temporaryPane.getChildren().clear();
            StackPane pane2 = FXMLLoader.load(getClass().getResource(pane));
            ObservableList<Node> elements = pane2.getChildren();
            MainViewControlleruser.temporaryPane.getChildren().setAll(elements);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

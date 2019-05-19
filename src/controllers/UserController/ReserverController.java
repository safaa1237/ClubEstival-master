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
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.*;
import javafx.event.ActionEvent;

import javafx.scene.layout.Pane;

import java.util.ResourceBundle;


public class ReserverController implements Initializable {
        @FXML
        private JFXButton btn_m , btn_a;
        @FXML
        private Pane heb , main;

        @FXML
        private void handleButtonAction(ActionEvent e) {
            if (e.getSource() == btn_a) {
                main.toFront();
            } else {

                if (e.getSource() == btn_a)
                    heb.toFront();

            }
        }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}

package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Accueil implements Initializable {
    @FXML
    private JFXButton btn_m , btn_a;
    @FXML
    private Pane heb , main;

    @FXML
    private void btn1(ActionEvent e) {

        main.toFront();
    }
    @FXML
    private void btn2 (ActionEvent e) {
        heb.toFront();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

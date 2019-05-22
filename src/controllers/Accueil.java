package controllers;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Accueil implements Initializable {

    public int ReservationPrix = 0;
    public int RestaurationPrix =0;
    int nbr;
    public double total;

    public JFXComboBox TF_TypeRestauration;
    public JFXComboBox TF_TypeHebergement;
    public JFXComboBox TF_NbPersonnes;
    public JFXButton simuler;
    @FXML
    private JFXButton sgn , btnM ,btnR, btnH ,sgn1;
    @FXML
    private AnchorPane root;
    @FXML
    private Pane heb , main, lp , lpl , resto;

    @FXML
    private void btn1(ActionEvent e) {

        heb.toFront();
    }
    @FXML
    private void btn2 (ActionEvent e) {
        resto.toFront();

    }

    @FXML
    private void btn3 (ActionEvent e) {
        main.toFront();

    }



    @FXML
    private void more(ActionEvent e) {
        if(sgn1.getText().equals("more")) {
            lp.toFront();
            sgn1.setText("less");
            sgn1.toFront();
        }
        else{
            lpl.toFront();
            sgn1.setText("more");
            sgn1.toFront();
        }

    }

    @FXML
    private void LoadLogin(ActionEvent e) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("/views/login/login.fxml"));

        Stage stage = (Stage) sgn.getScene().getWindow();
        // do what you have to do
        stage.close();

        Scene login = new Scene(pane,800,500);
        Stage primaryStage = new  Stage();
        primaryStage.setScene(login);

        primaryStage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TF_NbPersonnes.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                nbr=Integer.parseInt(t1);
                total=(ReservationPrix+RestaurationPrix)*nbr;

            }
        });
        TF_TypeHebergement.valueProperty().addListener(new ChangeListener<String>() {

            @Override public void changed(ObservableValue ov, String t, String t1) {
                if(t1.equals("Bungalow")) {
                    ReservationPrix=400;
                    total = (ReservationPrix+RestaurationPrix)*nbr;

                }
                else if(t1.equals("Appartement")) {
                    ReservationPrix = 300;
                    total = (ReservationPrix + RestaurationPrix)*nbr ;

                }
                else if(t1.equals("Caravane")){
                    ReservationPrix = 350;
                    total = (ReservationPrix + RestaurationPrix)*nbr ;

                }
            }
        });
        TF_TypeRestauration.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if(t1.equals("Demi Pension")) {
                    RestaurationPrix=50;
                    total = (ReservationPrix+RestaurationPrix)*nbr ;

                }
                else if(t1.equals("Pension Complete")) {
                    RestaurationPrix = 100;
                    total = (ReservationPrix + RestaurationPrix)*nbr ;

                }
                else if(t1.equals("Restauration Personnelle")){
                    RestaurationPrix = 70;
                    total = (ReservationPrix + RestaurationPrix)*nbr ;

                }
            }
        });
        new BounceInDown(main).setSpeed(0.3).play();



    }

    public void simulerprix(ActionEvent actionEvent) {
        new FadeIn(simuler).setSpeed(0.4).play();
        simuler.setText((total)+" Dh");
    }
}

package controllers;

import Utils.Constants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXToolbar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewControlleruser implements Initializable {
    @FXML
    private JFXButton sgn ;
    @FXML
    private StackPane maninStackPane;
    @FXML
    private JFXToolbar toolbar;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private JFXDrawer drawer;

    public static AnchorPane temporaryPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temporaryPane = contentPane;
        initDrawer();
    }

    public void initDrawer(  ){
        try {
            //test of the user Menu

            VBox menu = FXMLLoader.load(getClass().getResource(Constants.SIDEMENUUSER));

            MainViewController.initDrawer(menu, drawer, hamburger);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

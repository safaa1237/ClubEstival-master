package controllers;

import Connectivity.ConnectionClass;
import Models.ModelTable;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ListerClientRestaurationController implements Initializable {
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private JFXComboBox TF_restauration;
    @FXML
    private TableColumn <ModelTable,String> col_nom;
    @FXML
    private TableColumn <ModelTable,String> col_prenom;
    @FXML
    private TableColumn <ModelTable,String> col_cin;
    @FXML
    private TableColumn <ModelTable,String> col_email;
    @FXML
    private TableColumn <ModelTable,String> col_login;
    @FXML
    private TableColumn <ModelTable,String> col_rest;
    @FXML
    private TableColumn <ModelTable,String> col_nbr;
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql ="SELECT firstname,lastname,cin,email,login,type,nbr_personne from client,personnes,hebergement,restauration,reservation WHERE client.id=personnes.id AND client.id_reservation = reservation.id AND reservation.id_hebergement = hebergement.id AND reservation.id_restauration = restauration.id AND type_restauration=?";
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_rest.setCellValueFactory(new PropertyValueFactory<>("rest"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbr"));

        TF_restauration.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                table.getItems().clear();
                ResultSet rs = null;
                ResultSetMetaData rsmd =null;
                Statement st = null;
                PreparedStatement pst = null;
                try {
                    pst=connection.prepareStatement(sql);
                    pst.setString(1,t1);
                    rs= pst.executeQuery();
                    rsmd=rs.getMetaData();
                    while (rs.next()){
                        oblist.add(new ModelTable(rs.getString("firstname"),rs.getString("lastname"),rs.getString("cin"),rs.getString("email"),rs.getString("login"),rs.getString("type"),rs.getInt("nbr_personne")));
                    }
                    table.setItems(oblist);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

    }
}

package controllers.UserController;

import Connectivity.ConnectionClass;
import Utils.DatabaseUtilis;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ModifierReservationController implements Initializable {
    public JFXComboBox TF_TypeHebergement;
    public JFXComboBox TF_TypeRestauration;
    public JFXComboBox TF_NbPersonnes;

    public DatePicker DP_DateArrivee;
    public DatePicker DP_DateDepart;
    public int id_hebergement=0,id_restauration=0,id_reservation=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PreparedStatement pst = null;
        ResultSet rs= null;
        DatabaseUtilis connectionClass = new DatabaseUtilis();
        Connection connection = connectionClass.getConn();
        String sql = "select nbr_personne,type_restauration,type,date_arrive,date_depart,id_hebergement,id_restauration,id_reservation from reservation,hebergement,restauration WHERE reservation.id_hebergement=hebergement.id AND reservation.id_restauration=restauration.id AND id_client =?";
        try{
            pst=connection.prepareStatement(sql);
            pst.setInt(1,24);
            rs=pst.executeQuery();
            if(rs.next()){
                TF_NbPersonnes.setPromptText(Integer.toString(rs.getInt(1)));
                TF_TypeRestauration.setPromptText(rs.getString(2));
                TF_TypeHebergement.setPromptText(rs.getString(3));
                DP_DateArrivee.setPromptText(rs.getString(4));
                DP_DateDepart.setPromptText(rs.getString(5));
                id_hebergement= rs.getInt(6);
                id_restauration= rs.getInt(7);
                id_reservation= rs.getInt(8);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void modifier(ActionEvent actionEvent){
        PreparedStatement pst1 =null;
        PreparedStatement pst2 =null;
        PreparedStatement pst3 = null;
        ResultSet rs1= null;
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql1 = "Update hebergement set nbr_personne=?, type=? where id=? ";
        String sql2 = "Update restauration set type_restauration=? where id =?";
        String sql3 = "Update reservation set date_arrive=?,date_depart=? where id=?";
        try {
            pst1= connection.prepareStatement(sql1);
            pst1.setInt(1,(int)TF_NbPersonnes.getValue());
            pst1.setString(2,TF_TypeHebergement.getValue().toString());
            pst1.setInt(3,id_hebergement);
            pst1.executeUpdate();
            pst2.setString(1,TF_TypeRestauration.getValue().toString());
            pst2.setInt(2,id_restauration);
            pst2.executeUpdate();
            pst3.setString(1,DP_DateArrivee.getValue().toString());
            pst3.setString(2,DP_DateDepart.getValue().toString());
            pst3.setInt(3,id_reservation);
            pst3.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void annuler(ActionEvent event){
        PreparedStatement pst = null;
        ResultSet rs= null;
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "Drop from reservation where id=? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,id_reservation);
            pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

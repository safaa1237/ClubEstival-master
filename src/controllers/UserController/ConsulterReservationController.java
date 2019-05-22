package controllers.UserController;

import Connectivity.ConnectionClass;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ConsulterReservationController implements Initializable {
    public JFXComboBox TF_TypeHebergement;
    public JFXComboBox TF_TypeRestauration;
    public JFXComboBox TF_NbPersonnes;

    public DatePicker DP_DateArrivee;
    public DatePicker DP_DateDepart;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PreparedStatement pst = null;
        ResultSet rs= null;
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
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
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

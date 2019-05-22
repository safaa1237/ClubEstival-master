package controllers.UserController;


import Connectivity.ConnectionClass;
import animatefx.animation.BounceInDown;
import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.time.Period;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.sql.Date;


public class ReserverController {
    public String ReservationType = "";
    public String RestaurationType = "";
    public int ReservationPrix = 0;
    public int RestaurationPrix =0;
    public Label LB_Etape1;

    public AnchorPane AP_Appartement;
    public AnchorPane AP_Bongalow;
    public AnchorPane AP_Caravane;
    public AnchorPane AP_FormulaireReservation;

    public ImageView Img1 = new ImageView();
    public ImageView Img2 = new ImageView();
    public ImageView Img3 = new ImageView();

    public Button BTN_Appartement;
    public Button BTN_Bongalow;
    public Button BTN_Caravane;
    public Button BTN_SemiPension;
    public Button BTN_PensionComplete;
    public Button BTN_RestaurationPersonnelle;

    public JFXComboBox TF_TypeHebergement;
    public JFXComboBox TF_TypeRestauration;
    public JFXComboBox TF_NbPersonnes;

    public DatePicker DP_DateArrivee;
    public DatePicker DP_DateDepart;
    public Label LB_Etape4;
    public Label LB_Etape3;
    public Label LB_Etape2;
    public Label LB_total;
    public JFXComboBox TF_TypePaiement;
    public double total;
    public double taux=1;
    public Label LB_Paye;
    public int nbr;
    public String username;

    public void initialize() {



        TF_TypePaiement.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if(t1.equals("20% du Prix")) {
                    taux = 0.2;
                    LB_Paye.setText(Double.toString(total * taux));
                }
                else {
                    taux=1;
                    LB_Paye.setText(Double.toString(total * taux));
                }

            }
        });
        TF_NbPersonnes.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                nbr=Integer.parseInt(t1);
                total=(ReservationPrix+RestaurationPrix)*nbr;
                LB_Paye.setText(Double.toString(total*taux));
            }
        });
        TF_TypeHebergement.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if(t1.equals("Bungalow")) {
                    ReservationPrix=400;
                    total = (ReservationPrix+RestaurationPrix)*nbr;
                    LB_Paye.setText(Double.toString(total*taux));
                }
                else if(t1.equals("Appartement")) {
                    ReservationPrix = 300;
                    total = (ReservationPrix + RestaurationPrix)*nbr ;
                    LB_Paye.setText(Double.toString(total*taux));
                }
                else if(t1.equals("Caravane")){
                    ReservationPrix = 350;
                    total = (ReservationPrix + RestaurationPrix)*nbr ;
                    LB_Paye.setText(Double.toString(total*taux));
                }
            }
        });
        TF_TypeRestauration.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if(t1.equals("Demi Pension")) {
                    RestaurationPrix=50;
                    total = (ReservationPrix+RestaurationPrix)*nbr ;
                    LB_Paye.setText(Double.toString(total*taux));
                }
                else if(t1.equals("Pension Complete")) {
                    RestaurationPrix = 100;
                    total = (ReservationPrix + RestaurationPrix)*nbr ;
                    LB_Paye.setText(Double.toString(total*taux));
                }
                else if(t1.equals("Restauration Personnelle")){
                    RestaurationPrix = 70;
                    total = (ReservationPrix + RestaurationPrix)*nbr ;
                    LB_Paye.setText(Double.toString(total*taux));
                }
            }
        });

    }
    public void BTN_Appartement_Clicked(ActionEvent actionEvent) {
        ReservationType = "Appartement";
        ReservationPrix = 300;
        etape2();
    }

    public void BTN_Bongalow_Clicked(ActionEvent actionEvent) {
        ReservationType = "Bungalow";
        ReservationPrix = 400;
        etape2();
    }
    public void BTN_Caravane_Clicked(ActionEvent actionEvent) {
        ReservationType = "Caravane";
        ReservationPrix = 350;
        etape2();

    }
    public void BTN_SemiPension_Clicked(ActionEvent actionEvent) {
        RestaurationType="Demi Pension";
        RestaurationPrix = 50;
        etape3();
    }

    public void BTN_PensionComplete_Clicked(ActionEvent actionEvent) {
        RestaurationType="Pension Complete";
        RestaurationPrix = 100;
        etape3();
    }
    public void BTN_RestaurationPersonnelle_Clicked(ActionEvent actionEvent) {
        RestaurationType="Restauration Personnelle";
        RestaurationPrix = 70;
        etape3();
    }

    public void etape3() {
        LB_Etape3.setTextFill(Color.web("#c49018"));
        LB_Etape2.setTextFill(Color.web("#ffffff"));
        new FadeIn(LB_total).play();
        LB_total.setText(Integer.toString(ReservationPrix+RestaurationPrix));
        AP_Caravane.setVisible(false);
        AP_Appartement.setVisible(false);
        AP_Bongalow.setVisible(false);
        new BounceInDown(AP_FormulaireReservation).play();
        AP_FormulaireReservation.setVisible(true);

        TF_TypeRestauration.setValue(RestaurationType);
        TF_TypeHebergement.setValue(ReservationType);
    }
    public void etape2() {
        LB_Etape2.setTextFill(Color.web("#c49018"));
        LB_Etape1.setTextFill(Color.web("#ffffff"));
        new FadeIn(LB_total).play();
        LB_total.setText(Integer.toString(ReservationPrix));
        Image image = new Image("file:src/images/petit_dejeuner.jpg");
        Img1.setImage(image);
        image = new Image("file:src/images/repas.png");
        Img2.setImage(image);
        image = new Image("file:src/images/roomservice.jpg");
        Img3.setImage(image);
        BTN_Appartement.setVisible(false);
        BTN_Bongalow.setVisible(false);
        BTN_Caravane.setVisible(false);
        BTN_SemiPension.setVisible(true);
        BTN_PensionComplete.setVisible(true);
        BTN_RestaurationPersonnelle.setVisible(true);
        new Pulse(AP_Appartement).play();
        new Pulse(AP_Caravane).play();
        new Pulse(AP_Bongalow).play();
        new Pulse(BTN_PensionComplete).play();
        new Pulse(BTN_SemiPension).play();
        new Pulse(BTN_RestaurationPersonnelle).play();
    }


    public void ChangerTypeHebergement(ActionEvent actionEvent) {
        ReservationType = TF_TypeHebergement.toString();
    }

    public void ChangerTypeRestauration(ActionEvent actionEvent) {
        RestaurationType = TF_TypeRestauration.toString();
    }

    public void BTN_Enregistrer(ActionEvent actionEvent) {
        PreparedStatement pst =null;
        PreparedStatement pst1 =null;
        PreparedStatement pst2 =null;
        Statement st= null;
        Statement st1=null;
        ResultSet rs=null;
        ResultSet rs1 = null;
        int id_hebergement= 0;
        int id_restauration=0;
        ResultSetMetaData rsmd=null;
        boolean trouve = false;
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "insert into hebergement (localisation,nbr_personne,type) Values (?,?,?)";
        String sql1 = "insert into restauration (type_restauration,prix) Values (?,?)";
        String sql2 = "select Max(id) from hebergement";
        String sql3= "select Max(id) from restauration";
        String sql4 ="insert into reservation (durree_sejour,date_arrive,date_depart,id_hebergement,id_restauration,simule_paiement,regle_paiement,montant_total,Ref_reservation,id_client) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst1 = connection.prepareStatement(sql1);
            st=connection.createStatement();
            st1=connection.createStatement();
            pst2=connection.prepareStatement(sql4);
            pst.setString(1,"Batiment F");
            pst.setInt(2,nbr);
            pst.setString(3,ReservationType);
            pst.executeUpdate();
            pst1.setString(1,RestaurationType);
            pst1.setDouble(2,RestaurationPrix*nbr);
            pst1.executeUpdate();
            rs=st.executeQuery(sql2);
            if (rs.next()){
                id_hebergement=rs.getInt(1);
            }
            rs1=st1.executeQuery(sql3);
            if (rs1.next()){
                id_restauration=rs1.getInt(1);
            }
            LocalDate date_arrivee = DP_DateArrivee.getValue();
            LocalDate date_depart = DP_DateDepart.getValue();
            double duree_sejour = ChronoUnit.DAYS.between(date_arrivee,date_depart)/7;
            int simule_payment=0;
            int regler_solde=0;
            if (taux == 0.2){
                simule_payment = 1;
                regler_solde= 0;
            }
            else {
                simule_payment = 0;
                regler_solde= 1;
            }
            int i =150;
            float duree_sejouur=(float)duree_sejour;
            pst2.setFloat(1,duree_sejouur);
            pst2.setString(2,date_arrivee.toString());
            pst2.setString(3,date_depart.toString());
            pst2.setInt(4,id_hebergement);
            pst2.setInt(5,id_restauration);
            pst2.setInt(6,simule_payment);
            pst2.setInt(7,regler_solde);
            pst2.setFloat(8,Float.parseFloat(LB_Paye.getText()));
            pst2.setString(9,ReservationType+"-"+i);
            i++;
            pst2.setInt(10,loginController.idClient);//id statique
            pst2.executeUpdate();
            System.out.println(duree_sejour);
            System.out.println(date_arrivee);
            System.out.println(date_depart);
            System.out.println(id_hebergement);
            System.out.println(id_restauration);
            System.out.println(simule_payment);
            System.out.println(regler_solde);
            System.out.println(LB_total.getText());
            System.out.println(ReservationType+i);

            String reciever = "";
            int idclient = loginController.idClient;
            GenererFactureController test = new GenererFactureController();
            test.genererLaFacture(idclient);
            Utils.DatabaseUtilis.connection();
            String sqlx ="SELECT email FROM client WHERE id = '"+idclient+"'";
            Statement statement = Utils.DatabaseUtilis.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlx);
            while(resultSet.next()){
                reciever = resultSet.getString(1);
            }


            Email email = new Email();
            email.sendEmail(reciever);


        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public void BTN_Réinitialiser(ActionEvent actionEvent) {

    }

}

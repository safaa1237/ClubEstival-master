package controllers;

import Utils.DatabaseUtilis;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EtatReservationController implements Initializable {


    @FXML
    public VBox infoEtatRervation;

    @FXML
    public Label nameText;

    @FXML
    public ImageView imageEtatReservation;

    @FXML
    public ComboBox aPayerComboBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        aPayerComboBox.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
            ArrayList<GridPane> listPane = null;
            if(t1.equals("A payer")){
                infoEtatRervation.getChildren().clear();
                listPane = getPaneReservationPaye();
                for (int i = 0; i < listPane.size(); i++) {
                    infoEtatRervation.getChildren().add(listPane.get(i));
                }

            }

            if(t1.equals("N'a pas payer")) {
                infoEtatRervation.getChildren().clear();
                listPane = getPaneReservationNonPaye();
                for (int i = 0; i < listPane.size(); i++) {
                    infoEtatRervation.getChildren().add(listPane.get(i));
                }
            }

            System.out.println(listPane);

        });
        Utils.DatabaseUtilis.connection();
        String request = "Select id from client where id_reservation IS NOT NULL";
        try {
            Statement stm = DatabaseUtilis.getConn().createStatement();
            ResultSet rs = stm.executeQuery(request);
            if(!rs.isBeforeFirst()){
                infoEtatRervation.getChildren().clear();
                infoEtatRervation.getChildren().add(createPaneErrorNoData());
            }else{
                ArrayList<GridPane> paneList = new ArrayList();

                while(rs.next()){
                    int idReservation = rs.getInt("id");
                    String nomComplet=Utils.DatabaseUtilis.getNomComplet(idReservation);
//                    request = "Select id from reservation where Ref_reservation='"+idReservation+"'";
//                    stm = DatabaseUtilis.getConn().createStatement();
//                    rs = stm.executeQuery(request);
//                    while(rs.next()){
//                        int id = rs.getInt("id");
                    boolean aPayer = Utils.DatabaseUtilis.getReservationState(idReservation);
                    float montant = Utils.DatabaseUtilis.getMontantApayer(idReservation);
                    System.out.println(montant);
                    String typeHebergement = Utils.DatabaseUtilis.getTypeHebergement(idReservation);
                    GridPane gridPane = createPane(nomComplet,montant,aPayer,typeHebergement);
                    paneList.add(gridPane);
//                    }


                }
                for (int i = 0 ; i < paneList.size() ; i++){
                    infoEtatRervation.getChildren().addAll(paneList.get(i));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Utils.DatabaseUtilis.closeConnection();
    }


    private ArrayList<GridPane> getPaneReservationNonPaye(){
        Utils.DatabaseUtilis.connection();

        try {
            String request = "Select id from client where id_reservation IS NOT NULL";
            Statement stm = DatabaseUtilis.getConn().createStatement();
            ResultSet rs = stm.executeQuery(request);
            if(!rs.isBeforeFirst()){
                System.out.println("No data to show");
            }else{
                ArrayList<GridPane> listPanesReservatioNonpaye = new ArrayList<>();

                while(rs.next()){
                    int idReservation = rs.getInt("id");
                    String nomComplet=Utils.DatabaseUtilis.getNomComplet(idReservation);

                    boolean aPayer = Utils.DatabaseUtilis.getReservationState(idReservation);
                    float montant = Utils.DatabaseUtilis.getMontantApayer(idReservation);
                    System.out.println(montant);
                    String typeHebergement = Utils.DatabaseUtilis.getTypeHebergement(idReservation);
                    GridPane gridPane = createPane(nomComplet,montant,aPayer,typeHebergement);
                    if(aPayer == false) {
                        listPanesReservatioNonpaye.add(gridPane);
                    }
//                    }


                }
                return listPanesReservatioNonpaye;


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Utils.DatabaseUtilis.closeConnection();
        return null;
    }
    private ArrayList<GridPane> getPaneReservationPaye(){
        Utils.DatabaseUtilis.connection();

        try {
            String request = "Select id from client where id_reservation IS NOT NULL";
            Statement stm = DatabaseUtilis.getConn().createStatement();
            ResultSet rs = stm.executeQuery(request);
            if(!rs.isBeforeFirst()){
                System.out.println("No data to show");
            }else{
                ArrayList<GridPane> paneListReservationPaye = new ArrayList();

                while(rs.next()){
                    int idReservation = rs.getInt("id");
                    String nomComplet=Utils.DatabaseUtilis.getNomComplet(idReservation);

                    boolean aPayer = Utils.DatabaseUtilis.getReservationState(idReservation);
                    float montant = Utils.DatabaseUtilis.getMontantApayer(idReservation);
                    System.out.println(montant);
                    String typeHebergement = Utils.DatabaseUtilis.getTypeHebergement(idReservation);
                    GridPane gridPane = createPane(nomComplet,montant,aPayer,typeHebergement);
                    if(aPayer) {
                        paneListReservationPaye.add(gridPane);
                    }
//                    }


                }
                return paneListReservationPaye;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Utils.DatabaseUtilis.closeConnection();
        return null;
    }

    private GridPane createPaneErrorNoData(){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: #ffffff; -fx-font-size: 20px; -fx-border-radius: 5px; -fx-border-color: black;" );
        gridPane.setPrefSize(300,150);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        Label label = new Label("No data to show");
        gridPane.getChildren().add(label);
        return gridPane;

    }
    private GridPane createPane(String nomComplet,float montant, boolean aPayer,String typeHebergement){
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #ffffff; -fx-font-size: 20px; -fx-border-radius: 5px; -fx-border-color: black;" );
        gridPane.setPrefSize(300,150);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        Label label = new Label("Nom :");
        GridPane.setConstraints(label,0,0);
        Label nameLabel = new Label();
        nameLabel.setText(nomComplet);
        GridPane.setConstraints(nameLabel,1,0);


        Label label2 = new Label("Etat reservation");
        GridPane.setConstraints(label2, 0,1);
        Label etatReservationLabel = new Label();
        if(aPayer){
            etatReservationLabel.setText("a payer");
        }else{
            etatReservationLabel.setText("n'a pas payer");
        }

        GridPane.setConstraints(etatReservationLabel, 1,1);

        Label label3 = new Label("Montant a payer");
        GridPane.setConstraints(label3, 0,2);
        Label montantLabel = new Label();
        montantLabel.setText(String.valueOf(montant));
        GridPane.setConstraints(montantLabel,1,2);

        Label label4 = new Label("Type Hebergement");
        GridPane.setConstraints(label4, 0,3);
        Label typeHebergementLabel = new Label();
        typeHebergementLabel.setText(typeHebergement);
        GridPane.setConstraints(typeHebergementLabel,1,3);

        gridPane.getChildren().addAll(label,nameLabel,label2,etatReservationLabel,label3,montantLabel,label4,typeHebergementLabel);

        return gridPane;
    }
}

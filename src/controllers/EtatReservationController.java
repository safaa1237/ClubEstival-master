package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EtatReservationController implements Initializable {


    @FXML
    public AnchorPane infoEtatRervation;

    @FXML
    public Label nameText;

    @FXML
    public ImageView imageEtatReservation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utils.DatabaseUtilis.connection();
        String nomComplet=Utils.DatabaseUtilis.getNomComplet(1);
//        if(nomComplet == null){
//            System.out.println("Walo to afficher");
//        }
        boolean aPayer = Utils.DatabaseUtilis.getReservationState(123);
//        if (aPayer){
//            File file = new File("/images/yes.png");
//            Image image = new Image(file.toURI().toString());
//            imageEtatReservation.setImage(image);
//        }else{
//            File file = new File("/images/no.png");
//            Image image = new Image(file.toURI().toString());
//            imageEtatReservation.setImage(image);
//            System.out.println(aPayer);
//
//        }
//        nameText.setText(nomComplet);
//        System.out.println(nomComplet);
        float montant = Utils.DatabaseUtilis.getMontantApayer(123);
        GridPane gridPane = createPane(nomComplet,montant,aPayer);
        infoEtatRervation.getChildren().addAll(gridPane);
        Utils.DatabaseUtilis.closeConnection();
    }

    private GridPane createPane(String nomComplet,float montant, boolean aPayer){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(300,300);
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
        montantLabel.setText(Float.toString(montant));
        GridPane.setConstraints(montantLabel,1,2);

        gridPane.getChildren().addAll(label,nameLabel,label2,etatReservationLabel,label3,montantLabel);

        return gridPane;
    }
}

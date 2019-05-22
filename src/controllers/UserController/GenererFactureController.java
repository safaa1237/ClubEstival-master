package controllers.UserController;

import Utils.DatabaseUtilis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static org.apache.pdfbox.pdmodel.PDDocument.load;

public class GenererFactureController implements Initializable {

    public void genererLaFacture(int id){
        DatabaseUtilis.connection();

        //typeHeberegment done
//            String typeHebergement = Utils.DatabaseUtilis.getTypeHebergement(1);
//            System.out.println(typeHebergement);
//            //typeRestauration done
//            String typeRestauration = Utils.DatabaseUtilis.getTypeRestauration(1);
//            System.out.println(typeRestauration);
//            //Date depart et arrive done
//            String dateArrive = Utils.DatabaseUtilis.getDateArrive(1);
//            System.out.println(dateArrive);
//            String dateDepart = Utils.DatabaseUtilis.getDateDepart(1);
//            System.out.println(dateDepart);
//            //MontantTotal done
//            float montantTotal = Utils.DatabaseUtilis.getMontantApayer(1);
//            System.out.println(montantTotal);
//            //prixHebergement done
//            float prixHebergement = getPrixHebergement(typeHebergement);
//            System.out.println(prixHebergement);
//
//        float prixRestauration = getPrixRestauration(typeRestauration);
//        System.out.println(prixRestauration);
//        //Nombre personne done
//        int nbrPersonne = Utils.DatabaseUtilis.getNombrePersonne(1);
//        System.out.println(nbrPersonne);
//
//        String ref_facture = Utils.DatabaseUtilis.getReservationRef(1);
//        System.out.println(ref_facture);

//        try {
//            System.out.println( load(getClass().getClassLoader().getResourceAsStream("facture.pdf")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (PDDocument pdfDocument = load(getClass().getClassLoader().getResourceAsStream("facture.pdf")))
        {
//            System.out.println(pdfDocument.getNumberOfPages());
            // get the document catalog
            PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

            // as there might not be an AcroForm entry a null check is necessary
            if (acroForm != null)
            {
                //Id of the user who has reserved

                // Retrieve an individual field and set its value.

                PDTextField refReservationField = (PDTextField) acroForm.getField( "ref_reserv" );
                refReservationField.setValue(String.valueOf(id));

                PDTextField typeHebergementfield = (PDTextField) acroForm.getField( "TypeHebergement" );
                String typeHebergement = DatabaseUtilis.getTypeHebergement(id);
                typeHebergementfield.setValue(typeHebergement);

                PDTextField typeRestaurationField =  (PDTextField) acroForm.getField( "TypeRestauration" );
                String typeRestauration = DatabaseUtilis.getTypeRestauration(id);
                typeRestaurationField.setValue(typeRestauration);
//
                PDTextField montantField =  (PDTextField) acroForm.getField( "Total");
                float montantTotal = DatabaseUtilis.getMontantApayer(id);
                montantField.setValue(Float.toString(montantTotal));
                System.out.println(montantTotal);
//
                PDTextField dateArriveField = (PDTextField) acroForm.getField("date_arrive");
                String dateArrive = DatabaseUtilis.getDateDepart(id);
                dateArriveField.setValue(dateArrive);

                PDTextField dateDepartField = (PDTextField) acroForm.getField("date_depart");
                String dateDepart = DatabaseUtilis.getDateArrive(id);
                dateDepartField.setValue(dateDepart);

                PDTextField prixResturationField = (PDTextField) acroForm.getField("HT_restauration");
                float prixRestauration = getPrixRestauration(typeRestauration);
                prixResturationField.setValue(String.valueOf(prixRestauration));


                PDTextField prixHebergementField = (PDTextField) acroForm.getField("HT_hebergement");
                float prixHebergement = getPrixHebergement(typeHebergement);
                prixHebergementField.setValue(String.valueOf(prixHebergement));
//
//
                PDTextField nbrPersonneField = (PDTextField) acroForm.getField("nb_personne");
                int nbrPersonne = DatabaseUtilis.getNombrePersonne(id);
                nbrPersonneField.setValue(String.valueOf(nbrPersonne));
//
                //Date generation de la facture
                PDTextField dateFactureField = (PDTextField) acroForm.getField("Date facture");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String dateFacture = dtf.format(now);
                dateFactureField.setValue(dateFacture);

                PDTextField refFactureField = (PDTextField) acroForm.getField("ref_facture");
                String ref_facture = DatabaseUtilis.getReservationRef(id);
                refFactureField.setValue(ref_facture);

                // If a field is nested within the form tree a fully qualified name
                // might be provided to access the field.

//                PDTextField field = (PDTextField) acroForm.getField( "HT_hebergement" );
//                field.setValue("40");
            }

            // Save and close the filled out form.
            pdfDocument.save("./newfacture.pdf");
        }
        catch(Exception exeption){
            System.out.println(exeption.getMessage());


        }

        DatabaseUtilis.closeConnection();

    }


    private float getPrixRestauration(String typeRestauration){
        if(typeRestauration.equals("Demi Pension") ){
            return 50;
        }else if (typeRestauration.equals("Pension Complete")){
            return 100;
        }else if(typeRestauration.equals("Restauration Personnelle")){
            return 70;
        }
        return -1;
    }
    private float getPrixHebergement(String typeHebergement){
        if(typeHebergement.equals("Appartement") ){
            return 300;
        }else if (typeHebergement.equals("Bungalow")){
            return 400;
        }else if(typeHebergement.equals("Caravane")){
            return 350;
        }
        return -1;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}

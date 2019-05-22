package controllers.UserController;

import Connectivity.ConnectionClass;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import controllers.MainViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class loginController  implements Initializable {
    @FXML
    private JFXButton signup , btn_a , log;

    public JFXTextField TF_cin;
    public JFXTextField TF_prenom;
    public JFXTextField TF_usernamesup;
    public ImageView img1;

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer2;
    @FXML
    private JFXButton signin;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label s1;
    @FXML
    private Label s2;
    @FXML
    private Label s3;
    @FXML
    private Label a2;
    @FXML
    private Label b2;
    @FXML
    private Label a1;
    @FXML
    private Label b1;
    @FXML
    private Label label;
    @FXML
    private JFXButton btnsignup;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private JFXTextField TF_nom;
    @FXML
    private JFXTextField TF_email;
    @FXML
    private JFXPasswordField TF_passwordup;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label n3;
    @FXML
    private AnchorPane layer1;

    Image image1=new Image(getClass().getClassLoader().getResourceAsStream("bg.jpg"));
    Image image2=new Image(getClass().getClassLoader().getResourceAsStream("bg2.jpg"));
    public static int idClient = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        RequiredFieldValidator notvide=new RequiredFieldValidator();
        notvide.setMessage("Le champ est vide");



        TF_cin.getValidators().add(notvide);
        TF_cin.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal )  TF_cin.validate();
        });
        TF_prenom.getValidators().add(notvide);
        TF_prenom.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal )  TF_prenom.validate();
        });
        TF_email.getValidators().add(notvide);
        TF_email.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal )  TF_email.validate();
        });
        TF_nom.getValidators().add(notvide);
        TF_nom.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal )  TF_nom.validate();
        });

        TF_usernamesup.getValidators().add(notvide);
        TF_usernamesup.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal )  TF_usernamesup.validate();
        });
        username.getValidators().add(notvide);
        username.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal )  username.validate();
        });
        password.getValidators().add(notvide);
        password.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal )  password.validate();
        });

        // email validator
        RegexValidator emailvalidator=new RegexValidator();
        emailvalidator.setMessage("Email Invalid");
        emailvalidator.setRegexPattern("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
        TF_email.getValidators().add(emailvalidator);
        signup.setVisible(false);

        b2.setVisible(false);
        btnsignin.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        TF_nom.setVisible(true);
        TF_email.setVisible(true);
        TF_passwordup.setVisible(true);
        TF_usernamesup.setVisible(true);
        TF_prenom.setVisible(true);
        TF_cin.setVisible(true);
    }

    @FXML
    private void btn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);
        slide.setToX(491);
        slide.play();
        img1.setImage(image2);
        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);
        b2.setVisible(true);
        signup.setVisible(true);
        signin.setVisible(false);
        a2.setVisible(false);
        btnsignup.setVisible(false);
        username.setVisible(true);
        password.setVisible(true);
        TF_nom.setVisible(false);
        TF_email.setVisible(false);
        TF_passwordup.setVisible(false);
        TF_usernamesup.setVisible(false);
        TF_prenom.setVisible(false);
        TF_cin.setVisible(false);
        slide.setOnFinished((e->{
        }));
    }
    @FXML
    private void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);
        slide.setToX(0);
        slide.play();
        img1.setImage(image1);
        layer1.setTranslateX(0);
        btnsignin.setVisible(false);
        b2.setVisible(false);
        signup.setVisible(false);
        signin.setVisible(true);
        a2.setVisible(true);
        btnsignup.setVisible(true);
        username.setVisible(false);
        password.setVisible(false);
        TF_nom.setVisible(true);
        TF_email.setVisible(true);
        TF_passwordup.setVisible(true);
        TF_usernamesup.setVisible(true);
        TF_prenom.setVisible(true);
        TF_cin.setVisible(true);
        slide.setOnFinished((e->{
        }));
    }
    private int id_user(String login){
        PreparedStatement pst=null;
        ResultSet rs=null;
        ResultSetMetaData rsmd=null;
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql ="Select id from personnes where login = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,login);
            rs = pst.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
            else {
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    private boolean login_exist(String login){
        Statement st=null;
        ResultSet rs=null;
        ResultSetMetaData rsmd=null;
        boolean trouve = false;
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "Select login From personnes";
        try{
            st = connection.createStatement();
            rs= st.executeQuery(sql);
            rsmd=rs.getMetaData();
            int i;
            while(rs.next()) {
                if(rs.getString(1).equals(login)){
                    trouve = true;
                }
            }
            if (trouve == true){
                return true;
            }
            else {
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private boolean email_exist(String email){
        Statement st=null;
        ResultSet rs=null;
        ResultSetMetaData rsmd=null;
        boolean trouve = false;
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "Select email From client";
        try{
            st = connection.createStatement();
            rs= st.executeQuery(sql);
            rsmd=rs.getMetaData();
            int i;
            while(rs.next()) {
                if(rs.getString(1).equals(email)){
                    trouve = true;
                }
            }
            if (trouve == true){
                return true;
            }
            else {
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @FXML
    private void btnsignup(MouseEvent event) {
        if (login_exist(TF_usernamesup.getText()) == true){
            label.setText("Login existe, réessayez!");
        }
        else{
            if (email_exist(TF_email.getText())){
                label.setText("email existe, réessayez!");
            }
            else{
                PreparedStatement pst=null;
                PreparedStatement pst1=null;
                PreparedStatement pst2 = null;
                ResultSet rs=null;
                ResultSetMetaData rsmd=null;
                boolean trouve = false;
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String sql = "insert into personnes (firstname,lastname,login,password) values (?,?,?,?)";
                String sql1 ="insert into client (id,cin,email) values (?,?,?)";
                try{
                    pst= connection.prepareStatement(sql);
                    pst.setString(1,TF_prenom.getText());
                    pst.setString(2,TF_nom.getText());
                    pst.setString(3,TF_usernamesup.getText());
                    pst.setString(4,TF_passwordup.getText());
                    int b=pst.executeUpdate();
                    int id = id_user(TF_usernamesup.getText());
                    pst1=connection.prepareStatement(sql1);
                    pst1.setInt(1,id);
                    pst1.setString(2,TF_cin.getText());
                    pst1.setString(3,TF_email.getText());
                    int a;
                    a = pst1.executeUpdate();
                    if (b == 1 && a == 1){
                        label.setText("Inscription réuissite! Connectez-vous!");
                        btn(event);
                        TF_passwordup.clear();
                        TF_nom.clear();
                        TF_prenom.clear();
                        TF_cin.clear();
                        TF_email.clear();
                        TF_usernamesup.clear();
                    }
                    else{
                        label.setText("Registration failed!");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    private void sign(MouseEvent event) throws IOException {
        Statement st=null;
        ResultSet rs=null;
        ResultSetMetaData rsmd=null;
        boolean trouve = false;
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "Select login,password,id From personnes";


        try{
            st = connection.createStatement();
            rs= st.executeQuery(sql);
            rsmd=rs.getMetaData();
            int i;
            while(rs.next()) {
                if(rs.getString(1).equals(username.getText()) && rs.getString(2).equals(password.getText())){
                    trouve = true;
                    idClient = rs.getInt("id");

                }
            }
            if (username.getText().equals("admin") && password.getText().equals("admin")){
                StackPane pane = FXMLLoader.load(getClass().getResource("/views/main.fxml"));

                Stage stage = (Stage) signup.getScene().getWindow();
                // do what you have to do
                stage.close();

                Scene login = new Scene(pane);
                Stage primaryStage = new  Stage();
                primaryStage.setScene(login);

                primaryStage.show();
            }
            if (trouve == true){

                StackPane pane = FXMLLoader.load(getClass().getResource("/views/mainuser.fxml"));

                Stage stage = (Stage) signup.getScene().getWindow();
                // do what you have to do
                stage.close();

                Scene login = new Scene(pane);
                Stage primaryStage = new  Stage();
                primaryStage.setScene(login);

                primaryStage.show();

            }

            else {
                label.setText("Login ou mot de passe éronné!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

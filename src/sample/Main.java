package sample;

import Utils.Constants;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource(Constants.MAINVIEW));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1200, 760);
        scene.getStylesheets().add("views/style.css");
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package net.wvdr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that launches the application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/image-converter.fxml"));
        Parent root = (Parent)loader.load();
        ImageController controller = (ImageController) loader.getController();
        controller.setupStageAndFileChooser(primaryStage); // or what you want to do

        primaryStage.setTitle("Image Converter");
        primaryStage.setScene(new Scene(root, 500, 450));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

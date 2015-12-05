package net.wvdr;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Controller behind the only view in the application.
 */
public class ImageController {
    private Stage stage;
    private FileChooser fileChooser;

    public TextField hex;
    public TextField base64;
    public ImageView imageView;

    public void openImage(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(stage);

        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setImage(img);
        hex.setText(ImageConverter.imgToHexString(img, "jpg"));
        base64.setText(ImageConverter.imgToBase64String(img, "jpg"));
    }

    public void setupStageAndFileChooser(Stage primaryStage) {
        this.stage = primaryStage;
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
    }

    public void convertFromBase64(ActionEvent actionEvent) {
        String base64image = base64.getText();
        BufferedImage img = ImageConverter.base64StringToImg(base64image);
        setImage(img);

        hex.setText(ImageConverter.imgToHexString(img, "jpg"));
    }

    public void convertFromHex(ActionEvent actionEvent) {
        String hexImage = hex.getText();
        BufferedImage img = ImageConverter.hexStringToImg(hexImage);
        setImage(img);

        base64.setText(ImageConverter.imgToBase64String(img, "jpg"));
    }

    private void setImage(BufferedImage img) {
        imageView.setImage(SwingFXUtils.toFXImage(img, null));
    }
}

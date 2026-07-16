package com.bioskop.helloworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    public void initialize() {
        Image image = new Image(
                getClass().getResource("/Image/BioskopBackground.png").toExternalForm()
        );
        backgroundImage.setImage(image);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(
                getClass().getResource("Dashboard.fxml")
        );

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
}
package com.bioskop.helloworld;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {


    @FXML
    private ImageView backgroundImage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {

        Image image = new Image(
                getClass().getResource("/Image/BioskopBackground.png").toExternalForm()
        );

        backgroundImage.setImage(image);
    }

    @FXML
    protected void login() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equalsIgnoreCase("BioskopKita") &&
                password.equals("Kasir123456")) {

            statusLabel.setText("Selamat Datang Di Bioskop Kawan!!");
        } else {
            statusLabel.setText("Maaf, username atau password anda salah, silahkan coba lagi!");
        }
    }
}






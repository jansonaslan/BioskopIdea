package com.bioskop.helloworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void initialize() {

        Image bg = new Image(
                getClass()
                        .getResource("/Image/BioskopBackground.png")
                        .toExternalForm()
        );

        backgroundImage.setImage(bg);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        // Validasi jika kosong
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username dan Password harus diisi!");
            alert.showAndWait();
            return;
        }

        // Login Admin
        if (username.equals("admin") && password.equals("admin123")) {

            Session.username = username;
            Session.role = "admin";

            bukaDashboard(event);

        }

        // Login Kasir
        else if (username.equals("kasir") && password.equals("kasir123")) {

            Session.username = username;
            Session.role = "kasir";

            bukaDashboard(event);

        }

        // Login gagal
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username atau Password salah!");
            alert.showAndWait();

        }
    }

    private void bukaDashboard(ActionEvent event) throws IOException {

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
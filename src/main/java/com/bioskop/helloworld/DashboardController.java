package com.bioskop.helloworld;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class DashboardController {

    // Agar dapat dipanggil dari controller lain
    private static DashboardController instance;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button btnPengaturan;

    @FXML
    private Label lblUser;

    @FXML
    public void initialize() {

        instance = this;

        // Menampilkan username yang sedang login
        if (lblUser != null) {
            lblUser.setText("👤 " + Session.username);
        }

        // Jika login sebagai kasir, sembunyikan menu pengaturan
        if ("kasir".equals(Session.role)) {
            if (btnPengaturan != null) {
                btnPengaturan.setVisible(false);
                btnPengaturan.setManaged(false);
            }
        }

        // Halaman pertama
        loadPage("DashboardHome.fxml");
    }

    public static DashboardController getInstance() {
        return instance;
    }

    /**
     * Digunakan controller lain untuk mengganti isi contentPane
     */
    public void setContent(Parent page) {

        contentPane.getChildren().clear();
        contentPane.getChildren().add(page);

        AnchorPane.setTopAnchor(page, 0.0);
        AnchorPane.setBottomAnchor(page, 0.0);
        AnchorPane.setLeftAnchor(page, 0.0);
        AnchorPane.setRightAnchor(page, 0.0);
    }

    /**
     * Memuat halaman ke dalam contentPane
     */
    public void loadPage(String fxml) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    Objects.requireNonNull(getClass().getResource(fxml))
            );

            Parent page = loader.load();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(page);

            AnchorPane.setTopAnchor(page, 0.0);
            AnchorPane.setBottomAnchor(page, 0.0);
            AnchorPane.setLeftAnchor(page, 0.0);
            AnchorPane.setRightAnchor(page, 0.0);

        } catch (IOException e) {

            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Halaman gagal dimuat");
            alert.setContentText("Tidak dapat membuka file: " + fxml);
            alert.showAndWait();
        }
    }

    /**
     * Menu Dashboard
     */
    @FXML
    private void showDashboard() {
        loadPage("DashboardHome.fxml");
    }

    /**
     * Menu Pengaturan
     */
    @FXML
    private void showPengaturan() {
        loadPage("Pengaturan.fxml");
    }

    /**
     * Logout
     */
    @FXML
    private void logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Konfirmasi Logout");
        alert.setContentText("Apakah Anda yakin ingin logout?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            // Hapus session
            Session.username = "";
            Session.role = "";

            try {

                FXMLLoader loader = new FXMLLoader(
                        Objects.requireNonNull(getClass().getResource("hello-view.fxml"))
                );

                Parent root = loader.load();

                Stage stage = (Stage) contentPane.getScene().getWindow();

                double width = stage.getWidth();
                double height = stage.getHeight();
                boolean maximized = stage.isMaximized();

                Scene scene = new Scene(root, width, height);

                stage.setScene(scene);
                stage.setTitle("CineMax");
                stage.setMaximized(maximized);
                stage.centerOnScreen();
                stage.show();

            } catch (IOException e) {

                e.printStackTrace();

                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Logout Gagal");
                error.setContentText("Tidak dapat membuka halaman login.");
                error.showAndWait();
            }
        }
    }
}
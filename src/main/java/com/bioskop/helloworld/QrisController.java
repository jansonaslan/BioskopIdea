package com.bioskop.helloworld;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class QrisController implements Initializable {

    @FXML
    private VBox qrisPane;

    @FXML
    private VBox successPane;

    @FXML
    private ImageView qrisImage;

    @FXML
    private Label totalLabel;

    @FXML
    private Label statusLabel;

    private final int biayaAdmin = 2500;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ===============================
        // HITUNG TOTAL PEMBAYARAN
        // ===============================

        int subtotal = BookingData.hargaTiket * BookingData.kursi.size();
        int total = subtotal + biayaAdmin;

        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance(
                        new Locale("id", "ID"));

        totalLabel.setText(rupiah.format(total));

        statusLabel.setText("Menunggu Pembayaran");

        // ===============================
        // TAMPILKAN QRIS
        // ===============================

        try {

            Image image = new Image(
                    getClass().getResourceAsStream("/Image/qris.png"));

            qrisImage.setImage(image);

        } catch (Exception e) {

            System.out.println("Gagal memuat gambar QRIS.");

        }

        // ===============================
        // SIMULASI PEMBAYARAN
        // ===============================

        simulasiPembayaran();
    }

    // =====================================
    // SIMULASI PEMBAYARAN BERHASIL
    // =====================================

    private void simulasiPembayaran() {

        PauseTransition tunggu =
                new PauseTransition(Duration.seconds(5));

        tunggu.setOnFinished(event -> {

            // Status berubah
            statusLabel.setText("Pembayaran Berhasil");

            // Sembunyikan QR
            qrisPane.setVisible(false);
            qrisPane.setManaged(false);

            // Tampilkan halaman sukses
            successPane.setVisible(true);
            successPane.setManaged(true);

            // Simpan data pembayaran
            BookingData.metodePembayaran = "QRIS";
            BookingData.biayaAdmin = biayaAdmin;

            BookingData.nomorTransaksi =
                    "CMX-" + (100000 + (int) (Math.random() * 900000));

            // Tunggu 2 detik
            PauseTransition selesai =
                    new PauseTransition(Duration.seconds(2));

            selesai.setOnFinished(e -> {

                // Tutup popup QRIS
                Stage stage =
                        (Stage) successPane.getScene().getWindow();

                stage.close();

                // Buka halaman Struk
                DashboardController
                        .getInstance()
                        .loadPage("Struk.fxml");

            });

            selesai.play();

        });

        tunggu.play();

    }

}
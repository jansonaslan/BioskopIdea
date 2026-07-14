package com.bioskop.helloworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class PembayaranController implements Initializable {

    @FXML
    private ImageView posterImage;

    @FXML
    private Label judulLabel;

    @FXML
    private Label studioLabel;

    @FXML
    private Label tanggalLabel;

    @FXML
    private Label jamLabel;

    @FXML
    private Label kursiLabel;

    @FXML
    private Label jumlahLabel;

    @FXML
    private Label hargaLabel;

    @FXML
    private Label adminLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private ComboBox<String> metodeBox;

    @FXML
    private Button btnBayar;

    @FXML
    private Button btnBatal;

    @FXML
    private Button btnKembali;

    // ==========================
    // DATA PEMBAYARAN
    // ==========================

    private int hargaTiket;
    private int jumlahTiket;
    private final int biayaAdmin = 2500;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        metodeBox.getItems().addAll(
                "QRIS",
                "Cash"
        );

        metodeBox.getSelectionModel().selectFirst();

        // Ambil data dari BookingData
        judulLabel.setText(BookingData.judulFilm);
        studioLabel.setText(BookingData.studio);
        tanggalLabel.setText(BookingData.tanggal);
        jamLabel.setText(BookingData.jam);

        kursiLabel.setText(
                String.join(", ", BookingData.kursi)
        );

        if (posterImage != null) {
            posterImage.setImage(BookingData.posterFilm);
        }

        jumlahTiket = BookingData.kursi.size();
        hargaTiket = BookingData.hargaTiket;

        hitungTotal();
    }

    // ==========================
    // HITUNG TOTAL
    // ==========================

    private void hitungTotal() {

        int subtotal = hargaTiket * jumlahTiket;
        int total = subtotal + biayaAdmin;

        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance(
                        new Locale("id", "ID"));

        jumlahLabel.setText(String.valueOf(jumlahTiket));
        hargaLabel.setText(rupiah.format(subtotal));
        adminLabel.setText(rupiah.format(biayaAdmin));
        totalLabel.setText(rupiah.format(total));
    }

    // ==========================
    // BAYAR
    // ==========================

    @FXML
    private void bayar(ActionEvent event) throws IOException {

        if (metodeBox.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Silakan pilih metode pembayaran.");
            alert.showAndWait();
            return;
        }

        // Simpan data pembayaran
        BookingData.metodePembayaran = metodeBox.getValue();
        BookingData.biayaAdmin = biayaAdmin;
        BookingData.nomorTransaksi =
                "CMX-" + (100000 + (int) (Math.random() * 900000));

        if (metodeBox.getValue().equals("QRIS")) {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("QRIS.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Pembayaran QRIS");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pembayaran Cash");
            alert.setHeaderText(null);
            alert.setContentText("Silakan lakukan pembayaran di kasir.");
            alert.showAndWait();

            showSuccess();
        }
    }

    // ==========================
    // ALERT BERHASIL
    // ==========================

    private void showSuccess() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Pembayaran");

        alert.setHeaderText("Pembayaran Berhasil");

        alert.setContentText(
                "Pembayaran berhasil.\n\n" +
                        "Silakan cetak struk pembayaran."
        );

        alert.showAndWait();

        DashboardController.getInstance().loadPage("Struk.fxml");
    }

    // ==========================
    // KEMBALI
    // ==========================

    @FXML
    private void kembali() {

        DashboardController.getInstance().loadPage("Seat.fxml");

    }
}
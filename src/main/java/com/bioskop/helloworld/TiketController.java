package com.bioskop.helloworld;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class TiketController implements Initializable {

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
    private Label hargaLabel;

    @FXML
    private Label kodeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tampilkanData();

    }

    // =====================================================
    // MENAMPILKAN DATA TIKET
    // =====================================================

    private void tampilkanData() {

        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        judulLabel.setText(BookingData.judulFilm);

        studioLabel.setText(BookingData.studio);

        tanggalLabel.setText(BookingData.tanggal);

        jamLabel.setText(BookingData.jam);

        kursiLabel.setText(String.join(", ", BookingData.kursi));

        hargaLabel.setText(
                rupiah.format(
                        BookingData.hargaTiket * BookingData.kursi.size()
                )
        );

        kodeLabel.setText(BookingData.nomorTransaksi);

        if (BookingData.posterFilm != null) {

            posterImage.setImage(BookingData.posterFilm);

        }

    }
    // =====================================================
    // CETAK TIKET
    // =====================================================

    @FXML
    private void cetakTiket() {

        printNode(judulLabel.getScene().getRoot());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cetak Tiket");
        alert.setHeaderText(null);
        alert.setContentText("Tiket berhasil dicetak.");
        alert.showAndWait();

    }

    // =====================================================
    // SELESAI
    // =====================================================

    @FXML
    private void selesai() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Selesai");
        alert.setHeaderText(null);
        alert.setContentText("Terima kasih telah menggunakan CineMax.");

        alert.showAndWait();

        DashboardController.getInstance().loadPage("DashboardHome.fxml");

    }

    // =====================================================
    // PRINT NODE
    // =====================================================

    private void printNode(Node node) {

        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null) {

            boolean lanjut = job.showPrintDialog(node.getScene().getWindow());

            if (lanjut) {

                boolean sukses = job.printPage(node);

                if (sukses) {

                    job.endJob();

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal mencetak tiket.");
                    alert.showAndWait();

                }

            }

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Printer");
            alert.setHeaderText(null);
            alert.setContentText("Printer tidak ditemukan.");
            alert.showAndWait();

        }

    }

}

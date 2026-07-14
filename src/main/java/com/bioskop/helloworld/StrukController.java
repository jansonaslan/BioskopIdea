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

public class StrukController implements Initializable {

    @FXML
    private ImageView posterImage;

    @FXML
    private Label transaksiLabel;

    @FXML
    private Label tanggalLabel;

    @FXML
    private Label metodeLabel;

    @FXML
    private Label filmLabel;

    @FXML
    private Label studioLabel;

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

    private final int biayaAdmin = 2500;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tampilkanData();

    }

    /**
     * Menampilkan data transaksi
     */
    private void tampilkanData() {

        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        transaksiLabel.setText(BookingData.nomorTransaksi);

        tanggalLabel.setText(BookingData.tanggal);

        metodeLabel.setText(BookingData.metodePembayaran);

        filmLabel.setText(BookingData.judulFilm);

        studioLabel.setText(BookingData.studio);

        jamLabel.setText(BookingData.jam);

        kursiLabel.setText(String.join(", ", BookingData.kursi));

        jumlahLabel.setText(String.valueOf(BookingData.kursi.size()));

        int subtotal = BookingData.kursi.size() * BookingData.hargaTiket;

        int total = subtotal + biayaAdmin;

        hargaLabel.setText(rupiah.format(subtotal));

        adminLabel.setText(rupiah.format(biayaAdmin));

        totalLabel.setText(rupiah.format(total));

        if (BookingData.posterFilm != null) {
            posterImage.setImage(BookingData.posterFilm);
        }

    }
    // =====================================================
    // CETAK STRUK
    // =====================================================

    @FXML
    private void cetakStruk() {

        printNode(totalLabel.getScene().getRoot());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cetak");
        alert.setHeaderText(null);
        alert.setContentText("Struk berhasil dicetak.");
        alert.showAndWait();

    }

    // =====================================================
    // CETAK TIKET
    // =====================================================

    @FXML
    private void cetakTiket() {

        DashboardController.getInstance().loadPage("Tiket.fxml");

    }

    // =====================================================
    // SELESAI
    // =====================================================

    @FXML
    private void selesai() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Transaksi");

        alert.setHeaderText(null);

        alert.setContentText("Transaksi selesai.");

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

                }

            }

        }

    }

}
package com.bioskop.helloworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeatController {

    @FXML
    private FlowPane seatPane;

    @FXML
    private Label totalSeatLabel;

    @FXML
    private Label totalHargaLabel;

    @FXML
    private Button btnLanjut;

    @FXML
    private Button btnKembali;

    // Daftar kursi yang dipilih
    private final List<ToggleButton> selectedSeats = new ArrayList<>();

    // Harga tiket per kursi
    private final int hargaTiket = 35000;

    // Daftar kursi yang sudah terisi
    private final Set<String> bookedSeats = new HashSet<>();

    @FXML
    public void initialize() {

        btnLanjut.setDisable(true);

        totalSeatLabel.setText("0");
        totalHargaLabel.setText("Rp0");

        // Contoh kursi yang sudah terisi
        bookedSeats.add("A3");
        bookedSeats.add("A4");
        bookedSeats.add("B5");
        bookedSeats.add("C2");
        bookedSeats.add("D7");
        bookedSeats.add("F8");

        generateSeats();
    }

    // ==================================================
    // MEMBUAT KURSI
    // ==================================================

    private void generateSeats() {

        seatPane.getChildren().clear();

        String[] rows = {"A", "B", "C", "D", "E", "F"};

        for (String row : rows) {

            for (int i = 1; i <= 8; i++) {

                ToggleButton seat = new ToggleButton(row + i);

                seat.setPrefWidth(55);
                seat.setPrefHeight(45);

                seat.getStyleClass().add("seat-button");

                if (bookedSeats.contains(row + i)) {

                    seat.getStyleClass().add("seat-booked");
                    seat.setDisable(true);

                } else {

                    seat.setOnAction(this::pilihKursi);

                }

                seatPane.getChildren().add(seat);

            }

        }

    }
    // ==================================================
    // PILIH KURSI
    // ==================================================

    @FXML
    private void pilihKursi(ActionEvent event) {

        ToggleButton seat = (ToggleButton) event.getSource();

        if (seat.isSelected()) {

            if (selectedSeats.size() >= 6) {

                seat.setSelected(false);
                return;

            }

            selectedSeats.add(seat);

        } else {

            selectedSeats.remove(seat);

        }

        updateTotal();

    }

    // ==================================================
    // UPDATE TOTAL
    // ==================================================

    private void updateTotal() {

        int jumlah = selectedSeats.size();
        int total = jumlah * hargaTiket;

        totalSeatLabel.setText(String.valueOf(jumlah));
        totalHargaLabel.setText("Rp " + total);

        btnLanjut.setDisable(jumlah == 0);

    }

    // ==================================================
    // LANJUT PEMBAYARAN
    // ==================================================

    @FXML
    private void lanjutPembayaran() {

        // Simpan kursi yang dipilih
        BookingData.kursi.clear();

        for (ToggleButton seat : selectedSeats) {
            BookingData.kursi.add(seat.getText());
        }

        // Simpan harga tiket
        BookingData.hargaTiket = hargaTiket;

        System.out.println("===== KURSI DIPILIH =====");

        for (String kursi : BookingData.kursi) {
            System.out.println(kursi);
        }

        System.out.println("------------------------");
        System.out.println("Jumlah : " + BookingData.kursi.size());
        System.out.println("Total  : Rp " +
                (BookingData.kursi.size() * BookingData.hargaTiket));

        // Pindah ke halaman pembayaran
        DashboardController.getInstance().loadPage("Pembayaran.fxml");

    }

    // ==================================================
    // KEMBALI
    // ==================================================

    @FXML
    private void kembali() {

        DashboardController.getInstance().loadPage("detailFilm.fxml");

    }

}
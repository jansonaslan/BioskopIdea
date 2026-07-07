package com.bioskop.helloworld;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class DashboardController {

    @FXML
    private AnchorPane contentPane;

    @FXML
    public void initialize() {
        loadPage("DashboardHome.fxml");
    }

    private void loadPage(String fxml) {

        System.out.println("Mencari file: " + fxml);
        System.out.println("Lokasi: " + getClass().getResource(fxml));

        try {

            Parent page = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(fxml))
            );

            contentPane.getChildren().setAll(page);

            AnchorPane.setTopAnchor(page, 0.0);
            AnchorPane.setBottomAnchor(page, 0.0);
            AnchorPane.setLeftAnchor(page, 0.0);
            AnchorPane.setRightAnchor(page, 0.0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showDashboard() {
        System.out.println("Dashboard diklik");
        loadPage("DashboardHome.fxml");
    }

    @FXML
    private void showPenjualan() {
        System.out.println("Penjualan diklik");
        loadPage("HalamanPenjualan.fxml");
    }

    @FXML
    private void showFoodDrink() {
        System.out.println("Food & Drink diklik");
        loadPage("Makanan dan Minuman.fxml");
    }

    @FXML
    private void showJadwalFilm() {
        System.out.println("Jadwal Film diklik");
        loadPage("JadwalFilm.fxml");
    }

    @FXML
    private void showLaporan() {
        System.out.println("Laporan diklik");
        loadPage("LaporanHarian.fxml");
    }

    @FXML
    private void showCetakTiket() {
        System.out.println("Cetak Tiket diklik");
        loadPage("CetakTiket.fxml");
    }

    @FXML
    private void showPengaturan() {
        System.out.println("Pengaturan diklik");
        loadPage("Pengaturan.fxml");
    }
}
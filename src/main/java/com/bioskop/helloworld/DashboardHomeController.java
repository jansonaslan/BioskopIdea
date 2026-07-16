package com.bioskop.helloworld;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DashboardHomeController {

    @FXML private ImageView pacificRimImg;
    @FXML private ImageView moana1Img;
    @FXML private ImageView ingloriousImg;
    @FXML private ImageView Moana2Img;
    @FXML private ImageView carsImg;
    @FXML private ImageView upImg;
    @FXML private ImageView toyStoryImg;
    @FXML private ImageView tangledImg;
    @FXML private ImageView frozen1Img;
    @FXML private ImageView frozen2Img;
    @FXML private ImageView braveImg;
    @FXML private ImageView bigHeroImg;

    @FXML
    public void initialize() {

        loadImage(pacificRimImg, "/Image/pacificrim.jpg");
        loadImage(moana1Img, "/Image/moana1.jpeg");
        loadImage(ingloriousImg, "/Image/inglorious.jpg");
        loadImage(Moana2Img, "/Image/Moana2.jpg");
        loadImage(carsImg, "/Image/cars.jpg");
        loadImage(upImg, "/Image/up.jpg");
        loadImage(toyStoryImg, "/Image/toyStory.jpg");
        loadImage(tangledImg, "/Image/tangled.jpg");
        loadImage(frozen1Img, "/Image/frozen1.jpg");
        loadImage(frozen2Img, "/Image/frozen2.jpg");
        loadImage(braveImg, "/Image/brave.jpg");
        loadImage(bigHeroImg, "/Image/bigHero.jpg");

    }

    private void loadImage(ImageView imageView, String path) {

        if (imageView == null) return;

        var stream = getClass().getResourceAsStream(path);

        if (stream != null) {
            imageView.setImage(new Image(stream));
        }

    }

    // =====================================================
    // Membuka halaman detail film
    // =====================================================

    private void openFilm(String judul,
                          String genre,
                          String durasi,
                          String tahun,
                          String sutradara,
                          int harga,
                          String sinopsis,
                          String posterPath) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    Objects.requireNonNull(
                            getClass().getResource("detailFilm.fxml"))
            );

            Parent root = loader.load();

            DetailFilmController controller = loader.getController();

            controller.setFilm(
                    judul,
                    genre,
                    durasi,
                    tahun,
                    sutradara,
                    harga,
                    sinopsis,
                    new Image(
                            Objects.requireNonNull(
                                    getClass().getResourceAsStream(posterPath)
                            )
                    )
            );

            DashboardController.getInstance().setContent(root);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // =====================================================
    // PACIFIC RIM
    // =====================================================

    @FXML
    private void pilihpacificrim() {

        openFilm(
                "Pacific Rim",
                "Action, Sci-Fi",
                "2 Jam 11 Menit",
                "2013",
                "Guillermo del Toro",
                35000,
                "Ketika makhluk raksasa bernama Kaiju muncul dari sebuah celah antardimensi di dasar Samudra Pasifik, umat manusia berada di ambang kepunahan. Untuk melawan ancaman tersebut, berbagai negara bekerja sama menciptakan robot raksasa yang disebut Jaeger, yang dikendalikan oleh dua pilot melalui koneksi saraf yang disebut Drift.\n" +
                        "\n" +
                        "Mantan pilot Jaeger, Raleigh Becket, yang pensiun setelah kehilangan saudaranya dalam pertempuran, dipanggil kembali untuk bergabung dalam misi terakhir melawan para Kaiju. Ia dipasangkan dengan Mako Mori, seorang pilot muda yang memiliki tekad kuat untuk membalas dendam atas kehancuran yang disebabkan Kaiju.\n" +
                        "\n" +
                        "Bersama tim Jaeger dari berbagai negara, mereka harus menghadapi serangan Kaiju yang semakin kuat dan berusaha menutup portal tempat para monster itu berasal. Misi tersebut menjadi harapan terakhir umat manusia untuk menyelamatkan dunia dari kehancuran.",
                "/Image/pacificrim.jpg"
        );

    }

    // =====================================================
    // MOANA 1
    // =====================================================

    @FXML
    private void pilihMoana1() {

        openFilm(
                "Moana",
                "Animation, Adventure",
                "1 Jam 47 Menit",
                "2016",
                "Ron Clements",
                40000,
                "Moana (2016) mengisahkan Moana, putri pemberani dari suku Polinesia di Pulau Motunui, yang dipilih oleh samudra untuk mengembalikan jantung Dewi Te Fiti. Ditemani dewa Maui, ia mengarungi lautan berbahaya untuk menyelamatkan pulaunya dari kutukan kegelapan.",
                "/Image/moana1.jpeg"
        );

    }

    // =====================================================
    // FILM LAIN
    // =====================================================

    @FXML
    private void pilihinglorious() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihMoana2() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihcars() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihup() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihtoyStory() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihtangled() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihfrozen1() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihfrozen2() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihbrave() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihbigHero() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

}
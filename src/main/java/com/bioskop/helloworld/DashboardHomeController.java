package com.bioskop.helloworld;

import javafx.fxml.FXML;
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

        if (imageView == null) {
            System.out.println("ImageView belum terhubung untuk : " + path);
            return;
        }

        var stream = getClass().getResourceAsStream(path);

        if (stream == null) {
            System.out.println("Gambar tidak ditemukan : " + path);
            return;
        }

        imageView.setImage(new Image(stream));
    }

    @FXML
    private void pilihpacificrim() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

    @FXML
    private void pilihMoana1() {
        DashboardController.getInstance().loadPage("detailFilm.fxml");
    }

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
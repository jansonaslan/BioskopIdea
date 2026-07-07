package com.bioskop.helloworld;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DashboardHomeController {

    @FXML
    private ImageView pacificRimImg;

    @FXML
    private ImageView moana1Img;

    @FXML
    private ImageView ingloriousImg;

    @FXML
    private ImageView Moana2Img;

    @FXML
    public void initialize() {

        System.out.println(getClass().getResource("/Image/pacificrim.jpg"));
        System.out.println(getClass().getResource("/Image/moana1.jpeg"));
        System.out.println(getClass().getResource("/Image/inglorious.jpg"));
        System.out.println(getClass().getResource("/Image/Moana2.jpg"));

        pacificRimImg.setImage(
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/Image/pacificrim.jpg")))
        );

        moana1Img.setImage(
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/Image/moana1.jpeg")))
        );

        ingloriousImg.setImage(
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/Image/inglorious.jpg")))
        );

        Moana2Img.setImage(
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/Image/Moana2.jpg")))
        );
    }
}
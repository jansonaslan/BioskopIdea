package com.bioskop.helloworld;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DashboardController {

    @FXML
    private ImageView pacificRimImg;

    @FXML
    private ImageView moana1Img;

    @FXML
    private ImageView moana2Img;

    @FXML
    public void initialize() {

        Image pacificRim = new Image(
                getClass().getResource("/Image/pacificrim.jpg").toExternalForm()
        );

        pacificRimImg.setImage(pacificRim);

        Image moana = new Image(
                getClass().getResource("/Image/moana1.jpeg").toExternalForm()
        );

        moana1Img.setImage(moana);

        Image moana2 = new Image(
                getClass().getResource("/Image/Moana2.jpg").toExternalForm()
        );

        moana2Img.setImage(moana);

    }
}
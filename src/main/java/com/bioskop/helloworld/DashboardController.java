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
    public void initialize() {

        Image img = new Image(
                getClass().getResource("/Image/pacificrim.jpg").toExternalForm()
        );
        pacificRimImg.setImage(img);
    }
}

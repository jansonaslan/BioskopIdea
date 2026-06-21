module com.bioskop.helloworld {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.bioskop.helloworld to javafx.fxml;
    exports com.bioskop.helloworld;
}
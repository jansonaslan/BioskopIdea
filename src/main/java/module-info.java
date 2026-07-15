module com.bioskop.helloworld {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires java.net.http;
    requires org.json;
    requires com.google.zxing;
    requires javafx.swing;


    opens com.bioskop.helloworld to javafx.fxml;
    exports com.bioskop.helloworld;
}
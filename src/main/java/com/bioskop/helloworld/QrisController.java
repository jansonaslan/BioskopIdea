package com.bioskop.helloworld;

import com.bioskop.helloworld.api.ApiClient;
import com.bioskop.helloworld.dto.PaymentRequest;
import com.bioskop.helloworld.dto.PaymentResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class QrisController implements Initializable {

    @FXML
    private VBox qrisPane;

    @FXML
    private VBox successPane;

    @FXML
    private ImageView qrisImage;

    @FXML
    private Label totalLabel;

    @FXML
    private Label statusLabel;

    private final int biayaAdmin = 2500;

    private final ApiClient apiClient = new ApiClient();

    private String orderId;

    private Timeline pollingTimeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int subtotal =
                BookingData.hargaTiket * BookingData.kursi.size();

        int total =
                subtotal + biayaAdmin;

        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance(
                        new Locale("id", "ID"));

        totalLabel.setText(rupiah.format(total));

        statusLabel.setText("Menunggu Pembayaran");

        try {

            PaymentRequest request = new PaymentRequest();

            request.setOrderId("INV-" + System.currentTimeMillis());

            request.setAmount(total);

            PaymentResponse response =
                    apiClient.createQris(request);

            orderId = response.getOrderId();

            // Menampilkan QR dari qrString
            tampilkanQr(response.getQrString());

            BookingData.metodePembayaran = "QRIS";
            BookingData.biayaAdmin = biayaAdmin;
            BookingData.nomorTransaksi =
                    response.getTransactionId();

            startPolling();

        } catch (Exception e) {

            e.printStackTrace();

            statusLabel.setText("Gagal Membuat QRIS");

        }

    }

    private void tampilkanQr(String qrString) {

        try {

            BitMatrix matrix =
                    new MultiFormatWriter().encode(
                            qrString,
                            BarcodeFormat.QR_CODE,
                            300,
                            300);

            BufferedImage image =
                    new BufferedImage(
                            300,
                            300,
                            BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < 300; x++) {

                for (int y = 0; y < 300; y++) {

                    image.setRGB(
                            x,
                            y,
                            matrix.get(x, y)
                                    ? 0xFF000000
                                    : 0xFFFFFFFF);

                }

            }

            qrisImage.setImage(
                    SwingFXUtils.toFXImage(image, null));

        } catch (Exception e) {

            e.printStackTrace();

            statusLabel.setText("QRIS gagal ditampilkan");

        }

    }

    private void startPolling() {

        pollingTimeline = new Timeline(

                new KeyFrame(Duration.seconds(3), event -> {

                    new Thread(() -> {

                        try {

                            String status =
                                    apiClient.checkStatus(orderId);

                            System.out.println("Status Midtrans : " + status);

                            if ("settlement".equalsIgnoreCase(status)
                                    || "capture".equalsIgnoreCase(status)) {

                                pollingTimeline.stop();

                                Platform.runLater(this::showSuccess);

                            }

                        } catch (Exception e) {

                            e.printStackTrace();

                        }

                    }).start();

                })

        );

        pollingTimeline.setCycleCount(Animation.INDEFINITE);

        pollingTimeline.play();

    }

    private void showSuccess() {

        statusLabel.setText("Pembayaran Berhasil");

        qrisPane.setVisible(false);
        qrisPane.setManaged(false);

        successPane.setVisible(true);
        successPane.setManaged(true);

        PauseTransition selesai =
                new PauseTransition(Duration.seconds(2));

        selesai.setOnFinished(event -> {

            Stage stage =
                    (Stage) successPane.getScene().getWindow();

            stage.close();

            DashboardController
                    .getInstance()
                    .loadPage("Struk.fxml");

        });

        selesai.play();

    }

}
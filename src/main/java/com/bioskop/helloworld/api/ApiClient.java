package com.bioskop.helloworld.api;

import com.bioskop.helloworld.dto.PaymentRequest;
import com.bioskop.helloworld.dto.PaymentResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api/payment";

    private final HttpClient client = HttpClient.newHttpClient();

    public PaymentResponse createQris(PaymentRequest request)
            throws IOException, InterruptedException {

        JSONObject json = new JSONObject();

        json.put("orderId", request.getOrderId());
        json.put("amount", request.getAmount());

        HttpRequest httpRequest =
                HttpRequest.newBuilder()
                        .uri(URI.create(BASE_URL + "/qris"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                        .build();

        HttpResponse<String> response =
                client.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());

        JSONObject obj = new JSONObject(response.body());

        PaymentResponse payment = new PaymentResponse();

        payment.setOrderId(obj.optString("orderId"));
        payment.setTransactionId(obj.optString("transactionId"));
        payment.setPaymentType(obj.optString("paymentType"));
        payment.setTransactionStatus(obj.optString("transactionStatus"));
        payment.setQrCodeUrl(obj.optString("qrCodeUrl"));
        payment.setQrString(obj.optString("qrString"));
        payment.setQrImageUrl(obj.optString("qrImageUrl"));

        return payment;
    }

    /**
     * Mengecek status pembayaran dari backend
     */
    public String checkStatus(String orderId)
            throws IOException, InterruptedException {

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(BASE_URL + "/status/" + orderId))
                        .GET()
                        .build();

        HttpResponse<String> response =
                client.send(request,
                        HttpResponse.BodyHandlers.ofString());

        return response.body().trim();
    }

}
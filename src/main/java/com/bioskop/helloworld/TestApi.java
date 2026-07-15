package com.bioskop.helloworld;

import com.bioskop.helloworld.api.ApiClient;
import com.bioskop.helloworld.dto.PaymentRequest;
import com.bioskop.helloworld.dto.PaymentResponse;

public class TestApi {

    public static void main(String[] args) throws Exception {

        ApiClient api = new ApiClient();

        PaymentRequest req = new PaymentRequest();
        req.setOrderId("INV" + System.currentTimeMillis());
        req.setAmount(50000);

        PaymentResponse res = api.createQris(req);

        System.out.println(res.getQrCodeUrl());

    }
}
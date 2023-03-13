package com.example.mynt;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class VoucherDiscountRequester {

    private static final String API_URL = "https://mynt-exam.mocklab.io/voucher";
    private static final String API_KEY = "apikey";

    public static double requestDiscount(String voucherCode) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(API_URL);

        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("X-API-Key", API_KEY);

        String jsonRequestBody = "{\"voucherCode\": \"" + voucherCode + "\"}";
        StringEntity requestEntity = new StringEntity(jsonRequestBody);
        httpPost.setEntity(requestEntity);

        HttpResponse response = httpClient.execute(httpPost);

        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            String responseBody = EntityUtils.toString(responseEntity);

            ObjectMapper objectMapper = new ObjectMapper();
            MyResponse myResponse = objectMapper.readValue(responseBody, MyResponse.class);

            return myResponse.getDiscount()/100;
        }
        return 1;
    }
}

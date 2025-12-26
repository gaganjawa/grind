package org.lld.designpatterns.creational.builder;

public class Client {

    public static void main(String[] args) {
        HttpRequest request = HttpRequest.builder()
                .method(HttpMethod.POST)
                .url("https://api.example.com/orders")
                .addHeader("Content-Type", "application/json")
                .addQueryParam("region", "IN")
                .body("{\"orderId\":123}")
                .timeout(5000)
                .retryPolicy(RetryPolicy.EXPONENTIAL)
                .authentication(new BearerAuth("token123"))
                .enableCompression(true)
                .build();
    }
}

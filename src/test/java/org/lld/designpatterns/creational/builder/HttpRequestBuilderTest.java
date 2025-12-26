package org.lld.designpatterns.creational.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpRequestBuilderTest {


    @Test
    void shouldBuildValidHttpRequest() {
        HttpRequest request = HttpRequest.builder()
                .method(HttpMethod.GET)
                .url("https://example.com")
                .addHeader("Accept", "application/json")
                .timeout(3000)
                .build();

        assertEquals(HttpMethod.GET, request.getMethod());
        assertEquals("https://example.com", request.getUrl());
        assertEquals("application/json", request.getHeaders().get("Accept"));
        assertEquals(3000, request.getTimeout());
    }

    @Test
    void shouldThrowExceptionWhenMethodMissing() {
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> HttpRequest.builder()
                        .url("https://example.com")
                        .build()
        );

        assertEquals("HTTP method is mandatory", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUrlMissing() {
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> HttpRequest.builder()
                        .method(HttpMethod.POST)
                        .build()
        );

        assertEquals("URL is mandatory", ex.getMessage());
    }

    @Test
    void shouldBeImmutable() {
        HttpRequest request = HttpRequest.builder()
                .method(HttpMethod.GET)
                .url("https://example.com")
                .addHeader("key", "value")
                .build();

        assertThrows(
                UnsupportedOperationException.class,
                () -> request.getHeaders().put("newKey", "newValue")
        );
    }

    @Test
    void shouldSupportAuthenticationStrategy() {
        Authentication auth = new BearerAuth("abc123");

        HttpRequest request = HttpRequest.builder()
                .method(HttpMethod.POST)
                .url("https://secure.api")
                .authentication(auth)
                .build();

        assertEquals("Bearer abc123", request.getAuthentication().getAuthenticationHeader());
    }
}

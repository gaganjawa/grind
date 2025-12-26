package org.lld.designpatterns.creational.builder;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    //Mandatory fields
    private HttpMethod method;
    private String url;

    //Optional fields
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private String body;
    private int timeout;
    private Authentication authentication;
    private RetryPolicy retryPolicy;
    private boolean compressionEnabled;

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public String getBody() {
        return body;
    }

    public int getTimeout() {
        return timeout;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }

    private HttpRequest(HttpRequestBuilder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.headers = builder.headers;
        this.queryParams = builder.queryParams;
        this.body = builder.body;
        this.timeout = builder.timeout;
        this.authentication = builder.authentication;
        this.retryPolicy = builder.retryPolicy;
        this.compressionEnabled = builder.compressionEnabled;
    }

    public static HttpRequestBuilder builder() {
        return new HttpRequestBuilder();
    }

    public static class HttpRequestBuilder {
        //Mandatory fields
        private HttpMethod method;
        private String url;

        //Optional fields
        private Map<String, String> headers = new HashMap<>();
        private Map<String, String> queryParams = new HashMap<>();
        private String body;
        private int timeout = 0;
        private Authentication authentication;
        private RetryPolicy retryPolicy = RetryPolicy.NONE;
        private boolean compressionEnabled = false;

        public HttpRequestBuilder() {
        }

        public HttpRequestBuilder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        public HttpRequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public HttpRequestBuilder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public HttpRequestBuilder addQueryParam(String key, String value) {
            this.queryParams.put(key, value);
            return this;
        }

        public HttpRequestBuilder body(String body) {
            this.body = body;
            return this;
        }

        public HttpRequestBuilder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpRequestBuilder authentication(Authentication authentication) {
            this.authentication = authentication;
            return this;
        }

        public HttpRequestBuilder retryPolicy(RetryPolicy retryPolicy) {
            this.retryPolicy = retryPolicy;
            return this;
        }

        public HttpRequestBuilder enableCompression(boolean compressionEnabled) {
            this.compressionEnabled = compressionEnabled;
            return this;
        }

        public HttpRequest build() {
            validate();
            return new HttpRequest(this);
        }

        private void validate() {
            if (this.method == null) {
                throw new IllegalStateException("HTTP Method is required");
            }
            if (this.url == null || this.url.isEmpty()) {
                throw new IllegalStateException("URL is required");
            }
        }
    }
}

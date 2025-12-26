package org.lld.designpatterns.creational.builder;

public class BearerAuth implements Authentication {
    private String token;

    public BearerAuth(String token) {
        this.token = token;
    }

    @Override
    public String getAuthenticationHeader() {
        return "Bearer " + token;
    }
}

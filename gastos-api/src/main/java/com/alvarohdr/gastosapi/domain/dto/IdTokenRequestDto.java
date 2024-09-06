package com.alvarohdr.gastosapi.domain.dto;

public class IdTokenRequestDto {

    private String idToken;

    public IdTokenRequestDto() {
    }

    public IdTokenRequestDto(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}